
import java.util.*;

public class Example5_11 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap hm = new HashMap();
		hm.put("John Doe", new Double(3434.34));
		hm.put("John Doe1", new Double(434.34));
		hm.put("John Doe2", new Double(344.34));
		hm.put("John Doe3", new Double(34.34));
		hm.put("John Doe4", new Double(34.134));

		Set set = hm.entrySet();

		Iterator i = set.iterator();

		while (i.hasNext()) {
			Map.Entry me = (Map.Entry) i.next();
			System.out.print(me.getKey());
			System.out.println(me.getValue());
		}
		System.out.println();
		double bal = ((Double) hm.get("John Doe")).doubleValue();
		hm.put("John Doe", new Double(bal + 10000));
		System.out.println("John Doe 的现金现在为" + hm.get("John Doe"));
	}

}
