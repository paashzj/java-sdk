package com.github.shoothzj.javatool.util;

import com.google.common.net.InetAddresses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ipv6Util {

    private static final Logger log = LoggerFactory.getLogger(Ipv6Util.class);

    private static final String[] ZERO_ARRAY = {"", "0", "00", "000", "0000"};

    /**
     * 判断是否是合法的ipv6地址
     *
     * @param ip
     * @return
     */
    public static boolean isValidIp(String ip) {
        return InetAddressUtils.isIPv6Address(ip);
    }

    public static boolean isLinkLocalAddress(String ip) {
        if (!isValidIp(ip)) {
            return false;
        }
        InetAddress inetAddress = InetAddresses.forString(ip);
        return inetAddress.isLinkLocalAddress();
    }

    public static Optional<Integer> getMaskLen(String mask) {
        if (!isValidIp(mask)) {
            return Optional.empty();
        }
        char[] charArray = convertToByteString(mask).toCharArray();
        boolean encounterZero = false;

        int count = 0;

        for (char ch : charArray) {
            if (ch == '0') {
                if (!encounterZero) {
                    encounterZero = true;
                }
            } else if (ch == '1') {
                count++;
                if (encounterZero) {
                    return Optional.empty();
                }
            } else {
                return Optional.empty();
            }
        }

        return Optional.of(count);
    }

    public static Optional<String> convert2Cidr(String networkBit, String mask) {
        if (!isValidIp(networkBit)) {
            return Optional.empty();
        }
        Optional<Integer> integerOp = getMaskLen(mask);
        return integerOp.map(integer -> networkBit + "/" + integer);
    }

    public static boolean isInCidr(String ip, String cidr) {
        IpAddressMatcher addressMatcher = new IpAddressMatcher(cidr);
        return addressMatcher.matches(ip);
    }

    public static String convertToByteString(String ip) {
        char[] charArray = convertToStd(ip).toCharArray();
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            if (c != ':') {
                sb.append(char2Str(c));
            }
        }
        return sb.toString();
    }

    public static String convertToStd(String ip) {
        if (!InetAddressUtils.isIPv6HexCompressedAddress(ip)) {
            return ip;
        }
        String[] split = ip.split("::");
        //这是一个十六进制压缩后的ip
        if (ip.startsWith("::")) {
            //前压缩
            String[] split1 = split[1].split(":");
            return completeFullZero(8 - split1.length) + ":" + completeZero(split1);
        } else if (ip.endsWith("::")) {
            String[] split1 = split[0].split(":");
            return completeZero(split1) + ":" + completeFullZero(8 - split1.length);
        } else {
            String[] split1 = split[0].split(":");
            String[] split2 = split[1].split(":");
            return completeZero(split1) + ":" + completeFullZero(8 - split1.length - split2.length)
                    + ":" + completeZero(split2);
        }
    }

    private static String char2Str(char ch) {
        switch (ch) {
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010";
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'a':
                return "1010";
            case 'A':
                return "1010";
            case 'b':
                return "1011";
            case 'B':
                return "1011";
            case 'c':
                return "1100";
            case 'C':
                return "1100";
            case 'd':
                return "1101";
            case 'D':
                return "1101";
            case 'e':
                return "1110";
            case 'E':
                return "1110";
            case 'f':
                return "1111";
            case 'F':
                return "1111";
            default:
                return "";
        }
    }

    private static String completeZero(String[] array) {
        return Arrays.stream(array).map(s -> ZERO_ARRAY[4 - s.length()] + s).collect(Collectors.joining(":"));
    }

    private static String completeFullZero(int len) {
        String[] array = new String[len];
        for (int i = 0; i < len; i++) {
            array[i] = "";
        }
        return completeZero(array);
    }

}
