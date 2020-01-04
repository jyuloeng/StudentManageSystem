package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import dao.CourseDao;
import dao.ScoreDao;
import dao.TeacherDao;
import model.Course;
import model.Teacher;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ScoreStatisticFrm extends JInternalFrame{
	private JComboBox courseNameComboBox;
	private JPanel viewPanel;
	private List<Course> courseList = new ArrayList<Course>();
	private ChartPanel chartPanel ;
	private JPanel defaultPanel;
	private JLabel studentNumLabel;
	private JLabel maxScoreLabel;
	private JLabel minScoreLabel;
	private JLabel averScoreLabel;
	private JLabel FullScoreNumLabel;
	private JLabel aScoreNumLabel;
	private JLabel bScoreNumLabel;
	private JLabel cScoreNumLabel;
	private JLabel dScoreNumLabel;
	private JLabel eScoreNumLabel;
//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StatisticScoreFrm window = new StatisticScoreFrm();
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
	public ScoreStatisticFrm() {
		setFrameIcon(new ImageIcon(ScoreStatisticFrm.class.getResource("/images/\u7EDF\u8BA1.png")));
		setTitle("\u6210\u7EE9\u7EDF\u8BA1");
		getContentPane().setLayout(null);
		
		JButton baViewrButton = new JButton("\u67F1\u72B6\u56FE\u663E\u793A");
		baViewrButton.addActionListener(new ActionListener() {
			//��״ͼ��ʾ
			public void actionPerformed(ActionEvent e) {
				setBarView(e);
			}
		});
		baViewrButton.setIcon(new ImageIcon(ScoreStatisticFrm.class.getResource("/images/\u7EDF\u8BA1.png")));
		baViewrButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		baViewrButton.setBounds(463, 50, 127, 25);
		getContentPane().add(baViewrButton);
		
		JButton pieViewButton = new JButton("\u997C\u72B6\u56FE\u663E\u793A");
		pieViewButton.addActionListener(new ActionListener() {
			//��״ͼ��ʾ
			public void actionPerformed(ActionEvent e) {
				setPieView(e);
			}
		});
		pieViewButton.setIcon(new ImageIcon(ScoreStatisticFrm.class.getResource("/images/\u7EDF\u8BA1\u6210\u7EE9.png")));
		pieViewButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		pieViewButton.setBounds(611, 50, 127, 25);
		getContentPane().add(pieViewButton);
		
		
		JLabel label_2 = new JLabel("\u8BFE\u7A0B\u540D\u79F0\uFF1A");
		label_2.setIcon(new ImageIcon(ScoreStatisticFrm.class.getResource("/images/\u8BFE\u7A0B\u6DFB\u52A0.png")));
		label_2.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_2.setBounds(45, 50, 107, 23);
		getContentPane().add(label_2);
		
		viewPanel = new JPanel();
		viewPanel.setBorder(new TitledBorder(null, "\u6210\u7EE9\u7EDF\u8BA1\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		viewPanel.setBounds(15, 100, 285, 557);
		getContentPane().add(viewPanel);
		viewPanel.setLayout(null);
		
		JLabel label_3 = new JLabel("\u5B66\u751F\u4EBA\u6570\uFF1A");
		label_3.setBounds(68, 58, 107, 23);
		viewPanel.add(label_3);
		label_3.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel lblNewLabel = new JLabel("\u6700\u9AD8\u6210\u7EE9\uFF1A");
		lblNewLabel.setBounds(68, 103, 107, 23);
		viewPanel.add(lblNewLabel);
		lblNewLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel lblNewLabel_1 = new JLabel("\u6700\u4F4E\u6210\u7EE9\uFF1A");
		lblNewLabel_1.setBounds(68, 148, 107, 23);
		viewPanel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel label = new JLabel("\u5E73\u5747\u6210\u7EE9\uFF1A");
		label.setBounds(68, 193, 107, 23);
		viewPanel.add(label);
		label.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		
		JLabel label_1 = new JLabel("\u6EE1\u5206\u4EBA\u6570\uFF1A");
		label_1.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_1.setBounds(68, 238, 107, 23);
		viewPanel.add(label_1);
		
		JLabel label_6 = new JLabel("90~99\u5206\u4EBA\u6570\uFF1A");
		label_6.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_6.setBounds(36, 283, 121, 23);
		viewPanel.add(label_6);
		
		studentNumLabel = new JLabel("");
		studentNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		studentNumLabel.setBounds(156, 59, 107, 23);
		viewPanel.add(studentNumLabel);
		
		maxScoreLabel = new JLabel("");
		maxScoreLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		maxScoreLabel.setBounds(156, 104, 107, 23);
		viewPanel.add(maxScoreLabel);
		
		minScoreLabel = new JLabel("");
		minScoreLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		minScoreLabel.setBounds(156, 149, 107, 23);
		viewPanel.add(minScoreLabel);
		
		averScoreLabel = new JLabel("");
		averScoreLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		averScoreLabel.setBounds(156, 194, 107, 23);
		viewPanel.add(averScoreLabel);
		
		JLabel label_7 = new JLabel("80~89\u5206\u4EBA\u6570\uFF1A");
		label_7.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_7.setBounds(36, 328, 121, 23);
		viewPanel.add(label_7);
		
		JLabel label_8 = new JLabel("70~79\u5206\u4EBA\u6570\uFF1A");
		label_8.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_8.setBounds(36, 373, 121, 23);
		viewPanel.add(label_8);
		
		JLabel label_9 = new JLabel("60~69\u5206\u4EBA\u6570\uFF1A");
		label_9.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_9.setBounds(36, 418, 121, 23);
		viewPanel.add(label_9);
		
		JLabel label_10 = new JLabel("\u4E0D\u53CA\u683C\u4EBA\u6570\uFF1A");
		label_10.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		label_10.setBounds(54, 463, 121, 23);
		viewPanel.add(label_10);
		
		FullScoreNumLabel = new JLabel("");
		FullScoreNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		FullScoreNumLabel.setBounds(156, 239, 107, 23);
		viewPanel.add(FullScoreNumLabel);
		
		aScoreNumLabel = new JLabel("");
		aScoreNumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		aScoreNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		aScoreNumLabel.setBounds(156, 284, 107, 23);
		viewPanel.add(aScoreNumLabel);
		
		bScoreNumLabel = new JLabel("");
		bScoreNumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bScoreNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		bScoreNumLabel.setBounds(156, 329, 107, 23);
		viewPanel.add(bScoreNumLabel);
		
		cScoreNumLabel = new JLabel("");
		cScoreNumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		cScoreNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		cScoreNumLabel.setBounds(156, 374, 107, 23);
		viewPanel.add(cScoreNumLabel);
		
		dScoreNumLabel = new JLabel("");
		dScoreNumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		dScoreNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		dScoreNumLabel.setBounds(156, 419, 107, 23);
		viewPanel.add(dScoreNumLabel);
		
		eScoreNumLabel = new JLabel("");
		eScoreNumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		eScoreNumLabel.setFont(new Font("΢���ź�", Font.PLAIN, 16));
		eScoreNumLabel.setBounds(156, 464, 107, 23);
		viewPanel.add(eScoreNumLabel);
		
		courseNameComboBox = new JComboBox();
		courseNameComboBox.addItemListener(new ItemListener() {
			//��Ӽ����¼�
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED) {
					
					Course course = (Course) courseNameComboBox.getSelectedItem();
					ScoreDao scoreDao = new ScoreDao();
					Map<String, String> scoreInfo = scoreDao.getScoreInfo(course.getId());
					if(scoreInfo.size()>0) {
						maxScoreLabel.setText(scoreInfo.get("maxScore"));
						minScoreLabel.setText(scoreInfo.get("minScore"));
						averScoreLabel.setText(scoreInfo.get("avgScore"));
						studentNumLabel.setText(scoreInfo.get("studentNum"));
						FullScoreNumLabel.setText(scoreInfo.get("��������"));
						aScoreNumLabel.setText(scoreInfo.get("aNum"));
						bScoreNumLabel.setText(scoreInfo.get("bNum"));
						cScoreNumLabel.setText(scoreInfo.get("cNum"));
						dScoreNumLabel.setText(scoreInfo.get("dNum"));
						eScoreNumLabel.setText(scoreInfo.get("eNum"));
					}
				}
			}
		});
		setSelectCourseCombox();
		courseNameComboBox.setFont(new Font("΢���ź�", Font.PLAIN, 14));
		courseNameComboBox.setBounds(152, 50, 120, 23);
		getContentPane().add(courseNameComboBox);
		
		defaultPanel = new JPanel();
		defaultPanel.setBorder(new TitledBorder(null, "\u6210\u7EE9\u7EDF\u8BA1\u56FE\u4FE1\u606F\u663E\u793A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		defaultPanel.setBounds(313, 100, 557, 557);
		getContentPane().add(defaultPanel);
		
		defaultPanel.setLayout(null);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
		setBounds(100, 100, 900, 700);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�ɹر���С
		setClosable(true);
		setIconifiable(true);
	}
	
	//��ʾѡ����Ϣ
	private void setSelectCourseCombox() {
		CourseDao courseDao = new CourseDao();
		courseList = courseDao.getCourseList(new Course());
		courseDao.closeDao();
		for(Course cr:courseList) {
			if("��ʦ".equals(MainFrm.userType.getName())) {
				Teacher teacher = (Teacher) MainFrm.userObject;
				if(cr.getTeacherName().equals(teacher.getTeacherName())) {
					courseNameComboBox.addItem(cr);
				}
				continue;	
			}
			courseNameComboBox.addItem(cr);
		}
	}
	
	//������ʦ���ֻ��ѡ��
	private List<Course> getTeachCouseList(String teacherName){
		CourseDao courseDao = new CourseDao();
		List<Course> teachCourseList = courseDao.getTeachCourseList(teacherName);
		return teachCourseList;
	}
	
	//��״ͼ��ʾ
	protected void setBarView(ActionEvent e) {
		// TODO �Զ����ɵķ������
		Course course = (Course)courseNameComboBox.getSelectedItem();
		//�������ݿ�Ϊ��ͼ���������Ϣ
		ScoreDao scoreDao = new ScoreDao();
		Map<String, String> statsInfo = scoreDao.getScoreInfo(course.getId());
		clearPanel();
		drawBar(Integer.parseInt(statsInfo.get("studentNum")), 
				Integer.parseInt(statsInfo.get("��������")), 
				Integer.parseInt(statsInfo.get("aNum")), 
				Integer.parseInt(statsInfo.get("bNum")),  
				Integer.parseInt(statsInfo.get("cNum")), 
				Integer.parseInt(statsInfo.get("dNum")), 
				Integer.parseInt(statsInfo.get("eNum")), 
				course.getCourseName());
	}
	
	//��״ͼ��ʾ
	protected void setPieView(ActionEvent e) {
		// TODO �Զ����ɵķ������
		Course course = (Course)courseNameComboBox.getSelectedItem();
		//�������ݿ�Ϊ��ͼ���������Ϣ
		ScoreDao scoreDao = new ScoreDao();
		Map<String, String> statsInfo = scoreDao.getScoreInfo(course.getId());
		clearPanel();
		drawPie(Integer.parseInt(statsInfo.get("studentNum")), 
				Integer.parseInt(statsInfo.get("��������")),
				Integer.parseInt(statsInfo.get("aNum")), 
				Integer.parseInt(statsInfo.get("bNum")), 
				Integer.parseInt(statsInfo.get("cNum")), 
				Integer.parseInt(statsInfo.get("dNum")), 
				Integer.parseInt(statsInfo.get("eNum")), 
				course.getCourseName());
	}
	

	//��մ�����Ϣ
	private void clearPanel(){
		defaultPanel.removeAll();
		defaultPanel.updateUI();
		defaultPanel.repaint();
	}
	
	//����������ʽ
	private void setLanuage(){
		//����������ʽ  
		StandardChartTheme standardChartTheme=new StandardChartTheme("CN");  
		//���ñ�������  
		standardChartTheme.setExtraLargeFont(new Font("΢���ź�",Font.BOLD,20));  
		//����ͼ��������  
		standardChartTheme.setRegularFont(new Font("΢���ź�",Font.PLAIN,14));  
		//�������������  
		standardChartTheme.setLargeFont(new Font("΢���ź�",Font.PLAIN,16));  
		//Ӧ��������ʽ  
		ChartFactory.setChartTheme(standardChartTheme);
	}
	
	//����״ͼ
	private void drawBar(int studentNum,int fullScore,int aNum,int bNum,int cNum,int dNum,int eNum,String courseName) {
		setLanuage();
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();//����һ�����ݼ�
		//�������	
		dataSet.addValue(fullScore, "100��", "100��");
		dataSet.addValue(aNum, "90~99��", "90~99��");
		dataSet.addValue(bNum, "80~89��", "80~89��");
		dataSet.addValue(cNum, "70~79��", "70~79��");
		dataSet.addValue(dNum, "60~69��", "60~69��");
		dataSet.addValue(eNum, "0~59��", "0~59��");
		//����һ��chart���󣬰����ݼ��Ž�ȥ
		JFreeChart chart = ChartFactory.createBarChart(courseName+"�γ�ѧ���ɼ�ͳ�����", "�ɼ����", "ѧ������",
				dataSet, PlotOrientation.VERTICAL, true, false, false);
		//����һ��ͼ��panel
		chartPanel= new ChartPanel(chart);
		//��ͼ��panel��ӵ�Ҫ��ʾ��panel��
		chartPanel.setPreferredSize(new Dimension(532,519));
		defaultPanel.add(chartPanel,BorderLayout.CENTER);
		defaultPanel.setLayout(new FlowLayout());
		defaultPanel.updateUI();
		defaultPanel.repaint();
	}
	
	//����״ͼ
	private void drawPie(int studentNum,int fullScore,int aNum,int bNum,int cNum,int dNum,int eNum,String courseName) {
		setLanuage();
		DefaultPieDataset dataSet = new DefaultPieDataset();//�������ݼ�
		//�������	
		dataSet.setValue("100��",fullScore);
		dataSet.setValue("90~99��",aNum);
		dataSet.setValue("80~89��",bNum);
		dataSet.setValue("70~79��",cNum);
		dataSet.setValue("60~69��",dNum);
		dataSet.setValue("0~59��",eNum);
		//����һ��chart���󣬰����ݼ��Ž�ȥ
		JFreeChart chart = ChartFactory.createPieChart(courseName+"�γ�ѧ���ɼ�ͳ�����", dataSet, true, true, false);
		//����һ��plot���󣬽���״ͼ�����⴦��
		PiePlot plot = (PiePlot)chart.getPlot();
		plot.setExplodePercent("100��", fullScore);
		plot.setIgnoreNullValues(true);
		plot.setIgnoreZeroValues(true);
		//����һ��ͼ��panel
		chartPanel= new ChartPanel(chart);
		//��ͼ��panel��ӵ�Ҫ��ʾ��panel��
		chartPanel.setPreferredSize(new Dimension(532,519));
		defaultPanel.add(chartPanel,BorderLayout.CENTER);
		defaultPanel.setLayout(new FlowLayout());
		defaultPanel.updateUI();
		defaultPanel.repaint();
	}
}
