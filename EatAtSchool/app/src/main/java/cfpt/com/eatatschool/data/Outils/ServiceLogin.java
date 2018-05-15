package cfpt.com.eatatschool.data.Outils;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.List;

import cfpt.com.eatatschool.domaine.SchoolSerializable;
import cfpt.com.eatatschool.domaine.UserSerializable;
import retrofit2.Callback;
import retrofit2.Response;

public class ServiceLogin {
    public interface Callbacks{
        void onResponse(UserSerializable user);
        void onFailure();
    }

    public static void getLogin(ServiceLogin.Callbacks callback){
        final WeakReference<ServiceLogin.Callbacks> callbacksWeakReference = new WeakReference<ServiceLogin.Callbacks>(callback);

        AccesService accesService = AccesService.retrofitUser.create(AccesService.class);

        retrofit2.Call<UserSerializable> call = accesService.getUser();

        call.enqueue(new Callback<UserSerializable>() {
            @Override
            public void onResponse(retrofit2.Call<UserSerializable> call, Response<UserSerializable> response) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onResponse(response.body());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserSerializable> call, Throwable t) {
                if (callbacksWeakReference.get() != null){
                    callbacksWeakReference.get().onFailure();
                }
            }
        });
    }
}
