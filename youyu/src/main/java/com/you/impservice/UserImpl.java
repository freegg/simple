package com.you.impservice;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.you.bean.Money;
import com.you.bean.User;
import com.you.dao.MoneyDao;
import com.you.dao.UserDao;
import com.you.service.UserService;
@Service("userService") 
public class UserImpl implements UserService{

	    @Resource  
	    private UserDao userDao;  
	    @Resource  
	    private MoneyDao moneyDao;  
	    @Override  
	    public User getUserById(int userId) {  
	        return this.userDao.selectByPrimaryKey(userId);  
	    }
		@Override
		public int insert(User user) {
			return this.userDao.insert(user);
		}
		@Override
		public List selectAll() {
			return userDao.selectAll();
		}
		@Override
		public int update(int userId) {
			return userDao.update(userId);
		}
		@Override
		public int update2(User user) {
			return userDao.update2(user);
		}
		@Override
		public int delete(User user) {
			return userDao.delete(user);
		}
		@Override
		public int getCount() {
			return userDao.getCount();
		}
		@Override
		public List selectLimit(Integer offset, Integer limit) {
			return userDao.selectLimit(offset,limit);
		}
		@Override
		public int somedel(Integer[] arr) {
			return userDao.somedel(arr);
		}
		@Override
		public List twosel() {
			return userDao.twosel();
		}
		@Transactional
		@Override
		public void traninsert(List<User> users,Money money) {
			for(int i=0;i<users.size();i++){
				if(i>3){
					throw new RuntimeException();
				}else{
					userDao.traninsert(users.get(i));
					moneyDao.insert(money);
				}
			}
		}
		@Override
		public User sele(User user) {
			return userDao.sele(user);
		}
		

}
