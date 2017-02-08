package ru.gwtstudy.gwtApp.shared;

/**
 * gwt_helloworld FieldValidator
 * Created by admin on 08.02.2017.
 */
public class FieldValidator {
    public static boolean isValidData(String data) {
        return data != null && data.length() >= 3;
    }
}