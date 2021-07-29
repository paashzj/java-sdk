package com.github.shoothzj.javatool.module.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirtualIfCfg {

    private String name;

    private String virtualName;

    private String ip;

}
