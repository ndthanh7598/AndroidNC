package nhom10.hintfood.Object;

public class member {
    private int _id;
    private String hoten;
    private String email;
    private String avatar;

    public member(int _id, String hoten, String email, String avatar) {
        this._id = _id;
        this.hoten = hoten;
        this.email = email;
        this.avatar = avatar;
    }

    public int getId() {
        return _id;
    }

    public void setId(int _id) {
        this._id = _id;
    }

    public String gethoten() {
        return hoten;
    }

    public void sethoten(String hoten) {
        this.hoten = hoten;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.hoten = email;
    }

    public String getavatar() {
        return avatar;
    }

    public void setavatar(String avatar) {
        this.avatar = avatar;
    }
}
