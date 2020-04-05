public class Syntax
{
    protected Ensure ensure = new Ensure();

    protected class Ensure
    {
        public void that(int a)
        {
            System.out.println("that");
        }
    }
}