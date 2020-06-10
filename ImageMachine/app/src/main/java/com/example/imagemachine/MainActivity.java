package com.example.imagemachine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private List<Machine> myList;
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mScannerView = new ZXingScannerView(this);

        int max = 10000;
        int min = 1;

        // create instance list
        myList = new ArrayList<>();
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Dm1", "Xxxxxx", 12345, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Sm2", "Bbbbbbb", 12346, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Am3", "Ccccccc", 12347, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Mm4", "Nnnnn", 12348, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Bm5", "Eeeeeee", 12349, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Lm6", "Aaaaa", 12321, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max), "Km7", "Ggggggg", 12312, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Mm8", "Hhhhhhh", 12390, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Nm9", "Iiiiiii", 32190, new Date()));
        myList.add(new Machine(getRandomIntegerBetweenRange(min, max),"Cm10", "Jjjjjjj", 12456, new Date()));

        // create object adapter
        MachineAdapter adapter = new MachineAdapter(this, myList);

        // find view id listview
        ListView listView = findViewById(R.id.list);

        // adding item listener on listview
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // getting machine object based on click item
                Machine m = (Machine) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), MachineDetailActivity.class);
                intent.putExtra("MACHINE_DATA", m);
                startActivity(intent);
            }
        });

        // set adapter to listview
        listView.setAdapter(adapter);

        // find view id for sort by name button
        Button btnSortByName = findViewById(R.id.btn_sort_by_name);
        // add a listener to btnSortByName
        btnSortByName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Scroll down and up", Toast.LENGTH_SHORT).show();
                Collections.sort(myList, new Comparator<Machine>() {
                    @Override
                    public int compare(Machine o1, Machine o2) {
                        return o1.getmMachineName().compareTo(o2.getmMachineName());
                    }
                });
            }
        });

        // find view id for sort by type button
        Button btnSortByType = findViewById(R.id.btn_sort_by_type);
        // add a listener to btnSortByType
        btnSortByType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Scroll down and up", Toast.LENGTH_SHORT).show();
                Collections.sort(myList, new Comparator<Machine>() {
                    @Override
                    public int compare(Machine o1, Machine o2) {
                        return o1.getmMachineType().compareTo(o2.getmMachineType());
                    }
                });
            }
        });

    }

    // inflate main_menu.xml
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return  true;
    }

    // handle click event on main_menu.xml
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle item selection based on id
        switch (item.getItemId()) {
            case R.id.machine_data:
//                Toast.makeText(this, "Machine Data was clicked", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                return true;
            case R.id.code_reader:
//                Toast.makeText(this, "Code Reader was clicked", Toast.LENGTH_SHORT).show();
                // open camera and scan barcode
                setContentView(mScannerView);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // method to generate random int value
    public int getRandomIntegerBetweenRange(int min, int max){
        return (int)(Math.random()*((max-min)+1))+min;
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v("TAG", rawResult.getText()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().toString());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Scan Result");
        builder.setMessage(rawResult.getText());
        AlertDialog alert1 = builder.create();
        alert1.show();

        mScannerView.resumeCameraPreview(this);
        Intent intent = new Intent(this, MachineDetailActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }
}
