import java.util.ArrayList;

public class TestOutputWriter 
    implements OutputWriter
{
    ArrayList<String> userMessages = new ArrayList<String>();

    public void writeUserMessage(String message)
    {
        userMessages.add(message);
    }

    public String getNextMessage()
    {
        return (userMessages.size() > 0) ? userMessages.remove(0) : null;
    }
}