import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.sound.sampled.*;

//창 하나만 제거하는 dispose() 메소드 -> https://msource.tistory.com/3 참조
public class GameOver extends JFrame{

    Player2 user;    
	ArrayList<String> rank;
	int score;

    public GameOver(Player2 user , int score , ArrayList<String> rank){

        this.user = user;
        this.rank = rank;
		this.score = score;

        setTitle("Game Over");		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container c = getContentPane();
		c.setLayout(new BorderLayout());	
		printList(c);
        JLabel scoreLabel = new JLabel("점 수 : " + Integer.toString(score));
		JButton regame = new JButton("다시하기");
		c.add(scoreLabel,BorderLayout.CENTER);
		c.add(regame,BorderLayout.SOUTH);		
		regame.setSize(50,20);

		setSize(400,300);	
		setVisible(true);		

		regame.addActionListener(new MyActionListener());

    }

    class MyActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            JButton b = (JButton) e.getSource();
            if(b.getText().equals("다시하기")){
				new RhythmGame7(user);
				dispose();
			}
        }

    }

	//614.p
	public void printList(Container c){

		String[] r = rank.toArray(new String[rank.size()]);
		JList<String> rankList = new JList<String>(r);
		rankList.setVisibleRowCount(5);
		rankList.setFixedCellWidth(300);
		c.add(new JScrollPane(rankList) , BorderLayout.NORTH); // 스크롤 기능 추가	
		
	}
}
