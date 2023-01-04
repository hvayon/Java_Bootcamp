package edu.school21.classes;

import java.lang.annotation.*;
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.FIELD)
public @interface HtmlInput {
    String type();
    String name();
    String placeholder();
}