package cfpt.com.eatatschool.data.Outils;

import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import java.lang.ref.WeakReference;
import java.util.List;

import cfpt.com.eatatschool.domaine.SchoolSerializable;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceAddEcole {

    public interface Callbacks{
        void onResponse(SchoolSerializable ecoles);
        void onFailure();
    }

    public static void addSchool(ServiceAddEcole.Callbacks callback, String nomEcole, double lat, double lon){
        final WeakReference<ServiceAddEcole.Callbacks> callbacksWeakReference = new WeakReference<ServiceAddEcole.Callbacks>(callback);

        AccesService accesService = AccesService.retrofitAddSchool.create(AccesService.class);

        retrofit2.Call<SchoolSerializable> call = accesService.createSchool(nomEcole,lat,lon);

        call.enqueue(new Callback<SchoolSerializable>() {
            @Override
            public void onResponse(retrofit2.Call<SchoolSerializable> call, Response<SchoolSerializable> response) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<SchoolSerializable> call, Throwable t) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onFailure();
                }
            }
        });
    }
}
