package group3.pro205.services;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import group3.pro205.common.AppContanst;


public class BaseApi {
    public RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint(AppContanst.HOST)
            .setClient(getOkHttpClient())
            .build();


    public OkClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(5, TimeUnit.MINUTES);
        okHttpClient.setConnectTimeout(5, TimeUnit.MINUTES);
        okHttpClient.setWriteTimeout(5, TimeUnit.MINUTES);
        return new OkClient(okHttpClient);
    }
}
