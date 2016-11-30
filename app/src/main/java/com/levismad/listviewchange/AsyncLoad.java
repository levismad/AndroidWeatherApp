package com.levismad.listviewchange;

import android.app.Activity;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.entries;


/**
 * Created by root on 29/11/2016.
 */

public class AsyncLoad extends AsyncTask<String, Void, List<WeatherEntry>> {
    public AsyncLoad(WeatherAdapter arrayAdapter, ListView listView, Activity context){
        this.mArrayAdapter = arrayAdapter;
        this.mListView = listView;
        this.mContext = context;

    }
    private WeatherAdapter mArrayAdapter;
    private ListView mListView;
    private Activity mContext;
    private List<WeatherEntry> forecastArray = new ArrayList<WeatherEntry>();
    //private String currentWeather = "";
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected List<WeatherEntry> doInBackground(String... params) {

        if(params.length == 0){
            return  null;
        }
        
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String JsonResult = null;

        String format = "json";
        String units = "metric";
        int numDays = 7;
        try{
            final String FORECAST_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?";
            final String QUERY_PARAM = "q";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID = "APPID";
            final String KEY = "e503626284f885873528a61c03504d37";

            Uri buidUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM,params[0])
                    .appendQueryParameter(FORMAT_PARAM,format)
                    .appendQueryParameter(UNITS_PARAM,units)
                    .appendQueryParameter(DAYS_PARAM,Integer.toString(numDays))
                    .appendQueryParameter(APPID,KEY)
                    .build();

            URL url = new URL(buidUri.toString());

            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine()) != null)
            {
                buffer.append(line + "\n");
            }
            if(buffer.length() == 0){
                return null;
            }
            JsonResult = buffer.toString();

            //Log.v(LOG_TAG,"Forecast JSON string: " + JsonResult);

            JSONObject jsonObj = new JSONObject(JsonResult);
            JSONObject temp;
            JSONArray weather;
            JSONArray listObj = jsonObj.getJSONArray("list");
            WeatherEntry item;
            for (int i = 0; i < listObj.length(); i++) {
                item = new WeatherEntry();
                JSONObject c = listObj.getJSONObject(i);
                item.setUmidity(c.getString("humidity"));
                item.setRain(c.getString("clouds"));
                item.setTimestamp(c.getString("dt"));
                try {
                    java.util.Date time=new java.util.Date(Long.parseLong(item.getTimestamp())*1000);
                    item.setDate(time);
                }
                catch (Exception ex){

                }
                temp = c.getJSONObject("temp");
                item.setValue(temp.getString("max"));
                item.setValueMin(temp.getString("min"));
                weather = c.getJSONArray("weather");
                item.setIcon(weather.getJSONObject(0).getString("icon"));

                forecastArray.add(item);
            }
        }
        catch (Exception ex){
            //Log.e(LOG_TAG,"Error ", ex);
            return  null;
        }
        finally {
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(reader !=null){
                try{
                    reader.close();
                }
                catch (final IOException e){
                    //Log.e(LOG_TAG,"Error closing stream",e);
                }
            }
        }
        return forecastArray;
    }

    @Override
    protected void onPostExecute(List<WeatherEntry> result) {
        super.onPostExecute(result);
        mArrayAdapter = new WeatherAdapter(mContext,R.layout.list_view_item);
        mListView.setAdapter(mArrayAdapter);
        for(WeatherEntry e : result) {
            mArrayAdapter.add(e);
        }
    }
}

