package controller;

import java.io.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.FileData;
import data.MainOption;

public class JsonController {

    static public MainOption readMainOption(String filePath) {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, MainOption.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    static public void writeMainOption(MainOption option, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(option);

            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            e.printStackTrace();
        }
    }

    static public FileData readFileData(String filePath) {
        try {
            File file = new File(filePath);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(file, FileData.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    static public void writeFileData(FileData data, String filePath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(data);

            FileWriter writer = new FileWriter(filePath);
            writer.write(json);
            writer.flush();
            writer.close();
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            e.printStackTrace();
        }
    }
}