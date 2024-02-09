package GameSaver;

import java.io.Serializable;

public class GameCharacter implements Serializable {

	private static final long serialVersionUID = 1L;

	int power;
	String type;
	String[] weapons;
	
	public GameCharacter(int p, String t, String[] w) {
		power = p;
		type = t;
		weapons = w;
	}
	
	public int getPower() {
		return power;
	}
	
	public String getType() {
		return type;
	}
	
	public String getWeapons() {
		String weaponList = "";
		for (String s : weapons) {
			weaponList += s.toString() + " ";
		}
		return weaponList;
	}
}
