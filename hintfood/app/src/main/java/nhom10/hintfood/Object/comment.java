package nhom10.hintfood.Object;

public class comment {
    private int maBL;
    private String noidung;
    private String thoigian;
    private String anhbl;
    private String maUser;
    private String maMon;
    private String avatar;
    private String tenUser;

    public comment(int maBL, String noidung, String thoigian, String anhbl, String maUser, String maMon, String avatar, String tenUser) {
        this.maBL = maBL;
        this.noidung = noidung;
        this.thoigian = thoigian;
        this.anhbl = anhbl;
        this.maUser = maUser;
        this.maMon = maMon;
        this.avatar = avatar;
        this.tenUser = tenUser;
    }

    public comment(String noidung, String thoigian, String anhbl, String avatar, String tenUser) {
        this.noidung = noidung;
        this.thoigian = thoigian;
        this.anhbl = anhbl;
        this.avatar = avatar;
        this.tenUser = tenUser;
    }

    public int getMaBL() {
        return maBL;
    }

    public void setMaBL(int maBL) {
        this.maBL = maBL;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getAnhbl() {
        return anhbl;
    }

    public void setAnhbl(String anhbl) {
        this.anhbl = anhbl;
    }

    public String getMaUser() {
        return maUser;
    }

    public void setMaUser(String maUser) {
        this.maUser = maUser;
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTenUser() {
        return tenUser;
    }

    public void setTenUser(String tenUser) {
        this.tenUser = tenUser;
    }
}
