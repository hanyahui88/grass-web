/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.sys.dic.service;

import com.grass.common.persistence.BaseService;
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
public class DictService extends BaseService<DictEntity, DictMapper> {



}
