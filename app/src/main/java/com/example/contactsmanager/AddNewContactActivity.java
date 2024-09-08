package com.example.contactsmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.contactsmanager.databinding.ActivityAddNewContactBinding;

public class AddNewContactActivity extends AppCompatActivity {


    private ActivityAddNewContactBinding binding;

    private AddNewContactClickHandler handler;

    private Contacts contacts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_contact);


        MyViewModel myViewModel=new ViewModelProvider(this).get(MyViewModel.class);

        handler=new AddNewContactClickHandler(contacts,this,myViewModel);


        MyViewModel myViewModel1=new ViewModelProvider(this).get(MyViewModel.class);

contacts=new Contacts();
binding= DataBindingUtil.setContentView(this,R.layout.activity_add_new_contact);

handler=new AddNewContactClickHandler(contacts,this,myViewModel);
binding.setContact(contacts);
binding.setClickHandler(handler);



binding.setContact(contacts);

    }
}