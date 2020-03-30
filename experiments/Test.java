import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
public class Test
{
    public static void main(String[] args)
    {
        for(Method m : SampleShould.class.getDeclaredMethods())
        {
            if (
                Modifier.isPublic(m.getModifiers())
                && !Modifier.isAbstract(m.getModifiers())
                && !Modifier.isStatic(m.getModifiers())
                && m.getReturnType().equals(Void.TYPE)
                && m.getParameterCount() == 0
            )
            {
                System.out.print(m.getDeclaringClass().getName() + ".");
                System.out.println(m.getName());
                try 
                {
                    SampleShould c = new SampleShould();
                    m.invoke(c, (Object[])null);
                // } catch (ClassNotFoundException x) {
                //     x.printStackTrace();
                // // } catch (NoSuchMethodException x) {
                // //     x.printStackTrace();
                } catch (IllegalAccessException x) {
                    x.printStackTrace();
                } catch (InvocationTargetException x) {
                    x.printStackTrace();
                }
            }
        }
    }
}
