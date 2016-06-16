/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.sys.dic.mapper;

import com.grass.common.persistence.BaseMapper;
import com.grass.module.sys.dic.entity.DictEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典DAO接口
 *
 * @author hanyahui
 * @version 2014-05-16
 */
@Repository
public interface DictMapper extends BaseMapper<DictEntity> {

    List<String> findTypeList(DictEntity dict);

}
