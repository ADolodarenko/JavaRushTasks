package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException
	{
		List<String> words = new ArrayList<>();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader fileReader = new BufferedReader(new FileReader(bufferedReader.readLine()));
		bufferedReader.close();
		while (fileReader.ready())
			words.addAll(Arrays.asList(fileReader.readLine().split(" ")));
		fileReader.close();
		
		for(int i = 0; i < words.size(); i++)
		{
			for(int j = 0; j < words.size();)
			{
				if(words.get(j).equals(new StringBuilder(words.get(i)).reverse().toString()) && j != i)
				{
					Pair pair = new Pair();
					pair.first = words.get(j);
					pair.second = words.get(i);
					result.add(pair);
					words.remove(j);
					words.remove(i);
					j = 0;
				}
				else
					j++;
			}
		}
		
		/*List<String> words = new ArrayList<String>();
    
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader file = new BufferedReader(new FileReader(console.readLine()));
        console.close();
    
        String line;
        while ((line = file.readLine()) != null)
        {
            String[] elements = line.split(" ");
            words.addAll(Arrays.asList(elements));
        }
    
        file.close();
    
        int i = 0;
        while (i < words.size())
        {
            String firstWord = words.get(i);
            String revWord = new StringBuilder(firstWord).reverse().toString();
            boolean hasPair = false;
        
            for (int j = i + 1; j < words.size(); j++)
            {
                String secondWord = words.get(j);
            
                hasPair = (revWord.equals(secondWord));
            
                if (hasPair)
                {
                    Pair pair = new Pair();
                    pair.first = secondWord;
                    pair.second = firstWord;
                    result.add(pair);
                
                    words.remove(j);
                    words.remove(i);
                    break;
                }
            }
        
            if (!hasPair)
                i++;
        }*/
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
