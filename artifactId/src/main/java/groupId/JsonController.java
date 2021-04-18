package groupId;

import java.io.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import groupId.data.MainOption;

public class JsonController {
    private static ObjectMapper objectMapper = new ObjectMapper();

    private static String mainOptionPath = "main.json";

    public static MainOption readMainOption() {
        try {
            File file = new File(mainOptionPath);
            return objectMapper.readValue(file, MainOption.class);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException : " + e.getMessage());
            return new MainOption();
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
            return new MainOption();
        }
    }

    public static void saveMainOption(MainOption option) {
        try {
            String json = objectMapper.writeValueAsString(option);

            FileWriter writer = new FileWriter(mainOptionPath);
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