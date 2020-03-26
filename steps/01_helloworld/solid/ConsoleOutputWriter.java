public class ConsoleOutputWriter
    implements OutputWriter
{
    public void writeUserMessage(String message)
    {
        System.out.println(message);
    }
}