package cn.itcast.store.service.serviceImp;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImp.UserDaoImp;
import cn.itcast.store.domian.User;
import cn.itcast.store.service.UserService;

public class UserServiceImp implements UserService {

	public boolean userRegist(User user) {
		UserDao userDao=new UserDaoImp();
		return userDao.userRegist(user);
	}
	
}
