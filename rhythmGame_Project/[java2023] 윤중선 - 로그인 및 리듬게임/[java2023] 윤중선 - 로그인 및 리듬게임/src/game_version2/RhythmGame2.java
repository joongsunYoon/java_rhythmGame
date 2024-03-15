package game_version2;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

//더블버퍼링 문제 해결 https://m.blog.naver.com/lghlove0509/221009877738
//Image , ImageIcon을 활용한 게임화면 꾸미기

public class RhythmGame2 extends JFrame {

	Image image_node1 = new ImageIcon("images/background8.png").getImage();
	Image image_node2 = new ImageIcon("images/background8.png").getImage();
	Image image_node3 = new ImageIcon("images/background8.png").getImage();
	Image image_node4 = new ImageIcon("images/background8.png").getImage();
	Image noded = new ImageIcon("images/node.png").getImage();
	Image nodef = new ImageIcon("images/node.png").getImage();
	Image nodej = new ImageIcon("images/node.png").getImage();
	Image nodek = new ImageIcon("images/node.png").getImage();

	boolean doubleBuffer = false;

	public RhythmGame2() {

		setTitle("리듬게임");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(null);
		c.addKeyListener(new MyKeyListener());

		setSize(512, 1024);
		setVisible(true);
		setResizable(false);// 창크기 고정

		// 컨텐트팬이 키 입력을 받을수 있도록 포커스 강제 지정
		c.setFocusable(true);
		c.requestFocus();

	}

	class MyKeyListener extends KeyAdapter {

		boolean pressed_d, pressed_f, pressed_j, pressed_k;

		// key를 눌렀을때
		public void keyPressed(KeyEvent e) {

			char keycode = e.getKeyChar();

			if (keycode == 'd' && !pressed_d) {
				pressed_d = true;
				noded = new ImageIcon("images/pressed_node.png").getImage();
				System.out.println("pressed_d");
			} else if (keycode == 'f' && !pressed_f) {
				pressed_f = true;
				nodef = new ImageIcon("images/pressed_node.png").getImage();
				System.out.println("pressed_f");
			} else if (keycode == 'j' && !pressed_j) {
				pressed_j = true;
				nodej = new ImageIcon("images/pressed_node.png").getImage();
				System.out.println("pressed_j");
			} else if (keycode == 'k' && !pressed_k) {
				pressed_k = true;
				nodek = new ImageIcon("images/pressed_node.png").getImage();
				System.out.println("pressed_k");
			}

		}

		public void keyReleased(KeyEvent e) {

			char keycode = e.getKeyChar();

			// key를 눌렀다 땠을때
			if (keycode == 'd' && pressed_d) {
				pressed_d = false;
				noded = new ImageIcon("images/node.png").getImage();
				System.out.println("released_d");
			} else if (keycode == 'f' && pressed_f) {
				pressed_f = false;
				nodef = new ImageIcon("images/node.png").getImage();
				System.out.println("released_f");
			} else if (keycode == 'j' && pressed_j) {
				pressed_j = false;
				nodej = new ImageIcon("images/node.png").getImage();
				System.out.println("released_j");
			} else if (keycode == 'k' && pressed_k) {
				pressed_k = false;
				nodek = new ImageIcon("images/node.png").getImage();
				System.out.println("released_k");
			}
		}
	}

	// 656.P 참조
	// 더블버퍼링 참조 https://m.blog.naver.com/lghlove0509/221009877738
	@Override
	public void paint(Graphics g) {

		if (!doubleBuffer) {
			Image back = new ImageIcon("images/back.jpg").getImage();
			Image line = new ImageIcon("images/line.png").getImage();

			g.drawImage(back, 0, 0, getWidth(), getHeight() - 128, this);
			g.drawImage(image_node1, 0, 0, getWidth() / 4, getHeight() - 128, this);
			g.drawImage(line, getWidth() / 4, 0, 1, getHeight() - 128, this);
			g.drawImage(image_node2, getWidth() / 4, 0, getWidth() / 4, getHeight() - 128, this);
			g.drawImage(line, getWidth() / 4 * 2, 0, 1, getHeight() - 128, this);
			g.drawImage(image_node3, getWidth() / 4 * 2, 0, getWidth() / 4, getHeight() - 128, this);
			g.drawImage(line, getWidth() / 4 * 3, 0, 1, getHeight() - 128, this);
			g.drawImage(image_node4, getWidth() / 4 * 3, 0, getWidth() / 4, getHeight() - 128, this);

			g.drawImage(noded, 0, getHeight() - 128, getWidth() / 4, 128, this);
			g.drawImage(nodef, getWidth() / 4, getHeight() - 128, getWidth() / 4, 128, this);
			g.drawImage(nodej, getWidth() / 4 * 2, getHeight() - 128, getWidth() / 4, 128, this);
			g.drawImage(nodek, getWidth() / 4 * 3, getHeight() - 128, getWidth() / 4, 128, this);

			doubleBuffer = true;

		} else {

			update(g);
		}

		repaint();
	}

	@Override
	public void update(Graphics g) {

		Image img = createImage(getWidth(), 128);
		Graphics img_g = img.getGraphics();
		paintComponents(img_g);
		img_g.drawImage(noded, 0, 0, getWidth() / 4, 128, this);
		img_g.drawImage(nodef, getWidth() / 4, 0, getWidth() / 4, 128, this);
		img_g.drawImage(nodej, getWidth() / 4 * 2, 0, getWidth() / 4, 128, this);
		img_g.drawImage(nodek, getWidth() / 4 * 3, 0, getWidth() / 4, 128, this);
		g.drawImage(img, 0, getHeight() - 128, this);
	}

	public static void main(String args[]) {
		new RhythmGame2();
	}

}
