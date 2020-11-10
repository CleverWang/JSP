/*
 *lendbean.java
 *
 *作者：王聪
 *
 *此为实现设备转借功能设计的javabean
 *
 */

package javabeans;

public class lendbean{
	String lendDepart,handler,use,intendedData,backnews="无";
	//借用部门、经手人员、用途、拟还日期、转借操作反馈信息
	
	int num,internalNum;
	//借用数量、内部编号
	
	public void setLendDepart(String a){
		lendDepart=a;
	}
	public String getLendDepart(){
		return lendDepart;
	}
	
	public void setHandler(String a){
		handler=a;
	}
	public String getHandler(){
		return handler;
	}
	
	public void setUse(String a){
		use=a;
	}
	public String getUse(){
		return use;
	}
	
	public void setIntendedData(String a){
		intendedData=a;
	}
	public String getIntendedData(){
		return intendedData;
	}
	
	public void setNum(int a){
		num=a;
	}
	public int getNum(){
		return num;
	}
	
	public void setInternalNum(int a){
		internalNum=a;
	}
	public int getInternalNum(){
		return internalNum;
	}
	
	public void setBacknews(String b){
		backnews=b;
	}
	public String getBacknews(){
		return backnews;
	}
}