package it.polimi.ingsw.cg_2.model.player;

import static it.polimi.ingsw.cg_2.model.player.CharacterType.*;

public enum CharacterRank {

	CAPTAIN(HUMAN),
	PILOT(HUMAN),
	PSYCHOLOGIST(HUMAN),
	SOLDIER(HUMAN),
	FIRST(ALIEN),
	SECOND(ALIEN),
	THIRD(ALIEN),
	FOURTH(ALIEN);

	private CharacterType defaultType;

	CharacterRank(CharacterType defaultType) {
		this.defaultType = defaultType;
	}
	
	public CharacterType getDefaultType(){
		
		return defaultType;
	}

}
