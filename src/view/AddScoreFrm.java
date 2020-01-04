package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import dao.ClassDao;
import dao.CourseDao;
import dao.ScoreDao;
import dao.SelectCourseDao;
import dao.StudentDao;
import model.CollegeType;
import model.Course;
import model.Score;
import model.SelectCourse;
import model.Student;
import model.StudentClass;
import model.Teacher;
import util.StringUtil;

public class AddScoreFrm extends JInternalFrame{
	private JTextField sroceTextField;
	private JComboBox CollegeTypeComboBox;
	private JComboBox ClassComboBox;
	private JComboBox studentNameComboBox;
	private JComboBox courseNameComboBox;
	private List<SelectCourse> searchSelectCourseList = new ArrayList<SelectCourse>();
	private List<Student> studentList;
	private List<Course> courseList;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddScoreFrm window = new AddScoreFrm();
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
	public AddScoreFrm() {
		setTitle("\u5F55\u5165\u6210\u7EE9");
		setFrameIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u5F55\u5165\u6210\u7EE9.png")));
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(90, 120, 107, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		label_1.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(90, 80, 107, 23);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6240\u5C5E\u5B66\u9662\uFF1A");
		label_2.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(90, 40, 107, 23);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label_3.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_3.setBounds(90, 160, 107, 23);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u6240\u5F97\u6210\u7EE9\uFF1A");
		label_4.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u6210\u7EE9\u6392\u884C.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_4.setBounds(90, 200, 107, 23);
		getContentPane().add(label_4);
		
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.addActionListener(new ActionListener() {
			//录入成绩
			public void actionPerformed(ActionEvent e) {
				addScore(e);
			}
		});
		affButton.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		affButton.setBounds(139, 255, 100, 30);
		getContentPane().add(affButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			//重置按钮
			public void actionPerformed(ActionEvent e) {
				resetValues(e);
			}
		});
		resetButton.setIcon(new ImageIcon(AddScoreFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		resetButton.setBounds(250, 255, 100, 30);
		getContentPane().add(resetButton);
		
		CollegeTypeComboBox = new JComboBox();
		CollegeTypeComboBox.addItemListener(new ItemListener() {
			//添加下拉监听事件
			//下拉框监听事件
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
				ClassComboBox.removeAllItems();
				ClassDao classDao = new ClassDao();
				CollegeType ct = (CollegeType) CollegeTypeComboBox.getSelectedItem();
				List<StudentClass> serachClassList = classDao.getClassList(new StudentClass(), ct.getName());	
				for(StudentClass sc : serachClassList) {
					ClassComboBox.addItem(sc);
				}
				classDao.closeDao();
				}
			}
			});
		
		//将下拉框的默认值换用为枚举类CollegeType里的值
		CollegeTypeComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
			CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
			CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		
		CollegeTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		CollegeTypeComboBox.setBounds(207, 40, 165, 23);
		getContentPane().add(CollegeTypeComboBox);
		
		ClassComboBox = new JComboBox();
		ClassComboBox.addItemListener(new ItemListener() {
			//添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					studentNameComboBox.removeAllItems();
					StudentDao studentDao = new StudentDao();
					StudentClass sc = (StudentClass) ClassComboBox.getSelectedItem();
					List<Student> searchStudentList = studentDao.getStudentList(new Student(), sc.getClassName());
					for(Student sd:searchStudentList) {
						studentNameComboBox.addItem(sd);
					}
					studentDao.closeDao();
				}
			}
		});
		ClassComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ClassComboBox.setBounds(207, 80, 165, 23);
		getContentPane().add(ClassComboBox);
		
		studentNameComboBox = new JComboBox();
		studentNameComboBox.addItemListener(new ItemListener() {
			//添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					courseNameComboBox.removeAllItems();	//清楚课程名下拉框所有项
					CourseDao courseDao = new CourseDao();	//打开CoureDao连接数据库
					courseList = courseDao.getCourseList(new Course());	//将一个新的课程列表放入上文定义的列表courseList
					courseDao.closeDao();	
					String studentName = studentNameComboBox.getSelectedItem().toString();	//获得学生名字
					//根据学生Id新创建一个课程信息列表selectCourseList
					List<Course> selectCourseList = getSelectCourseList(getStudentIdByName(studentName));
					for(Course cr:courseList) {		//遍历courseList(即全部课程的列表)
						for(Course cr2:selectCourseList) { 	//遍历selectCourseList(即根据学生Id所选课程列表)
							if("系统管理员".equals(MainFrm.userType.getName())) {//若是管理员登陆
								if(cr.getId()==cr2.getId()) {	//则直接根据该等式判断全部课程列表中有哪个是根据学生Id所选出来的课程
									courseNameComboBox.addItem(cr);	//加入进下拉框中
								}
							}else {
								Teacher tc = (Teacher) MainFrm.userObject;	//若是教师登陆，则还要加如下判断该课程是否为登陆用户所教授的
								if(cr.getId()==cr2.getId()&&tc.getTeacherName().equals(cr.getTeacherName())) {
									courseNameComboBox.addItem(cr);
								}
							}
						}
					}
				}
			}
		});

		studentNameComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameComboBox.setBounds(207, 120, 165, 23);
		getContentPane().add(studentNameComboBox);
		
		courseNameComboBox = new JComboBox();
		courseNameComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		courseNameComboBox.setBounds(207, 160, 165, 23);
		getContentPane().add(courseNameComboBox);
		
		sroceTextField = new JTextField();
		sroceTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sroceTextField.setColumns(10);
		sroceTextField.setBounds(207, 200, 165, 23);
		getContentPane().add(sroceTextField);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 500, 365);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
	}
	
	//录入成绩
	protected void addScore(ActionEvent e) {
		// TODO 自动生成的方法存根
		int score = 0;
		try {
			score = Integer.parseInt(sroceTextField.getText().toString());
		}catch (Exception e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(this, "所得成绩只能为阿拉伯数字！");
			return;
		}
		if(score<=0) {
			JOptionPane.showMessageDialog(this, "所得成绩必须为大于或等于0的整数！");
			return;
		}
		
		String student = studentNameComboBox.getSelectedItem().toString();
		
		Course course = (Course) courseNameComboBox.getSelectedItem();
		Score sr = new Score();
		sr.setCourseId(course.getId());
		sr.setStudentId(getStudentIdByName(student));
		sr.setScore(score);
		ScoreDao scoreDao = new ScoreDao();
		if(scoreDao.isAdd(sr)) {
			JOptionPane.showMessageDialog(this, "学生成绩已经录入，请勿重复录入！");
			sroceTextField.setText("");
			return;
		}
		if(scoreDao.addScore(sr)) {
			JOptionPane.showMessageDialog(this, "学生成绩录入成功！");
			sroceTextField.setText("");
		}else {
			JOptionPane.showMessageDialog(this, "学生成绩录入失败！");
		}
		scoreDao.closeDao();
	}
	
	//根据学生名字获取id
	private int getStudentIdByName(String name) {
		StudentDao sd = new StudentDao();
		studentList = sd.getStudentList(new Student());
		sd.closeDao();
		for(int i=0;i<studentList.size();i++) {
			if(studentList.get(i).getStudentName().equals(name)) {
				return studentList.get(i).getId();
			}
		}
		return 0;
	}
	
	//根据学生id获得选课
	private List<Course> getSelectCourseList(int studentId){
		SelectCourseDao scDao = new SelectCourseDao();
		List<Course> selectCourseList = scDao.getSelectCourseList(studentId);
		return selectCourseList;
	}
	
	//重置成绩框
	protected void resetValues(ActionEvent e) {
		// TODO 自动生成的方法存根
		sroceTextField.setText("");
	}
}
