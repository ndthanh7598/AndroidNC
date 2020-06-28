package nhom10.hintfood.ApiService;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CommentResponse {
    @SerializedName("data")
    public List<Comment> data = new ArrayList();

    public class Comment {
        @SerializedName("PK_iMaBL")
        public Integer maBL;
        @SerializedName("sNoiDung")
        public String noidung;
        @SerializedName("sThoiGian")
        public String thoigian;
        @SerializedName("sLinkAnh")
        public String anhBL;
        @SerializedName("FK_iMaUser")
        public String maUser;
        @SerializedName("FK_iMaMon")
        public String maMon;
        @SerializedName("sTenHienThi")
        public String tenUser;
        @SerializedName("sAvatar")
        public String avatarUser;
    }
}
