
public class BlueArrow extends Piece{
	
	public BlueArrow(String dir){
		super(dir);
	}

	@Override
	boolean valid(Box [][] box,Box prevbox, Box newbox) {
		super.board = box;
		if(boundary(newbox)){
			int dis = distance(prevbox,newbox);
			if(dis>0 && dis<=2 && vertical(prevbox,newbox)){
				if(dir.equals("up") && prevbox.x>newbox.x){
					return obstackle(prevbox,newbox);
				}
				else if(dir.equals("down") && prevbox.x<newbox.x){
					return obstackle(prevbox,newbox);
				}
				else{
					return false;
				}
			}
		}
		return false;
	}

}
