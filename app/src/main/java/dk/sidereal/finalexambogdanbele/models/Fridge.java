package dk.sidereal.finalexambogdanbele.models;

import java.util.ArrayList;
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


}
