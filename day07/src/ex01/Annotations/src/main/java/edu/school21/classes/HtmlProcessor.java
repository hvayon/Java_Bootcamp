package edu.school21.classes;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.StandardLocation;
import java.io.File;
import java.io.FileWriter;
import java.util.Set;

@SupportedAnnotationTypes("edu.school21.classes.HtmlForm")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)

public class HtmlProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);

        for (Element e : elements) {
            createHtmlFile(e);
        }
        return false;
    }

    private void createHtmlFile(Element htmlFormElement) {
        final String FORM_CREATED_FORMAT = "<form action = \"%s\" method = \"%s\">\n";
        final String INPUT_FORMAT = "\t<input type = \"%s\" name = \"%s\" placeholder = \"%s\">\n";
        final String SUBMIT_BUTTON = "\t<input type = \"submit\" value = \"Send\">\n";
        final String FORM_CLOSE = "</form>\n";

        HtmlForm htmlForm = htmlFormElement.getAnnotation(HtmlForm.class);
        HtmlInput htmlInput;

        try (FileWriter fileWriter = new FileWriter("target/classes/" + htmlForm.fileName())) {
            File file = new File(String.valueOf(StandardLocation.SOURCE_PATH));

            fileWriter.write(String.format(FORM_CREATED_FORMAT, htmlForm.action(), htmlForm.method()));

            for (Element e : htmlFormElement.getEnclosedElements()) {
                htmlInput = e.getAnnotation(HtmlInput.class);
                if (htmlInput != null)
                    fileWriter.write(String.format(INPUT_FORMAT, htmlInput.type(), htmlInput.name(), htmlInput.placeholder()));
            }

            fileWriter.write(SUBMIT_BUTTON);
            fileWriter.write(FORM_CLOSE);

            fileWriter.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
