package com.hhtc.sams.Model;

public class Admin extends User {
	public Admin() {
	}

	/**
	 * ID:账号 PWD:密码 Name:姓名 Gender:性别 YoB:出生年月 Remark:备用字段(默认为空)
	 */
	public Admin(String ID, String PWD, String Name, String Gender, String YoB, String Remark) {
		this.setID(ID);
		this.setPWD(PWD);
		this.setName(Name);
		this.setGender(Gender);
		this.setYoB(YoB);
		this.setRemark(Remark);
	}

	@Override
	public String toString() {
		return "{ \"ID\":\"" + getID() + "\", \"PWD\":\"" + getPWD() + "\", \"Name\":\"" + getName() + "\", \"YoB\":\""
				+ getYoB() + "\", \"Gender\":\"" + getGender() + "\", \"Remark\":\"" + getRemark() + "\"}";
	}
}
/*
 * 
 * 
 * public STATE Register(Admin adm){ DatabaseHelper db = new DatabaseHelper();
 * String insert = "INSERT INTO "+TABLE_NAME.tstu+"VALUES('"
 * +adm.ID+"','"+adm.PWD+"','"+adm.Name
 * +"','"+adm.Gender+"','"+adm.YoB+"','"+"','"+adm.Remark; boolean m =
 * db.execute(insert); if(m){ return STATE.REGISTER_SUCCESS; } return
 * STATE.REGISTER_FAILURE; }
 * 
 * public STATE Login(String ID, String PWD){ DatabaseHelper db = new
 * DatabaseHelper(); String query =
 * "SELECT * FROM "+TABLE_NAME.tadm+" WHERE ID='"+ID+"' and PWD='"+PWD+"'";
 * ResultSet m = db.query(query); try { while(m.next()){ this.ID =
 * m.getString("ID"); this.Name = m.getString("Name"); this.Gender =
 * m.getString("Gender"); this.YoB = m.getString("YoB"); this.Remark =
 * m.getString("Remark"); return STATE.LOGIN_SUCCESS; } } catch (SQLException e)
 * { // TODO Auto-generated catch block e.printStackTrace(); } return
 * STATE.LOGIN_FAILURE; }
 * 
 * public STATE Register(Teacher tch){ DatabaseHelper db = new DatabaseHelper();
 * String insert = "INSERT INTO "+TABLE_NAME.ttch+"VALUES('"
 * +tch.ID+"','"+tch.PWD+"','"+tch.Name
 * +"','"+tch.Gender+"','"+tch.YoB+"','"+tch.Title+"','"+tch.Remark; boolean m =
 * db.execute(insert); if(m){ return STATE.REGISTER_SUCCESS; } return
 * STATE.REGISTER_FAILURE; }
 * 
 * public STATE Login(String ID, String PWD){ DatabaseHelper db = new
 * DatabaseHelper(); String query =
 * "SELECT * FROM "+TABLE_NAME.ttch+" WHERE ID='"+ID+"' and PWD='"+PWD+"'";
 * ResultSet m = db.query(query); try { while(m.next()){ this.ID =
 * m.getString("ID"); this.Name = m.getString("Name"); this.Gender =
 * m.getString("Gender"); this.YoB = m.getString("YoB"); this.Title =
 * m.getString("Title"); this.Remark = m.getString("Remark"); return
 * STATE.LOGIN_SUCCESS; } } catch (SQLException e) { // TODO Auto-generated
 * catch block e.printStackTrace(); } return STATE.LOGIN_FAILURE; }
 * 
 * public STATE Register(Student stu){ DatabaseHelper db = new DatabaseHelper();
 * String insert = "INSERT INTO "+TABLE_NAME.tstu+"VALUES('"
 * +stu.ID+"','"+stu.PWD+"','"+stu.Name
 * +"','"+stu.Gender+"','"+stu.YoB+"','"+stu.ErlYear+"','"+stu.Remark; boolean m
 * = db.execute(insert); if(m){ return STATE.REGISTER_SUCCESS; } return
 * STATE.REGISTER_FAILURE; }
 * 
 * public STATE Login(String ID, String PWD){ DatabaseHelper db = new
 * DatabaseHelper(); String query =
 * "SELECT * FROM "+TABLE_NAME.tstu+" WHERE ID='"+ID+"' and PWD='"+PWD+"'";
 * ResultSet m = db.query(query); try { while(m.next()){ this.ID =
 * m.getString("ID"); this.Name = m.getString("Name"); this.Gender =
 * m.getString("Gender"); this.YoB = m.getString("YoB"); this.ErlYear =
 * m.getString("erlYear"); this.Remark = m.getString("Remark");
 * 
 * return STATE.LOGIN_SUCCESS; } } catch (SQLException e) { // TODO
 * Auto-generated catch block e.printStackTrace(); } return STATE.LOGIN_FAILURE;
 * }
 * 
 */