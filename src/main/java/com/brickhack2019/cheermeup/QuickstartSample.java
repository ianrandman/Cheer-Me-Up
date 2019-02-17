package com.brickhack2019.cheermeup;

// Imports the Google Cloud client library

import com.google.cloud.vision.v1.AnnotateImageRequest;
import com.google.cloud.vision.v1.AnnotateImageResponse;
import com.google.cloud.vision.v1.BatchAnnotateImagesResponse;
import com.google.cloud.vision.v1.EntityAnnotation;
import com.google.cloud.vision.v1.Feature;
import com.google.cloud.vision.v1.Feature.Type;
import com.google.cloud.vision.v1.Image;
import com.google.cloud.vision.v1.ImageAnnotatorClient;
import com.google.protobuf.ByteString;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class QuickstartSample {
    public static void main(String... args) throws Exception {
        Map<String, Set<String>> categoryToFileNames = new HashMap<>();
        Map<String, Set<String>> fileNameToCategories = new HashMap<>();

        File folder = new File(".");
        File[] listOfFiles = folder.listFiles();

        // Instantiates a client
        try (ImageAnnotatorClient vision = ImageAnnotatorClient.create()) {

            for (File file: listOfFiles) {
                // The path to the image file to annotate
                String fileName = file.getName();

                // Reads the image file into memory
                Path path = Paths.get(fileName);
                byte[] data = Files.readAllBytes(path);
                ByteString imgBytes = ByteString.copyFrom(data);

                // Builds the image annotation request
                List<AnnotateImageRequest> requests = new ArrayList<>();
                Image img = Image.newBuilder().setContent(imgBytes).build();
                Feature feat = Feature.newBuilder().setType(Type.LABEL_DETECTION).build();
                AnnotateImageRequest request = AnnotateImageRequest.newBuilder()
                        .addFeatures(feat)
                        .setImage(img)
                        .build();
                requests.add(request);

                // Performs label detection on the image file
                BatchAnnotateImagesResponse response = vision.batchAnnotateImages(requests);
                List<AnnotateImageResponse> responses = response.getResponsesList();

                for (AnnotateImageResponse res : responses) {
                    if (res.hasError()) {
                        System.out.printf("Error: %s\n", res.getError().getMessage());
                        return;
                    }


                    for (int i = 0; i < 3; i++) {
                        EntityAnnotation annotation = res.getLabelAnnotationsList().get(i);

                        String category = annotation.getDescription();

                        if (!categoryToFileNames.containsKey(category)) {
                            categoryToFileNames.put(category, new HashSet<>());
                        }
                        categoryToFileNames.get(category).add(fileName);

                        if (!fileNameToCategories.containsKey(fileName)) {
                            fileNameToCategories.put(fileName, new HashSet<>());
                        }
                        fileNameToCategories.get(fileName).add(category);
                    }
//                    for (EntityAnnotation annotation : res.getLabelAnnotationsList()) {
//                        annotation.getAllFields().forEach((k, v) ->
//                                System.out.printf("%s : %s\n", k, v.toString()));
//                    }
                }
            }

            categoryToFileNames.forEach((k, v) ->
                    System.out.printf("%s : %s\n", k, v.toString()));
            System.out.println();
            fileNameToCategories.forEach((k, v) ->
                    System.out.printf("%s : %s\n", k, v.toString()));
        }
    }
}
