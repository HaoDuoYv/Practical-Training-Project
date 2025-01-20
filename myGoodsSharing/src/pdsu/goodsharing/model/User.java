package pdsu.goodsharing.model;

import java.io.Serializable;
import java.util.Arrays;

/**
 *封装用户信息，用户名，密码
 * @author dtk
 *
 */
@SuppressWarnings("serial")
public class User implements Serializable{
	//定义属性 私有的
	//用户名nmame，String
	//用户密码pwd，char[]
	private String uname;
	private char[]pwd;
	
	
	// 定义构造方法
	
	public User(String uname, char[] pwd) {
		super();
		this.uname = uname;
		this.pwd = pwd;
	}
	//定义get set方法
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public char[] getPwd() {
		return pwd;
	}

	public void setPwd(char[] pwd) {
		this.pwd = pwd;
	}



	//重新toString方法
	public String toString() {
		return "User[uname="+uname+",pwd"+Arrays.toString(pwd)+"]";
	}
}
	