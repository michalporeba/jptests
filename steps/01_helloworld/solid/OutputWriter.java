/**
 * The interface to facilitate separating the {@link Greeter} from the 
 * console output
 */
public interface OutputWriter
{
    /**
     * write a message to a user facing output
     * @param message The message to be written to the user
     */
    void writeUserMessage(String message);
}