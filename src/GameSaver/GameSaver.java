package GameSaver;

import java.io.*;

public class GameSaver {

	public static void main(String[] args) {
		
		// Make some characters
		GameCharacter char01 = new GameCharacter(50, "Elf", new String[] {"bow", "sword", "dust"});
		GameCharacter char02 = new GameCharacter(100, "Troll", new String[] {"bare hands", "big ax"});
		GameCharacter char03 = new GameCharacter(150, "Magician", new String[] {"spells", "invisibility"});

		// use your imagination here - there should be plenty of codes for the characters to do stuffs

		//TODO we need to save object here
		try {
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("/home/margaux/java-workspace/EnterpriseJavaProgrammingW5/src/Game.ser"));
			os.writeObject(char01);
			os.writeObject(char02);
			os.writeObject(char03);
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Before we save game: ");
		System.out.println(char01.getType() + " " + char01.getPower());
		System.out.println(char02.getType() + " " + char02.getPower());
		System.out.println(char03.getType() + " " + char02.getPower());


		char01 = null;
		char02 = null;
		char03 = null;

		//TODO now, we need to restore object here
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("/home/margaux/java-workspace/EnterpriseJavaProgrammingW5/src/Game.ser"));
			GameCharacter char01R = (GameCharacter) is.readObject();
			GameCharacter char02R = (GameCharacter) is.readObject();
			GameCharacter char03R = (GameCharacter) is.readObject();
			is.close();

			System.out.println("\nAfter we save game: ");
			System.out.println(char01R.getType() + " " + char01R.getPower());
			System.out.println(char02R.getType() + " " + char02R.getPower());
			System.out.println(char03R.getType() + " " + char03R.getPower());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
