package dk.sidereal.finalexambogdanbele.utils;

import android.os.Environment;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

import dk.sidereal.finalexambogdanbele.models.Fridge;
import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class FileHandler {


    private final Fridge fridge;

    public FileHandler() {
        this.fridge = readFile();
    }

    public FileHandler(Fridge fridge){
        writeFile(fridge);
        this.fridge = readFile();
    }



    public static Fridge readFile() {

          try (Reader reader = new FileReader(Environment.getExternalStorageDirectory() + "/test.json")) {
               // Convert JSON to Java Object
               return new Gson().fromJson(reader, Fridge.class);

          } catch (IOException e) {
               e.printStackTrace();
               return createDummyObject();
          }

     }


     public static void writeFile(Fridge fridge) {
          //1. Convert object to JSON string
          Gson gson = new Gson();
          File sdCardFile;
          sdCardFile = new File(Environment.getExternalStorageDirectory() + "/test.json");
          //2. Convert object to JSON string and save into a file directly

          try (FileWriter writer = new FileWriter(sdCardFile)) {

               gson.toJson(fridge, writer);

          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     private static Fridge createDummyObject() {

          Product test = new Product("Test if empty", "23/11/2051");
          Fridge fridge = new Fridge();
          fridge.addProduct(test);
          return fridge;


     }
}
