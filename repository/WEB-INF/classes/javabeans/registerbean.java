/*
 *registerbean.java
 *
 *���ߣ�����
 *
 *��Ϊʵ���û�ע�Ṧ����Ƶ�javabean
 *
 */

package javabeans;

public class registerbean{
	String name,password,confirm,backnews="��";
	//�û��������롢ȷ�����롢ע�����������Ϣ
	
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