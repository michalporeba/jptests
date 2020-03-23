public class HelloWorld3
{
    public static void main(String[] args)
    {
        Greeter greeter = new Greeter();

        for(String name : args)
        {
            greeter.sayHelloTo(args[0]);
        }
    }
}