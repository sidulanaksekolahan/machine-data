package com.example.imagemachine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MachineDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_detail);
        Machine machine = getIntent().getParcelableExtra("MACHINE_DATA");
        Toast.makeText(this, "Machine: " + machine, Toast.LENGTH_LONG).show();

        // find view by id
        TextView tvIdDynamic = findViewById(R.id.id_dynamic_tv);
        TextView tvMachineNameDynamic = findViewById(R.id.machine_name_dynamic_tv);
        TextView tvMachineTypeDynamic = findViewById(R.id.machine_type_dynamic_tv);
        TextView tvMachineQrDynamic = findViewById(R.id.machine_qr_dynamic_tv);

        // set text view
        String id = String.valueOf(machine.getId());
        tvIdDynamic.setText(id);
        tvMachineNameDynamic.setText(machine.getmMachineName());
        tvMachineTypeDynamic.setText(machine.getmMachineType());
        String qr = String.valueOf(machine.getQrCode());
        tvMachineQrDynamic.setText(qr);

    }
}
