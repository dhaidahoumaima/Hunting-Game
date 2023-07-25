
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Point;

import javax.swing.JPanel;

/**
 *
 * @author oumai
 */
public class Board {

	private final int size;
	private Field[][] board;
	private Fugitive fugitive = new Fugitive();
	private Hunter hunter1 = new Hunter();
	private Hunter hunter2 = new Hunter();
	private Hunter hunter3 = new Hunter();
	private Hunter hunter4 = new Hunter();

	public Board(int size) {
		this.size = size;

		board = new Field[this.size][this.size];
		for (int i = 0; i < this.size; ++i) {
			for (int j = 0; j < this.size; ++j) {
				board[i][j] = new Field();
			}
		}
		this.setPlayers();
	}

	public Field getField(int x, int y) {
		return board[x][y];
	}

	public void setField(int x, int y, boolean empty, String type) {
		this.getField(x, y).setType(type);
		this.getField(x, y).setEmpty(empty);
	}

	public Field getField(Point point) {
		int x = (int) point.getX();
		int y = (int) point.getY();

		return getField(x, y);
	}

	public Field[][] getBoard() {
		return this.board;
	}

	public int getSize() {
		return size;
	}

	public void setPlayers() {
		hunter1 = new Hunter(0, 0);
		hunter2 = new Hunter(0, size - 1);
		hunter3 = new Hunter(size - 1, 0);
		hunter4 = new Hunter(size - 1, size - 1);
		fugitive = new Fugitive(size / 2, size / 2);

		this.setField(0, 0, false, "hunter");
		this.setField(0, size - 1, false, "hunter");
		this.setField(size - 1, 0, false, "hunter");
		this.setField(size - 1, size - 1, false, "hunter");
		this.setField((size) / 2, (size) / 2, false, "fugitive");
	}

	public boolean move(String position, int x, int y, String turn) {

		if (position == "right") {
			if (y < this.getSize() - 1) {
				if (getField(x, y + 1).isEmpty() && getField(x, y).getType() == turn) {
					setField(x, y, true, null);
					setField(x, y + 1, false, turn);

					fugitive.setPosition(x, y + 1);
					return true;
				}
			}
		}

		if (position == "left") {
			if (y != 0) {
				if (getField(x, y - 1).isEmpty() && getField(x, y).getType() == turn) {
					setField(x, y, true, null);
					setField(x, y - 1, false, turn);

					fugitive.setPosition(x, y - 1);
					return true;
				}
			}
		}

		if (position == "up") {
			if (x != 0) {
				if (getField(x - 1, y).isEmpty() && getField(x, y).getType() == turn) {
					setField(x, y, true, null);
					setField(x - 1, y, false, turn);

					fugitive.setPosition(x - 1, y);
					return true;
				}
			}
		}

		if (position == "down") {
			if (x < this.getSize() - 1) {
				if (getField(x + 1, y).isEmpty() && getField(x, y).getType() == turn) {
					setField(x, y, true, null);
					setField(x + 1, y, false, turn);

					fugitive.setPosition(x + 1, y);
					return true;
				}
			}
		}

		return false;
	}
	
	public boolean isFugitiveLost(Fugitive fugitive) {
		int x=fugitive.getX();
		int y=fugitive.getY();
		
		if(x==0 && y==0) {
			if(!getField(x, y+1).isEmpty() && !getField(x+1, y).isEmpty()){
				return true;
			}
		}
		
		if(x==0 && y==getSize()-1) 
			if(!getField(x, y-1).isEmpty() && !getField(x+1, y).isEmpty()){
				return true;
		}
		
		if(x==0 && y!=getSize()-1 && y!=0) {
			if(!getField(x, y-1).isEmpty() && !getField(x, y+1).isEmpty() && !getField(x+1, y).isEmpty())
				return true;
		}
		
		if(y==0 && x==getSize()-1) {
			if(!getField(x-1, y).isEmpty() && !getField(x, y+1).isEmpty())
				return true;
		}
		
		if(y==getSize()-1 && x==getSize()-1) {
			if(!getField(x-1, y).isEmpty() && !getField(x, y-1).isEmpty())
				return true;
		}
		if(x==getSize()-1 && y!=getSize()-1 && y!=0) {
			if(!getField(x, y-1).isEmpty() && !getField(x, y+1).isEmpty() && !getField(x-1, y).isEmpty())
				return true;
		}

		if(y==0 && x!=getSize()-1 && x!=0) {
			if(!getField(x+1, y).isEmpty() && !getField(x-1, y).isEmpty() && !getField(x, y+1).isEmpty())
				return true;
		}
		if(y==getSize()-1 && x!=getSize()-1 && x!=0) {
			if(!getField(x+1, y).isEmpty() && !getField(x-1, y).isEmpty() && !getField(x, y-1).isEmpty())
			return true;
		}
			
		if(x!=0 && x!=getSize()-1 && y!=getSize()-1 && y!=0) {
			if(!getField(x+1, y).isEmpty() && !getField(x-1, y).isEmpty() && !getField(x, y-1).isEmpty() && !getField(x, y+1).isEmpty())
				return true;
		}
	
		return false;
	
	}
}
