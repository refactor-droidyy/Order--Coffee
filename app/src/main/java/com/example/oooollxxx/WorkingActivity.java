package com.example.oooollxxx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class WorkingActivity extends AppCompatActivity {

    private Toolbar tolbar;
    private FirebaseUser user;
    private TextView txt;
    private DatabaseReference reference;
    private Button Order,PrintRecipt,inc,dec;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);


        Order = findViewById(R.id.submitOrder);
        PrintRecipt = findViewById(R.id.printRecipt);
        txt = findViewById(R.id.username_toolbar);
        inc = findViewById(R.id.increment_by_1);
        dec = findViewById(R.id.decrement_by_1);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User userr = dataSnapshot.getValue(User.class);
                assert userr != null;
                txt.setText(userr.getUsername());

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitOrder();

            }
        });

        PrintRecipt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrintRecipt();
            }
        });

        inc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increment();
            }
        });

        dec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrement();
            }
        });
    }

    private void PrintRecipt() {


    }

    int quantity = 0;


    private void increment() {


        quantity = quantity + 1;

        displayquantity(quantity);


    }

    private void decrement() {
        int res = 0;
        quantity = quantity - 1;
        if (quantity <= 0) {
            quantity = res;
        }
        displayquantity(quantity);

    }


    String priceMessage ;

    private void submitOrder() {

        EditText text = (EditText) findViewById(R.id.name);
        String value = text.getText().toString();
        CheckBox Whipped = (CheckBox) findViewById(R.id.whippes);


        CheckBox choco = (CheckBox) findViewById(R.id.choco);
        if (value.length() != 0) {
            if (quantity > 0 && Whipped.isChecked() && choco.isChecked()) {

                Toast.makeText(getApplicationContext(), "Select Any One  Topping",
                        Toast.LENGTH_SHORT).show();

            } else if (quantity > 0 && !Whipped.isChecked() && !choco.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                        " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 0 + "\n" + "Total Price : " + 4 * quantity + "\n" + "Thank You  " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                Toast.makeText(getApplicationContext(), "Order Not Placed...",
                        Toast.LENGTH_LONG).show();
                change();
            } else if (quantity > 0 && Whipped.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                        " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 1 * quantity + "\n" + "Total Price : " + (4 * quantity + 1 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                Toast.makeText(getApplicationContext(), "Order Alwayse Placed...",
                        Toast.LENGTH_LONG).show();
                change();
            } else if (quantity > 0 && choco.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" + " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + (2 * quantity) + "\n" + "Total Price : " + (4 * quantity + 2 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                Toast.makeText(getApplicationContext(), "Order Thankyou Placed...",
                        Toast.LENGTH_LONG).show();
            } else if (quantity == 0) {
                Toast.makeText(getApplicationContext(), "Invalid Thankyou Selection....",
                        Toast.LENGTH_LONG).show();
                displayError("Please Select The Amount Of Coffee..");
            }



        } else if (value.length() == 0)

        {
            Toast.makeText(getApplicationContext(),"Please ", Toast.LENGTH_SHORT).show();
        }


    }




    private void displayquantity(int number) {

        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);


    }

    private void change(){
        TextView PriceTextView = (TextView) findViewById(R.id.Order_sum);
        PriceTextView.setText("Order \nSummary:");
    }




    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }




    private void displayError(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_logout:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(WorkingActivity.this,MainActivity.class));
                finish();

        }
        return true;
    }

}
