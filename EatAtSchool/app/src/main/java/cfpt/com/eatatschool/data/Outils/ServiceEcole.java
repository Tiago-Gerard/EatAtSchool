package cfpt.com.eatatschool.data.Outils;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

import cfpt.com.eatatschool.domaine.SchoolSerializable;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceEcole {

    public interface Callbacks{
        void onResponse(@Nullable List<SchoolSerializable> ecoles);
        void onFailure();
    }

    public static void getSchool(Callbacks callback){
        final WeakReference<Callbacks> callbacksWeakReference = new WeakReference<Callbacks>(callback);

        SchoolService schoolService = SchoolService.retrofitEcoles.create(SchoolService.class);

        retrofit2.Call<List<SchoolSerializable>> call = schoolService.getEcoles();

        call.enqueue(new Callback<List<SchoolSerializable>>() {
            @Override
            public void onResponse(retrofit2.Call<List<SchoolSerializable>> call, Response<List<SchoolSerializable>> response) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<SchoolSerializable>> call, Throwable t) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onFailure();
                }
            }
        });
    }
}
