package cfpt.com.eatatschool.data;

import android.os.AsyncTask;

import java.lang.ref.WeakReference;

public class HttpThreadAsync<T> extends AsyncTask<Void, Void, T>{

    public HttpThreadAsync(Listeners callback) {
        this.callback = new WeakReference<>(callback);
    }

    public interface Listeners<T>{
        void onPreExecute();

        T doInBackground();

        void onPostExecute(String success);
    }

    private final WeakReference<Listeners> callback;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.callback.get().onPreExecute();
    }

    @Override
    protected void onPostExecute(T o) {
        super.onPostExecute(o);
        this.callback.get().onPostExecute(String.valueOf(o));
    }

    @Override
    protected T doInBackground(Void... voids) {
        this.callback.get().doInBackground();
        return null;
    }
}
