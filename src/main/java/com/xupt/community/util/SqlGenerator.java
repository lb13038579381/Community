package com.xupt.community.util;

import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * sql自动生成
 *
 * @author
 */
public class SqlGenerator {

    public static void main(String[] args) {

        String className = "com.xupt.community.domain.Member";
        List<String> allClasses = getAllClasses(
                "E:\\ss");
        // 获取包下的所有类名称
        String prefix = "cn.xxx.";
        String sql = generateSql(className);
        System.out.println(sql);
//		for (String str : allClasses) {
//			className = prefix + str.substring(0, str.lastIndexOf("."));
//			String sql = generateSql(className);
//			System.out.println(sql.toString());
//		}

    }

    /**
     * 根据实体类生成建表语句
     *
     * @param className 全类名
     * @param filePath  磁盘路径 如 : d:/workspace/
     * @author
     */
    public static String generateSql(String className) {
        try {
            Class<?> clz = Class.forName(className);
            className = clz.getSimpleName();
            Field[] fields = clz.getDeclaredFields();
            StringBuffer column = new StringBuffer();
//			String varchar = " varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,";
            String varchar = " int(4) NULL ,";

            for (Field f : fields) {

                column.append(" \n `" + camelToUnderline(f.getName()) + "`").append(varchar);
            }
            StringBuffer sql = new StringBuffer();
            String tableName = camelToUnderline(className);
            sql.append("\n DROP TABLE IF EXISTS `" + tableName + "`; ")
                    .append(" \n CREATE TABLE `" + tableName + "`  (")
                    .append(" \n `id` int(11) NOT NULL AUTO_INCREMENT,").append(" \n " + column)
                    .append(" \n PRIMARY KEY (`id`) USING BTREE,").append("\n INDEX `id`(`id`) USING BTREE")
                    .append(" \n ) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci;");
            return sql.toString();
        } catch (ClassNotFoundException e) {
            return null;
        }

    }

    public static final char UNDERLINE = '_';

    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append(UNDERLINE);
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取包下的所有类名称,获取的结果类似于 XXX.java
     *
     * @param packageName
     * @return
     * @author
     */
    public static List<String> getAllClasses(String packageName) {
        List<String> classList = new ArrayList<String>();
        String className = "";
        File f = new File(packageName);
        if (f.exists() && f.isDirectory()) {
            File[] files = f.listFiles();
            for (File file : files) {
                className = file.getName();
                classList.add(className);
            }
            return classList;
        } else {
            return null;
        }
    }

    /**
     * 将string 写入sql文件
     *
     * @param str
     * @param path
     * @author
     */
    public static void StringToSql(String str, String path) {
        byte[] sourceByte = str.getBytes();
        if (null != sourceByte) {
            try {
                File file = new File(path); // 文件路径（路径+文件名）
                if (!file.exists()) { // 文件不存在则创建文件，先创建目录
                    File dir = new File(file.getParent());
                    dir.mkdirs();
                    file.createNewFile();
                }
                FileOutputStream outStream = new FileOutputStream(file); // 文件输出流用于将数据写入文件
                outStream.write(sourceByte);
                outStream.flush();
                outStream.close(); // 关闭文件输出流
                System.out.println("生成成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
