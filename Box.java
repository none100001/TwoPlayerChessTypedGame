
public class Box {
	int player;
	Piece p;
	int x,y;
	
	public Box(Box box){
		
		this.player = box.player;
		this.p = box.p;
		this.x = box.x;
		this.y = box.y;

	}
	
	
	public Box(int player,Piece p,int x,int y){
		this.player = player;
		this.p = p;
		this.x = x;
		this.y = y;
	}
}
