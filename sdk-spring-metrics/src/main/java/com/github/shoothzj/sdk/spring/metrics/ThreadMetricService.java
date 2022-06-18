/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.shoothzj.sdk.spring.metrics;

import com.google.common.util.concurrent.AtomicDouble;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.HashMap;

@Slf4j
@Service
public class ThreadMetricService {

    @Autowired
    private MeterRegistry meterRegistry;

    private final ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();

    private final HashMap<Long, ThreadMetricsAux> map = new HashMap<>();

    private final HashMap<Meter.Id, AtomicDouble> dynamicGauges = new HashMap<>();

    /**
     * one minutes
     */
    @Scheduled(cron = "0 * * * * ?")
    public void schedule() {
        final long[] allThreadIds = threadBean.getAllThreadIds();
        for (long threadId : allThreadIds) {
            final ThreadInfo threadInfo = threadBean.getThreadInfo(threadId);
            if (threadInfo == null) {
                continue;
            }
            final long threadNanoTime = getThreadCPUTime(threadId);
            if (threadNanoTime == 0) {
                // abnormal, clean map
                map.remove(threadId);
            }
            // check if map has data
            final long nanoTime = System.nanoTime();
            ThreadMetricsAux oldMetrics = map.get(threadId);
            if (oldMetrics != null) {
                double percent = (double) (threadNanoTime - oldMetrics.getUsedNanoTime())
                        / (double) (nanoTime - oldMetrics.getLastNanoTime());
                    handleDynamicGauge("jvm.threads.cpu", "threadName", threadInfo.getThreadName(), percent);
            }
            map.put(threadId, new ThreadMetricsAux(threadNanoTime, nanoTime));
        }
    }

    private void handleDynamicGauge(String meterName, String labelKey, String labelValue, double snapshot) {
        Meter.Id id = new Meter.Id(meterName, Tags.of(labelKey, labelValue), null, null, Meter.Type.GAUGE);

        dynamicGauges.compute(id, (key, current) -> {
            if (current == null) {
                AtomicDouble initialValue = new AtomicDouble(snapshot);
                meterRegistry.gauge(key.getName(), key.getTags(), initialValue);
                return initialValue;
            } else {
                current.set(snapshot);
                return current;
            }
        });
    }

    long getThreadCPUTime(long threadId) {
        long time = threadBean.getThreadCpuTime(threadId);
        /* thread of the specified ID is not alive or does not exist */
        return time == -1 ? 0 : time;
    }

}
