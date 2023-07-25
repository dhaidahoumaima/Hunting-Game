
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oumai
 */
public class BoardGUI {
	private JButton[][] buttons;
	private Board board;
	private JPanel boardPanel;
	private JLabel TurnsLable;
	private Fugitive fugitive;
	private JFrame frame;

	private ArrayList<Point> points;

	private Random random = new Random();
	private int clickNum = 0;
	private String turn = "fugitive";
	private Image imageF, imageH;

	public BoardGUI(int boardSize,JFrame frame) throws IOException {
		this.frame=frame;
		fugitive = new Fugitive(boardSize / 2, boardSize / 2);
		board = new Board(boardSize);
		boardPanel = new JPanel();
		points = new ArrayList<>();
		boardPanel.setLayout(new GridLayout(board.getSize(), board.getSize()));
		buttons = new JButton[board.getSize()][board.getSize()];
		imageF = ImageIO.read(getClass().getResource("ressources/fugitive1.png"));
		imageH = ImageIO.read(getClass().getResource("ressources/police44.png"));

		for (int i = 0; i < board.getSize(); ++i) {
			for (int j = 0; j < board.getSize(); ++j) {
				final int curRow = i;
				final int curCol = j;

				JButton button = new JButton();
				button.addActionListener(new ButtonListener(i, j));
				button.setPreferredSize(new Dimension(90, 80));
				buttons[i][j] = button;
				buttons[i][j].setBackground(Color.white);
				buttons[i][j].setBorder(new LineBorder(Color.GRAY));
				buttons[i][j].requestFocusInWindow();
				buttons[i][j].addKeyListener(enter);
				buttons[i][j].addKeyListener(new KeyAdapter() {

					@Override
					public void keyPressed(KeyEvent e) {
						switch (e.getKeyCode()) {
						case KeyEvent.VK_UP:
							if (curRow > 0) {
								if (board.move("up", curRow, curCol, turn)) {
									buttons[curRow - 1][curCol].requestFocus();
									buttons[curRow - 1][curCol].doClick();
									if (turn == "fugitive") {
										buttons[curRow - 1][curCol].setIcon(new ImageIcon(imageF));
										fugitive.setPosition(curRow - 1, curCol);
									} else
										buttons[curRow - 1][curCol].setIcon(new ImageIcon(imageH));

									buttons[curRow][curCol].setIcon(null);
									turn();
									try {
										over();
									} catch (IOException e1) {
										e1.printStackTrace();
									}
								}
							}
							break;

						case KeyEvent.VK_DOWN:
							if (curRow < buttons.length - 1) {
								if (board.move("down", curRow, curCol, turn)) {
									buttons[curRow + 1][curCol].requestFocus();
									buttons[curRow + 1][curCol].doClick();
									if (turn == "fugitive") {
										buttons[curRow + 1][curCol].setIcon(new ImageIcon(imageF));
										fugitive.setPosition(curRow + 1, curCol);
									} else
										buttons[curRow + 1][curCol].setIcon(new ImageIcon(imageH));

									buttons[curRow][curCol].setIcon(null);
									turn();
									try {
										over();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}

							}
							break;

						case KeyEvent.VK_LEFT:
							if (curCol > 0) {
								if (board.move("left", curRow, curCol, turn)) {
									buttons[curRow][curCol - 1].requestFocus();
									buttons[curRow][curCol - 1].doClick();

									if (turn == "fugitive") {
										buttons[curRow][curCol - 1].setIcon(new ImageIcon(imageF));
										fugitive.setPosition(curRow, curCol - 1);
									} else
										buttons[curRow][curCol - 1].setIcon(new ImageIcon(imageH));

									buttons[curRow][curCol].setIcon(null);
									turn();
									try {
										over();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							break;

						case KeyEvent.VK_RIGHT:
							if (curCol < buttons[curRow].length - 1) {
								if (board.move("right", curRow, curCol, turn)) {
									buttons[curRow][curCol + 1].requestFocus();
									buttons[curRow][curCol + 1].doClick();

									if (turn == "fugitive") {
										buttons[curRow][curCol + 1].setIcon(new ImageIcon(imageF));
										fugitive.setPosition(curRow, curCol + 1);
									} else
										buttons[curRow][curCol + 1].setIcon(new ImageIcon(imageH));

									buttons[curRow][curCol].setIcon(null);
									turn();
									try {
										over();
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}
							}
							break;
						default:
							break;
						}
					}
				});

				boardPanel.add(button);
				points.add(new Point(i, j));
			}
		}

		buttons[0][0].setIcon(new ImageIcon(imageH));
		buttons[0][boardSize - 1].setIcon(new ImageIcon(imageH));
		buttons[boardSize - 1][0].setIcon(new ImageIcon(imageH));
		buttons[boardSize - 1][boardSize - 1].setIcon(new ImageIcon(imageH));
		buttons[boardSize / 2][boardSize / 2].setIcon(new ImageIcon(imageF));
		buttons[boardSize / 2][boardSize / 2].requestFocus();
		buttons[boardSize / 2][boardSize / 2].doClick();

		EmptyBorder border = new EmptyBorder(10, 10, 10, 10);
		LineBorder line = new LineBorder(new Color(166, 58, 67));
		CompoundBorder compound = new CompoundBorder(line, border);

		Collections.shuffle(points);
		TurnsLable = new JLabel(" ddd");
		TurnsLable.setHorizontalAlignment(JLabel.CENTER);
		TurnsLable.setText("Fugitive " + " turn");
		TurnsLable.setForeground(Color.black);
		TurnsLable.setBorder(compound);

	}

	private KeyListener enter = new KeyAdapter() {
		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				((JButton) e.getComponent()).doClick();
			}
		}
	};

	public JLabel getTurnsLable() {
		return TurnsLable;
	}

	public void turn() {
		if (turn == "hunter")
			this.turn = "fugitive";
		else
			this.turn = "hunter";

		this.clickNum++;
		TurnsLable.setText(turn + " turn");
	}

	public JPanel getBoardPanel() {
		return boardPanel;
	}

	public void over() throws IOException {
		String winner = "";
		boolean lost = false;
		if (board.isFugitiveLost(fugitive)) {
			winner = "Hunter";
			lost = true;
		}
		if (this.clickNum == board.getSize() * 4 && !board.isFugitiveLost(fugitive)) {
			winner = "Fugitive";
			lost = true;
		}
		if (lost == true) {
			System.out.println(clickNum);
			JOptionPane.showMessageDialog(null, winner, "The winner of the game is : ",
					JOptionPane.INFORMATION_MESSAGE);
			;
			frame.dispose();
			new HuntingGUI();
		}

	}

	class ButtonListener implements ActionListener {

		private int x, y;

		public ButtonListener(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

}
