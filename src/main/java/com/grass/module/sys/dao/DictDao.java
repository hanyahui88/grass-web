/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.sys.dao;

import com.grass.common.persistence.BaseDao;
import com.grass.module.sys.entity.Dict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典DAO接口
 * @author hanyahui
 * @version 2014-05-16
 */
@Repository
public interface DictDao extends BaseDao<Dict> {

	public List<String> findTypeList(Dict dict);

}
