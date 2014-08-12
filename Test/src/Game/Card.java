package Game;

public class Card {
	public static enum Type {Character, Weapon, Room};
	public static enum Character{NULL,Miss_Scarlett, Colonel_Mustard, Mrs_White, The_Reverend_Green, Mrs_Peacock,Professor_Plum}
	public static enum Weapon {NULL,Candlestick, Dagger, Lead_Pipe, Revolver, Rope, Spanner}
	public static enum Room {NULL,Kitchen, Ball_Room, Conservator, Billiard_Room, Library, Study, Hall, Lounge, DiningRoom}

	public Type type;
	public Character character;
	public Weapon weaponType;
	public Room room;

	public Card(int typ, int chr, int wep, int rom){
		type = Card.Type.values()[typ];
		character = Character.values()[chr];
		weaponType = Weapon.values()[wep];
		room = Room.values()[rom];
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



	@Override
	public String toString() {
		return "Card [type=" + type + ", character=" + character
				+ ", weaponType=" + weaponType + ", room=" + room + "]";
	}

}


