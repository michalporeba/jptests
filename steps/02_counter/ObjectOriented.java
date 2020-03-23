public class ObjectOriented
{
    public static void main(String[] args)
    {
        Counter counter = new Counter();
        System.out.println(counter.getCount());

        counter.increment();
        counter.increment();
        System.out.println(counter.getCount());

        counter.reset();
        counter.increment();
        System.out.println(counter.getCount());
    }
}