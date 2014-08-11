package Game;

import java.awt.Dimension;

public class Room {

	private Room hallRoom;
	private Card Weapon;
	private Card Person;
	private Dimension dimensions;
	private Board board;

	public Room(Board b){
		board = b;
	}

	public Room getHallRoom() {
		return hallRoom;
	}

	public void setHallRoom(Room hallRoom) {
		this.hallRoom = hallRoom;
	}


}
