package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.weatherapp.AccuWeather.Country;
import com.example.weatherapp.AccuWeather.Example;
import com.example.weatherapp.AccuWeather.Location;
import com.example.weatherapp.AccuWeather.Metric;
import com.example.weatherapp.AccuWeather.Temperature;
import com.example.weatherapp.AccuWeather.Weather;
import com.example.weatherapp.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "data";
    ActivityMainBinding binding;
    private String cityName = null;

    //  https://api.openweathermap.org/data/2.5/weather?q=Abbottabad&appid=07f0535d70568e34ec41a89d008bfd10
//    private static final String BASE_URL = "https://api.openweathermap.org/";
    private static final String BASE_URL = "https://dataservice.accuweather.com/";

    //  api.openweathermap.org/data/2.5/weather?q={city name}&appid={API key}";
//    private static final String API_KEY = "07f0535d70568e34ec41a89d008bfd10";

    // for accuweather
    private static final String API_KEY = "d5UtLkmrjJJKs77rpAAkq1ZXLFCA1yrb";
    private String locationKey = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        String lock_key = locationKey();
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getWeather(lock_key);

            }
        });

        try {
            cityName = binding.etCity.getText().toString();
            JSONObject jsonObject = new JSONObject(cityName);
            JSONArray jsonArray = jsonObject.getJSONArray("Key");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i).getJSONObject("Key");
                locationKey = object.getString("Key");

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private void getWeather(String key) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        key = locationKey();
        Call<List<Weather>> weatherCall = weatherApi.getWeather(key, API_KEY);
        weatherCall.enqueue(new Callback<List<Weather>>() {
            @Override
            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
                if (response.isSuccessful()) {

                    List<Weather> data = response.body();



                    for (int i = 0; i < data.size(); i++) {
                        String DateTime = data.get(i).getLocalObservationDateTime();
                        Temperature temperature = data.get(i).getTemperature();
                        Double tempValue  = temperature.getMetric().getValue();
                        String tempUnit = temperature.getMetric().getUnit();

                        String weather = data.get(i).getWeatherText();




                        Log.e(TAG, "\n" + "Date & Time: " + DateTime +"\n"+
                                "\n" + "Weather: " + weather +
                                "\n" + "Temperature: " + tempValue+""+tempUnit+
                                "\n" + "Precipitation Type: "+data.get(i).getPrecipitationType()+
                                "\n" + "Is it Day Time? "+data.get(i).getIsDayTime());
                    }
                } else {
                    try {
                        Log.e(TAG, "onResponseFailed: "+response.errorBody().string() );
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Weather>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
            }
        });
//        weatherCall.enqueue(new Callback<List<Weather>>() {
//            @Override
//            public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
//                if (response.isSuccessful()) {
//                    List<Weather> data = response.body();
//
////                    List<Weather> weathers = data.getWeather();
////                    Log.e(TAG, "onResponse: " + weathers.size());
//                    for (int i = 0; i < data.size(); i++) {
//                        String DateTime = data.get(i).getLocalObservationDateTime();
//                        Temperature temperature = data.get(i).getTemperature();
//                        String weather = data.get(i).getWeatherText();
//                        Log.e(TAG, "\n" + "Date & Time: " + DateTime +
//                                "\n" + "Weather: " + weather +
//                                "\n" + "Temperature: " + temperature);
//
//
//
//
//        }
//            @Override
//            public void onFailure(Call<List<Weather>> call, Throwable t) {
//                Log.e(TAG, "onFailure of WeatherCall: "+t.getMessage() );
//            }
//        });

    }

    private String locationKey() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        cityName = binding.etCity.getText().toString();


        Call<List<Location>> locationCall = weatherApi.getLocation(API_KEY, cityName);

        locationCall.enqueue(new Callback<List<Location>>() {
            @Override
            public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
                Log.e(TAG, "onResponse: ");

                List<Location> data = response.body();

                for (int i = 0; i < data.size(); i++) {
                    Country country = data.get(i).getCountry();
                    String countryName = country.getEnglishName();
                    locationKey = data.get(i).getKey();
                    String city = data.get(i).getEnglishName();
                    Log.e(TAG, "City Key: " + locationKey + "\n" +
                            "City Name: " + city + "\n" +
                            "Country Name: " + countryName);
                }

            }

            @Override
            public void onFailure(Call<List<Location>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());

            }
        });

        return locationKey;


    }


//    private void getTemperature() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
//        String cityName = binding.etCity.getText().toString();
//
//        Call<Example> exampleCall = weatherApi.getTemperature(
//               cityName, API_KEY);
//
//        exampleCall.enqueue(new Callback<Example>() {
//            @Override
//            public void onResponse(Call<Example> call, Response<Example> response) {
//                if (response.code() == 404){
//                    Log.e(TAG, "invalid city");
//                }else {
//                    Example data = response.body();
//                    List<Weather> weather = data.getWeatherList();
//                    Main main = data.getMain();
//                    Double T = main.getTemp();
//                    Integer temp = (int) (T - 273.15);
//                    Country country = data.getCountry();
////               binding.tvTemp.setText(weather.getMain());
//                    for (int i = 0; i < weather.size(); i++) {
//                        Log.e(TAG,  " Weather Report of " + cityName+ ", city of "+country.getCountry()+"\n "
//                                + "Weather Description: " + weather.get(i).getDescription() + "\n"
//                                + "Weather Main Report: " + weather.get(i).getMain() + "\n"
//                                + "Current Temperature: " + temp + "C" + " or " + main.getTemp() + "K");
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<Example> call, Throwable t) {
//                Log.e(TAG, "onFailure: "+t.toString() );
//
//
//
//            }
//        });
//    }
}