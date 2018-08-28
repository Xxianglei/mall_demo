package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.omg.CORBA.Request;

import cn.itcast.store.domian.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImp.UserServiceImp;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {

	public String registUI(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return "/jsp/register.jsp";
	}
	public String loginUI(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		return "/jsp/login.jsp";
	}

	public String userRegist(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		// 接收表单数据
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();
		user.setUid(UUIDUtils.getId());
		user.setState(0);
		user.setCode(UUIDUtils.getCode());
		MyBeanUtils.populate(user, map);
		// System.out.println(user.toString());

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
		UserService userService = new UserServiceImp();
		boolean flag = userService.userRegist(user);
		if (flag) {
			// 成功跳转
			//发送邮件
			try {
				MailUtils.sendMail(user.getEmail(), user.getCode());
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("msg", "注册成功");
		} else {
			// 失败跳转
			req.setAttribute("msg", "注册失败");
		}

		return "/jsp/info.jsp";
	}
	
	public String active(HttpServletRequest req, HttpServletResponse resp) {
		// 获取激活码
		//调用业务层激活功能
		//进行激活的信息提示
		String code=req.getParameter("code");
		UserService userService=new UserServiceImp();
		boolean flag=userService.active(code);
		if (flag) {
			//激活成功
			req.setAttribute("msg", "用户激活成功请登录");
			return "/jsp/login.jsp";
			
		}
		else {
			req.setAttribute("msg", "激活失败");
			return "/jsp/info.jsp";
		}
		
		
	}
	public String userLogin(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//获取账号密码
		User user=new User();
		MyBeanUtils.populate(user, req.getParameterMap());
		//调用登录功能
		UserService userService=new UserServiceImp();
		User user2=null;  // 可以获取更多的用户数据 所以将其放入session
		try {
			user2=userService.userLogin(user);
			// 登录成功,将用户信息放入session
			req.getSession().setAttribute("loginUser", user2);
			resp.sendRedirect("/store-v5/jsp/index.jsp");
			return null;
		} catch (Exception e) {
			// TODO: handle exception
			String message = e.getMessage();
			req.setAttribute("msg", message);
			return "/jsp/login.jsp";
		}
	
	
	}
	public String logOut(HttpServletRequest req, HttpServletResponse resp) {
		// 清除会话
		req.getSession().invalidate();
		try {
			resp.sendRedirect("/store-v5/jsp/index.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	}
	

