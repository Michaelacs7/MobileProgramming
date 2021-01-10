package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyOrderActivity extends AppCompatActivity {
    static ArrayList<Product> MainArray;
    GridView gridView;
    Integer nominal = 0;

    public static final String EXTRA_MESSAGE = "objekOrder";
    public static final String FINAL_MESSAGE = "storageMainArray";

    private int getTotalPrice(){
        int totalPrice = 0;
        for(Product drink : MainArray){
            totalPrice += (drink.getQty() * drink.getPrice());
        }
        return totalPrice;
    }

    public void payNow(View view) {
        if(getTotalPrice()> nominal){
            Toast.makeText(getApplicationContext(),"Your balance is not enough. please top up",Toast.LENGTH_SHORT).show();
        }else{
            nominal -= getTotalPrice();
            Intent OrderIntent = new Intent(this, OrderFinished.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(FINAL_MESSAGE,MainArray);
            bundle.putSerializable("nominal", nominal);
            OrderIntent.putExtras(bundle);
            startActivity(OrderIntent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);

        Intent MyOrderIntent = getIntent();
        Bundle bundle = MyOrderIntent.getExtras();
        MainArray = (ArrayList<Product>) bundle.getSerializable(DrinkActivity.FINAL_MESSAGE);
        nominal = (Integer) bundle.getSerializable("nominal");

//        Toast.makeText(getApplicationContext(), "You Clicked " + MainArray.get(0).getQty(), Toast.LENGTH_SHORT).show();
        MyOrderAdapter adapter = new MyOrderAdapter(MyOrderActivity.this, MainArray, nominal);
        gridView = findViewById(R.id.gridViewMyOrder);
        gridView.setAdapter(adapter);

        TextView totalPrice = findViewById(R.id.textViewTotal);
        TextView saldo = findViewById(R.id.textViewSaldo);
        totalPrice.setText("total Price : Rp. "+ getTotalPrice());
        saldo.setText("Your Balance : Rp. "+nominal);
    }
}