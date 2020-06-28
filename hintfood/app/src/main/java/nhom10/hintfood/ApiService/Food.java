package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("PK_iMaMon")
    public Integer _id;
    @SerializedName("sTenMon")
    public String tenmon;
    @SerializedName("sGioiThieu")
    public String gioithieu;
    @SerializedName("sCongThuc")
    public String congthuc;
    @SerializedName("sNguyenLieu")
    public String nguyenlieu;
    @SerializedName("sLinkAnh")
    public String image;
    @SerializedName("fDanhGia")
    public float danhgia;
    @SerializedName("iSoDanhGia")
    public int sodanhgia;
    @SerializedName("sXuatXu")
    public String xuatxu;
    @SerializedName("sLoai")
    public String loai;
    @SerializedName("sCheDo")
    public String chedo;
}
