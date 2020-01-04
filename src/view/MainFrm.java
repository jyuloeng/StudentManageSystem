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
	public static UserType userType; //�û�����
	public static Object userObject; //�û�������
	
	private JMenu teacherNewMenu;	//��ʦ��Ϣ��ť
	private JMenu studentNewMenu;	//ѧ����Ϣ��ť
	private JMenu classNewMenu;		//�༶��Ϣ��ť
	private JMenu selectCourseNewMenu;	//ѡ����Ϣ��ť
	private JMenu courseNewMenu;	//�γ���Ϣ��ť
	private JMenuItem addStudentMenuItem;	//���ѧ����ť
	private JMenuItem addTeacherMenuItem;	//��ӽ�ʦ��ť
	private JMenuItem gradeInputMenuItem;	//�ɼ��Ǽǰ�ť
	private JMenuItem gradeViewMenuItem;	//�ɼ��鿴��ť
	private JMenuItem gradeManageMenuItem;	//�ɼ�����ť
	private JMenuItem gradeStatisticsMenuItem;	//�ɼ�ͳ�ư�ť
	
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
		setFont(new Font("΢���ź�", Font.PLAIN, 12));
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
		mnNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);
		
		JMenuItem menuItem = new JMenuItem("\u4FEE\u6539\u5BC6\u7801");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				editPassword(ae);
			}
		});
		menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u7801.png")));
		menuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		mnNewMenu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("\u9000\u51FA\u767B\u9646");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				exitMainFrm(ae);
			}
		});
		menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u9000\u51FA\u767B\u9646.png")));
		menuItem_1.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		mnNewMenu.add(menuItem_1);
		
		teacherNewMenu = new JMenu("\u6559\u5E08\u4FE1\u606F");
		teacherNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6559\u5E08\u7BA1\u7406.png")));
		teacherNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(teacherNewMenu);
		
		addTeacherMenuItem = new JMenuItem("\u6559\u5E08\u6DFB\u52A0");
		addTeacherMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addTeacher(ae);
			}
		});
		addTeacherMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		addTeacherMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		teacherNewMenu.add(addTeacherMenuItem);
		
		//�����ʦ
		JMenuItem menuItem_5 = new JMenuItem("\u6559\u5E08\u7BA1\u7406");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ManageTeacher(ae);
			}
		});
		menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		menuItem_5.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		teacherNewMenu.add(menuItem_5);
		
		studentNewMenu = new JMenu("\u5B66\u751F\u4FE1\u606F");
		studentNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
		studentNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(studentNewMenu);
		
		addStudentMenuItem = new JMenuItem("\u5B66\u751F\u6DFB\u52A0");
		addStudentMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addStudent(ae);
			}
		});
		addStudentMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5B66\u751F\u6DFB\u52A0.png")));
		addStudentMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		studentNewMenu.add(addStudentMenuItem);
		
		//����ѧ��
		JMenuItem menuItem_6 = new JMenuItem("\u5B66\u751F\u7BA1\u7406");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ManageStudent(ae);
			}
		});
		menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		menuItem_6.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		studentNewMenu.add(menuItem_6);
		
		classNewMenu = new JMenu("\u73ED\u7EA7\u4FE1\u606F");
		classNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u73ED\u7EA7\u7BA1\u74061.png")));
		classNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(classNewMenu);
		
		//��Ӱ༶
		JMenuItem menuItem_7 = new JMenuItem("\u73ED\u7EA7\u6DFB\u52A0");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addClass(ae);
			}
		});
		menuItem_7.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u73ED\u7EA7\u6DFB\u52A0.png")));
		menuItem_7.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		classNewMenu.add(menuItem_7);
		
		
		//����༶
		JMenuItem menuItem_8 = new JMenuItem("\u73ED\u7EA7\u7BA1\u7406");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				ManageClass(ae);
			}
		});
		menuItem_8.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		menuItem_8.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		classNewMenu.add(menuItem_8);
		
		selectCourseNewMenu = new JMenu("\u9009\u8BFE\u4FE1\u606F");
		selectCourseNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u9009\u8BFE2.png")));
		selectCourseNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(selectCourseNewMenu);
		
		JMenuItem courseManageMenuItem = new JMenuItem("\u9009\u8BFE\u7BA1\u7406");
		courseManageMenuItem.addActionListener(new ActionListener() {
			//����ѡ��
			public void actionPerformed(ActionEvent ae) {
				selectCourse(ae);
			}
		});
		courseManageMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		courseManageMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		selectCourseNewMenu.add(courseManageMenuItem);
		
		courseNewMenu = new JMenu("\u8BFE\u7A0B\u4FE1\u606F");
		courseNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B1.png")));
		courseNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(courseNewMenu);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u8BFE\u7A0B\u6DFB\u52A0");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			//���ѡ��
			public void actionPerformed(ActionEvent ae) {
				addCourse(ae);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u8BFE\u7A0B\u6DFB\u52A0.png")));
		mntmNewMenuItem_1.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		courseNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("\u8BFE\u7A0B\u7BA1\u7406");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			//�γ̹����ʵ��
			public void actionPerformed(ActionEvent ae) {
				ManageCourse(ae);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5217\u8868.png")));
		mntmNewMenuItem_2.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		courseNewMenu.add(mntmNewMenuItem_2);
		
		JMenu gradeInfoNewMenu = new JMenu("\u6210\u7EE9\u4FE1\u606F");
		gradeInfoNewMenu.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u6210\u7EE9.png")));
		gradeInfoNewMenu.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(gradeInfoNewMenu);
		
		gradeInputMenuItem = new JMenuItem("\u5F55\u5165\u6210\u7EE9");
		gradeInputMenuItem.addActionListener(new ActionListener() {
			//¼��ɼ�
			public void actionPerformed(ActionEvent e) {
				AddCourse(e);
			}
		});
		gradeInputMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5F55\u5165\u6210\u7EE9.png")));
		gradeInputMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeInputMenuItem);
		
		gradeViewMenuItem = new JMenuItem("\u6210\u7EE9\u67E5\u770B");
		gradeViewMenuItem.addActionListener(new ActionListener() {
			//�ɼ��鿴
			public void actionPerformed(ActionEvent e) {
				ScoreView(e);
			}
		});
		gradeViewMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u67E5\u770B\u6210\u7EE93.png")));
		gradeViewMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeViewMenuItem);
		
		gradeManageMenuItem = new JMenuItem("\u6210\u7EE9\u7BA1\u7406");
		gradeManageMenuItem.addActionListener(new ActionListener() {
			//����ɼ�
			public void actionPerformed(ActionEvent e) {
				ManageScore(e);
			}
		});
		gradeManageMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7BA1\u7406\u56FE\u6807.png")));
		gradeManageMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeManageMenuItem);
		
		gradeStatisticsMenuItem = new JMenuItem("\u6210\u7EE9\u7EDF\u8BA1");
		gradeStatisticsMenuItem.addActionListener(new ActionListener() {
			//ͳ�Ƴɼ�
			public void actionPerformed(ActionEvent e) {
				StatisticScore(e);
			}
		});
		gradeStatisticsMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u7EDF\u8BA1.png")));
		gradeStatisticsMenuItem.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		gradeInfoNewMenu.add(gradeStatisticsMenuItem);
		
		JMenu mnNewMenu_4 = new JMenu("\u5E2E\u52A9");
		mnNewMenu_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5E2E\u52A91.png")));
		mnNewMenu_4.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem menuItem_4 = new JMenuItem("\u5173\u4E8E\u6211\u4EEC");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				aboutUs(ae);
			}
		});
		menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("/images/\u5173\u4E8E\u6211\u4EEC.png")));
		menuItem_4.setFont(new Font("΢���ź�", Font.PLAIN, 13));
		mnNewMenu_4.add(menuItem_4);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(SystemColor.text);
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		setLimit();
	}
	
	//�޸����빦��
    protected void editPassword(ActionEvent ae) {
		// TODO �Զ����ɵķ������
    	EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
    	editPasswordFrm.setVisible(true);
    	desktopPane.add(editPasswordFrm);
    	
	}
    
    //�˳���½����
	protected void exitMainFrm(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		Icon icon = new ImageIcon(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png"));
		String info = "��ȷ��Ҫ�˳���\n";
		String[] buttons= {"�ԣ������˳�","�����¿���8"};
		int ret = JOptionPane.showOptionDialog(this, info, "�˳���¼", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, icon, buttons,null);
		if(ret == 0) {
			this.dispose();
			new LoginFrm().frmCc.setVisible(true);
			setLocationRelativeTo(null);
		}else {
			return;
		}
	}
	
    //��ӽ�ʦ����
    protected void addTeacher(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		AddTeacherFrm addTeacherFrm = new AddTeacherFrm();
		addTeacherFrm.setVisible(true);
		desktopPane.add(addTeacherFrm);
	}
    
    //�����ʦ����
    protected void ManageTeacher(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		ManageTeacherFrm teacherMangeFrm = new ManageTeacherFrm();
		teacherMangeFrm.setVisible(true);
		desktopPane.add(teacherMangeFrm);
	}
    
    //���ѧ������
    protected void addStudent(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		AddStudentFrm addStudentFrm = new AddStudentFrm();
		addStudentFrm.setVisible(true);
		desktopPane.add(addStudentFrm);
	}
    
    //����ѧ������
    protected void ManageStudent(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		ManageStudentFrm studentManageFrm = new ManageStudentFrm();
		studentManageFrm.setVisible(true);
		desktopPane.add(studentManageFrm);
	}
    
    //��Ӱ༶����
    protected void addClass(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		AddClassFrm addClassFrm = new AddClassFrm();
		addClassFrm.setVisible(true);
		desktopPane.add(addClassFrm);
	}

    //�༶������
    protected void ManageClass(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		ManageClassFrm classManageFrm = new ManageClassFrm();
		classManageFrm.setVisible(true);
		desktopPane.add(classManageFrm);
    }
    
    //����ѡ�ι���
	protected void selectCourse(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		ManageSelectCourseFrm selectCourseFrm = new ManageSelectCourseFrm();
		selectCourseFrm.setVisible(true);
		desktopPane.add(selectCourseFrm);
	}
	
    //��ӿγ̹���
    protected void addCourse(ActionEvent ae) {
		// TODO �Զ����ɵķ������
    	AddCourseFrm addCourseFrm = new AddCourseFrm();
    	addCourseFrm.setVisible(true);
    	desktopPane.add(addCourseFrm);
	}
    
    //�γ̹�����
	protected void ManageCourse(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		ManageCourseFrm courseManageFrm = new ManageCourseFrm();
		courseManageFrm.setVisible(true);
		desktopPane.add(courseManageFrm);
	}
	
	//¼��ɼ�����
	protected void AddCourse(ActionEvent e) {
		// TODO �Զ����ɵķ������
		AddScoreFrm addScoreFrm = new AddScoreFrm();
		addScoreFrm.setVisible(true);
		desktopPane.add(addScoreFrm);
	}
	
	//����ɼ�����
	protected void ManageScore(ActionEvent e) {
		// TODO �Զ����ɵķ������
		ManageScoreFrm manageScoreFrm = new ManageScoreFrm();
		manageScoreFrm.setVisible(true);
		desktopPane.add(manageScoreFrm);
	}
	
	//ͳ�Ƴɼ�����
	protected void StatisticScore(ActionEvent e) {
		// TODO �Զ����ɵķ������
		ScoreStatisticFrm statisticScoreFrm = new ScoreStatisticFrm();
		statisticScoreFrm.setVisible(true);
		desktopPane.add(statisticScoreFrm);
	}
	
	//�鿴�ɼ�����
	protected void ScoreView(ActionEvent e) {
		// TODO �Զ����ɵķ������
		ScoreViewFrm scoreViewFrm = new ScoreViewFrm();
		scoreViewFrm.setVisible(true);
		desktopPane.add(scoreViewFrm);
	}
	
	//�������ǵĵ���
	protected void aboutUs(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		Icon icon = new ImageIcon(LoginFrm.class.getResource("/images/\u7CFB\u7EDFlogo1.png"));
		String info = "2017030402064�����\n";
		info += "��ӭ��Һ��ҽ���ѧ����������~\n";
		info +="����Ļ����Լ���΢��Ӵ~\n";
		String[] buttons= {"�ӣ����Ͼͼӣ�","ûʲô��Ȥ��!"};
		int ret = JOptionPane.showOptionDialog(this, info, "��������", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION, icon, buttons,null);
		if(ret == 0) {
//			frmCc.dispose();
//			new VxFrm().frame.setVisible(true);
			VxFrm vxFrm = new VxFrm();
			vxFrm.setVisible(true);
			desktopPane.add(vxFrm);
			setLocationRelativeTo(null);
		}else {
			JOptionPane.showMessageDialog(this, "886��");
		}
	}
	
	//����Ȩ��
	private void setLimit() {
		if("ѧ��".equals(userType.getName())) {
			teacherNewMenu.setEnabled(false);
			classNewMenu.setEnabled(false);
			addStudentMenuItem.setEnabled(false);
			gradeInputMenuItem.setEnabled(false);
			gradeManageMenuItem.setEnabled(false);
			gradeStatisticsMenuItem.setEnabled(false);
			courseNewMenu.setEnabled(false);
			
		}
		if("��ʦ".equals(userType.getName())) {
			studentNewMenu.setEnabled(false);
			classNewMenu.setEnabled(false);
			addTeacherMenuItem.setEnabled(false);
			selectCourseNewMenu.setEnabled(false);
			gradeViewMenuItem.setEnabled(false);
		}
		if("ϵͳ����Ա".equals(userType.getName())){
			gradeViewMenuItem.setEnabled(false);
		}
	}
}
