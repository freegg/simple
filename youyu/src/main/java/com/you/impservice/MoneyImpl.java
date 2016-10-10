package com.you.impservice;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.you.bean.Money;
import com.you.dao.MoneyDao;
import com.you.service.MoneyService;
@Service("moneySercive") 
public class MoneyImpl implements MoneyService{
    @Resource  
    private MoneyDao moneyDao;  
	@Override
	public int insertMoney(Money money) {
		return moneyDao.insert(money);
	}

}
