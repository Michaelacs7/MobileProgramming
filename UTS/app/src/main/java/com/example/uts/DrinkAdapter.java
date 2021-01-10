package com.example.uts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DrinkAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    static ArrayList<Product> arrayDrinks;

    public DrinkAdapter(Context c, ArrayList<Product> arrayDrinks) {
        context = c;
        this.arrayDrinks = arrayDrinks;
    }

    @Override
    public int getCount() {
        return arrayDrinks.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.items, null);
        }

        Product drinkPosition = arrayDrinks.get(position);
        TextView nameView = convertView.findViewById(R.id.itemName);
        TextView priceView = convertView.findViewById(R.id.itemPrice);
        ImageView imgView = convertView.findViewById(R.id.foodImage);

        imgView.setImageResource(drinkPosition.getImg());
        nameView.setText(drinkPosition.getName());
        priceView.setText("Rp " + Integer.toString(drinkPosition.getPrice()));
        return convertView;
    }

}
