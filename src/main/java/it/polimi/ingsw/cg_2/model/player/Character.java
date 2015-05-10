package it.polimi.ingsw.cg_2.model.player;

import it.polimi.ingsw.cg_2.model.map.Sector;

public class Character {

	private Sector position;
	private boolean alive;
	private CharacterType type;
	private CharacterRank rank;

	public Character(Sector position, CharacterRank rank, CharacterType type) {
		this.position = position;
		this.rank = rank;
		this.type = type;
		alive = true;
	}

	public Character(Sector position, CharacterRank rank) {
		this(position, rank, rank.getDefaultType());
	}

	public Sector getPosition() {
		return position;
	}

	public void setPosition(Sector position) {
		this.position = position;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public CharacterType getType() {
		return type;
	}

	public void setType(CharacterType type) {
		this.type = type;
	}

	public CharacterRank getRank() {
		return rank;
	}

}
