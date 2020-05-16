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


public class List_View extends AppCompatActivity {
    Button mbtn;
    ListView listView;
    ArrayList<String> stringArrayList = new ArrayList<String>();
    ArrayAdapter<String> arrayAdapter;
    BluetoothAdapter myAdapter = BluetoothAdapter.getDefaultAdapter();
    ArrayList<BluetoothDevice> paired_devices = new ArrayList<>(); //New
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_list);
        listView = (ListView) findViewById(R.id.list);
        mbtn = (Button) findViewById(R.id.btn_scan);
        myAdapter.startDiscovery();
        exebtn();

    }

    private void exebtn() {
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        });

        //New
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String clickItemObj = adapterView.getAdapter().getItem(index).toString();
                Toast.makeText(List_View.this, "You selected admin is  " + clickItemObj, Toast.LENGTH_SHORT).show();
                BluetoothDevice admin_node = paired_devices.get(index);
            }});
    }
}

  /*
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
        registerReceiver(mbroad,filter);
        IntentFilter scan_filter = new IntentFilter();
        scan_filter.addAction(BluetoothDevice.ACTION_FOUND);
        scan_filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        scan_filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(scan,scan_filter);

    private final BroadcastReceiver scan = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_STARTED))
            {
                Toast.makeText(List_View.this, "Started", Toast.LENGTH_SHORT).show();
            }
            if(action.equals(BluetoothAdapter.ACTION_DISCOVERY_FINISHED))
            {}
            if(action.equals((BluetoothDevice.ACTION_FOUND)))
            {
                BluetoothDevice dev = (BluetoothDevice) intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Toast.makeText(List_View.this, "FOUND : "+dev.getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }; */
 /*   private final BroadcastReceiver mbroad = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
             if(action.equals((BluetoothDevice.ACTION_BOND_STATE_CHANGED)))
             {
                 BluetoothDevice mdevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                 if (mdevice.getBondState()==BluetoothDevice.BOND_BONDED)
                 {
                     Log.d("rec","Bonded");
                 }
                 if(mdevice.getBondState()==BluetoothDevice.BOND_BONDING)
                 {
                     Log.d("rec","Bonding");
                 }
                 if(mdevice.getBondState()==BluetoothDevice.BOND_NONE)
                 {
                     Log.d("rec","None");
                 }
             }
        }
    }; */


    /*private void exebtn() {
        Set<BluetoothDevice> bt=myAdapter.getBondedDevices();
        final ArrayList<BluetoothDevice> all_devices = new ArrayList<>();
        final String[] device_name=new String[bt.size()];
        final String[] device_addr=new String[bt.size()];
        int index=0;
        if(bt.size()<0)
        {
            for(BluetoothDevice device:bt)
            {
                device_name[index]=device.getName();
                device_addr[index]=device.getAddress();
                all_devices.add(device);
                index++;
            }
            ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,device_name);
            scanlistView.setAdapter(arrayAdapter);
        }
        scanlistView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                String clickItemObj = adapterView.getAdapter().getItem(index).toString();
                //String dvc_name= device_name[index];
                //HashMap clickItemMap = (HashMap)clickItemObj;
                //String itemTitle = (String)clickItemMap.get("name");
                Toast.makeText(List_View.this, "You select item is  " + clickItemObj, Toast.LENGTH_SHORT).show();


                //Connecting
                /*
                BluetoothDevice to_connect=all_devices.get(index);
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    Toast.makeText(List_View.this, "State 1  " + to_connect.getBondState(), Toast.LENGTH_SHORT).show();
                    try {
                        Method method = to_connect.getClass().getMethod("createBond", (Class[]) null);
                        method.invoke(to_connect, (Object[]) null);
                    }
                    catch (Exception e)
                    {
                        Toast.makeText(List_View.this, "Error  " + e, Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(List_View.this, "State 2  " + to_connect.getBondState(), Toast.LENGTH_SHORT).show();
                }
                */
/*
                BluetoothDevice admin_device=all_devices.get(index);

                try {
                    BluetoothSocket sock = admin_device.createInsecureRfcommSocketToServiceRecord(app_UUID);
                    OutputStream fout = sock.getOutputStream();
                }
                catch(Exception e)
                {}
                */


