package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Product> MainArray = new ArrayList<>();
    public static final String FINAL_MESSAGE = "storageMainArray";
    Integer nominal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nominal = 50000;

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle !=null){
            if(bundle.containsKey("storageMainArray") ){
                MainArray = (ArrayList<Product>) bundle.getSerializable(FINAL_MESSAGE);
            }

            if(bundle.containsKey("nominal")){
                nominal = (Integer) bundle.getSerializable("nominal");
                Log.e("storageMainArray", nominal.toString());
            }
        }
    }

    public void sendDrink(View view){
        Bundle bundle = new Bundle();
        Intent mainIntent = new Intent(this, DrinkActivity.class);
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        mainIntent.putExtras(bundle);
        startActivity(mainIntent);
        finish();
    }

    public void sendFood(View view) {
        Bundle bundle = new Bundle();
        Intent mainIntent = new Intent(this, FoodActivity.class);
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        mainIntent.putExtras(bundle);
        startActivity(mainIntent);
        finish();
    }

    public void sendSnack(View view) {
        Bundle bundle = new Bundle();
        Intent mainIntent = new Intent(this, SnackActivity.class);
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        mainIntent.putExtras(bundle);
        startActivity(mainIntent);
        finish();
    }

    public void sendTopup(View view) {
        Bundle bundle = new Bundle();
        Intent Intent = new Intent(this, TopUpActivity.class);
        bundle.putSerializable(FINAL_MESSAGE,MainArray);
        bundle.putSerializable("nominal", nominal);
        Intent.putExtras(bundle);
        startActivity(Intent);
        finish();
    }


    public void sendStore(View view) {
        Intent intent = new Intent(this, StoreActivity.class);
        startActivity(intent);
        finish();
    }

    public void MyOrder(View view) {
        if(MainArray.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Order still empty", Toast.LENGTH_SHORT).show();
        }else{
            Bundle bundle = new Bundle();
            Intent mainIntent = new Intent(this, MyOrderActivity.class);
            bundle.putSerializable(FINAL_MESSAGE,MainArray);
            bundle.putSerializable("nominal", nominal);
            mainIntent.putExtras(bundle);
            startActivity(mainIntent);
            finish();
        }
    }



}