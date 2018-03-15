package com.mmall.dao;

import com.mmall.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;

/**
 * 数据库操作接口类
 */
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUserName(String username);

    int checkEmail(String email);

    User selectLogin(@Param("username") String username, @Param("password") String password);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("passwordNew") String passwordNew);

    int checkPassword(@Param("password") String password, @Param("id") Integer userId);

    int checkEmailByUserId(@Param("emial")String emial,@Param("userId")Integer userId);
}