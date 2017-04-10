
public abstract class Piece {
	
	Box [][] board;
	String dir;
	public Piece(String dir){
		this.dir = dir;
	}
	
	public Piece(){
		
	}
	
	abstract boolean valid(Box [][] box,Box prevbox, Box newbox);
	
	
	int distance(Box prevbox,Box newBox){
		return Math.abs(prevbox.x-newBox.x)+Math.abs(prevbox.y-newBox.y);
	}
	
	boolean horizontal(Box prevbox,Box newBox){
		if(prevbox.x == newBox.x) return true;
		return false;
	}
	
	boolean vertical(Box prevbox,Box newBox){
		if(prevbox.y == newBox.y) return true;
		return false;
	}
	
	boolean daigonal(Box prevbox,Box newBox){
		int d1 = Math.abs(prevbox.x-newBox.x);
		int d2 = Math.abs(prevbox.y-newBox.y);
		if(d1 == d2) return true;
		return false;
	}
	
	
	boolean obstackle(Box prevbox, Box newbox){
		int x1 = prevbox.x;
		int y1 = prevbox.y;
		
		int x2 = newbox.x;
		int y2 = newbox.y;
		
		while(true){
		
			if(x2>x1){
				x1++;
			}
			else if(x2<x1){
				x1--;
			}
			
			if(y2>y1){
				y1++;
			}
			else if(y2<y1){
				y1--;
			}
			
			
			if(x1 == x2 && y1 == y2 && (prevbox.player != newbox.player)){
				return true;
			}
			
			if(board[x1][y1].player != -1){
				return false;
			}
		
		}

	}
	
	
	boolean boundary(Box newbox){
		int x = newbox.x;
		int y = newbox.y;
		
		if(x>=0 && x<8 && y>=0 && y<8){
			return true;
		}
		return false;
	}
	
	
	
	boolean allside(Box p,Box n){
		return ( horizontal(p,n) || vertical(p,n) || daigonal(p,n) );
	}
	
	
	
		
}
