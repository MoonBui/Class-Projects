import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

public class Data {

	private ArrayList<Event> list;

	public static void main(String[] args) throws IOException {

		System.out.println("STARTING");
		// This puts all the data from the file into a list
		Data data = new Data("covid-data.csv");
		PrintWriter output = new PrintWriter("output.txt");

//		Set<String> locations = data.allLocations();
//		display(output, locations);
//		display(output, data.codeCounts());
//		display(output, data.casesByDate());
//		display(output, data.mostCasesByDate());
		display(output, data.locationsSortedByCaseCount());
//		display(output, data.casesByLocation(10));
//		display(output, data.casesByLocation());
//		display(output, data.deathsByContinent());
//		display(output, data.getLocationsInThisContinent("Asia"));
//		display(output, data.allContinents());
//		display(output, data.casesByMonth());
		// See video for information about testing using the display() methods

		// You can use the display method to write your results
		// to a file. This makes it easier to view larger results.
//		Set<String> locations = data.allLocations();
//		display(output, locations);
//		display(output, data.allLocations());
//		System.out.println(data.casesByMonth());
//		display(output, data.locationsByContinent());

//		
//		Set<String> locations = data.allLocations();
//		System.out.println(locations);
//		System.out.println(locations.size());

		output.close();
		System.out.println("DONE");
	}

	// solved in video
	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allLocations() {
		Set<String> result = new TreeSet<>();
		
		//list contains all events
		for (Event e : list) {
			result.add(e.location);
		}
		
		return result;
	}

	/*
	 * Returns a set of all location names in the database
	 */
	public Set<String> allContinents() {
		Set<String> results = new TreeSet<>();
		
		for(Event e : list) {
			results.add(e.continent);
		}
		return results;
	}

	/*
	 * Returns all the locations in a particular continent. The empty set is
	 * returned if the continent does not have any locations.
	 */
	public Set<String> getLocationsInThisContinent(String continent) {
		Set<String> result = new TreeSet<>();
		
		for(Event e : list) {
			if (e.continent.equals(continent))
				result.add(e.location);
		}
		return result;
	}

	/*
	 * Returns a map keyed to a continent, where the value is the total number of
	 * deaths in that continent.
	 */
	public Map<String, Integer> deathsByContinent() {
		Map<String, Integer> result = new TreeMap<>();
		
		for (Event e : list) {
			if (!result.containsKey(e.continent))
				result.put(e.continent, 0);
			result.put(e.continent, result.get(e.continent) + e.deaths);
		}
		return result;
	}

	/*
	 * Returns a map keyed to a location, where the value is the total number of
	 * cases for that location. The map should only contain locations that had at
	 * least 1 case.
	 */
	public Map<String, Integer> casesByLocation() {
		Map<String, Integer> result = new TreeMap<>();

		for (Event e : list) {
			if (e.cases > 0) {
			if (!result.containsKey(e.location))
				result.put(e.location, 0);
			result.put(e.location, result.get(e.location) + e.cases);
			}
		}
		return result;
	}

	/*
	 * For a specified month (0 is January, 1 is February, and so on), returns a map
	 * keyed to a location, where the value is the total number of cases for that
	 * location. The map should only contain locations that had at least one case.
	 * If there are no cases for a given month, returns an empty map. NOTE: Date has
	 * a getMonth() method you can use. You may get a warning that getMonth() is a
	 * deprecated method, but that's ok.
	 */
	public Map<String, Integer> casesByLocation(int month) {
		Map<String, Integer> result = new TreeMap<>();
		
		for (Event e: list) {
			if (e.date.getMonth() == month) {
				if (e.cases > 0) {
				if (!result.containsKey(e.location)) 
					result.put(e.location, 0);
				result.put(e.location, result.get(e.location) + e.cases);
				}
			}
		}
		return result;
	}

	// solved in video
	/*
	 * Returns a map keyed to the continent, where the value for each continent is a
	 * set of all locations in that continent.
	 */
	public Map<String, Set<String>> locationsByContinent() {
		Map<String, Set<String>> result = new TreeMap<>();
		
		for (Event e : list) {
			String continent = e.continent;
			String location  = e.location;
			
			if (!result.containsKey(continent)) {
				result.put(continent, new TreeSet<String>());
			}
			
			result.get(continent).add(location);
		}
		return result;
	}

