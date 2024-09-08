package com.example.contactsmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.contactsmanager.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Data source
    private ContactsDatabase contactsDatabase;
    private ArrayList<Contacts> contactsArrayList=new ArrayList<>();
    // Adapter
    private MyAdapter myAdapter1;



    //Binding
    private ActivityMainBinding activityMainBinding;
    private MainActivityClickHandler handler;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        handler = new MainActivityClickHandler(this);

        activityMainBinding.setClickHandler(handler);

        recyclerView = activityMainBinding.rcv1;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setHasFixedSize(true);


        contactsDatabase = ContactsDatabase.getInstance(this);


        // View Model


        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        // Inserting a new contact (Just for Testing):
        Contacts c1 = new Contacts("akhilesh", "akhi@gmail.com");
        viewModel.addNewContact(c1);


        //Loading the Data from Room DB

        viewModel.getAllContacts().observe(this, new Observer<List<Contacts>>() {
            @Override
            public void onChanged(List<Contacts> contacts) {

                contactsArrayList.clear();

                for (Contacts c : contacts) {
                    Log.v("TAGY", c.getName());
                    contactsArrayList.add(c);

                }
                //imp points
                myAdapter1.notifyDataSetChanged();
            }
        });

        myAdapter1 = new MyAdapter(contactsArrayList);


        recyclerView.setAdapter(myAdapter1);


        //swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Contacts c=contactsArrayList.get(viewHolder.getAdapterPosition());
                viewModel.deleteConact(c);
            }
        }).attachToRecyclerView(recyclerView);


    }
    }