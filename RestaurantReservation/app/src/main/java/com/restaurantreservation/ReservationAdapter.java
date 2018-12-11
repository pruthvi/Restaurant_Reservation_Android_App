package com.restaurantreservation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ReservationAdapter extends ArrayAdapter<Reservation> {

    public ReservationAdapter(Context context, ArrayList<Reservation> reservations){
        super(context, 0, reservations);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Reservation reservation = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reservationlayout, parent, false);
        }

        TextView txtTableNumber = (TextView) convertView.findViewById(R.id.txtTableNumber);
        TextView txtNumberOfGuess = (TextView) convertView.findViewById(R.id.txtNumberOfGuess);
        TextView txtDate = (TextView) convertView.findViewById(R.id.txtDate);
        TextView txtTime = (TextView) convertView.findViewById(R.id.txtTime);
        TextView txtNotes = (TextView) convertView.findViewById(R.id.txtNotes);

        txtTableNumber.setText(String.valueOf(reservation.getTableNumber()));
        txtNumberOfGuess.setText(String.valueOf(reservation.getNumberOfGuess()));
        txtDate.setText(String.valueOf(reservation.getDate()));
        txtTime.setText(reservation.getTime());
        txtNotes.setText(reservation.getNote());

        return convertView;

    }
}
