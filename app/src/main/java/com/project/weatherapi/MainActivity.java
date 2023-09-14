package com.project.weatherapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button getCity, byName, byId;
    EditText input;
    ListView listWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getCity= findViewById(R.id.getId);
        byName= findViewById(R.id.byName);
        byId= findViewById(R.id.byId);
        input = findViewById(R.id.datainput);
        listWeather = findViewById(R.id.weatherList);


        //click listener

        getCity.setOnClickListener( v->{
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url = "https://www.hko.gov.hk/openData/json/sccw_json_datagov.json";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String cityId=" ";
                            try {
                                JSONObject cityInfo = response.getJSONObject("Armonk, North Castle");
                                cityId= cityInfo.getString("Armonk, North Castle");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            showToaster("CityID= "+ cityId);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error
                            showToaster("something is wrong");
                        }
                    });
            queue.add(jsonObjectRequest);
// Access the RequestQueue through your singleton class.

            /*// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                           showToaster(response);
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    showToaster("That didn't work!");
                }
            });
*/
// Add the request to the RequestQueue.

        });

        byName.setOnClickListener( v->{
            showToaster(input.getText()+ "By Name button");
        });

        byId.setOnClickListener( v->{
            showToaster(input.getText()+ "By Id button");
        });

    }


    public void showToaster(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}