package com.data.hope.curd.mapper;

import com.data.hope.curd.entity.BaseEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * mybatis基础接口
 * @author guoqinghua
 * @since 2022-03-01
 */
public interface ReportBaseMapper<T extends BaseEntity> extends BaseMapper<T> {

  /**
   * 如果要自动填充，@{@code Param}(xx) xx参数名必须是 list/collection/array 3个的其中之一
   *
   * @param list
   * @return
   */
  int insertBatch(@Param("list") List<T> list);


  /**
   *  根据ID 更新指定字段
   * @param map 指定字段和值
   * @param id id
   * @return
   */
  int updateFieldsById(@Param("map") Map<String, Object> map, @Param("id") Long id);


  /**
   *  根据ID 更新指定字段
   * @param map 指定字段和值
   * @param ids ids
   * @return
   */
  int updateFieldsBatchById(@Param("map") Map<String, Object> map, @Param("ids") List<Long> ids);

  /**
   * 批量更新
   * @param map 指定字段和值
   * @param list 待更新的实体数据
   * @return
   */
  int updateFieldsBatch(@Param("map") Map<String, Object> map, @Param("list") List<T> list);

}
