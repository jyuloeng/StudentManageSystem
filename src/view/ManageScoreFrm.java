package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.ScoreDao;
import dao.SelectCourseDao;
import dao.StudentDao;
import model.Course;
import model.Score;
import model.Student;
import model.Teacher;
import util.StringUtil;

public class ManageScoreFrm extends JInternalFrame{
	private JTextField scoreTextField;
	private JTable scoreListTable;
	private JComboBox selectCourseNameComboBox;
	private JComboBox studentNameComboBox;
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
//					ManageScoreFrm window = new ManageScoreFrm();
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
	public ManageScoreFrm() {
		setFrameIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u7BA1\u7406\u56FE\u6807.png")));
		setTitle("\u6210\u7EE9\u7BA1\u7406");
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		label.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(297, 35, 107, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u9009\u8BFE\u540D\u79F0\uFF1A");
		label_1.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(50, 35, 107, 23);
		getContentPane().add(label_1);
		
		selectCourseNameComboBox = new JComboBox();
		setSelectCourseCombox();
		selectCourseNameComboBox.addItemListener(new ItemListener() {
			//添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					studentNameComboBox.removeAllItems();
					StudentDao studentDao = new StudentDao();
					studentList = studentDao.getStudentList(new Student());
					studentDao.closeDao();
					Course course = (Course) selectCourseNameComboBox.getSelectedItem();
					Score score = new Score();
					score.setCourseId(course.getId());
					setTable(score);
					List<Student> selectCourseStudentList =  getSelectStudentList(course);
					for(Student sd:studentList) {
						for(Student sd2:selectCourseStudentList) {
							if(sd.getId()==sd2.getId()) {
								studentNameComboBox.addItem(sd);
							}
						}
					}
				}
			}
		});
		selectCourseNameComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		selectCourseNameComboBox.setBounds(166, 35, 120, 23);
		getContentPane().add(selectCourseNameComboBox);
		
		
		studentNameComboBox = new JComboBox();
		studentNameComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameComboBox.setBounds(414, 35, 120, 23);
		getContentPane().add(studentNameComboBox);
		
		scoreListTable = new JTable();
		scoreListTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
				scoreTextField.setText(dft.getValueAt(scoreListTable.getSelectedRow(), 3).toString());
			}
		});
		scoreListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scoreListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6210\u7EE9Id", "\u5B66\u751F\u59D3\u540D", "\u9009\u8BFE\u540D\u79F0", "\u6240\u5F97\u6210\u7EE9"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(scoreListTable);
		scrollPane.setBounds(80, 86, 536, 290);
		getContentPane().add(scrollPane);
		
		
