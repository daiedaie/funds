package com.mrbt.test.dao;

import com.mrbt.test.model.TestUser;

public interface TestUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(TestUser record);

    int insertSelective(TestUser record);

    TestUser selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(TestUser record);

    int updateByPrimaryKey(TestUser record);
}