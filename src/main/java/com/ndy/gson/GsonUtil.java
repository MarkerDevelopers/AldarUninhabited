package com.ndy.gson;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class GsonUtil<T> {

    private final String JSON_EXTENSION = ".json";
    private File jsonPathFolder;
    private Gson gson;

    public GsonUtil(File jsonPathFolder) {
        this.jsonPathFolder = jsonPathFolder;
        this.gson = new Gson();
    }

    public File getJsonPathFolder() { return jsonPathFolder; }
    private File getJsonPath(String fileName) { return new File(getJsonPathFolder(), fileName + JSON_EXTENSION); }

    public void writeGson(String fileName, T obj) {
        try{
            FileWriter writer = new FileWriter(getJsonPath(fileName));
            String json = gson.toJson(obj);
            writer.write(json);
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public T parseObject(String fileName, Class<T> clazz) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(getJsonPath(fileName)));

            return gson.fromJson(reader, clazz);
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void delete(String fileName) {
        getJsonPath(fileName).delete();
    }

    public boolean exists(String fileName) {
        return getJsonPath(fileName).exists();
    }
}
