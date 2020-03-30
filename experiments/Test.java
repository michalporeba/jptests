import java.lang.reflect.Method;

public class Test
{
    public static void main(String[] args)
    {
        for(Method m : SampleShould.class.getDeclaredMethods())
        {
            System.out.print(m.getDeclaringClass().getName() + ".");
            System.out.println(m.getName());
        }
    }
}