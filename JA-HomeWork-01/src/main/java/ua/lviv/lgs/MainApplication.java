package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MainApplication {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Movie [] movieArray = {new Movie("First test", new Time(0, 30)),new Movie("Second test", new Time(0, 30)),
				new Movie("Third test", new Time(0, 30)),new Movie("Fourth test", new Time(0, 10)),
				new Movie("a1", new Time(0, 30)), new Movie("a2", new Time(0, 30)), new Movie("a3", new Time(0, 30)), new Movie("a4", new Time(0, 30))};
		
		
		Seance s = new Seance(new Movie("First test", new Time(0, 30)), new Time(10, 00));
		Seance s1 = new Seance(new Movie("Second test", new Time(0, 30)), new Time(12, 0));
		Seance s2 = new Seance(new Movie("Third test", new Time(0, 30)), new Time(13, 0));
		Seance s3 = new Seance(new Movie("Fourth test", new Time(0, 10)), new Time(13, 35));

		ArrayList<Movie> moviesLibrary = new ArrayList<>(Arrays.asList(movieArray));


		Cinema cinema = new Cinema(moviesLibrary, new Time(9, 0), new Time(23, 0));

		cinema.addSeance(s, "MONDAY");
		cinema.addSeance(s1, "Monday");
		cinema.addSeance(s2, "Monday");
		cinema.addSeance(s3, "Monday");
		cinema.addSeance(s, "wednesday");
		cinema.addSeance(s1, "wednesday");
		cinema.addSeance(s2, "wednesday");
		cinema.addSeance(s3, "wednesday");
		cinema.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(11, 00)), "Monday");
		cinema.addSeance(new Seance(new Movie("a2", new Time(0, 30)), new Time(14, 00)), "Monday");
		cinema.addSeance(new Seance(new Movie("a3", new Time(0, 30)), new Time(12, 30)), "saturday");
		cinema.addSeance(new Seance(new Movie("a2", new Time(0, 30)), new Time(10, 00)), "Tuesday");
		cinema.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(11, 00)), "Tuesday");
		cinema.addSeance(new Seance(new Movie("a3", new Time(0, 30)), new Time(15, 00)), "Tuesday");
		cinema.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(10, 00)), "friday");
		cinema.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(11, 00)), "friday");
		cinema.addSeance(new Seance(new Movie("a3", new Time(0, 30)), new Time(21, 00)), "friday");
		cinema.addSeance(new Seance(new Movie("a2", new Time(0, 30)), new Time(12, 00)), "friday");

		System.out.println(cinema);


		while (true) {
			menu();
			switch (sc.nextInt()) {
			case 1: {
				System.out.println("Enter movie title");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("Enter movie lenght in minute");
				int movieLenght = sc.nextInt();
				cinema.addMovie(new Movie(title, new Time(0, movieLenght)));

				break;
			}

			case 2: {
				System.out.println("Enter movie title");
				sc.nextLine();
				String title = sc.nextLine();
				System.out.println("Enter day");
				String day = sc.next();
				System.out.println("Enter seance time hour min");
				int hour = sc.nextInt();
				int min = sc.nextInt();

				cinema.addSeance(new Seance(cinema.getMovie(title), new Time(hour, min)), day);

				break;
			}

			case 3: {
				System.out.println("Enter movie title");
				sc.nextLine();
				String title = sc.nextLine();
				cinema.removeMovie(cinema.getMovie(title));

				break;
			}
			case 4: {
				System.out.println("Delete seance:");
				System.out.println("Enter seance day");
				sc.nextLine();
				String day = sc.nextLine();
				System.out.println("Enter seance start time ");
				int hour = sc.nextInt();
				int min = sc.nextInt();
				cinema.removeSeance(new Time(hour, min), day);
				break;

			}

			case 0: {
				System.out.println(cinema);
				break;
			}

			default:
				break;
			}
		}

	}

	public static void menu() {
		System.out.println("==========================================");
		System.out.println("Enter 1 to add movie to Libruary ");
		System.out.println("Enter 2 to add sceanse ");
		System.out.println("Enter 3 to remove movie ");
		System.out.println("Enter 4 to remove seance ");
		System.out.println("Enter 0 print Cinema ");
	}
}
