package dk.sidereal.finalexambogdanbele;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import dk.sidereal.finalexambogdanbele.models.Fridge;
import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 1/3/2018.
 */

public class AddProductActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        Calendar myCalendar = Calendar.getInstance();


    }

    public void addProduct(View view) {
        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();
        EditText name = (EditText) findViewById(R.id.productNameInput);
        String productName = name.getText().toString();
        String productDateS = Integer.toString(day) + "/" +Integer.toString(month) +"/"+ Integer.toString(year);
        Fridge fridge = readFile();
        Product newProduct = new Product(productName, productDateS);
        newProduct.scheduleNotification(this);
        fridge.addProduct(newProduct);
        writeFile(fridge);
        startActivity(new Intent(this, LandingScreenActivity.class));
        finish();
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

    void writeFile(Fridge fridge) {
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
}
