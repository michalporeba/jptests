import java.lang.Class;
import java.lang.Exception;

/**
 * A single base class for writing simple class unit tests.
 * For mor information see <a href="https://github.com/michalporeba/jptests">JPTest Repository</a>.
 *  
 * @version 0.1.0 
 */

public abstract class JPTests
{
	protected static int assertions = 0;
	protected static int failed = 0;
	protected static int passed = 0;

	protected static void assertAreEqual(char expected, char actual, String description)
	{
		assertAreEqual(new Character(expected), new Character(actual), "c", description);
	}

	protected static void assertAreEqual(int expected, int actual, String description)
	{
		assertAreEqual(new Integer(expected), new Integer(actual), "d", description);
	}

	protected static void assertAreEqual(double expected, double actual, String description)
	{
		assertAreEqual(new Double(expected), new Double(actual), "f", description);
	}
	
	protected static void assertAreEqual(boolean expected, boolean actual, String description)
	{
		assertAreEqual(new Boolean(expected), new Boolean(actual), "s", description);
	}

	protected static void assertAreEqual(String expected, String actual, String description)
	{
		assertAreEqual(expected, actual, "s", description);
	}

	protected static void assertIsNotNull(Object obj, String description) 
	{
		if (obj != null)
		{
			System.out.printf("[PASS] %s%n", description);
			++passed;
		}
		else 
		{
			System.out.printf("[FAIL] %s%n\tExpected: NULL%n\tActual: %s%n", description, obj);
			++failed;
		}
	}
	
	protected static void assertThrows(SimpleAction action, Class<? extends Exception> exceptionType, String description)
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

	private static <T> void assertAreEqual(T expected, T actual, String format, String description)
	{
		if ((expected == null && actual == null)
			|| expected.equals(actual))
		{
			System.out.printf("[PASS] %s%n", description);
			++passed;
		}
		else 
		{
			System.out.printf("[FAIL] %s%n\tExpected: %" + format + "%n\tActual:  %" + format + "%n", description, expected, actual);
			++failed;
		}
	}

	public interface SimpleAction
	{
		void execute();
	}
}