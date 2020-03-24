public class CounterShould 
    extends JPTests
{
    public static void main(String[] args)
    {
        Counter counter = new Counter();
        assertAreEqual(0, counter.getCount(), "New counter should have count 0");

        counter.increment();
        counter.increment();
        assertAreEqual(2, counter.getCount(), "New counter twice incremented should have count 0");

        counter.reset();
        counter.increment();
        assertAreEqual(1, counter.getCount(), "Counter reset and incremented once should have count 1");
    }
}