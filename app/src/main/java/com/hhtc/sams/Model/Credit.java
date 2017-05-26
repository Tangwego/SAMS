package com.hhtc.sams.Model;

public class Credit {
	private String ID;
	private int Credit;
	private String Remark;
	public Credit(){}
	public Credit(String iD, int credit, String remark) {
		super();
		ID = iD;
		Credit = credit;
		Remark = remark;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public int getCredit() {
		return Credit;
	}
	public void setCredit(int credit) {
		Credit = credit;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "{ \"ID\":\"" + getID() + "\", \"Credit\":\"" + getCredit() + "\", \"Remark\":\"" + getRemark() + "\"}";
	}
	
}
