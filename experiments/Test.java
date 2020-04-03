import java.lang.ClassLoader;
import java.lang.ClassNotFoundException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Locale;
import java.lang.reflect.InvocationTargetException;
import java.io.File;
import java.io.IOException;
import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

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
        DiagnosticCollector diagnosticCollector = new DiagnosticCollector();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticListener, Locale.ENGLISH, Charset.forName("utf-8"));
        processFolder(fileManager, compiler, loader, new StringWriter(), new File("."));
    }

    private static void processFolder(JavaFileManager fileManager, JavaCompiler compiler, ClassLoader loader, StringWriter output, DiagnosticCollector diagnosticCollector, File file)
    {
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                processFolder(fileManager, compiler, loader, output, diagnosticCollector, f);
            }
            else if (f.isFile())
            {
                if (f.getName().endsWith("Should.java"))
                {
                    System.out.printf("%s is a test class%n", f.getName());
                    String className = f.getName().replace(".java", "");
                    System.out.printf("  class name is %s%n", className);

                    String[] options = new String[] {};
                    CompilationTask task = compiler.getTask(output, fileManager, diagnosticListener, options, classes, compilationUnits)
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
}
