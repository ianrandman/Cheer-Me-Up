package com.brickhack2019.cheermeup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    private static Map<String, Double> categoryToProb;
    private static Map<List<String>, Double> categoryFileNameToProb;
    private static ObjectMapper gson = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        init();
    }

    public static String init() throws IOException {

        Map<String, Set<String>> categoryToFileNames = new HashMap<>();
        Map<String, Set<String>> fileNameToCategories = new HashMap<>();

        Scanner cat = new Scanner(new File("img/categoryToFileNames"));
        textToHashMap(categoryToFileNames, cat);

        Scanner file = new Scanner(new File("img/fileNameToCategories"));
        textToHashMap(fileNameToCategories, file);

        String ftcJson = gson.writeValueAsString(categoryToFileNames);

        //TypeReference<Map<String, Set<String>>> ctfType = new TypeReference<Map<String, Set<String>>>(){};
        Map<String, Set<String>> ctf = gson.readValue(ftcJson, new TypeReference<Map<String, Set<String>>>() {});

        String ctfJson = gson.writeValueAsString(fileNameToCategories);

        //Type ftcType = new TypeToken<Map<String, Set<String>>>(){}.getType();
        Map<String, Set<String>> ftc = gson.readValue(ctfJson, new TypeReference<Map<String, Set<String>>>() {});

        ///////////////////////////////////////////////////////////////////////

        categoryToProb = new HashMap<>();
        categoryFileNameToProb = new HashMap<>();

        categoryToFileNames.forEach((category, fileNames) -> {
            categoryToProb.put(category, 1.0 / categoryToFileNames.size());

            categoryToFileNames.get(category).forEach((fileName) -> {
                List<String> categoryFileName = new ArrayList<>();
                categoryFileName.add(category);
                categoryFileName.add(fileName);

                categoryFileNameToProb.put(categoryFileName, 1.0 / categoryToFileNames.get(category).size());
            });

            System.out.printf("%s : %s\n", category, fileNames.toString());
        });


        System.out.println();

        fileNameToCategories.forEach((fileName, categories) -> {
            System.out.printf("%s : %s\n", fileName, categories.toString());
        });
        return "";
    }

    public static String getCtpJson() throws JsonProcessingException {
        return gson.writeValueAsString(categoryToProb);
    }

    public static String cfnpJson() throws JsonProcessingException {
        return gson.writeValueAsString(categoryFileNameToProb);
    }

    private static void textToHashMap(Map<String, Set<String>> categoryToFileNames, Scanner cat) {
        while (cat.hasNext()) {
            String[] entry = cat.nextLine().split(" : ");
            String key = entry[0];
            String valueString = entry[1].substring(1, entry[1].length() - 1);
            Set<String> value = new HashSet<>(Arrays.asList(valueString.split(", ")));
            categoryToFileNames.put(key, value);
        }
    }
}
