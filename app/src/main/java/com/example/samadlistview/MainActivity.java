package com.example.samadlistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.FirebaseAuthCredentialsProvider;

public class MainActivity extends AppCompatActivity {


    String [] name = {"True berry","Stone fruit","Pome","Lilium","Magnolia",
            "Raspberry","Pawpaw","Blackberry","Strawberry","Fig",
            "Osage orange","Mulberry","Pineapple","Banana","Blackcurrant","Blueberry","Chili pepper","Gooseberry","Eggplant","Tomato"};


    String [] desc = {"This is True berry","This is Stone fruit","This is Pome",
            "This is Boysenberry","This is Lilium","This is Magnolia",
            "This is Raspberry","This is Pawpaw","This is Blackberry","This is Strawberry",
            "This is Fig","This is Osage orange",
            "This is Mulberry","This is Pineapple","This is Banana","This is Blackcurrant",
            "This is Blueberry","This is Chili pepper",
            "This is Gooseberry","This is Eggplant","This is Tomato"};


    int [] noimage = {R.drawable.berries,R.drawable.nectarine,R.drawable.mespilus,R.drawable.boysenberries,
    R.drawable.lily,R.drawable.magnolia,R.drawable.fertodi,R.drawable.asimina,R.drawable.ripeninggreenblackberries
    ,R.drawable.gardenstrawberry,R.drawable.fig,R.drawable.maclura,R.drawable.morus,R.drawable.pineapple,R.drawable.banana,
    R.drawable.ribes,R.drawable.blueberries,R.drawable.chilipepper,R.drawable.stachelbeeren,R.drawable.solanum,R.drawable.tomato};


    RecyclerView recyclerView;
    MyAdaptor myAdaptor;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        System.out.println(name.length);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdaptor = new MyAdaptor(getApplicationContext(),name,desc,noimage);
        recyclerView.setAdapter(myAdaptor);



    }


}