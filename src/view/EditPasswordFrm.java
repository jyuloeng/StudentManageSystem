package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.Admin;
import model.Student;
import model.Teacher;
import model.UserType;
import util.StringUtil;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditPasswordFrm extends JInternalFrame {

//	JFrame frame;
	private JTextField oldPasswordTextField;	//ԭ���������
	private JTextField newPasswordTextField;	//�����������
	private JTextField affPasswordTextField;	//ȷ�����������
	private JButton resetButton;	//�ύ��ť
	private JLabel nowUserNewLabel;	//�û���

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					EditPasswordFrm window = new EditPasswordFrm();
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
	public EditPasswordFrm() {
		setFrameIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u7801.png")));

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setFont(new Font("΢���ź�", Font.PLAIN, 12));
//		setIconImage(Toolkit.getDefaultToolkit().getImage(EditPasswordFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u7801.png")));
		setTitle("\u4FEE\u6539\u5BC6\u7801");
		setBounds(100, 100, 450, 300);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//�ɹر���С
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		lblNewLabel.setBounds(98, 75, 91, 23);
		lblNewLabel.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u539F\u5BC6\u7801.png")));
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(98, 115, 91, 23);
		lblNewLabel_1.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setBounds(82, 155, 107, 23);
		lblNewLabel_2.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		oldPasswordTextField = new JTextField();
		oldPasswordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		oldPasswordTextField.setBounds(199, 75, 153, 21);
		oldPasswordTextField.setColumns(10);
		
		newPasswordTextField = new JTextField();
		newPasswordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		newPasswordTextField.setBounds(199, 115, 153, 21);
		newPasswordTextField.setColumns(10);
		
		affPasswordTextField = new JTextField();
		affPasswordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		affPasswordTextField.setBounds(199, 155, 153, 21);
		affPasswordTextField.setColumns(10);
		
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.setBounds(103, 203, 99, 31);
		affButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editPassword(ae);
			}
		});
		affButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		getContentPane().setLayout(null);
		
		resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setBounds(232, 203, 99, 31);
		resetButton.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		getContentPane().add(resetButton);
		getContentPane().add(lblNewLabel);
		getContentPane().add(lblNewLabel_1);
		getContentPane().add(lblNewLabel_2);
		getContentPane().add(affPasswordTextField);
		getContentPane().add(newPasswordTextField);
		getContentPane().add(oldPasswordTextField);
		getContentPane().add(affButton);
		
		JLabel lblNewLabel_3 = new JLabel("\u5F53\u524D\u7528\u6237\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(EditPasswordFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(82, 35, 107, 23);
		getContentPane().add(lblNewLabel_3);
		
		nowUserNewLabel = new JLabel("");
		nowUserNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		nowUserNewLabel.setEnabled(false);
		nowUserNewLabel.setBounds(199, 35, 120, 23);
		getContentPane().add(nowUserNewLabel);
		
		//��nowUser������
		if("ϵͳ����Ա".equals(MainFrm.userType.getName())){
			Admin admin = (Admin)MainFrm.userObject;
			nowUserNewLabel.setText(admin.getName());
		}else if("ѧ��".equals(MainFrm.userType.getName())) {
			Student student = (Student) MainFrm.userObject;
			nowUserNewLabel.setText(student.getStudentName());
		}else {
			Teacher teacher = (Teacher) MainFrm.userObject;
			nowUserNewLabel.setText(teacher.getTeacherName());
		}
	}
	//�޸�����
	protected void editPassword(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		//��ȡ��������������
		String oldPassword = oldPasswordTextField.getText().toString();
		String newPassword = newPasswordTextField.getText().toString();
		String affPassword = affPasswordTextField.getText().toString();
		
		if(StringUtil.isEmpty(oldPassword)) {
			JOptionPane.showMessageDialog(this, "����дԭ���룡");
			return;
		}
		if(StringUtil.isEmpty(newPassword)) {
			JOptionPane.showMessageDialog(this, "����д�����룡");
			return;
		}
		if(StringUtil.isEmpty(affPassword)){
			JOptionPane.showMessageDialog(this, "��ȷ�������룡");
			return;
		}
		if(!newPassword.equals(affPassword)) {
			JOptionPane.showMessageDialog(this, "���������벻һ�£�����������");
			return;
		}
		if(newPassword.equals(oldPassword)) {
			JOptionPane.showMessageDialog(this, "ԭ������������һ�£�����������");
			return;
		}
		
		if("ϵͳ����Ա".equals(MainFrm.userType.getName())){
		AdminDao adminDao = new AdminDao();	//����һ��AdminDao����ʹ��AdminDao���������ݿ�����ķ���
		Admin adminTmp = new Admin();	//����һ��Admin����׼������adminDao�в���
		Admin admin = (Admin)MainFrm.userObject;	//����һ��Admin������մӵ�½�����õ��û�������Ϣ
		adminTmp.setName(admin.getName());
		adminTmp.setId(admin.getId());
		adminTmp.setPassword(oldPassword);	//��֤ԭ�����Ƿ���ȷ
		JOptionPane.showMessageDialog(this, adminDao.editPassWord(adminTmp, newPassword));
		adminDao.closeDao();	//�ͷ���Դ
		oldPasswordTextField.setText("");   
		newPasswordTextField.setText("");   
		affPasswordTextField.setText("");
		return;
		}
		if("��ʦ".equals(MainFrm.userType.getName())){
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			Teacher teacher = (Teacher)MainFrm.userObject;
			teacherTmp.setTeacherId(teacher.getTeacherId());
			teacherTmp.setId(teacher.getId());
			teacherTmp.setPassWord(oldPassword);
			JOptionPane.showMessageDialog(this, teacherDao.editPassWord(teacherTmp, newPassword));
			teacherDao.closeDao();
			oldPasswordTextField.setText("");   
			newPasswordTextField.setText("");   
			affPasswordTextField.setText("");
			return;
			}
		if("ѧ��".equals(MainFrm.userType.getName())){
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			Student student = (Student)MainFrm.userObject;
			studentTmp.setStudentId(student.getStudentId());
			studentTmp.setId(student.getId());
			studentTmp.setPassWord(oldPassword);
			JOptionPane.showMessageDialog(this, studentDao.editPassWord(studentTmp, newPassword));
			studentDao.closeDao();
			oldPasswordTextField.setText("");   
			newPasswordTextField.setText("");   
			affPasswordTextField.setText("");
			return;
			}
		
	}
	
	//���ð�ť
	protected void resetValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		oldPasswordTextField.setText("");   
		newPasswordTextField.setText("");   
		affPasswordTextField.setText("");
	}
}
