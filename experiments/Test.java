import java.lang.ClassLoader;
import java.lang.ClassNotFoundException;
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
        ClassLoader loader = Test.class.getClassLoader();
        processFolder(compiler, loader, new File("."));
    }

    private static void processFolder(JavaCompiler compiler, ClassLoader loader, File file)
    {
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                processFolder(compiler, loader, f);
            }
            else if (f.isFile())
            {
                if (f.getName().endsWith("Should.java"))
                {
                    System.out.printf("%s is a test class%n", f.getName());
                    String className = f.getName().replace(".java", "");
                    System.out.printf("  class name is %s%n", className);
                    compiler.run(null, null, null, f.getPath());
                    try 
                    {
                        Class cls = loader.loadClass(className);
                        processClass(cls);
                    }
                    catch (ClassNotFoundException ex)
                    {
                        System.out.println("Class not found");
                    }
                }
            }
        }
    }

    private static void processClass(Class cls)
    {
        for(Method m : cls.getDeclaredMethods())
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
                    Object instance = cls.newInstance();
                    m.invoke(instance, (Object[])null);
                } catch (IllegalAccessException x) {
                    x.printStackTrace();
                } catch (InvocationTargetException x) {
                    x.printStackTrace();
                } catch (InstantiationException x) {
                    x.printStackTrace();
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
                } catch (IllegalAccessException x) {
                    x.printStackTrace();
                } catch (InvocationTargetException x) {
                    x.printStackTrace();
                }
            }
        }
    }
}
