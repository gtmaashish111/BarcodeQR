package com.example.umaaamm.barcode.Rest;

/**
 * Created by umaaamm on 29/03/18.
 */

import com.example.umaaamm.barcode.Model.PosPutDel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @PUT("put/{id}")
    Call<PosPutDel> postKontak(@Path("id") String id);
}
