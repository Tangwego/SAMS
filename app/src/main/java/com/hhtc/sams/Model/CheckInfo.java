package com.hhtc.sams.Model;

public class CheckInfo {
	private int CusID;
	private String StuID;
	private String ChkinTime;
	private int Row;
	private int Clm;
	private String ChkinState;
	private String Remark;

	public CheckInfo() {
	}

	public CheckInfo(int cusID, String stuID, String chkinTime, int row, int clm, String chkinState, String remark) {
		super();
		CusID = cusID;
		StuID = stuID;
		ChkinTime = chkinTime;
		Row = row;
		Clm = clm;
		ChkinState = chkinState;
		Remark = remark;
	}

	public int getCusID() {
		return CusID;
	}

	public void setCusID(int cusID) {
		CusID = cusID;
	}

	public String getStuID() {
		return StuID;
	}

	public void setStuID(String stuID) {
		StuID = stuID;
	}

	public String getChkinTime() {
		return ChkinTime;
	}

	public void setChkinTime(String chkinTime) {
		ChkinTime = chkinTime;
	}

	public int getRow() {
		return Row;
	}

	public void setRow(int row) {
		if(row<1)
			Row = 1;
		Row = row;
	}

	public int getClm() {
		return Clm;
	}

	public void setClm(int clm) {
		if(clm<1)
			Clm = 1;
		Clm = clm;
	}

	public String getChkinState() {
		return ChkinState;
	}

	public void setChkinState(String chkinState) {
		ChkinState = chkinState;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}
	
	@Override
	public String toString() {
		return "{ \"CusID\":\"" + getCusID() + "\", \"StuID\":\"" + getStuID() + "\", \"ChkinTime\":\"" + getChkinTime()
		+ "\", \"Row\":\"" + getRow() + "\", \"Clm\":\"" + getClm() +"\", \"ChkinState\":\"" + getChkinState() + 
		"\", \"Remark\":\"" + getRemark() + "\"}";
	}

}
