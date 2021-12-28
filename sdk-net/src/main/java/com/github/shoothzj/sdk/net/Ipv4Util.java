package com.github.shoothzj.sdk.net;

import com.github.shoothzj.javatool.module.network.IfCfg;
import com.github.shoothzj.javatool.module.network.VirtualIfCfg;
import com.github.shoothzj.javatool.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.util.SubnetUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
public class Ipv4Util {

    /**
     * 获取网卡对应的ip地址,支持虚拟ip
     * ex: Ipv4Util.getIp("eth0");
     * ex: Ipv4Util.getIp("eth0:virtual");
     *
     * @param eth
     * @return
     */
    public static String getIp(String eth) {
        String[] split = eth.split(":");
        if (split.length == 0) {
            throw new IllegalArgumentException("Not correct eth format");
        }
        List<IfCfg> ifCfgs = getIfCfgs();
        Optional<IfCfg> ifCfgOptional = ifCfgs.stream().filter(ifCfg -> ifCfg.getName().equals(split[0])).findAny();
        if (!ifCfgOptional.isPresent()) {
            return null;
        }
        IfCfg ifCfg = ifCfgOptional.get();
        if (split.length == 1) {
            return ifCfg.getIp();
        }
        return ifCfg.getVirtualIfCfgs().stream().filter(virtualIfCfg -> virtualIfCfg.getName().equals(split[1]))
                .findAny().map(VirtualIfCfg::getIp).orElse(null);
    }

    public static List<IfCfg> getIfCfgs() {
        List<IfCfg> ifCfgs = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netInt : Collections.list(nets)) {
                //每一个NetworkInterface构造一个IfCfg对象
                IfCfg ifCfg = new IfCfg();
                List<VirtualIfCfg> virtualIfCfgs = new ArrayList<>();
                Enumeration<NetworkInterface> interfaces = netInt.getSubInterfaces();

                Set<String> virtualIpSet = new HashSet<>();

                for (NetworkInterface aux : Collections.list(interfaces)) {
                    Enumeration<InetAddress> inetAddresses = aux.getInetAddresses();
                    InetAddress inetAddress = inetAddresses.nextElement();
                    String hostAddress = inetAddress.getHostAddress();
                    virtualIpSet.add(hostAddress);
                    virtualIfCfgs.add(new VirtualIfCfg(netInt.getName(), aux.getName().split(":")[1], hostAddress));
                }
                for (InetAddress inetAddress : Collections.list(netInt.getInetAddresses())) {
                    if (!virtualIpSet.contains(inetAddress.getHostAddress())) {
                        ifCfg.setName(netInt.getName());
                        ifCfg.setIp(inetAddress.getHostAddress());
                        break;
                    }
                }
                ifCfg.setVirtualIfCfgs(virtualIfCfgs);
                ifCfgs.add(ifCfg);
            }
        } catch (Exception e) {
            log.error("get IP exception, exception is ", e);
        }
        return ifCfgs;
    }

    public static boolean isInCidr(String ip, String cidr) {
        //sub net utils仅支持ipv4
        SubnetUtils subnetUtils = new SubnetUtils(cidr);
        return subnetUtils.getInfo().isInRange(ip);
    }

    public static String convertIp2Cidr(String ip, String netMask) {
        long num = ip2Num(ip);
        int loop = 32 - getPrefixLen(netMask);

        int help = 1;
        while (loop > 0) {
            num &= ~help;
            help <<= 1;
            loop--;
        }

        return num2Ip(num);
    }

    /**
     * 网段和掩码转化为CIDR
     *
     * @param netSeg
     * @param netMask
     * @return
     */
    public static String convert2Cidr(String netSeg, String netMask) {
        return netSeg + "/" + getPrefixLen(netMask);
    }

    public static int getPrefixLen(String netMask) {
        long num = ip2Num(netMask);
        int result = 0;
        while (num % 2 == 0) {
            num /= 2;
            result++;
        }
        return 32 - result;
    }

    public static boolean isValidIp(String ip) {
        return RegexUtil.IPV4_PATTERN.matcher(ip).matches();
    }

    public static boolean isValidNetMask(String netMask) {
        if (!isValidIp(netMask)) {
            return false;
        }
        long num = ip2Num(netMask);

        return ((num - 1) | num) == 0xffffffffL;
    }

    public static long ip2Num(String ip) {
        long result = 0;
        String[] split = ip.split("\\.");

        for (int i = 3; i >= 0; i--) {
            long ipSeg = Long.parseLong(split[3 - i]);

            //left shifting 24,16,8,0 and bitwise OR

            // 192 << 24;168 << 16;1 << 8;2 << 0
            result |= ipSeg << (i * 8);
        }

        return result;
    }

    public static String num2Ip(long num) {
        return ((num >> 24) & 0xFF) + "." + ((num >> 16) & 0xFF) + "." + ((num >> 8) & 0xFF) + "." + (num & 0xFF);
    }

}
