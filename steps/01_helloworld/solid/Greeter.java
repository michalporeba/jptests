public class Greeter
{
    private OutputWriter output;

    public Greeter(OutputWriter output)
    {
        this.output = output;
    }

    public void greet(String name)
    {
        output.writeUserMessage(createWelcomeMessage(name));
    }

    public void greet(String... names[])
    {
        for(String name : name) 
        {
            greet(name);
        }
    }

    private String createWelcomeMessage(String name)
    {
        return String.format("Hello %s", name);
    }
}