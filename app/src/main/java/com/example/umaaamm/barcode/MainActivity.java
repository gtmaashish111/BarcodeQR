package com.example.umaaamm.barcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import com.example.umaaamm.barcode.Model.PosPutDel;
import com.example.umaaamm.barcode.Rest.Api;
import com.example.umaaamm.barcode.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;
    ApiInterface mApiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiInterface = Api.getClient().create(ApiInterface.class);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

//    public void QrScanner(View view){
//        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
//        setContentView(mScannerView);
//        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
//        mScannerView.startCamera();         // Start camera
//    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        String a;
        Log.e("handler", rawResult.getText()); // Prints scan results
        Log.e("handler", rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode)
        a = rawResult.getText();
        //  AlertDialog alert1 = builder.create();
        // Toast.makeText(this, "Barcode berhasil = "+a, Toast.LENGTH_SHORT).show();
        Call<PosPutDel> postKontakCall = mApiInterface.postKontak(a.toString());
        postKontakCall.enqueue(new Callback<PosPutDel>() {
            @Override
            public void onResponse(Call<PosPutDel> call, Response<PosPutDel> response) {
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                finish();
                startActivity(getIntent());
            }

            @Override
            public void onFailure(Call<PosPutDel> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }
}
