import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

//JButton , KeyListener 작동 확인

public class RhythmGame1 extends JFrame{

	JButton d,f,j,k;

    public RhythmGame1(){ 

		setTitle("리듬게임");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grid = new GridLayout(1,4);
		JPanel panel = new JPanel();
		panel.setLayout(grid);
		Container c = getContentPane();
		c.setLayout(new BorderLayout());	
       	c.addKeyListener(new MyKeyListener());		
		c.add(panel , BorderLayout.SOUTH);
		setSize(400,1000);	
		setVisible(true);		
		
        //컨텐트팬이 키 입력을 받을수 있도록 포커스 강제 지정
		c.setFocusable(true);
		c.requestFocus();
	    
    }

	class MyKeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e){
			char keycode = e.getKeyChar();
			
			if(keycode == 'd'){
				System.out.println("d");
			}else if(keycode == 'f'){
				System.out.println("f");
			}else if(keycode == 'j'){
				System.out.println("j");
			}else if(keycode == 'k'){
				System.out.println("k");
			}

		}
		public void keyReleased(KeyEvent e){
			System.out.println("keyReleased");
		}
	}

    public static void main(String args[]) {
		new RhythmGame1();
	}
	
}
