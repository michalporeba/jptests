public class HelloWorld2
{
    public static void main(String[] args)
    {
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHelloTo(args[0]));
    }
}