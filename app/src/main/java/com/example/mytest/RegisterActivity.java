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
public class RegisterActivity extends AppCompatActivity {
    EditText name,lastname,email,telephone,password,confirmpassword,datenaiss,cina,sex;
    Button signup;
    RadioButton homme,femme;
    RadioGroup group;
    database bdd;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("s'identifier");
        name=(EditText) findViewById(R.id.name);
        lastname=(EditText) findViewById(R.id.lastname);
        email=(EditText) findViewById(R.id.email);

        datenaiss=(EditText) findViewById(R.id.datenaiss);

        password=(EditText) findViewById(R.id.password);
        confirmpassword=(EditText) findViewById(R.id.confirmpassword);
        next=(Button) findViewById(R.id.suivantbutton);

        bdd = new database(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String prenom = name.getText().toString();
                String nom = lastname.getText().toString();
                String user = email.getText().toString();
                //String phone = telephone.getText().toString();
                String datenaissance= datenaiss.getText().toString();
               // String cin = cina.getText().toString();
                //String sexe ;
                String pass = password.getText().toString();
                String repass = confirmpassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("") ||nom.equals("")||datenaissance.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = bdd.checkemail(user);
                        if(checkuser==false){
                            //Boolean insert = bdd.insertData1(user,pass,prenom,nom ,"client",datenaissance);
                            //if(insert==true){
                                Toast.makeText(RegisterActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                intent.putExtra("prenom",prenom);
                                intent.putExtra("nom", nom);
                                intent.putExtra("user", user);
                                intent.putExtra("datenaissance", datenaissance);
                                intent.putExtra("pass", pass);
                                intent.putExtra("repass", repass);
                                startActivity(intent);
                           /* }else{
                                Toast.makeText(RegisterActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }*/
                        }
                        else{
                            Toast.makeText(RegisterActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}