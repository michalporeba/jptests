/**
 *  A single base class for writing simple class unit tests
 *  for mor information https://github.com/michalporeba/jptests
 *  @version 0.1.0 
 */

import java.lang.Class;
import java.lang.Exception;

public class JPTests
{
	protected static int assertions = 0;
	protected static int failed = 0;
	protected static int passed = 0;

	protected static void AssertAreEqual(char expected, char actual, String description)
	{
		if (expected != actual)
		{
			System.out.printf("FAIL: %s should be %c but was %c%n", description, expected, actual);
			++failed;
		}
		else 
		{
			System.out.printf("PASS: %s%n", description);
			++passed;
		}
	}

	protected static void AssertAreEqual(int expected, int actual, String description)
	{
		if (expected != actual)
		{
			System.out.printf("FAIL: %s should be %d but was %d%n", description, expected, actual);
			++failed;
		}
		else 
		{
			System.out.printf("PASS: %s%n", description);
			++passed;
		}
	}

	protected static void AssertAreEqual(double expected, double actual, String description)
	{
		if (expected != actual) 
		{
			System.out.printf("FAIL: %s should be %f but was %f%s", description, expected, actual);
			++failed;
		}
		else 
		{
			System.out.printf("PASS: %s%n", description);
		}
	}
	
	protected static void AssertAreEqual(boolean expected, boolean actual, String description)
	{
		if (expected != actual)
		{
			System.out.printf("FAIL: %s should be %s but was %s%n", description, expected, actual);
			++failed;
		}
		else 
		{
			System.out.printf("PASS: %s%n", description);
			++passed;
		}
	}

	protected static void AssertAreEqual(String expected, String actual, String description)
	{
		if (!expected.equals(actual))
		{
			System.out.printf("FAIL: %s should be %s but was %s%n", description, expected, actual);
			++failed;
		}
		else 
		{
			System.out.printf("PASS: %s%n", description);
			++passed;
		}
	}

	protected static void AssertIsNotNull(Object obj, String description) 
	{
		if (obj == null)
		{
			System.out.printf("FAIL: %s", description);
			++failed;
		}
		else 
		{
			System.out.printf("PASS: %s", description);
			++passed;
		}
	}
	
	protected static void AssertThrows(SimpleAction action, Class<? extends Exception> exceptionType, String description)
	{	
		try 
		{
			action.execute();
			System.out.printf("FAIL: %s. %s exception was expected but it wasn't thrown%n", description, exceptionType);
			++failed;
		} 
		catch (Exception ex) 
		{
			if (ex.getClass().equals(exceptionType))
			{
				System.out.printf("PASS: %s%n", description);
				++passed;
			}
			else 
			{
				System.out.printf("FAIL: %s. %s exception was thrown instead of %s%n", description, ex.getClass(), exceptionType);
				++failed;
			}
		}
		
	}

	public interface SimpleAction
	{
		void execute();
	}
}

