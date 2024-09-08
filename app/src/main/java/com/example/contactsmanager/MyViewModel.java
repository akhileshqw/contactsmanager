package com.example.contactsmanager;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class MyViewModel extends AndroidViewModel {

    private Repository myrepository;
    private LiveData<List<Contacts>> allContacts;

    public MyViewModel(@NonNull Application application ) {
        super(application);
        this.myrepository = new Repository(application);
    }



    public LiveData<List<Contacts>> getAllContacts(){
        allContacts= myrepository.getAllContacts();
        return allContacts;

    }



    public void addNewContact(Contacts contacts){

        myrepository.addContact(contacts);



    }

    public void deleteConact(Contacts contacts){
        myrepository.deleteContact(contacts);
    }



}
