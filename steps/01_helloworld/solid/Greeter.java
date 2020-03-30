/**
 * The greeter class implements the main functionality of the sample application
 */
public class Greeter
{
    private OutputWriter output;

    /**
     * Creats an instance of the Gretter that depends 
     * on an implementation of an {@link OutputWriter} interface
     * @param output the output writer to be used for writing the output
     */
    public Greeter(OutputWriter output)
    {
        this.output = output;
    }

    /**
     * Greet name
     * @param names a single or a collection of names to be greeted
     */
    public void greet(String... names)
    {
        for(String name : names) 
        {
            output.writeUserMessage(createWelcomeMessage(name));
        }
    }

    private String createWelcomeMessage(String name)
    {
        return String.format("Hello %s!", name);
    }
}