package com.javarush.task.task31.task3106;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args)
    {
        String resultFileName = args[0];
        
        List<Path> paths = new ArrayList<>();
        for (int i = 1; i < args.length; i++)
            paths.add(Paths.get(args[i]));
    
        Collections.sort(paths, new Comparator<Path>()
        {
            @Override
            public int compare(Path o1, Path o2)
            {
                return o1.getFileName().toString().compareTo(o2.getFileName().toString());
            }
        });
    
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        
        byte[] buffer = new byte[2048];
        int length;
        FileInputStream input;
        
        try
        {
            for (int i = 0; i < paths.size(); i++)
            {
                input = new FileInputStream(paths.get(i).toString());
        
                while ((length = input.read(buffer)) > 0)
                    output.write(buffer, 0, length);
        
                input.close();
            }
        }
        catch (IOException e)
        {}
    
        try (ZipInputStream zipInput = new ZipInputStream(new ByteArrayInputStream(output.toByteArray()));
             FileOutputStream plainOutput = new FileOutputStream(resultFileName))
        {
            ZipEntry entry = zipInput.getNextEntry();
            
            if (entry != null)
            {
                while ((length = zipInput.read(buffer)) > 0)
                    plainOutput.write(buffer, 0, length);
                
                zipInput.closeEntry();
            }
    
            output.close();
        }
        catch (IOException e)
        {}
    }
}
