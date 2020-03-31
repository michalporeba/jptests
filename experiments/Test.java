import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.IOException;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class Test
{
    public static void main(String[] args)
    {
        executeAll();
        //executeSampleShould();
    }

    private static void executeAll()
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        processFolder(compiler, new File("."));
    }

    private static void processFolder(JavaCompiler compiler, File file)
    {
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                processFolder(compiler, f);
            }
            else if (f.isFile())
            {
                if (f.getName().endsWith("Should.java"))
                {
                    System.out.printf("%s is a test class%n", f.getName());
                    compiler.run(null, null, null, f.getPath());
                }
            }
        }
    }

    private static void executeSampleShould()
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
