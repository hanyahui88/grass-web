/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.dao;

import com.grass.common.persistence.BaseDao;
import com.grass.module.gen.entity.GenTableColumn;
import org.springframework.stereotype.Repository;

/**
 * 业务表字段DAO接口
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@Repository
public interface GenTableColumnDao extends BaseDao<GenTableColumn> {

    public void deleteByGenTableId(GenTableColumn genTableColumn);
}
