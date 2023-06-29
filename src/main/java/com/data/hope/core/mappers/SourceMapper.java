package com.data.hope.core.mappers;


import com.data.hope.core.entity.Source;
import com.data.hope.core.mappers.ext.CRUDMapper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface SourceMapper extends CRUDMapper {
    @Delete({
        "delete from source",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String id);

    @Insert({
        "insert into source (id, `name`, ",
        "config, `type`, org_id, ",
        "create_by, create_time, ",
        "update_by, update_time, ",
        "`status`)",
        "values (#{id,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{config,jdbcType=VARCHAR}, #{type,jdbcType=VARCHAR}, #{orgId,jdbcType=VARCHAR}, ",
        "#{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=TINYINT})"
    })
    int insert(Source record);

    @InsertProvider(type=SourceSqlProvider.class, method="insertSelective")
    int insertSelective(Source record);

    @Select({
        "select",
        "id, `name`, config, `type`, org_id, create_by, create_time, update_by, update_time, ",
        "`status`",
        "from source",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="config", property="config", jdbcType=JdbcType.VARCHAR),
        @Result(column="type", property="type", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_by", property="createBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_by", property="updateBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="status", property="status", jdbcType=JdbcType.TINYINT)
    })
    Source selectByPrimaryKey(String id);

    @UpdateProvider(type=SourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Source record);

    @Update({
        "update source",
        "set `name` = #{name,jdbcType=VARCHAR},",
          "config = #{config,jdbcType=VARCHAR},",
          "`type` = #{type,jdbcType=VARCHAR},",
          "org_id = #{orgId,jdbcType=VARCHAR},",
          "create_by = #{createBy,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_by = #{updateBy,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP},",
          "`status` = #{status,jdbcType=TINYINT}",
        "where id = #{id,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(Source record);
}