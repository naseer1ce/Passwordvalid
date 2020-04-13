package com.najeed.passwordValidator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.regex.Pattern;

@Service
public class PasswordValidatorService {

    ArrayList<String> resultValues;
    Pattern checkCase = Pattern.compile("[A-Z]");
    Pattern letterDigits = Pattern.compile("\\d+[a-z]+|[a-z]+\\d+");
    Pattern repeatedSequence = Pattern.compile("(\\w{2,})\\1");

    public ArrayList<String> validatePassword(String password){
        resultValues = new ArrayList<String>();
        checkLength(password);
        checkCase(password);
        checkLetterDigits(password);
        checkRepeatedSequence(password);

        if(resultValues.isEmpty()){
            resultValues.add("The password is strong enough");
        }

        return resultValues;
    }

    public void checkLength(String passKey){
        int length = passKey.length();
        System.out.println(length);
        if (passKey.length() < 5 || passKey.length() > 12) {
            resultValues.add("The password must be between 5 and 12 characters in length");
        }
    }

    public void checkCase(String passKey){
        if (checkCase.matcher(passKey).find()) {
            resultValues.add("The password must only be lowercase");
        }
    }

    public void checkLetterDigits(String passKey){
        if (!letterDigits.matcher(passKey).find()) {
            resultValues.add("The password must contain at least a letter and a digit");
        }
    }

    public void checkRepeatedSequence(String passKey){
        if (repeatedSequence.matcher(passKey).find()) {
            resultValues.add("The password must not contain any sequence of characters immediately followed by the same sequence");
        }
    }

}