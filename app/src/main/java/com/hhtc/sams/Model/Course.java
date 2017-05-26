package com.hhtc.sams.Model;

public class Course {
	private int ID;
	private String Name;
	private String TchID;
	private int ChkinFlg;
	private String Remark;
	public Course(){}
	public Course(int iD, String name, String tchID, int chkinFlg, String remark) {
		super();
		ID = iD;
		Name = name;
		TchID = tchID;
		ChkinFlg = chkinFlg;
		Remark = remark;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTchID() {
		return TchID;
	}
	public void setTchID(String tchID) {
		TchID = tchID;
	}
	public int getChkinFlg() {
		return ChkinFlg;
	}
	public void setChkinFlg(int chkinFlg) {
		ChkinFlg = chkinFlg;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	@Override
	public String toString() {
		return "{ \"ID\":\"" + ID + "\", \"Name\":\"" + Name +"\", \"TchID\":\"" + TchID+"\", \"ChkinFlg\":\"" + ChkinFlg
				+ "\", \"Remark\":\"" + getRemark() + "\"}";
	}
	
}
