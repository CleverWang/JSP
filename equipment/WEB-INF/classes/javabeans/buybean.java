/*
 *buybean.java
 *
 *���ߣ�����
 *
 *��Ϊʵ�ֹ������豸������Ƶ�javabean
 *
 */

package javabeans;

public class buybean{
	String buyTime,name,brand,model,type,department,reciever,position,backnews="��";
	//����ʱ�䡢���ơ�Ʒ�ơ��ͺš����͡��������š������ˡ����λ�á��������������Ϣ
	
	int num,internalNum;
	//�����������ڲ����
	
	public void setBuyTime(String a){
		buyTime=a;
	}
	public String getBuyTime(){
		return buyTime;
	}
	
	public void setName(String a){
		name=a;
	}
	public String getName(){
		return name;
	}
	
	public void setBrand(String a){
		brand=a;
	}
	public String getBrand(){
		return brand;
	}
	
	public void setModel(String a){
		model=a;
	}
	public String getModel(){
		return model;
	}
	
	public void setType(String a){
		type=a;
	}
	public String getType(){
		return type;
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
	
	public void setDepartment(String a){
		department=a;
	}
	public String getDepartment(){
		return department;
	}
	
	public void setReciever(String a){
		reciever=a;
	}
	public String getReciever(){
		return reciever;
	}
	
	public void setPosition(String a){
		position=a;
	}
	public String getPosition(){
		return position;
	}
	
	public void setBacknews(String b){
		backnews=b;
	}
	public String getBacknews(){
		return backnews;
	}
}