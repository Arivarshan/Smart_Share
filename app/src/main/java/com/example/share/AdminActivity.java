package com.example.share;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class AdminActivity extends AppCompatActivity {
    Button mbtn1;
    ListView listView;
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter myAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList<BluetoothDevice> paired_devices = new ArrayList<>(); //New

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        listView = (ListView) findViewById(R.id.list1);
        mbtn1 = (Button) findViewById(R.id.btn_scan1);
        myAdapter.startDiscovery();
        exebtn();
        exbtn1();
    }

    private void exbtn1() {
            mbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int  PICKFIILE_RESULT_CODE=1;
                    Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("file/*");
                    startActivityForResult(intent,PICKFIILE_RESULT_CODE);
                }
            });
    }


    private void exebtn() {

        Set<BluetoothDevice> bt = myAdapter.getBondedDevices();

        String[] strings = new String[bt.size()];
        int index = 0;

        if (bt.size() > 0) {
            for (BluetoothDevice device : bt) {
                strings[index] = device.getName();
                paired_devices.add(device); //new
                index++;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, strings);
            listView.setAdapter(arrayAdapter);
        }
    }
}

        //New
   /*     //listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String clickItemObj = adapterView.getAdapter().getItem(index).toString();
                Toast.makeText(List_View.this, "You selected admin is  " + clickItemObj, Toast.LENGTH_SHORT).show();
                BluetoothDevice admin_node = paired_devices.get(index);
            }});
    }
} */
