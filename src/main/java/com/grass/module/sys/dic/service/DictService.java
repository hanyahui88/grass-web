/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.sys.dic.service;

import com.grass.common.persistence.CommonService;
import com.grass.common.utils.CacheUtils;
import com.grass.module.sys.dic.entity.DictEntity;
import com.grass.module.sys.dic.mapper.DictMapper;
import com.grass.module.sys.dic.utils.DictUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典Service
 *
 * @author hanyahui
 * @version 2014-05-16
 */
@Service
@Transactional(readOnly = true)
public class DictService extends CommonService<DictEntity, DictMapper> {

    /**
     * 查询字段类型列表
     *
     * @return
     */
    public List<String> findTypeList() {
        return dao.findTypeList(new DictEntity());
    }

    @Transactional(readOnly = false)
    public void save(DictEntity dict) {
        super.save(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

    @Transactional(readOnly = false)
    public void delete(DictEntity dict) {
        super.delete(dict);
        CacheUtils.remove(DictUtils.CACHE_DICT_MAP);
    }

}
