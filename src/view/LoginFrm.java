package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.AdminDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.Admin;
import model.Student;
import model.Teacher;
import model.UserType;
import util.StringUtil;

import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JFrame;
public class LoginFrm extends JInternalFrame{

	JFrame frmCc;   //������
	private JTextField userNameTextField;	  //�û��������
	private JPasswordField passwordTextField; //�û����������
	private JComboBox userTypeComboBox;		  //�û�����ѡ��������
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrm window = new LoginFrm();
					window.frmCc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCc = new JFrame();
		frmCc.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png")));
		frmCc.setFont(new Font("΢���ź�", Font.PLAIN, 12));
		frmCc.setTitle("cc\u6559\u5B66\u4FE1\u606F\u7EFC\u5408\u7BA1\u7406\u7CFB\u7EDF");
		frmCc.setBounds(100, 100, 512, 375);
		frmCc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCc.getContentPane().setLayout(null);
		frmCc.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("  cc\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF\u767B\u9646\u754C\u9762");
		lblNewLabel.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png")));
		lblNewLabel.setFont(new Font("΢���ź� Light", Font.BOLD, 18));
		lblNewLabel.setBounds(91, 55, 320, 48);
		frmCc.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5B66\u751F2.png")));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(111, 130, 95, 23);
		frmCc.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u5BC6   \u7801\uFF1A");
		label.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5BC6\u78011.png")));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label.setBounds(111, 180, 95, 23);
		frmCc.getContentPane().add(label);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		userNameTextField.setBounds(209, 132, 160, 21);
		frmCc.getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		passwordTextField.setBounds(209, 182, 160, 21);
		frmCc.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u4E0B\u62C9\u68461.png")));
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(121, 225, 119, 23);
		frmCc.getContentPane().add(lblNewLabel_2);
		
		userTypeComboBox = new JComboBox();
		//���������Ĭ��ֵ����Ϊö����userType���ֵ
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] 
				{UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		userTypeComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		userTypeComboBox.setToolTipText("");
		userTypeComboBox.setBounds(232, 225, 113, 23);
		frmCc.getContentPane().add(userTypeComboBox);
		
		//��¼��ť��ʵ��
		JButton loginButton = new JButton("\u767B\u9646");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loginAction(ae);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u767B\u9646.png")));
		loginButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		loginButton.setBounds(140, 273, 95, 31);
		frmCc.getContentPane().add(loginButton);
		
		//���ð�ť��ʵ��
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("΢���ź�", Font.PLAIN, 17));
		resetButton.setBounds(250, 273, 95, 31);
		frmCc.getContentPane().add(resetButton);
	}
	
	//��¼��ť�¼�
	protected void loginAction(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		//�������������л�ȡ��Ӧ������
		String userName = userNameTextField.getText().toString();
		String passWord = passwordTextField.getText().toString();
		UserType selectedItem = (UserType)userTypeComboBox.getSelectedItem();
		
		//�ж��û����������Ƿ�Ϊ��
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(frmCc, "�û�������Ϊ�գ�");
			return;
		}
		if(StringUtil.isEmpty(passWord)){
			JOptionPane.showMessageDialog(frmCc, "�û����벻��Ϊ�գ�");
			return;
		}
		
		//�жϵ�½
		Admin admin = null;		//����һ��ϵͳ����Ա����
		Student student = null; //����һ��ѧ������
		Teacher teacher = null; //����һ����ʦ����
		
		if("ϵͳ����Ա".equals(selectedItem.getName())) {
			//ϵͳ����Ա��½
			AdminDao adminDao = new AdminDao(); //����һ���µ�ϵͳ����Ա���ݿ��������
			Admin adminTmp = new Admin();	//����һ���µ�ϵͳ����Ա����adminTmp
			adminTmp.setName(userName);		//����õ��û����Ž�adminTmp��
			adminTmp.setPassword(passWord); //����õ��û�����Ž�adminTmp��
			//ʹ��ֵ��Admin��admin�������AdminDao�з��ص�Admin���͵�ֵ
			//������ݿ���û��adminTmp�������ҵ��ͻ᷵�ؿ�ֵ�������������AdminDao��login��Admin admin)����
			admin = adminDao.login(adminTmp);	
			adminDao.closeDao();	//�ͷ���Դ
			if(admin == null) {		//�ж�admin���޽��յ����ݿ������
				JOptionPane.showMessageDialog(frmCc, "�û������������");
				return;
			}
			//��½����رգ�����������
			frmCc.dispose();
			new MainFrm(selectedItem,admin).setVisible(true);
			
		}else if("��ʦ".equals(selectedItem.getName())) {
			//��ʦ��½
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			teacherTmp.setTeacherId(userName);
			teacherTmp.setPassWord(passWord);
			teacher = teacherDao.login(teacherTmp);
			teacherDao.closeDao();
			if(teacher == null) {
				JOptionPane.showMessageDialog(frmCc, "�û������������");
				return;
			}
			frmCc.dispose();
			new MainFrm(selectedItem,teacher).setVisible(true);
		}else {
			//ѧ����½
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			studentTmp.setStudentId(userName);
			studentTmp.setPassWord(passWord);
			student =studentDao.login(studentTmp);
			studentDao.closeDao();
			if(student == null) {
				JOptionPane.showMessageDialog(frmCc, "�û������������");
				return;
			}
			frmCc.dispose();
			new MainFrm(selectedItem,student).setVisible(true);
		}
	}

	//���ð�ť�¼�
	protected void resetValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		userNameTextField.setText("");   
		passwordTextField.setText("");   
		userTypeComboBox.setSelectedIndex(0); 
	}
}
