package com.sunshine.shopping.common.mapper;

import java.util.List;

public interface BaseMapper<T> {

    /**
     * @Title: query
     * @Description: 查询单个实体对象
     * @author LiMG
     * @date 2017/6/29 10:24
     * @see [类、类#方法、类#成员]
     */
    T query(T entity) throws Exception;

    /**
     * @Title: queryList
     * @Description: 查询列表
     * @author LiMG
     * @date 2017/6/29 10:24
     * @see [类、类#方法、类#成员]
     */
    List<T> queryList(T entity) throws Exception;

    /**
     * @Title: save
     * @Description: 保存实体对象
     * @author LiMG
     * @date 2017/6/29 10:28
     * @see [类、类#方法、类#成员]
     */
    int save(T entity) throws Exception;

    /**
     * @Title: delete
     * @Description: 删除实体对象
     * @author LiMG
     * @date 2017/6/29 10:28
     * @see [类、类#方法、类#成员]
     */
    int delete(T entity) throws Exception;

    /**
     * @Title: update
     * @Description: 更新实体对象
     * @author LiMG
     * @date 2017/6/29 10:29
     * @see [类、类#方法、类#成员]
     */
    int update(T entity) throws Exception;

    /**
     * @Title: batchDelete
     * @Description: 批量删除实体对象
     * @author LiMG
     * @date 2017/6/29 10:34
     * @see [类、类#方法、类#成员]
     */
    int batchDelete(List<Long> list) throws Exception;

    /**
     * @Title: batchUpdate
     * @Description: 批量更新实体对象
     * @author LiMG
     * @date 2017/6/29 10:35
     * @see [类、类#方法、类#成员]
     */
    int batchUpdate(List<T> list) throws Exception;

}
