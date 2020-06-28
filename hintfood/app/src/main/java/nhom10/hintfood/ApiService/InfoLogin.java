package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

public class InfoLogin {
    @SerializedName("PK_iMaTaiKhoan")
    public Integer _id;
    @SerializedName("sTenHienThi")
    public String hoten;
    @SerializedName("sGioiTinh")
    public String gioitinh;
    @SerializedName("sNgaySinh")
    public String ngaysinh;
    @SerializedName("sEmail")
    public String email;
    @SerializedName("sAvatar")
    public String avatar;
    @SerializedName("sNhom")
    public String nhom;
    @SerializedName("PK_iMaUser")
    public String maUser;
    @SerializedName("sKhaoSat")
    public String khaosat;
//    @SerializedName("data")
//    public String data;
    @SerializedName("result")
    public String result;
}
