package com.brickhack2019.cheermeup;

import com.google.appengine.repackaged.com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new Gson();

        Map<String, Set<String>> categoryToFileNames = new HashMap<>();
        Map<String, Set<String>> fileNameToCategories = new HashMap<>();

        Scanner cat = new Scanner(new File("categoryToFileNames"));
        textToHashMap(categoryToFileNames, cat);

        Scanner file = new Scanner(new File("fileNameToCategories"));
        textToHashMap(fileNameToCategories, file);

        String ftcJson = gson.toJson(categoryToFileNames);

        Type ctfType = new TypeToken<Map<String, Set<String>>>(){}.getType();
        Map<String, Set<String>> ctf = gson.fromJson(ftcJson, ctfType);

        String ctfJson = gson.toJson(fileNameToCategories);

        Type ftcType = new TypeToken<Map<String, Set<String>>>(){}.getType();
        Map<String, Set<String>> ftc = gson.fromJson(ctfJson, ftcType);

        ///////////////////////////////////////////////////////////////////////

        Map<String, Double> categoryToProb = new HashMap<>();
        Map<String[], Double> categoryFileNameToProb = new HashMap<>();

        categoryToFileNames.forEach((category, fileNames) -> {
            categoryToProb.put(category, 1.0 / categoryToFileNames.size());

            categoryToFileNames.get(category).forEach((fileName) -> {
                String[] categoryFileName = new String[2];
                categoryFileName[0] = category;
                categoryFileName[1] = fileName;

                categoryFileNameToProb.put(categoryFileName, 1.0 / categoryToFileNames.get(category).size());
            });

            System.out.printf("%s : %s\n", category, fileNames.toString());
        });


        System.out.println();

        fileNameToCategories.forEach((fileName, categories) -> {
            System.out.printf("%s : %s\n", fileName, categories.toString());
        });



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
