import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//게임 로비화면 구현

public class Loby extends JFrame {

	Player2 user;

    public Loby(Player2 user){    
		
		System.out.println(user.name);
		this.user = user;

		setTitle("게임 로비화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grid = new GridLayout(2,1);

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		grid.setVgap(5);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());	
        
		JButton wgame = new JButton("whack-a-mole game");
		JButton rgame = new JButton("Rhythm game");
		c.add(panel);
		
		wgame.setSize(50,20);
		rgame.setSize(50,20);
		panel.add(wgame);
		panel.add(rgame);

		setSize(400,300);	
		setVisible(true);		

		wgame.addActionListener(new MyActionListener());
		rgame.addActionListener(new MyActionListener());
		
	    
    }

    class MyActionListener implements ActionListener{

        public void actionPerformed(ActionEvent e){

            JButton b = (JButton) e.getSource();

            if(b.getText().equals("whack-a-mole game")){
				new WhackAMoleGame1();
				dispose();
            }else if(b.getText().equals("Rhythm game")){
				new RhythmGame7(user);
				dispose();
			}

        }

    }

}
