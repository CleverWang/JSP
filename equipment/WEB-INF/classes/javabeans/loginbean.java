/*
 *loginbean.java
 *
 *���ߣ�����
 *
 *��Ϊʵ���û���¼������Ƶ�javabean
 *
 */

package javabeans;

public class loginbean{
	String name,password,backnews="��";
	//�û��������롢��¼����������Ϣ
	
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