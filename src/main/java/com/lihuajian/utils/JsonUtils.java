package com.lihuajian.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON工具类
 * 
 */

public class JsonUtils {

    /*使用GsonBuilder创建Gson实例的步骤：
    首先创建GsonBuilder,然后调用GsonBuilder提供的各种配置方法进行配置，
    最后调用GsonBuilder的create方法，将基于当前的配置创建一个Gson实例。*/

    //setDateFormat("yyyy-MM-dd HH:mm:ss") 设置时间输出格式，disableHtmlEscaping() 禁用html转义，serializeNulls()，支持null输出
    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss") //时间转化为特定格式
            .disableHtmlEscaping() //禁用html转义，防止特殊字符出现乱码
            .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
            .create(); //创建json实例

    /**
     * 将对象转为json字符串
     * @param obj
     * @return
     * @throws
     * //此方法将指定的对象序列化为其等效的Json表示形式。
     */
    public static String toJson(Object obj) {
    	if(obj != null) {
    		return gson.toJson(obj);
    	}
    	return "{}";
    }
    public static String toJsonNull(Object obj) {
    	Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();
    	return gson.toJson(obj);
    }

    /**
     * 将对象转换成JSON字符串 JSON格式:{seccess:true,data:'信息'}
     * @param seccess
     * @param obj
     * @return
     */
    public static String objToJson(boolean seccess, Object... obj) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("success", seccess);
        if (null != obj && obj.length > 0) {
            if (obj.length > 1) {
                m.put("data", obj);
            } else {
                m.put("data", obj[0]);
            }
        }
        return gson.toJson(m);
    }

    /**
     * 根据json字符串，得到某个特定类型的对象（当此特定类型不是泛型时，可以使用本方法）
     * @param json
     * @param clazz
     * @return
     * @throws
     * //此方法将指定的Json反序列化为指定类的对象。
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        return (T)gson.fromJson(json, clazz);
    }


    /**
     * 从Json格式字符串，得到对象
     * @param json
     * @param t
     * @return
     * // 此方法将指定的Json反序列化为指定类型的对象。
     */
    @SuppressWarnings("unchecked")
	public static <T> T fromJson(String json, Type t) {
        return (T)gson.fromJson(json, t);
    }

    /**
     * 根据json字符串，得到一个JsonElement对象  （JsonElement对象 即可为 一个对象，也可以是数组或集合）
     * @param json
     * @return
     */
    public static JsonElement fromJsonAsJsonElement(String json) {
    	return gson.fromJson(json, JsonElement.class);
    }
}
