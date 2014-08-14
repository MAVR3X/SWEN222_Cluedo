package Game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Tokens.PlayerToken;
import Tokens.Token;


public class CluedoCanvas extends JPanel{

	private static int BOARD_HEIGHT = 600;
	private static int WINDOW_WIDTH;
	private static int WINDOW_HEIGHT;
	private static int SQUARE_SIZE;
	
	private final Board board;
	
	public CluedoCanvas(int width, int height, Board board){
		super();
		this.board = board;
		WINDOW_WIDTH  = width;
		WINDOW_HEIGHT = height;
		//SQUARE_SIZE = Math.min(width/26, height/27);
		SQUARE_SIZE = 30;
		
//		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();		
//		HashSet<String> availableNames = new HashSet();
//
//		for(String name : env.getAvailableFontFamilyNames()) {
//			availableNames.add(name);			
//		}
//
//		for(String pf : preferredFonts) {
//			if(availableNames.contains(pf)) {				
//				font = new Font(pf,Font.BOLD,16);
//				break;
//				
//			}
//		}
		
		font = new Font("Arial",Font.BOLD,16);
		
		
		
	}
	
//	private static final String[] preferredFonts = {"Arial","Courier New","Times New Roman"};
	private Font font;	
	
	@Override
	public void paint(Graphics g){
		super.paint(g);

	//Basic frame coloring
	//drawBackgrounds(g);
	g.setColor(new Color(0, 105,61));
	g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
	// Draw the board
		for(int x=0; x<26; x++){
			for(int y=0; y<27; y++){
				if(boardImage[x][y]!=-1){
					g.drawImage(squares[boardImage[x][y]], x*SQUARE_SIZE, y*SQUARE_SIZE, null, null);
				}
			}
		}
		
	drawLabels(g);
		
	drawTokens(g);
	
		
	}
	
	private void drawLabels(Graphics g) {
		g.setColor(Color.black);
		
		HashMap <TextAttribute, Object> attrs = new HashMap<TextAttribute, Object>();
		attrs.put(TextAttribute.TRACKING, 0.5);
		font = font.deriveFont(attrs);
		
		g.setFont(font);
		
		g.drawString("KITCHEN", 60, 150);
		g.drawString("BALL ROOM", 310, 180);
		g.drawString("DINING ROOM", 50, 420);
		g.drawString("BILLIARD", 595, 340);
		g.drawString("ROOM", 625, 370);
		g.drawString("LIBRARY", 590, 530);
		g.drawString("LOUNGE", 80, 700);
		g.drawString("HALL", 357, 660);
		g.drawString("STUDY", 610, 730);
		
		attrs = new HashMap<TextAttribute, Object>();
		attrs.put(TextAttribute.TRACKING, 0.1);
		font = font.deriveFont(attrs);
		
		g.setFont(font);
		
		g.drawString("CONSERVATORY", 585, 130);
	}

	private static int DICE_SIZE = 40;
	private static int CARD_HEIGHT = 50;
	private static int CARD_WIDTH = 30;
	private static int CARD_X = 300;
	private static int NAME_HEIGHT = 30;
	
	
	
	/**
	 * Draw backgrounds of interface sections
	 * @param g graphics item to draw on
	 */
	private void drawBackgrounds(Graphics g) {
		//Draw Board square
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, BOARD_HEIGHT);
		
		//Interface square
		g.setColor(new Color(50,50,50));
		g.fillRect(0, BOARD_HEIGHT, WINDOW_WIDTH, BOARD_HEIGHT);
		
		
		//Draw dice area
		g.setColor(Color.WHITE);
		g.fillRect(DICE_SIZE,  BOARD_HEIGHT + DICE_SIZE, DICE_SIZE * 5, DICE_SIZE * 2);
		
		//Play name section
		g.setColor(new Color(25,25,25));
		g.fillRect(0,  BOARD_HEIGHT - (NAME_HEIGHT/ 2), WINDOW_WIDTH, NAME_HEIGHT);
		
