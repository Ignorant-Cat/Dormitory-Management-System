package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Room;
import model.StudentInfo;
import model.StudentRoom;

public class StudentInfoDao extends BaseDao{

	public String addStudentInfo(StudentInfo tempStu , StudentRoom tempSR) {
		String resultStr = "添加失败";
		String sqlStr1 = "insert into student values(?,?,?,?,?,?,?,?)";
		String sqlStr2 = "insert into student_room values(?,?,?)";
		String sqlStr3 = "set foreign_key_checks = 0;";
		String sqlStr4 = "set foreign_key_checks = 1;";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr3);
			ResultSet executeQuery1 = this.pStatement.executeQuery();
			this.pStatement = this.con.prepareStatement(sqlStr2);
			this.pStatement.setInt(1,tempSR.getRoomnumber());
			this.pStatement.setString(2,tempSR.getBuildingname());
			this.pStatement.setString(3, tempStu.getUsername());

			
			if(this.pStatement.executeUpdate()>0) {
				this.pStatement = this.con.prepareStatement(sqlStr1);
				this.pStatement.setString(1,tempStu.getUsername());
				this.pStatement.setString(2,tempStu.getName());
				this.pStatement.setString(3,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 tempStu.getGrade());
				this.pStatement.setString(4,tempStu.getAcademy());
				this.pStatement.setString(5,tempStu.getMajor());
				this.pStatement.setString(6,tempStu.getSex());
				this.pStatement.setObject(7, tempStu.getAge());
				this.pStatement.setString(8,tempStu.getTelephone());				
				if(this.pStatement.executeUpdate()>0) {
					resultStr = "添加成功";
				}
				this.pStatement = this.con.prepareStatement(sqlStr4);
				ResultSet executeQuery2 = this.pStatement.executeQuery();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return resultStr;
	}

	public ArrayList <StudentInfo> queryAllStu() {
		ArrayList <StudentInfo> array = new ArrayList<StudentInfo>();
		String sqlStr = "select * from student";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next()) {
				StudentInfo tempStu = new StudentInfo();
				tempStu.setUsername(executeQuery.getString(1));
				tempStu.setName(executeQuery.getString(2));
				tempStu.setGrade(executeQuery.getString(3));
				tempStu.setAcademy(executeQuery.getString(4));
				tempStu.setMajor(executeQuery.getString(5));
				tempStu.setSex(executeQuery.getString(6));
				tempStu.setAge(executeQuery.getInt(7));
				if(tempStu.getAge()==0)tempStu.setAge(null);
				tempStu.setTelephone(executeQuery.getString(8));
				array.add(tempStu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		
		return array;
	}

	
	//条件搜索
	public ArrayList <StudentInfo> querySomeStu(StudentInfo tempStu){
		ArrayList <StudentInfo> arrays = new ArrayList<StudentInfo>();
		String sqlStr = "select * from student where student_number like '"+ tempStu.getUsername()+"%' and ifnull(name,'') like '"+ tempStu.getName() +"%' and ifnull(grade,'') like '"+ tempStu.getGrade()
		+"%' and ifnull(college,'') like '%"+ tempStu.getAcademy()+ "%' and ifnull(major,'') like '%"+tempStu.getMajor()+"%' and ifnull(sex,'') like '"+tempStu.getSex()+"%';";
		
		System.out.println(sqlStr);
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next()) {
				StudentInfo tempStu2 = new StudentInfo();
				tempStu2.setUsername(executeQuery.getString(1));
				tempStu2.setName(executeQuery.getString(2));
				tempStu2.setGrade(executeQuery.getString(3));
				tempStu2.setAcademy(executeQuery.getString(4));
				tempStu2.setMajor(executeQuery.getString(5));
				tempStu2.setSex(executeQuery.getString(6));
				tempStu2.setAge(executeQuery.getInt(7));
				tempStu2.setTelephone(executeQuery.getString(8));
				System.out.println(tempStu2.getUsername());
				System.out.println(tempStu2.getName());
				System.out.println(tempStu2.getGrade());
				System.out.println(tempStu2.getAcademy());
				System.out.println(tempStu2.getMajor());
				System.out.println(tempStu2.getSex());
				System.out.println(tempStu2.getAge());
				System.out.println(tempStu2.getTelephone());
				arrays.add(tempStu2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}			
		return arrays;
		
	}


	public String delStuDao (String username){
		String resultStr = "删除失败";
		String sqlStr = "delete from student where student_number = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1, username);
			if(this.pStatement.executeUpdate()>0) {
				resultStr = "删除成功";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return resultStr;
	}

	public StudentRoom queryStuRoom(String username){
		StudentRoom SR = new StudentRoom();
		String sqlStr = "select * from student_room where student_number = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr);
			this.pStatement.setString(1, username);
			ResultSet executeQuery = this.pStatement.executeQuery();
			while(executeQuery.next()) {
				StudentRoom tempStuRoom = new StudentRoom();
				tempStuRoom.setRoomnumber(executeQuery.getInt(1));
				tempStuRoom.setBuildingname(executeQuery.getString(2));
				tempStuRoom.setUsername(username);
//				System.out.println(tempStuRoom.getBuildingname());
//				System.out.println(tempStuRoom.getRoomnumber());
//				System.out.println(tempStuRoom.getUsername());
				SR = tempStuRoom;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		
		return SR;
	}

	public String editStuInfo(StudentInfo tempStu,StudentRoom SR) {
		String resultStr  = "修改失败";
		String sqlStr1 = "update student set name = ?,grade = ?,college = ?,major = ?,sex = ?,age = ?,tel= ? where student_number = ?";
		String sqlStr2 = "update student_room set room_number = ? ,Bname = ? where student_number = ?";
		try {
			this.pStatement = this.con.prepareStatement(sqlStr1);
			this.pStatement.setString(1, tempStu.getName());
			this.pStatement.setString(2, tempStu.getGrade());
			this.pStatement.setString(3, tempStu.getAcademy());
			this.pStatement.setString(4, tempStu.getMajor());
			this.pStatement.setString(5, tempStu.getSex());
			this.pStatement.setString(6, tempStu.getAge().toString());
			this.pStatement.setString(7, tempStu.getTelephone());
			this.pStatement.setString(8, tempStu.getUsername());

			if(this.pStatement.executeUpdate()>0) {
				this.pStatement = this.con.prepareStatement(sqlStr2);
				this.pStatement.setInt(1, SR.getRoomnumber());
				this.pStatement.setString(2, SR.getBuildingname());
				this.pStatement.setString(3, SR.getUsername());
				if(this.pStatement.executeUpdate()>0) {
					resultStr = "修改成功";
				}
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.close();
		}
		return resultStr;
		
	}
}
