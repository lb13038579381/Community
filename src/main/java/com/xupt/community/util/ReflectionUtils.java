package com.xupt.community.util;


import com.xupt.community.exception.FrontException;

import java.lang.reflect.Method;

public class ReflectionUtils {
    public ReflectionUtils() {
    }

    public static Method getPropertyMethod(Class clz, String propertyName) {
        Method mth = null;
        propertyName = NeteaseEduStringUtils.upperFirstChar(propertyName);

        try {
            mth = clz.getMethod("get" + propertyName);
        } catch (Exception var6) {
            try {
                mth = clz.getMethod("is" + propertyName);
            } catch (Exception var5) {
                throw new FrontException(var5.toString());
            }
        }

        return mth;
    }

    public static Method getSetPropertyMethod(Class clz, String propertyName, Class propertyClz) {
        Method mth = null;
        propertyName = NeteaseEduStringUtils.upperFirstChar(propertyName);

        try {
            mth = clz.getMethod("set" + propertyName, propertyClz);
            return mth;
        } catch (Exception var5) {
            throw new FrontException(var5.toString());
        }
    }

    public static Method getSetPropertyMethod(Class clz, String propertyName) {
        Method mth = null;
        propertyName = NeteaseEduStringUtils.upperFirstChar(propertyName);

        try {
            mth = clz.getMethod("set" + propertyName);
            return mth;
        } catch (Exception var4) {
            throw new FrontException(var4.toString());
        }
    }
}

