package view;

import java.awt.EventQueue;
import java.beans.PropertyVetoException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import dao.CourseDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCourseFrm extends JInternalFrame{
	private JTextField courseNameTextField;
	private JTextField maxStudentNumTextField;
	private JComboBox teacherNamecomboBox;
	
//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddCourseFrm window = new AddCourseFrm();
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
	public AddCourseFrm() {
		setFrameIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u9009\u8BFE\u6DFB\u52A0.png")));
		setTitle("\u9009\u8BFE\u6DFB\u52A0");
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 500, 285);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		label.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8BFE\u7A0B\u6DFB\u52A0.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(107, 40, 97, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(90, 80, 107, 23);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6700\u5927\u9009\u8BFE\u4EBA\u6570\uFF1A");
		label_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(54, 120, 139, 23);
		getContentPane().add(label_2);
		
		courseNameTextField = new JTextField();
		courseNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		courseNameTextField.setColumns(10);
		courseNameTextField.setBounds(207, 40, 165, 23);
		getContentPane().add(courseNameTextField);
		
		maxStudentNumTextField = new JTextField();
		maxStudentNumTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		maxStudentNumTextField.setColumns(10);
		maxStudentNumTextField.setBounds(207, 120, 165, 23);
		getContentPane().add(maxStudentNumTextField);
		
		teacherNamecomboBox = new JComboBox();
		teacherNamecomboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		teacherNamecomboBox.setBounds(207, 80, 165, 23);
		getContentPane().add(teacherNamecomboBox);
		setTeacher();
		
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.addActionListener(new ActionListener() {
			//确定按钮的实现
			public void actionPerformed(ActionEvent ae) {
				addValue(ae);
			}
		});
		affButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		affButton.setBounds(139, 175, 100, 30);
		getContentPane().add(affButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			//重置按钮的实现
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		resetButton.setBounds(250, 175, 100, 30);
		getContentPane().add(resetButton);
	}

	
	//获取教师列表
	private void setTeacher() {
		// TODO 自动生成的方法存根
		if("系统管理员".equals(MainFrm.userType.getName())) {
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = new TeacherDao().getTeacherList(new Teacher());
		for(Teacher tc: teacherList) {
			teacherNamecomboBox.addItem(tc);
		}
		}
		if("教师".equals(MainFrm.userType.getName())) {
			Teacher teacher = (Teacher) MainFrm.userObject;
			TeacherDao teacherDao = new TeacherDao();
			List<Teacher> teacherList = new TeacherDao().getTeacherList(new Teacher());
			for(Teacher tc: teacherList) {
				if(tc.getTeacherName().equals(teacher.getTeacherName()))
				teacherNamecomboBox.addItem(tc);
			}
			teacherNamecomboBox.setEnabled(false);
		}
	}
	
	//确定按钮的方法
	protected void addValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		
		String courseName = courseNameTextField.getText().toString();
		int maxStudentNum = 0;
		try {
			maxStudentNum = Integer.parseInt(maxStudentNumTextField.getText().toString());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "课程最大人数只能输入数字！");
			return;
		}
		Teacher selectItem = (Teacher) teacherNamecomboBox.getSelectedItem();
		
		//判断是否为空
		if(StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "课程名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(maxStudentNum)) {
			JOptionPane.showMessageDialog(this, "最大选课人数不能为空！");
			return;
		}
		
		//判断输入信息是否不对应
		if(!StringUtil.isHanzi(courseName)) {
			JOptionPane.showMessageDialog(this, "课程名只能为汉字！");
			return;
		}

		
		//添加信息
		Course cr = new Course();
		cr.setCourseName(courseName);
		cr.setTeacherName(selectItem.getTeacherName());
		cr.setMaxStudentNum(maxStudentNum);
		
		CourseDao courseDao = new CourseDao();
		if(courseDao.addCourse(cr)) {
			JOptionPane.showMessageDialog(this, "选课添加成功！");
		}else {
			JOptionPane.showMessageDialog(this, "选课添加失败！");
		}
		courseDao.closeDao();
		resetValue(ae);
	}
	//重置按钮的方法
	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		courseNameTextField.setText("");
		teacherNamecomboBox.setSelectedIndex(0);
		maxStudentNumTextField.setText("");
	}
	
	
}
