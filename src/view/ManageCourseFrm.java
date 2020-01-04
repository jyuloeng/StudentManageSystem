package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.CourseDao;
import dao.TeacherDao;
import model.CollegeType;
import model.Course;
import model.StudentClass;
import model.Teacher;
import util.StringUtil;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ManageCourseFrm extends JInternalFrame{
	private JTextField maxStudentNumTextField;
	private JTextField courseNameTextField;
	private JTable CourseListTable;
	private JComboBox teacherComboBox;
	private List<Teacher> teacherList = new ArrayList<Teacher>();
	private JTextField searchTextField;
	private JComboBox searchTeacherComboBox;
	private JButton searchButton;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CourseManageFrm window = new CourseManageFrm();
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
	public ManageCourseFrm() {
		getContentPane().setEnabled(false);
		
		setTitle("\u8BFE\u7A0B\u7BA1\u7406");
		setFrameIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u5217\u8868.png")));
		getContentPane().setLayout(null);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 600, 536);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
		
		
		
		CourseListTable = new JTable();
		CourseListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		CourseListTable.addMouseListener(new MouseAdapter() {
			//添加鼠标点击时发生的事件
			@Override
			public void mouseClicked(MouseEvent ae) {
				selectTableRow(ae);
			}
		});
		
		CourseListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u8BFE\u7A0B\u7F16\u53F7", "\u8BFE\u7A0B\u540D\u79F0", "\u6388\u8BFE\u8001\u5E08", "\u6700\u5927\u6388\u8BFE\u4EBA\u6570", "\u5DF2\u9009\u4EBA\u6570"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		CourseListTable.getColumnModel().getColumn(3).setPreferredWidth(100);
		if("系统管理员".equals(MainFrm.userType.getName())) {
			setTable(new Course());
		}else {
			Teacher tc = (Teacher) MainFrm.userObject;
			Course course = new Course();
			course.setTeacherName(tc.getTeacherName());
			setTable(course);
		}
		
//		scrollPane.setColumnHeaderView(table);

		
		JScrollPane scrollPane = new JScrollPane(CourseListTable);
		scrollPane.setBounds(67, 108, 450, 254);
		getContentPane().add(scrollPane);
		
		
		JLabel label = new JLabel("\u6388\u8BFE\u6559\u5E08\uFF1A");
		label.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(55, 402, 107, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6700\u5927\u9009\u8BFE\u4EBA\u6570\uFF1A");
		label_1.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(20, 442, 139, 23);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0:");
		label_2.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(317, 402, 107, 23);
		getContentPane().add(label_2);
		
		teacherComboBox = new JComboBox();
		teacherComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherComboBox.setBounds(171, 402, 120, 23);
		getContentPane().add(teacherComboBox);
		
		
		maxStudentNumTextField = new JTextField();
		maxStudentNumTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		maxStudentNumTextField.setColumns(10);
		maxStudentNumTextField.setBounds(171, 442, 120, 23);
		getContentPane().add(maxStudentNumTextField);
		
		courseNameTextField = new JTextField();
		courseNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		courseNameTextField.setColumns(10);
		courseNameTextField.setBounds(433, 402, 120, 23);
		getContentPane().add(courseNameTextField);
		
		JButton affButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		affButton.addActionListener(new ActionListener() {
			//更新教师信息
			public void actionPerformed(ActionEvent ae) {
				editCourse(ae);
			}
		});
		affButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		affButton.setBounds(310, 441, 121, 25);
		getContentPane().add(affButton);
		
		JButton deleteButton = new JButton("\u5220\u9664\u8BFE\u7A0B");
		deleteButton.addActionListener(new ActionListener() {
			//删除课程
			public void actionPerformed(ActionEvent ae) {
				deleteCourse(ae);
			}
		});
		deleteButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u5220\u96641.png")));
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteButton.setBounds(440, 441, 121, 25);
		getContentPane().add(deleteButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "\u7F16\u8F91\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 372, 564, 113);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6388\u8BFE\u6559\u5E08\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(135, 35, 107, 23);
		getContentPane().add(lblNewLabel_1);
		
		searchTeacherComboBox = new JComboBox();
		setTeacherCombox();
		searchTeacherComboBox.addItemListener(new ItemListener() {
			//为下拉框添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					Teacher teacher = (Teacher) searchTeacherComboBox.getSelectedItem();
					Course cr = new Course();
					cr.setTeacherName(teacher.getTeacherName());
					setTable(cr);
				}
			}
		});
		searchTeacherComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchTeacherComboBox.setBounds(252, 35, 120, 23);
		getContentPane().add(searchTeacherComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(130, 68, 107, 23);
		getContentPane().add(lblNewLabel_2);
		
		searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			//搜索课程
			public void actionPerformed(ActionEvent e) {
				Course cr = new Course();
				cr.setCourseName(searchTextField.getText().toString());
				setTable(cr);
			}
		});
		searchButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchButton.setBounds(397, 53, 89, 25);
		getContentPane().add(searchButton);
		
		searchTextField = new JTextField();
		searchTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchTextField.setBounds(252, 68, 120, 23);
		getContentPane().add(searchTextField);
		searchTextField.setColumns(10);
		
		setLimit();
	}

	//删除课程信息
	protected void deleteCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
		CourseDao courseDao = new CourseDao();
		int row = CourseListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的课程信息！");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) CourseListTable.getModel();
		String id = dft.getValueAt(CourseListTable.getSelectedRow(), 0).toString();
		if(JOptionPane.showConfirmDialog(this, "您确定要删除该课程信息吗？", "温馨提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)!=JOptionPane.OK_OPTION) {
			return;
		}
		if(courseDao .delete(id)) {
			JOptionPane.showMessageDialog(this, "删除课程成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除课程失败！");
		}
		if("系统管理员".equals(MainFrm.userType.getName())) {
			setTable(new Course());
		}else {
			Teacher tc = (Teacher) MainFrm.userObject;
			Course course = new Course();
			course.setTeacherName(tc.getTeacherName());
			setTable(course);
		}
		
		courseDao.closeDao();
	}
	

	//确认修改课程信息
	protected void editCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
		CourseDao courseDao = new CourseDao();
		int row = CourseListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的课程信息！");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) CourseListTable.getModel();
		//获得修改前的信息
		String courseName = dft.getValueAt(CourseListTable.getSelectedRow(), 1).toString();
		int maxStudentNum = Integer.parseInt(dft.getValueAt(CourseListTable.getSelectedRow(), 3).toString());
		String teahcerName = dft.getValueAt(CourseListTable.getSelectedRow(), 2).toString();
		//获得修改后的信息
		String editCourseName = courseNameTextField.getText().toString();
		int editMaxStudentNum = Integer.parseInt(maxStudentNumTextField.getText().toString());
		Teacher editTeacher = (Teacher) teacherComboBox.getSelectedItem();
		//判断输入框是否为空值
		if(StringUtil.isEmpty(editCourseName)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的课程名称!");
			return;
		}
		if(StringUtil.isEmpty(editMaxStudentNum)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的选修课程的最大人数!");
			return;
		}
		if(!StringUtil.isNum(maxStudentNumTextField.getText().toString())) {
			JOptionPane.showMessageDialog(this, "选修课程的最大人数只能为阿拉伯数字！");
			return;
		}
		if(editMaxStudentNum<=0) {
			JOptionPane.showMessageDialog(this, "选修课程的最大人数必须大于0!");
			return;
		}
		//判断有无修改
		if(courseName.equals(editCourseName) && maxStudentNum==(editMaxStudentNum) 
				&& teahcerName.equals(editTeacher.getTeacherName())) {
			JOptionPane.showMessageDialog(this, "您还没有做任何修改!");
			return;
		}
		//获取课程数据表的id
		int id = Integer.parseInt( dft.getValueAt(CourseListTable.getSelectedRow(), 0).toString());
		//更新课程信息
		Course cr = new Course();
		cr.setId(id);
		cr.setCourseName(editCourseName);
		cr.setTeacherName(editTeacher.getTeacherName());
		cr.setMaxStudentNum(editMaxStudentNum);
		if(courseDao.update(cr)) {
			JOptionPane.showMessageDialog(this, "更新课程信息成功！");
		}else {
			JOptionPane.showMessageDialog(this, "更新课程信息失败！");
		}
		if("系统管理员".equals(MainFrm.userType.getName())) {
			setTable(new Course());
		}else {
			Teacher tc = (Teacher) MainFrm.userObject;
			Course course = new Course();
			course.setTeacherName(tc.getTeacherName());
			setTable(course);
		}
		courseDao.closeDao();
	}

	

	//选课列表显示
	private void setTable(Course course) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) CourseListTable.getModel();
		dft.setRowCount(0);
		CourseDao courseDao = new CourseDao();
		List<Course> courseList = courseDao.getCourseList(course);
		for(Course cr: courseList) {
			Vector vt = new Vector();
			vt.add(cr.getId());
			vt.add(cr.getCourseName());
			vt.add(cr.getTeacherName());
			vt.add(cr.getMaxStudentNum());
			vt.add(cr.getSelectNum());
			dft.addRow(vt);
		}
		courseDao.closeDao();
	}

	//为编辑信息的文本框添加信息
	protected void selectTableRow(MouseEvent ae) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) CourseListTable.getModel();
		courseNameTextField.setText(dft.getValueAt(CourseListTable.getSelectedRow(), 1).toString());
		maxStudentNumTextField.setText(dft.getValueAt(CourseListTable.getSelectedRow(), 3).toString());
		//按名称获得单选框里面的数据
		String editTeacher = dft.getValueAt(CourseListTable.getSelectedRow(), 2).toString();
		for(int i=0;i<teacherComboBox.getItemCount();i++) {
			Teacher tc = (Teacher) teacherComboBox.getItemAt(i);
			if(editTeacher.equals(tc.getTeacherName())) {
				teacherComboBox.setSelectedIndex(i);
			}
		}
	}
	
	private void setTeacherCombox() {
		if("系统管理员".equals(MainFrm.userType.getName())) {
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = new Teacher();
		List<Teacher> teacherList = teacherDao.getTeacherList(teacher);
		for(Teacher tc:teacherList) {
			teacherComboBox.addItem(tc);
			searchTeacherComboBox.addItem(tc);
		}
		}
		if("教师".equals(MainFrm.userType.getName())) {
			Teacher teacher = (Teacher) MainFrm.userObject;
			TeacherDao teacherDao = new TeacherDao();
			List<Teacher> teacherList = new TeacherDao().getTeacherList(new Teacher());
			for(Teacher tc: teacherList) {
				if(tc.getTeacherName().equals(teacher.getTeacherName()))
				
				searchTeacherComboBox.addItem(tc);
			}
			for(Teacher tc: teacherList) {
				if(tc.getTeacherName().equals(teacher.getTeacherName()))
				teacherComboBox.addItem(tc);
			}
			
			searchTeacherComboBox.setEnabled(false);
			teacherComboBox.setEnabled(false);
		}
	}
	
	private void setLimit(){
		if("教师".equals(MainFrm.userType.getName())) {
			searchButton.setEnabled(false);
			searchTextField.setEnabled(false);
		}
		
	}
}
