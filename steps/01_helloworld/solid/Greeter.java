public class Greeter
{
    private OutputWriter output;

    public Greeter(OutputWriter output)
    {
        this.output = output;
    }

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