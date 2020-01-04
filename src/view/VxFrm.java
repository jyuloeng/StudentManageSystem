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
import javax.swing.JLabel;

import model.UserType;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class VxFrm extends JInternalFrame{

//	JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VxFrm window = new VxFrm();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VxFrm() {
		setFrameIcon(new ImageIcon(VxFrm.class.getResource("/images/\u5173\u4E8E\u6211\u4EEC.png")));
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
//		frame = new JFrame();
//		setIconImage(Toolkit.getDefaultToolkit().getImage(VxFrm.class.getResource("/images/\u5173\u4E8E\u6211\u4EEC.png")));
		setTitle("\u5173\u4E8E\u6211\u4EEC");
		setBounds(100, 100, 236, 250);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocation(215, 185);
	
		
		//ø…πÿ±’Àı–°
		setClosable(true);
		setIconifiable(true);
		
		JLabel lblNewLabel = new JLabel("   wu");
		lblNewLabel.setIcon(new ImageIcon(VxFrm.class.getResource("/images/\u4E8C\u7EF4\u7801.png")));
		lblNewLabel.setBounds(10, 10, 200, 200);
		getContentPane().add(lblNewLabel);
	}

}
