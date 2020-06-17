package com.company;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {

    public static Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
        return wordCounts.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public static void main(String[] args) throws IOException {

        String InStr = "";

        InStr = new String(Files.readAllBytes(Paths.get("input.txt")));
        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

        InStr = InStr.toLowerCase();
        InStr = InStr.replaceAll("\r\n", " ");

        while(InStr.contains("  ")){
            InStr = InStr.replaceAll("  ", " ");
        }
/*
        System.out.print(InStr);
        System.out.println("");*/

        String[] WordsArr = InStr.split(" ");

        Map<String, Integer> occurrences = new LinkedHashMap<String, Integer>();

        for ( String word : WordsArr ) {
            if(word != null && word != "") {
                Integer oldCount = occurrences.get(word);
                if (oldCount == null) {
                    oldCount = 0;
                }
                occurrences.put(word, oldCount + 1);
            }
        }

        final Map<String, Integer> sortedByCount = sortByValue(occurrences);
/*
        for ( String Key : sortedByCount.keySet() ) {
            System.out.println(Key + ":" + sortedByCount.get(Key));
        }*/

//        System.out.println("");

        List <String> SortedWords = new ArrayList<>();
        int Cnt = 0;
        for(String Key : sortedByCount.keySet()) {

            SortedWords.add(Key);
            if(Cnt!=0 && Cnt != sortedByCount.get(Key)){
                SortedWords.remove(SortedWords.size() - 1);
                break;
            }
            Cnt = sortedByCount.get(Key);
        }

        Collections.sort(SortedWords);

/*        for(String Word : SortedWords) {
            System.out.println(Word);
        }*/

        for(String Word : SortedWords) {
            writer.write(Word);
            writer.newLine();
        }

        writer.close();
    }
}