/*    public synchronized void connect(BluetoothDevice device)
    {
        ConnectThread mConnectThread = new ConnectThread(device);
        mConnectThread.start();
    }

    private class ConnectThread extends Thread{
        private final BluetoothSocket mmSocket;
        private final BluetoothDevice mmDevice;

        public ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            BluetoothSocket tmpSocket = null;

            try {
                UUID app_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
                tmpSocket = device.createRfcommSocketToServiceRecord(app_UUID);
            } catch (Exception e) {
            }
            mmSocket = tmpSocket;
        }
        public void run()
        {
            myAdapter.cancelDiscovery();
            try
            {
                mmSocket.connect();
            }
            catch (Exception e)
            {

                //connectionFailed();
                return;
            }
            connected(mmSocket,mmDevice);
        }
        public synchronized void connected(BluetoothSocket socket,BluetoothDevice device)
        {
            ConnectThread mConnectedThread = new ConnectThread(socket);
            mConnectedThread.start();

        }
    }

}

       /* mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapter.startDiscovery();
                Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
            }
        });
        IntentFilter intentFilter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(myReceiver, intentFilter);
        arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArrayList);
        scanlistView.setAdapter(arrayAdapter);
        Toast.makeText(getApplicationContext(),"Bluetooth Not Supported1",Toast.LENGTH_SHORT).show();
    }
    BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Toast.makeText(getApplicationContext(),"Bluetooth Not Supported2",Toast.LENGTH_SHORT).show();
            if (BluetoothDevice.ACTION_FOUND.equals(action))
            {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                stringArrayList.add(device.getName());
                arrayAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"Bluetooth Not Supported3",Toast.LENGTH_SHORT).show();
            }
        }
    };
} */
    /*  if(mBluetoothAdapter!=null)
       {
           if(mBluetoothAdapter.isEnabled())
           {
               mBluetoothAdapter.startDiscovery();
           }
       mReceiver = new BroadcastReceiver() {
               @Override
           public void onReceive(Context context, Intent intent) {
               String action = intent.getAction();
               Log.d("chk","in123");
               if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                   BluetoothDevice device = intent
                           .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                   mDeviceList.add(device.getName() + "\n" + device.getAddress());
                   Log.i("BT", device.getName() + "\n" + device.getAddress());
                   listView.setAdapter(new ArrayAdapter<String>(context,
                           android.R.layout.simple_list_item_1, mDeviceList));
               }
               else
                   Log.d("chk","out");
           }
       };
       }
       //End
       //IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
       //registerReceiver(mReceiver, filter);


       //this.customSimpleAdapterListView();
   }*/


    /*
    final BroadcastReceiver mReceiver = new BroadcastReceiver() {

        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("chk","in");
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent
                        .getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                mDeviceList.add(device.getName() + "\n" + device.getAddress());
                Log.i("BT", device.getName() + "\n" + device.getAddress());
                listView.setAdapter(new ArrayAdapter<String>(context,
                        android.R.layout.simple_list_item_1, mDeviceList));
            }
            else
                Log.d("chk","out");
        }
    };*/
  /*  private void customSimpleAdapterListView()
    {

        String[] B_Name = { "sudhar","arivu"};
        ArrayList<Map<String,Object>> itemDataList = new ArrayList<Map<String,Object>>();;

        int len = B_Name.length;
        for(int i =0; i < len; i++) {
            Map<String,Object> listItemMap = new HashMap<String,Object>();
            listItemMap.put("name", B_Name[i]);//listItemMap.put("st", B_Status[i]);
            itemDataList.add(listItemMap);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,itemDataList,R.layout.device_list,
             //   new String[]{"name"},new int[]{R.id.txt_name});

         listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(simpleAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long l) {
                Object clickItemObj = adapterView.getAdapter().getItem(index);
                HashMap clickItemMap = (HashMap)clickItemObj;
                String itemTitle = (String)clickItemMap.get("name");
                Toast.makeText(List_View.this, "You select item is  " + itemTitle, Toast.LENGTH_SHORT).show();
            }
        });
    }*/


