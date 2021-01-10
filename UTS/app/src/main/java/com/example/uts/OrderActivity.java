package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    static ArrayList<Product> MainArray;
    public static final String FINAL_MESSAGE = "storageMainArray";
    Integer nominal = 0;
    Product data;


    public void BackOrder(View view) {
        EditText qtyText = findViewById(R.id.QuantityTotal);
        if(qtyText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Quantity can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        int qty = Integer.parseInt(qtyText.getText().toString());

        if(qty<=0){
            Toast.makeText(getApplicationContext(),"Quantity can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        data.setQty(qty);
        MainArray.add(data);

        Intent intent = new Intent(this,MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    public void MyOrder(View view) {
        EditText qtyText = findViewById(R.id.QuantityTotal);
        if(qtyText.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(),"Quantity can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        int qty = Integer.parseInt(qtyText.getText().toString());

        if(qty<=0){
            Toast.makeText(getApplicationContext(),"Quantity can't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        data.setQty(qty);
        MainArray.add(data);

        Intent MyOrderIntent = new Intent(this, MyOrderActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        MyOrderIntent.putExtras(bundle);
        startActivity(MyOrderIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        data = (Product) bundle.getSerializable(DrinkActivity.EXTRA_MESSAGE);
        MainArray = (ArrayList<Product>) bundle.getSerializable(DrinkActivity.FINAL_MESSAGE);
        nominal = (Integer) bundle.getSerializable("nominal");

        TextView textName = findViewById(R.id.textViewName);
        textName.setText(data.getName());
        TextView textPrice = findViewById(R.id.textViewPrice);
        textPrice.setText("Rp " + Integer.toString(data.getPrice()));
    }

}