import java.util.Queue;

public class GreeterShould
    extends JPTests
{
    public static void main(String[] args)
    {
        TestOutputWriter outputWriter = new TestOutputWriter();
        Greeter greeter = new Greeter(outputWriter);

        greeter.greet("World");
    }

    private class TestOutputWriter 
        implements OutputWriter
    {
        Queue<String> userMessages = new Queue<String>();

        public void writeUserMessage(String message)
        {
            userMessages.add(message);
        }

        public String getNextMessage()
        {
            return userMessages.remove();
        }
    }
}