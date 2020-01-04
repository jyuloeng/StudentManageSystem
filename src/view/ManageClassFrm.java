package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import dao.ClassDao;
import model.CollegeType;
import model.StudentClass;
import util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ManageClassFrm extends JInternalFrame{

//	private JFrame frame;
	private JTextField searchClassNameTextField;
	private JTable ClassListTable;
	private JLabel label;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JComboBox editCollegeComboBox;
	private JTextField editClassNumberTextField;
	private JTextField editClassNameTextField;
	private JButton editButton;
	private JButton deleteButton;
	private JComboBox searchCollegeComboBox;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ClassManageFrm window = new ClassManageFrm();
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
	public ManageClassFrm() {
		setFrameIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u5217\u8868.png")));
		setTitle("\u73ED\u7EA7\u7BA1\u7406");
		initialize();
	}

	//班级列表显示
	private void setTable(StudentClass studentClass) {
		DefaultTableModel dft = (DefaultTableModel) ClassListTable.getModel();
		dft.setRowCount(0);
		ClassDao classDao = new ClassDao();
		List<StudentClass> classList = classDao.getClassList(studentClass);
		for(StudentClass sc: classList) {
			Vector vt = new Vector();
			vt.add(sc.getId());
			vt.add(sc.getCollegeType());
			vt.add(sc.getClassNumber());
			vt.add(sc.getClassName());
			dft.addRow(vt);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 600, 536);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//可关闭缩小
		setClosable(true);
		
	   ClassListTable = new JTable();
	   ClassListTable.addMouseListener(new MouseAdapter() {
		   //添加鼠标点击时发生的事件
	   	@Override
	   	public void mouseClicked(MouseEvent ae) {
	   		selectTableRow(ae);
	   	}
	   });
	   ClassListTable.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		ClassListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u73ED\u7EA7id", "\u4E8C\u7EA7\u5B66\u9662", "\u73ED\u7EA7\u7F16\u53F7", "\u73ED\u7EA7\u540D\u79F0"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		setTable(new StudentClass());
		
		JScrollPane scrollPane = new JScrollPane(ClassListTable);
		scrollPane.setBounds(80, 112, 425, 250);
		getContentPane().add(scrollPane);
		
		
//		scrollPane.setColumnHeaderView(table);
		
		
		label = new JLabel("\u73ED\u7EA7\u540D\u79F0\uFF1A");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u73ED\u7EA7\u6DFB\u52A0.png")));
		label.setBounds(154, 65, 107, 25);
		getContentPane().add(label);
		
		searchClassNameTextField = new JTextField();
		searchClassNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchClassNameTextField.setBounds(254, 66, 120, 23);
		getContentPane().add(searchClassNameTextField);
		searchClassNameTextField.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			//查询按钮
			public void actionPerformed(ActionEvent ae) {
				StudentClass sc = new StudentClass();
				sc.setClassName(searchClassNameTextField.getText().toString());
				setTable(sc);
			}
		});
		searchButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		searchButton.setBounds(384, 48, 89, 25);
		getContentPane().add(searchButton);
		
		lblNewLabel = new JLabel("\u6240\u5C5E\u5B66\u9662\uFF1A");
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		lblNewLabel.setBounds(40, 402, 107, 23);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("\u73ED\u7EA7\u7F16\u53F7\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u5B66\u53F7.png")));
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(40, 442, 107, 23);
		getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("\u73ED\u7EA7\u540D\u79F0:");
		lblNewLabel_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_2.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		lblNewLabel_2.setBounds(302, 402, 107, 23);
		getContentPane().add(lblNewLabel_2);
		
		editCollegeComboBox = new JComboBox();
		editCollegeComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		editCollegeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editCollegeComboBox.setBounds(156, 402, 120, 23);
		getContentPane().add(editCollegeComboBox);
		
		editClassNumberTextField = new JTextField();
		editClassNumberTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editClassNumberTextField.setBounds(156, 442, 120, 23);
		getContentPane().add(editClassNumberTextField);
		editClassNumberTextField.setColumns(10);
		
		editClassNameTextField = new JTextField();
		editClassNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editClassNameTextField.setBounds(418, 402, 120, 23);
		getContentPane().add(editClassNameTextField);
		editClassNameTextField.setColumns(10);
		
		editButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		editButton.addActionListener(new ActionListener() {
			//确认修改按钮的实现
			public void actionPerformed(ActionEvent ae) {
				editClass(ae);
			}
		});
		editButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u786E\u8BA4.png")));
		editButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		editButton.setBounds(295, 441, 121, 25);
		getContentPane().add(editButton);
		
		deleteButton = new JButton("\u5220\u9664\u73ED\u7EA7");
		deleteButton.addActionListener(new ActionListener() {
			//删除按钮的实现
			public void actionPerformed(ActionEvent ae) {
				deleteClass(ae);
			}
		});
		deleteButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		deleteButton.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u5220\u96641.png")));
		deleteButton.setBounds(425, 441, 121, 25);
		getContentPane().add(deleteButton);
		
		JLabel lblNewLabel_3 = new JLabel("\u6309\u6240\u5C5E\u5B66\u9662\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(ManageClassFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		lblNewLabel_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(134, 32, 125, 23);
		getContentPane().add(lblNewLabel_3);
		
		searchCollegeComboBox = new JComboBox();
		searchCollegeComboBox.addItemListener(new ItemListener() {
			//设置监听事件
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					CollegeType collegeType = (CollegeType) searchCollegeComboBox.getSelectedItem();
					StudentClass sc = new StudentClass();
					sc.setCollegeType(collegeType.getName());
					setTable(sc);
				}
			}
		});
		searchCollegeComboBox .setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		searchCollegeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		searchCollegeComboBox.setBounds(254, 33, 120, 23);
		getContentPane().add(searchCollegeComboBox);
		
		
	}
	
	//删除班级信息
	protected void deleteClass(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ClassDao classDao = new ClassDao();
		int row = ClassListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要删除的班级信息！");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) ClassListTable.getModel();
		
		String id = dft.getValueAt(ClassListTable.getSelectedRow(), 0).toString();
		if(JOptionPane.showConfirmDialog(this, "您确定要删除该班级信息吗？", "温馨提示",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)!=JOptionPane.OK_OPTION) {
			return;
		}
		if(classDao.delete(id)) {
			JOptionPane.showMessageDialog(this, "删除班级成功！");
		}else {
			JOptionPane.showMessageDialog(this, "删除班级失败！");
		}
		setTable(new StudentClass());
		classDao.closeDao();
	}

	//确认修改班级信息
	protected void editClass(ActionEvent ae) {
		// TODO 自动生成的方法存根
		ClassDao classDao = new ClassDao();
		int row = ClassListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "请选择要修改的班级信息！");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) ClassListTable.getModel();
		
		String classNumber = dft.getValueAt(ClassListTable.getSelectedRow(), 2).toString();
		String className = dft.getValueAt(ClassListTable.getSelectedRow(), 3).toString();
		String classCollegeType = dft.getValueAt(ClassListTable.getSelectedRow(), 1).toString();
		String editClassNumber = editClassNumberTextField.getText().toString();
		String editClassName = editClassNameTextField.getText().toString();
		CollegeType editclassCollegeType = (CollegeType) editCollegeComboBox.getSelectedItem();
		if(StringUtil.isEmpty(editClassNumber)){
			JOptionPane.showMessageDialog(this, "请填写要修改的班级编号!");
			return;
		}
		if(StringUtil.isEmpty(editClassName)){
			JOptionPane.showMessageDialog(this, "请填写要修改的名称!");
			return;
		}
		if(className.equals(editClassName) && classNumber.equals(editClassNumber ) && classCollegeType.equals(editclassCollegeType.getName())){
			JOptionPane.showMessageDialog(this, "您还没有做任何修改!");
			return;
		}
		
		int id = Integer.parseInt( dft.getValueAt(ClassListTable.getSelectedRow(), 0).toString());
		StudentClass sc =new StudentClass();
		sc.setId(id);
		sc.setClassName(editClassName);
		sc.setClassNumber(editClassNumber);
		sc.setCollegeType(editclassCollegeType.getName());
		if(classDao.update(sc)) {
			JOptionPane.showMessageDialog(this, "更新班级信息成功！");
		}else {
			JOptionPane.showMessageDialog(this, "更新班级信息失败！");
		}
		setTable(new StudentClass());
		classDao.closeDao();
	}

	//选中行时的效果
	protected void selectTableRow(MouseEvent ae) {
		// TODO 自动生成的方法存根
		DefaultTableModel dft = (DefaultTableModel) ClassListTable.getModel();
		editClassNumberTextField.setText(dft.getValueAt(ClassListTable.getSelectedRow(), 2).toString());
		editClassNameTextField.setText(dft.getValueAt(ClassListTable.getSelectedRow(), 3).toString());
		//按名称获得单选框里的数据
		String editClassCollege = dft.getValueAt(ClassListTable.getSelectedRow(), 1).toString();
		for(int i=0;i<editCollegeComboBox.getItemCount();i++) {
			CollegeType ct = (CollegeType) editCollegeComboBox.getItemAt(i);
			if(editClassCollege.equals(ct.getName())){
				editCollegeComboBox.setSelectedIndex(i);
			}
		}
	}
}
