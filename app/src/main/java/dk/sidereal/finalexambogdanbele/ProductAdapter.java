package dk.sidereal.finalexambogdanbele;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import dk.sidereal.finalexambogdanbele.utils.Product;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context pContext;
    private List<Product> productsList = new ArrayList<>();

    public ProductAdapter(@NonNull Context context, @NonNull ArrayList<Product> objects) {
        super(context, 0, objects);
        pContext = context;
        productsList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent ){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(pContext).inflate(R.layout.list_item,parent,false);
           }
        Product currentProduct = productsList.get(position);
        TextView name = (TextView) listItem.findViewById(R.id.textView);
        name.setText((currentProduct.getName()));
        TextView date = (TextView) listItem.findViewById((R.id.textView2));
        date.setText(currentProduct.getExpirationDateToString());
        return listItem;
    }
}
