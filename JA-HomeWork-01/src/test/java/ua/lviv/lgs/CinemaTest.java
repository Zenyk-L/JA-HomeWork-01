package ua.lviv.lgs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import java.util.ArrayList;
import java.util.Arrays;

public class CinemaTest {

    private Cinema cinema;
    private Cinema cinemaManual;
    Movie[] movieArray = {new Movie("First test", new Time(0, 30)), new Movie("Second test", new Time(0, 30)),
            new Movie("Third test", new Time(0, 30)), new Movie("Fourth test", new Time(0, 10)),
            new Movie("a1", new Time(0, 30)), new Movie("a2", new Time(0, 30)), new Movie("a3", new Time(0, 30)), new Movie("a4", new Time(0, 30))};




    @Rule
    public TestWatcher testWatcher = new TestWatcher() {
        @Override
        protected void succeeded(Description description) {
            System.out.println("SUCCEED -> " + description.getMethodName());
        }

        @Override
        protected void failed(Throwable e, Description description) {
            System.out.println("FAILED -> " + description.getMethodName());
        }
    };

    @Before
    public void newCinema() {


        ArrayList<Movie> moviesLibrary = new ArrayList<>(Arrays.asList(movieArray));
        cinema = new Cinema(moviesLibrary, new Time(9, 0), new Time(23, 0));

        Seance s = new Seance(new Movie("First test", new Time(0, 30)), new Time(10, 00));
        Seance s1 = new Seance(new Movie("Second test", new Time(0, 30)), new Time(12, 0));
        Seance s2 = new Seance(new Movie("Third test", new Time(0, 30)), new Time(13, 0));
        Seance s3 = new Seance(new Movie("Fourth test", new Time(0, 10)), new Time(13, 35));

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

        cinemaManual = new Cinema(moviesLibrary, new Time(9, 0), new Time(23, 0));
        cinemaManual.addSeance(s, "MONDAY");
        cinemaManual.addSeance(s1, "Monday");
        cinemaManual.addSeance(s2, "Monday");
        cinemaManual.addSeance(s3, "Monday");
        cinemaManual.addSeance(s, "wednesday");
        cinemaManual.addSeance(s1, "wednesday");
        cinemaManual.addSeance(s2, "wednesday");
        cinemaManual.addSeance(s3, "wednesday");
        cinemaManual.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(11, 00)), "Monday");
        cinemaManual.addSeance(new Seance(new Movie("a2", new Time(0, 30)), new Time(14, 00)), "Monday");
        cinemaManual.addSeance(new Seance(new Movie("a3", new Time(0, 30)), new Time(12, 30)), "saturday");
        cinemaManual.addSeance(new Seance(new Movie("a2", new Time(0, 30)), new Time(10, 00)), "Tuesday");
        cinemaManual.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(11, 00)), "Tuesday");
        cinemaManual.addSeance(new Seance(new Movie("a3", new Time(0, 30)), new Time(15, 00)), "Tuesday");
        cinemaManual.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(10, 00)), "friday");
        cinemaManual.addSeance(new Seance(new Movie("a1", new Time(0, 30)), new Time(11, 00)), "friday");
        cinemaManual.addSeance(new Seance(new Movie("a3", new Time(0, 30)), new Time(21, 00)), "friday");
        cinemaManual.addSeance(new Seance(new Movie("a2", new Time(0, 30)), new Time(12, 00)), "friday");
    }

    @Test
    public void shouldGetMovieByName() {

        Assert.assertEquals(new Movie("First test", new Time(0, 30)),cinema.getMovie("First test"));

    }

    @Test
    public void shouldAddNewMovie(){

        ArrayList<Movie> moviesLibraryForManualAdd = cinema.getMoviesLibrary();
        Movie movieToAdd = new Movie("a10", new Time(0,10));
        cinema.addMovie(movieToAdd);
        moviesLibraryForManualAdd.add(movieToAdd);

        Assert.assertEquals(moviesLibraryForManualAdd,cinema.getMoviesLibrary());

    }

    @Test
    public void shouldNotAddExistingMovie(){

        ArrayList<Movie> moviesLibraryForManualAdd = new ArrayList<>(Arrays.asList(movieArray));
        Movie movieToAdd = new Movie("Second test", new Time(0, 30));

        cinema.addMovie(movieToAdd);
        moviesLibraryForManualAdd.add(movieToAdd);

        Assert.assertNotEquals(moviesLibraryForManualAdd,cinema.getMoviesLibrary());

    }
    @Test
    public void shouldAddSeanceStartAfterOpenTimeEndBeforeCloseTime(){
        Seance seanceToAdd = new Seance(new Movie("fifth test", new Time(0, 30)),new Time(14, 00));

        Assert.assertTrue(cinema.addSeance(seanceToAdd,"monday"));
    }

    @Test
    public void shouldNotAddSeanceStartBeforOpenTime(){
        Seance seanceToAdd = new Seance(new Movie("fifth test", new Time(0, 30)),new Time(8, 50));

        Assert.assertFalse(cinema.addSeance(seanceToAdd,"monday"));
    }

    @Test
    public void shouldNotAddSeanceStartAfterCloseTime(){
        Seance seanceToAdd = new Seance(new Movie("fifth test", new Time(0, 30)),new Time(23, 15));

        Assert.assertFalse(cinema.addSeance(seanceToAdd,"monday"));
    }

    @Test
    public void shouldNotAddSeanceStartAfterOpenTimeEndAfterCloseTime(){
        Seance seanceToAdd = new Seance(new Movie("fifth test", new Time(0, 30)),new Time(22, 45));

        Assert.assertFalse(cinema.addSeance(seanceToAdd,"monday"));
    }

    @Test
    public void removeMovieTest(){

        Assert.assertTrue( cinema.removeMovie(new Movie("second test", new Time(0, 30))));

    }

    @Test
    public void removeSeanceTest(){

        cinema.removeSeance(new Time(10, 00), "Monday");
        Assert.assertNotEquals(cinema,cinemaManual);


    }
}
