
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ParseException {
        String json = readString("data.json");
        List<Employee> list = jsonToList(json);
        for (Employee k : list
        ) {
            System.out.println(k);
        }
    }

    public static List<Employee> jsonToList(String json) throws ParseException {
        List<Employee> list = new ArrayList<>();
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(json);
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        for (Object j : jsonArray) {
            list.add(gson.fromJson(j.toString(), Employee.class));
        }
        return list;
    }

    public static String readString(String nameFile) {
        StringBuilder json = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(nameFile))) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                json.append(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }
}
