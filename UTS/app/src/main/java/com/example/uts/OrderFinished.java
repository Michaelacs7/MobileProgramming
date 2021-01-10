package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderFinished extends AppCompatActivity {
    static ArrayList<Product> MainArray;// array dari hal 1
    GridView gridView;
    Integer nominal = 0;
    public static final String FINAL_MESSAGE = "storageMainArray"; // ini bikin array


    private int getTotalPrice(){
        int totalPrice = 0;
        for(Product drink : MainArray){
            totalPrice += (drink.getQty() * drink.getPrice());
        }
        return totalPrice;
    }

    public void BackHome(View view) {
        Intent BackIntent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FINAL_MESSAGE,new ArrayList<Product>());
        bundle.putSerializable("nominal", nominal);
        BackIntent.putExtras(bundle);
        startActivity(BackIntent);
        finish();
    }
// tempat rei
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_finished);
        Intent MyOrderIntent = getIntent();
        Bundle bundle = MyOrderIntent.getExtras();
        MainArray = (ArrayList<Product>) bundle.getSerializable(DrinkActivity.FINAL_MESSAGE);
        nominal = (Integer) bundle.getSerializable("nominal");

//        Toast.makeText(getApplicationContext(), "You Clicked " + MainArray.get(0).getQty(), Toast.LENGTH_SHORT).show();
        FinishedAdapter adapter = new FinishedAdapter(OrderFinished.this, MainArray);
        gridView = findViewById(R.id.gridViewMyOrder);
        gridView.setAdapter(adapter);

        TextView totalPrice = findViewById(R.id.textViewTotal);
        totalPrice.setText("Total Price : Rp. "+ getTotalPrice());
    }

    @Override
    public void onBackPressed() {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        bundle.putSerializable("nominal", nominal);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }
}