package com.example.mayank.travelagentproject;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ShowBooking extends AppCompatActivity {

    Firebase firebase;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    YourBookingPOJO details;
    TextView yourbookingtext;


    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<YourBookingPOJO> list=new ArrayList<YourBookingPOJO>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_booking);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Your Bookings");

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter=new BookingCardAdapter(list,this);
        recyclerView.setAdapter(adapter);

        final ProgressDialog pd = new ProgressDialog(this);
        pd.setMessage("Please Wait...");
        pd.setCancelable(false);
        pd.show();


        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        FinalForm.firebase=new Firebase("https://travelagentproject-40e3a.firebaseio.com/");

        FinalForm.firebase.child("Booking Information").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                if (dataSnapshot != null && dataSnapshot.getValue() != null) {
                    if (dataSnapshot.child("userid").getValue().equals(firebaseUser.getUid())){
                        details=dataSnapshot.getValue(YourBookingPOJO.class);
                        list.add(details);
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        Toast.makeText(ShowBooking.this, "You have not booked anything yet.", Toast.LENGTH_SHORT).show();
                    }
                }
                pd.dismiss();
                }
                @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
