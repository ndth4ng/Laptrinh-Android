package com.example.buoi2;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Categories {
    String name;
    ArrayList<Furniture> arrayList;
    Bitmap image;
    String image1;
    public Categories(String name, ArrayList<Furniture> arrayList) {
        this.name = name;
        this.arrayList = arrayList;
    }

    public Categories(String name, String img) {
        this.name = name;
        this.image1 = img;
    }
    public Categories(String name, ArrayList<Furniture> arrayList, Bitmap image) {
        this.name = name;
        this.arrayList = arrayList;
        this.image = image;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Furniture> getArrayList() {
        return arrayList;
    }
    public void setArrayList(ArrayList<Furniture> arrayList) {
        this.arrayList = arrayList;
    }
    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image) {
        this.image = image;
    }
    public static Bitmap convertStringToBitmapFromAccess(Context context, String
            filename){
        AssetManager assetManager = context.getAssets();
        try {
            InputStream is = assetManager.open(filename);
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
