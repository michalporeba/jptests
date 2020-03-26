public abstract class JPTests
{
    protected static void assertAreEqual(String expected, String actual, String description)
	{
        if ((expected == null && actual == null)
			|| (expected != null && expected.equals(actual)))
		{
			System.out.printf("[PASS] %s%n", description);
		}
		else 
		{
			System.out.printf("[FAIL] %s%n\tExpected: %s%n\tActual:  %s%n", description, expected, actual);
		}
	}
}