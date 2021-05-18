package ua.lviv.lgs;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class Cinema {

    TreeMap<Day, Schedule> schedules = new TreeMap<>();

    ArrayList<Movie> moviesLibrary = new ArrayList<>();

    Time openTime;
    Time closeTime;

    public Cinema(Time openTime, Time closeTime) {
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public Cinema(ArrayList<Movie> moviesLibrary, Time openTime, Time closeTime) {
//		this.schedules = schedules;
        this.moviesLibrary = moviesLibrary;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public TreeMap<Day, Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(TreeMap<Day, Schedule> schedules) {
        this.schedules = schedules;
    }

    public ArrayList<Movie> getMoviesLibrary() {
        return moviesLibrary;
    }

    public void setMoviesLibrary(ArrayList<Movie> moviesLibrary) {
        this.moviesLibrary = moviesLibrary;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Time openTime) {
        this.openTime = openTime;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
    }

    public Movie getMovie(String title) {
        return moviesLibrary.stream().filter(m -> m.getTitle().equalsIgnoreCase(title)).findAny().get();

    }

    public void addMovie(Movie movie) {
        if (!moviesLibrary.stream().anyMatch(m -> m.equals(movie)))
            moviesLibrary.add(movie);
    }

    public boolean addSeance(Seance seance, String dayName) {
        Schedule tempSchedule = new Schedule();
        boolean isAdded = false;
        if ((seance.getStartTime().inRange(openTime, closeTime))
                && (seance.getEndTime().inRange(openTime, closeTime))) {

            if (schedules.containsKey(Day.valueOf(dayName.toUpperCase()))) {
                tempSchedule = schedules.get(Day.valueOf(dayName.toUpperCase()));
            }
            tempSchedule.addSeance(seance);
            schedules.put(Day.valueOf(dayName.toUpperCase()), tempSchedule);
            isAdded = true;
        } else System.out.println("Sorry cinema will be closed at that time ");
        return isAdded;
    }


    public boolean removeMovie(Movie movie) {
        for (Day day : Day.values()) {
            if (schedules.containsKey(day)) {
                schedules.get(day).removeSeance(movie);
            }
        }
        return moviesLibrary.remove(movie);
    }


    public void removeSeance(Time startSeanceTime, String dayName) {

        if (schedules.containsKey(Day.valueOf(dayName.toUpperCase()))) {
            Schedule tempSchedule = schedules.get(Day.valueOf(dayName.toUpperCase()));
            tempSchedule.removeSeance(startSeanceTime);
            schedules.replace(Day.valueOf(dayName.toUpperCase()), tempSchedule);
        }
    }


    @Override
    public String toString() {
        return "Cinema  openTime=" + openTime + ", closeTime= " + closeTime + ",\n" + " moviesLibrary=" + moviesLibrary
                + "\n" + schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cinema cinema = (Cinema) o;
        return Objects.equals(schedules, cinema.schedules) &&
                Objects.equals(moviesLibrary, cinema.moviesLibrary) &&
                Objects.equals(openTime, cinema.openTime) &&
                Objects.equals(closeTime, cinema.closeTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedules, moviesLibrary, openTime, closeTime);
    }
}
