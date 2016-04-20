package com.grass.common.utils;


import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 配置文件工具类
 * Created by 韩亚辉 on 2016/4/16.
 */
public class ConfigUtils {
    /**
     * 当前对象实例
     */
    private static ConfigUtils global = new ConfigUtils();

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = Maps.newHashMap();

    /**
     * 属性文件加载对象
     */
    private static PropertiesLoader loader = new PropertiesLoader("application.yml");

    /**
     * 获取当前对象实例
     */
    public static ConfigUtils getInstance() {
        return global;
    }

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public static String getConfig(String key) {
        String value = map.get(key);
        if (value == null) {
            value = loader.getProperty(key);
            map.put(key, value != null ? value : StringUtils.EMPTY);
        }
        return value;
    }


    /**
     * 获取上传文件的根目录
     *
     * @return
     */
    public static String getUploadImagePath() {
        String dir = getConfig("userfiles.basedir");
        if (StringUtils.isBlank(dir)) {
            try {
                dir = ConfigUtils.class.getClassLoader().getResource("").getPath();
            } catch (Exception e) {
                return "";
            }
        }
        if (!dir.endsWith("/")) {
            dir += "/";
        }
        return dir;
    }

    /**
     * 获取显示最大的页数
     *
     * @return
     */
    public static Integer getPageSize() {
        return Integer.parseInt(getConfig("page.pageSize"));
    }

    public static String getUrlSuffix() {
        return getConfig("suffix");
    }

    public static String getUpdateSession() {
        return getConfig("updateSession");
    }

    public static String getProjectPath() {
        return getConfig("projectPath");
    }
}