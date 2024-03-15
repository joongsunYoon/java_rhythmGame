package game_version3;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;

//마우스 클릭을 이용한 이벤트 처리

public class WhackAMoleGame1 extends JFrame {    
    
    public WhackAMoleGame1(){

        setTitle("WhackAMoleGame1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container c = getContentPane();        
	    JButton[] moles = new JButton[9]; 
        GridLayout grid = new GridLayout(3,3);
        for(int i = 0 ; i < moles.length ; i++){
            moles[i] = new JButton("mole"+i);
            c.add(moles[i]);
            moles[i].addActionListener(new MyActionListener());
        }
        c.setLayout(grid);
        grid.setVgap(10);



        setSize(400,400);
        setVisible(true);

    }

    class MyActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            JButton b = (JButton) e.getSource();

            if(b.getText().equals("mole0")){
                System.out.println("mole0");
            }else if(b.getText().equals("mole1")){
                System.out.println("mole1");
            }else if(b.getText().equals("mole2")){
                System.out.println("mole2");
            }else if(b.getText().equals("mole3")){
                System.out.println("mole3");
            }else if(b.getText().equals("mole4")){
                System.out.println("mole4");
            }else if(b.getText().equals("mole5")){
                System.out.println("mole5");
            }else if(b.getText().equals("mole6")){
                System.out.println("mole6");
            }else if(b.getText().equals("mole7")){
                System.out.println("mole7");
            }else if(b.getText().equals("mole8")){
                System.out.println("mole8");
            }

        }
    }


    public static void main(String args[]) {
		new WhackAMoleGame1();
	}
	

}
