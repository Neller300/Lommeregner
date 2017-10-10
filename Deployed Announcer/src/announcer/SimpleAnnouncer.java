package announcer;

import announcer.logic.Announcer;
import announcer.logic.AnnouncerImpl;

public class SimpleAnnouncer {

	public static void main(String[] args) {
		Announcer announcer = new AnnouncerImpl();

		System.out.println(announcer.getMessage());
		System.out.println(announcer.getMessage());
	}

}
