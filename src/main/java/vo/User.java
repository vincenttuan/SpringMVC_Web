package vo;

import com.google.gson.Gson;

public class User {
    private String uname;
    private int uage;
    private Num num;
    
    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public int getUage() {
        return uage;
    }

    public void setUage(int uage) {
        this.uage = uage;
    }

    public Num getNum() {
        return num;
    }

    public void setNum(Num num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
    
    
    
    
}