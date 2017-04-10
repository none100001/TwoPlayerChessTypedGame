import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

  
public class UI{
	
	Button [][] buttons = new Button[8][5];
	public static final int blueplayer = 2;
	public static final int blackplayer = 1;
	public static final int noplayer = -1;
	public static int PLAYER = blackplayer;
	public static Icon blankicon,baup,badown,blup,bldown;
	public static Box prevBox = null;
	Board board = new Board();
	Blank blank = new Blank();
	
	JButton a = new JButton("TURN PLAYER BLACK");
	JButton b = new JButton("RESTART");
	
	String path;
	
	

	public void start(){
		JFrame f = new JFrame();  
		
		Path workingDirectory=Paths.get(".").toAbsolutePath();
	    
		path = workingDirectory+"";
		path = path.replace("\\","\\\\");
		path = path.replace(".","");
		
		baup = new ImageIcon(path+"src\\blackarrowup.png");
		badown = new ImageIcon(path+"src\\blackarrowdown.png");
		blup = new ImageIcon(path+"src\\bluearrowup.png");
		bldown = new ImageIcon(path+"src\\bluearrowdown.png");
		
		
		Piece [] topbottom = {new Star(),new Cross(),new Heart(),new Cross(),new Star()};
		Piece [] secondline = {blank,new BlueArrow("down"),new BlueArrow("down"),new BlueArrow("down"),blank};
		Piece [] sixthline = {blank,new BlackArrow("up"),new BlackArrow("up"),new BlackArrow("up"),blank};
		
		
		String [] array = {"star.png","cross.png","daimond.png","cross.png","star.png"};
		String [] solders = {"blank.png","arrowup.png","arrowup.png","arrowup.png","blank.png"};
		String [] solders2 = {"blank.png","arrowdown.png","arrowdown.png","arrowdown.png","blank.png"};
		
		for(int c = 0;c<8;c++){
			for(int d = 0;d<5;d++){
				
				if(c == 0){
				
					ImageIcon icon = new ImageIcon(path+"src\\blue"+array[d]);
					buttons[c][d] = new Button(icon);
						
					board.y[c][d] = new Box(blueplayer,topbottom[d],c,d);
					
					buttons[c][d].addActionListener(new Button(board.y[c][d]));
					f.add(buttons[c][d]);	
					
				}
				else if(c == 1){
					
					ImageIcon icon = new ImageIcon(path+"src\\blue"+solders2[d]);
					buttons[c][d] = new Button(icon);
					
					if(secondline[d] instanceof Blank){
						board.y[c][d] = new Box(noplayer,secondline[d],c,d);
					}
					else{
						board.y[c][d] = new Box(blueplayer,secondline[d],c,d);
					}
					
					buttons[c][d].addActionListener(new Button(board.y[c][d]));
					f.add(buttons[c][d]);
					
				}
				
				else if(c == 6){
					
					ImageIcon icon = new ImageIcon(path+"src\\black"+solders[d]);
					buttons[c][d] = new Button(icon);
					
					if(sixthline[d] instanceof Blank){
						board.y[c][d] = new Box(noplayer,sixthline[d],c,d);
					}
					else{
						board.y[c][d] = new Box(blackplayer,sixthline[d],c,d);
					}
					
					buttons[c][d].addActionListener(new Button(board.y[c][d]));
					f.add(buttons[c][d]);
				}
				
				else if(c == 7){
					
					ImageIcon icon = new ImageIcon(path+"src\\black"+array[d]);
					buttons[c][d] = new Button(icon);
					
					board.y[c][d] = new Box(blackplayer,topbottom[d],c,d);
					buttons[c][d].addActionListener(new Button(board.y[c][d]));
					f.add(buttons[c][d]);
				}
				
				else{
					
					ImageIcon icon = new ImageIcon(path+"src\\blank.png");
					blankicon = icon;
					buttons[c][d] = new Button(icon);
					
					board.y[c][d] = new Box(noplayer,blank,c,d);
					
					buttons[c][d].addActionListener(new Button(board.y[c][d]));
					
					f.add(buttons[c][d]);
				}
				
			}
		}
		
		
		a.setPreferredSize(new Dimension(376,40));
	    f.add(a);
	    
	    b.setPreferredSize(new Dimension(376,40));
	    b.addActionListener(new Reset());
	    f.add(b);
	      
	    f.setLayout(new FlowLayout(FlowLayout.LEADING));  
	  
	    f.setSize(400,575);  
	    f.setVisible(true);
	}
	
	
	
	
	
