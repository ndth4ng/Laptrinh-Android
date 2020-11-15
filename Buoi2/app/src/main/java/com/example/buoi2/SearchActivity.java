package com.example.buoi2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

import me.gujun.android.taggroup.TagGroup;

public class SearchActivity extends AppCompatActivity {
    SearchView searchView;
    ArrayList<Furniture> arrayList;
    Utils utils;
    ListView listView;
    FurnitureAdapter furnitureAdapter;
    TagGroup mTagGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        utils = new Utils(SearchActivity.this);
        arrayList =new ArrayList<>();
        listView = findViewById(R.id.listView);
        furnitureAdapter = new FurnitureAdapter(SearchActivity.this, arrayList);
        listView.setAdapter(furnitureAdapter);
        searchView = findViewById(R.id.search_vew);
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchFurniture(newText);
                return false;
            }
        });
        mTagGroup = findViewById(R.id.tag_group);
        mTagGroup.setTags(new String[]{"Bed", "Living",
                "Accessories","Sealy","Christopher"});
        mTagGroup.setOnTagClickListener(new TagGroup.OnTagClickListener() {
            @Override
            public void onTagClick(String tag) {
                searchView.setQuery(tag,false);
                hideSoftKeyboard(searchView);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Utils.furnitureHistory.add(arrayList.get(i));
                Toast.makeText(SearchActivity.this, i+"", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SearchActivity.this, FurnitureDetailActivity.class);
                intent.putExtra("objFurniture", arrayList.get(i));
                startActivity(intent);
            }
        });
    }

    private void searchFurniture(String newText) {
        ArrayList<Furniture> tmp = new ArrayList<>();
        if (utils.LoadFileInternal() == null) {
            listView.setVisibility(View.GONE);
            return;
        }
        for(Furniture furniture : utils.LoadFileInternal()){
            if(furniture.getName().toLowerCase().contains(newText.toLowerCase())){
                tmp.add(furniture);
            }
        }
        Toast.makeText(this, tmp.size()+"", Toast.LENGTH_SHORT).show();
        if(tmp.size() > 0){
            furnitureAdapter.arrayList.clear();
            furnitureAdapter.arrayList.addAll(tmp);
            furnitureAdapter.notifyDataSetChanged();
            listView.setVisibility(View.VISIBLE);
        }
        if(newText.isEmpty()){
            listView.setVisibility(View.GONE);
        }
    }

    public void hideSoftKeyboard(View view){
        InputMethodManager imm
                =(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}