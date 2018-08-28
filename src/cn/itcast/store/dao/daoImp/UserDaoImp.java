package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domian.User;
import cn.itcast.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	public boolean userRegist(User user) {

		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTelephone(), user.getBirthday(), user.getSex(), user.getState(), user.getCode() };
		System.out.println(user.getPassword() + "****");
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		try {
			qr.update(sql, params);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			return false;
		}

	}

	public User active(String code) {
		String sql = "select * from user where code=?";
		User user = null;
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public void updateUser(User user01) {
		String sql = "UPDATE USER SET username= ? ,PASSWORD=? ,NAME =? ,email =? ,telephone =? , birthday =?  ,sex =? ,state= ? , CODE = ? WHERE uid=?";
		Object[] params = { user01.getUsername(), user01.getPassword(), user01.getName(), user01.getEmail(),
				user01.getTelephone(), user01.getBirthday(), user01.getSex(), user01.getState(), user01.getCode(),
				user01.getUid() };
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public User userLogin(User user) {

		String sql = "select * from user where username=? and password=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		try {
			user = qr.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

}
