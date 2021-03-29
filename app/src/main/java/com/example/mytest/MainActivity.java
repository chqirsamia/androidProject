package com.example.mytest;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
EditText name,lastname,email,telephone,password,confirmpassword,datenaiss,cina,sex;
Button signup;
RadioButton homme,femme;
RadioGroup group;
database bdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("s'identifier");
        name=(EditText) findViewById(R.id.name);
        lastname=(EditText) findViewById(R.id.lastname);
        email=(EditText) findViewById(R.id.email);
        telephone=(EditText) findViewById(R.id.telephone);
        datenaiss=(EditText) findViewById(R.id.datenaiss);
        cina=(EditText) findViewById(R.id.cina);
        password=(EditText) findViewById(R.id.password);
        confirmpassword=(EditText) findViewById(R.id.confirmpassword);
        signup=(Button) findViewById(R.id.signupbutton);
        homme=(RadioButton) findViewById(R.id.homme);
        femme=(RadioButton) findViewById(R.id.femme);
        group=(RadioGroup)findViewById(R.id.gender);
        bdd = new database(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = name.getText().toString();
                String nom = lastname.getText().toString();
                String user = email.getText().toString();
                String phone = telephone.getText().toString();
                String datenaissance= datenaiss.getText().toString();
                String cin = cina.getText().toString();
                String sexe ;
                String pass = password.getText().toString();
                String repass = confirmpassword.getText().toString();
                if(group.getCheckedRadioButtonId() == R.id.homme)
                    sexe="homme";
                else
                    sexe="femme";
                if(user.equals("")||pass.equals("")||repass.equals("") ||nom.equals("")||phone.equals("")||datenaissance.equals("")
                        ||cin.equals("")||sexe.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = bdd.checkemail(user);
                        if(checkuser==false){
                            Boolean insert = bdd.insertData(user,pass,prenom,nom,cin,sexe
                                    ,"client",datenaissance,phone);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}