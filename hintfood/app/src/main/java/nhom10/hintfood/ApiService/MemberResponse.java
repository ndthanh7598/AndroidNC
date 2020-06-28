package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class MemberResponse {

    @SerializedName("data")
    public List<Member> data = new ArrayList();

    public class Member {
        @SerializedName("PK_iMaUser")
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
    }
}