package edu.school21.app;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;

import edu.school21.classes.Car;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;


import java.lang.reflect.*;
import java.util.*;

import java.lang.Object;
import java.util.stream.Collectors;

public class Program {

    private static Object[] classes;
    private static Object o;
    private static String input;
    private static String className;
    public static void main(String[] args) throws Exception {

    Reflections reflections = new Reflections("edu.school21.classes", new SubTypesScanner(false));
    Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        List<String> classes = allClasses.stream()
                .map(Class::getSimpleName)
                .collect(Collectors.toList());

//        List<Class<?>> allClasses = getClassesFromPackage("edu.school21.classes");
//        classes = new Object[allClasses.size()];
//
//        for (int i = 0; i < allClasses.size(); i++) {
//            classes[i] = allClasses.get(i).newInstance();
//        }

    Scanner in = new Scanner(System.in);


        while (true) {
            System.out.println("Classes:");
            for (String aClass : classes) {
                //System.out.println("\t" + "-" + object.toString().substring(27));
                System.out.println("\t" + "-" + aClass);
            }

            System.out.println("---------------------");

            System.out.println("Enter class name:");
            input = in.nextLine();

            for (String aClass : classes) {
                if (aClass.equals(input)) {
                    className = input;
                }
            }

            if (className == null) {
                System.out.println("Class not found! Try again");
                System.out.println("---------------------");
            } else
                break;
        }

    //System.out.println(o);
    Field[] fields = null;
    Method[] methods = null;
    Class<?> cls = null;

    Checker checker = new Checker();

    try {
        cls = Class.forName("edu.school21.classes." + className);
        fields = checker.getFields(cls.newInstance());
        methods = checker.getMethods(cls.newInstance());

        System.out.println("fields :");

        for (Field field : fields) {
            System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());
        }

        System.out.println("methods :");

        for (Method method : methods) {
            System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");

            Parameter[] parameters = method.getParameters();

            for (int i = 0; i < parameters.length - 1; i++) {
                System.out.print(parameters[i].getType().getSimpleName() + ", ");
            }

            if (parameters.length != 0)
                System.out.print(parameters[(parameters.length == 0) ? 0 : parameters.length - 1].getType().getSimpleName());
            System.out.println(")");
        }

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        System.err.println("Error");
        System.exit(-1);
    }

        System.out.println("---------------------");

        System.out.println("Let's create an object.");

        Constructor constructorClass = null;
        Constructor[] constructors = cls.getDeclaredConstructors();
        Parameter[] parameters = null;

        for (Constructor constructor : constructors) {
            if (constructor.getParameters().length > 0){
                constructorClass = constructor;
                parameters = constructor.getParameters();
                break;
            }
        }

        List<Object> constructorParam = new ArrayList<>();

        for (int i = 0; i < parameters.length; i++) {
            System.out.println(fields[i].getName() + ":");
            //constructorParam.add(getParamObject(parameters[i], input));
        }

//        if (!(constructorParam.get(constructorParam.size() -1 ) instanceof String)){
//            in.nextLine();
//        }

        System.out.println("Object created:");
        Object object = null;

        try {
            object = constructorClass.newInstance(constructorParam.toArray());
            System.out.println(object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("ERROR");
            System.exit(-1);
        }

   }
}