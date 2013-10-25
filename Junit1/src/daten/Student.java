package daten;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Student extends Person implements Comparable<Student> {

	public Student(String firstName, String lastName) {
		this(firstName, lastName, false);
	}

	public Student(String firstName, String lastName, boolean selfEntitled) {
		super(firstName, lastName);
		setSelfEntitled(selfEntitled);
	}
	public Student(String firstName, String lastName, GregorianCalendar birthDate) {
		super(firstName, lastName);
		setBirthDate(birthDate);
	}

	private boolean selfEntitled;
	private GregorianCalendar birthDate; 
	
	public GregorianCalendar getBirthDate() {
		return birthDate;
	}
	
	public String getStringBirthDate(){
		String s = "" + birthDate.get(Calendar.YEAR);
		s += ""+ birthDate.get(Calendar.MONTH);
		s += ""+ birthDate.get(Calendar.DAY_OF_MONTH);
		return s;

	}

	public void setBirthDate(GregorianCalendar birthDate) {
		if (birthDate == null) {
			throw new IllegalArgumentException("birthDate must not be null!");
		}
		GregorianCalendar cal = new GregorianCalendar();
		if(birthDate.getTimeInMillis()>= cal.getTimeInMillis()){
			throw new IllegalArgumentException("birthDate must not be in the future!");
		}
		this.birthDate = birthDate;
	}

	public void setSelfEntitled(boolean selfEntitled) {
		this.selfEntitled = selfEntitled;
	}

	@Override
	public int compareTo(Student o) {
		int result = 1;

		if (compareLastName(o) == 0) {
			if (compareFirstName(o) == 0) {
				result = compareBirthDate(o);
			} 
		}
		
		return result;
	}
	
	public int compareLastName(Student o){
		return getLastName().compareTo(o.getLastName());
	}
	
	public int compareFirstName(Student o){
		return getFirstName().compareTo(o.getFirstName());
	}
	
	public int compareBirthDate(Student o){
		return getBirthDate().compareTo(o.getBirthDate());
	}

	@Override
	public String toString() {
		String s = String.format("%s {firstName=%s, lastName=%s, birthDate=%s}", getClass()
				.getSimpleName(), getFirstName(), getLastName(), getStringBirthDate());
		return s;
	}
}
