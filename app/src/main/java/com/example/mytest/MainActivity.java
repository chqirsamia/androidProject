package com.example.mytest;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import bean.User;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private CircleImageView cinpicrecto,cinpicverso;
    private static final int pickpicrecto=1,pickpicverso=2;
    Uri imageUrirecto,imageUriverso;
    EditText telephone, cina, sex;
    Button signup;
    RadioButton homme, femme;
    RadioGroup group;
    database bdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("s'identifier");
        telephone = (EditText) findViewById(R.id.telephone);
        cina = (EditText) findViewById(R.id.cina);
        signup = (Button) findViewById(R.id.signupbutton);
        homme = (RadioButton) findViewById(R.id.homme);
        femme = (RadioButton) findViewById(R.id.femme);
        group = (RadioGroup) findViewById(R.id.gender);
        bdd = new database(this);
        cinpicrecto = (CircleImageView) findViewById(R.id.cinpicrecto);
        cinpicverso = (CircleImageView) findViewById(R.id.cinpicverso);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = telephone.getText().toString();
                String cin = cina.getText().toString();
                String sexe;
                String prenom;
                String nom;
                String user;
                String datenaissance;
                User utilisateur = new User();
                String pass;
                String repass;

                Intent intent = getIntent();
               /* if (intent != null){

                    if (intent.hasExtra("nom")){*/
                nom = intent.getStringExtra("nom");
                    /*}
                    if (intent.hasExtra("prenom")){*/
                prenom = intent.getStringExtra("prenom");
                    /*}
                    if (intent.hasExtra("user")){*/
                user = intent.getStringExtra("user");
                    /*}
                    if (intent.hasExtra("datenaissance")){*/
                datenaissance = intent.getStringExtra("datenaissance");
                    /*}
                    if (intent.hasExtra("pass")){*/
                pass = intent.getStringExtra("pass");
                    /*}
                    if (intent.hasExtra("repass")){*/
                repass = intent.getStringExtra("repass");
                // }
               /* try {
                    utilisateur=inscrireUser(user,pass, repass,nom,prenom, "client", datenaissance);
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            //}*/

                traiterTel(phone);
                traitercin(cin);
                if (group.getCheckedRadioButtonId() == R.id.homme)
                    sexe = "homme";
                else
                    sexe = "femme";
                utilisateur.setSexe(sexe);
                if (validationCin(cin)==false || validationTel(phone)==false)
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean insert = bdd.insertData(user, pass, prenom, nom, "client", datenaissance, cin, sexe, phone);
                    if (insert == true) {
                        Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent1);
                    } else {
                        Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

        cinpicrecto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent gallerie=new Intent();
                gallerie.setType("image/*");
                gallerie.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallerie,"choisir une photo"),pickpicrecto);*/
                selectImage(MainActivity.this,"recto");
            }
        });
        cinpicverso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent gallerie=new Intent();
                gallerie.setType("image/*");
                gallerie.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallerie,"choisir une photo"),pickpicverso);*/
                selectImage(MainActivity.this,"verso");

            }
        });
    }

    public User inscrireUser(String email, String password, String repass, String name, String lastname
            , String role, String datenaissance) throws NoSuchAlgorithmException {
        User utilisateur = new User();
        utilisateur.setNom(lastname);
        utilisateur.setRole(role);
        utilisateur.setPrenom(name);

        utilisateur.setPassword(toHexString(getSHA(password)));
        utilisateur.setEmail(email);
        utilisateur.setDatenaissance(datenaissance);
        utilisateur.setEmail(email);
        utilisateur.setPassword(password);

        return utilisateur;
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private void traiterTel(String tel) {

        validationTel(tel);

    }

    private boolean validationTel(String tel) {
        boolean validation=true;
        if (tel != null) {
            if (!tel.matches("[0]{1}[0-9]{9}[ ]*")) {
                Toast.makeText(MainActivity.this, "Merci de saisir un numéro de téléphone valide.", Toast.LENGTH_SHORT).show();
                validation=false;
            }
        } else {
            Toast.makeText(MainActivity.this, "Merci de saisir un numéro de téléphone.", Toast.LENGTH_SHORT).show();
            validation=false;
        }
        return validation;
    }

    private void traitercin(String cin) {

        validationCin(cin);


    }

    private boolean validationCin(String cin) {
        boolean validation=true;
        if (cin != null) {
            if (!cin.matches("[A-Z]{1}[0-9]{6}[ ]*")) {
                Toast.makeText(MainActivity.this, "Merci de saisir un numéro de carte d'identité valide.", Toast.LENGTH_SHORT).show();
                validation= false;
            }
        } else{
                Toast.makeText(MainActivity.this, "Merci de saisir un numéro de carte d'identité.", Toast.LENGTH_SHORT).show();
                validation= false;


        }
        return validation;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, requestCode, data);
        /*if (requestcode == pickpicrecto && resultcode == RESULT_OK) ;
        imageUrirecto = data.getData();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUrirecto);
            cinpicrecto.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }

        if(requestcode==pickpicverso && resultcode== RESULT_OK);
        imageUriverso=data.getData();
        try {
            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUriverso);
            cinpicverso.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        cinpicverso.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                      /*  Uri selectedImage =  data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                cinpicverso.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }*/
                        Intent gallerie=new Intent();
                        gallerie.setType("image/*");
                        gallerie.setAction(Intent.ACTION_GET_CONTENT);
                        imageUrirecto=data.getData();
                        try {
                            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUrirecto);
                            cinpicrecto.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    break;
                case 3:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        cinpicrecto.setImageBitmap(selectedImage);
                    }

                    break;
                case 4:
                    if (resultCode == RESULT_OK && data != null) {
                      /*  Uri selectedImage =  data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                cinpicrecto.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }*/ Intent gallerie=new Intent();
                        gallerie.setType("image/*");
                        gallerie.setAction(Intent.ACTION_GET_CONTENT);
                        imageUriverso=data.getData();
                        try {
                            Bitmap bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(),imageUriverso);
                            cinpicverso.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                    break;
            }
        }
    }

    private void selectImage(Context context,String cin) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose  picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if(cin.equals("recto"))
                    {
                        startActivityForResult(takePicture, 0);}
                    else
                    {
                        startActivityForResult(takePicture, 3);
                    }

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    if(cin.equals("recto"))
                    {
                        startActivityForResult(pickPhoto , 1);}
                    else{
                        startActivityForResult(pickPhoto , 4);
                    }

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}