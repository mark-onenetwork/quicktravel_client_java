package au.com.sealink.quicktravel.client.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Booking {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("state")
    @Expose
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
