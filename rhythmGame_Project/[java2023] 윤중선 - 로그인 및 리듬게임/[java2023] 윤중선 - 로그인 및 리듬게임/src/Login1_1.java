import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login1_1 extends JFrame{
	
	JTextField id,password;
	HashMap<Integer,Player1> playerMap = new HashMap<Integer,Player1>();
	Player1 user = new Player1();
	
	public Login1_1() {

		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grid = new GridLayout(5,1);
		GridLayout grid_search = new GridLayout(1,2);

		JPanel p = new JPanel();
		p.setLayout(grid_search);
		grid.setVgap(5);

		Container c = getContentPane();
		c.setLayout(grid);	
		JButton login = new JButton("로그인");
		JButton join = new JButton("회원가입");
		id = new JTextField();
		password = new JTextField();
		c.add(new JLabel("아이디"));
		c.add(id);
		c.add(new JLabel("비밀번호"));
		c.add(password);
		c.add(p);
		
		login.setSize(50,20);
		join.setSize(50,20);
		p.add(login);
		p.add(join);

		setSize(400,300);	
		setVisible(true);		

		login.addActionListener(new MyActionListener());
		join.addActionListener(new MyActionListener());
		
	}
	
	class MyActionListener implements ActionListener{
       
		public void actionPerformed(ActionEvent e){
            JButton b =(JButton) e.getSource();
            if(b.getText().equals("로그인")){
                // 학생 입력된 정보 저장
                Player1 p = new Player1();
                p.id = id.getText();
                p.password = password.getText();      
                login_search(p);
               
            	}else if(b.getText().equals("회원가입")){
            		
            		goToJoinPage();
            		
            }
        }
		
		public void goToJoinPage() {
			
				
		}
		
		public void login_search(Player1 p){	
			
		 	//로그인 정보 확인
		 	//로그인 성공
			Readplayerfile();
			
		    if(playerMap.containsValue(p)){
		       	Set<Integer> set = playerMap.keySet();
				Iterator<Integer> iset = set.iterator();
				
				//로그인 정보로 사용자 특정
				while(iset.hasNext()){	
					Integer num = iset.next();
					if((p.id).equals(playerMap.get(num).id) && (p.password).equals(playerMap.get(num).password)) {
						System.out.println("로그인 되었습니다.");
					}
				}
					
		    }
		    //로그인 실패
		    else{
		        System.out.println("아이디와 패스워드가 일치하지 않습니다.");
		    }
		}
		
		//p.449 File FileReader 참고
		//Player파일 읽어오기
		public void Readplayerfile() {
	    	
	    	FileReader fin = null;
	    	BufferedReader bf = null;
	    	
	    	try {

	    		fin = new FileReader("./player.txt");
	    		bf = new BufferedReader(fin);
	    	    String line;
				int count = 0;
	    		
	    		while((line = bf.readLine()) != null) {
	    			
	    			String[] s = line.split(",");
	    			Player1 p = new Player1();
	    			p.id = s[1].trim();
	    			p.password = s[2].trim();
					playerMap.put(count,p);
					count++;
					
	    		}
	    		
	    	}catch( IOException e) {
	    		System.out.println("파일불러오기가 실패하였습니다.");
	    	}
	    	
	    	
	    }
		
	}
	
	public static void main(String args[]) {
		new Login1_1();
	}
	
	
	
	
}

