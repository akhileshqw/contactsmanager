package com.example.contactsmanager;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProvider;

public class AddNewContactClickHandler {

   private Contacts contacts;
   Context context;

   MyViewModel myViewModel;

    public AddNewContactClickHandler(Contacts contacts, Context context,   MyViewModel myViewModel) {
        this.contacts = contacts;
        this.context = context;
        this.myViewModel=myViewModel;
    }
    public void onSubmitBtnClicked(View view){
        if(contacts.getEmail()==null || contacts.getName()==null){
            Toast.makeText(context,"Fields can't be empty",Toast.LENGTH_SHORT).show();

        }else{
            Intent intent=new Intent(context,MainActivity.class);
            //View Model


            Contacts c=new Contacts(contacts.getName(), contacts.getEmail());

            myViewModel.addNewContact(c);

            context.startActivity(intent);



//
//            intent.putExtra("name:",contacts.getName());
//            intent.putExtra("email:",contacts.getEmail());
            context.startActivity(intent);

        }

    }



}
