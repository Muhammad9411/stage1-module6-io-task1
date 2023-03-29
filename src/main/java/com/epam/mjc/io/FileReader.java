package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public static Profile getDataFromFile(File file) {

        Map<String, Object> map = new HashMap<>();

        try (java.io.FileReader fr = new java.io.FileReader(file)) {
            String line;
            BufferedReader reader = new BufferedReader(fr);
            while ((line = reader.readLine()) != null) {
                String[] keyValuePair = line.split(":");
                if (keyValuePair.length > 1) {
                    String key = keyValuePair[0];
                    String value = keyValuePair[1];
                    map.put(key, value.replace(" ", ""));
                } else {
                    System.out.println("No Key:Value found in line, ignoring: " + line);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Profile profile = new Profile();
        profile.setName(map.get("Name").toString());
        profile.setAge(Integer.parseInt(map.get("Age").toString()));
        profile.setEmail(map.get("Email").toString());
        profile.setPhone(Long.parseLong(map.get("Phone").toString()));

        return profile;
    }
}
