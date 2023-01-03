package edu.school21.app;

import edu.school21.classes.Car;
import edu.school21.classes.User;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.Scanners;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import static org.reflections.scanners.Scanners.*;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

import java.lang.reflect.Field;

import java.lang.reflect.Field;
import java.lang.Object;

public class Program {

    private static Object [] classes;
    private static Object o;
    public static void main(String[] args) {
    User user = new User();
    Class cls = user.getClass();
    Reflections reflections = new Reflections("edu.school21.classes", new SubTypesScanner(false));
    Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

    String input;
    Scanner in = new Scanner(System.in);

    System.out.println("Classes:");
    for(Object object : allClasses) {
        System.out.println("\t" + "-" + object.toString().substring(27));
    }

    System.out.println("---------------------");

    System.out.println("Enter class name:");
    input = in.nextLine();

    for(Object object : allClasses) {
        if (object.toString().substring(27).equals(input)) {
            o = object;
        }
    }

        if (o == null) {
            System.out.println("Class not found! Try again");
            System.out.println("---------------------");
        }

    System.out.println(o);


        System.out.println("fields :");
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName());;
        }

        System.out.println("methods :");
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");

            Parameter[] parameters = method.getParameters();

            for (int i = 0; i < parameters.length - 1; i++) {
                System.out.print(parameters[i].getType().getSimpleName() + ", ");
            }

//            if (parameters.length != 0)
//                System.out.print(parameters[(parameters.length == 0) ? 0 : parameters.length - 1].getType().getSimpleName());
            System.out.println(")");
        }
        System.out.println("---------------------");

        System.out.println("Let's create an object.");
    }
}