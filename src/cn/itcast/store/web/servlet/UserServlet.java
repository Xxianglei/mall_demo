package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.Request;

import cn.itcast.store.domian.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImp.UserServiceImp;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return "/jsp/register.jsp";
	}

	public String userRegist(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 接收表单数据
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();

		MyBeanUtils.populate(user, map);
		//System.out.println(user.toString());

		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			System.out.println(key);
			String[] strings = map.get(key);
			for (String string : strings) {
				System.out.println(string);
			}
		}
		System.out.println();
		// 调用业务层注册
		UserService userService=new UserServiceImp();
		boolean flag=userService.userRegist(user);
		if (flag) {
			// 成功跳转
			req.setAttribute("msg", "注册成功");
		}
		else {
			// 失败跳转
			req.setAttribute("msg", "注册失败");
		}
		
		
		return "/jsp/info.jsp";
	}
}
