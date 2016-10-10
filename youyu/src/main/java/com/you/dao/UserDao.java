package com.you.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.you.bean.User;

public interface UserDao {
    int insert(User record);
    int insertSelective(User record);
    User selectByPrimaryKey(int id);
    List selectAll();
    List twosel();
    List selectLimit(@Param("offset") Integer offset,@Param("limit") Integer limit);
    int update(int userId);
    int update2(User user);
    int delete(User user);
    int getCount();
    int somedel(Integer [] arr);
    void traninsert(User users);
    User sele(User user);
    
}