package com.example.supermarket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;



public class cartFunction extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private FirebaseAuth fAuth;
    private FirebaseDatabase fDb = FirebaseDatabase.getInstance();
    private myAdapterCartFunction cart_fun_Adapter;
    FirebaseUser user;
    FirebaseFirestore fStore;
    String uId;
    ProgressBar cartbar;
    TextView totalcart;
    Integer childrenCount;
    private Integer total=0;
    private ArrayList<modelCartFunction> funlist;

    Button navButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_function);

        cartbar = findViewById(R.id.cartBar);

        navButton = findViewById(R.id.navigateItemsFromCart);


/*       on nav click  *****************UNITY CONNECTION*********************************** */

        navButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i1 = new Intent(cartFunction.this,unityActivity.class);
                        startActivity(i1);

                /*Log.d("alert","button is working");
                Intent unity = new Intent(cartFunction.this,unityActivity.class);
                startActivity(unity);
                finish();*/

                //openNewActivity();

            }
        });



    /*    public void openFolder(){
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                    +  File.separator + "myFolder" + File.separator);
            intent.setDataAndType(uri, "text/csv");
            startActivity(Intent.createChooser(intent, "Open folder"));
        } */

 /*                  TESTING

        public void onUnityLoad(View v) {
            Intent intent = new Intent(this, androidUnity.class);
            startActivity(intent);
        }

        public void onUnityUnload(View v) {
            if(androidBuild.instance != null)
                androidBuild.instance.finish(); */


        //               data base hooks
        totalcart = (TextView)findViewById(R.id.txtTotalPrice);
        recyclerViewCart = findViewById(R.id.rv_cartRecylerFun);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));

        funlist = new ArrayList<>();
        cart_fun_Adapter = new myAdapterCartFunction(this,funlist);
        recyclerViewCart.setAdapter( cart_fun_Adapter);
        user = fAuth.getInstance().getCurrentUser();
        uId = user.getUid();

        DatabaseReference rootCart = fDb.getReference().child("user_orders").child(uId);
        rootCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    modelCartFunction cartModel = dataSnapshot1.getValue(modelCartFunction.class);
                    funlist.add(cartModel);


                }

                cart_fun_Adapter.notifyDataSetChanged();

                DatabaseReference totalitems = FirebaseDatabase.getInstance().getReference().child("user_orders").child(uId);
                totalitems.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            total = (int)snapshot.getChildrenCount();
                            totalcart.setText(Integer.toString(total));

                            cartbar.setVisibility(View.INVISIBLE);
                        }
                        else {
                            totalcart.setText("0");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public void back(View v) {
        Intent backIntent = new Intent(cartFunction.this,Store.class);
        startActivity(backIntent);
    };





    /*public void openNewActivity(){
        Intent i1 = new Intent();
        try {
            i1 = new Intent(cartFunction.this,
                    Class.forName("com.unity3d.player.UnityPlayerActivity")); 
            startActivity(i1);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }*/





}