package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.TreeSet;

import javax.lang.model.element.Element;

import org.junit.BeforeClass;
import org.junit.Test;

import daten.BirthDateComparator;
import daten.Student;

public class StudentTest {
	private GregorianCalendar cal = new GregorianCalendar(1994,10,06);
	static Student s = new Student("Niko", "Kenig", new GregorianCalendar(1994,10,06));
	static Student scopy = new Student("Niko", "Kenig", new GregorianCalendar(1994,10,06));
	static Student s2 = new Student("Mohamed", "Ata", new GregorianCalendar(1993,11,15));
	static Student s3 = new Student("Julia", "Schatzi", new GregorianCalendar(1995,07,04));
	static TreeSet<Student> t = new TreeSet<Student>(new BirthDateComparator());
	
	@BeforeClass
	public static void setUp() throws Exception{
		t.add(s);
		t.add(scopy);
		t.add(s2);
		t.add(s3);
	}
	
	@Test
	public void WhenSortWorks(){
		assertSame(t.last(), s3);
		assertSame(t.first(),s2);
		Iterator iterator = t.iterator();
		while (iterator.hasNext())
			System.out.print( iterator.next().toString() + ", " );
		System.out.println();
	}
	
	//COMPARETO TESTS
	@Test
	public void testIfCompareToWorks(){
		assertEquals(s.compareTo(scopy),0);
	}
	
	@Test
	public void WhenCompareToIsWrong(){
		assertEquals(s.compareTo(s2), 1);
	}
	
	
	
	//CONSTRUCTOR TESTS
	@Test
	public void constructorWithValidArguments() {
		assertSame("Niko", s.getFirstName());
		assertSame("Kenig", s.getLastName());
		assertSame("19931115", s2.getStringBirthDate());

		assertEquals("Niko", scopy.getFirstName());
		assertEquals("Kenig", scopy.getLastName());
	}
	
	@Test
	public void constructorWithValidArgumentsFirstLastName(){
		Student st = new Student("John", "Legend");
		assertSame("John", st.getFirstName());
		assertSame("Legend", st.getLastName());
	}
	
	@Test
	public void IsEqualTrue(){
		assertTrue(s.isEqual(scopy));
	}
	
	@Test
	public void IsEqualFalse(){
		assertFalse(s.isEqual(s2));
	}
	
	//BIRTHDATE TEST
	@Test(expected=IllegalArgumentException.class)
	public void WhenBirthDateIsInFuture(){
		Student st = new Student("John", "Legend", new GregorianCalendar());
		
	}
	
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
