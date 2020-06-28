package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListKhaoSat {
    @SerializedName("dmks")
    public List<KhaoSat> dmks = new ArrayList();

    public class KhaoSat {
        @SerializedName("PK_iMaKS")
        public Integer maks;
        @SerializedName("sTieuChi")
        public String tieuchi;
        @SerializedName("sGhiChu")
        public String ghichu;
        @SerializedName("sAnh")
        public String image;
    }

    @SerializedName("ksuser")
    public List<KsUser> ksuser = new ArrayList();

    public class KsUser {
        @SerializedName("PK_iMaKS")
        public Integer maks;
        @SerializedName("PK_iMaUser")
        public Integer mauser;
    }
}
