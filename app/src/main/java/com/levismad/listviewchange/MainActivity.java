package com.levismad.listviewchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private WeatherAdapter customAdapter;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Set keyboard hidden on startup
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setTitle("Get Weather -- levismad\"");
        if(savedInstanceState == null || true) { //Doesn't worked as expected, the view is reload but the state of the objects are recreated
            customAdapter = new WeatherAdapter(this, R.layout.list_view_item);
            customAdapter.setNotifyOnChange(true);
            listView = (ListView) findViewById(R.id.list_view);
            EditText input = (EditText) findViewById(R.id.edit_text);


            AsyncLoad get = new AsyncLoad(customAdapter, listView, MainActivity.this);
            get.execute(input.getText().toString());
        }


    }
    public void doSomething(View context){
        EditText input = (EditText) findViewById(R.id.edit_text);
        String city = input.getText().toString();
        if(city != null || !city.equals("")) {
            AsyncLoad get = new AsyncLoad(customAdapter, listView, MainActivity.this);
            get.execute(city);
        }
    }

}
