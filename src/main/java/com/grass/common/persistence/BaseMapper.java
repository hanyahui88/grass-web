package com.grass.common.persistence;

import com.grass.common.params.SqlParams;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 韩亚辉 on 2016/4/19.
 */
public interface BaseMapper<T extends BaseEntiry> {
    int save(T t);

    int batchSave(@Param("entity") List<T> entity);

    int delete(int id);

    int batchDelete(@Param("ids") List<Integer> ids);

    int softDelete(int id);

    int batchSoftDelete(@Param("ids") List<Integer> ids);

    int update(T t);

    int batchUpdate(@Param("entity") List<T> entity);

    int conditionUpdate(@Param("map") Map<String, Object> map, @Param("params") List<SqlParams> params);

    T get(int id);

    List<T> findAll();

    List<T> findList(@Param("params") List<SqlParams> params);
}
