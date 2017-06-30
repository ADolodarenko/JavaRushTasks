package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword()
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        boolean isCorrect = false;
        
        while (!isCorrect)
        {
            outputStream.reset();
            
            int digits = 0;
            int upperLetters = 0;
            int lowerLetters = 0;
            
            for (int i = 0; i < 8; i++)
            {
                byte c = (byte) (Math.random() * 62);
                
                if (c > 35)
                    c += 61;
                else if (c > 9)
                    c += 55;
                else
                    c += 48;
    
                if ( Character.isDigit((char)c) )
                    digits++;
    
                if ( Character.isUpperCase((char)c) )
                    upperLetters++;
    
                if ( Character.isLowerCase((char)c) )
                    lowerLetters++;
                
                outputStream.write(c);
            }
            
            if ( digits > 0 && upperLetters > 0 && lowerLetters > 0 )
                isCorrect = true;
        }
    
        return outputStream;
    }
}