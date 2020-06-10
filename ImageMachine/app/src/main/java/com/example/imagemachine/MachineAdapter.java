package com.example.imagemachine;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class MachineAdapter extends ArrayAdapter<Machine> {

//    private static final String LOG_TAG = MachineAdapter.class.getSimpleName();

    /**
     * custom constructor
     * The context is used to inflate the layout file
     * The machine is the data we want to populate into this list
     */
    MachineAdapter(Activity context, List<Machine> machine) {
        super(context, 0, machine);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     * @param position The position in the list of data that should be displayed in the list item view
     * @param convertView The recycled view to populate
     * @param parent The parent ViewGroup that is used for inflation
     * @return The View for the position in the AdapterView
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // get the object located at this position in the list
        Machine currentMachine = getItem(position);
        Log.d("MachineAdapter", currentMachine.toString());

        // Find the TextView in the list_item.xml layout
        TextView nameTextView = listItemView.findViewById(R.id.machine_name);
        TextView typeTextView = listItemView.findViewById(R.id.machine_type);

        // set this text on the nameTextView
        if (currentMachine != null) {
            nameTextView.setText(currentMachine.getmMachineName());
        }
        if (currentMachine != null) {
            typeTextView.setText(currentMachine.getmMachineType());
        }


        /*
         Return the whole list item layout
         so that it can be shown in the ListView
        */
        return listItemView;
    }
}
