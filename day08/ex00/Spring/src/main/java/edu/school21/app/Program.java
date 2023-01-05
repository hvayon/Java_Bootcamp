package edu.school21.app;


import edu.school21.printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    //    public static void main(String[] args) {
//        PreProcessor preProcessor = new PreProcessorToUpperImpl();
//        PreProcessor preProcessor1 = new PreProcessorToLower();
//        Renderer renderer = new RendererErrImpl(preProcessor);
//        Renderer renderer1 = new RendererStandardImpl(preProcessor1);
//        PrinterWithDateTimeImpl printer1 = new PrinterWithDateTimeImpl(renderer1);
//        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
//        printer1.print("Hello");
//        System.out.println();
//        printer.setPrefix ("Prefix");
//        printer.print ("Hello!") ;
//    }

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer = context.getBean("printerWithPrefixImpl", Printer.class);
        printer.print("Hello!");
    }
}