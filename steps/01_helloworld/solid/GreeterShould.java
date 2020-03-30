import java.util.ArrayList;

/**
 * The class that tests the behaviour of {@link Greeter} class. 
 */
public class GreeterShould
    extends JPTests
{
    public static void main(String[] args)
    {
        TestOutputWriter outputWriter = new TestOutputWriter();
        Greeter greeter = new Greeter(outputWriter);

        greeter.greet("World");
        assertAreEqual("Hello World!", outputWriter.getNextMessage(), "Greet the world");

        greeter.greet("Alun");
        greeter.greet("Bethan");
        assertAreEqual("Hello Alun!", outputWriter.getNextMessage(), "Greet Alun");
        assertAreEqual("Hello Bethan!", outputWriter.getNextMessage(), "Greet Bethan");
    }
}