package announcer.logic;

public class AnnouncerBest implements Announcer {
	private boolean firstTime = true;

	@Override
	public String getMessage() {
		String message = "Hej";
		if (firstTime) {
			firstTime = false;
		} 
		else {
			message = message + " med dig";
		}
		message = message + "!";
		
		return message;
	}

}
