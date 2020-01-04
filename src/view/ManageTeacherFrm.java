package view;

import java.awt.EventQueue;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.StudentDao;
import dao.TeacherDao;
import model.CollegeType;
import model.EntryYear;
import model.SexType;
import model.Student;
import model.StudentClass;
import model.Teacher;
import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ManageTeacherFrm extends JInternalFrame{

//	private JFrame frame;
	private JTable TeacherListTable;
	private JTextField editTeacherIDTextField;
	private JTextField editPassWordTextField;
	private JTextField editTeacherNameTextField;
	private JTextField searchTeacherNTextField;
	private JComboBox editTeacherSexComboBox;
	private JComboBox editTeacherCoComboBox ;
	private JComboBox editEntryYearComboBox;
	private JComboBox searchTeacherCoComboBox;
	private JButton deleteButton;
	private JButton searchButton;
	private JButton photoButton;
	private Image img;
	private ImageIcon icon;
	private ImageIcon icon1;
	private JLabel photoLabel;
	private String address;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TeacherManageFrm window = new TeacherManageFrm();
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
	public ManageTeacherFrm() {
		setFrameIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5217\u8868.png")));
		initialize();
	}
	
	private void setTable(Teacher tc) {
		//����ǽ�ʦ��½ʱ
		if("��ʦ".equals(MainFrm.userType.getName())) {
			Teacher t = (Teacher) MainFrm.userObject;
			tc.setTeacherName(t.getTeacherName());
		}//TeacherListTableΪJTable���ͣ�������ʾ��ʦ��Ϣ��
		DefaultTableModel dft = (DefaultTableModel) TeacherListTable.getModel();
		dft.setRowCount(0);
		TeacherDao td = new TeacherDao();
		List<Teacher> teacherList = td.getTeacherList(tc);
		for(Teacher teacher: teacherList) {
			//����һ��Vector�������ڴ����Ϣ
			Vector vt = new Vector();
			vt.add(teacher.getId());
			vt.add(teacher.getTeacherId());
			vt.add(teacher.getPassWord());
			vt.add(teacher.getTeacherName());
			vt.add(teacher.getTeacherSex());
			vt.add(teacher.getTeacherCollege());
			vt.add(teacher.getEntryYear());
			dft.addRow(vt);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setTitle("\u6559\u5E08\u7BA1\u7406");
		setBounds(100, 100, 918, 600);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		//�ɹر���С
		setClosable(true);
		setIconifiable(true);
		
		
		
		TeacherListTable = new JTable();
		TeacherListTable.addMouseListener(new MouseAdapter() {
			////�����ʱ��Ч��
			@Override
			public void mouseClicked(MouseEvent ae) {
				selectTableRow(ae);
			}
		});
		TeacherListTable.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		TeacherListTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"\u6559\u5E08Id", "\u6559\u5E08\u5DE5\u53F7", "\u6559\u5E08\u5BC6\u7801", "\u6559\u5E08\u59D3\u540D", "\u6559\u5E08\u6027\u522B", "\u4E8C\u7EA7\u5B66\u9662", "\u5165\u804C\u5E74\u4EFD"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		setTable(new Teacher());
		
		JScrollPane scrollPane = new JScrollPane(TeacherListTable);
		scrollPane.setBounds(70, 96, 600, 300);
		getContentPane().add(scrollPane);
		
		JLabel lblNewLabel = new JLabel("\u6559\u5E08\u5DE5\u53F7\uFF1A");
		lblNewLabel.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5B66\u53F7.png")));
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel.setBounds(104, 423, 107, 23);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		lblNewLabel_1.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(103, 503, 107, 23);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u6559\u5E08\u5BC6\u7801\uFF1A");
		lblNewLabel_2.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u65B0\u5BC6\u7801.png")));
		lblNewLabel_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(103, 463, 107, 23);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u6559\u5E08\u6027\u522B\uFF1A");
		lblNewLabel_3.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u6027\u522B.png")));
		lblNewLabel_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(366, 423, 107, 23);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u4E8C\u7EA7\u5B66\u9662\uFF1A");
		lblNewLabel_4.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		lblNewLabel_4.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(366, 463, 107, 23);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u5165\u804C\u5E74\u4EFD\uFF1A");
		lblNewLabel_5.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5165\u5B66\u5E74\u4EFD2.png")));
		lblNewLabel_5.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_5.setBounds(366, 503, 107, 23);
		getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_8 = new JLabel("\u6559\u5E08\u59D3\u540D\uFF1A");
		lblNewLabel_8.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_8.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5F53\u524D\u7528\u6237.png")));
		lblNewLabel_8.setBounds(130, 44, 107, 23);
		getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("\u4E8C\u7EA7\u5B66\u9662\uFF1A");
		lblNewLabel_9.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u4E8C\u7EA7\u5B66\u9662.png")));
		lblNewLabel_9.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		lblNewLabel_9.setBounds(392, 44, 107, 23);
		getContentPane().add(lblNewLabel_9);
		
		searchButton = new JButton("\u67E5\u8BE2");
		searchButton.addActionListener(new ActionListener() {
			//��ѯ��ť
			public void actionPerformed(ActionEvent e) {
				searchTeacher(e);
			}
		});
		searchButton.setHorizontalAlignment(SwingConstants.LEFT);
		searchButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		searchButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u67E5\u8BE2.png")));
		searchButton.setBounds(673, 43, 89, 25);
		getContentPane().add(searchButton);
		
		JButton editButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
		editButton.addActionListener(new ActionListener() {
			//�޸����ݿ����ʦ����Ϣ
			public void actionPerformed(ActionEvent ae) {
				editTeacher(ae);
			}
		});
		editButton.setHorizontalAlignment(SwingConstants.LEFT);
		editButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u786E\u8BA4.png")));
		editButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editButton.setBounds(647, 448, 121, 25);
		getContentPane().add(editButton);
		
		deleteButton = new JButton("\u5220\u9664\u6559\u5E08");
		deleteButton.addActionListener(new ActionListener() {
			//ɾ�����ݿ���Ľ�ʦ
			public void actionPerformed(ActionEvent ae) {
				deleteTeacher(ae);
			}
		});
		deleteButton.setHorizontalAlignment(SwingConstants.LEFT);
		deleteButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u5220\u96641.png")));
		deleteButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		deleteButton.setBounds(647, 483, 121, 25);
		getContentPane().add(deleteButton);
		
		editTeacherIDTextField = new JTextField();
		editTeacherIDTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editTeacherIDTextField.setBounds(220, 423, 120, 23);
		getContentPane().add(editTeacherIDTextField);
		editTeacherIDTextField.setColumns(10);
		
		editPassWordTextField = new JTextField();
		editPassWordTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editPassWordTextField.setBounds(220, 463, 120, 23);
		getContentPane().add(editPassWordTextField);
		editPassWordTextField.setColumns(10);
		
		editTeacherNameTextField = new JTextField();
		editTeacherNameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editTeacherNameTextField.setBounds(220, 505, 120, 23);
		getContentPane().add(editTeacherNameTextField);
		editTeacherNameTextField.setColumns(10);
		
		editTeacherSexComboBox = new JComboBox();
		editTeacherSexComboBox.setModel(new DefaultComboBoxModel(new SexType[] {SexType.MAN,SexType.WOMAN}));
		editTeacherSexComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editTeacherSexComboBox.setBounds(486, 423, 120, 23);
		getContentPane().add(editTeacherSexComboBox);
		
		editTeacherCoComboBox = new JComboBox();
		editTeacherCoComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		editTeacherCoComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editTeacherCoComboBox.setBounds(486, 463, 120, 23);
		getContentPane().add(editTeacherCoComboBox);
		
		editEntryYearComboBox = new JComboBox();
		editEntryYearComboBox.setModel(new DefaultComboBoxModel(new EntryYear[] {
				EntryYear.A1,EntryYear.A2,EntryYear.A3,EntryYear.A4,EntryYear.A5,
				EntryYear.B1,EntryYear.B2,EntryYear.B3,EntryYear.B4,EntryYear.B5,
				EntryYear.B6,EntryYear.B7,EntryYear.B8,EntryYear.B9,EntryYear.B10,
				EntryYear.C1,EntryYear.C2,EntryYear.C3,EntryYear.C4,EntryYear.C5,
				EntryYear.C6,EntryYear.C7,EntryYear.C8,EntryYear.C9,EntryYear.C10,
				}) );
		editEntryYearComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		editEntryYearComboBox.setBounds(486, 503, 120, 23);
		getContentPane().add(editEntryYearComboBox);
		
		searchTeacherNTextField = new JTextField();
		searchTeacherNTextField.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		searchTeacherNTextField.setBounds(246, 44, 120, 23);
		getContentPane().add(searchTeacherNTextField);
		searchTeacherNTextField.setColumns(10);
		
		searchTeacherCoComboBox = new JComboBox();
		searchTeacherCoComboBox.addItemListener(new ItemListener() {
			//���ü����¼�
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					CollegeType collegeType = (CollegeType) searchTeacherCoComboBox.getSelectedItem();
					Teacher tc = new Teacher();
					tc.setTeacherCollege(collegeType.getName());
					setTable(tc);
				}
			}
		});
		searchTeacherCoComboBox.setModel(new DefaultComboBoxModel(new CollegeType[] {CollegeType.COMPUTING,CollegeType.ELECTRONIC_IM,
				CollegeType.ELECTROMECHANICAL_EG,CollegeType.MANAGEMENT,CollegeType.FACULTY_HM,
				CollegeType.FOREIGN_LG,CollegeType.ART_DESIGN,CollegeType.ECONOMIC_TD,CollegeType.MATERICAL_FD}));
		searchTeacherCoComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		searchTeacherCoComboBox.setBounds(509, 44, 120, 23);
		getContentPane().add(searchTeacherCoComboBox);
		
		photoLabel = new JLabel("");
		photoLabel.setBounds(713, 135, 120, 160);
		getContentPane().add(photoLabel);
		
		photoButton = new JButton("\u4FEE\u6539\u7167\u7247");
		photoButton.addActionListener(new ActionListener() {
			//�޸���Ƭ
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();	//����ѡ���ļ�����
				chooser.setDialogTitle("��ѡ���ļ�");  //���ñ���
				chooser.setMultiSelectionEnabled(true);  //����ֻ��ѡ����ļ�
				FileNameExtensionFilter filter = new FileNameExtensionFilter("jpg", "jpg"); //�������ѡ����ļ�����
				chooser.setFileFilter(filter); //���ÿ�ѡ����ļ�����
				chooser.showOpenDialog(null);  //��ѡ���ļ��Ի���
				icon1 = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
				icon1.setImage(icon1.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
				photoLabel.setIcon(icon1);
				address=chooser.getSelectedFile().getAbsolutePath();	//�����Ƭ�ľ��Ե�ַ
			}
		});
		photoButton.setIcon(new ImageIcon(ManageTeacherFrm.class.getResource("/images/\u91CD\u7F6E\u5BC6\u78011.png")));
		photoButton.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		photoButton.setBounds(700, 315, 130, 30);
		getContentPane().add(photoButton);
//		scrollPane.setColumnHeaderView(TeacherListTable);
		setLimit();
	}
	
	
	//���ҽ�ʦ
	protected void searchTeacher(ActionEvent e) {
		// TODO �Զ����ɵķ������
		Teacher tc = new Teacher();
		tc.setTeacherName(searchTeacherNTextField.getText().toString());
		setTable(tc);
	}

	//ѡ����ʱ��Ч��
	protected void selectTableRow(MouseEvent ae) {
		// TODO �Զ����ɵķ������
		address="";		//�����ص�ַ��Ϊ�����ⱨ��
		DefaultTableModel dft = (DefaultTableModel) TeacherListTable.getModel();
		editTeacherIDTextField.setText(dft.getValueAt(TeacherListTable.getSelectedRow(), 1).toString());
		editPassWordTextField.setText(dft.getValueAt(TeacherListTable.getSelectedRow(), 2).toString());
		editTeacherNameTextField.setText(dft.getValueAt(TeacherListTable.getSelectedRow(), 3).toString());
		//�����ƻ�õ�ѡ���������
		String teacherSex = dft.getValueAt(TeacherListTable.getSelectedRow(), 4).toString();
		for(int i=0;i<editTeacherSexComboBox.getItemCount();i++) {
			SexType st = (SexType) editTeacherSexComboBox.getItemAt(i);
			if(teacherSex.equals(st.getName())) {
				editTeacherSexComboBox.setSelectedIndex(i);
			}
		}
		String teacherCollegeType = dft.getValueAt(TeacherListTable.getSelectedRow(), 5).toString();
		for(int i=0;i<editTeacherCoComboBox.getItemCount();i++) {
			CollegeType ct = (CollegeType) editTeacherCoComboBox.getItemAt(i);
			if(teacherCollegeType.equals(ct.getName())) {
				editTeacherCoComboBox.setSelectedIndex(i);
			}
		}
		String entryYear = dft.getValueAt(TeacherListTable.getSelectedRow(), 6).toString();
		for(int i=0;i<editEntryYearComboBox.getItemCount();i++) {
			EntryYear ey = (EntryYear) editEntryYearComboBox.getItemAt(i);
			if(entryYear.equals(ey.getName())) {
				editEntryYearComboBox.setSelectedIndex(i);
			}
		}
		TeacherDao teacherDao = new TeacherDao();
		Teacher teacher = new Teacher();
		Teacher tc3 = new Teacher();
		teacher.setTeacherName(dft.getValueAt(TeacherListTable.getSelectedRow(), 3).toString());
		List<Teacher> teacherList = teacherDao.getTeacherList(new Teacher());
		List<Teacher> teacherList2 = teacherDao.getTeacherList(teacher);
		for(Teacher tc:teacherList) {
			for(Teacher tc2:teacherList2) {
				if(tc.getTeacherName().equals(tc2.getTeacherName())){
					tc3=tc;
				}
			}
		}
		try {
			InputStream is =tc3.getBlob().getBinaryStream();
			img=ImageIO.read(is);
			icon = new ImageIcon(img);
			photoLabel.setIcon(icon);
			icon.setImage(icon.getImage().getScaledInstance(106, 150, Image.SCALE_DEFAULT));
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	//ȷ���޸Ľ�ʦ��Ϣ
	protected void editTeacher(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		TeacherDao teacherDao = new TeacherDao();
		int row = TeacherListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫ�޸ĵĽ�ʦ��Ϣ��");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) TeacherListTable.getModel();
		//��ȡ�޸�ǰ����Ϣ
		String teacherId = dft.getValueAt(TeacherListTable.getSelectedRow(), 1).toString();
		String teacherPW = dft.getValueAt(TeacherListTable.getSelectedRow(), 2).toString();
		String teacherName = dft.getValueAt(TeacherListTable.getSelectedRow(), 3).toString();
		String teacherSex = dft.getValueAt(TeacherListTable.getSelectedRow(), 4).toString();
		String teacherCollege = dft.getValueAt(TeacherListTable.getSelectedRow(), 5).toString();
		String entryYear = dft.getValueAt(TeacherListTable.getSelectedRow(), 6).toString();
		ImageIcon photo = (ImageIcon) photoLabel.getIcon();
		//��ȡ�޸ĺ����Ϣ
		String editTeacherId = editTeacherIDTextField.getText().toString();
		String editTeacherPW = editPassWordTextField.getText().toString();
		String editTeacherName = editTeacherNameTextField.getText().toString();
		SexType editTeacherSex = (SexType) editTeacherSexComboBox.getSelectedItem();
		CollegeType editTeacherCollege = (CollegeType) editTeacherCoComboBox.getSelectedItem();
		EntryYear editEntryYear = (EntryYear) editEntryYearComboBox.getSelectedItem();
		
		//�ж�������Ƿ�Ϊ��ֵ
		if(StringUtil.isEmpty(editTeacherId)) {
			JOptionPane.showMessageDialog(this, "����дҪ�޸ĵĽ�ʦ���!");
			return;
		}
		if(StringUtil.isEmpty(editTeacherPW)) {
			JOptionPane.showMessageDialog(this, "����дҪ�޸ĵĽ�ʦ����!");
			return;
		}
		if(StringUtil.isEmpty(editTeacherName)) {
			JOptionPane.showMessageDialog(this, "����дҪ�޸ĵĽ�ʦ����!");
			return;
		}
		//�ж�������Ϣ�Ƿ񲻶�Ӧ
		if(!StringUtil.isNum(editTeacherId)) {
			JOptionPane.showMessageDialog(this, "��ʦ���ֻ��Ϊ���������֣�");
			return;
		}
		if(!StringUtil.isHanzi(editTeacherName)) {
			JOptionPane.showMessageDialog(this, "��ʦ����ֻ��Ϊ���֣�");
			return;
		}

		//�ж������޸�
		if(teacherId.equals(editTeacherId) && teacherPW.equals(editTeacherPW) &&
				teacherName.equals(editTeacherName) && teacherSex.equals(editTeacherSex.getName()) &&
				teacherCollege.equals(editTeacherCollege.getName()) && entryYear.equals(editEntryYear.getName())&& StringUtil.isEmpty(address)) {
			JOptionPane.showMessageDialog(this, "����û�����κ��޸�!");
			return;
		}
		//��ȡ��ʦ���ݱ��id
		int id = Integer.parseInt( dft.getValueAt(TeacherListTable.getSelectedRow(), 0).toString());
		//���½�ʦ��Ϣ
		Teacher tc = new Teacher();
		tc.setId(id);
		tc.setTeacherId(editTeacherId);
		tc.setAffpassWord(editTeacherPW);
		tc.setTeacherName(editTeacherName);
		tc.setTeacherSex(editTeacherSex.getName());
		tc.setTeacherCollege(editTeacherCollege.getName());
		tc.setEntryYear(editEntryYear.getName());
		if(teacherDao.isAdd(tc)) {
			JOptionPane.showMessageDialog(this, "�����ظ���Ӹù��ŵ���Ϣ��");
			editTeacherIDTextField.setText("");
			return;
		}
		if(teacherDao.update(tc,address)) {
			JOptionPane.showMessageDialog(this, "���½�ʦ��Ϣ�ɹ���");
		}else {
			JOptionPane.showMessageDialog(this, "���½�ʦ��Ϣʧ�ܣ�");
		}
		setTable(new Teacher());
		teacherDao.closeDao();
	}
	//ɾ����ʦ
	protected void deleteTeacher(ActionEvent ae) {
		// TODO �Զ����ɵķ������
		TeacherDao teacherDao = new TeacherDao();
		int row = TeacherListTable.getSelectedRow();
		if(row == -1) {
			JOptionPane.showMessageDialog(this, "��ѡ��Ҫɾ���Ľ�ʦ��Ϣ��");
			return;
		}
		DefaultTableModel dft = (DefaultTableModel) TeacherListTable.getModel();
		String id = dft.getValueAt(TeacherListTable.getSelectedRow(), 0).toString();
		if(JOptionPane.showConfirmDialog(this, "��ȷ��Ҫɾ���ý�ʦ��Ϣ��", "��ܰ��ʾ",
				JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE)!=JOptionPane.OK_OPTION) {
			return;
		}
		if(teacherDao .delete(id)) {
			JOptionPane.showMessageDialog(this, "ɾ����ʦ�ɹ���");
		}else {
			JOptionPane.showMessageDialog(this, "ɾ����ʦʧ�ܣ�");
		}
		setTable(new Teacher());
		teacherDao.closeDao();
		}
	
	//����Ȩ��
	private void setLimit() {
		if("��ʦ".equals(MainFrm.userType.getName())) {
			Teacher sd = (Teacher) MainFrm.userObject;
			deleteButton.setEnabled(false);
			searchTeacherNTextField.setText(sd.getTeacherName());
			searchTeacherCoComboBox.setSelectedItem(sd.getTeacherCollege());
			searchTeacherNTextField.setEditable(false);
			searchTeacherCoComboBox.setEnabled(false);
			searchButton.setEnabled(false);
		}
	}
		
}

