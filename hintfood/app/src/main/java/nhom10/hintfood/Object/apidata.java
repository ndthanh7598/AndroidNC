package nhom10.hintfood.Object;

import com.google.gson.JsonArray;

import java.util.ArrayList;

public class apidata {
    private String action;
    private ArrayList<String> data;

    public apidata(String action, ArrayList<String> data) {
        this.action = action;
        this.data = data;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }
}
