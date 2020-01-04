package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import dao.TeacherDao;
import model.CollegeType;
import model.EntryYear;
import model.SexType;
import model.Teacher;
import util.ImagePanel;
import util.StringUtil;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;
import javax.swing.JPanel;

public class AddTeacherFrm extends JInternalFrame{


	private JTextField userNumberNextField;
	private JTextField userNameTextField;
	private JTextField passWordTextField;
	private JTextField affrimPassWordTextField;
	private JComboBox sexTypeComboBox;
	private JComboBox CollegeTypeComboBox;
	private JComboBox yearComboBox;
	private JButton photoButton;
	private JLabel photoLabel;	//���ڷ����û�ͷ��
	private String address = "";	//���ڻ�ôӱ��ػ�õ��û�ͷ��ľ��Ե�ַ
	private ImageIcon img;	//���û�ͷ����ImageIcon����ʽչ��

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddTeacherFrm window = new AddTeacherFrm();
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
	public AddTeacherFrm() {
		setFrameIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
//		setIconImage(Toolkit.getDefaultToolkit().getImage(AddTeacherFrm.class.getResource("/images/\u6DFB\u52A0\u6559\u5E08.png")));
		setFont(new Font("΢���ź�", Font.PLAIN, 16));
		setTitle("\u6559\u5E08\u6DFB\u52A0");
		setBounds(100, 100, 630, 445);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
			
		//�ɹر���С
		setClosable(true);
		setIconifiable(true);
		
		JLabel label = new JLabel("\u5DE5\u53F7\uFF1A");
		label.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u5B66\u53F7.png")));
		label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label.setBounds(120, 40, 80, 23);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
		label_1.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_1.setBounds(120, 160, 80, 23);
		getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u6027\u522B\uFF1A");
		label_2.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u6027\u522B.png")));
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_2.setBounds(120, 200, 80, 23);
		getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("\u4E8C\u7EA7\u5B66\u9662\uFF1A");
		label_3.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_3.setBounds(90, 240, 107, 23);
		getContentPane().add(label_3);
		
		JLabel label_5 = new JLabel("\u5165\u804C\u5E74\u4EFD\uFF1A");
		label_5.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u5165\u5B66\u5E74\u4EFD2.png")));
		label_5.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_5.setBounds(90, 280, 107, 23);
		getContentPane().add(label_5);
		
		userNumberNextField = new JTextField();
		userNumberNextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		userNumberNextField.setColumns(10);
		userNumberNextField.setBounds(207, 40, 165, 23);
		getContentPane().add(userNumberNextField);
		
		userNameTextField = new JTextField();
		userNameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		userNameTextField.setColumns(10);
		userNameTextField.setBounds(207, 160, 165, 23);
		getContentPane().add(userNameTextField);
		
		sexTypeComboBox = new JComboBox();
		//���������Ĭ��ֵ����Ϊö����sexType���ֵ
		sexTypeComboBox.setModel(new DefaultComboBoxModel(new SexType[] {SexType.MAN,SexType.WOMAN}));
		sexTypeComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		sexTypeComboBox.setBounds(207, 200, 165, 23);
		getContentPane().add(sexTypeComboBox);
		
		CollegeTypeComboBox = new JComboBox();
		//���������Ĭ��ֵ����Ϊö����CollegeType���ֵ
		CollegeTypeComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		
		CollegeTypeComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		CollegeTypeComboBox.setBounds(207, 240, 165, 23);
		getContentPane().add(CollegeTypeComboBox);
		
		yearComboBox = new JComboBox();
		//���������Ĭ��ֵ����Ϊö����EnterYear���ֵ
		yearComboBox.setModel(new DefaultComboBoxModel(new EntryYear[] {
				EntryYear.A1,EntryYear.A2,EntryYear.A3,EntryYear.A4,EntryYear.A5,
				EntryYear.B1,EntryYear.B2,EntryYear.B3,EntryYear.B4,EntryYear.B5,
				EntryYear.B6,EntryYear.B7,EntryYear.B8,EntryYear.B9,EntryYear.B10,
				EntryYear.C1,EntryYear.C2,EntryYear.C3,EntryYear.C4,EntryYear.C5,
				EntryYear.C6,EntryYear.C7,EntryYear.C8,EntryYear.C9,EntryYear.C10,
				}) );
		yearComboBox.setSelectedIndex(24);
		yearComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		yearComboBox.setBounds(207, 280, 165, 23);
		getContentPane().add(yearComboBox);
		
		
		//��ӽ�ʦ��Ϣ��ť��ʵ��
		JButton affButton = new JButton("\u786E\u8BA4");
		affButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addValue(ae);
			}
		});
		affButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u786E\u8BA4\u6309\u94AE.png")));
		affButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		affButton.setBounds(205, 335, 100, 30);
		getContentPane().add(affButton);
		
		//���ð�ť��ʵ��
		JButton resetButton = new JButton("\u91CD\u7F6E");
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				resetValue(ae);
			}
		});
		resetButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		resetButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		resetButton.setBounds(325, 335, 100, 30);
		getContentPane().add(resetButton);
		
		JLabel label_6 = new JLabel("\u8BBE\u7F6E\u5BC6\u7801\uFF1A");
		label_6.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		label_6.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_6.setBounds(90, 80, 107, 23);
		getContentPane().add(label_6);
		
		JLabel label_7 = new JLabel("\u786E\u5B9A\u5BC6\u7801\uFF1A");
		label_7.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		label_7.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_7.setBounds(90, 120, 107, 23);
		getContentPane().add(label_7);
		
		passWordTextField = new JTextField();
		passWordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		passWordTextField.setColumns(10);
		passWordTextField.setBounds(207, 80, 165, 23);
		getContentPane().add(passWordTextField);
		
		affrimPassWordTextField = new JTextField();
		affrimPassWordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		affrimPassWordTextField.setColumns(10);
		affrimPassWordTextField.setBounds(207, 120, 165, 23);
		getContentPane().add(affrimPassWordTextField);
		
		photoLabel = new JLabel("");
		photoLabel.setBounds(430, 70, 120, 160);
		getContentPane().add(photoLabel);
		img = new ImageIcon("C:\\Users\\Shinelon\\Desktop\\logo�����\\defaultPhoto.jpg");
		img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
		photoLabel.setIcon(img);
		
		photoButton = new JButton("\u4E0A\u4F20\u7167\u7247");
		photoButton.setIcon(new ImageIcon(AddTeacherFrm.class.getResource("/images/\u4E0A\u4F20\u7167\u7247.png")));
		photoButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		photoButton.addActionListener(new ActionListener() {
			//���ļ�
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();	//����ѡ���ļ�����
				chooser.setDialogTitle("��ѡ���ļ�");  //���ñ���
				chooser.setMultiSelectionEnabled(true);  //����ֻ��ѡ����ļ�
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); //�������ѡ����ļ�����
				chooser.setFileFilter(filter); //���ÿ�ѡ����ļ�����
				chooser.showOpenDialog(null);  //��ѡ���ļ��Ի���
				img = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));//����ͼƬ��ʾ�Ĵ�С����λ
				photoLabel.setIcon(img);
				address=chooser.getSelectedFile().getAbsolutePath(); //���ѡ���ļ�����ľ��Ե�ַ
				
			}	
		});
		photoButton.setBounds(419, 250, 130, 30);
		getContentPane().add(photoButton);
		
		
	}
	

	//ȷ����ť�ķ���
	protected void addValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		String teacherID = userNumberNextField.getText().toString();
		
