package com.example.uts;

import android.content.Context;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyOrderAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public ArrayList<Product> arrayMyOrder = new ArrayList<>();
    Integer nominal = 0;
    public static final String FINAL_MESSAGE = "storageMainArray"; // ini bikin array

    public MyOrderAdapter(Context context, ArrayList<Product> arrayMyOrder, Integer nominal) {
        this.context = context;
        this.arrayMyOrder = arrayMyOrder;
        this.nominal = nominal;
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
            CartView = inflater.inflate(R.layout.itemsmyorder, null);
        }

        Product myOrder = arrayMyOrder.get(position);

        TextView nameView = CartView.findViewById(R.id.itemName);
        TextView qtyView = CartView.findViewById(R.id.itemQty);
        TextView priceView = CartView.findViewById(R.id.itemPrice);

        nameView.setText(myOrder.getName());
        qtyView.setText(Integer.toString(myOrder.getQty()) + " x ");
        priceView.setText("Rp " + Integer.toString(myOrder.getPrice()));

        // delete
        Button DeleteButton = CartView.findViewById(R.id.DeleteButton);

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View DeleteView) {
            arrayMyOrder.remove(position);
            Integer abc = arrayMyOrder.size();
//                Toast.makeText(DeleteView.getContext(),"Quantity can't be empty" + abc.toString(),Toast.LENGTH_SHORT).show();
            Intent deleteIntent = new Intent(DeleteView.getContext(), MyOrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(FINAL_MESSAGE, arrayMyOrder);
            bundle.putSerializable("nominal", nominal);
            deleteIntent.putExtras(bundle);
            DeleteView.getContext().startActivity(deleteIntent);
            ((MyOrderActivity) DeleteView.getContext()).finish();
            }
        });

        return CartView;
    }
}
