package cn.itcast.store.service;

import java.sql.SQLDataException;
import java.sql.SQLException;

import cn.itcast.store.domian.User;

public interface UserService {

	public boolean userRegist(User user);


	public boolean active(String code);


	public User userLogin(User user) throws SQLException;



}
