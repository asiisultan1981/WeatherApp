package com.example.weatherapp;

import com.example.weatherapp.AccuWeather.Example;
import com.example.weatherapp.AccuWeather.Location;
import com.example.weatherapp.AccuWeather.Weather;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/*Current weather data*/

/*Current and forecast weather data
* How to make an API call
  * https://api.openweathermap.org/data/2.5/onecall?lat={lat}&lon={lon}&exclude={part}&appid={API key}
  * "exclude" is optional:	By using this parameter you can exclude some parts of the weather data from the API response. It should be a comma-delimited list (without spaces).
            Available values:

            current
            minutely
            hourly
            daily
            alerts
            * Example of API call
* https://api.openweathermap.org/data/2.5/onecall?lat=33.441792&lon=-94.037689&exclude=hourly,daily&appid=07f0535d70568e34ec41a89d008bfd10

* * *********************************
* for weather:
* https://dataservice.accuweather.com/currentconditions/v1/{locationKey}?apikey={apikey}
* example:
*https://dataservice.accuweather.com/currentconditions/v1/258955?apikey=zp3bjYGauWoYCrzfz2r8j0HSV2KCcGSO

* *for location Id/key:
* https://dataservice.accuweather.com/locations/v1/cities/search?apikey=zp3bjYGauWoYCrzfz2r8j0HSV2KCcGSO&q=abbottabad*/

public interface WeatherApi {
    @GET("locations/v1/cities/search")
    Call<List<Location>> getLocation(
            @Query("apikey") String Api_key,
            @Query("q" ) String city
    );

    @GET("currentconditions/v1/258955")
    Call<List<Weather>> getWeather(
            @Query("locationKey") String locationKey,
            @Query("apikey") String Api_key);


}


//public interface WeatherApi {
//    @GET("data/2.5/weather")
//    Call<Example> getTemperature(@Query("q" ) String city,
//                                 @Query("appid") String Api_key);
//}
