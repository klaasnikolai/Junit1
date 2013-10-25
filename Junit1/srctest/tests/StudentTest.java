package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.GregorianCalendar;
import java.util.TreeSet;

import org.junit.BeforeClass;
import org.junit.Test;

import daten.BirthDateComparator;
import daten.Student;

public class StudentTest {
	private GregorianCalendar cal = new GregorianCalendar(1994,10,06);
	static Student s = new Student("Niko", "Kenig", new GregorianCalendar(1994,19,06));
	static Student scopy = new Student("Niko", "Kenig", new GregorianCalendar(1994,10,06));
	static Student s2 = new Student("Mohamed", "Ata", new GregorianCalendar(1993,11,15));
	static TreeSet<Student> t = new TreeSet<Student>(new BirthDateComparator());
	
	@BeforeClass
	public static void setUp() throws Exception{
		t.add(s);
		t.add(scopy);
		t.add(s2);
		System.out.println(t.first().getFirstName());
	}
	
	//COMPARETO TESTS
	@Test()
	public void testIfCompareToWorks(){
		assertEquals(s.compareTo(s),0);
	}
	
	//CONSTRUCTOR TESTS
	@Test
	public void constructorWithValidArguments() {
		assertSame("Niko", s.getFirstName());
		assertSame("Kenig", s.getLastName());
		assertSame("1994106", s.getStringBirthDate());

		assertEquals("Niko", scopy.getFirstName());
		assertEquals("Kenig", scopy.getLastName());
	}
	
	//BIRTHDATE TEST
	
	//NULL TESTS
	@Test(expected = IllegalArgumentException.class)
	public void constructorWithNullArgumentForFirstName() {
		Student s = new Student(null, "Kenig", cal);
	}
	@Test(expected = IllegalArgumentException.class)
	public void constructorWithNullArgumentForLastName() {
		Student s = new Student("Niko", null , cal);
	}
	@Test(expected = IllegalArgumentException.class)
	public void constructorWithNullArgumentForBirthDate() {
		Student s = new Student("Niko", "Kenig", null);
	}
	
	//EMPTYTEXT TESTS

	@Test(expected = IllegalArgumentException.class)
	public void constructorWithEmptyArgumentForFirstName() {
		Student s = new Student("", "Kenig", cal);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorWithEmptyArgumentForLastName() {
		Student s = new Student("Niko", "", cal);
	}

	
	//WHITESPACE TESTS

	@Test(expected = IllegalArgumentException.class)
	public void constructorWithWhiteSpaceArgumentForLastName() {
		Student s = new Student("Niko", " ", cal);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void constructorWithWhiteSpaceArgumentForFirstName() {
		Student s = new Student(" ", "Kenig", cal);
	}
	
	//TOSTRING TEST
	@Test
	public void testToString() {
		Student s = new Student("Niko", "Kenig", cal);
		assertEquals("Student {firstName=Niko, lastName=Kenig, birthDate=1994106}",
				s.toString());
	}


}
