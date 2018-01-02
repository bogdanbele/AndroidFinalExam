package dk.sidereal.finalexambogdanbele;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import dk.sidereal.finalexambogdanbele.utils.Product;

/**
 * Created by Bogdan on 12/26/2017.
 */

public class LandingScreenActivity extends AppCompatActivity {


    Set<String> localSet;
    static final String filename = "products.txt";


    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int PERMISSION_REQUEST_CODE2 = 2;


    private ListView listView;
    private ProductAdapter pAdapter;
    static ArrayList<Product> productsList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_screen);


        //ListView shoppingList = (ListView) findViewById(R.id.productList);
        //readFile();

        listView = (ListView) findViewById(R.id.productList);
        ArrayList<Product> productsList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        productsList.add(new Product("Maioneza", "20/12/2011"));
        productsList.add(new Product("Bec", "23/11/2051"));
        productsList.add(new Product("Remblar", "03/02/2021"));
        productsList.add(new Product("Ciocolata Neagra", "03/02/2011"));
        pAdapter = new ProductAdapter(this, productsList);
        writeFile();


        listView.setAdapter(pAdapter);
        Product product = createDummyObject();

        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(product);
        System.out.println(json);
        File sdCardFile;
        sdCardFile = new File(Environment.getExternalStorageDirectory() + "/test.json");
        //2. Convert object to JSON string and save into a file directly


        try (FileWriter writer = new FileWriter(sdCardFile)) {

            gson.toJson(product, writer);


        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Reader reader = new FileReader(Environment.getExternalStorageDirectory() + "/test.json")) {
            // Convert JSON to Java Object
            Product productI = gson.fromJson(reader, Product.class);
            System.out.println(productI);

            // Convert JSON to JsonElement, and later to String
            /*JsonElement json = gson.fromJson(reader, JsonElement.class);
            String jsonInString = gson.toJson(json);
            System.out.println(jsonInString);*/
            System.out.println(productI.getName() + " is in the file");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void requestStoragePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
    }

    private void requestStoragePermission2(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }

        //And finally ask for the permission
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE2);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if(requestCode == PERMISSION_REQUEST_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
        if(requestCode == PERMISSION_REQUEST_CODE2){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }




    private static Product createDummyObject() {

        return new Product("Bec","23/11/2051");


    }


    void readFile(){
        localSet = new HashSet<>();
        FileInputStream fis = null;
        try {
            fis = openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null && line.length() != 0) {

      //          productsList.add(line);
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    void writeFile(){

        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            final StringBuilder stringBuilder = new StringBuilder();
            for (Product item : productsList) {
                stringBuilder.append(item);
                stringBuilder.append('\n');
            }
            outputStream.write(stringBuilder.toString().getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    List<Product> products = null;
    FileOutputStream outputStream;



}
