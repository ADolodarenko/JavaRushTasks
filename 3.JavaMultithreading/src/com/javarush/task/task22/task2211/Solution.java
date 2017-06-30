package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    static String win1251TestString = "РќР°СЂСѓС€РµРЅРёРµ РєРѕРґРёСЂРѕРІРєРё РєРѕРЅСЃРѕР»Рё?"; //only for your testing

    public static void main(String[] args) throws IOException {
        String sourceFileName = args[0];
        String destFileName = args[1];
    
        Charset sourceCharset = Charset.forName("Windows-1251");
        Charset destCharset = Charset.forName("UTF-8");
	
		InputStream sourceStream = new FileInputStream(sourceFileName);
		byte[] buffer = new byte[sourceStream.available()];
		sourceStream.read(buffer);
		sourceStream.close();
		
		String text = new String(buffer, destCharset);
		buffer = text.getBytes(sourceCharset);
	
		OutputStream destStream = new FileOutputStream(destFileName);
		destStream.write(buffer);
		destStream.close();
    }
}
