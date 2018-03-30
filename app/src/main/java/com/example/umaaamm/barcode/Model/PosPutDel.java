package com.example.umaaamm.barcode.Model;

/**
 * Created by umaaamm on 29/03/18.
 */

import com.google.gson.annotations.SerializedName;


public class PosPutDel {

    @SerializedName("id")
    String id;

    public String getid() {
        return id;
    }
    public void setid(String id) {
        this.id = id;
    }


}
