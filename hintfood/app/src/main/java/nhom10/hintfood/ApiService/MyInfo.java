package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

public class MyInfo {
    @SerializedName("PK_iMaUser")
    public Integer _id;
    @SerializedName("sTenHienThi")
    public String hoten;
    @SerializedName("sGioiTinh")
    public String gioitinh;
    @SerializedName("sNgaySinh")
    public String ngaysinh;
    @SerializedName("PK_iMaTaiKhoan")
    public String maTK;
    @SerializedName("sEmail")
    public String email;
    @SerializedName("sAvatar")
    public String avatar;
}
