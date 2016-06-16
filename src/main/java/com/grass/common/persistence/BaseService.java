package com.grass.common.persistence;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grass.common.params.RequestParams;
import com.grass.common.params.SqlParams;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by 韩亚辉 on 2016/4/19.
 */
@Transactional(readOnly = true)
public abstract class BaseService<T extends BaseEntiry, D extends BaseMapper<T>> {
    protected Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private D baseMapper;

    /**
     * 保存一个对象
     *
     * @param t
     * @return
     */
    @Transactional
    public int save(T t) {
        t.preInsert();
        return baseMapper.save(t);
    }

    /**
     * 保存一组对象
     *
     * @param entity
     * @return
     */
    @Transactional
    public int save(List<T> entity) {
        entity.forEach(item -> item.preInsert());
        return baseMapper.batchSave(entity);
    }

    /**
     * 根据一个id 删除
     *
     * @param id
     * @return
     */
    @Transactional
    public int delete(int id) {
        return baseMapper.delete(id);
    }

    /**
     * 根据一组id 删除
     *
     * @param ids
     * @return
     */
    @Transactional
    public int delete(List<Integer> ids) {
        return baseMapper.batchDelete(ids);
    }

    /**
     * 根据一个id 软删除
     *
     * @param id
     * @return
     */
    @Transactional
    public int softDelete(int id) {
        return baseMapper.softDelete(id);
    }

    /**
     * 根据一组id 软删除
     *
     * @param ids
     * @return
     */
    @Transactional
    public int softDelete(List<Integer> ids) {
        return baseMapper.batchSoftDelete(ids);
    }

    /**
     * 根据id 更新一个对象
     *
     * @param t
     * @return
     */
    @Transactional
    public int update(T t) {
        t.preUpdate();
        return baseMapper.update(t);
    }

    /**
     * 根据id 更新一组对象
     *
     * @param entity
     * @return
     */
    @Transactional
    public int update(List<T> entity) {
        entity.forEach(item -> item.preUpdate());
        return baseMapper.batchUpdate(entity);
    }

    /**
     * 根据条件更新对象
     *
     * @param map    更新的键值对
     * @param params 条件
     * @return
     */
    @Transactional
    public int update(Map<String, Object> map, List<SqlParams> params) {
        return baseMapper.conditionUpdate(map, params);
    }

    /**
     * 根据id查找一个
     *
     * @param id
     * @return
     */
    public T get(int id) {
        return baseMapper.get(id);
    }

    /**
     * 查询所有
     *
     * @return
     */
    public List<T> findAll() {
        return baseMapper.findAll();
    }

    /**
     * 分页 查询所有
     *
     * @param requestParams
     * @return
     */
    public PageInfo<T> findAll(RequestParams requestParams) {
        PageHelper.startPage(requestParams.getPageNum(), requestParams.getPageSize());
        PageInfo<T> pageInfo = new PageInfo(baseMapper.findAll());
        return pageInfo;
    }

    /**
     * 根据条件查询
     *
     * @param params
     * @return
     */
    public List<T> findList(List<SqlParams> params) {
        return baseMapper.findList(params);
    }

    /**
     * 分页根据条件查询
     *
     * @param requestParams
     * @param params
     * @return
     */
    public PageInfo<T> findList(RequestParams requestParams, List<SqlParams> params) {
        PageHelper.startPage(requestParams.getPageNum(), requestParams.getPageSize());
        PageInfo<T> pageInfo = new PageInfo(baseMapper.findList(params));
        return pageInfo;
    }

}
