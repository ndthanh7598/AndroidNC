package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListFood {
    @SerializedName("data")
    public List<Food> data = new ArrayList();

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
    }
}
