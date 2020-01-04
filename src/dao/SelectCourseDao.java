package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Course;
import model.SelectCourse;
import model.Student;
import util.StringUtil;

//ѡ����Ϣ�����ݿ������
public class SelectCourseDao extends BaseDao{
	//ȷ��ѡ��
	public boolean addCourse(SelectCourse scr) {
		
		String sql = "insert into s_selectcourse values(null,?,?)";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, scr.getStudentId());
			ps.setInt(2, scr.getCourseId());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	
	//ѡ���б����ʾ
	public List<SelectCourse> getCourseList(SelectCourse cr) {
		List<SelectCourse> retList = new ArrayList<SelectCourse>();
		String sql = "select * from s_selectcourse";
		if(cr.getCourseId()!=0) {
			sql+=" where courseId like '%"+cr.getCourseId()+"%'";			
		}
		if(cr.getStudentId()!=0) {
			sql+=" where studentId like '%"+cr.getStudentId()+"%'";			
		}
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ResultSet  eq = ps.executeQuery();
			while(eq.next()) {
				SelectCourse course = new SelectCourse();
				course.setId(eq.getInt("id"));
				course.setCourseId(eq.getInt("courseId"));
				course.setStudentId(eq.getInt("studentId"));
				retList.add(course);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
	//����ѧ��id��ʾ�γ���Ϣ
	public List<Course> getSelectCourseList(int studentId){
		List<Course> retList = new ArrayList<Course>();
		String sql = "SELECT sc.studentId,c.* FROM "
	+ "s_selectcourse sc,s_course c WHERE sc.courseID=c.id and studentId= ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, studentId);
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Course cr = new Course();
				cr.setId(eq.getInt("id"));
				cr.setCourseName("courseName");
				cr.setTeacherName("teacherName");
				retList.add(cr);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
	//�γ�id��ʾѧ����Ϣ
	public List<Student> getSelectStudentList(Course course){
		List<Student> retList = new ArrayList<Student>();
		String sql = "SELECT sc.courseId,s.* FROM "
		+ "s_selectcourse sc,s_student s WHERE sc.studentID=s.id and courseId= ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, course.getId());
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Student sd = new Student();
				sd.setId(eq.getInt("id"));
				sd.setStudentName("studentName");
				retList.add(sd);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
	//SELECT teacherName from s_course c,s_selectcourse sc where sc.studentId = 8 and c.id=sc.courseId
	
		//�ж��Ƿ�ѡ��
		public boolean isSelected(SelectCourse selectCourse) {
			String sql = "select * from s_selectcourse where studentId=? and courseId=? ";
			try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, selectCourse.getStudentId());
				ps.setInt(2, selectCourse.getCourseId());
				ResultSet eq =ps.executeQuery();
				if(eq.next()) {
						return true;
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return false;
		}
	//��ѡ�γ�	
		public boolean delete(int id) {
			String sql = "delete from s_selectcourse where id=?";
		try {
				java.sql.PreparedStatement ps = con.prepareStatement(sql);
				ps.setInt(1, id);
				if(ps.executeUpdate()>0) {
					return true;
				}
			} catch (SQLException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			return false;
		}
}

