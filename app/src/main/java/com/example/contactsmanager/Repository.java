package com.example.contactsmanager;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {

    private final ContactDao contactDao;


  public   ExecutorService executor;
  public  Handler handler;


    public Repository(Application application) {

        ContactsDatabase contactsDatabase=ContactsDatabase.getInstance(application);
        this.contactDao = contactsDatabase.getContactDao();

        handler=new Handler(Looper.getMainLooper());
        executor= Executors.newSingleThreadExecutor();

    }


    public void addContact(Contacts contact){

        //text view


        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.insert(contact);

            }
        });





    }

    public void deleteContact(Contacts contact){

        executor.execute(new Runnable() {
            @Override
            public void run() {
                contactDao.delete(contact);

            }
        });

    }

    public LiveData<List<Contacts>> getAllContacts(){






                return contactDao.getAllContacts();


    }







}