		//Cards
		g.setColor(Color.WHITE);
		int cardSectionHeight = BOARD_HEIGHT + ((WINDOW_HEIGHT - BOARD_HEIGHT) / 2) - (CARD_HEIGHT / 2); 
		g.fillRect(CARD_X, cardSectionHeight, CARD_WIDTH * 5, CARD_HEIGHT + 5);
		
		
	}
	
	private static final String IMAGE_PATH = "Images/";
	
	private static final Image Tile = loadImage("Tile.png");
	private static final Image Room_Tile = loadImage("RmT.png");
	private static final Image Room_Wall = loadImage("RmW.png");
	private static final Image Room_Wall_Corner = loadImage("RmC.png");
	private static final Image Room_Wall_Point = loadImage("RmI.png");
	private static final Image Room_Window = loadImage("RmWi.png");
	private static final Image Room_Window_Corner = loadImage("RmWiC.png");
	private static final Image Room_Door = loadImage("RmD.png");
	private static final Image Room_Wall_Door_1 = loadImage("WD1.png");
	private static final Image Room_Wall_Door_2 = loadImage("WD2.png");
	private static final Image Hall_1 = loadImage("H1.png");
	private static final Image Hall_2 = loadImage("H2.png");
	private static final Image External_Wall = loadImage("EW.png");
	private static final Image External_Wall_Corner = loadImage("EWC.png");
	private static final Image External_Wall_Point = loadImage("EWO.png");
	private static final Image External_Wall_U = loadImage("EWU.png");
	private static final Image External_Double_Point = loadImage("DP.png");
	private static final Image Transparent = loadImage("N.png");
	
	private static final Image[] squares = {
		Tile,								//0 - Common floor tile
		Room_Tile,							//1 - Common room tile
		Room_Wall,							//2 - Room wall Left
		rotate(Room_Wall, 90),				//3 - Room wall Up
		rotate(Room_Wall, 180),				//4 - Room wall Right
		rotate(Room_Wall, -90),				//5 - Room wall Down
		Room_Wall_Corner,					//6 - Room wall Corner Left and Up
		rotate(Room_Wall_Corner, 90),		//7 - Room wall Corner Up and Right
		rotate(Room_Wall_Corner, 180),		//8 - Room wall Corner Right and Down
		rotate(Room_Wall_Corner, -90),		//9 - Room wall Corner Down and Left
		Room_Wall_Point,					//10 - Room wall Point Left and Up
		rotate(Room_Wall_Point, 90),		//11 - Room wall Point Up and Right
		rotate(Room_Wall_Point, 180),		//12 - Room wall Point Right and Down
		rotate(Room_Wall_Point, -90),		//13 - Room wall Point Down and Left
		Room_Window,						//14 - Room window Left
		rotate(Room_Window, 90),			//15 - Room window Up
		rotate(Room_Window, 180),			//16 - Room window Right
		rotate(Room_Window, -90),			//17 - Room window Down
		Room_Window_Corner,					//18 - Room window Corner Up and Right
		rotate(Room_Window_Corner, 90),		//19 - Room window Corner Right and Down
		Hall_1,								//20 - Lounge hallway
		rotate(Hall_1, 90),					//21 - Kitchen hallway
		rotate(Hall_1, 180),				//22 - Conservatory hallway
		Hall_2,								//23 - Study hallway
		External_Wall,						//24 - External wall Left
		rotate(External_Wall, 90),			//25 - External wall Up
		rotate(External_Wall, 180),			//26 - External wall Right
		rotate(External_Wall, -90),			//27 - External wall Down
		External_Wall_Corner,				//28 - External wall Corner Left and Up
		rotate(External_Wall_Corner, 90),	//29 - External wall Corner Up and Right
		rotate(External_Wall_Corner, 180),	//30 - External wall Corner Right and Down
		rotate(External_Wall_Corner, -90),	//31 - External wall Corner Down and Left
		External_Wall_Point,				//32 - External wall Point Left and Up
		rotate(External_Wall_Point, 90),	//33 - External wall Point Up and Right
		rotate(External_Wall_Point, 180),	//34 - External wall Point Right and Down
		rotate(External_Wall_Point, -90),	//35 - External wall Point Down and Left
		External_Wall_U,					//36 - External wall U Down
		rotate(External_Wall_U, 90),		//37 - External wall U Left
		rotate(External_Wall_U, -90),		//38 - External wall U Right
		rotate(External_Wall_U, 180),		//39 - External wall U up (Normal U shape)
		External_Double_Point,				//40 - External Double point left
		rotate(External_Double_Point, 90),	//41 - External Double point up
		rotate(External_Double_Point, 180),	//42 - External Double point Right
		rotate(External_Double_Point, -90),	//43 - External Double point down
		Transparent							//44 - Transparent Tile
	};
	
	
	int [][] boardImage = loadBoardImage("boardImage.txt");
	
	
	
	
	/**
	 * Load an image from the file system, using a given filename.
	 * 
	 * @param filename
	 * @return
	 */
	public static Image loadImage(String filename) {
		// using the URL means the image loads when stored
		// in a jar or expanded into individual files.
		//java.net.URL imageURL = CluedoCanvas.class.getResource(IMAGE_PATH + filename);

		java.net.URL imageURL = CluedoCanvas.class.getResource(IMAGE_PATH
				+ filename);

		try {
			Image img = ImageIO.read(imageURL);
			return img;
		} catch (IOException e) {
			// we've encountered an error loading the image. There's not much we
			// can actually do at this point, except to abort the game.
			throw new RuntimeException("Unable to load image: " + filename);
		}
	}

	

	/**
	 * Rotate an image a given number of degrees.
	 * @param src
	 * @param angle
	 * @return
	 */
	public static Image rotate(Image src, double angle) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);		
		BufferedImage img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = img.createGraphics();
		g.rotate(Math.toRadians(angle), width/2, height/2);	
		g.drawImage(src,0,0,width,height,null);
		g.dispose();
		return img;
	}
	
	/**
	 * Loads a 2D array of integers from a file
	 * @param filename
	 * @return 2D array of int
	 */
	private int[][] loadBoardImage(String filename){
		
		int[][] boardImage = new int[26][27];
		
		try{
			FileReader fr = new FileReader(filename);		
			BufferedReader br = new BufferedReader(fr);
			String line;
		
			for(int y=0; y<27; y++){
				line = br.readLine();
				String[] values = line.split("\t");
				for(int x=0; x<26; x++){
					
					boardImage[x][y] = Integer.parseInt(values[x]);
				}
			}
		}
		catch(IOException e){
			System.out.println("I/O error: " + e.getMessage());
			System.exit(1);
		}
		
		return boardImage;
	}
	
	private void drawTokens(Graphics g){
		
		
	}
	
	private void drawToken(Token t, Graphics g){
		
	}
	
}
