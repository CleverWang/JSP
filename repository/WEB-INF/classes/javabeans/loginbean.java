/*
 *loginbean.java
 *
 *作者：王聪
 *
 *此为实现用户登录功能设计的javabean
 *
 */

package javabeans;

public class loginbean{
	String name,password,backnews="无";
	//用户名、密码、登录操作反馈信息
	
	public void setName(String a){
		name=a;
	}
	public String getName(){
		return name;
	}
	
	public void setPassword(String b){
		password=b;
	}
	public String getPassword(){
		return password;
	}
	
	public void setBacknews(String b){
		backnews=b;
	}
	public String getBacknews(){
		return backnews;
	}
}