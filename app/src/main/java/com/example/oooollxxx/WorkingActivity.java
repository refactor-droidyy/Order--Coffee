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

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);

        txt = findViewById(R.id.username_toolbar);

        tolbar = findViewById(R.id.toolbarworking);
        setSupportActionBar(tolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
        getSupportActionBar().dispatchMenuVisibilityChanged(true);


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

    }

    int quantity = 0;


    public void increment(View view) {


        quantity = quantity + 1;

        displayquantity(quantity);


    }

    public void decrement(View view) {
        int res = 0;
        quantity = quantity - 1;
        if (quantity <= 0) {
            quantity = res;
        }
        displayquantity(quantity);

    }


    String priceMessage ;

    public void submitOrder(View view) {

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
                Toast.makeText(getApplicationContext(), "Order Placed...",
                        Toast.LENGTH_LONG).show();
                change();
            } else if (quantity > 0 && Whipped.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                        " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 1 * quantity + "\n" + "Total Price : " + (4 * quantity + 1 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                Toast.makeText(getApplicationContext(), "Order Placed...",
                        Toast.LENGTH_LONG).show();
                change();
            } else if (quantity > 0 && choco.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" + " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + (2 * quantity) + "\n" + "Total Price : " + (4 * quantity + 2 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                Toast.makeText(getApplicationContext(), "Order Placed...",
                        Toast.LENGTH_LONG).show();
            } else if (quantity == 0) {
                Toast.makeText(getApplicationContext(), "Invalid Selection....",
                        Toast.LENGTH_LONG).show();
                displayError("Please Select The Amount Of Coffee..");
            }



        } else if (value.length() == 0)

        {
            Toast.makeText(getApplicationContext(),"Please enter your name", Toast.LENGTH_SHORT).show();
        }


    }


    public void Print_Recipt(View view) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));

        intent.putExtra(Intent.EXTRA_SUBJECT, "Bill for coffee");
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        startActivity(intent);

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
