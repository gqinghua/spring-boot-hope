package com.data.hope.core.mappers;


import com.data.hope.core.entity.Widget;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface WidgetMapper  {
    @Delete({
        "delete from widget",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into widget (id, dashboard_id, ",
        "config, parent_id, ",
        "create_by, create_time, ",
        "update_by, update_time)",
        "values (#{id,jdbcType=VARCHAR}, #{dashboardId,jdbcType=VARCHAR}, ",
        "#{config,jdbcType=VARCHAR}, #{parentId,jdbcType=VARCHAR}, ",
        "#{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(Widget record);

    @InsertProvider(type=WidgetSqlProvider.class, method="insertSelective")
    int insertSelective(Widget record);

    @Select({
        "select",
        "id, dashboard_id, config, parent_id, create_by, create_time, update_by, update_time",
        "from widget",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="dashboard_id", property="dashboardId", jdbcType=JdbcType.VARCHAR),
        @Result(column="config", property="config", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    Widget selectByPrimaryKey(String id);

    @UpdateProvider(type=WidgetSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Widget record);

    @Update({
        "update widget",
        "set dashboard_id = #{dashboardId,jdbcType=VARCHAR},",
          "config = #{config,jdbcType=VARCHAR},",
          "parent_id = #{parentId,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Widget record);
}