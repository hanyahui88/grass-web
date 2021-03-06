/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.sys.dic.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.grass.common.mapper.JsonMapper;
import com.grass.common.utils.CacheUtils;
import com.grass.common.utils.SpringContextHolder;
import com.grass.module.sys.dic.entity.DictEntity;
import com.grass.module.sys.dic.mapper.DictMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 字典工具类
 *
 * @author hanyahui
 * @version 2013-5-29
 */
public class DictUtils {

    private static DictMapper dictDao = SpringContextHolder.getBean(DictMapper.class);

    public static final String CACHE_DICT_MAP = "dictMap";

    public static String getDictLabel(String value, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (DictEntity dict : getDictList(type)) {
                if (type.equals(dict.getType()) && value.equals(dict.getValue())) {
                    return dict.getLabel();
                }
            }
        }
        return defaultValue;
    }

    public static String getDictLabels(String values, String type, String defaultValue) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)) {
            List<String> valueList = Lists.newArrayList();
            for (String value : StringUtils.split(values, ",")) {
                valueList.add(getDictLabel(value, type, defaultValue));
            }
            return StringUtils.join(valueList, ",");
        }
        return defaultValue;
    }

    public static String getDictValue(String label, String type, String defaultLabel) {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)) {
            for (DictEntity dict : getDictList(type)) {
                if (type.equals(dict.getType()) && label.equals(dict.getLabel())) {
                    return dict.getValue();
                }
            }
        }
        return defaultLabel;
    }

    public static List<DictEntity> getDictList(String type) {
        @SuppressWarnings("unchecked")
        Map<String, List<DictEntity>> dictMap = (Map<String, List<DictEntity>>) CacheUtils.get(CACHE_DICT_MAP);
        if (dictMap == null) {
            dictMap = Maps.newHashMap();
            for (DictEntity dict : dictDao.findAll()) {
                List<DictEntity> dictList = dictMap.get(dict.getType());
                if (dictList != null) {
                    dictList.add(dict);
                } else {
                    dictMap.put(dict.getType(), Lists.newArrayList(dict));
                }
            }
            CacheUtils.put(CACHE_DICT_MAP, dictMap);
        }
        List<DictEntity> dictList = dictMap.get(type);
        if (dictList == null) {
            dictList = Lists.newArrayList();
        }
        return dictList;
    }

    /**
     * 返回字典列表（JSON）
     *
     * @param type
     * @return
     */
    public static String getDictListJson(String type) {
        return JsonMapper.toJsonString(getDictList(type));
    }

}
