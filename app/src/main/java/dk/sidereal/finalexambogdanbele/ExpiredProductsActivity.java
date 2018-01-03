package dk.sidereal.finalexambogdanbele;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import dk.sidereal.finalexambogdanbele.models.Fridge;
import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 1/3/2018.
 */

public class ExpiredProductsActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expired_products);

        ListView listView = (ListView) findViewById(R.id.expiredList);
        Fridge fridge = readFile();
        listView.setAdapter(new ExpiredAdapter(this, fridge));
    }

    Fridge readFile() {
        Fridge fridge = null;

        try (Reader reader = new FileReader(Environment.getExternalStorageDirectory() + "/test.json")) {
            // Convert JSON to Java Object
            return new Gson().fromJson(reader, Fridge.class);

        } catch (IOException e) {
            e.printStackTrace();
            return createDummyObject();
        }


    }
    private static Fridge createDummyObject() {


        Product test = new Product("Test if empty", "23/11/2051");
        Fridge fridge = new Fridge();
        fridge.addProduct(test);
        return fridge;


    }
}
