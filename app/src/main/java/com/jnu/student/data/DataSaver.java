package com.jnu.student.data;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class DataSaver {
    public void Save(Context context, ArrayList<Book> data){
        try {
            FileOutputStream dataStream = context.openFileOutput("mydata.dat",Context.MODE_PRIVATE);
            ObjectOutputStream out =new ObjectOutputStream(dataStream);
            out.writeObject(data);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @NonNull
    public ArrayList<Book> Load(Context context){
        ArrayList<Book> data = new ArrayList<>();
        try {
            FileInputStream fileIn = context.openFileInput("mydata.dat");
            ObjectInputStream in =new ObjectInputStream(fileIn);
            data = (ArrayList<Book>) in.readObject();
            in.close();
            fileIn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}
