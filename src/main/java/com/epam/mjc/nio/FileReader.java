package com.epam.mjc.nio;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String fileContent = "";
        Profile profile = new Profile();

        // IO Input Stream from previous task
        try (BufferedInputStream fileInputStream = new BufferedInputStream(new FileInputStream(file))) {
            fileContent = new String(fileInputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Using Files class from nio package
        try {
           fileContent = Files.readString(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }



        if (!fileContent.isEmpty()) {
            String[] keyValueArray = fileContent.split("\n");

            if (keyValueArray.length == 4) {
                profile.setName(keyValueArray[0].substring(keyValueArray[0].indexOf(" ")).trim());
                profile.setAge(Integer.parseInt(keyValueArray[1].substring(keyValueArray[1].indexOf(" ")).trim()));
                profile.setEmail(keyValueArray[2].substring(keyValueArray[2].indexOf(" ")).trim());
                profile.setPhone(Long.parseLong(keyValueArray[3].substring(keyValueArray[3].indexOf(" ")).trim()));
            }
        }

        return profile;
    }
}
