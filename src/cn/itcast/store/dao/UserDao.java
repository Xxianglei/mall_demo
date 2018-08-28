package cn.itcast.store.dao;

import java.sql.SQLException;

import cn.itcast.store.domian.User;

public interface UserDao {

	boolean userRegist(User user);

	User active(String code);

	void updateUser(User user);

	User userLogin(User user);

	

}
