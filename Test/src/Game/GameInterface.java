package Game;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameInterface extends javax.swing.JFrame implements
		ActionListener, MouseListener {
	private static int FRAME_WIDTH = 600;
	private static int FRAME_HEIGHT = 800;
	
	private CluedoCanvas canvas;

	public GameInterface() {
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
		setVisible(true);

		addMenu();
		addCanvas();
		addInterface();

	}

	
	private void addInterface() {
		JPanel gui = new JPanel();
		gui.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		gui.setBackground(Color.BLACK);
		this.add(gui);
		
	}

	
	private static int CANVAS_WIDTH = FRAME_WIDTH;
	private static int CANVAS_HEIGHT = 500;
	
	/**
	 * Create canvas to draw UI components on the panel.
	 */
	private void addCanvas() {
		canvas = new CluedoCanvas(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
		canvas.setBackground(Color.WHITE);
		this.add(canvas);
	}

	/**
	 * Create menu and add options, then assign to this interface's jmenubar.
	 */
	public void addMenu() {
		JMenu fileMenu = new JMenu("File");

		JMenuItem menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		fileMenu.add(menuItem);

		JMenu gameMenu = new JMenu("Game");

		JMenuBar menu = new JMenuBar();
		menu.add(fileMenu);
		menu.add(gameMenu);

		this.setJMenuBar(menu);
	}

	/**
	 * Override of standard actionperformed to allow for jmenu items to prompt custom actions.
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {

		// User wishes to exit, prompt for confirmation with
		// JOptionPanel/JDialog
		if (arg0.getActionCommand().equals("Exit")) {

			int input = JOptionPane.showOptionDialog(null,
					"Are you sure you wish to exit?", "Are you a quitter?",
					JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.INFORMATION_MESSAGE, null, null, null);
			if (input == JOptionPane.OK_OPTION) {
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}