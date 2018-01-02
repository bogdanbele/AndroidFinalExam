package dk.sidereal.finalexambogdanbele.utils;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class Product {

    private String name;
    private Date expirationDate;


    public Product(String name, String expirationDate) {
        this.name = name;
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        try {
            this.expirationDate = f.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Date getExpirationDate() {
        return expirationDate;
    }

    public String getExpirationDateToString() {
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");


        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        //to convert Date to String, use format method of SimpleDateFormat class.


        return dateFormat.format(expirationDate);
        // return expirationDate.toString();


    }

    public void setExpirationDate(String expirationDate) {
        SimpleDateFormat f = new SimpleDateFormat("dd/mm/yyyy");
        try {
            this.expirationDate = f.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }
}
