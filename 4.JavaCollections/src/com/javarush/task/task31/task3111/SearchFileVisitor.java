package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {
    private String partOfName;
    private String partOfContent;
    private int minSize;
    private int maxSize;
    private List<Path> foundFiles = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
        boolean isPartOfName = (partOfName == null) ? true : file.getFileName().toString().contains(partOfName);
        
        boolean isPartOfContent = true;
        
        if (partOfContent != null)
        {
            isPartOfContent = false;
    
            String content = new String(Files.readAllBytes(file));
            
            if (content.contains(partOfContent))
                isPartOfContent = true;
        }
        
        boolean isMinSize = (minSize == 0) ? true : attrs.size() > minSize;
        
        boolean isMaxSize = (maxSize == 0) ? true : attrs.size() < maxSize;
        
        if (isPartOfName && isPartOfContent && isMinSize && isMaxSize)
            foundFiles.add(file);
        
        return FileVisitResult.CONTINUE;
    }
    
    public void setPartOfName(String partOfName)
    {
        this.partOfName = partOfName;
    }
    
    public void setPartOfContent(String partOfContent)
    {
        this.partOfContent = partOfContent;
    }
    
    public void setMinSize(int minSize)
    {
        this.minSize = minSize;
    }
    
    public void setMaxSize(int maxSize)
    {
        this.maxSize = maxSize;
    }
    
    public List<Path> getFoundFiles()
    {
        return foundFiles;
    }
}
