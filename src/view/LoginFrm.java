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

	JFrame frmCc;   //主界面
	private JTextField userNameTextField;	  //用户名输入框
	private JPasswordField passwordTextField; //用户密码输入框
	private JComboBox userTypeComboBox;		  //用户类型选择下拉框
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
		frmCc.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		frmCc.setTitle("cc\u6559\u5B66\u4FE1\u606F\u7EFC\u5408\u7BA1\u7406\u7CFB\u7EDF");
		frmCc.setBounds(100, 100, 512, 375);
		frmCc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCc.getContentPane().setLayout(null);
		frmCc.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("  cc\u5B66\u751F\u4FE1\u606F\u7BA1\u7406\u7CFB\u7EDF\u767B\u9646\u754C\u9762");
		lblNewLabel.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png")));
		lblNewLabel.setFont(new Font("微软雅黑 Light", Font.BOLD, 18));
		lblNewLabel.setBounds(91, 55, 320, 48);
		frmCc.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5B66\u751F2.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(111, 130, 95, 23);
		frmCc.getContentPane().add(lblNewLabel_1);
		
		JLabel label = new JLabel("\u5BC6   \u7801\uFF1A");
		label.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u5BC6\u78011.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(111, 180, 95, 23);
		frmCc.getContentPane().add(label);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userNameTextField.setBounds(209, 132, 160, 21);
		frmCc.getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		passwordTextField.setBounds(209, 182, 160, 21);
		frmCc.getContentPane().add(passwordTextField);
		passwordTextField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u4E0B\u62C9\u68461.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(121, 225, 119, 23);
		frmCc.getContentPane().add(lblNewLabel_2);
		
		userTypeComboBox = new JComboBox();
		//将下拉框的默认值换用为枚举类userType里的值
		userTypeComboBox.setModel(new DefaultComboBoxModel(new UserType[] 
				{UserType.ADMIN, UserType.TEACHER, UserType.STUDENT}));
		userTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userTypeComboBox.setToolTipText("");
		userTypeComboBox.setBounds(232, 225, 113, 23);
		frmCc.getContentPane().add(userTypeComboBox);
		
		//登录按钮的实现
		JButton loginButton = new JButton("\u767B\u9646");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				loginAction(ae);
			}
		});
		loginButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u767B\u9646.png")));
		loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		loginButton.setBounds(140, 273, 95, 31);
		frmCc.getContentPane().add(loginButton);
		
		//重置按钮的实现
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(LoginFrm.class.getResource("/images/\u91CD\u7F6E.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 17));
		resetButton.setBounds(250, 273, 95, 31);
		frmCc.getContentPane().add(resetButton);
	}
	
	//登录按钮事件
	protected void loginAction(ActionEvent ae) {
		// TODO 自动生成的方法存根
		//从三个操作框中获取相应的数据
		String userName = userNameTextField.getText().toString();
		String passWord = passwordTextField.getText().toString();
		UserType selectedItem = (UserType)userTypeComboBox.getSelectedItem();
		
		//判断用户名或密码是否为空
		if(StringUtil.isEmpty(userName)){
			JOptionPane.showMessageDialog(frmCc, "用户名不能为空！");
			return;
		}
		if(StringUtil.isEmpty(passWord)){
			JOptionPane.showMessageDialog(frmCc, "用户密码不能为空！");
			return;
		}
		
		//判断登陆
		Admin admin = null;		//定义一个系统管理员类型
		Student student = null; //定义一个学生类型
		Teacher teacher = null; //定义一个教师类型
		
		if("系统管理员".equals(selectedItem.getName())) {
			//系统管理员登陆
			AdminDao adminDao = new AdminDao(); //定义一个新的系统管理员数据库操作对象
			Admin adminTmp = new Admin();	//定义一个新的系统管理员对象adminTmp
			adminTmp.setName(userName);		//将获得的用户名放进adminTmp中
			adminTmp.setPassword(passWord); //将获得的用户密码放进adminTmp中
			//使空值的Admin类admin对象接受AdminDao中返回的Admin类型的值
			//如果数据库中没有adminTmp的数据找到就会返回空值（详情见上文中AdminDao的login（Admin admin)方法
			admin = adminDao.login(adminTmp);	
			adminDao.closeDao();	//释放资源
			if(admin == null) {		//判断admin有无接收到数据库的数据
				JOptionPane.showMessageDialog(frmCc, "用户名或密码错误！");
				return;
			}
			//登陆界面关闭，开启主界面
			frmCc.dispose();
			new MainFrm(selectedItem,admin).setVisible(true);
			
		}else if("教师".equals(selectedItem.getName())) {
			//教师登陆
			TeacherDao teacherDao = new TeacherDao();
			Teacher teacherTmp = new Teacher();
			teacherTmp.setTeacherId(userName);
			teacherTmp.setPassWord(passWord);
			teacher = teacherDao.login(teacherTmp);
			teacherDao.closeDao();
			if(teacher == null) {
				JOptionPane.showMessageDialog(frmCc, "用户名或密码错误！");
				return;
			}
			frmCc.dispose();
			new MainFrm(selectedItem,teacher).setVisible(true);
		}else {
			//学生登陆
			StudentDao studentDao = new StudentDao();
			Student studentTmp = new Student();
			studentTmp.setStudentId(userName);
			studentTmp.setPassWord(passWord);
			student =studentDao.login(studentTmp);
			studentDao.closeDao();
			if(student == null) {
				JOptionPane.showMessageDialog(frmCc, "用户名或密码错误！");
				return;
			}
			frmCc.dispose();
			new MainFrm(selectedItem,student).setVisible(true);
		}
	}

	//重置按钮事件
	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		userNameTextField.setText("");   
		passwordTextField.setText("");   
		userTypeComboBox.setSelectedIndex(0); 
	}
}
