package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.view.View;

public class LoginPage extends AppCompatActivity {
    TextInputLayout username,password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login_page);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        signIn=findViewById(R.id.signInBtn);
        signIn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                authenticateUsers();
            }
        });
    }


    public void authenticateUsers(){
        String usernameEntered=username.getEditText().getText().toString().trim();
        String passEntered=password.getEditText().getText().toString().trim();

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference("Users");
        Query checkUser=reference.orderByChild("Username").equalTo(usernameEntered);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    //username.setError(null);
                    //username.setErrorEnabled(false);
                    String passfromdb=snapshot.child(usernameEntered).child("Password").getValue(String.class);
                    if(passfromdb.equals(passEntered)){

                        //password.setError(null);
                        //password.setErrorEnabled(false);

                        String usernamefromdb=snapshot.child(usernameEntered).child("Username").getValue(String.class);
                        //String namefromdb=snapshot.child(usernameEntered).child("name").getValue(String.class);

                        Intent intent =new Intent(getApplicationContext(),HomePage.class);
                        intent.putExtra("Username",usernamefromdb);
                        intent.putExtra("Password",passfromdb);
                        // intent.putExtra("Name",namefromdb);

                        startActivity(intent);
                    }
                    else{
                        Log.v("error","wrong password");
                        //password.setError("wrong password");
                        //password.requestFocus();
                    }
                }
                else{
                    Log.v("error","No such User exist");
                    //username.setError("No such User exist");
                    //username.requestFocus();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}