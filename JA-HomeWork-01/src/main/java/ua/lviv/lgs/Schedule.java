package ua.lviv.lgs;

import java.util.Set;
import java.util.TreeSet;
//import static java.util.stream.Collectors.*;

import java.util.Iterator;

public class Schedule {

	private Set<Seance> seances = new TreeSet<>();

	public void addSeance(Seance seance) {

		if (!isSeance(seance)) {
			seances.add(seance);
		} else
			System.out
					.println("Error!!! Time " + seance.getStartTime() + seance.getEndTime() + " isn`t available !!! for " + seance);
	}

	public boolean isSeance(Seance seance) {
		return seances.stream()
				.anyMatch(s -> (seance.getStartTime().inRange(s.getStartTime(), s.getEndTime()))
						| (seance.getEndTime().inRange(s.getStartTime(), s.getEndTime()))
						| (s.getStartTime().inRange(seance.getStartTime(), seance.getEndTime()))
						| (s.getEndTime().inRange(seance.getStartTime(), seance.getEndTime())));
	}

	public void removeSeance(Time startSeanceTime) {

		Iterator<Seance> iterator = seances.iterator();
		while (iterator.hasNext()) {
			Seance nextSeance = iterator.next();
			if (nextSeance.getStartTime().equals(startSeanceTime)) {
				iterator.remove();
			}
		}
	}
	
	public void removeSeance(Movie movie) {

		Iterator<Seance> iterator = seances.iterator();
		while (iterator.hasNext()) {
			Seance nextSeance = iterator.next();
			if (nextSeance.getMovie().getTitle().equalsIgnoreCase(movie.getTitle())) {
				iterator.remove();
			}
		}
	}

	public Set<Seance> getSeances() {
		return seances;
	}

	public void setSeances(Set<Seance> seances) {
		this.seances = seances;
	}

	@Override
	public String toString() {
		return "Schedule \n " + seances;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seances == null) ? 0 : seances.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (seances == null) {
			if (other.seances != null)
				return false;
		} else if (!seances.equals(other.seances))
			return false;
		return true;
	}

}
