import java.util.ArrayList;

/**
 * This is a test output writer that instead of writing messages to the user
 * stores them internally, and allows the tests to check what would have been printed out
 */
public class TestOutputWriter 
    implements OutputWriter
{
    ArrayList<String> userMessages = new ArrayList<String>();

    /** {@inheritDoc} */
    public void writeUserMessage(String message)
    {
        userMessages.add(message);
    }

    /**
     * Get the next message the the cache
     * @return
     */
    public String getNextMessage()
    {
        return (userMessages.size() > 0) ? userMessages.remove(0) : null;
    }
}