public class LPTests
    extends JPTests 
{
    public void discoverAndExecute()
    {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        ClassLoader loader = Test.class.getClassLoader();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        try 
        {
            processFolder(fileManager, compiler, loader, new File("."));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static void processFolder(StandardJavaFileManager fileManager, JavaCompiler compiler, ClassLoader loader, File file)
        throws Exception
    {
        for (File f : file.listFiles())
        {
            if (f.isDirectory())
            {
                // step to the folder
                processFolder(fileManager, compiler, loader, f);
            }
            else if (f.isFile())
            {
                if (f.getName().endsWith("Should.java"))
                {
                    String className = f.getName().replace(".java", "");

                    ArrayList<File> files = new ArrayList<File>();
                    files.add(f);
                    CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileManager.getJavaFileObjectsFromFiles(files));
                    if (task.call())
                    {
                        try 
                        {
                            processClass(loader.loadClass(className));
                        }
                        catch (ClassNotFoundException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                    else 
                    {
                        // compilation failed, so throw to stop any further processing
                        throw new Exception("Compilation failed. Tests failed.");
                    }
                }
            }
        }
    }

    private static void processClass(Class cls)
    {
        boolean first = true;
        for(Method m : cls.getDeclaredMethods())
        {
            if (first)
            {
                System.out.println("\n" + m.getDeclaringClass().getName().replace("Should", " should... "));
                first = false;
            }

            if (
                Modifier.isPublic(m.getModifiers())         // test methods need to be public
                && !Modifier.isAbstract(m.getModifiers())   // test methdos cannot be abstract
                && !Modifier.isStatic(m.getModifiers())     // test methods cannot be static
                && m.getReturnType().equals(Void.TYPE)      // test methods cannot return anything
                && m.getParameterCount() == 0               // at the moment only parameterless methods are supported
            )
            {
                System.out.println("  " + m.getName().replace("_", " "));
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