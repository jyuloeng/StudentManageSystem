package view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Window.Type;
import java.awt.Toolkit;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Admin;
import model.UserType;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;
public class MainFrm extends JFrame	{

//	JFrame frmCc;
	private JDesktopPane desktopPane;
	public static UserType userType; //用户类型
	public static Object userObject; //用户抽象类
	
	private JMenu teacherNewMenu;	//教师信息按钮
	private JMenu studentNewMenu;	//学生信息按钮
	private JMenu classNewMenu;		//班级信息按钮
	private JMenu selectCourseNewMenu;	//选课信息按钮
	private JMenu courseNewMenu;	//课程信息按钮
	private JMenuItem addStudentMenuItem;	//添加学生按钮
	private JMenuItem addTeacherMenuItem;	//添加教师按钮
	private JMenuItem gradeInputMenuItem;	//成绩登记按钮
	private JMenuItem gradeViewMenuItem;	//成绩查看按钮
	private JMenuItem gradeManageMenuItem;	//成绩管理按钮
	private JMenuItem gradeStatisticsMenuItem;	//成绩统计按钮
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrm window = new MainFrm(null,null);
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param admin 
	 * @param selectedItem 
	 */
	public MainFrm(UserType userType, Object userObject) {
		this.userType = userType;
		this.userObject = userObject;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frmCc = new JFrame();
		setFont(new Font("微软雅黑", Font.PLAIN, 12));
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png")));
		setTitle("cc\u6559\u5B66\u4FE1\u606F\u7EFC\u5408\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 1000, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setToolTipText("\u83DC\u5355\u680F");
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("\u7CFB\u7EDF\u8BBE\u7F6E");
		mnNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7CFB\u7EDF\u8BBE\u7F6E.png")));
		mnNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editPassword(ae);
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u7801.png")));
		menuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA\u767B\u9646");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				exitMainFrm(ae);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u9000\u51FA\u767B\u9646.png")));
		menuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		mnNewMenu.add(menuItem_1);
		
		teacherNewMenu = new JMenu("\u6559\u5E08\u4FE1\u606F");
		teacherNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6559\u5E08\u7BA1\u7406.png")));
		teacherNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(teacherNewMenu);
		
		addTeacherMenuItem = new JMenuItem("\u6559\u5E08\u6DFB\u52A0");
		addTeacherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addTeacher(ae);
			}
		});
		addTeacherMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		addTeacherMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		teacherNewMenu.add(addTeacherMenuItem);
		
		//管理教师
		JMenuItem menuItem_5 = new JMenuItem("\u6559\u5E08\u7BA1\u7406");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ManageTeacher(ae);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		menuItem_5.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		teacherNewMenu.add(menuItem_5);
		
		studentNewMenu = new JMenu("\u5B66\u751F\u4FE1\u606F");
		studentNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		studentNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(studentNewMenu);
		
		addStudentMenuItem = new JMenuItem("\u5B66\u751F\u6DFB\u52A0");
		addStudentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addStudent(ae);
			}
		});
		addStudentMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5B66\u751F\u6DFB\u52A0.png")));
		addStudentMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		studentNewMenu.add(addStudentMenuItem);
		
		//管理学生
		JMenuItem menuItem_6 = new JMenuItem("\u5B66\u751F\u7BA1\u7406");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ManageStudent(ae);
			}
		});
		menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		menuItem_6.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		studentNewMenu.add(menuItem_6);
		
		classNewMenu = new JMenu("\u73ED\u7EA7\u4FE1\u606F");
		classNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u73ED\u7EA7\u7BA1\u74061.png")));
		classNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(classNewMenu);
		
		//添加班级
		JMenuItem menuItem_7 = new JMenuItem("\u73ED\u7EA7\u6DFB\u52A0");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addClass(ae);
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u73ED\u7EA7\u6DFB\u52A0.png")));
		menuItem_7.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		classNewMenu.add(menuItem_7);
		
		
		//管理班级
		JMenuItem menuItem_8 = new JMenuItem("\u73ED\u7EA7\u7BA1\u7406");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ManageClass(ae);
			}
		});
		menuItem_8.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		menuItem_8.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		classNewMenu.add(menuItem_8);
		
		selectCourseNewMenu = new JMenu("\u9009\u8BFE\u4FE1\u606F");
		selectCourseNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u9009\u8BFE2.png")));
		selectCourseNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(selectCourseNewMenu);
		
		JMenuItem courseManageMenuItem = new JMenuItem("\u9009\u8BFE\u7BA1\u7406");
		courseManageMenuItem.addActionListener(new ActionListener() {
			//管理选课
			public void actionPerformed(ActionEvent ae) {
				selectCourse(ae);
			}
		});
		courseManageMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		courseManageMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		selectCourseNewMenu.add(courseManageMenuItem);
		
		courseNewMenu = new JMenu("\u8BFE\u7A0B\u4FE1\u606F");
		courseNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B1.png")));
		courseNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(courseNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u8BFE\u7A0B\u6DFB\u52A0");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			//添加选课
			public void actionPerformed(ActionEvent ae) {
				addCourse(ae);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B\u6DFB\u52A0.png")));
		mntmNewMenuItem_1.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		courseNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u8BFE\u7A0B\u7BA1\u7406");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			//课程管理的实现
			public void actionPerformed(ActionEvent ae) {
				ManageCourse(ae);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		mntmNewMenuItem_2.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		courseNewMenu.add(mntmNewMenuItem_2);
		
		JMenu gradeInfoNewMenu = new JMenu("\u6210\u7EE9\u4FE1\u606F");
		gradeInfoNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6210\u7EE9.png")));
		gradeInfoNewMenu.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(gradeInfoNewMenu);
		
		gradeInputMenuItem = new JMenuItem("\u5F55\u5165\u6210\u7EE9");
		gradeInputMenuItem.addActionListener(new ActionListener() {
			//录入成绩
			public void actionPerformed(ActionEvent e) {
				AddCourse(e);
			}
		});
		gradeInputMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5F55\u5165\u6210\u7EE9.png")));
		gradeInputMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeInputMenuItem);
		
		gradeViewMenuItem = new JMenuItem("\u6210\u7EE9\u67E5\u770B");
		gradeViewMenuItem.addActionListener(new ActionListener() {
			//成绩查看
			public void actionPerformed(ActionEvent e) {
				ScoreView(e);
			}
		});
		gradeViewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u67E5\u770B\u6210\u7EE93.png")));
		gradeViewMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeViewMenuItem);
		
		gradeManageMenuItem = new JMenuItem("\u6210\u7EE9\u7BA1\u7406");
		gradeManageMenuItem.addActionListener(new ActionListener() {
			//管理成绩
			public void actionPerformed(ActionEvent e) {
				ManageScore(e);
			}
		});
		gradeManageMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7BA1\u7406\u56FE\u6807.png")));
		gradeManageMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeManageMenuItem);
		
		gradeStatisticsMenuItem = new JMenuItem("\u6210\u7EE9\u7EDF\u8BA1");
		gradeStatisticsMenuItem.addActionListener(new ActionListener() {
			//统计成绩
			public void actionPerformed(ActionEvent e) {
				StatisticScore(e);
			}
		});
		gradeStatisticsMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7EDF\u8BA1.png")));
		gradeStatisticsMenuItem.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeStatisticsMenuItem);
		
		JMenu mnNewMenu_4 = new JMenu("\u5E2E\u52A9");
		mnNewMenu_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5E2E\u52A91.png")));
		mnNewMenu_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				aboutUs(ae);
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5173\u4E8E\u6211\u4EEC.png")));
		menuItem_4.setFont(new Font("微软雅黑", Font.PLAIN, 13));
		mnNewMenu_4.add(menuItem_4);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.text);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		setLimit();
	}
	
	//修改密码功能
    protected void editPassword(ActionEvent ae) {
		// TODO 自动生成的方法存根
    	EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
    	editPasswordFrm.setVisible(true);
    	desktopPane.add(editPasswordFrm);
    	
	}
    
    //退出登陆功能
	protected void exitMainFrm(ActionEvent ae) {
		// TODO 自动生成的方法存根
		Icon icon = new ImageIcon(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png"));
		String info = "您确定要退出吗？\n";
		String[] buttons= {"对！狠心退出","再留下看看8"};
		int ret = JOptionPane.showOptionDialog(this, info, "退出登录", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, icon, buttons,null);
		if(ret == 0) {
			this.dispose();
			new LoginFrm().frmCc.setVisible(true);
			setLocationRelativeTo(null);
		}else {
			return;
		}
	}
	
    //添加教师功能
    protected void addTeacher(ActionEvent ae) {
		// TODO 自动生成的方法存根
		AddTeacherFrm addTeacherFrm = new AddTeacherFrm();
		addTeacherFrm.setVisible(true);
		desktopPane.add(addTeacherFrm);
	}
    
    //管理教师功能
    protected void ManageTeacher(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ManageTeacherFrm teacherMangeFrm = new ManageTeacherFrm();
		teacherMangeFrm.setVisible(true);
		desktopPane.add(teacherMangeFrm);
	}
    
    //添加学生功能
    protected void addStudent(ActionEvent ae) {
		// TODO 自动生成的方法存根
		AddStudentFrm addStudentFrm = new AddStudentFrm();
		addStudentFrm.setVisible(true);
		desktopPane.add(addStudentFrm);
	}
    
    //管理学生功能
    protected void ManageStudent(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ManageStudentFrm studentManageFrm = new ManageStudentFrm();
		studentManageFrm.setVisible(true);
		desktopPane.add(studentManageFrm);
	}
    
    //添加班级功能
    protected void addClass(ActionEvent ae) {
		// TODO 自动生成的方法存根
		AddClassFrm addClassFrm = new AddClassFrm();
		addClassFrm.setVisible(true);
		desktopPane.add(addClassFrm);
	}

    //班级管理功能
    protected void ManageClass(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ManageClassFrm classManageFrm = new ManageClassFrm();
		classManageFrm.setVisible(true);
		desktopPane.add(classManageFrm);
    }
    
    //管理选课功能
	protected void selectCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ManageSelectCourseFrm selectCourseFrm = new ManageSelectCourseFrm();
		selectCourseFrm.setVisible(true);
		desktopPane.add(selectCourseFrm);
	}
	
    //添加课程功能
    protected void addCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
    	AddCourseFrm addCourseFrm = new AddCourseFrm();
    	addCourseFrm.setVisible(true);
    	desktopPane.add(addCourseFrm);
	}
    
    //课程管理功能
	protected void ManageCourse(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ManageCourseFrm courseManageFrm = new ManageCourseFrm();
		courseManageFrm.setVisible(true);
		desktopPane.add(courseManageFrm);
	}
	
	//录入成绩功能
	protected void AddCourse(ActionEvent e) {
		// TODO 自动生成的方法存根
		AddScoreFrm addScoreFrm = new AddScoreFrm();
		addScoreFrm.setVisible(true);
		desktopPane.add(addScoreFrm);
	}
	
	//管理成绩功能
	protected void ManageScore(ActionEvent e) {
		// TODO 自动生成的方法存根
		ManageScoreFrm manageScoreFrm = new ManageScoreFrm();
		manageScoreFrm.setVisible(true);
		desktopPane.add(manageScoreFrm);
	}
	
	//统计成绩功能
	protected void StatisticScore(ActionEvent e) {
		// TODO 自动生成的方法存根
		ScoreStatisticFrm statisticScoreFrm = new ScoreStatisticFrm();
		statisticScoreFrm.setVisible(true);
		desktopPane.add(statisticScoreFrm);
	}
	
	//查看成绩功能
	protected void ScoreView(ActionEvent e) {
		// TODO 自动生成的方法存根
		ScoreViewFrm scoreViewFrm = new ScoreViewFrm();
		scoreViewFrm.setVisible(true);
		desktopPane.add(scoreViewFrm);
	}
	
	//关于我们的弹窗
	protected void aboutUs(ActionEvent ae) {
		// TODO 自动生成的方法存根
		Icon icon = new ImageIcon(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png"));
		String info = "2017030402064刘宇樑\n";
		info += "欢迎大家和我进行学术交流讨论~\n";
		info +="有意的话可以加我微信哟~\n";
		String[] buttons= {"加！马上就加！","没什么兴趣噢!"};
		int ret = JOptionPane.showOptionDialog(this, info, "关于我们", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, icon, buttons,null);
		if(ret == 0) {
//			frmCc.dispose();
//			new VxFrm().frame.setVisible(true);
			VxFrm vxFrm = new VxFrm();
			vxFrm.setVisible(true);
			desktopPane.add(vxFrm);
			setLocationRelativeTo(null);
		}else {
			JOptionPane.showMessageDialog(this, "886！");
		}
	}
	
	//设置权限
	private void setLimit() {
		if("学生".equals(userType.getName())) {
			teacherNewMenu.setEnabled(false);
			classNewMenu.setEnabled(false);
			addStudentMenuItem.setEnabled(false);
			gradeInputMenuItem.setEnabled(false);
			gradeManageMenuItem.setEnabled(false);
			gradeStatisticsMenuItem.setEnabled(false);
			courseNewMenu.setEnabled(false);
			
		}
		if("教师".equals(userType.getName())) {
			studentNewMenu.setEnabled(false);
			classNewMenu.setEnabled(false);
			addTeacherMenuItem.setEnabled(false);
			selectCourseNewMenu.setEnabled(false);
			gradeViewMenuItem.setEnabled(false);
		}
		if("系统管理员".equals(userType.getName())){
			gradeViewMenuItem.setEnabled(false);
		}
	}
}
