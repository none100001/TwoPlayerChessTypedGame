
public class Star extends Piece{
	
	public Star(){
		
	}

	@Override
	boolean valid(Box [][] box,Box prevbox, Box newbox) {
		super.board = box;
		if(boundary(newbox)){
			int dis = distance(prevbox,newbox);
			
			if(daigonal(prevbox, newbox)){
				dis--;
			}
			
			if(dis>0 && dis<=3 && (allside (prevbox,newbox))){
				return obstackle(prevbox, newbox);
			}
		}
		return false;
	}

	
}
