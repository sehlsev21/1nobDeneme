
package com.example.nobdeneme.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataEczaneler {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("rowCount")
    @Expose
    private Integer rowCount;
    @SerializedName("data")
    @Expose
    private List<Eczaneler> data = null;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    public List<Eczaneler> getData() {
        return data;
    }

    public void setData(List<Eczaneler> data) {
        this.data = data;
    }



}
