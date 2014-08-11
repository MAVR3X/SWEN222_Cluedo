package Game;

import java.awt.Point;

public class Door {

	public final Point p1;
	public final Point p2;

	public  Door(Point a, Point b){
		p1 = a;
		p2 = b;
	}

	public boolean canMove(Point a, Point b){

		if(a.equals(b)){
			return true;
		}

		if(a.equals(p1) || a.equals(p2)){
			if(b.equals(p1) || b.equals(p2)){
				return true;
			}
		}

		return false;
	}


}
