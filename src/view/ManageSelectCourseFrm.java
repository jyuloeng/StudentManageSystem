package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;
import javax.swing.border.TitledBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.SelectCourseDao;
import dao.StudentDao;
import model.Course;
import model.SelectCourse;
import model.SexType;
import model.Student;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageSelectCourseFrm extends JInternalFrame{
	private JTable selectCourseListTable;
	private JComboBox studentNameComboBox;
	private JComboBox courseComboBox;
	private JButton affButton;
	private JButton deleteButton;
	private List<Course> courseList = new ArrayList<Course>();
	private List<Student> studentList = new ArrayList<Student>();
	private JButton btnNewButton;
	

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					SelectCourseFrm window = new SelectCourseFrm();
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
	public ManageSelectCourseFrm() {
		setTitle("\u9009\u8BFE\u7BA1\u7406");
		setFrameIcon(new ImageIcon(ManageSelectCourseFrm.class.getResource("/images/\u7BA1\u7406\u56FE\u6807.png")));
		getContentPane().setLayout(null);
		
		selectCourseListTable = new JTable();
		selectCourseListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		selectCourseListTable.addMouseListener(new MouseAdapter() {
			//点击选中行时
			@Override
			public void mouseClicked(MouseEvent e) {
				selectTableRow(e);
			}
		});
		selectCourseListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u9009\u8BFE\u7F16\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u5B66\u751F\u59D3\u540D"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		
//		scrollPane.setColumnHeaderView(selectCourseListTable);
		JScrollPane scrollPane = new JScrollPane(selectCourseListTable);
		scrollPane.setBounds(80, 112, 425, 313);
		getContentPane().add(scrollPane);
		
		
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageSelectCourseFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(53, 45, 107, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("\u9009\u8BFE\u540D\u79F0\uFF1A");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setIcon(new ImageIcon(ManageSelectCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		lblNewLabel_2.setBounds(290, 45, 107, 23);
		getContentPane().add(lblNewLabel_2);
		
		studentNameComboBox = new JComboBox();
		studentNameComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentNameComboBox.addItemListener(new ItemListener() {
			//添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					Student student = (Student) studentNameComboBox.getSelectedItem();
					SelectCourse sc = new SelectCourse();
					sc.setStudentId(student.getId());
					setTable(sc);
				}
			}
		});
		studentNameComboBox.setBounds(159, 45, 115, 21);
		getContentPane().add(studentNameComboBox);
		setStudent();
		
		courseComboBox =  new JComboBox();
		courseComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		courseComboBox.setBounds(400, 45, 128, 21);
		getContentPane().add(courseComboBox);
		setCourse();
		
		affButton = new JButton("\u786E\u8BA4\u9009\u8BFE");
		affButton.setIcon(new ImageIcon(ManageSelectCourseFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		affButton.addActionListener(new ActionListener() {
			//确定选课
			public void actionPerformed(ActionEvent ae) {
				addSelectCourse();
			}
		});
		affButton.setBounds(228, 460, 121, 25);
		getContentPane().add(affButton);
		
		deleteButton = new JButton("\u9000\u9009\u8BFE\u7A0B");
		deleteButton.setIcon(new ImageIcon(ManageSelectCourseFrm.class.getResource("/images/\u5220\u96641.png")));
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteButton.addActionListener(new ActionListener() {
			//退选课程
			public void actionPerformed(ActionEvent ae) {
				deleteSelectCourse();
			}
		});
		deleteButton.setBounds(366, 460, 121, 25);
		getContentPane().add(deleteButton);
		
		btnNewButton = new JButton("\u67E5\u770B\u5168\u90E8");
		btnNewButton.setIcon(new ImageIcon(ManageSelectCourseFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			//刷新页面
			public void actionPerformed(ActionEvent e) {
				setTable(new SelectCourse());
			}
		});
		btnNewButton.setBounds(90, 460, 121, 25);
		getContentPane().add(btnNewButton);
		initialize();
		setLimit();

	}




	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 606, 547);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
		if("学生".equals(MainFrm.userType.getName())) {
			Student st = (Student) MainFrm.userObject;
			SelectCourse sc = new SelectCourse();
			sc.setStudentId(st.getId());
			setTable(sc);
			btnNewButton.setEnabled(false);
		}else {
			setTable(new SelectCourse());
		}
	}
	
	//显示学生列表
	private void setStudent() {
		StudentDao studentDao = new StudentDao();
		studentList = studentDao.getStudentList(new Student());
		for(Student st:studentList) {
			studentNameComboBox.addItem(st);
		}
		if("学生".equals(MainFrm.userType.getName())) {
			Student user = (Student) MainFrm.userObject;
			for(int i=0;i<studentNameComboBox.getItemCount();i++) {
				Student student = (Student)studentNameComboBox.getItemAt(i);
				if(student.getId()==user.getId()) {
					studentNameComboBox.setSelectedIndex(i);
					break;
				}
			}
		}
		studentDao.closeDao();
	}
	
	//显示选课列表
	private void setCourse() {
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		for(Course cr:courseList) {
			courseComboBox.addItem(cr);
		}
		courseDao.closeDao();
	}
	
	//显示已选课程信息
	private void setTable(SelectCourse scr) {
		DefaultTableModel dft = (DefaultTableModel) selectCourseListTable.getModel();
		dft.setRowCount(0);
		SelectCourseDao selectCourseDao = new SelectCourseDao();
		List<SelectCourse> selectCourseList = selectCourseDao.getCourseList(scr);
		for(SelectCourse selectCourse:selectCourseList) {
			Vector vt = new Vector();
			vt.add(selectCourse.getId());
			vt.add(getCourseNameById(selectCourse.getCourseId()));
			vt.add(getStudentNameById(selectCourse.getStudentId()));
			dft.addRow(vt);
		}
		selectCourseDao.closeDao();
	}	
	
	//添加选课
	protected void addSelectCourse() {
	// TODO 自动生成的方法存根
		Student studentId = (Student) studentNameComboBox.getSelectedItem();
		Course courseId = (Course) courseComboBox.getSelectedItem();
		
		//添加信息
		SelectCourse sc = new SelectCourse();
		sc.setStudentId(studentId.getId());
		sc.setCourseId(courseId.getId());
		
		SelectCourseDao selectCourseDao = new SelectCourseDao();
		CourseDao courseDao = new CourseDao();
		if(!courseDao.selectedEnable(courseId.getId())) {
			JOptionPane.showMessageDialog(this, "该课程已经满选，不能继续选该课程！");
			return;
		}
		
		if(selectCourseDao.isSelected(sc)) {
			JOptionPane.showMessageDialog(this, "您已经选过该课程，不能重复选课！");
			return;
		}
		if(selectCourseDao.addCourse(sc)) {
			if(courseDao.updateSelectedNum(sc.getCourseId(),1)) {
				JOptionPane.showMessageDialog(this, "选课成功！");
			}
		}else {
			JOptionPane.showMessageDialog(this, "选课失败！");
		}
		selectCourseDao.closeDao();
		SelectCourse sc1 = new SelectCourse();
		Student student = (Student) studentNameComboBox.getSelectedItem();
		sc1.setStudentId(student.getId());
		setTable(sc1);
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
	
	//根据名字换提取id

	@SuppressWarnings("unused")
	private int getCourseIdByName(String name) {
		for(int i=0;i<courseList.size();i++) {
			if(courseList.get(i).getCourseName().equals(name)) {
				return courseList.get(i).getId();
			}
		}
		return 0;
	}
	
	//单击选中行时的效果
	protected void selectTableRow(MouseEvent e) {
	// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) selectCourseListTable.getModel();
		//按名称获得单选框里的数据
		String courseName = dft.getValueAt(selectCourseListTable.getSelectedRow(), 1).toString();
		String studentName = dft.getValueAt(selectCourseListTable.getSelectedRow(), 2).toString();
		for(int i=0;i<courseComboBox.getItemCount();i++) {
			Course cr = (Course) courseComboBox.getItemAt(i);
			if(courseName.equals(cr.getCourseName())) {
				courseComboBox.setSelectedIndex(i);
				break;
			}
		}
	}	
	
	//退选课程
	protected void deleteSelectCourse() {
	// TODO 自动生成的方法存根
		//获取选中行
		int row = selectCourseListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要退选的课程！");
			return;
		}
		SelectCourse sc = new SelectCourse();
		SelectCourseDao scd = new SelectCourseDao();
		CourseDao courseDao = new CourseDao();
		DefaultTableModel dft = (DefaultTableModel) selectCourseListTable.getModel();
		int selectCourseId = Integer.parseInt(dft.getValueAt(row, 0).toString());
		String selectCourseName = dft.getValueAt(row, 1).toString();
		if(JOptionPane.showConfirmDialog(this, "您确定要退选该课程吗？", "温馨提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)!=JOptionPane.OK_OPTION) {
			return;
		}
		if(scd.delete(selectCourseId)) {
			if(courseDao.updateSelectedNum(getCourseIdByName(selectCourseName),-1)){
			JOptionPane.showMessageDialog(this, "退选课程成功！");
			}
		}else {
			JOptionPane.showMessageDialog(this, "退选课程失败！");
		}
		scd.closeDao();
		Student student = (Student) studentNameComboBox.getSelectedItem();
		sc.setStudentId(student.getId());
		setTable(sc);
	}
	
	//设置学生登陆时的权限
	@SuppressWarnings("unused")
	private void setLimit() {
		if("学生".equals(MainFrm.userType.getName())) {
			Student sd = (Student) MainFrm.userObject;
			studentNameComboBox.setEnabled(false);
		}
	}
}
