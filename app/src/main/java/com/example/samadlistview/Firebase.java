package com.example.samadlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Firebase extends AppCompatActivity {

    TextInputEditText fullname, email, password, conform_password, address;
    MaterialButton button1;
    DatabaseReference db;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;
    ProgressDialog dialog;
    private Object Users;
    ImageView imageView;
    Uri imageUri;
    StorageReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);
        initilization();
    }


    private void initilization(){
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        conform_password = findViewById(R.id.cpassword);
        address = findViewById(R.id.address);
        button1 = findViewById(R.id.btnregister);
        imageView = findViewById(R.id.pickimage);

        db = FirebaseDatabase.getInstance().getReference().child("users");
        reference = FirebaseStorage.getInstance().getReference().child("Images");
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("User Account Created..!!");
        dialog.setMessage("Please Wait Creating Your Account");
        dialog.setCancelable(false);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accessgallery();
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imageUri != null){
                    uploadToFirebase(imageUri,v);
                }
                else{
                    Toast.makeText(Firebase.this,"Please Select Any Image",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }

    private void accessgallery(){
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent , 2);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==2 && resultCode == RESULT_OK && data != null){

            imageUri = data.getData();
            imageView.setImageURI(imageUri);

        }
    }

    private void uploadToFirebase(Uri uri, View view){

        final StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        validations(view,uri);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Firebase.this, "Uploading Failed !!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getFileExtension(Uri mUri){

        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));

    }



    private void validations(View view, Uri uri){
        dialog.show();

        DocumentReference db1 = firebaseFirestore.collection("users").document();

        String sname = fullname.getText().toString();
        String semail = email.getText().toString();
        String spassword = password.getText().toString();
        String sconform_password = conform_password.getText().toString();
        String saddress = address.getText().toString();

        if(sname.equals("")){
            Snackbar.make(view, "Please Enter Your Full Name",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(fullname);
            dialog.dismiss();
        }
        else if(semail.equals("")){
            Snackbar.make(view, "Please Enter Your Email",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(email);
            dialog.dismiss();
        }
        else if(spassword.equals("")){
            Snackbar.make(view, "Please Enter Your Password",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(password);
            dialog.dismiss();
        }
        else if(sconform_password.equals("")){
            Snackbar.make(view, "Please Enter Your Conform Password",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(conform_password);
            dialog.dismiss();
        }
        else if(saddress.equals("")){
            Snackbar.make(view, "Please Enter Your Address",Snackbar.LENGTH_SHORT).show();
            YoYo.with(Techniques.Shake).duration(1000).repeat(1).playOn(address);
            dialog.dismiss();
        }
        else{
            mAuth.createUserWithEmailAndPassword(semail,spassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Users users = new Users(sname,semail,spassword,sconform_password,saddress, uri.toString());
                        db.push().setValue(users);
                        db1.set(users);
                        Snackbar.make(view,"User Account Has Been Created",Snackbar.LENGTH_SHORT).show();
                        cleartext();
                        dialog.dismiss();
                        startActivity(new Intent(getApplicationContext(),LoginPage.class));
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    dialog.dismiss();
                    Toast.makeText(Firebase.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }


    private void cleartext(){
        fullname.setText("");
        email.setText("");
        password.setText("");
        conform_password.setText("");
        address.setText("");
        imageView.setImageResource(R.drawable.ic_baseline_image_24);
    }

}