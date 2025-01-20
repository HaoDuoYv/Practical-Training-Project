package pdsu.goodsharing.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import pdsu.goodsharing.model.User;
import pdsu.goodsharing.utils.FileUitil;

/**
 * 管理和维护用户信息的类，用户注册，登录验证
 * @author dtk
 *
 */
public class UserDao {
	static List<User> users;
	static String fname="user.dat";
	@SuppressWarnings("unchecked")
	public static String login(User user) {
		int i=0;
		if( userEmpty( user)) {
			return "用户名和密码不能为空";
		}
		users=(List<User>)FileUitil.load(fname);//读取文件数据到list
		if(users!=null) {
			for(User u:users) {
				if(user.getUname().equals(u.getUname())&&Arrays.equals(user.getPwd(), u.getPwd())) {
					
					return "登录成功"; 
				}
				if(user.getUname().equals(u.getUname())) {
					i++;
				}
				
			}
				
			}else {
				users=new ArrayList<User>();
				return "该用户未注册";
		}
		if(i==0) {
			return "该用户未注册";
		}
		
		return "登录失败，请检查密码是否正确";
		
	}
	
	public static String reg(User user) {
		//判断用户名信息是否为空
		//用户名是否已存在 思路：便利List集合 查看是否有重名，如果有提示已存在，如果没有这写入文件
		if( userEmpty( user)) {
			return "用户名和密码不能为空";
		}
		users=(List<User>)FileUitil.load(fname);//读取文件数据到list
		if(users!=null) {
			for(User u:users) {
				if(user.getUname().equals(u.getUname())) {
					return "用户名已存在";
				}
			}
				
			}else {
				users=new ArrayList<User>();
				
		}
		users.add(user);
		FileUitil.save(users, fname);
		return "注册成功";
		
	}
	//public static String Login(User user) {
		
	//}
	//判断用户名或密码是否为空
  static boolean userEmpty(User user){
		if(user.getUname()==null||user.getUname().isEmpty()||user.getPwd()==null||user.getPwd().length==0) {
			return true;
		}
		return false;
	}

public static String gai(String curuser,char[] pwd,char[]pwd1) {
	// TODO 自动生成的方法存根
	users=(List<User>)FileUitil.load(fname);
	if(pwd.length==0||pwd==null) {
		return "密码不能为空";
	}
	else{for(User u :users) {
		if(u.getUname().equals(curuser)) {
			
			if(Arrays.equals(u.getPwd(), pwd1)) {
			u.setPwd(pwd);
			FileUitil.save(users, fname);
			return "修改成功";}else {
				return "原密码错误";
			}
			
		}
	}
	}
	return"修改失败";
}
}
