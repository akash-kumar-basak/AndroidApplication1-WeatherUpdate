package com.example.engineerakash.weatherupdate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class information extends AppCompatActivity {
    ApiService apiService;
    TextView temperature;
    TextView maxTemp;
    //TextView minTemp;
    TextView humidity;
    TextView pressur;
    TextView windSpeed;
    TextView nameOfTheCountry;
    EditText enterCityName;
    TextView error;
    TextView topDisplayTemperature;
    private static final String API_URL = "http://api.openweathermap.org";
    private static final String UNITS = "metric";
    private static final String APP_ID = "c6afdab60aa89481e297e0a4f19af055";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        temperature = (TextView) findViewById(R.id.Temperature);
        maxTemp = (TextView) findViewById(R.id.MaxTemp);
        //minTemp = (TextView) findViewById(R.id.MinTemp);
        humidity = (TextView) findViewById(R.id.Humidity);
        pressur = (TextView) findViewById(R.id.Pressure);
        windSpeed = (TextView) findViewById(R.id.WindSpeed);
        nameOfTheCountry = (TextView) findViewById(R.id.NameOfTheCountry);
        enterCityName = (EditText) findViewById(R.id.EnterCityName);
        error = (TextView) findViewById(R.id.Error);
        topDisplayTemperature = (TextView) findViewById(R.id.TopDisplayTemperature);
    }

    public void search(View view) {
        netWorkingLibraryInitializer();
        getData();}

    public void getData() {
        final String CITY_NAME = enterCityName.getText().toString();
        if (CITY_NAME == "") {
            error.setText("Enter any valid city name...");
            Toast.makeText(information.this, "Enter any valid city name...", Toast.LENGTH_LONG).show();
        } else {
            Call<MainModelClass> mainModelClassCall = apiService.getWeatherInformation(CITY_NAME, UNITS, APP_ID);
            mainModelClassCall.enqueue(new Callback<MainModelClass>() {
                @Override
                public void onResponse(Call<MainModelClass> call, Response<MainModelClass> response) {
                    MainModelClass mainModelClass = response.body();
                    String newTemperature = mainModelClass.getMain().getTemp().toString();
                    String maximumTemp = mainModelClass.getMain().getTempMax().toString();
                    //String minimumTemp = mainModelClass.getMain().getTempMin().toString();
                    String newHumidity = mainModelClass.getMain().getHumidity().toString();
                    String newPressur = mainModelClass.getMain().getPressure().toString();
                    String newWind = mainModelClass.getWind().getSpeed().toString();
                    String newNameOfCountry = mainModelClass.getSys().getCountry().toString();

                    topDisplayTemperature.setText(newTemperature);
                    temperature.setText(newTemperature+"degree celsius");
                    maxTemp.setText(maximumTemp+"d.c.");
                    //minTemp.setText(minimumTemp);
                    humidity.setText(newHumidity+"%");
                    pressur.setText(newPressur+"atm");
                    windSpeed.setText(newWind+"kmp/h");
                    nameOfTheCountry.setText(newNameOfCountry);
                    error.setText("");
                }

                @Override
                public void onFailure(Call<MainModelClass> call, Throwable t) {
                    temperature.setText(" ");
                    maxTemp.setText(" ");
                    //minTemp.setText(" ");
                    humidity.setText(" ");
                    pressur.setText(" ");
                    windSpeed.setText(" ");
                    nameOfTheCountry.setText(" ");
                    topDisplayTemperature.setText(" ");
                    error.setText("Enter any valid city name...");
                    Toast.makeText(information.this, "Invalid city, try again...", Toast.LENGTH_LONG).show();
                }
            });
            Log.e("url", "getData: " + mainModelClassCall.request().url());

        }
    }
    public void netWorkingLibraryInitializer () {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = retrofit.create(ApiService.class);

}
}
