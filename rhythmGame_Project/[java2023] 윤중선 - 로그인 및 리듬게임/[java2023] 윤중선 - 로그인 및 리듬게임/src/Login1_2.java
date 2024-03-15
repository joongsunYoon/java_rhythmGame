import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//회원가입 메소드 완성
//FileWriter 사용

public class Login1_2 extends JFrame {

	JTextField id, password, name;
	HashMap<Integer, Player2> playerMap = new HashMap<Integer, Player2>();
	Player2 user = new Player2();

	public Login1_2() {

		setTitle("로그인 화면");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		GridLayout grid = new GridLayout(5, 1);

		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		grid.setVgap(5);

		Container c = getContentPane();
		c.setLayout(grid);
		JButton login = new JButton("로그인");
		JButton join = new JButton("회원가입 하기");
		id = new JTextField();
		password = new JTextField();
		c.add(new JLabel("아이디"));
		c.add(id);
		c.add(new JLabel("비밀번호"));
		c.add(password);
		c.add(p);

		login.setSize(50, 20);
		join.setSize(50, 20);
		p.add(login);
		p.add(join);

		setSize(400, 300);
		setVisible(true);

		login.addActionListener(new MyActionListener());
		join.addActionListener(new MyActionListener());

	}

	class MyActionListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("로그인")) {
				// 학생 입력된 정보 저장
				Player2 p = new Player2();
				p.id = id.getText();
				p.password = password.getText();
				login_search(p);

			} else if (b.getText().equals("회원가입 하기")) {

				goToJoinPage();

			} else if (b.getText().equals("회원가입")) {

				user.name = name.getText();
				user.id = id.getText();
				user.password = password.getText();

				Readplayerfile();
				writeFile();

			}
		}

		public void writeFile() {
			// 닉네임 중복시 재입력
			if (playerMap.containsValue(user)) {
				System.out.println("이미 등록된 정보가 있습니다. 다시 입력해주세요");
			}
			// 닉네임 중복되지 않을 경우 player파일에 정보 입력
			else {
				// https://9d4u.tistory.com/322 FileWriter 참고
				// 457.p FileWriter 참고
				// player파일에 name,id,password 순으로 저장
				File f = new File("./player.txt");
				FileWriter fw = null;
				try {
					fw = new FileWriter(f, true);
					BufferedWriter bw = new BufferedWriter(fw);
					String str = user.name + "," + user.id + "," + user.password;
					bw.newLine();
					bw.write(str);
					bw.write("");
					bw.flush();
					bw.close();

				} catch (IOException e1) {
					System.out.println("파일불러오기가 실패하였습니다.");
				}
				System.out.println("등록되었습니다.");
			}
		}

		// 회원가입 창 메소드
		public void goToJoinPage() {

			class JoinPage extends JFrame {

				public JoinPage() {

					setTitle("회원가입");

					GridLayout grid = new GridLayout(7, 1);

					grid.setVgap(5);

					Container c = getContentPane();
					c.setLayout(grid);
					JButton join = new JButton("회원가입");

					name = new JTextField();
					id = new JTextField();
					password = new JTextField();

					c.add(new JLabel("이름"));
					c.add(name);
					c.add(new JLabel("아이디"));
					c.add(id);
					c.add(new JLabel("비밀번호"));
					c.add(password);

					join.setSize(50, 20);
					c.add(join);

					setSize(400, 300);
					setVisible(true);

					join.addActionListener(new MyActionListener());

				}

			}

			new JoinPage();

		}

		public void login_search(Player2 p) {

			// player파일 불러오기
			Readplayerfile();

			// 로그인 정보 확인
			// 로그인 성공
			if (playerMap.containsValue(p)) {
				Set<Integer> set = playerMap.keySet();
				Iterator<Integer> iset = set.iterator();

				// 로그인 정보로 사용자 특정
				while (iset.hasNext()) {
					Integer num = iset.next();
					if ((p.id).equals(playerMap.get(num).id) && (p.password).equals(playerMap.get(num).password)) {
						user = p;
						user.name = playerMap.get(num).name;
						System.out.println("로그인 되었습니다.");

						new Loby(user);
						dispose();
					}
				}

			}
			// 로그인 실패
			else {
				System.out.println("아이디와 패스워드가 일치하지 않습니다.");
			}
		}

		// p.449 File FileReader 참고
		// Player파일 읽어오기
		public void Readplayerfile() {

			FileReader fin = null;
			BufferedReader bf = null;

			try {

				fin = new FileReader("./player.txt");
				bf = new BufferedReader(fin);
				String line;
				int count = 0;

				while ((line = bf.readLine()) != null) {

					String[] s = line.split(",");
					Player2 p = new Player2();
					p.name = s[0].trim();
					p.id = s[1].trim();
					p.password = s[2].trim();
					playerMap.put(count, p);
					count++;
				}

			} catch (IOException e) {
				System.out.println("파일불러오기가 실패하였습니다.");
			}

		}

	}

	public static void main(String args[]) {
		new Login1_2();
	}

}
