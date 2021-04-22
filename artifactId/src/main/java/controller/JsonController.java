package controller;

import java.io.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonController<T> {
    //private static ObjectMapper objectMapper = new ObjectMapper();

    //private static String mainOptionPath = "main.json";

    public T readMainOption(String filePath) {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, new TypeReference<T>(){});
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            return null;
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            return null;
        }
    }

    public void saveMainOption(T option, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(option);

            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            e.printStackTrace();
        }
    }


}