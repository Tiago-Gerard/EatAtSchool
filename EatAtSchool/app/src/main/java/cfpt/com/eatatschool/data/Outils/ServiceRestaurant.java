package cfpt.com.eatatschool.data.Outils;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.List;

import cfpt.com.eatatschool.domaine.RestaurantSerializable;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceRestaurant {

    public interface Callbacks{
        void onResponse(@Nullable List<RestaurantSerializable> restos);
        void onFailure();
    }

    public static void getRestaurants(ServiceRestaurant.Callbacks callback){
        final WeakReference<ServiceRestaurant.Callbacks> callbacksWeakReference = new WeakReference<ServiceRestaurant.Callbacks>(callback);

        AccesService accesService = AccesService.retrofitRestaurants.create(AccesService.class);

        retrofit2.Call<List<RestaurantSerializable>> call = accesService.getRestaurants();

        call.enqueue(new Callback<List<RestaurantSerializable>>() {
            @Override
            public void onResponse(retrofit2.Call<List<RestaurantSerializable>> call, Response<List<RestaurantSerializable>> response) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<RestaurantSerializable>> call, Throwable t) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onFailure();
                }
            }
        });
    }
}
