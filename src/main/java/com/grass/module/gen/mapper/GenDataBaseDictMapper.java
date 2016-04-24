/**
 * Copyright &copy; 2015-2016 <a href="https://github.com/hanyahui88/swifts">swifts</a> All rights reserved.
 */
package com.grass.module.gen.mapper;


import com.grass.common.persistence.BaseDao;
import com.grass.module.gen.entity.GenTable;
import com.grass.module.gen.entity.GenTableColumn;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 业务表字段DAO接口
 *
 * @author hanyahui
 * @version 2013-10-15
 */
@Repository
public interface GenDataBaseDictMapper extends BaseDao<GenTableColumn> {

    /**
     * 查询表列表
     *
     * @param genTable
     * @return
     */
    List<GenTable> findTableList(GenTable genTable);

    /**
     * 获取数据表字段
     *
     * @param genTable
     * @return
     */
    List<GenTableColumn> findTableColumnList(GenTable genTable);

    /**
     * 获取数据表主键
     *
     * @param genTable
     * @return
     */
    List<String> findTablePK(GenTable genTable);

}
