/**
 * The Main class is responsible for the orchestration
 */
public class Main
{
    public static void main(String[] args)
    {
        // the output provider to be used in a production run
        OutputWriter outputWriter = new ConsoleOutputWriter();
        // the greeter to be used with the output writer
        Greeter greeter = new Greeter(outputWriter);

        // greet based on the arguments from the console
        greeter.greet(args);
    }
}