package com.github.shoothzj.javatool.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;

/**
 * @author hezhangjian
 */
@Slf4j
public class DateUtil extends DateUtils {

    public static final String TIME_ZONE_ID = Calendar.getInstance().getTimeZone().getID();

}