//		Integer teacherID = Integer.valueOf(str);
		String teacherpassWord = passWordTextField.getText().toString();
		String teacherAffPassWord = affrimPassWordTextField.getText().toString();
		String teacherName = userNameTextField.getText().toString();
		SexType teacherSexType = (SexType) sexTypeComboBox.getSelectedItem();
		CollegeType teacherCollege = (CollegeType) CollegeTypeComboBox.getSelectedItem();
		EntryYear entryYear = (EntryYear) yearComboBox.getSelectedItem();
		
		//�ж�������Ϣ�Ƿ�Ϊ��
		if(StringUtil.isEmpty(teacherID)) {
			JOptionPane.showMessageDialog(this, "��ʦ���Ų���Ϊ�գ�");
			return;
		}
		if(StringUtil.isEmpty(teacherpassWord)) {
			JOptionPane.showMessageDialog(this, "��ʦ���벻��Ϊ�գ�");
			return;
		}
		if(StringUtil.isEmpty(teacherAffPassWord)) {
			JOptionPane.showMessageDialog(this, "��ȷ�Ͻ�ʦ���룡");
			return;
		}
		if(!(teacherpassWord).equals(teacherAffPassWord)) {
			JOptionPane.showMessageDialog(this, "�����������벻һ�£����������룡");
			return;
		}
		if(StringUtil.isEmpty(teacherName)) {
			JOptionPane.showMessageDialog(this, "��ʦ��������Ϊ�գ�");
			return;
		}
		
		//�ж�������Ϣ�Ƿ񲻶�Ӧ
		if(!StringUtil.isNum(teacherID)) {
			JOptionPane.showMessageDialog(this, "��ʦ����ֻ��Ϊ���������֣�");
			return;
		}
		if(!StringUtil.isHanzi(teacherName)) {
			JOptionPane.showMessageDialog(this, "��ʦ����ֻ��Ϊ���֣�");
			return;
		}
		if(address.equals("")) {
			JOptionPane.showMessageDialog(this, "����ӽ�ʦͷ��");
			return;
		}
		
		//�����Ϣ
		//����һ���µ�Teacher���󽫴Ӳ������ȡ����Ϣ��ӽ�ȥ
		Teacher tc = new Teacher();
		tc.setTeacherId(teacherID);
		tc.setPassWord(teacherpassWord);
		tc.setTeacherName(teacherName);
		tc.setTeacherSex(teacherSexType.getName());
		tc.setTeacherCollege(teacherCollege.getName());
		tc.setEntryYear(entryYear.getName());
		
		//�������ݿ�
		TeacherDao teacherDao = new TeacherDao();
		if(teacherDao.isAdd(tc)) {
			JOptionPane.showMessageDialog(this, "�����ظ���Ӹù��ŵ���Ϣ��");
			userNumberNextField.setText("");
			return;
		}
		if(teacherDao.addTeacher(tc,address)) {
			JOptionPane.showMessageDialog(this, "��ʦ��Ϣ��ӳɹ���");
			//�����ɺ�ͷ�񻻻�Ĭ��ͷ��
			img = new ImageIcon("C:\\Users\\Shinelon\\Desktop\\logo�����\\defaultPhoto.jpg");
			img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
			photoLabel.setIcon(img);
		}else {
			JOptionPane.showMessageDialog(this, "��ʦ��Ϣ���ʧ�ܣ�");
		}
		teacherDao.closeDao();
		resetValue(ae);		//��������ֵ��������������������ʱһ��
	}

	//���ð�ť�ķ���
	protected void resetValue(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		userNumberNextField.setText("");
		userNameTextField.setText("");
		passWordTextField.setText("");
		affrimPassWordTextField.setText("");
		sexTypeComboBox.setSelectedIndex(0); 
		CollegeTypeComboBox.setSelectedIndex(0);
		yearComboBox.setSelectedIndex(24);
		img = new ImageIcon("C:\\Users\\Shinelon\\Desktop\\logo�����\\defaultPhoto.jpg");
		img.setImage(img.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
		photoLabel.setIcon(img);
	}
}
