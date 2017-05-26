package com.hhtc.sams.Model;

public class Teacher extends User {
	private String Title;

	public Teacher() {
	}

	/**
	 * ID:账号 PWD:密码 Name:姓名 Gender:性别 YoB:出生年月 Title:教师职称 Remark:备用字段(默认为空)
	 */
	public Teacher(String ID, String PWD, String Name, String Gender, String YoB, String Title, String Remark) {
		this.setID(ID);
		this.setPWD(PWD);
		this.setName(Name);
		this.setGender(Gender);
		this.setYoB(YoB);
		this.setTitle(Title);
		this.setRemark(Remark);
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	@Override
	public String toString() {
		return "{ \"ID\":\"" + getID() + "\", \"PWD\":\"" + getPWD() + "\", \"Name\":\"" + getName() + "\", \"YoB\":\""
				+ getYoB() + "\", \"Gender\":\"" + getGender() + "\", \"Title\":\"" + getTitle() + "\", \"Remark\":\"" + getRemark()
				+ "\"}";
	}
}
