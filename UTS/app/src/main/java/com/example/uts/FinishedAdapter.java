package com.example.uts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class FinishedAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    static ArrayList<Product> arrayMyOrder;
    public static final String FINAL_MESSAGE = "storageMainArray";

    public FinishedAdapter(Context context, ArrayList<Product> arrayMyOrder) {
        this.context = context;
        this.arrayMyOrder = arrayMyOrder;
    }

    @Override
    public int getCount() {
        return arrayMyOrder.size();
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
    public View getView(final int position, View CartView, ViewGroup viewGroup) {
        if (CartView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (CartView == null) {
            CartView = inflater.inflate(R.layout.finishedorder, null);
        }

        Product myOrder = arrayMyOrder.get(position);
        TextView nameView = CartView.findViewById(R.id.itemName);
        TextView qtyView = CartView.findViewById(R.id.itemQty);
        TextView priceView  = CartView.findViewById(R.id.itemPrice);

        nameView.setText(myOrder.getName());
        qtyView.setText(Integer.toString(myOrder.getQty()) + " x ");
        priceView.setText("Rp " + Integer.toString(myOrder.getPrice()));
        return CartView;
    }
}

