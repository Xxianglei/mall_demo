package cn.itcast.store.dao.daoImp;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domian.User;
import cn.itcast.store.utils.JDBCUtils;

public class UserDaoImp implements UserDao {

	public void userRegist(User user) {
	
			String sql="INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
			Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
			QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
			try {
				qr.update(sql,params);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
	}

	
}
