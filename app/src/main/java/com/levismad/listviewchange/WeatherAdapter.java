package com.levismad.listviewchange;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;


/**
 * Created by root on 30/11/2016.
 */

public class WeatherAdapter extends ArrayAdapter<WeatherEntry> {

    private int layoutResourceId;
    //private List<WeatherEntry> mEntries;
    private static final String LOG_TAG = "WeatherListAdapter";
    private SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");

    public WeatherAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        layoutResourceId = textViewResourceId;
        //mEntries = entries;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            WeatherEntry item = getItem(position);
            View v = null;
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                v = inflater.inflate(layoutResourceId, null);

            } else {
                v = convertView;
            }

            ImageView header = (ImageView) v.findViewById(R.id.w_icon);
            TextView temperature = (TextView) v.findViewById(R.id.w_temp);
            TextView temperatureMin = (TextView) v.findViewById(R.id.w_temp_min);
            TextView rain = (TextView) v.findViewById(R.id.w_rain);
            TextView umidity = (TextView) v.findViewById(R.id.w_umidity);
            TextView date = (TextView) v.findViewById(R.id.w_date);


            temperature.setText(item.getValue().substring(0,2));
            temperatureMin.setText(item.getValueMin().substring(0,2));
            rain.setText(item.getRain());
            umidity.setText(item.getUmidity());
            date.setText(fmt.format(item.getDate()));


            if(item.getIcon().toString().equals("01d") || item.getIcon().toString().equals("01n")){
                header.setImageResource(R.drawable.sunny);
            }
            else if(item.getIcon().toString().equals("02d") || item.getIcon().toString().equals("02n")){
                header.setImageResource(R.drawable.sunny_s_cloudy);
            }
            else if(item.getIcon().toString().equals("03d") || item.getIcon().toString().equals("03n")){
                header.setImageResource(R.drawable.cloudy);
            }
            else if(item.getIcon().toString().equals("04d") || item.getIcon().toString().equals("04n")){
                header.setImageResource(R.drawable.cloudy);
            }
            else if(item.getIcon().toString().equals("09d") || item.getIcon().toString().equals("09n")){
                header.setImageResource(R.drawable.rain_s_cloudy);
            }
            else if(item.getIcon().toString().equals("10d") || item.getIcon().toString().equals("10n")){
                header.setImageResource(R.drawable.rain);
            }
            else if(item.getIcon().toString().equals("11d") || item.getIcon().toString().equals("11n")){
                header.setImageResource(R.drawable.thunderstorms);
            }
            else if(item.getIcon().toString().equals("13d") || item.getIcon().toString().equals("13n")){
                header.setImageResource(R.drawable.snow);
            }
            else if(item.getIcon().toString().equals("50d") || item.getIcon().toString().equals("50n")){
                header.setImageResource(R.drawable.fog);
            }

            return v;
        } catch (Exception ex) {
            Log.e(LOG_TAG, "error", ex);
            return null;
        }
    }
}