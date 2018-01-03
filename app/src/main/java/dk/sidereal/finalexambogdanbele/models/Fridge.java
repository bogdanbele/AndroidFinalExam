package dk.sidereal.finalexambogdanbele.models;

import android.icu.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class Fridge {

    private List<Product> productList = new ArrayList<>();


    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        this.getProductList().add(product);
    }

    public void emptyFridge() { this.getProductList().removeAll(productList); }

    public List<Product> getExpiredProducts() {

        final int size = productList.size();
        List<Product> expiredProductsList = new ArrayList<>();
        for (int i = 0; i < size; i++)
        {
            Product product = productList.get(i);
            //do something with i
            Date currentTime = Calendar.getInstance().getTime();
            if ( product.getExpirationDate().before(currentTime) ){
            expiredProductsList.add(product);
            }
        }


        return expiredProductsList;

    }


}