//		scrollPane.setColumnHeaderView(table);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u4FEE\u6539\u6210\u7EE9", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 397, 673, 99);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u6240\u5F97\u6210\u7EE9\uFF1A");
		label_2.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u6210\u7EE9\u6392\u884C.png")));
		label_2.setBounds(93, 37, 107, 23);
		panel.add(label_2);
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		
		scoreTextField = new JTextField();
		scoreTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		scoreTextField.setBounds(210, 37, 120, 23);
		panel.add(scoreTextField);
		scoreTextField.setColumns(10);
		
		JButton affButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		affButton.addActionListener(new ActionListener() {
			//更新成绩
			public void actionPerformed(ActionEvent e) {
				updateScore(e);
			}
		});
		affButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setBounds(340, 36, 121, 25);
		panel.add(affButton);
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton deleteButton = new JButton("\u5220\u9664\u6210\u7EE9");
		deleteButton.addActionListener(new ActionListener() {
			//删除成绩
			public void actionPerformed(ActionEvent e) {
				deleteScore(e);
			}
		});
		deleteButton.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u5220\u96641.png")));
		deleteButton.setBounds(471, 36, 121, 25);
		panel.add(deleteButton);
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			//查询
			public void actionPerformed(ActionEvent e) {
				Student student = (Student)studentNameComboBox.getSelectedItem();
				Course course  = (Course) selectCourseNameComboBox.getSelectedItem();
				Score sc = new Score();
				sc.setStudentId(student.getId());
				sc.setCourseId(course.getId());
				setTable(sc);
			}
		});
		button.setIcon(new ImageIcon(ManageScoreFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		button.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		button.setBounds(544, 34, 89, 25);
		getContentPane().add(button);
		initialize();
		setTable(new Score());
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 709, 536);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//可关闭缩小
				setClosable(true);
				setIconifiable(true);
	}
	
	//根据id换名字显示
		private String getStudentNameById(int id) {
			StudentDao studentDao = new StudentDao();
			studentList = studentDao.getStudentList(new Student());
			for(int i=0;i<studentList.size();i++) {
				if(studentList.get(i).getId()==id) {
					return studentList.get(i).getStudentName();
				}
			}
			return "";
		}
		private String getCourseNameById(int id) {
			CourseDao courseDao = new CourseDao();
			courseList = courseDao.getCourseList(new Course());
			for(int i=0;i<courseList.size();i++) {
				if(courseList.get(i).getId()==id) {
					return courseList.get(i).getCourseName();
				}
			}
			return "";
		}	
		
	//显示成绩信息
	private void setTable(Score sc) {
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		dft.setRowCount(0);
		ScoreDao scoreDao = new ScoreDao();
		List<Score> scoreList = scoreDao.getScoreList(sc);
		for(Score score:scoreList) {
			Vector vt = new Vector();
			vt.add(score.getId());
			vt.add(getStudentNameById(score.getStudentId()));
			vt.add(getCourseNameById(score.getCourseId()));
			vt.add(score.getScore());
			dft.addRow(vt);
		}
		scoreDao.closeDao();
	}
	
	//更新成绩
	protected void updateScore(ActionEvent e) {
		// TODO 自动生成的方法存根
		int row = scoreListTable.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(this, "请选中要修改成绩的同学!");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		int id = Integer.parseInt(dft.getValueAt(row, 0).toString());
		int scoreNum = Integer.parseInt(scoreTextField.getText().toString());
		ScoreDao scoreDao = new ScoreDao();
		Score score = new Score();
		
		if(StringUtil.isEmpty(scoreNum)){
			JOptionPane.showMessageDialog(this, "请填写要修改的成绩!");
			return;
		}
		if(!StringUtil.isNum(scoreTextField.getText().toString())) {
			JOptionPane.showMessageDialog(this, "选修课程的最大人数只能为阿拉伯数字！");
			return;
		}
		if(scoreNum<0) {
			JOptionPane.showMessageDialog(this, "修改的成绩必须大于或等于0!");
			return;
		}
		score.setId(id);
		score.setScore(scoreNum);
		
		if(scoreDao.updateScore(score)) {
			JOptionPane.showMessageDialog(this, "更新成绩成功!");
		}else {
			JOptionPane.showMessageDialog(this, "更新成绩失败!");
		}
		setTable(new Score());
		scoreDao.closeDao();
	}
	
	//显示选课名称复选框
	private void setSelectCourseCombox() {
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for(Course cr:courseList) {
			if("教师".equals(MainFrm.userType.getName())) {
				Teacher teacher = (Teacher) MainFrm.userObject;
				if(cr.getTeacherName().equals(teacher.getTeacherName())) {
					selectCourseNameComboBox.addItem(cr);
				}
				continue;	
			}
			selectCourseNameComboBox.addItem(cr);
		}
	}
	
	//根据选课id获得学生
	private List<Student> getSelectStudentList(Course course){
		SelectCourseDao scDao = new SelectCourseDao();
		List<Student> selectStudentList = scDao.getSelectStudentList(course);
		return selectStudentList;
	}
	
	//删除学生成绩->变为空
	protected void deleteScore(ActionEvent e) {
		// TODO 自动生成的方法存根
		int row = scoreListTable.getSelectedRow();
		if(row==-1) {
			JOptionPane.showMessageDialog(this, "请选中要删除成绩的同学!");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) scoreListTable.getModel();
		int id = Integer.parseInt(dft.getValueAt(row, 0).toString());
		if(JOptionPane.showConfirmDialog(this, "您确定要删除该课程信息吗？", "温馨提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)!=JOptionPane.OK_OPTION) {
			return;
		}
		ScoreDao scoreDao = new ScoreDao();
		Score score = new Score();
		score.setId(id);
		if(scoreDao.deleteScore(score)) {
			JOptionPane.showMessageDialog(this, "删除成绩成功!");
		}else {
			JOptionPane.showMessageDialog(this, "删除成绩失败!");
		}
		scoreDao.closeDao();
		setTable(new Score());
	}
}
