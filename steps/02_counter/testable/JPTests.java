public abstract class JPTests
{
    protected static void assertAreEqual(int expected, int actual, String description)
	{
        if (expected == actual)
		{
			System.out.printf("[PASS] %s%n", description);
		}
		else 
		{
			System.out.printf("[FAIL] %s%n\tExpected: %d%n\tActual:  %d%n", description, expected, actual);
		}
	}
}