	/*
	 * Returns a map keyed to a location's 3-letter code, where the value is the
	 * total number of times that code appears in the data.
	 */
	public Map<String, Integer> codeCounts() {
		Map<String, Integer> result = new TreeMap<>();
		
		for (Event e : list) {
		if (!result.containsKey(e.abbreviation)) 
			result.put(e.abbreviation, 0);
		result.put(e.abbreviation, result.get(e.abbreviation) + 1);
		}
		
		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value is the total number of cases
	 * for that date.
	 */
	public Map<Date, Integer> casesByDate() {
		Map<Date, Integer> result = new TreeMap<>();
		
		for (Event e : list) {
			if (!result.containsKey(e.date))
				result.put(e.date, 0);
			result.put(e.date, result.get(e.date) + e.cases);
		}
		return result;
	}

	/*
	 * Returns a list of the locations in the database, sorted by the total number
	 * of cases reported for that location. The location with the fewest cases
	 * should be first, and the location with the most cases should be last.
	 */
	public List<String> locationsSortedByCaseCount() {
		List<String> result = new LinkedList<>();
		Map<String, Integer> caseByLocation = this.casesByLocation();
		Set<String> locations = this.allLocations();
		Map<Integer, Set<String>> sorted = new TreeMap<>();
		
		for (String name : locations) {
			//sorted
			if (!caseByLocation.containsKey(name)) {
				if (!sorted.containsKey(0))
					sorted.put(0, new TreeSet<>());
				sorted.get(0).add(name);
			}
			else {
				if (!sorted.containsKey(caseByLocation.get(name)))
					sorted.put(caseByLocation.get(name), new TreeSet<>());
				sorted.get(caseByLocation.get(name)).add(name);
			}
		}
		
		Set<Integer> cases = sorted.keySet();
		for (Integer number : cases) {
			for (String locaCases : sorted.get(number)) {
				result.add(locaCases);
			}
		}
		return result;
	}

	/*
	 * Returns a map keyed to the date, where the value for that date is the
	 * location (or locations) that had the highest number of cases on that date.
	 * It's possible that the set will contain only one location. But if there are
	 * ties, the set will contain more than one location.
	 */
	public Map<Date, Set<String>> mostCasesByDate() {
		Map<Date, Set<String>> result = new TreeMap<>();
		Map<Date, Integer> maxCase = new TreeMap<>();
		
			
		for (Event e : list) {
			if (!maxCase.containsKey(e.date))
				maxCase.put(e.date, e.cases);
			if (maxCase.get(e.date) < e.cases)
				maxCase.put(e.date, e.cases);
		}
		
		for (Event e : list) {
			if (!result.containsKey(e.date))
				result.put(e.date, new TreeSet<>());
			if (e.cases >= maxCase.get(e.date))
				result.get(e.date).add(e.location);
		}
		
		
		return result;
	}

	// Solved in video
	/*
	 * Returns a map keyed to a month (0=January, 1=February, and so on), where the
	 * value is the number of reported cases for that month. Only include months
	 * that are listed in the data. NOTE: Date has a getMonth() method you can use.
	 * You may get a warning that getMonth() is a deprecated method, but that's ok.
	 */
	public Map<Integer, Integer> casesByMonth() {
		Map<Integer, Integer> result = new TreeMap<>();
		
		for (Event e : list) {
			int month = e.date.getMonth();
			int cases = e.cases;
			
			if (!result.containsKey(month)) {
				result.put(month, cases);
			} else {
				result.put(month, cases + result.get(month));
			}
		}
		return result;
	}

	/********** DON'T MODIFY ANY OF THE CODE BELOW ************/

	// Reads file data into an ArrayList of Event objects.
	// Don't modify this code
	public Data(String filename) throws IOException {
		Scanner in = new Scanner(new File(filename));
		list = new ArrayList<Event>();
		in.nextLine();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		while (in.hasNextLine()) {
			String input = in.nextLine();
			String[] line = input.split(",");
			String abbreviation = line[0];
			String continent = line[1];
			String location = line[2];
			Date date = simpleDateFormat.parse(line[3], new ParsePosition(0));
			int cases = Integer.parseInt(line[4]);
			int deaths = Integer.parseInt(line[5]);
			Event d = new Event(abbreviation, continent, location, date, cases, deaths);
			list.add(d);
		}

		in.close();
	}

	/*
	 * Writes a collection (list, set) to a specified file. DON'T CHANGE THIS.
	 */
	public static <T> void display(PrintWriter output, Collection<T> items) {
		if (items == null) {
			output.println("null");
			return;
		}
		int LEN = 80;
		String line = "[";
		for (T item : items) {
			line += item.toString() + ",";
			if (line.length() > LEN) {
				output.println(line);
				line = "";
			}
		}
		output.println(line + "]");
	}

	/*
	 * Writes a map to a specified file DON'T CHANGE THIS.
	 */
	@SuppressWarnings("unchecked")
	public static <K, V> void display(PrintWriter output, Map<K, V> items) {
		if (items == null) {
			output.println("null");
			return;
		}

		for (K key : items.keySet()) {
			output.print(key + "---------->");
			Object o = items.get(key);
			if (o instanceof Collection) {
				output.println();
				display(output, (Collection<Object>) items.get(key));
			} else {
				output.println(items.get(key));
			}
		}
	}

	/*
	 * Inner class for organizing event information. DON'T CHANGE THIS.
	 */
	private class Event {
		private String abbreviation;
		private String continent;
		private String location;
		private Date date;
		private int cases;
		private int deaths;

		private Event(String abbreviation, String continent, String location, Date date, int cases, int deaths) {
			this.abbreviation = abbreviation;
			this.continent = continent;
			this.location = location;
			this.date = date;
			this.cases = cases;
			this.deaths = deaths;
		}

		@Override
		public String toString() {
			return "[" + abbreviation + "," + continent + "," + location + "," + date + "," + cases + "," + deaths
					+ "]";
		}

	}

}
