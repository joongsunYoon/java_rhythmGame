package game_version2;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

//tile class를 제작하고 Thread를 사용
//tile이 내려오는것을 구현

public class RhythmGame3_1 extends JFrame{

	Image line = new ImageIcon("images/line.png").getImage();
	Image background = new ImageIcon("images/back.jpg").getImage();	
	Image image_node1 = new ImageIcon("images/background8.png").getImage();
	Image image_node2 = new ImageIcon("images/background8.png").getImage();
	Image image_node3 = new ImageIcon("images/background8.png").getImage();
	Image image_node4 = new ImageIcon("images/background8.png").getImage();
	Image noded = new ImageIcon("images/node.png").getImage();
	Image nodef = new ImageIcon("images/node.png").getImage();
	Image nodej = new ImageIcon("images/node.png").getImage();
	Image nodek = new ImageIcon("images/node.png").getImage();
	
	boolean doubleBuffer = false;

	int width ,height;	


    public RhythmGame3_1(){ 

		setTitle("리듬게임");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TileThread tileThread = new TileThread();
		tileThread.start();

		Container c = getContentPane();
		c.setLayout(null);	
       	c.addKeyListener(new MyKeyListener());		
		
		setSize(512, 1024);	
		setVisible(true);	
		setResizable(false);// 창크기 고정

        //컨텐트팬이 키 입력을 받을수 있도록 포커스 강제 지정
		c.setFocusable(true);
		c.requestFocus();
	    
    }

	class MyKeyListener extends KeyAdapter{

		boolean pressed_d,pressed_f,pressed_j,pressed_k;

		//key를 눌렀을때
		public void keyPressed(KeyEvent e){

			char keycode = e.getKeyChar();
			
			if(keycode == 'd' && !pressed_d){				
				pressed_d = true;
				noded = new ImageIcon("images/pressed_node.png").getImage();
			}else if(keycode == 'f' && !pressed_f){
				pressed_f = true;
				nodef = new ImageIcon("images/pressed_node.png").getImage();
			}else if(keycode == 'j' && !pressed_j){
				pressed_j = true;
				nodej = new ImageIcon("images/pressed_node.png").getImage();
			}else if(keycode == 'k' && !pressed_k){
				pressed_k = true;
				nodek = new ImageIcon("images/pressed_node.png").getImage();
			}

		}

		public void keyReleased(KeyEvent e){

			char keycode = e.getKeyChar();
			
			//key를 눌렀다 땠을때
			if(keycode == 'd' && pressed_d){
				pressed_d = false;
				noded = new ImageIcon("images/node.png").getImage();
			}else if(keycode == 'f' && pressed_f){
				pressed_f = false;
				nodef = new ImageIcon("images/node.png").getImage();
			}else if(keycode == 'j' && pressed_j){
				pressed_j = false;
				nodej = new ImageIcon("images/node.png").getImage();
			}else if(keycode == 'k' && pressed_k){
				pressed_k = false;
				nodek = new ImageIcon("images/node.png").getImage();
			}


		}
	}

	@Override
	public void paint(Graphics g) {
		
		if(!doubleBuffer){
			paintComponents(g);
			Image back = new ImageIcon("images/back.jpg").getImage();
			Image line = new ImageIcon("images/line.png").getImage();


			g.drawImage(back , 0, 0, getWidth(),  getHeight()-128, this);
			g.drawImage(image_node1 , 0, 0, getWidth()/4,  getHeight()-128, this);
			g.drawImage(line , getWidth()/4 , 0, 1,  getHeight()-128, this);
			g.drawImage(image_node2 , getWidth()/4 , 0, getWidth()/4,  getHeight()-128, this);			
			g.drawImage(line , getWidth()/4*2 , 0, 1,  getHeight()-128, this);
			g.drawImage(image_node3 , getWidth()/4*2, 0, getWidth()/4,  getHeight()-128, this);			
			g.drawImage(line , getWidth()/4*3 , 0, 1,  getHeight()-128, this);
			g.drawImage(image_node4 , getWidth()/4*3, 0, getWidth()/4,  getHeight()-128, this);	
			
			g.drawImage(noded , 0, getHeight()-128 , getWidth()/4,  128, this);
			g.drawImage(nodef , getWidth()/4, getHeight()-128, getWidth()/4, 128, this);
			g.drawImage(nodej , getWidth()/4*2, getHeight()-128, getWidth()/4, 128, this);
			g.drawImage(nodek , getWidth()/4*3, getHeight()-128, getWidth()/4, 128, this);
			
			doubleBuffer = true;

		}else{
			
			update(g);
			
		}
		repaint();
	}
	
	
	@Override
	public void update(Graphics g){	
		
		Image img = createImage(getWidth() , 128);
		Graphics img_g = img.getGraphics();
		paintComponents(img_g);
		img_g.drawImage(noded , 0, 0 , getWidth()/4,  128, this);
		img_g.drawImage(nodef , getWidth()/4, 0, getWidth()/4, 128, this);
		img_g.drawImage(nodej , getWidth()/4*2, 0, getWidth()/4, 128, this);
		img_g.drawImage(nodek , getWidth()/4*3, 0, getWidth()/4, 128, this);
		g.drawImage(img , 0, getHeight() - 128, this);
	}

	//tile 객체생성 696.p
	//Thread 사용
	class TileThread extends Thread{

		int time = 0 ;
		int speed = 2000;

		Tile tile = new Tile(width/4 , 0);

		@Override
		public void run(){
			while(true){
				time++;
				//check timer 
				System.out.println(time);
				try{
					//tile_speed를 높히면 속도가 올라가기위한 나누기
					Thread.sleep(100000/speed);
					
				}catch(InterruptedException e){
					System.out.println("쓰레드에서 문제가 생겼습니다.");
					return;
				}
				
				tile.draw(getGraphics());		
				repaint();	
			}
		}
	}

	class Tile{

		Image tile = new ImageIcon("images/tile.png").getImage();
		int x, y;
		int tile_speed = 40; // 20 ~ 40

		public Tile(int x , int y ){
			this.x = x;
			this.y = y;
		}

		public void draw(Graphics g){

			y++;				
			g.drawImage(background , 0, 0, width,  height-128, null);	
			g.drawImage(image_node1 , 0, 0, width/4,  height-128, null);
			g.drawImage(line , width/4 , 0, 1,  height-128, null);
			g.drawImage(image_node2 , width/4 , 0, width/4,  height-128, null);			
			g.drawImage(line , width/4*2 , 0, 1,  height-128, null);
			g.drawImage(image_node3 , width/4*2, 0, width/4,  height-128, null);			
			g.drawImage(line , width/4*3 , 0, 1,  height-128, null);
			g.drawImage(image_node4 , width/4*3, 0, width/4,  height-128, null);				
			g.drawImage(tile, width/4 , y * tile_speed , width/4 , 32 , null);	
			
		}

	}

    public static void main(String args[]) {
		new RhythmGame3_1();
	}
	
}
