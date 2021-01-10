package com.example.uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SnackActivity extends AppCompatActivity {
    ArrayList<Product> arraySnacks = new ArrayList<>();
    static ArrayList<Product>MainArray = new ArrayList<>();
    Integer nominal = 0;
    GridView gridView;

    DrinkAdapter adapter = new DrinkAdapter(this,arraySnacks);

    public static final String EXTRA_MESSAGE = "objek"; // ini bikin objek
    public static final String FINAL_MESSAGE = "storageMainArray"; // ini bikin array

    public void init() {
        arraySnacks.add( new Product(R.drawable.lemper,"Lemper Ayam",7000));
        arraySnacks.add( new Product(R.drawable.jentik,"Jentik Manis", 3000));
        arraySnacks.add( new Product(R.drawable.onde,"Onde Onde",8000));
        arraySnacks.add( new Product(R.drawable.lapis,"Kue Lapis",5000));
    };

    public void MyOrder(View view) {
        if(MainArray.size() == 0){
            Toast.makeText(getApplicationContext(),"Order still empty",Toast.LENGTH_SHORT).show();
        }
        else{
            Intent MyOrderIntent = new Intent(this, MyOrderActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(FINAL_MESSAGE,MainArray);
            bundle.putSerializable("nominal", nominal);
            MyOrderIntent.putExtras(bundle);
            startActivity(MyOrderIntent);
            finish();
        }
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
        setContentView(R.layout.activity_drink);
        init();

        Intent mainIntent = getIntent();
        Bundle bundle = mainIntent.getExtras();

        MainArray = (ArrayList<Product>) bundle.getSerializable(MainActivity.FINAL_MESSAGE);
        nominal = (Integer) bundle.getSerializable("nominal");

        gridView = findViewById(R.id.gridViewItems);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//            Toast.makeText(getApplicationContext(), "You Clicked " + arrayDrinks.get(position).getName(), Toast.LENGTH_SHORT).show();
            Product foodsObj = arraySnacks.get(position);
            Bundle bundle = new Bundle();
            Intent intent = new Intent (getBaseContext(),OrderActivity.class);
            bundle.putSerializable(FINAL_MESSAGE,MainArray);
            bundle.putSerializable(EXTRA_MESSAGE,foodsObj);
            bundle.putSerializable("nominal", nominal);
            intent.putExtras(bundle);
            startActivity(intent);
            finish();
            }
        });
    }
}
