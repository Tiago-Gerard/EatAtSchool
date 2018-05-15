package cfpt.com.eatatschool.data.Outils;

import java.io.IOException;
import java.util.List;

import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import cfpt.com.eatatschool.domaine.SchoolSerializable;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AccesService {


    //TODO : AUTH à régler
    OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                    Credentials.basic("aUsername", "aPassword"));

            Request newRequest = builder.build();
            return chain.proceed(newRequest);
        }
    }).build();

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

    @POST("php/restaurants/create.php")
    @FormUrlEncoded
    Call<RestaurantSerializable> createRestaurant(
            @Field("nom") String nom,
            @Field("lat") double lat,
            @Field("lon") double lon);
    Retrofit retrofitAddRestaurant = new Retrofit.Builder()
            .baseUrl("http://10.134.98.158/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}