public class GreeterShould
    extends JPTests
{
    public static void main(String[] main)
    {
        Greeter greeter = new Greeter();
        assertAreEqual("Hello World!", greeter.sayHelloTo("World"), "World should be greeted by 'Hello World!'");
        assertAreEqual("Hello Michal!", greeter.sayHelloTo("Michal"), "World should be greeted by 'Hello Michal!'");
    }
}