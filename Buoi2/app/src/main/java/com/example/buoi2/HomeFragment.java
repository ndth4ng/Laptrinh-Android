package com.example.buoi2;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    ListView listView;
    ArrayList<Furniture> arrayList;
    FurnitureAdapter furnitureAdapter;
    Utils utils;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
// Inflate the layout for this fragment
        utils = new Utils(getContext());
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listView);
        arrayList = getMockDataDrawable(); // thay the bang cai moi
        furnitureAdapter = new FurnitureAdapter(getContext(),arrayList); // tự thêm cho đúng

        listView.setAdapter(furnitureAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(getContext(), i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), FurnitureDetailActivity.class);
                intent.putExtra("objFurniture", arrayList.get(i));
                startActivity(intent);
            }
        });

    }
    public ArrayList<Furniture> getMockData(){
        ArrayList<Furniture> tmp = new ArrayList<>();
        tmp.add(new Furniture(getString(R.string.name_product_one),
                getString(R.string.product_one),Furniture.convertStringToBitmapFromAccess(getContext()
                ,"hinh_1.png")));
        tmp.add(new Furniture(getString(R.string.name_product_two),
                getString(R.string.product_two),Furniture.convertStringToBitmapFromAccess(getContext()
                ,"hinh_2.png")));
        tmp.add(new Furniture(getString(R.string.name_product_three),
                getString(R.string.product_three),Furniture.convertStringToBitmapFromAccess(getContext
                (),"hinh_3.png")));
        tmp.add(new Furniture(getString(R.string.name_product_four),
                getString(R.string.product_four),Furniture.convertStringToBitmapFromAccess(getContext(
        ),"hinh_4.png")));
        tmp.add(new Furniture(getString(R.string.name_product_five),
                getString(R.string.product_five),Furniture.convertStringToBitmapFromAccess(getContext(
        ),"hinh_5.png")));
        tmp.add(new Furniture(getString(R.string.name_product_six),
                getString(R.string.product_six),Furniture.convertStringToBitmapFromAccess(getContext()
                ,"hinh_6.png")));
        tmp.add(new Furniture(getString(R.string.name_product_seven),
                getString(R.string.product_seven),Furniture.convertStringToBitmapFromAccess(getContext()
                ,"hinh_7.png")));
        tmp.add(new Furniture(getString(R.string.name_product_eight),
                getString(R.string.product_eight),Furniture.convertStringToBitmapFromAccess(getContext
                (),"hinh_8.png")));
        tmp.add(new Furniture(getString(R.string.name_product_nine),
                getString(R.string.product_nine),Furniture.convertStringToBitmapFromAccess(getContext(
        ),"hinh_9.png")));
        tmp.add(new Furniture(getString(R.string.name_product_ten),
                getString(R.string.product_ten),Furniture.convertStringToBitmapFromAccess(getContext(
        ),"hinh_10.png")));
        return tmp;
    }

    public ArrayList<Furniture> getMockDataDrawable(){
        ArrayList<Furniture> tmp = new ArrayList<>();
        tmp.add(new Furniture(getString(R.string.name_product_one),
                getString(R.string.product_one),R.drawable.hinh_1));
        tmp.add(new Furniture(getString(R.string.name_product_two),
                getString(R.string.product_two),R.drawable.hinh_2));
        tmp.add(new Furniture(getString(R.string.name_product_three),
                getString(R.string.product_three),R.drawable.hinh_3));
        tmp.add(new Furniture(getString(R.string.name_product_four),
                getString(R.string.product_four),R.drawable.hinh_4));
        tmp.add(new Furniture(getString(R.string.name_product_five),
                getString(R.string.product_five),R.drawable.hinh_5));
        tmp.add(new Furniture(getString(R.string.name_product_six),
                getString(R.string.product_six),R.drawable.hinh_6));
        tmp.add(new Furniture(getString(R.string.name_product_seven),
                getString(R.string.product_seven),R.drawable.hinh_7));
        tmp.add(new Furniture(getString(R.string.name_product_eight),
                getString(R.string.product_eight),R.drawable.hinh_8));
        tmp.add(new Furniture(getString(R.string.name_product_nine),
                getString(R.string.product_nine),R.drawable.hinh_9));
        tmp.add(new Furniture(getString(R.string.name_product_ten),
                getString(R.string.product_ten),R.drawable.hinh_10));
        return tmp;
    }

    @Override
    public void onPause() {
        super.onPause();
        Utils utils = new Utils(getContext());
        utils.WriteToFileInternal(arrayList);
    }
}