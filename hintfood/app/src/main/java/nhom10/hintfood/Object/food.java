package nhom10.hintfood.Object;

public class food {
    private int _id;
    private String tenmon;
    private String gioithieu;
    private String congthuc;
    private String nguyenlieu;
    private String fileanh;
    private String danhgia;
    private String xuatxu;
    private String loaimon;
    private String chedo;

    public food(int _id, String tenmon, String gioithieu, String congthuc, String nguyenlieu, String fileanh, String danhgia, String xuatxu, String loaimon, String chedo) {
        this._id = _id;
        this.tenmon = tenmon;
        this.gioithieu = gioithieu;
        this.congthuc = congthuc;
        this.nguyenlieu = nguyenlieu;
        this.fileanh = fileanh;
        this.danhgia = danhgia;
        this.xuatxu = xuatxu;
        this.loaimon = loaimon;
        this.chedo = chedo;
    }

    public food(int _id, String tenmon, String fileanh) {
        this._id = _id;
        this.tenmon = tenmon;
        this.fileanh = fileanh;
    }

    public food(String tenmon, String fileanh) {
        this.tenmon = tenmon;
        this.fileanh = fileanh;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTenmon() {
        return tenmon;
    }

    public void setTenmon(String tenmon) {
        this.tenmon = tenmon;
    }

    public String getGioithieu() {
        return gioithieu;
    }

    public void setGioithieu(String gioithieu) {
        this.gioithieu = gioithieu;
    }

    public String getCongthuc() {
        return congthuc;
    }

    public void setCongthuc(String congthuc) {
        this.congthuc = congthuc;
    }

    public String getNguyenlieu() {
        return nguyenlieu;
    }

    public void setNguyenlieu(String nguyenlieu) {
        this.nguyenlieu = nguyenlieu;
    }

    public String getFileanh() {
        return fileanh;
    }

    public void setFileanh(String fileanh) {
        this.fileanh = fileanh;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getLoaimon() {
        return loaimon;
    }

    public void setLoaimon(String loaimon) {
        this.loaimon = loaimon;
    }

    public String getChedo() {
        return chedo;
    }

    public void setChedo(String chedo) {
        this.chedo = chedo;
    }
}
