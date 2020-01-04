package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.Course;
import model.SelectCourse;
import util.StringUtil;

//课程信息与数据库的连接
public class CourseDao extends BaseDao{

	//添加选课信息
	public boolean addCourse(Course cr) {
		
		String sql = "insert into s_course values(null,?,?,?,0)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cr.getCourseName());
			ps.setString(2, cr.getTeacherName());
			ps.setInt(3, cr.getMaxStudentNum());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	//课程列表的显示
	public List<Course> getCourseList(Course cr) {
		List<Course> retList = new ArrayList<Course>();
		String sql = "select * from s_course";
		if(!StringUtil.isEmpty(cr.getCourseName())) {
			sql+=" where courseName like '%"+cr.getCourseName()+"%'";			
		}
		if(!StringUtil.isEmpty(cr.getTeacherName())) {
			sql+=" where teacherName like '%"+cr.getTeacherName()+"%'";
		}
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ResultSet  eq = ps.executeQuery();
			while(eq.next()) {
				Course course = new Course();
				course.setId(eq.getInt("id"));
				course.setCourseName(eq.getString("courseName"));
				course.setTeacherName(eq.getString("teacherName"));
				course.setMaxStudentNum(eq.getInt("MaxStudentNum"));
				course.setSelectNum(eq.getInt("selectNum"));
				retList.add(course);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
	
	
	//更新课程信息
	public boolean update(Course cr) {
		String sql = "update s_course set teacherName=?,courseName=?,MaxStudentNum=? where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, cr.getTeacherName());
			ps.setString(2, cr.getCourseName());
			ps.setInt(3, cr.getMaxStudentNum());
			ps.setInt(4, cr.getId());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	//删除课程信息
	public boolean delete(String id) {
		String sql = "delete from s_course where id=?";
	try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return false;
	}
	
	//判断课程是否可选
	public boolean selectedEnable(int courseId) {
		String sql = "select * from s_course where id=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, courseId);
			ResultSet eq =ps.executeQuery();
			if(eq.next()) {
				int MaxStudentNum = eq.getInt("MaxStudentNum");
				int selectNum = eq.getInt("selectNum");
				//如果已选人数大于等于最大选课人数，返回false,代表课程不可选
				if(selectNum >= MaxStudentNum) {
					return false;
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return true;
	}
	
	//修改已选人数
	public boolean updateSelectedNum(int courseId,int num) {
		String sql = "update s_course set selectNum = selectNum +? where id=?";
		if(num<0) {
			sql = "update s_course set selectNum = selectNum -? where id=?";
		}
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, 1);
			ps.setInt(2, courseId);
			if(ps.executeUpdate()>0) {
					return true;
				}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		return false;
	}
	
	//根据老师名字获得选课
	public List<Course>  getTeachCourseList(String teacherName){
		List<Course> retList = new ArrayList<Course>();
		String sql ="select courseName from s_course where teacherName=?";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, teacherName);
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Course course = new Course();
				course.setId(eq.getInt("id"));
				course.setTeacherName(eq.getString("teacherName"));
				retList.add(course);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return retList;
	}
}
