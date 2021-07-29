package com.github.shoothzj.javatool.util;

import java.security.SecureRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author shoothzj
 */
public class RandomUtil {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static boolean randomBoolean() {
        return ThreadLocalRandom.current().nextInt(2) == 0;
    }

    public static int nextInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int nextInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    public static int randomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(max - min) + min;
    }

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = ThreadLocalRandom.current().nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public static<E> E chooseOne(List<? extends E> list) {
        return list.get(nextInt(list.size()));
    }

    public byte[] getSecRanByte(int len) {
        byte[] bytes = new byte[len];
        secureRandom.nextBytes(bytes);
        return bytes;
    }

}
