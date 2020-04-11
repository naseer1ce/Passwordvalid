package com.najeed.passwordValidator.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PasswordValidatorServiceTest {

    private final String passwordLength = "The password must be between 5 and 12 characters in length";
    private final String passwordLowercase = "The password must only be lowercase";
    private final String passwordAlphaNumeric = "The password must contain at least a letter and a digit";
    private final String passwordRepeatedSequence = "The password must not contain any sequence of characters immediately followed by the same sequence";
    private final String passwordStrongEnough = "The password is strong enough";

    @Autowired
    private PasswordValidatorService passwordValidatorServiceService;

    @Test
    public void alphaOnly()
    {
        String password = "aaaaa";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.size() > 0);
        assertTrue(resultValues.contains(passwordAlphaNumeric));
    }

    @Test
    public void numericOnly()
    {
        String password = "12345";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.size() > 0);
        assertTrue(resultValues.contains(passwordAlphaNumeric));
    }

    @Test
    public void passwordTooShort()
    {
        String password = "aba1";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.contains(passwordLength));
    }

    @Test
    public void passwordTooLong()
    {
        String password = "1234567890abc";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.contains(passwordLength));
    }

    @Test
    public void upperCaseChar()
    {
        String password = "1234Ua";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.contains(passwordLowercase));
    }

    @Test
    public void repeatingSequence()
    {
        String password = "1231234a";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.contains(passwordRepeatedSequence));
    }

    @Test
    public void passwordValid()
    {
        String password = "abc1237";
        ArrayList<String> resultValues = passwordValidatorServiceService.validatePassword(password);
        assertTrue(resultValues.contains(passwordStrongEnough));
    }

}
