package org.pengfei.Lesson13_Common_Data_Structure.L13_S9_Maps_HashTables_SkipLists;

import org.pengfei.Lesson13_Common_Data_Structure.L13_S8_Priority_Queues.MyEntry;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WordCount {

    public static void main(String[] args){

        Map<String,Integer> freq =new HashMap<>();
        // Scan input for words, using all nonletters as delimiters
        Scanner doc=new Scanner(System.in).useDelimiter("[^a-zA-Z]");
        while(doc.hasNext()){
            String word=doc.next().toLowerCase();
            if(word.equals("end")){
                doc.close();
                break;
            }
            Integer count=freq.get(word);
            if(count==null) count=0;
            freq.put(word,1+count);


        }

        int maxCount=0;
        String maxWord="no word";

        for(Map.Entry<String, Integer> ent: freq.entrySet()){
            if (ent.getValue() > maxCount) {
                maxWord=ent.getKey();
                maxCount=ent.getValue();
            }
        }

        System.out.println("The most frequent word is : "+maxWord);
        System.out.println("The word count is : "+ maxCount);
    }
}
