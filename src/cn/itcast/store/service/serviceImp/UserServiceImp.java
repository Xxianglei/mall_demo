package cn.itcast.store.service.serviceImp;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.daoImp.UserDaoImp;
import cn.itcast.store.domian.User;

import cn.itcast.store.service.UserService;

public class UserServiceImp implements UserService {

	public boolean userRegist(User user) {
		UserDao userDao=new UserDaoImp();
		return userDao.userRegist(user);
	}

	public boolean active(String code) {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImp();
		User user=userDao.active(code);
		if (null!=user) {
			// 修改状态
			//清除激活码
			user.setState(1);
			user.setCode(null);
			//对数据库执行一次更新
			userDao.updateUser(user);
			return true;
		}
		return false;
	}

	public User userLogin(User user) throws SQLException {
		// TODO Auto-generated method stub
		UserDao userDao=new UserDaoImp();
		User user2=userDao.userLogin(user);
		if (null==user2) {
		throw new 	RuntimeException( "密码有误");
		}
		else if (user2.getState()==0) {
			throw new 	RuntimeException( "您未激活");	
		}
		else {
			return user2;
		}
	
		
	}
	
}
