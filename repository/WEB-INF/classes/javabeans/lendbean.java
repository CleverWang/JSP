/*
 *lendbean.java
 *
 *���ߣ�����
 *
 *��Ϊʵ���豸ת�蹦����Ƶ�javabean
 *
 */

package javabeans;

public class lendbean{
	String lendDepart,handler,use,intendedData,backnews="��";
	//���ò��š�������Ա����;���⻹���ڡ�ת�����������Ϣ
	
	int num,internalNum;
	//�����������ڲ����
	
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