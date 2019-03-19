package com.truebeans.easyinternetcall;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class InternetCaller extends AsyncTask<Void, Void, Void> implements InternetCall {

    private String serverResponse;

    @Override
    protected Void doInBackground(Void... voids) {
        internetCall();
        return null;
    }

    public void internetCall() {

        OkHttpClient client = new OkHttpClient();

        Request request = createRequest();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                assert response.body() != null;
                serverResponse = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        responseReceived(serverResponse);

                    }
                });
            }
        });
    }

    private Request createRequest(){
        return new Request.Builder()
                .url(getHostingUrl() + getPageUrl())
                .post(getRequestBody())
                .build();
    }

    public abstract RequestBody getRequestBody();

    public abstract String getPageUrl();

    public abstract void responseReceived(String response);

    public abstract String getHostingUrl();
}
