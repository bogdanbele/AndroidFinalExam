package dk.sidereal.finalexambogdanbele;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dk.sidereal.finalexambogdanbele.models.Fridge;
import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 12/26/2017.
 */

public class LandingScreenActivity extends AppCompatActivity {


    Set<String> localSet;
    static final String filename = "products.txt";


    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE2 = 2;


    private ListView listView;

    static ArrayList<Product> productsList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);


        //ListView shoppingList = (ListView) findViewById(R.id.productList);
        //readFile();

        requestStoragePermission();

        listView = (ListView) findViewById(R.id.productList);
        ArrayList<Product> productsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        Product product = new Product("Bread","01/01/2018");
        Fridge fridge = readFile();
        fridge.addProduct(product);
        product.scheduleNotification(this);
        listView.setAdapter(new ProductAdapter(this, fridge));
    }

    @Override
    protected void onPause() {
        super.onPause();
        writeFile(((ProductAdapter) listView.getAdapter()).getFridge());
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    private void requestStoragePermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }

    private void requestStoragePermission2() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == PERMISSION_REQUEST_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }

    }


    private static Fridge createDummyObject() {


        Product test = new Product("Bec", "23/11/2051");
        Fridge fridge = new Fridge();
        fridge.addProduct(test);
        return fridge;


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
