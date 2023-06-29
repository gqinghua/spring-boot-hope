package com.data.hope.core.mappers.ext;


import com.data.hope.core.entity.Variable;
import com.data.hope.core.mappers.VariableMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Mapper
@Repository
public interface VariableMapperExt extends VariableMapper {

    @Select({
            "<script>",
            "SELECT * FROM variable WHERE id IN ",
            "<foreach collection='ids' item='item' index='index' open='(' close=')' separator=','>  #{item} </foreach> ;",
            "</script>",
    })
    List<Variable> selectByIds(Collection<String> ids);

    @Select({
            "SELECT * FROM variable WHERE view_id = #{viewId}"
    })
    List<Variable> selectViewVariables(String viewId);

    @Select({
            "SELECT * FROM report_variable WHERE view_id = #{viewId} AND `type`='QUERY'"
    })
    List<Variable> selectViewQueryVariables(String viewId);

    @Select({
            "SELECT * FROM report_variable WHERE org_id = #{orgId} AND view_id is NULL"
    })
    List<Variable> selectOrgVariables(String orgId);

    @Select({
            "SELECT * FROM report_variable WHERE view_id is NULL AND `type`='QUERY' "
    })
    List<Variable> selectOrgQueryVariables(String orgId);


    @Update({
            "<script>",
            "<foreach collection='elements' item='record' index='index' separator=';'>",
            "update variable",
            "set org_id = #{orgId,jdbcType=VARCHAR},",
            "view_id = #{viewId,jdbcType=VARCHAR},",
            "`name` = #{name,jdbcType=VARCHAR},",
            "`type` = #{type,jdbcType=VARCHAR},",
            "value_type = #{valueType,jdbcType=VARCHAR},",
            "permission = #{permission,jdbcType=INTEGER},",
            "encrypt = #{encrypt,jdbcType=TINYINT},",
            "`label` = #{label,jdbcType=VARCHAR},",
            "default_value = #{defaultValue,jdbcType=VARCHAR},",
            "expression = #{expression,jdbcType=TINYINT},",
            "create_time = #{createTime,jdbcType=TIMESTAMP},",
            "create_by = #{createBy,jdbcType=VARCHAR},",
            "update_time = #{updateTime,jdbcType=TIMESTAMP},",
            "update_by = #{updateBy,jdbcType=VARCHAR}",
            "where id = #{id,jdbcType=VARCHAR}",
            "</foreach>",
            "</script>",
    })
    int batchUpdate(List<Variable> elements);

    @Insert({
            "<script>",
            "insert into variable (id, org_id, ",
            "view_id, `name`, `type`, ",
            "value_type, permission, ",
            "encrypt, `label`, ",
            "default_value, expression, ",
            "create_time, create_by, ",
            "update_time, update_by) values ",
            "<foreach collection='elements' item='record' index='index' separator=','>",
            "<trim prefix='(' suffix=')' suffixOverrides=','>",
            " #{record.id,jdbcType=VARCHAR}, #{record.orgId,jdbcType=VARCHAR}, ",
            "#{record.viewId,jdbcType=VARCHAR}, #{record.name,jdbcType=VARCHAR}, #{record.type,jdbcType=VARCHAR}, ",
            "#{record.valueType,jdbcType=VARCHAR}, #{record.permission,jdbcType=INTEGER}, ",
            "#{record.encrypt,jdbcType=TINYINT}, #{record.label,jdbcType=VARCHAR}, ",
            "#{record.defaultValue,jdbcType=VARCHAR}, #{record.expression,jdbcType=TINYINT}, ",
            "#{record.createTime,jdbcType=TIMESTAMP}, #{record.createBy,jdbcType=VARCHAR}, ",
            "#{record.updateTime,jdbcType=TIMESTAMP}, #{record.updateBy,jdbcType=VARCHAR}",
            "</trim>",
            "</foreach>",
            "</script>",
    })
    int batchInsert(List<Variable> elements);

    @Delete({
            "DELETE FROM variable where view_id=#{viewId}"
    })
    int deleteByView(String viewId);
}
