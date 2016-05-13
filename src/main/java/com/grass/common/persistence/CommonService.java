package com.grass.common.persistence;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 韩亚辉 on 2016/4/19.
 */
@Transactional(readOnly = true)
public abstract class CommonService<T extends CommonEntiry, D extends CommonMapper<T>> {
    protected Logger logger = LoggerFactory.getLogger(CommonService.class);
    /**
     * 持久层对象
     */
    @Autowired
    protected D dao;

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id) {
        return dao.get(id);
    }

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity) {
        return dao.get(entity);
    }

    /**
     * 查询列表数据
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity) {
        return dao.findList(entity);
    }

    /**
     * 查询所有的数据
     *
     * @return
     */
    public List<T> findAll() {
        return dao.findAll();
    }

    /**
     * 查询分页数据
     *
     * @param pageNum  页码
     * @param pageSize 页数
     * @param entity
     * @return
     */
    public PageInfo<T> findPage(int pageNum, int pageSize, T entity) {
        PageHelper.startPage(pageNum, pageSize, true);
        PageInfo<T> page = new PageInfo<>(dao.findList(entity));
        return page;
    }

    /**
     * 保存数据（插入或更新）
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void save(T entity) {
        if (StringUtils.isNoneBlank(entity.getId())) {
            entity.preInsert();
            dao.insert(entity);
        } else {
            entity.preUpdate();
            dao.update(entity);
        }
    }

    /**
     * 删除数据
     *
     * @param entity
     */
    @Transactional(readOnly = false)
    public void delete(T entity) {
        dao.delete(entity);
    }


}
