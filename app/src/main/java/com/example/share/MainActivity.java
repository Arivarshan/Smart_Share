package com.example.share;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    Button btn_crt,btn_jn;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         btn_crt = (Button)findViewById(R.id.btn_crt);
         btn_jn = (Button)findViewById(R.id.btn_jn);
        final BluetoothAdapter bAdapter = BluetoothAdapter.getDefaultAdapter();
        btn_crt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bAdapter == null)
                {
                    Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!bAdapter.isEnabled()){
                        startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                        while(!bAdapter.isEnabled());
                        Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                    }
                }
                Intent in = new Intent(getApplicationContext(), AdminActivity.class);
                startActivity(in);


            }
        });
        btn_jn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!bAdapter.isEnabled()){
                    startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE),1);
                    while(!bAdapter.isEnabled());
                    Toast.makeText(getApplicationContext(),"Bluetooth Turned ON",Toast.LENGTH_SHORT).show();
                }
                Intent in = new Intent(getApplicationContext(), List_View.class);
                startActivity(in);

            }
        });
   }
}
