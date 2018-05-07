package cfpt.com.eatatschool.data.Outils;

import java.util.List;

import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import cfpt.com.eatatschool.domaine.SchoolSerializable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SchoolService {
    @GET("php/ecoles/")
    Call<List<SchoolSerializable>> getEcoles();
    Retrofit retrofitEcoles = new Retrofit.Builder()
                .baseUrl("http://10.134.98.158/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    @GET("php/restaurants/")
    Call<List<RestaurantSerializable>> getRestaurants();
    Retrofit retrofitRestaurants = new Retrofit.Builder()
            .baseUrl("http://10.134.98.158/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}