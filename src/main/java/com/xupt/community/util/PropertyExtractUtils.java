package com.xupt.community.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.xupt.community.exception.FrontException;
import com.xupt.community.exception.SystemErrorRuntimeException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;

public class PropertyExtractUtils {
    public PropertyExtractUtils() {
    }

    public static <T> void setByPropertyValue(Object object, String propertyName, T value, Class<T> clazz) {
        if (object != null) {
            Method mth = ReflectionUtils.getSetPropertyMethod(object.getClass(), propertyName, clazz);

            try {
                mth.invoke(object, value);
            } catch (Exception var6) {
                throw new FrontException(var6.toString());
            }
        }
    }

    public static <T> T getByPropertyValue(Object object, String propertyName) {
        if (object == null) {
            return null;
        } else {
            Method mth = ReflectionUtils.getPropertyMethod(object.getClass(), propertyName);
            Object value = null;

            try {
                value = mth.invoke(object);
                return value;
            } catch (Exception var5) {
                throw new FrontException(var5.toString());
            }
        }
    }

    public static <T> List<T> getByPropertyValue(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            List<T> retList = new ArrayList(list.size());
            Set<T> set = new HashSet(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var6 = list.iterator();

            while (var6.hasNext()) {
                Object item = var6.next();
                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var10) {
                    throw new FrontException(var10.toString());
                }

                if (value != null) {
                    set.add(value);
                }
            }

            retList.addAll(set);
            return retList;
        }
    }

    public static <T> List<T> getByPropertyValueWithCallBack(List<? extends Object> list, String propertyName, Class<T> clazz, ExtractCallBack callBack) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyList();
        } else {
            List<T> retList = new ArrayList(list.size());
            Set<T> set = new HashSet(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var8 = list.iterator();

            while (var8.hasNext()) {
                Object item = var8.next();
                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var12) {
                    throw new FrontException(var12);
                }

                if (value != null) {
                    T val = clazz.cast(value);
                    set.add(val);
                    if (callBack != null) {
                        callBack.process(val);
                    }
                }
            }

            retList.addAll(set);
            return retList;
        }
    }

    public static <T> List<T> getByPropertyValue(List<? extends Object> list, String propertyName, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            List<T> retList = new ArrayList(list.size());
            Set<T> set = new HashSet(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var7 = list.iterator();

            while (var7.hasNext()) {
                Object item = var7.next();
                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var11) {
                    throw new FrontException(var11);
                }

                if (value != null) {
                    set.add(clazz.cast(value));
                }
            }

            retList.addAll(set);
            return retList;
        }
    }

    public static <T> List<T> safeGetByPropertyValue(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        } else {
            List<T> retList = new ArrayList(list.size());
            Set<T> set = new HashSet(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var6 = list.iterator();

            while (var6.hasNext()) {
                Object item = var6.next();
                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var10) {
                    throw new FrontException(var10);
                }

                if (value != null) {
                    set.add(value);
                }
            }

            retList.addAll(set);
            return retList;
        }
    }

    public static <T> List<T> safeGetByPropertyValue(List<? extends Object> list, String propertyName, Class<T> clazz) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        } else {
            List<T> retList = new ArrayList(list.size());
            Set<T> set = new HashSet(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var7 = list.iterator();

            while (var7.hasNext()) {
                Object item = var7.next();
                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var11) {
                    throw new FrontException(var11);
                }

                if (value != null) {
                    set.add(clazz.cast(value));
                }
            }

            retList.addAll(set);
            return retList;
        }
    }

    public static <T, OBJ> List<T> safeGetByPropertyValue(List<? extends OBJ> list, String propertyName, Class<T> clazz, PropertyExtractUtils.ExtractCondition<OBJ> condition) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        } else {
            List<T> retList = new ArrayList(list.size());
            Set<T> set = new HashSet(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var8 = list.iterator();

            while (true) {
                Object item;
                do {
                    if (!var8.hasNext()) {
                        retList.addAll(set);
                        return retList;
                    }

                    item = var8.next();
                } while (condition != null && !condition.extract(item));

                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var12) {
                    throw new FrontException(var12);
                }

                if (value != null) {
                    set.add(clazz.cast(value));
                }
            }
        }
    }

    public static <K, V> Map<K, V> getMapFromListByProperty(List<V> list, String propertyName, Class<K> propertyClass) {
        Map<K, V> resultMap = new HashMap();
        if (CollectionUtils.isEmpty(list)) {
            return resultMap;
        } else {
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var6 = list.iterator();

            while (var6.hasNext()) {
                Object obj = var6.next();
                Object value = null;

                try {
                    value = mth.invoke(obj);
                } catch (Exception var10) {
                    throw new FrontException(var10);
                }

                if (value != null) {
                    resultMap.put(propertyClass.cast(value), obj);
                }
            }

            return resultMap;
        }
    }

    public static <K, V> Map<K, V> getMapFromListByPropertySafely(List<V> list, String propertyName, Class<K> propertyClass) {
        return (Map) (CollectionUtils.isEmpty(list) ? Maps.newHashMap() : getMapFromListByProperty(list, propertyName, propertyClass));
    }

    public static <V> Map<String, V> getMapFromListByMultiProperty(List<V> list, boolean separator, String[] propertyList) {
        if (CollectionUtils.isEmpty(list)) {
            return Maps.newHashMap();
        } else if (propertyList != null && propertyList.length != 0) {
            Class<?> clz = list.get(0).getClass();
            Map<String, V> resultMap = new HashMap(list.size());
            Method[] mthArray = new Method[propertyList.length];

            for (int i = 0; i < propertyList.length; ++i) {
                mthArray[i] = ReflectionUtils.getPropertyMethod(clz, propertyList[i]);
            }

            StringBuffer key = new StringBuffer();
            Iterator var8 = list.iterator();

            while (var8.hasNext()) {
                Object obj = var8.next();
                key.setLength(0);

                for (int i = 0; i < mthArray.length; ++i) {
                    Object value = null;

                    try {
                        value = mthArray[i].invoke(obj);
                    } catch (Exception var12) {
                        throw new FrontException(var12);
                    }

                    if (value == null) {
                        key.setLength(0);
                        break;
                    }

                    if (i != 0 && separator) {
                        key.append('-');
                    }

                    key.append(value);
                }

                if (key.length() > 0) {
                    resultMap.put(key.toString(), obj);
                }
            }

            return resultMap;
        } else {
            return Maps.newHashMap();
        }
    }

    public static <K, V> Map<K, List<V>> getListMapFromListByProperty(List<V> list, String propertyName, Class<K> propertyClass) {
        if (CollectionUtils.isEmpty(list)) {
            return Collections.emptyMap();
        } else {
            Class<?> clz = list.get(0).getClass();
            Map<K, List<V>> resultMap = new HashMap(list.size());
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var6 = list.iterator();

            while (var6.hasNext()) {
                Object obj = var6.next();
                Object value = null;

                try {
                    value = mth.invoke(obj);
                } catch (Exception var10) {
                    throw new FrontException(var10);
                }

                if (value != null) {
                    List<V> valueList = (List) resultMap.get(propertyClass.cast(value));
                    if (valueList == null) {
                        valueList = new ArrayList();
                    }

                    ((List) valueList).add(obj);
                    resultMap.put(propertyClass.cast(value), valueList);
                }
            }

            return resultMap;
        }
    }

    public static <K, V> Map<K, V> getMapFromListMap(Map<K, List<V>> map) {
        Map<K, V> result = Maps.newHashMap();
        if (MapUtils.isNotEmpty(map)) {
            Iterator var2 = map.keySet().iterator();

            while (var2.hasNext()) {
                K key = var2.next();
                List<V> valueList = (List) map.get(key);
                if (CollectionUtils.isNotEmpty(valueList)) {
                    result.put(key, valueList.get(0));
                }
            }
        }

        return result;
    }

    public static <T> List<T> getDistinctsByPropertyValueWithOrder(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            List<T> retList = new ArrayList(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var5 = list.iterator();

            Object value;
            while (var5.hasNext()) {
                Object item = var5.next();
                value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var9) {
                    throw new FrontException(var9);
                }

                if (value != null) {
                    retList.add(value);
                }
            }

            List<T> distinctList = Lists.newArrayList();
            Iterator var11 = retList.iterator();

            while (var11.hasNext()) {
                value = var11.next();
                if (!distinctList.contains(value)) {
                    distinctList.add(value);
                }
            }

            return distinctList;
        }
    }

    public static <T> List<T> getAllByPropertyValueWithOrder(List<? extends Object> list, String propertyName) {
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            List<T> retList = new ArrayList(list.size());
            Class<?> clz = list.get(0).getClass();
            Method mth = ReflectionUtils.getPropertyMethod(clz, propertyName);
            Iterator var5 = list.iterator();

            while (var5.hasNext()) {
                Object item = var5.next();
                Object value = null;

                try {
                    value = mth.invoke(item);
                } catch (Exception var9) {
                    throw new FrontException(var9);
                }

                if (value != null) {
                    retList.add(value);
                }
            }

            return retList;
        }
    }

    public static <K, V> List<V> sortListByPropertyListSafely(List<V> needSortList, List<K> propertyList, String propertyName, Class<K> propertyClass) {
        if (CollectionUtils.isEmpty(needSortList)) {
            return Collections.EMPTY_LIST;
        } else if (CollectionUtils.isEmpty(propertyList)) {
            return needSortList;
        } else {
            Map<K, V> map = getMapFromListByProperty(needSortList, propertyName, propertyClass);
            List<V> sortedList = new ArrayList(needSortList.size());
            Iterator var6 = propertyList.iterator();

            while (var6.hasNext()) {
                K property = var6.next();
                V item = map.get(property);
                if (item != null) {
                    sortedList.add(item);
                }
            }

            return sortedList;
        }
    }

    public static <K, V> List<V> buildValueListByKeys(List<K> keyList, Map<K, V> map) {
        if (!CollectionUtils.isEmpty(keyList) && map != null) {
            List<V> valueList = Lists.newArrayList();
            Iterator var3 = keyList.iterator();

            while (var3.hasNext()) {
                K key = var3.next();
                V v = map.get(key);
                if (v != null) {
                    valueList.add(v);
                }
            }

            return valueList;
        } else {
            return Lists.newArrayList();
        }
    }

    public interface ExtractCondition<OBJ> {
        boolean extract(OBJ var1);
    }
}
