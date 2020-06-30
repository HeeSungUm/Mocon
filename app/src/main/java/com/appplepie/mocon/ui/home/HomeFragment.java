package com.appplepie.mocon.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appplepie.mocon.AddTodoActivity;
import com.appplepie.mocon.R;
import com.appplepie.mocon.ui.calendar.CalendarRemainderRecyclerAdapter;
import com.appplepie.mocon.TodoItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    TextView ssidTv;
    ImageButton addTodo;
    WifiManager wifiManager;
    String ssid;
    public static ArrayList<TodoItem> todoItems = new ArrayList<>();
    CalendarRemainderRecyclerAdapter adapter;

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String place = data.getStringExtra("place");
        String desc = data.getStringExtra("desc");
        String date = data.getStringExtra("date");
        String time = data.getStringExtra("time");
        TodoItem todoItem = new TodoItem(desc, place);
        if (!date.equals("") && time.equals("")){
            todoItem.setDate(date);
        }
        else if(date.equals("")){

        }
        else {
            todoItem.setDate(date);
            todoItem.setTime(time);
        }
        todoItems.add(todoItem);
        //        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                manager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);
        adapter.notifyDataSetChanged();

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        addTodo = root.findViewById(R.id.homeAddTodo);
        ssidTv = root.findViewById(R.id.homeWifiTv);
        RecyclerView recyclerView = root.findViewById(R.id.homeTodoRecycler);
        adapter = new CalendarRemainderRecyclerAdapter(todoItems);

        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        getContext().registerReceiver(mReceiver, filter);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addTodo.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AddTodoActivity.class);
            this.startActivityForResult(intent, 111);
        });

        return root;
    }
    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals (intent.getAction())){
                NetworkInfo netInfo = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (ConnectivityManager.TYPE_WIFI == netInfo.getType()){
                    if (getContext().getSystemService(getContext().WIFI_SERVICE) != null){
                        wifiManager = (WifiManager) getContext().getSystemService(getContext().WIFI_SERVICE);
                        WifiInfo wifiinfo = wifiManager.getConnectionInfo();
                        ssid = wifiinfo.getSSID();
                        ssidTv.setText(ssid);
                    }

                }

            }
        }
    };
}