	package game_version3;
	import java.awt.*;
	import java.awt.event.*;
	import java.io.*;
	import java.util.*;
	import javax.swing.*;
	import javax.sound.sampled.*;

	//ranking 파일에 점수 삽입

	public class RhythmGame7 extends JFrame{

		int score = 0 ;
		Player2 user;
		ArrayList<String> rank = new ArrayList<String>();

		TileMap tiles = new TileMap(100);
		TileThread tileThread;
		private Clip clip;
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
		boolean pause = false;

		int width = 512 ; 
		int height = 1024; 

		public RhythmGame7(Player2 user){ 
			System.out.println(user.name);

			this.user = user;
			readRank();

			setTitle("리듬게임");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			loadAudio("music/Liszt_Paganini_-_Etude_No.3_La_campanella.wav");
			//Audio 시작
			clip.start();
			
			//tileMap 초기화 후 사용
			tiles.init();

			tileThread = new TileThread();
			tileThread.start();
			

			Container c = getContentPane();
			c.setLayout(null);
			c.addKeyListener(new MyKeyListener());		
			
			setSize(width, height);		
			setVisible(true);
			setResizable(false); // 창크기 

			//컨텐트팬이 키 입력을 받을수 있도록 포커스 강제 지정
			c.setFocusable(true);
			c.requestFocus();
			
		}

		//key관련 메소드
		class MyKeyListener extends KeyAdapter{

			boolean pressed_d,pressed_f,pressed_j,pressed_k;

			//key를 눌렀을때
			public void keyPressed(KeyEvent e){

				char keycode = e.getKeyChar();
				
				if(keycode == 'd' && !pressed_d){				
					pressed_d = true;
					noded = new ImageIcon("images/pressed_node.png").getImage();
					tiles.getScore('d');
				}else if(keycode == 'f' && !pressed_f){
					pressed_f = true;
					nodef = new ImageIcon("images/pressed_node.png").getImage();
					tiles.getScore('f');
				}else if(keycode == 'j' && !pressed_j){
					pressed_j = true;
					nodej = new ImageIcon("images/pressed_node.png").getImage();
					tiles.getScore('j');
				}else if(keycode == 'k' && !pressed_k){
					pressed_k = true;
					nodek = new ImageIcon("images/pressed_node.png").getImage();
					tiles.getScore('k');
				}else if(keycode == 'p'){
					pause = !pause;
					if(pause){		
						clip.stop();	
						new SoundSetting();								
					}else{
						play();
					}
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

		// 배경 그리기
		public void paintScreen(Graphics g){		
			
			Image img = createImage(width , height - 128);
			Graphics img_g = img.getGraphics();
			paintComponents(img_g);
			img_g.drawImage(background , 0, 0, width,  height-128, this);	
			img_g.drawImage(image_node1 , 0, 0, width/4,  height-128, this);
			img_g.drawImage(line , width/4 , 0, 1,  height-128, this);
			img_g.drawImage(image_node2 , width/4 , 0, width/4,  height-128, this);			
			img_g.drawImage(line , width/4*2 , 0, 1,  height-128, this);
			img_g.drawImage(image_node3 , width/4*2, 0, width/4,  height-128, this);			
			img_g.drawImage(line , width/4*3 , 0, 1,  height-128, this);
			img_g.drawImage(image_node4 , width/4*3, 0, width/4,  height-128, this);			
			g.drawImage(img , 0, 0, this);

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

		//789.p 29번줄부터 참고
		public void loadAudio(String filename){
			try{
				clip = AudioSystem.getClip();
				File audioFile = new File(filename);
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				clip.open(audioStream);
			}catch(LineUnavailableException e){			
				e.printStackTrace();
			}catch(UnsupportedAudioFileException e){
				e.printStackTrace();
			}catch(IOException e){
				e.printStackTrace();
			}
		}

		//p.449 File FileReader 참고
		//Player파일 읽어오기
		//"이름,점수" 
		public void readRank(){			
	    	
	    	FileReader fin = null;
	    	BufferedReader bf = null;
	    	
	    	try {

	    		fin = new FileReader("./ranking.txt");
	    		bf = new BufferedReader(fin);
	    	    String line;
	    		
	    		while((line = bf.readLine()) != null) {	    			
					rank.add(line);
	    		}

				System.out.println("읽기 성공");
	    		
	    	}catch( IOException e) {
	    		System.out.println("파일불러오기가 실패하였습니다.");
	    	}   	
		}

		//ranking 작성
		public void writeRank(){
				
			File f = new File("./ranking.txt");
			FileWriter fw = null;						
			try{
				fw = new FileWriter(f, false);
				BufferedWriter bw = new BufferedWriter(fw);	
				for(int i = 0 ; i < rank.size() ; i++){
					String str = rank.get(i);					
					bw.write(str);
					bw.write("");
					bw.newLine();	
					bw.flush();
				}

				
				bw.close();
				System.out.println("삽입성공");
			}catch(IOException e1){
   				System.out.println("파일넣기가 실패하였습니다.");
			}	
				
		}

		//랭킹순으로 집어넣기
		public void sortRank(String name , int score){
			
			if(rank.isEmpty()){
				rank.add(name+","+score);
			}else{

				//내 기록 초기화
				for(int i = 0 ; i < rank.size() ; i++){

					String str = rank.get(i);
					String[] s = str.split(",");
					int num = Integer.valueOf(s[1]);
					if(s[0].equals(name)){
					rank.remove(i);
					}
				}
				//나보다 낮은사람위에 삽입
				for(int i = 0 ; i < rank.size() ; i++){
					String str = rank.get(i);
					String[] s = str.split(",");
					int num = Integer.valueOf(s[1]);		

					//점수가 내점수보다 낮을경우 그 위에 삽입
					if(num < score){
						rank.add(i,name + "," +score);
						break;
					}else if(i == rank.size()-1){				
						rank.add(name + "," +score);
					}				
				}	
			}	
		}

		//tile 객체생성 696.p
		//Thread 사용
		class TileThread extends Thread{

			int time = 0 ;
			int speed = 10;

			@Override
			public void run(){
				while(true){
					time++;
					try{
						//tile_speed를 높히면 속도가 올라가기위한 나누기
						Thread.sleep(50);
						
					}catch(InterruptedException e){
						System.out.println("게임종료");
						return;
					}

					playGame();

					//10초에 게임종료
					if(time >= 10 * 20){	
						sortRank(user.name , score);
						clip.stop();
						writeRank();					
						interrupt(); //710.p 참조	
						new GameOver(user ,score , rank);						
						dispose();
					}					
				}
			}
		}

		class TileMap{
			
			//Tile로 Map을 만들 list 생성
			ArrayList<Tile> tileMap = new ArrayList<Tile>();
			int size;

			public TileMap(int size){
				this.size = size;
			}

			//Random함수로 초기화
			public void init(){
				Random r = new Random();
				for(int i = 0 ; i < size ; i++){						
					tileMap.add(new Tile( width/4 * r.nextInt(4) , -i * 10 - 30 ));
				}
				
			}

			//배경 그린 후 tile 배치
			public void draw(Graphics g){
				paintScreen(g);
				for(int i = 0 ; i < tileMap.size() ; i++){							
					tileMap.get(i).draw(g);				
				}
			}

			public void outOfMap(){
				try{
					Tile tiledumy;			
					tiledumy =  tileMap.get(0);
					if(tiledumy.y * tiledumy.tile_speed > height){
						tileMap.remove(0);	
						size -= 1;				
						System.out.println("MISS");
					}
				}catch(IndexOutOfBoundsException e){
					System.out.println("game over");
				}
				
				
			}

			//키를 눌렀을때 점수를 더할 메소드
			public void getScore(char key){
				Tile tiledumy = tileMap.get(0);

				if(key == tiledumy.getLine()){
					//같을 경우 해당 높이에 따라 출력후 타일 삭제
					//-90 ~ 0
					if(tiledumy.y * tiledumy.tile_speed > height - 128 + 38){
						score++;
						System.out.println("Late");
						tileMap.remove(0);
					}
					//-38 ~ 38
					else if(tiledumy.y * tiledumy.tile_speed > height - 128 - 38 ){
						score += 2;
						System.out.println("Good");
						tileMap.remove(0);
					//64 ~ 28
					}else if(tiledumy.y * tiledumy.tile_speed > height - 128 - 64){
						score++;
						System.out.println("Early");
						tileMap.remove(0);
					}
				}			
				
			}

		}

		class Tile{

			Image tile = new ImageIcon("images/tile.png").getImage();
			int x, y;
			int tile_speed = 40; // 20 ~ 40

			//Tile 생성자
			public Tile(int x , int y ){
				this.x = x;
				this.y = y;
			}		

			// y값 증가후 그리기
			public void draw(Graphics g){
				y++;
				g.drawImage(tile, x , y * tile_speed , width/4 , 32 , null);				
			}

			//해당 타일의 라인을 출력
			public char getLine(){
				if(x == 0){
					return 'd';
				}else if(x == width / 4){
					return 'f';
				}else if(x ==  width / 4 * 2){
					return 'j';
				}else if(x ==  width / 4 * 3){
					return 'k';
				}else{
					return 'a';
				}
			}

		}

		class SoundSetting extends JFrame{
			
			public SoundSetting(){
				setTitle("소리설정");

				Container c = getContentPane();
				c.setLayout(new FlowLayout());
				JSlider sound = new JSlider(JSlider.HORIZONTAL,0,100,50);
				sound.setPaintTrack(true);
				c.add(sound);
				setSize(300,100);	
				setVisible(true);	

			}

		}

		//728.p 참조
		synchronized public void playGame(){
			if(pause){
				try{
				wait();
				}catch(InterruptedException e1){}
			}
			tiles.outOfMap();
			tiles.draw(getGraphics());
			notify();
		}	

		//728.p 참조	
		synchronized public void play(){	
			notify();
			clip.start();
		}

		public static void main(String args[]) {
		Player2 user = new Player2("yoon" , "202001934" , "011019");
		new RhythmGame7(user);

	}
		
}
