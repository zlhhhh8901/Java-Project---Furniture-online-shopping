package com.hspedu.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

public class DataUtils {
    //自动封装JavaBean
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean, value);
        }catch (Exception e){
            e.printStackTrace();
        }
        return bean;
    }

    //将字符串转成整数，否则返回默认值
    public static int parseInt(String val, int defaultVal){
        try {
            return Integer.parseInt(val);
        } catch (NumberFormatException e) {
            System.out.println(val + "格式不对");
        }
        return defaultVal;
    }
}
