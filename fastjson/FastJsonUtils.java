package org.example;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

import java.util.*;

/**
 * fastJson工具类
 * @Author: huangzhimao
 * @Date: 2020-04-07
 */
public class FastJsonUtils {
    private static SerializeConfig config;

    static {
        config = new SerializeConfig();
        config.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * 解析json字符串到Map
     *
     * @param json 要解析的json字符串
     * @return 返回Map
     */
    public static Map toMap(String json) {
        Map result = null;
        try {
            result = JSON.parseObject(json, LinkedHashMap.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 解析json字符串到List
     *
     * @param json 要解析的json字符串
     * @return 返回List
     */
    public static List toList(String json) {
        List result = null;
        try {
            result = JSON.parseObject(json, LinkedList.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 按指定的TypeReference解析json字符串到List
     *
     * @param json 要解析的json字符串
     * @return 返回List
     */
    public static <T> List<T> toList(String json, TypeReference<List<T>> typeReference) {
        return JSON.parseObject(json, typeReference.getType());
    }

    /**
     * 解析对象为Json字符串
     *
     * @param object 要转换的对象
     * @return 返回对象的json字符串
     */
    public String toJsonString(Object object) {
        String result = null;
        try {
            result = JSON.toJSONString(object,config,features);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 解析对象为Json字符串
     *
     * @param object            要转换的对象
     * @param dateFormatPattern 日期格式，如"yyyy年MM月dd日 HH时mm分ss秒"
     * @return 返回对象的json字符串
     */
    public static String toJsonWithDateFormat(Object object, String dateFormatPattern) {
        String result = null;
        try {
            result = JSON.toJSONString(object, SerializeConfig.globalInstance, null, dateFormatPattern, JSON.DEFAULT_GENERATE_FEATURE, features);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 解析json字符串到指定类型的对象
     *
     * @param json      要解析的json字符串
     * @param valueType 类对象class
     * @param <T>       泛型参数类型
     * @return 返回解析后的对象
     */
    public static <T> T toPojo(String json, Class<T> valueType) {
        T result = null;
        try {
            result = JSON.parseObject(json, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 转换对象到Map
     *
     * @param fromValue 与Map可兼容的对象
     * @return 返回Map对象
     */
    public static Map convertToMap(Object fromValue) {
        Map result = null;
        try {
            String json = JSON.toJSONString(fromValue,config,features);
            result = JSON.parseObject(json, LinkedHashMap.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * 从Map转换到对象
     *
     * @param fromMap     Map对象
     * @param toValueType 与Map可兼容的对象类型
     * @param <T>         泛型参数类型
     * @return 返回Map转换得到的对象
     */
    public static <T> T convertFromMap(Map fromMap, Class<T> toValueType) {
        T result = null;
        try {
            String json = JSON.toJSONString(fromMap,config,features);
            result = JSON.parseObject(json, toValueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
