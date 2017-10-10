package announcer.logic;

public class AnnouncerImpl implements Announcer {
	private boolean firstTime = true;

	@Override
	public String getMessage() {
		String message = "Welcome";
		if (firstTime) {
			firstTime = false;
		} 
		else {
			message = message + " again";
		}
		message = message + "!";
		
		return message;
	}

}
