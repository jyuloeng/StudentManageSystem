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
		
		//�ɹر���С
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		label.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u8BFE\u7A0B\u6DFB\u52A0.png")));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label.setBounds(107, 40, 97, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6388\u8BFE\u8001\u5E08\uFF1A");
		label_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_1.setBounds(90, 80, 107, 23);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6700\u5927\u9009\u8BFE\u4EBA\u6570\uFF1A");
		label_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_2.setBounds(54, 120, 139, 23);
		getContentPane().add(label_2);
		
		courseNameTextField = new JTextField();
		courseNameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		courseNameTextField.setColumns(10);
		courseNameTextField.setBounds(207, 40, 165, 23);
		getContentPane().add(courseNameTextField);
		
		maxStudentNumTextField = new JTextField();
		maxStudentNumTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		maxStudentNumTextField.setColumns(10);
		maxStudentNumTextField.setBounds(207, 120, 165, 23);
		getContentPane().add(maxStudentNumTextField);
		
		teacherNamecomboBox = new JComboBox();
		teacherNamecomboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		teacherNamecomboBox.setBounds(207, 80, 165, 23);
		getContentPane().add(teacherNamecomboBox);
		setTeacher();
		
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.addActionListener(new ActionListener() {
			//ȷ����ť��ʵ��
			public void actionPerformed(ActionEvent ae) {
				addValue(ae);
			}
		});
		affButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		affButton.setBounds(139, 175, 100, 30);
		getContentPane().add(affButton);
		
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			//���ð�ť��ʵ��
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		resetButton.setBounds(250, 175, 100, 30);
		getContentPane().add(resetButton);
	}

	
	//��ȡ��ʦ�б�
	private void setTeacher() {
		// TODO �Զ����ɵķ������
		if("ϵͳ����Ա".equals(MainFrm.userType.getName())) {
		TeacherDao teacherDao = new TeacherDao();
		List<Teacher> teacherList = new TeacherDao().getTeacherList(new Teacher());
		for(Teacher tc: teacherList) {
			teacherNamecomboBox.addItem(tc);
		}
		}
		if("��ʦ".equals(MainFrm.userType.getName())) {
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
	
	//ȷ����ť�ķ���
	protected void addValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		
		String courseName = courseNameTextField.getText().toString();
		int maxStudentNum = 0;
		try {
			maxStudentNum = Integer.parseInt(maxStudentNumTextField.getText().toString());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(this, "�γ��������ֻ���������֣�");
			return;
		}
		Teacher selectItem = (Teacher) teacherNamecomboBox.getSelectedItem();
		
		//�ж��Ƿ�Ϊ��
		if(StringUtil.isEmpty(courseName)) {
			JOptionPane.showMessageDialog(this, "�γ�������Ϊ�գ�");
			return;
		}
		if(StringUtil.isEmpty(maxStudentNum)) {
			JOptionPane.showMessageDialog(this, "���ѡ����������Ϊ�գ�");
			return;
		}
		
		//�ж�������Ϣ�Ƿ񲻶�Ӧ
		if(!StringUtil.isHanzi(courseName)) {
			JOptionPane.showMessageDialog(this, "�γ���ֻ��Ϊ���֣�");
			return;
		}

		
		//�����Ϣ
		Course cr = new Course();
		cr.setCourseName(courseName);
		cr.setTeacherName(selectItem.getTeacherName());
		cr.setMaxStudentNum(maxStudentNum);
		
		CourseDao courseDao = new CourseDao();
		if(courseDao.addCourse(cr)) {
			JOptionPane.showMessageDialog(this, "ѡ����ӳɹ���");
		}else {
			JOptionPane.showMessageDialog(this, "ѡ�����ʧ�ܣ�");
		}
		courseDao.closeDao();
		resetValue(ae);
	}
	//���ð�ť�ķ���
	protected void resetValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		courseNameTextField.setText("");
		teacherNamecomboBox.setSelectedIndex(0);
		maxStudentNumTextField.setText("");
	}
	
	
}
