package com.you.service;

import java.util.List;
import java.util.Map;

import com.you.bean.Money;
import com.you.bean.User;

public interface UserService {
	public User getUserById(int userId);
	public int insert(User user);
	public List selectAll();
	public List selectLimit(Integer offset,Integer limit);
	public int update(int userId);
	public int update2(User user);
	public int delete(User user);
	public int getCount();
	public int somedel(Integer [] arr);
	public List twosel();
	public void traninsert(List<User> users,Money money);
	public User sele(User user);
}
