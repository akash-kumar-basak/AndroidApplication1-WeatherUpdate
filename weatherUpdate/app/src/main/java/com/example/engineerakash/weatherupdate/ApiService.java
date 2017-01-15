package com.example.engineerakash.weatherupdate;

        import retrofit2.Call;
        import retrofit2.http.Body;
        import retrofit2.http.FormUrlEncoded;
        import retrofit2.http.GET;
        import retrofit2.http.Query;

/**
 * Created by ENGINEER AKASH on 12/3/2016.
 */
public interface ApiService {
    @GET("/data/2.5/weather")
    Call<MainModelClass> getWeatherInformation(
            @Query("q") String cityName,
            @Query("units") String units,
            @Query("APPID") String appId);
}
