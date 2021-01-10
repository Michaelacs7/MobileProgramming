package com.example.uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TopUpActivity  extends AppCompatActivity {
    ArrayList<Product> arrayTopup = new ArrayList<>();
    static ArrayList<Product>MainArray = new ArrayList<>();
    public static final String FINAL_MESSAGE = "storageMainArray"; // ini bikin array
    Integer nominal = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nominal = (Integer) bundle.getSerializable("nominal");
        MainArray = (ArrayList<Product>) bundle.getSerializable(FINAL_MESSAGE);

    }

    public void inputTotal(View view){
        EditText balanceAmount = findViewById(R.id.topupamount);
        Integer input = Integer.parseInt(balanceAmount.getText().toString());
        if(input<= 0){
            Toast.makeText(getApplicationContext(),"Minimum amount for top up cant be less than 0",Toast.LENGTH_SHORT).show();
        }
        else if(input >2000000){
            Toast.makeText(getApplicationContext(),"Balance must be less than 2 million",Toast.LENGTH_SHORT).show();
        }
        else{
            nominal += input;
            Bundle bundle = new Bundle();
            Intent intent = new Intent(this, MainActivity.class);
//            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            bundle.putSerializable(FINAL_MESSAGE,MainArray);
            bundle.putSerializable("nominal", nominal);
            intent.putExtras(bundle);
            startActivity(intent);
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
}
