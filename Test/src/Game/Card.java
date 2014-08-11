package Game;

public class Card {
	private static enum Type {Character, Weapon, Room};
	private static enum Character{NULL,Miss_Scarlett, Colonel_Mustard, Mrs_White, The_Reverend_Green, Mrs_Peacock,Professor_Plum}
	private static enum WeaponType {NULL,Candlestick, Dagger, Lead_Pipe, Revolver, Rope, Spanner}
	private static enum Room {NULL,Kitchen, Ball_Room, Conservator, Billiard_Room, Library, Study, Hall, Lounge, DiningRoom}

	public Type type;
	public Character character;
	public WeaponType weaponType;
	public Room room;

	public Card(int typ, int chr, int wep, int rom){
		type = Card.Type.values()[typ];


	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Card other = (Card) obj;
		if (character != other.character)
			return false;
		if (room != other.room)
			return false;
		if (type != other.type)
			return false;
		if (weaponType != other.weaponType)
			return false;
		return true;
	}

}


