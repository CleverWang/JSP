/*
 *registerbean.java
 *
 *作者：王聪
 *
 *此为实现用户注册功能设计的javabean
 *
 */

package javabeans;

public class registerbean{
	String name,password,confirm,backnews="无";
	//用户名、密码、确认密码、注册操作反馈信息
	
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
	
	public void setConfirm(String b){
		confirm=b;
	}
	public String getConfirm(){
		return confirm;
	}
	
	public void setBacknews(String b){
		backnews=b;
	}
	public String getBacknews(){
		return backnews;
	}
}