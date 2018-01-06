package dk.sidereal.finalexambogdanbele;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import dk.sidereal.finalexambogdanbele.models.Fridge;
import dk.sidereal.finalexambogdanbele.models.Product;

/**
 * Created by Bogdan on 12/27/2017.
 */

public class ExpiredAdapter extends ArrayAdapter<Product> {
    private Context pContext;
    private Fridge fridge;

    public ExpiredAdapter(@NonNull Context context, @NonNull Fridge fridge) {
        super(context, 0, fridge.getExpiredProducts());
        pContext = context;
        this.fridge = fridge;
    }

    public Fridge getFridge() {
        return fridge;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent ){
        View listItem = convertView;
        if(listItem == null){
            listItem = LayoutInflater.from(pContext).inflate(R.layout.list_item,parent,false);
        }
        Product currentProduct = fridge.getExpiredProducts().get(position);
        TextView name = listItem.findViewById(R.id.textView);
        name.setText((currentProduct.getName()));
        TextView date = listItem.findViewById((R.id.creationDate));
        date.setText(currentProduct.getExpirationDateToString());
        return listItem;
    }
}
