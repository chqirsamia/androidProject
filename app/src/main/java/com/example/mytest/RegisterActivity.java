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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RegisterActivity extends AppCompatActivity {
    EditText name,lastname,email,password,telephone,confirmpassword,datenaiss,cina,sex;

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

                if(!validationdate(datenaissance)||!validationEmail(user,bdd)||pass.equals("")||repass.equals("") ||nom.equals("")||datenaissance.equals("") ||!validationMotDePasse(pass,repass))
                    Toast.makeText(RegisterActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = bdd.checkemail(user);
                        if(checkuser==false){
                            //Boolean insert = bdd.insertData1(user,pass,prenom,nom ,"client",datenaissance);
                            //if(insert==true){
                                Toast.makeText(RegisterActivity.this, "veuillez terminer le formulaire", Toast.LENGTH_SHORT).show();
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
    private boolean validationdate(String date) {

        boolean validation=true;
        if ( date != null) {
            if ( !date.matches( "[1]{1}[9]{1}[1-9]{2}[-][0]{1}[1-9]{1}[-][0-9]{1}[1-9]{1}" ) && !date.matches( "[1]{1}[9]{1}[1-9]{2}[-][1]{1}[0-2]{1}[-][0-9]{1}[1-9]{1}" )) {
                Toast.makeText(RegisterActivity.this, "Merci de saisir une date valide.\"", Toast.LENGTH_SHORT).show();;
                validation=false;
            }/*else {
                Date dateact = new Date();
                Date datenais = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateactu = sdf.format(dateact);

                try{
                    //Définir la date
                    datenais=sdf.parse(date);
                    if (date.compareTo(dateactu)>0)
                        Toast.makeText(RegisterActivity.this, "Cette date n'existe pas.\"", Toast.LENGTH_SHORT).show();;

                    validation=false;

                }catch(ParseException e){
                    e.printStackTrace();
                }
            }*/
        }
            else  {
            Toast.makeText(RegisterActivity.this, "Merci de saisir une date.", Toast.LENGTH_SHORT).show();;
            validation=false;
        }
        return validation;
    }
    private boolean validationEmail(String email,database bdd) {
        Boolean checkuser = bdd.checkemail(email);
        boolean validation=true;
        if ( email != null) {
            if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) )  {
                Toast.makeText(RegisterActivity.this, "Merci de saisir une adresse mail valide.", Toast.LENGTH_SHORT).show();;
                validation=false;
            }else if ( checkuser ) {
                Toast.makeText(RegisterActivity.this, "Cette adresse email est déjà utilisée, merci d'en choisir une autre.", Toast.LENGTH_SHORT).show();;

                validation=false;
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Merci de saisir une adresse mail.", Toast.LENGTH_SHORT).show();
validation=false;
        }
        return validation;
    }
    private boolean validationMotDePasse(String motDePasse,String confirmation){
        boolean validation=true;
        if (motDePasse != null && confirmation != null) {
            if (!motDePasse.equals(confirmation)) {
                Toast.makeText(RegisterActivity.this, "Les mots de passe entrés sont différents, merci de les saisir à nouveau.", Toast.LENGTH_SHORT).show();
                validation=false;
            } else if (motDePasse.trim().length() < 3) {
                Toast.makeText(RegisterActivity.this, "Les mots de passe doivent contenir au moins 3 caractères.", Toast.LENGTH_SHORT).show();;
                validation=false;
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Merci de saisir et confirmer votre mot de passe.", Toast.LENGTH_SHORT).show();;
            validation=false;

        }
        return validation;
    }

}