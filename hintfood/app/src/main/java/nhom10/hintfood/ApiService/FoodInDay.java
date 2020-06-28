package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FoodInDay {
    @SerializedName("sang")
    public List<Food> sang = new ArrayList();

    @SerializedName("trua")
    public List<Food> trua = new ArrayList();

    @SerializedName("toi")
    public List<Food> toi = new ArrayList();

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
        public String danhgia;
        @SerializedName("sXuatXu")
        public String xuatxu;
        @SerializedName("sLoai")
        public String loai;
        @SerializedName("sCheDo")
        public String chedo;
        @SerializedName("PK_iMaTD")
        public Integer maTD;
    }
}
