package Tokens;

import java.awt.Color;

public class PlayerToken implements Token{

	Color c;
	
	public PlayerToken(){
		
	}

	@Override
	public Color getColor() {
		return c;
	}
}
