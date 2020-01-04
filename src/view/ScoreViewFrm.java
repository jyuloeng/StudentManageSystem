package view;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.ScoreDao;
import dao.SelectCourseDao;
import model.Course;
import model.Score;
import model.SelectCourse;
import model.Student;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ScoreViewFrm extends JInternalFrame{
	private JComboBox selectCourseComboBox;
	private JLabel studentNameLabel;
	private JTable scoreListTable;
	private List<Student> studentList = new ArrayList<Student>();
	private List<Course> courseList = new ArrayList<Course>();
//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ScoreViewFrm window = new ScoreViewFrm();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ScoreViewFrm() {
		setFrameIcon(new ImageIcon(ScoreViewFrm.class.getResource("/images/\u67E5\u770B\u6210\u7EE93.png")));
		setTitle("\u4E2A\u4EBA\u6210\u7EE9");
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(ScoreViewFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(80, 45, 107, 23);
		getContentPane().add(label);
		
		studentNameLabel = new JLabel("");
		//给学生姓名框上名字
		if("学生".equals(MainFrm.userType.getName())) {
			Student student = (Student) MainFrm.userObject;
			studentNameLabel.setText(student.getStudentName());
		}
		studentNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		studentNameLabel.setEnabled(false);
		studentNameLabel.setBounds(185, 45, 120, 23);
		getContentPane().add(studentNameLabel);
		
		
		
		JLabel label_1 = new JLabel("\u9009\u8BFE\u540D\u79F0\uFF1A");
		label_1.setIcon(new ImageIcon(ScoreViewFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(290, 45, 107, 23);
		getContentPane().add(label_1);
		
		scoreListTable = new JTable();
		scoreListTable.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		scoreListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6210\u7EE9Id", "\u5B66\u751F\u59D3\u540D", "\u8BFE\u7A0B\u540D\u79F0", "\u6240\u5F97\u6210\u7EE9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		setAllTable() ;
		
		selectCourseComboBox = new JComboBox();
		selectCourseComboBox.addItemListener(new ItemListener() {
			//添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					CourseChange(e);
				}
			}
		});
		selectCourseComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		selectCourseComboBox.setBounds(395, 45, 120, 23);
		getContentPane().add(selectCourseComboBox);
		setselectCourseComboBox();
		
		
		
		JScrollPane scrollPane = new JScrollPane(scoreListTable);
		scrollPane.setBounds(80, 98, 425, 312);
		getContentPane().add(scrollPane);
		
	
//		scrollPane.setColumnHeaderView(scoreListTable);
		
		JButton ViewButton = new JButton("\u67E5\u770B\u5168\u90E8");
		ViewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAllTable() ;
			}
		});
		ViewButton.setIcon(new ImageIcon(ScoreViewFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		ViewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ViewButton.setBounds(223, 442, 121, 25);
		getContentPane().add(ViewButton);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 600, 536);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
	}

	//设置成绩
	private void setScoreList(Score score) {
		Student student = (Student) MainFrm.userObject;
		DefaultTableModel  dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		ScoreDao scoreDao = new ScoreDao();
		List<Score> scoreList = scoreDao.getScoreList(score);
		for(Score sc:scoreList) {
			Vector vt = new Vector<>();
			vt.add(sc.getId());
			vt.add(student.getStudentName());
			vt.add(getCourseById(sc.getCourseId()));
			vt.add(sc.getScore());
			dft.addRow(vt);
		}
		scoreDao.closeDao();
	}
	
	//显示成绩
	private void setAllTable() {
			Student student = (Student) MainFrm.userObject;
			Score score = new Score();
//			Course course = (Course) selectCourseComboBox.getSelectedItem();
			score.setStudentId(student.getId());
//			score.setCourseId(course.getId());
			setScoreList(score);
	}
	
	//显示成绩
	private void setTable() {
		Student student = (Student) MainFrm.userObject;
		Score score = new Score();
		Course course = (Course) selectCourseComboBox.getSelectedItem();
		score.setStudentId(student.getId());
		score.setCourseId(course.getId());
		setScoreList(score);
	}
	
	//根据选择课程看成绩
	protected void CourseChange(ItemEvent e) {
	// TODO 自动生成的方法存根
		setTable();
	}
	
	//根据学生显示选课
	private void setselectCourseComboBox() {
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		Student student = (Student) MainFrm.userObject;
		SelectCourse selectCourse = new SelectCourse();
		selectCourse.setStudentId(student.getId());
		SelectCourseDao scD = new SelectCourseDao();
		List<SelectCourse> selectCourseList = scD.getCourseList(selectCourse);;
		for(SelectCourse sc:selectCourseList) {
			selectCourseComboBox.addItem(getCourseById(sc.getCourseId()));
		}
		scD.closeDao();
	}
	
	//根据id显示课程名称
	private Course getCourseById(int id) {
		
		for(int i = 0;i<courseList.size();i++) {
			if(courseList.get(i).getId()==id) {
				return courseList.get(i);
			}
		}
		return null;
	}
	
	//根据id换名字显示
		private String getStudentNameById(int id) {
			for(int i=0;i<studentList.size();i++) {
				if(studentList.get(i).getId()==id) {
					return studentList.get(i).getStudentName();
				}
			}
			return "";
		}
		private String getCourseNameById(int id) {
			for(int i=0;i<courseList.size();i++) {
				if(courseList.get(i).getId()==id) {
					return courseList.get(i).getCourseName();
				}
			}
			return "";
		}	
}
