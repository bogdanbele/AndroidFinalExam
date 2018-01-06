package dk.sidereal.finalexambogdanbele;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import dk.sidereal.finalexambogdanbele.models.Fridge;
import dk.sidereal.finalexambogdanbele.models.Product;

import static dk.sidereal.finalexambogdanbele.utils.FileHandler.readFile;
import static dk.sidereal.finalexambogdanbele.utils.FileHandler.writeFile;



public class AddProductActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

    }

    public void addProduct(View view) {

        DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);

        // Getting the selected date from the Calendar
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();

        EditText name = (EditText) findViewById(R.id.productNameInput);
        String productName = name.getText().toString();
        String productDateS = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);

        Fridge fridge = readFile();
        Product newProduct = new Product(productName, productDateS);
        newProduct.scheduleNotifications(this);
        fridge.addProduct(newProduct);
        writeFile(fridge);

        startActivity(new Intent(this, LandingScreenActivity.class));
        finish();
    }

}