	public void setAllbuttondisable(){
		for(int c = 0;c<8;c++){
			for(int d = 0;d<5;d++){
				buttons[c][d].setEnabled(false);
			}
		}
	}
	
	
	
	class Reset implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			start();
			
		}
		
	}
	
	
	
	class Button extends JButton implements ActionListener{

		Box box;
		
		public Button(Icon icon){
			super(icon);
		}
		
		
		public Button(Box box){
			this.box = new Box(box.player,box.p,box.x,box.y);
		}

		
		@Override
		public void actionPerformed(ActionEvent event) {
			//System.out.println(box.x);
			
			if(PLAYER == box.player){
				prevBox = new Box(box.player,box.p,box.x,box.y);
			}
			else if(prevBox != null){
				if(prevBox.p.valid(board.y,prevBox,box)){
					
					boolean ok = true;
					
				
					if(box.x == 0 || box.x == 7){
						if(prevBox.p instanceof BlackArrow){
							ok = false;
							if(prevBox.p.dir.equals("up")){
								prevBox.p.dir = "down";
								buttons[box.x][box.y].setIcon(badown);
							}
							else{
								prevBox.p.dir = "up";
								buttons[box.x][box.y].setIcon(baup);
							}
						}
						else if(prevBox.p instanceof BlueArrow){
							ok = false;
							if(prevBox.p.dir.equals("up")){
								prevBox.p.dir = "down";
								buttons[box.x][box.y].setIcon(bldown);
							}
							else{
								prevBox.p.dir = "up";
								buttons[box.x][box.y].setIcon(blup);
							}
						}
					}
					
					
					

					
					Icon previcon = buttons[prevBox.x][prevBox.y].getIcon();
					
					Box oldbox = new Box(board.y[prevBox.x][prevBox.y]);
					
					
					Box newBox = new Box(oldbox.player,oldbox.p,box.x,box.y);
					
					board.y[box.x][box.y] = newBox; 
					
					
					board.y[prevBox.x][prevBox.y] = new Box(noplayer,new Blank(),prevBox.x,prevBox.y);
					
					
					for( ActionListener al : buttons[box.x][box.y].getActionListeners() ) {
						buttons[box.x][box.y].removeActionListener( al );
				    }
					
					for( ActionListener al : buttons[prevBox.x][prevBox.y].getActionListeners() ) {
						buttons[prevBox.x][prevBox.y].removeActionListener( al );
				    }
					
					
					buttons[box.x][box.y].addActionListener(new Button(newBox));
					buttons[prevBox.x][prevBox.y].addActionListener(new Button(new Box(noplayer,blank,prevBox.x,prevBox.y)));
					
					if(ok)buttons[box.x][box.y].setIcon(previcon);
					
					buttons[prevBox.x][prevBox.y].setIcon(blankicon);
					
					if(box.p instanceof Heart){
						if(PLAYER == blueplayer){
							a.setText("!!!PLAYER BLUE WIN!!!");
						}
						else{
							a.setText("!!!PLAYER BLACK WIN!!!");
						}
						
						setAllbuttondisable();
					}
					
					else{

						if(PLAYER == blueplayer) {
							PLAYER = blackplayer;
							a.setText("TURN PLAYER BLACK");
						}
						else{
							PLAYER = blueplayer;
							a.setText("TURN PLAYER BLUE");
						}
					}
					
				
					prevBox = null;
				}
			}
			
		}

	}
	
	
	
	

	public static void main(String[] args) {  
		
		UI a = new UI();
		a.start();
	}
 
} 