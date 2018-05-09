package cfpt.com.eatatschool.data.Outils;

import java.util.List;

import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import cfpt.com.eatatschool.domaine.SchoolSerializable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccesService {
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

    @POST("php/ecoles/create.php")
    @FormUrlEncoded
    Call<SchoolSerializable> createSchool(
                        @Field("nom") String nom,
                        @Field("lat") double lat,
                        @Field("lon") double lon);
    Retrofit retrofitAddSchool = new Retrofit.Builder()
            .baseUrl("http://10.134.98.158/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}