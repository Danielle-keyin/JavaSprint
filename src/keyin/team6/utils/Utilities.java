package keyin.team6.utils;

public class Utilities {

	public static boolean isQuitChoice(String choice) {
		if (choice == null || choice.isEmpty()) {
			return false;
		}
		String trimmedChoice = choice.trim().toLowerCase();
		
		if(trimmedChoice.equals("0")) return true;
		if(trimmedChoice.equals("quit")) return true;
		if(trimmedChoice.equals("exit")) return true;
		if(trimmedChoice.equals("q")) return true;
		if(trimmedChoice.equals("x")) return true;
		
		return false;
	}
	
}
