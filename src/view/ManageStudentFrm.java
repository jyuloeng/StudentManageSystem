package view;

import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.ClassDao;
import dao.StudentDao;
import dao.TeacherDao;
import model.CollegeType;
import model.EntryYear;
import model.SexType;
import model.Student;
import model.StudentClass;
import model.Student;
import util.StringUtil;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ManageStudentFrm extends JInternalFrame{
	private JTable StudentListTable;
	private JTextField editStudentIdTextField;
	private JTextField editPassWordTextField;
	private JTextField editStudentNameTextField;
	private JComboBox editStudentClassComboBox;
	private JComboBox editStudentSexComboBox ;
	private JComboBox editStudentCoComboBox;
	private JComboBox editEntryYearComboBox;
	private JButton deleteButton;
	private JTextField searchStudentTextField;
	private JComboBox studentCoComboBox;
	private JComboBox studentClassComboBox;
	private JButton searchIdOfNameButton;
	private JButton searchClassButton;
	private JLabel photoLabel;
	private JButton photoButton;
	private ImageIcon icon ;
	private ImageIcon icon1;
	private String address;
	private Image img;
	
//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StudentManageFrm window = new StudentManageFrm();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public ManageStudentFrm() {
		setFrameIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5217\u8868.png")));
		setTitle("\u5B66\u751F\u7BA1\u7406");
		initialize();
	}

	private void setTable(Student st) {
		//如果是学生登陆时
		if("学生".equals(MainFrm.userType.getName())) {
			Student sd = (Student) MainFrm.userObject;
			st.setStudentName(sd.getStudentName());
		}
		DefaultTableModel dft = (DefaultTableModel) StudentListTable.getModel();
		dft.setRowCount(0);
		StudentDao std = new StudentDao();
		List<Student> studentList = std.getStudentList(st);
		for(Student student :studentList) {
			Vector vt = new Vector();
			vt.add(student.getId());
			vt.add(student.getStudentId());
			vt.add(student.getPassWord());
			vt.add(student.getStudentName());
			vt.add(student.getStudentSex());
			vt.add(student.getStudentCollege());
			vt.add(student.getStudentClass());
			vt.add(student.getEntryYear());
			dft.addRow(vt);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 950, 650);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
		
		StudentListTable = new JTable();
		StudentListTable.addMouseListener(new MouseAdapter() {
			//鼠标点击时的效果
			@Override
			public void mouseClicked(MouseEvent ae) {
				selectTableRow(ae);
			}
		});
		StudentListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		StudentListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u5B66\u751FId", "\u5B66\u751F\u5B66\u53F7", "\u5B66\u751F\u5BC6\u7801", "\u5B66\u751F\u59D3\u540D", "\u5B66\u751F\u6027\u522B", "\u4E8C\u7EA7\u5B66\u9662", "\u6240\u5C5E\u73ED\u7EA7", "\u5165\u5B66\u5E74\u4EFD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
//		scrollPane.setColumnHeaderView(StudentListTable);
		setTable(new Student());
	
		JScrollPane scrollPane = new JScrollPane(StudentListTable);
		
		scrollPane.setBounds(70, 118, 650, 300);
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u751F\u5B66\u53F7\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5B66\u53F7.png")));
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(70, 452, 107, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5B66\u751F\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(70, 492, 107, 23);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(70, 532, 107, 23);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u5B66\u751F\u6027\u522B\uFF1A");
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_3.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u6027\u522B.png")));
		lblNewLabel_3.setBounds(324, 452, 107, 23);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u4E8C\u7EA7\u5B66\u9662\uFF1A");
		lblNewLabel_4.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		lblNewLabel_4.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(324, 492, 107, 23);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u6240\u5C5E\u73ED\u7EA7\uFF1A");
		lblNewLabel_5.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_5.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		lblNewLabel_5.setBounds(324, 532, 107, 23);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\u5165\u5B66\u5E74\u4EFD\uFF1A");
		lblNewLabel_6.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5165\u5B66\u5E74\u4EFD2.png")));
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(578, 452, 107, 23);
		getContentPane().add(lblNewLabel_6);
		
		editStudentIdTextField = new JTextField();
		editStudentIdTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editStudentIdTextField.setBounds(186, 452, 120, 23);
		getContentPane().add(editStudentIdTextField);
		editStudentIdTextField.setColumns(10);
		
		editPassWordTextField = new JTextField();
		editPassWordTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editPassWordTextField.setBounds(186, 492, 120, 23);
		getContentPane().add(editPassWordTextField);
		editPassWordTextField.setColumns(10);
		
		editStudentNameTextField = new JTextField();
		editStudentNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editStudentNameTextField.setBounds(186, 532, 120, 23);
		getContentPane().add(editStudentNameTextField);
		editStudentNameTextField.setColumns(10);
		
		editStudentSexComboBox = new JComboBox();
		editStudentSexComboBox.setModel(new DefaultComboBoxModel(new SexType[] {SexType.MAN,SexType.WOMAN}));
		editStudentSexComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editStudentSexComboBox.setBounds(440, 452, 120, 23);
		getContentPane().add(editStudentSexComboBox);
		
		editStudentCoComboBox = new JComboBox();
		editStudentCoComboBox.addItemListener(new ItemListener() {
			//添加监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					editStudentClassComboBox.removeAllItems();
					ClassDao classDao = new ClassDao();
					CollegeType ct = (CollegeType) editStudentCoComboBox.getSelectedItem();
					List<StudentClass> serachClassList = classDao.getClassList(new StudentClass(), ct.getName());	
					for(StudentClass sc : serachClassList) {
						editStudentClassComboBox.addItem(sc);
					}
					classDao.closeDao();
					}
				}
			
		});
		editStudentCoComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		editStudentCoComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editStudentCoComboBox.setBounds(440, 492, 120, 23);
		getContentPane().add(editStudentCoComboBox);
		
		editStudentClassComboBox = new JComboBox();
		editStudentClassComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editStudentClassComboBox.setBounds(440, 532, 120, 23);
		getContentPane().add(editStudentClassComboBox);
		
		
		editEntryYearComboBox = new JComboBox();
		editEntryYearComboBox.setModel(new DefaultComboBoxModel(new EntryYear[] {
				EntryYear.A1,EntryYear.A2,EntryYear.A3,EntryYear.A4,EntryYear.A5,
				EntryYear.B1,EntryYear.B2,EntryYear.B3,EntryYear.B4,EntryYear.B5,
				EntryYear.B6,EntryYear.B7,EntryYear.B8,EntryYear.B9,EntryYear.B10,
				EntryYear.C1,EntryYear.C2,EntryYear.C3,EntryYear.C4,EntryYear.C5,
				EntryYear.C6,EntryYear.C7,EntryYear.C8,EntryYear.C9,EntryYear.C10,
				}) );
		editEntryYearComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editEntryYearComboBox.setBounds(694, 452, 120, 23);
		getContentPane().add(editEntryYearComboBox);
		
		JButton editButton = new JButton("\u786E\u5B9A\u4FEE\u6539");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//更新学生信息
				updateStudent(ae);
			}
		});
		editButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u786E\u8BA4.png")));
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editButton.setBounds(639, 493, 121, 25);
		getContentPane().add(editButton);
		
		deleteButton = new JButton("\u5220\u9664\u5B66\u751F");
		deleteButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5220\u96641.png")));
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteButton.addActionListener(new ActionListener() {
			//删除学生按钮
			public void actionPerformed(ActionEvent ae) {
				deleteStudent(ae);
			}
		});
		deleteButton.setBounds(639, 531, 121, 25);
		getContentPane().add(deleteButton);
		
		JLabel lblNewLabel_7 = new JLabel("\u6309\u5B66\u751F\u59D3\u540D\uFF1A");
		lblNewLabel_7.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		lblNewLabel_7.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(281, 30, 177, 23);
		getContentPane().add(lblNewLabel_7);
		
		searchStudentTextField = new JTextField();
		searchStudentTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchStudentTextField.setBounds(405, 30, 120, 23);
		getContentPane().add(searchStudentTextField);
		searchStudentTextField.setColumns(10);
		
		searchIdOfNameButton = new JButton("\u67E5\u8BE2");
		searchIdOfNameButton.addActionListener(new ActionListener() {
			//查询学生
			public void actionPerformed(ActionEvent e) {
				Student sd = new Student();
				sd.setStudentName(searchStudentTextField.getText().toString());
				setTable(sd);
			}
		});
		searchIdOfNameButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		searchIdOfNameButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchIdOfNameButton.setBounds(578, 29, 89, 25);
		getContentPane().add(searchIdOfNameButton);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(182, 63, 54, 25);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u6309\u73ED\u7EA7\uFF1A");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(210, 63, 177, 23);
		getContentPane().add(label_1);
		
		studentCoComboBox = new JComboBox();
		studentCoComboBox.addItemListener(new ItemListener() {
			//下拉框监听事件
			@Override
			public void itemStateChanged(ItemEvent e) {
				studentClassComboBox.removeAllItems();
				ClassDao classDao = new ClassDao();
				CollegeType ct = (CollegeType) studentCoComboBox.getSelectedItem();
				List<StudentClass> serachClassList = classDao.getClassList(new StudentClass(), ct.getName());	
				for(StudentClass sc : serachClassList) {
					studentClassComboBox.addItem(sc);
				}
				
				classDao.closeDao();
				
			}
		});
		studentCoComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		studentCoComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentCoComboBox.setBounds(276, 63, 120, 23);
		getContentPane().add(studentCoComboBox);
		
		
		studentClassComboBox = new JComboBox();
		studentClassComboBox.addItemListener(new ItemListener() {
			//设置监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					StudentClass studentClass = (StudentClass) studentClassComboBox.getSelectedItem();
					Student sd = new Student();
					sd.setStudentClass(studentClass.getClassName());
					setTable(sd);
				}
			}
		});
		studentClassComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		studentClassComboBox.setBounds(405, 63, 120, 23);
		getContentPane().add(studentClassComboBox);
		setStudentClass();
		
		searchClassButton = new JButton("\u67E5\u770B\u5168\u90E8");
		searchClassButton.addActionListener(new ActionListener() {
			//查看全部学生
			public void actionPerformed(ActionEvent e) {
				setTable(new Student());
			}
		});
		searchClassButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		searchClassButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchClassButton.setBounds(561, 63, 121, 25);
		getContentPane().add(searchClassButton);
		
		photoLabel = new JLabel("");
		photoLabel.setBounds(764, 160, 120, 160);
		getContentPane().add(photoLabel);
		
		photoButton = new JButton("\u4FEE\u6539\u7167\u7247");
		photoButton.addActionListener(new ActionListener() {
			//修改照片
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();	//创建选择文件对象
				chooser.setDialogTitle("请选择文件");  //设置标题
				chooser.setMultiSelectionEnabled(true);  //设置只能选择的文件
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); //定义可以选择的文件类型
				chooser.setFileFilter(filter); //设置可选择的文件类型
				chooser.showOpenDialog(null);  //打开选择文件对话框
				icon1 = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				icon1.setImage(icon1.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
				photoLabel.setIcon(icon1);
				address=chooser.getSelectedFile().getAbsolutePath();
			}
		});
		photoButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		photoButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		photoButton.setBounds(751, 340, 130, 30);
		getContentPane().add(photoButton);
		setClosable(true);
		setIconifiable(true);
		setLimit();
		
	}
	
	


	//选中行时的效果
	protected void selectTableRow(MouseEvent ae) {
		// TODO 自动生成的方法存根
		address="";
		DefaultTableModel dft = (DefaultTableModel) StudentListTable.getModel();
		editStudentIdTextField.setText(dft.getValueAt(StudentListTable.getSelectedRow(), 1).toString());
		editPassWordTextField.setText(dft.getValueAt(StudentListTable.getSelectedRow(), 2).toString());
		editStudentNameTextField.setText(dft.getValueAt(StudentListTable.getSelectedRow(), 3).toString());
		//按名称获得单选框里的数据
			String studentSex = dft.getValueAt(StudentListTable.getSelectedRow(), 4).toString();
			for(int i=0;i<editStudentSexComboBox.getItemCount();i++) {
				SexType st = (SexType) editStudentSexComboBox.getItemAt(i);
				if(studentSex.equals(st.getName())) {
					editStudentSexComboBox.setSelectedIndex(i);
				}
			}
			String studentCollegeType = dft.getValueAt(StudentListTable.getSelectedRow(), 5).toString();
			for(int i=0;i<editStudentCoComboBox.getItemCount();i++) {
				CollegeType ct = (CollegeType) editStudentCoComboBox.getItemAt(i);
				if(studentCollegeType.equals(ct.getName())) {
					editStudentCoComboBox.setSelectedIndex(i);
				}
			}
			String studentClass = dft.getValueAt(StudentListTable.getSelectedRow(), 6).toString();
			for(int i=0;i<editStudentClassComboBox.getItemCount();i++) {
				StudentClass ct = (StudentClass) editStudentClassComboBox.getItemAt(i);
				if(studentClass.equals(ct.getClassName())) {
					editStudentClassComboBox.setSelectedIndex(i);
				}
			}
			String entryYear = dft.getValueAt(StudentListTable.getSelectedRow(), 7).toString();
			for(int i=0;i<editEntryYearComboBox.getItemCount();i++) {
				EntryYear ey = (EntryYear) editEntryYearComboBox.getItemAt(i);
				if(entryYear.equals(ey.getName())) {
					editEntryYearComboBox.setSelectedIndex(i);
				}
			}
			StudentDao studentDao = new StudentDao();
			Student student = new Student();
			Student sd3 = new Student();
			student.setStudentName(dft.getValueAt(StudentListTable.getSelectedRow(), 3).toString());
			List<Student> studentList = studentDao.getStudentList(new Student());
			List<Student> studentList2 = studentDao.getStudentList(student);
			for(Student sd:studentList) {
				for(Student sd2:studentList2) {
					if(sd.getStudentName().equals(sd2.getStudentName())){
						sd3=sd;
					}
				}
			}
			try {
				InputStream is =sd3.getBlob().getBinaryStream();
				img=ImageIO.read(is);
				icon = new ImageIcon(img);
				photoLabel.setIcon(icon);
				icon.setImage(icon.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}

	private void setStudentClass() {
		ClassDao classDao = new ClassDao();
		List<StudentClass> classList = classDao.getClassList(new StudentClass());
		for(StudentClass sc : classList) {
			editStudentClassComboBox.addItem(sc);
		}
		
		classDao.closeDao();
	}
	
	
	
	//删除学生信息
	protected void deleteStudent(ActionEvent ae) {
		// TODO 自动生成的方法存根
		
		StudentDao StudentDao = new StudentDao();
		int row = StudentListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的学生信息！");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) StudentListTable.getModel();
		String id = dft.getValueAt(StudentListTable.getSelectedRow(), 0).toString();
		if(JOptionPane.showConfirmDialog(this, "您确定要删除该学生信息吗？", "温馨提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)!=JOptionPane.OK_OPTION) {
			return;
		}
		if(StudentDao.delete(id)) {
			JOptionPane.showMessageDialog(this, "删除学生成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除学生失败！");
		}
		setTable(new Student());
		StudentDao.closeDao();
		}
	
	//更新学生信息
	protected void updateStudent(ActionEvent ae) {
		// TODO 自动生成的方法存根	
		StudentDao studentDao = new StudentDao();
		int row = StudentListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要修改的教师信息！");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) StudentListTable.getModel();
		//获取修改前的信息
		String studentId = dft.getValueAt(StudentListTable.getSelectedRow(), 1).toString();
		String studentPW = dft.getValueAt(StudentListTable.getSelectedRow(), 2).toString();
		String studentName = dft.getValueAt(StudentListTable.getSelectedRow(), 3).toString();
		String studentSex = dft.getValueAt(StudentListTable.getSelectedRow(), 4).toString();
		String studentCollege = dft.getValueAt(StudentListTable.getSelectedRow(), 5).toString();
		String studentClass = dft.getValueAt(StudentListTable.getSelectedRow(), 6).toString();
		String entryYear = dft.getValueAt(StudentListTable.getSelectedRow(), 7).toString();
		//获取修改后的信息
		String editStudentId = editStudentIdTextField.getText().toString();
		String editStudentPW = editPassWordTextField.getText().toString();
		String editStudentName = editStudentNameTextField.getText().toString();
		SexType editStudentSex = (SexType) editStudentSexComboBox.getSelectedItem();
		CollegeType editStudentCollege = (CollegeType) editStudentCoComboBox.getSelectedItem();
		StudentClass editStudentClass = (StudentClass) editStudentClassComboBox.getSelectedItem();
		EntryYear editEntryYear = (EntryYear) editEntryYearComboBox.getSelectedItem();
		//判断输入框是否为空值
		if(StringUtil.isEmpty(editStudentId)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的学生学号!");
			return;
		}
		if(StringUtil.isEmpty(editStudentPW)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的学生密码!");
			return;
		}
		if(StringUtil.isEmpty(editStudentName)) {
			JOptionPane.showMessageDialog(this, "请填写要修改的学生姓名!");
			return;
		}
		//判断输入信息是否不对应
		if(!StringUtil.isNum(editStudentId)) {
			JOptionPane.showMessageDialog(this, "学生学号只能为阿拉伯数字！");
			return;
		}
		if(!StringUtil.isHanzi(editStudentName)) {
			JOptionPane.showMessageDialog(this, "学生姓名只能为汉字！");
			return;
		}

		//判断有无修改
		if(studentId.equals(editStudentId) && studentPW.equals(editStudentPW) &&
				studentName.equals(editStudentName) && studentSex.equals(editStudentSex.getName()) &&
				studentCollege.equals(editStudentCollege.getName()) && entryYear.equals(editEntryYear.getName()) && studentClass.equals(editStudentClass.getClassName()) && StringUtil.isEmpty(address)) {
			JOptionPane.showMessageDialog(this, "您还没有做任何修改!");
			return;
		}
		

		//获取学生数据表的id
		int id = Integer.parseInt( dft.getValueAt(StudentListTable.getSelectedRow(), 0).toString());
		//更新学生信息
		Student sd = new Student();
		sd.setId(id);
		sd.setStudentId(editStudentId);
		sd.setAffpassWord(editStudentPW);
		sd.setStudentName(editStudentName);
		sd.setStudentSex(editStudentSex.getName());
		sd.setStudentCollege(editStudentCollege.getName());
		sd.setStudentClass(editStudentClass.getClassName());
		sd.setEntryYear(editEntryYear.getName());
		if(studentDao.isAdd(sd)) {
			JOptionPane.showMessageDialog(this, "请勿重复添加该学号的信息！");
			editStudentIdTextField.setText("");
			return;
		}
		if(studentDao.update(sd,address)) {
			JOptionPane.showMessageDialog(this, "更新学生信息成功！");
		}else {
			JOptionPane.showMessageDialog(this, "更新学生信息失败！");
		}
		setTable(new Student());
		studentDao.closeDao();
	}
	
	//设置学生登陆时的权限
	private void setLimit() {
		if("学生".equals(MainFrm.userType.getName())) {
			Student sd = (Student) MainFrm.userObject;
			deleteButton.setEnabled(false);
			searchStudentTextField.setText(sd.getStudentName());
			studentCoComboBox.setSelectedItem(sd);
			studentCoComboBox.setEnabled(false);
			studentClassComboBox.setEnabled(false);
			searchStudentTextField.setEnabled(false);
			searchIdOfNameButton.setEnabled(false);
			searchClassButton.setEnabled(false);
		}
	}
}
