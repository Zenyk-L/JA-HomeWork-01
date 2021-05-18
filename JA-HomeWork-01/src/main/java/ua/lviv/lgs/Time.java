package ua.lviv.lgs;

public class Time implements Comparable<Time> {

	private int hour;
	private int min;

	public Time(int hour, int min) {
		if ((min < 0) | (hour < 0))
			System.out.println("Wrong time value, < 0 ");
		else {
			if (min < 60) {
				this.min = min;
			} else if (min >= 60) {
				hour += min / 60;
				this.min = min % 60;
			}

			if (hour < 24) {
				this.hour = hour;
			} else {
				this.hour = hour % 24;
				System.out.println("Wrong hour value  > 24 ");
			}
		}
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	@Override
	public String toString() {
		if (hour < 10) {
			if (min < 10) {
				return " [0" + hour + ":0" + min + "]";
			} else {
				return " [0" + hour + ":" + min + "]";
			}
		} else if (min < 10) {
			return " [" + hour + ":0" + min + "]";
		} else
			return " [" + hour + ":" + min + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hour;
		result = prime * result + min;
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
		Time other = (Time) obj;
		if (hour != other.hour)
			return false;
		if (min != other.min)
			return false;
		return true;
	}

	public Time addTime(Time addedTime) {
		int resultMin = this.getMin() + addedTime.getMin();
		int resultHour = this.getHour() + addedTime.getHour();
		return new Time(resultHour, resultMin);

	}

	public boolean inRange(Time timeStart, Time timeEnd) {
		boolean result = false;
		if ((this.hour*60 + this.min) >= (timeStart.hour*60 + timeStart.min) && (this.hour*60 + this.min) <= (timeEnd.hour *60 + timeEnd.min)) 			
				result = true;		
		
		return result;
	}

	@Override
	public int compareTo(Time time) {
		if (this.hour > time.hour) {
			return 1;
		} else if (this.hour < time.hour) {
			return -1;
		} else {
			if (this.min > time.min) {
				return 1;
			} else if (this.min < time.min) {
				return -1;
			} else
				return 0;
		}
	}

}
