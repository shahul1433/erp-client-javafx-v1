package erp.client.javafx.component.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateSearchable {

	private LocalDateTime fromDate, toDate;
	
	public DateSearchable() {
		// TODO Auto-generated constructor stub
	}
	
	public DateSearchable(LocalDate from, LocalDate to) {
		this.fromDate = LocalDateTime.of(from, LocalTime.MIN);
		this.toDate = LocalDateTime.of(to, LocalTime.MAX);
	}
	
	public DateSearchable(LocalDateTime from, LocalDateTime to) {
		this.fromDate = from;
		this.toDate = to;
	}

	public LocalDateTime getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDateTime fromDate) {
		this.fromDate = fromDate;
	}
	
	public LocalDateTime getToDate() {
		return toDate;
	}

	public void setToDate(LocalDateTime toDate) {
		this.toDate = toDate;
	}
	
}