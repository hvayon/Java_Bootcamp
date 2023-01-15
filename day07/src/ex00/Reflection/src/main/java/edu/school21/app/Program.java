package edu.school21.app;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;


import java.lang.reflect.*;
import java.util.*;

import java.lang.Object;
import java.util.stream.Collectors;

public class Program {

    private static String className;

    public static void main(String[] args) {

   Reflections reflections = new Reflections("edu.school21.classes", new SubTypesScanner(false));
    Set<Class<?>> allClasses = reflections.getSubTypesOf(Object.class);
        List<String> classes = allClasses.stream()
                .map(Class::getSimpleName)
                .collect(Collectors.toList());


    Scanner in = new Scanner(System.in);


        while (true) {
            System.out.println("Classes:");
            for (String aClass : classes) {
                System.out.println("\t" + "-" + aClass);
            }

            System.out.println("---------------------");

            System.out.println("Enter class name:");
            String input = in.nextLine();

            for (String aClass : classes) {
                if (aClass.equals(input)) {
                    className = input;
                    System.out.println("---------------------");
                    break;
                }
            }

            if (className == null) {
                System.out.println("Class not found! Try again");
                System.out.println("---------------------");
            } else
                break;
        }

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
            if (!method.getName().equals("toString")) {
                System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
                Parameter[] parameters = method.getParameters();

                for (int i = 0; i < parameters.length - 1; i++) {
                    System.out.print(parameters[i].getType().getSimpleName() + ", ");
                }
                if (parameters.length != 0)
                    System.out.print(parameters[parameters.length - 1].getType().getSimpleName());
                System.out.println(")");
            }

        }

    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
        System.err.println("Error");
        System.exit(-1);
    }

        System.out.println("---------------------");

        System.out.println("Let's create an object.");

        Constructor<?> constructorClass = null;
        Constructor<?>[] constructors = cls.getDeclaredConstructors();
        Parameter[] parameters = null;

        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameters().length > 0){
                constructorClass = constructor;
                parameters = constructor.getParameters();
                break;
            }
        }

        List<Object> constructorParam = new ArrayList<>();

        for (int i = 0; i < Objects.requireNonNull(parameters).length; i++) {
            System.out.println(fields[i].getName() + ":");
            constructorParam.add(getInfoObject(parameters[i], in));
        }

        if (!(constructorParam.get(constructorParam.size() -1 ) instanceof String)){
            in.nextLine();
        }

        System.out.print("Object created: ");
        Object object = null;

        try {
            object = constructorClass.newInstance(constructorParam.toArray());
            System.out.println(object);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("ERROR");
            System.exit(-1);
        }

        System.out.println("---------------------");
        System.out.println("Enter name of the field for changing:");
        String fieldName = in.nextLine();
        int i = 0;

        for (i = 0; i < fields.length; i++){
            if (fieldName.equals(fields[i].getName())){
                break;
            }
        }
        System.out.println("Enter " + fields[i].getType().getSimpleName() + " value");
        Object object1 = getInfoObject(parameters[i], in);
        fields[i].setAccessible(true);

        try {
            fields[i].set(object, object1);
        } catch (IllegalAccessException e) {
            System.err.println("Invalid parameter");
            System.exit(-1);
        }

        System.out.println("Object updated: " + object);
        System.out.println("---------------------");
        System.out.println("Enter name of the method for call");
        runMethod(methods, object, in);

   }

    private static void runMethod(Method[] methods, Object object, Scanner scanner) {
        String inputLine = scanner.nextLine();

        for (Method method : methods) {
            Class<?>[] clazz = method.getParameterTypes();
            StringBuilder parametrs = new StringBuilder();

            if (clazz.length > 0) {
                parametrs.append(clazz[0].getSimpleName());

                for (int i = 1; i < clazz.length; i++) {
                    parametrs.append(", ");
                    parametrs.append(clazz[i].getSimpleName());
                }
            }
            if (inputLine.equals(method.getName() +"(" + parametrs +")")){
                List<Object> constructorParam = new ArrayList<>();
                Parameter[] parameters = method.getParameters();

                for (Parameter parameter : parameters) {
                    System.out.println("Enter " + parameter.getType().getSimpleName() +   " value:");
                    constructorParam.add(getInfoObject(parameter, scanner));
                }
                method.setAccessible(true);

                try {
                    if (method.getReturnType().getSimpleName().equals("void")) {
                        method.invoke(object, constructorParam.toArray());
                    } else {
                        System.out.println("Method returned:");
                        System.out.println(method.invoke(object, constructorParam.toArray()));
                    }
                    return;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println("Invalid parameter");
                    System.exit(-1);
                }
                System.out.println(method.getName() + "(" + parametrs + ")");
            }
        }
        System.err.println("Method not found");
        System.exit(-1);
    }


   public static Object getInfoObject(Parameter parameter, Scanner scanner) {
       String paramName = parameter.getType().getSimpleName().toLowerCase();
       try {
           switch (paramName) {
               case "string":
                   return scanner.nextLine();
               case "int":
               case "integer":
                   return scanner.nextInt();
               case "long":
                   return scanner.nextLong();
               case "boolean":
                   return scanner.nextBoolean();
               case "double":
                   return scanner.nextDouble();
               case "float":
                   return scanner.nextFloat();
               case "char":
               case "character":
                   return scanner.nextLine().charAt(0);
           }
       } catch (InputMismatchException error) {
           System.err.println("Error type");
           System.exit(-1);
       }
       return null;
   }
}