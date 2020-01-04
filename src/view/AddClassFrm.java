package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import dao.ClassDao;
import model.CollegeType;
import model.StudentClass;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddClassFrm extends JInternalFrame{

//	private JFrame frame;
	private JTextField classNumberTextField;
	private JTextField classNameTextField;
	private JComboBox CollegeTypeComboBox;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddClassFrm window = new AddClassFrm();
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
	public AddClassFrm() {
		setFrameIcon(new ImageIcon(AddClassFrm.class.getResource("/images/\u73ED\u7EA7\u6DFB\u52A0.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
//		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddClassFrm.class.getResource("/images/\u73ED\u7EA7\u6DFB\u52A0.png")));
		setTitle("\u73ED\u7EA7\u6DFB\u52A0");
		setBounds(100, 100, 500, 285);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//可关闭缩小
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("\u73ED\u7EA7\u7F16\u53F7\uFF1A");
		label.setIcon(new ImageIcon(AddClassFrm.class.getResource("/images/\u5B66\u53F7.png")));
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(90, 40, 107, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u73ED\u7EA7\u540D\uFF1A");
		label_1.setIcon(new ImageIcon(AddClassFrm.class.getResource("/images/\u73ED\u7EA72.png")));
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(107, 80, 97, 23);
		getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel("\u4E8C\u7EA7\u5B66\u9662\uFF1A");
		label_3.setIcon(new ImageIcon(AddClassFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		label_3.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_3.setBounds(90, 120, 107, 23);
		getContentPane().add(label_3);
		
		classNumberTextField = new JTextField();
		classNumberTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		classNumberTextField.setColumns(10);
		classNumberTextField.setBounds(207, 40, 165, 23);
		getContentPane().add(classNumberTextField);
		
		classNameTextField = new JTextField();
		classNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		classNameTextField.setColumns(10);
		classNameTextField.setBounds(207, 80, 165, 23);
		getContentPane().add(classNameTextField);
		
		CollegeTypeComboBox = new JComboBox();
		//将下拉框的默认值换用为枚举类CollegeType里的值
		CollegeTypeComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
			CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
			CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		
		CollegeTypeComboBox.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		CollegeTypeComboBox.setBounds(207, 120, 165, 23);
		getContentPane().add(CollegeTypeComboBox);
		
		//确定添加按钮的实现
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addValue(ae);
			}
		});
		affButton.setIcon(new ImageIcon(AddClassFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		affButton.setBounds(139, 175, 100, 30);
		getContentPane().add(affButton);
		
		//重置按钮的实现
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddClassFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		resetButton.setBounds(250, 175, 100, 30);
		getContentPane().add(resetButton);
	}
	
	//确定按钮的方法
	protected void addValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		String classNumber = classNumberTextField.getText().toString();
		String className = classNameTextField.getText().toString();
		CollegeType selectedItem = (CollegeType) CollegeTypeComboBox.getSelectedItem();
		
		//判断输入信息是否为空
		if(StringUtil.isEmpty(classNumber)) {
			JOptionPane.showMessageDialog(this, "班级编号不能为空！");
			return;
		}
		if(StringUtil.isEmpty(className)) {
			JOptionPane.showMessageDialog(this, "班级名不能为空！");
			return;
		}

		//添加信息
		StudentClass scl = new StudentClass();
		scl.setClassNumber(classNumber);
		scl.setClassName(className);
		scl.setCollegeType(selectedItem.getName());
		
		ClassDao classDao = new ClassDao();
		if(classDao.addClass(scl)) {
			JOptionPane.showMessageDialog(this, "班级添加成功！");
		}else {
			JOptionPane.showMessageDialog(this, "班级添加失败！");
		}
		classDao.closeDao();
		resetValue(ae);
	}

	//重置按钮的方法
	protected void resetValue(ActionEvent ae) {
		// TODO 自动生成的方法存根
		classNumberTextField.setText("");
		classNameTextField.setText("");
		CollegeTypeComboBox.setSelectedIndex(0);
	}

}
