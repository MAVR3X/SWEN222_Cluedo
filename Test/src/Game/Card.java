package Game;

import java.awt.Image;

/**
 * Wrapper class for card types. Non-unique fields utilise static enums.
 *
 * Each type prefixed with NULL enum to ensure only a single state is possible while
 * ignoring Type enum
 */
public class Card {
	public static enum Type {
		Character, Weapon, Room
	};

	public static enum Character {
		NULL, Miss_Scarlett, Colonel_Mustard, Mrs_White, The_Reverend_Green, Mrs_Peacock, Professor_Plum
	}

	public static enum Weapon {
		NULL, Candlestick, Dagger, Lead_Pipe, Revolver, Rope, Spanner
	}

	public static enum Room {
		NULL, Kitchen, Ball_Room, Conservatory, Billiard_Room, Library, Study, Hall, Lounge, DiningRoom
	}

	public Type type;
	public Character character;
	public Weapon weaponType;
	public Room room;

	public Image cardImage;

	public Card(int typ, int chr, int wep, int rom) {
		type = Card.Type.values()[typ];
		character = Character.values()[chr];
		weaponType = Weapon.values()[wep];
		room = Room.values()[rom];
		cardImage = getCardImage();
	}

	/**
	 * Returns Character count + 1 (NULL)
	 *
	 * @since count + 1, start selection from 1 to ignore NULL)
	 * @return
	 * @author isaac
	 */
	public static int charCount() {
		return Character.values().length;
	}

	/**
	 * Returns Weapon count + 1 (NULL)
	 *
	 * @since count + 1, start selection from 1 to ignore NULL)
	 * @return
	 * @author isaac
	 */
	public static int wepCount() {
		return Weapon.values().length;
	}

	/**
	 * Returns Room count + 1 (NULL)
	 *
	 * @since count + 1, start selection from 1 to ignore NULL)
	 * @return
	 * @author isaac
	 */
	public static int roomCount() {
		return Room.values().length;
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

	private Image getCardImage() {

		if (type == Type.Character) {

			if (character == Character.Colonel_Mustard) {
				return CluedoCanvas.loadImage("colonelmustard.png");
			} else if (character == Character.Miss_Scarlett) {
				return CluedoCanvas.loadImage("mrsscarlett.png");
			} else if (character == Character.Mrs_Peacock) {
				return CluedoCanvas.loadImage("mrspeacock.png");
			} else if (character == Character.Mrs_White) {
				return CluedoCanvas.loadImage("mrswhite.png");
			} else if (character == Character.Professor_Plum) {
				return CluedoCanvas.loadImage("professorplum.png");
			} else if (character == Character.The_Reverend_Green) {
				return CluedoCanvas.loadImage("reverendgreen.png");
			}
		}
		if (type == Type.Weapon) {

			if (weaponType == Weapon.Candlestick) {
				return CluedoCanvas.loadImage("candlestick.png");
			} else if (weaponType == Weapon.Dagger) {
				return CluedoCanvas.loadImage("dagger.png");
			} else if (weaponType == Weapon.Lead_Pipe) {
				return CluedoCanvas.loadImage("leadpipe.png");
			} else if (weaponType == Weapon.Revolver) {
				return CluedoCanvas.loadImage("revolver.png");
			} else if (weaponType == Weapon.Rope) {
				return CluedoCanvas.loadImage("rope.png");
			}else if (weaponType == Weapon.Spanner) {
				return CluedoCanvas.loadImage("spanner.png");
			}
		}

		if (type == Type.Room) {

			if (room == Room.Ball_Room) {
				return CluedoCanvas.loadImage("ballroom.png");
			} else if (room == Room.Billiard_Room) {
				return CluedoCanvas.loadImage("billiardroom.png");
			} else if (room == Room.Conservatory) {
				return CluedoCanvas.loadImage("conservatory.png");
			} else if (room == Room.DiningRoom) {
				return CluedoCanvas.loadImage("diningroom.png");
			} else if (room == Room.Hall) {
				return CluedoCanvas.loadImage("hall.png");
			} else if (room == Room.Kitchen) {
				return CluedoCanvas.loadImage("kitchen.png");
			} else if (room == Room.Library) {
				return CluedoCanvas.loadImage("library.png");
			} else if (room == Room.Lounge) {
				return CluedoCanvas.loadImage("lounge.png");
			} else if (room == Room.Study) {
				return CluedoCanvas.loadImage("study.png");
			}
		}


		return null;
	}

	@Override
	public String toString() {
		return "Card [type=" + type + ", character=" + character
				+ ", weaponType=" + weaponType + ", room=" + room + "]";
	}

}
