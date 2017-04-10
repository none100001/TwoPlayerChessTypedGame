
public class Cross extends Piece{
	
	public Cross(){
		
	}

	@Override
	boolean valid(Box[][] box, Box prevbox, Box newbox) {
		super.board = box;
		if(boundary(newbox)){
			if(daigonal (prevbox,newbox)){
				return obstackle(prevbox, newbox);
			}
		}
		return false;
	}


	

}
