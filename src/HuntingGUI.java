
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oumai
 */
public class HuntingGUI {
	private JFrame frame;
	private BoardGUI boardGUI;

	// by default the size is 3*3
	private final int INITIAL_BOARD_SIZE = 3;

	public HuntingGUI() throws IOException {
		// the name of the frame is hunting game
		frame = new JFrame("Hunting Game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		boardGUI = new BoardGUI(INITIAL_BOARD_SIZE,frame);
		frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
		frame.getContentPane().add(boardGUI.getTurnsLable(), BorderLayout.SOUTH);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu gameMenu = new JMenu("Hunting");
		menuBar.add(gameMenu);
		JMenu newMenu = new JMenu("New");
		gameMenu.add(newMenu);
		int[] boardSizes = new int[] { 3, 5, 7 };
		for (int boardSize : boardSizes) {
			JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);
			newMenu.add(sizeMenuItem);
			sizeMenuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.getContentPane().remove(boardGUI.getBoardPanel());
					frame.getContentPane().remove(boardGUI.getTurnsLable());
					try {
						boardGUI = new BoardGUI(boardSize,frame);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.getContentPane().add(boardGUI.getBoardPanel(), BorderLayout.CENTER);
					frame.getContentPane().add(boardGUI.getTurnsLable(), BorderLayout.SOUTH);

					frame.pack();
				}
			});
		}
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		gameMenu.add(exitMenuItem);
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		frame.pack();
		frame.setVisible(true);
	}

}
