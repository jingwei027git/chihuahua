import java.util.Date;

import org.joda.time.DateTime;


public class test {

	public static void main(String[] args) throws Exception {
		DateTime dt = DateTime.now();
		System.err.println(dt.toDate().getTime());
		Date d = new Date();
		System.err.println(d.getTime());
	}

}
