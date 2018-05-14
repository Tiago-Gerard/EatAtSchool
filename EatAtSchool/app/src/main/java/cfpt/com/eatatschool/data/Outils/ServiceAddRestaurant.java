package cfpt.com.eatatschool.data.Outils;

import java.lang.ref.WeakReference;

import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import cfpt.com.eatatschool.domaine.SchoolSerializable;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAddRestaurant {

    public interface Callbacks{
        void onResponse(RestaurantSerializable restos);
        void onFailure();
    }

    public static void addRestaurant(ServiceAddRestaurant.Callbacks callback, String nomRestaurant, double lat, double lon){
        final WeakReference<ServiceAddRestaurant.Callbacks> callbacksWeakReference = new WeakReference<ServiceAddRestaurant.Callbacks>(callback);

        AccesService accesService = AccesService.retrofitAddRestaurant.create(AccesService.class);

        retrofit2.Call<RestaurantSerializable> call = accesService.createRestaurant(nomRestaurant,lat,lon);

        call.enqueue(new Callback<RestaurantSerializable>() {
            @Override
            public void onResponse(retrofit2.Call<RestaurantSerializable> call, Response<RestaurantSerializable> response) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<RestaurantSerializable> call, Throwable t) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onFailure();
                }
            }
        });
    }
}
