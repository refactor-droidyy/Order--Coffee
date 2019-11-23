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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

public class WorkingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Toolbar tb;
    private FirebaseUser user;
    private TextView txt;
    private DatabaseReference reference;
    private Button Order,PrintRecipt;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working);


        Order = findViewById(R.id.submitOrder);
        PrintRecipt = findViewById(R.id.printRecipt);
        txt = findViewById(R.id.username_toolbar);
        tb = findViewById(R.id.toolbarworking);
        setSupportActionBar(tb);


        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users").child(user.getUid());


        Spinner spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


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

    }

    private void PrintRecipt() {


    }

    int quantity = 0;





    String priceMessage ;

    private void submitOrder() {

        EditText text = (EditText) findViewById(R.id.name);
        String value = text.getText().toString();
        CheckBox Whipped = (CheckBox) findViewById(R.id.whippes);


        CheckBox choco = (CheckBox) findViewById(R.id.choco);
        CheckBox nutella = (CheckBox) findViewById(R.id.nutella);
        CheckBox mint = (CheckBox) findViewById(R.id.mint);
        CheckBox cinnamon = (CheckBox) findViewById(R.id.cinnamon);
        CheckBox choclate = (CheckBox) findViewById(R.id.choclate);
        CheckBox butter = (CheckBox) findViewById(R.id.butter);
        if (value.length() != 0) {
            if(quantity>0){
                if (!Whipped.isChecked() && !choco.isChecked() && !nutella.isChecked() && !mint.isChecked() && !cinnamon.isChecked() && !choclate.isChecked() && !butter.isChecked()) {

                Toast.makeText(getApplicationContext(), "Select atleast One Topping only",
                        Toast.LENGTH_SHORT).show();

                }else if (Whipped.isChecked() && !choco.isChecked() && !nutella.isChecked() && !mint.isChecked() && !cinnamon.isChecked() && !choclate.isChecked() && !butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 1 * quantity + "\n" + "Total Price : " + (4 * quantity + 1 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else if (!Whipped.isChecked() && choco.isChecked() && !nutella.isChecked() && !mint.isChecked() && !cinnamon.isChecked() && !choclate.isChecked() && !butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 2 * quantity + "\n" + "Total Price : " + (4 * quantity + 2 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else if (!Whipped.isChecked() && !choco.isChecked() && nutella.isChecked() && !mint.isChecked() && !cinnamon.isChecked() && !choclate.isChecked() && !butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 3 * quantity + "\n" + "Total Price : " + (4 * quantity + 3 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else if (!Whipped.isChecked() && !choco.isChecked() && !nutella.isChecked() && mint.isChecked() && !cinnamon.isChecked() && !choclate.isChecked() && !butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 2 * quantity + "\n" + "Total Price : " + (4 * quantity + 2 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else if (!Whipped.isChecked() && !choco.isChecked() && !nutella.isChecked() && !mint.isChecked() && cinnamon.isChecked() && !choclate.isChecked() && !butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 1 * quantity + "\n" + "Total Price : " + (4 * quantity + 1 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else if (!Whipped.isChecked() && !choco.isChecked() && !nutella.isChecked() && !mint.isChecked() && !cinnamon.isChecked() && choclate.isChecked() && !butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 2 * quantity + "\n" + "Total Price : " + (4 * quantity + 2 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else if (!Whipped.isChecked() && !choco.isChecked() && !nutella.isChecked() && !mint.isChecked() && !cinnamon.isChecked() && !choclate.isChecked() && butter.isChecked()) {

                    priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                            " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 3 * quantity + "\n" + "Total Price : " + (4 * quantity + 3 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                    displayMessage(priceMessage);


                    Toast.makeText(getApplicationContext(), "Order Placed...",
                            Toast.LENGTH_LONG).show();
                    change();

                }else {


                    Toast.makeText(getApplicationContext(), "You can select only one of the toppings",
                            Toast.LENGTH_LONG).show();

                }
            } else if (quantity > 0 && !Whipped.isChecked() && !choco.isChecked()) {                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                        " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 0 + "\n" + "Total Price : " + 4 * quantity + "\n" + "Thank You  " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                Toast.makeText(getApplicationContext(), "Select atleast one topping",
                        Toast.LENGTH_LONG).show();

            } else if (quantity > 0 && Whipped.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" +
                        " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + 1 * quantity + "\n" + "Total Price : " + (4 * quantity + 1 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                SendMail();
                Toast.makeText(getApplicationContext(), "Order Placed...",
                        Toast.LENGTH_LONG).show();

            } else if (quantity > 0 && choco.isChecked()) {
                priceMessage = "Customer Name :" + value + "\n" + "Price of coffee :" + " 4 Rupees" + "\n" + "Total coffee : " + quantity + "\n" + "Topping Cost :" + (2 * quantity) + "\n" + "Total Price : " + (4 * quantity + 2 * quantity) + "\n" + "Thank You " +"\n" + "Have a nice day";
                displayMessage(priceMessage);
                SendMail();
                Toast.makeText(getApplicationContext(), "Order Placed...",
                        Toast.LENGTH_LONG).show();

            } else if (quantity == 0) {
                Toast.makeText(getApplicationContext(), "Invalid Selection....",
                        Toast.LENGTH_LONG).show();
                displayError("Please Select The Amount Of Coffee..");
            }



        } else if (value.length() == 0)

        {
            Toast.makeText(getApplicationContext(),"Please ", Toast.LENGTH_SHORT).show();
        }


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
                startActivity(new Intent(WorkingActivity.this,LoginActivity.class));
                finish();

        }
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).equals("One")){
            quantity = 1;
        }
        if (parent.getItemAtPosition(position).equals("Two")){
            quantity = 2;
        }
        if (parent.getItemAtPosition(position).equals("Three")){
            quantity = 3;
        }
        if (parent.getItemAtPosition(position).equals("Four")){
            quantity = 4;
        }
        if (parent.getItemAtPosition(position).equals("Five")){
            quantity = 5;
        }
        if (parent.getItemAtPosition(position).equals("Six")){
            quantity = 6;
        }
        if (parent.getItemAtPosition(position).equals("Seven")){
            quantity = 7;
        }
        if (parent.getItemAtPosition(position).equals("Eight")){
            quantity = 8;
        }
        if (parent.getItemAtPosition(position).equals("Nine")){
            quantity = 9;
        }
        if (parent.getItemAtPosition(position).equals("Ten")){
            quantity = 10;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
