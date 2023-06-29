

package com.data.hope.core.mappers.ext;


import com.data.hope.core.entity.User;
import com.data.hope.core.mappers.UserMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserMapperExt extends UserMapper {

    @Select({
            "SELECT ",
            "	u.*  ",
            "FROM ",
            "	`user` u  ",
            "WHERE ",
            "	u.username = #{username} or ",
            "	u.email = #{username}"
    })
    User selectByNameOrEmail(@Param("username") String username);

    @Select({
            "SELECT " +
                    "	u.*  " +
                    "FROM " +
                    "	`user` u  " +
                    "WHERE " +
                    "	u.email = #{email}"
    })
    User selectByEmail(@Param("email") String username);

    @Select({"SELECT " +
            "	count( DISTINCT u.email )  " +
            "FROM " +
            "	`user` u  " +
            "WHERE " +
            "	u.email = #{email}"})
    long countEmail(@Param("email") String email);

    @Select({
            " select * ",
            " from `user` u",
            " where ",
            " lower(`username`) like #{keyword}",
            " or lower(`name`) like #{keyword}",
            " or lower(`email`) like #{keyword}",
    })
    List<User> searchUsers(@Param("keyword") String keyword);

    @Select({
            "SELECT " +
                    "	u.*  " +
                    "FROM " +
                    "	`user` u  " +
                    "WHERE " +
                    "	u.`username` = #{username}"
    })
    User selectByUsername(@Param("username") String username);

    @Update({
            "UPDATE `user` u " +
                    "SET u.active = 1  " +
                    "WHERE " +
                    "	u.active = 0  " +
                    "	AND u.id = #{userId}"
    })
    int updateToActiveById(String id);

}