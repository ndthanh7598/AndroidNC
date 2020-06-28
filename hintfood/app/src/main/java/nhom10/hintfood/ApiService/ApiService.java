package nhom10.hintfood.ApiService;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("androidnc/register.php")
    Call<ResultResponse> registerAccount(@Field("email") String email, @Field("pass") String pass, @Field("name") String name);

    @FormUrlEncoded
    @POST("androidnc/login.php")
    Call<InfoLogin> loginApp(@Field("email") String email, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("androidnc/member.php")
    Call<MemberResponse> getGroup(@Field("action") String action, @Field("nhom") String nhom, @Field("currentUser") String currentUser);

    @FormUrlEncoded
    @POST("androidnc/member.php")
    Call<MemberResponse> getSearch(@Field("action") String action, @Field("search") String search, @Field("currentUser") String currentUser);

    @FormUrlEncoded
    @POST("androidnc/member.php")
    Call<ResultResponse> addGroup(@Field("action") String action, @Field("currentId") String currentId, @Field("addId") String addId);

    @FormUrlEncoded
    @POST("androidnc/member.php")
    Call<ResultResponse> delGroup(@Field("action") String action, @Field("userId") int userId);

    @FormUrlEncoded
    @POST("androidnc/food.php")
    Call<ListFood> getFood(@Field("action") String action, @Field("userId") String userId);

    @FormUrlEncoded
    @POST("androidnc/khaosat.php")
    Call<ListKhaoSat> getListKhaoSat(@Field("action") String action, @Field("userId") String userId);

    @FormUrlEncoded
    @POST("androidnc/khaosat.php")
    Call<ResultResponse> saveKhaoSat(@Field("action") String action, @Field("userId") String userId, @Field("listKs") String listKs);

    @FormUrlEncoded
    @POST("androidnc/food.php")
    Call<ListFood> getFoodByIngredients(@Field("action") String action, @Field("hintFood") String hintFood);

    @FormUrlEncoded
    @POST("androidnc/food.php")
    Call<Food> getFoodById(@Field("action") String action, @Field("idFood") int idFood);

    @FormUrlEncoded
    @POST("androidnc/comment.php")
    Call<ResultResponse> saveComment(@Field("action") String action, @Field("idFood") int idFood, @Field("maUser") String maUser, @Field("noidung") String noidung, @Field("thoigian") String thoigian, @Field("image") String image);

    @FormUrlEncoded
    @POST("androidnc/comment.php")
    Call<CommentResponse> getCommentByIdFood(@Field("action") String action, @Field("idFood") int idFood);

    @FormUrlEncoded
    @POST("androidnc/member.php")
    Call<MyInfo> getMyInfo(@Field("action") String action, @Field("_id") int _id);

    @FormUrlEncoded
    @POST("androidnc/member.php")
    Call<ResultResponse> updateMyInfo(@Field("action") String action, @Field("userId") int userId, @Field("hoten") String hoten, @Field("ngaysinh") String ngaysinh, @Field("gioitinh") String gioitinh, @Field("image") String image);

    @FormUrlEncoded
    @POST("androidnc/food.php")
    Call<FoodInDay> getFoodInDay(@Field("action") String action, @Field("ngay") String ngay, @Field("maUser") String maUser);

    @FormUrlEncoded
    @POST("androidnc/food.php")
    Call<ResultResponse> saveFoodInDay(@Field("action") String action, @Field("buoi") String buoi, @Field("ngay") String ngay, @Field("maMon") int maMon, @Field("maUser") String maUser);

    @FormUrlEncoded
    @POST("androidnc/food.php")
    Call<ResultResponse> deleteFoodInDay(@Field("action") String action, @Field("idTD") int idTD);

    @FormUrlEncoded
    @POST("androidnc/comment.php")
    Call<ResultResponse> rateFood(@Field("action") String action, @Field("idFood") int idFood, @Field("rate") int rate);

    @FormUrlEncoded
    @POST("androidnc/login.php")
    Call<ResultResponse> saveTokenNotification(@Field("userId") String userId, @Field("token") String token);
}