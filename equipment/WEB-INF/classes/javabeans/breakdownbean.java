package javabeans;

/*
 *����Ϊ�豸�������bean
 */
public class breakdownbean {
	
	// �ڲ�����
	int internalNum;
	// �������
	String breakdownReason;
	// ά��ʱ��
	String fixTime;
	// ά����Ա
	String fixMan;
	// ά�޽��
	String fixResult;
	// ά�޻���
	String fixBill;
	// ������Ϣ
	String backnews="��";
	
	public int getInternalNum() {
		return internalNum;
	}
	public void setInternalNum(int internalNum) {
		this.internalNum = internalNum;
	}
	public String getBreakdownReason() {
		return breakdownReason;
	}
	public void setBreakdown(String brackdown) {
		this.breakdownReason = brackdown;
	}
	public String getFixTime() {
		return fixTime;
	}
	public void setFixTime(String fixTime) {
		this.fixTime = fixTime;
	}
	public String getFixMan() {
		return fixMan;
	}
	public void setFixMan(String fixMan) {
		this.fixMan = fixMan;
	}
	public String getFixResult() {
		return fixResult;
	}
	public void setFixResult(String fixResult) {
		this.fixResult = fixResult;
	}
	public String getFixBill() {
		return fixBill;
	}
	public void setFixBill(String fixBill) {
		this.fixBill = fixBill;
	}
	public String getBacknews() {
		return backnews;
	}
	public void setBacknews(String backnews) {
		this.backnews = backnews;
	}
	

	
}
