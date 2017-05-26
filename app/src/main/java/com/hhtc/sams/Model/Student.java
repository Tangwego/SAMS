package com.hhtc.sams.Model;

public class Student extends User {
	private String ErlYear;

	public Student() {
	}

	/**
	 * ID:账号 PWD:密码 Name:姓名 Gender:性别 YoB:出生年月 erlYear:入学年份 Remark:备用字段(默认为空)
	 */
	public Student(String ID, String PWD, String Name, String Gender, String YoB, String ErlYear, String Remark) {
		this.setID(ID);
		this.setPWD(PWD);
		this.setName(Name);
		this.setGender(Gender);
		this.setYoB(YoB);
		this.setErlYear(ErlYear);
		this.setRemark(Remark);
	}

	public String getErlYear() {
		return ErlYear;
	}

	public void setErlYear(String erlYear) {
		this.ErlYear = erlYear;
	}

	@Override
	public String toString() {
		return "{ \"ID\":\"" + getID() + "\", \"PWD\":\"" + getPWD() + "\", \"Name\":\"" + getName() + "\", \"YoB\":\""
				+ getYoB() + "\", \"Gender\":\"" + getGender() + "\", \"ErlYear\":\"" + ErlYear + "\", \"Remark\":\"" + getRemark()
				+ "\"}";
	}

}
