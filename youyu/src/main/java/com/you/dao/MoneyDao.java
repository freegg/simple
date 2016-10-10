package com.you.dao;

import com.you.bean.Money;

public interface MoneyDao {
    int insert(Money record);

    int insertSelective(Money record);
}