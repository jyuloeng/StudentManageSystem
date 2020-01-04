package util;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private Image img;
	public ImagePanel(Image img) {
		this.img=img;
		Dimension size = new Dimension(150, 100);
		setSize(size);
		setPreferredSize(size);
		setMinimumSize(size);
		setMaximumSize(size);
		setLayout(null);
	}
	public ImagePanel() {
		// TODO 自动生成的构造函数存根
		setLayout(null);
	}
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
}
