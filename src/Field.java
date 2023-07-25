/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oumai
 */
class Field {

	private Player p;
	private boolean empty;
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Field() {
		this.empty = true;
		this.p = null;
	}

	public Field(Player p) {
		this.p = p;
		this.empty = false;
	}

	public Player getP() {
		return p;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public void setP(Player p) {
		this.p = p;
	}

}
