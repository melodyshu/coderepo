package org.example;

import java.util.*;

/**
 * @Author: huangzhimao
 * @Date: 2020-04-11
 */
public class ConvertUtils {
    /**
     * Don't let anyone instantiate this class.
     */
    private ConvertUtils() {
        //AssertionError不是必须的. 但它可以避免不小心在类的内部调用构造器. 保证该类在任何情况下都不会被实例化.
        //see 《Effective Java》 2nd
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }

    public static <T> List<T> toList(T... arrays) {
        return new ArrayList<>(Arrays.asList(arrays));
    }

    public static <T> List<T> toList(final Enumeration<T> enumeration) {
        return null == enumeration ? Collections.<T>emptyList() : Collections.list(enumeration);
    }

    public static <T> List<T> toList(final Collection<T> collection) {
        if (null == collection) {
            return Collections.<T>emptyList();
        }
        return collection instanceof List ? (List<T>) collection : new ArrayList<T>(collection);
    }
}
