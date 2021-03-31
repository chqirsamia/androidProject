package com.example.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button loginbutton;
    TextView signin;
    database bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("se connecter");
        email = (EditText) findViewById(R.id.email2);
        password = (EditText) findViewById(R.id.password2);
        loginbutton = (Button) findViewById(R.id.signinbutton2);
        signin=(TextView)findViewById(R.id.signin);
        bdd = new database(this);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString();
                String pass = password.getText().toString();

                if(mail.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkpass = bdd.checkpassword(mail, pass);
                    if(checkpass==true){
                        Toast.makeText(LoginActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}