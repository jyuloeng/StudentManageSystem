package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Score;

public class ScoreDao extends BaseDao {
	
	//¼��ɼ���Ϣ
	public boolean addScore(Score score) {
		String sql = "insert into s_score values(null,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, score.getStudentId());
			ps.setInt(2, score.getCourseId());
			ps.setInt(3, score.getScore());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	//�ж��Ƿ��Ѿ�¼��
	public boolean isAdd(Score score) {
		String sql = "select * from s_score where studentId=? and courseId=? ";
		try {
			java.sql.PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, score.getStudentId());
			ps.setInt(2, score.getCourseId());
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
	
	//��ȡ�ɼ�
	public List<Score> getScoreList(Score score) {
		List<Score> retList = new ArrayList<Score>();
		StringBuffer sql = new StringBuffer("select * from s_score");
		if(score.getStudentId() != 0){
			sql.append(" and studentId = "+score.getStudentId());
		}
		if(score.getCourseId() != 0){
			sql.append(" and courseId ="+score.getCourseId());
		}
		try {
			PreparedStatement ps = con.prepareStatement(sql.toString().replaceFirst("and", "where"));
			ResultSet eq = ps.executeQuery();
			while(eq.next()) {
				Score sc = new Score();
				sc.setId(eq.getInt("id"));
				sc.setStudentId(eq.getInt("studentId"));
				sc.setCourseId(eq.getInt("courseId"));
				sc.setScore(eq.getInt("score"));
				retList.add(sc);
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retList;
	}
	
	//���³ɼ�
	public boolean updateScore(Score score) {
		String sql ="update s_score set score=? where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, score.getScore());
			ps.setInt(2, score.getId());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return false;
	}
	
	//ɾ���ɼ�
	public boolean deleteScore(Score score) {
		String sql="delete from s_score where id=?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, score.getId());
			if(ps.executeUpdate()>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		return false;
	}
	
	//�ڳɼ�ͳ������ʾ�ɼ�
	//ʹ��Mapӳ�伯����������ݿ���value��Ϣ��������Ӧ��keyֵ
	public Map<String,String> getScoreInfo(int courseId) {
		Map<String,String> retMap = new HashMap<String,String>();
		String sql="SELECT COUNT(id) as studentNum,MAX(score) as maxScore,"
				+ "MIN(score) as minScore,AVG(score) as avgScore,"
				+ "sum(case when score between 100 and 101 then 1 else 0 end) as '��������',"
				+ "sum(case when score between 90 and 99 then 1 else 0 end) as 'aNum',"
				+ "sum(case when score between 80 and 89 then 1 else 0 end) as 'bNum',"
				+ "sum(case when score between 70 and 79 then 1 else 0 end) as 'cNum',"
				+ "sum(case when score between 60 and 69 then 1 else 0 end) as 'dNum',"
				+ "sum(case when score between 0 and 59 then 1 else 0 end) as 'eNum' "
				+ "from s_score WHERE courseId =?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, courseId);
			ResultSet eq = ps.executeQuery();
			if(eq.next()) {
				retMap.put("studentNum", eq.getInt("studentNum")+"");
				retMap.put("maxScore", eq.getInt("maxScore")+"");
				retMap.put("minScore", eq.getInt("minScore")+"");
				retMap.put("avgScore", eq.getFloat("avgScore")+"");
				retMap.put("��������", eq.getInt("��������")+"");
				retMap.put("aNum", eq.getInt("aNum")+"");
				retMap.put("bNum", eq.getInt("bNum")+"");
				retMap.put("cNum", eq.getInt("cNum")+"");
				retMap.put("dNum", eq.getInt("dNum")+"");
				retMap.put("eNum", eq.getInt("eNum")+"");
			}
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		return retMap;
	}
}
