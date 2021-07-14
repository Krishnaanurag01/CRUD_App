package com.example.crudapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.crudapp.Contact.Contact;
import com.example.crudapp.DBhandler.DbHandler;
import com.example.crudapp.RecycleViewAdapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Contact> contactListforAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHandler db = new DbHandler(MainActivity.this);

        Contact Anurag = new Contact("Anurag","98118902121");
        db.addContacts(Anurag);

        Contact Rahul = new Contact("Rahul","98118902121");
        db.addContacts(Rahul);
        Contact priya = new Contact("Priya","98118902121");
        db.addContacts(priya);
        Contact Ankita = new Contact("Ankita","98118902121");
        db.addContacts(Ankita);
        Contact Ankit = new Contact("Ankit","98118902121");
        db.addContacts(Ankit);
        Contact Aman = new Contact("Aman","98118902121");
        db.addContacts(Aman);
        Contact Alice = new Contact("Alice","98118902121");
        db.addContacts(Alice);



        // command to delete contacts by name.
//        db.deleteContactByName("Anurag");
        contactListforAdapter=new ArrayList<>();
        List<Contact> allRecords = db.getAllContacts();
        for (Contact contact: allRecords) {
            int i=1;
            Log.d("Records", "Record no "+i+" :"+"Name - "+contact.getName()+", id : "+ contact.getId());
            contactListforAdapter.add(contact);
        }

        recyclerView=findViewById(R.id.mainRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this,contactListforAdapter);
        recyclerView.setAdapter(recyclerViewAdapter);

    }
}