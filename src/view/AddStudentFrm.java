package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.ClassDao;
import dao.StudentDao;
import model.CollegeType;
import model.EntryYear;
import model.SexType;
import model.Student;
import model.StudentClass;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class AddStudentFrm extends JInternalFrame{

//	private JFrame frame;
	private JTextField userNumberNextField;
	private JTextField userNameTextField;
	private JComboBox sexTypeComboBox;
	private JComboBox CollegeTypeComboBox;
	private JTextField passWordTextField;
	private JTextField affrimPassWordTextField;
	private JComboBox yearComboBox;
	private JComboBox classTpyeComboBox;
	private JLabel photoLabel;
	private JButton photoButton;
	private String address="";
	private ImageIcon img;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddStudentFrm window = new AddStudentFrm();
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
	public AddStudentFrm() {
		setFrameIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u5B66\u751F\u6DFB\u52A0.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
//		setIconImage(Toolkit.getDefaultToolkit().getImage(AddStudentFrm.class.getResource("/images/\u5B66\u751F\u6DFB\u52A0.png")));
		setTitle("\u5B66\u751F\u6DFB\u52A0");
		setBounds(100, 100, 630, 485);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u5B66\u53F7.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(120, 40, 80, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(120, 160, 80, 23);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B\uFF1A");
		label_2.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u6027\u522B.png")));
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(120, 200, 80, 23);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u4E8C\u7EA7\u5B66\u9662\uFF1A");
		label_3.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_3.setBounds(90, 240, 107, 23);
		getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("\u73ED\u7EA7\uFF1A");
		label_4.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_4.setBounds(120, 280, 80, 23);
		getContentPane().add(label_4);
		
		JLabel lblNewLabel = new JLabel("\u5165\u5B66\u5E74\u4EFD\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u5165\u5B66\u5E74\u4EFD2.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(90, 320, 107, 23);
		getContentPane().add(lblNewLabel);
		
		userNumberNextField = new JTextField();
		userNumberNextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userNumberNextField.setBounds(207, 40, 165, 23);
		getContentPane().add(userNumberNextField);
		userNumberNextField.setColumns(10);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		userNameTextField.setBounds(207, 160, 165, 23);
		getContentPane().add(userNameTextField);
		userNameTextField.setColumns(10);
		
		sexTypeComboBox = new JComboBox();
		//将下拉框的默认值换用为枚举类sexType里的值
		sexTypeComboBox.setModel(new DefaultComboBoxModel(new SexType[] {SexType.MAN,SexType.WOMAN}));
		sexTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		sexTypeComboBox.setBounds(207, 200, 165, 23);
		getContentPane().add(sexTypeComboBox);
		
		CollegeTypeComboBox = new JComboBox();	
		CollegeTypeComboBox.addItemListener(new ItemListener() {
			//下拉框监听事件
			@Override
			public void itemStateChanged(ItemEvent e) {
				//清楚所有选择项
				classTpyeComboBox.removeAllItems();
				ClassDao classDao = new ClassDao();
				CollegeType ct = (CollegeType) CollegeTypeComboBox.getSelectedItem();
				List<StudentClass> serachClassList = classDao.getClassList(new StudentClass(), ct.getName());	
				for(StudentClass sc : serachClassList) {
					classTpyeComboBox.addItem(sc);
				}
				
				classDao.closeDao();
				
			}
		});
		//将下拉框的默认值换用为枚举类CollegeType里的值
		CollegeTypeComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		
		
		
		//为学院下拉框增加监听事件
//		CollegeTypeComboBox.addItemListener(new ItemListener() {
//			
//			
//			//重写ItemListener的itemStateChanged方法
//			@Override
//			public void itemStateChanged(ItemEvent ae) {
//			CollegeType studentCollege = (CollegeType) CollegeTypeComboBox.getSelectedItem();
//			String ct = studentCollege.getName();
//			ClassDao classDao = new ClassDao();
//				classTpyeComboBox.removeAll(); //清楚该下拉框的原始数据
//				classTpyeComboBox.setModel(new DefaultComboBoxModel<>());
//				
//			}
//		});
		
		
		CollegeTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		CollegeTypeComboBox.setBounds(207, 240, 165, 23);
		getContentPane().add(CollegeTypeComboBox);
		
		
		classTpyeComboBox = new JComboBox();
		classTpyeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		classTpyeComboBox.setBounds(207, 280, 165, 23);
		getContentPane().add(classTpyeComboBox);
		setStudentClass();
		
//		String[] arry = new String[201];
//		for (int i = 2000; i < 2201; i++) {
//		arry[i-2000] = i+"年";
//		System.out.println(arry[i-2000]);
//		}
//		JComboBox yearComboBox = new JComboBox(arry);
		
		yearComboBox = new JComboBox();
		yearComboBox.setModel(new DefaultComboBoxModel(new EntryYear[] {
				EntryYear.A1,EntryYear.A2,EntryYear.A3,EntryYear.A4,EntryYear.A5,
				EntryYear.B1,EntryYear.B2,EntryYear.B3,EntryYear.B4,EntryYear.B5,
				EntryYear.B6,EntryYear.B7,EntryYear.B8,EntryYear.B9,EntryYear.B10,
				EntryYear.C1,EntryYear.C2,EntryYear.C3,EntryYear.C4,EntryYear.C5,
				EntryYear.C6,EntryYear.C7,EntryYear.C8,EntryYear.C9,EntryYear.C10,
				}) );
		yearComboBox.setSelectedIndex(24);
		yearComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		yearComboBox.setBounds(207, 320, 165, 23);
		getContentPane().add(yearComboBox);
		
		
		//确定按钮都的实现
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addValue(ae);
			}
		});
		affButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		affButton.setBounds(139, 375, 100, 30);
		getContentPane().add(affButton);
		
		//重置按钮的实现
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		resetButton.setBounds(250, 375, 100, 30);
		getContentPane().add(resetButton);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BBE\u7F6E\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(90, 80, 107, 23);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u786E\u5B9A\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(90, 120, 107, 23);
		getContentPane().add(lblNewLabel_2);
		
		passWordTextField = new JTextField();
		passWordTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		passWordTextField.setBounds(207, 80, 165, 23);
		getContentPane().add(passWordTextField);
		passWordTextField.setColumns(10);
		
		affrimPassWordTextField = new JTextField();
		affrimPassWordTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		affrimPassWordTextField.setBounds(207, 120, 165, 23);
		getContentPane().add(affrimPassWordTextField);
		affrimPassWordTextField.setColumns(10);
		
		photoLabel = new JLabel("");
		photoLabel.setBounds(432, 93, 120, 160);
		getContentPane().add(photoLabel);
		img = new ImageIcon("C:\\Users\\Shinelon\\Desktop\\logo处理后\\defaultPhoto.jpg");
		img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
		photoLabel.setIcon(img);
		
		
		photoButton = new JButton("\u4E0A\u4F20\u7167\u7247");
		photoButton.addActionListener(new ActionListener() {
			//打开文件
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();	//创建选择文件对象
				chooser.setDialogTitle("请选择文件");  //设置标题
				chooser.setMultiSelectionEnabled(true);  //设置只能选择的文件
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); //定义可以选择的文件类型
				chooser.setFileFilter(filter); //设置可选择的文件类型
				chooser.showOpenDialog(null);  //打开选择文件对话框
				img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
				photoLabel.setIcon(img);
				address=chooser.getSelectedFile().getAbsolutePath();
				
			}
		});
		photoButton.setIcon(new ImageIcon(AddStudentFrm.class.getResource("/images/\u4E0A\u4F20\u7167\u7247.png")));
		photoButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		photoButton.setBounds(419, 273, 130, 30);
		getContentPane().add(photoButton);
	}
	
	//确定按钮的方法
	protected void addValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		
		String studentId = userNumberNextField.getText().toString();
		

		String studentPassWord = passWordTextField.getText().toString();
		String studentAffPassWord = affrimPassWordTextField.getText().toString();
		String studentName = userNameTextField.getText().toString();
		SexType studentSexType = (SexType) sexTypeComboBox.getSelectedItem();
		CollegeType studentCollege = (CollegeType) CollegeTypeComboBox.getSelectedItem();
		StudentClass studentClass = (StudentClass) classTpyeComboBox.getSelectedItem();
		EntryYear entryYear = (EntryYear) yearComboBox.getSelectedItem();
	
		//判断输入信息是否为空
		if(StringUtil.isEmpty(studentId)) {
			JOptionPane.showMessageDialog(this, "学生学号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(studentPassWord)) {
			JOptionPane.showMessageDialog(this, "学生密码不能为空！");
			return;
		}
		if(StringUtil.isEmpty(studentAffPassWord)) {
			JOptionPane.showMessageDialog(this, "请确认学生密码！");
			return;
		}
		if(!(studentPassWord).equals(studentAffPassWord)) {
			JOptionPane.showMessageDialog(this, "两次输入密码不一致，请重新输入！");
			return;
		}
		if(StringUtil.isEmpty(studentName)) {
			JOptionPane.showMessageDialog(this, "学生姓名不能为空！");
			return;
		}
		
		//判断输入信息是否不对应
				if(!StringUtil.isNum(studentId)) {
					JOptionPane.showMessageDialog(this, "学生学号只能为阿拉伯数字！");
					return;
				}
				if(!StringUtil.isHanzi(studentName)) {
					JOptionPane.showMessageDialog(this, "学生姓名只能为汉字！");
					return;
				}
				if(address.equals("")) {
					JOptionPane.showMessageDialog(this, "请添加教师头像！");
					return;
				}
		
		//添加信息
		Student sd = new Student();
		sd.setStudentId(studentId);
		sd.setPassWord(studentPassWord);
		sd.setStudentName(studentName);
		sd.setStudentSex(studentSexType.getName());
		sd.setStudentCollege(studentCollege.getName());
		sd.setStudentClass(studentClass.getClassName());
		sd.setEntryYear(entryYear.getName());
		
		StudentDao studentDao = new StudentDao();
		if(studentDao.isAdd(sd)) {
			JOptionPane.showMessageDialog(this, "请勿重复添加该学号的信息！");
			userNumberNextField.setText("");
			return;
		}
		if(studentDao.addStudent(sd,address)) {
			JOptionPane.showMessageDialog(this, "学生信息添加成功！");
			img = new ImageIcon("C:\\Users\\Shinelon\\Desktop\\logo处理后\\defaultPhoto.jpg");
			img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
			photoLabel.setIcon(img);
		}else {
			JOptionPane.showMessageDialog(this, "学生信息添加失败！");
		}
		studentDao.closeDao();
		resetValue(ae);
	}

	
	
	//重置按钮的方法
	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		userNumberNextField.setText("");
		userNameTextField.setText("");
		passWordTextField.setText("");
		affrimPassWordTextField.setText("");
		sexTypeComboBox.setSelectedIndex(0); 
		CollegeTypeComboBox.setSelectedIndex(0);
		yearComboBox.setSelectedIndex(24);
	}
	
	//导入班级列表
	private void setStudentClass() {
		ClassDao classDao = new ClassDao();
		List<StudentClass> classList = classDao.getClassList(new StudentClass());
		for(StudentClass sc : classList) {
			classTpyeComboBox.addItem(sc);
		}
		classDao.closeDao();
	}
}
