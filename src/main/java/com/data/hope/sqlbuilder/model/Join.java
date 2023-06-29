package com.data.hope.sqlbuilder.model;

import com.data.hope.sqlbuilder.enums.DatabaseType;
import com.data.hope.sqlbuilder.enums.JoinType;
import com.data.hope.sqlbuilder.enums.ModelType;
import com.data.hope.sqlbuilder.constant.SQLConstant;
import com.data.hope.sqlbuilder.util.AssertUtils;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * join片段.
 *
 * @author yinkaifeng
 * @since 2021-07-14 4:39 下午
 */
@Getter
public final class Join implements Model {
    /**
     * join类型.
     */
    private final JoinType joinType;

    /**
     * 关联表.
     */
    private final Model joinTable;

    /**
     * 关联条件.
     */
    private final Conditions joinConditions;

    private Join(JoinType joinType, Model joinTable, Conditions joinConditions) {
        AssertUtils.notNull(joinType, "关联类型不能为空");
        AssertUtils.notNull(joinTable, "关联表不能为空");
        AssertUtils.notNull(joinConditions, "关联条件不能为空");
        this.joinType = joinType;
        this.joinTable = joinTable;
        this.joinConditions = joinConditions;
    }

    /**
     * 获取join对象.
     *
     * @param joinType       join类型枚举
     * @param joinTable      join表
     * @param joinConditions on条件
     * @return join对象
     */
    public static Join getJoin(JoinType joinType, Model joinTable, Conditions joinConditions) {
        return new Join(joinType, joinTable, joinConditions);
    }

    @Override
    public ModelType getModelType() {
        return ModelType.JOIN;
    }

    @Override
    public String getDatabaseSql(DatabaseType databaseType) {
        StringBuilder sql = new StringBuilder();
        sql.append(getJoinSql(databaseType)).append(StringUtils.SPACE);
        ModelType modelType = joinTable.getModelType();
        switch (modelType) {
            case TABLE:
                sql.append(joinTable.getDatabaseSql(databaseType));
                break;
            case QUERY:
                Query query = (Query) joinTable;
                sql.append(query.getChildQuerySql(databaseType));
                break;
            default:
                throw new RuntimeException("join暂不支持该类型模型");
        }
        sql.append(" ").append(SQLConstant.ON).append(" ");
        sql.append(joinConditions.getDatabaseSql(databaseType));
        return sql.toString();
    }

    /**
     * 根据数据库类型获取join的sql.
     * @param databaseType 数据库类型
     * @return joinSql
     */
    public String getJoinSql(DatabaseType databaseType) {
        switch (joinType) {
            case LEFT:
            case INNER:
            case RIGHT:
                return joinType.getCode();
            case FULL:
                switch (databaseType) {
                    case MSSQL:
                    case ORACLE:
                        return "FULL JOIN";
                    case HIVE:
                    case SPARK:
                    case POSTGRESQL:
                    case KDW:
                        return "FULL OUTER JOIN";
                    case CLICKHOUSE:
                        return "ALL FULL JOIN";
                    default:
                        throw new RuntimeException("MYSQL 暂不支持FULL JOIN!!!");
                }
            default:
                throw new RuntimeException("暂不支持" + joinType.getCode());
        }
    }
}
