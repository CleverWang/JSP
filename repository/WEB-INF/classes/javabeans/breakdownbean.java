package javabeans;

/*
 *此类为设备故障类的bean
 */
public class breakdownbean {
	
	// 内部编码
	int internalNum;
	// 故障情况
	String breakdownReason;
	// 维修时间
	String fixTime;
	// 维修人员
	String fixMan;
	// 维修结果
	String fixResult;
	// 维修花费
	String fixBill;
	// 反馈消息
	String backnews="无";
	
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
