package com.xupt.community.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

public class NeteaseEduStringUtils {
    public NeteaseEduStringUtils() {
    }

    public static String[] parseStandardStringArrayFromString(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        } else {
            int left = text.indexOf("[");
            int right = text.lastIndexOf("]");
            if (left >= 0 && right > left) {
                String strNoThresh = text.substring(left + 1, right);
                return StringUtils.split(strNoThresh, ",");
            } else {
                return null;
            }
        }
    }

    public static int length(String s) {
        if (s == null) {
            return 0;
        } else {
            char[] c = s.toCharArray();
            int len = 0;

            for(int i = 0; i < c.length; ++i) {
                ++len;
                if (!isLetter(c[i])) {
                    ++len;
                }
            }

            return len;
        }
    }

    public static boolean isLetter(char c) {
        int k = 128;
        return c / k == 0;
    }

    public static String parseColumnNameToPropertyString(String text) {
        if (text != null && text.length() != 0) {
            String[] subStrings = text.split("_");
            StringBuilder sb = new StringBuilder();
            String[] var3 = subStrings;
            int var4 = subStrings.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String subString = var3[var5];
                if (Character.isLowerCase(subString.charAt(0)) && subString.length() > 0) {
                    subString = Character.toUpperCase(subString.charAt(0)) + subString.substring(1);
                }

                sb.append(subString);
            }

            return sb.toString();
        } else {
            return text;
        }
    }

    public static Long[] parseStandardLongArrayFromString(String text) {
        String[] strAry = parseStandardStringArrayFromString(text);
        if (strAry == null) {
            return null;
        } else {
            Long[] longAry = new Long[strAry.length];

            for(int i = 0; i < strAry.length; ++i) {
                longAry[i] = Long.parseLong(strAry[i]);
            }

            return longAry;
        }
    }

    public static String substring(String s, int maxLength) {
        if (!StringUtils.isEmpty(s) && maxLength > 0) {
            maxLength = maxLength > s.length() ? s.length() : maxLength;
            return s.substring(0, maxLength);
        } else {
            return null;
        }
    }

    public static String upperFirstChar(String source) {
        if (StringUtils.isBlank(source)) {
            return "";
        } else {
            Character firstChar = source.charAt(0);
            return Character.isLowerCase(firstChar) ? Character.toUpperCase(firstChar) + source.substring(1) : source;
        }
    }

    public static String convertCamel2UnderlineStyle(String str) {
        if (StringUtils.isBlank(str)) {
            return str;
        } else {
            StringBuilder sb = new StringBuilder(str.length() + 10);

            for(int i = 0; i < str.length(); ++i) {
                char array_element = str.charAt(i);
                if (Character.isUpperCase(array_element)) {
                    if (i != 0) {
                        sb.append("_");
                    }

                    sb.append(Character.toLowerCase(array_element));
                } else {
                    sb.append(array_element);
                }
            }

            return sb.toString();
        }
    }

    public static String replaceAllRegular(String content, String regular, String replace) {
        String result = "";
        if (content != null) {
            Pattern p = Pattern.compile(regular);
            Matcher m = p.matcher(content);
            result = m.replaceAll(replace);
        }

        return result;
    }

    public static String removeMutiCRLF(String content) {
        String onlyLF = replaceAllRegular(content, "\r|\n", "\n");
        return replaceAllRegular(onlyLF, "\n+", "\n");
    }
}

