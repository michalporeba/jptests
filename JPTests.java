import java.lang.Class;
import java.lang.Exception;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * A single base class for writing simple class unit tests.
 * For mor information see <a href="https://github.com/michalporeba/jptests">JPTest Repository</a>.
 *  
 * @version 0.3.0 
 */

public abstract class JPTests
{
	private static final double DEFAULT_DOUBLE_PRECISION = 0.001;
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
		assertAreEqual(expected, actual, DEFAULT_DOUBLE_PRECISION, description);
	}
	
	protected static void assertAreEqual(double expected, double actual, double precision, String description)
	{
		expected = precision*Math.round(expected*Math.pow(10,-Math.log10(precision)));
		actual = precision*Math.round(actual*Math.pow(10,-Math.log10(precision)));
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
    
    public static <T> void assertProperty(Object obj, String propertyName, T defaultValue, T newValue)
    {
        try {
            T value = invokeGetter(obj, propertyName);
            assertAreEqual(defaultValue, value, "%s", String.format("%s's default value should be [%s]", propertyName, defaultValue));
            invokeSetter(obj, propertyName, newValue);
            value = invokeGetter(obj, propertyName);
            assertAreEqual(newValue, value, "%s", String.format("%s's changed value should be [%s]", propertyName, newValue));
        } 
        catch (Exception ex) 
        {
            System.out.printf("FAIL: %s. %s exception was thrown when testing property.", propertyName, ex.getClass());
            ++failed;
        }
    }

    private static <T> void invokeSetter(Object obj, String propertyName, T value)
        throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
        Method setter = pd.getWriteMethod();
        setter.invoke(obj, value);
	}
 
    @SuppressWarnings("unchecked")
    private static <T> T invokeGetter(Object obj, String propertyName)
        throws IntrospectionException, IllegalAccessException, InvocationTargetException
	{
        PropertyDescriptor pd = new PropertyDescriptor(propertyName, obj.getClass());
        Method getter = pd.getReadMethod();
        Object value = getter.invoke(obj); 
        return (T)value;
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