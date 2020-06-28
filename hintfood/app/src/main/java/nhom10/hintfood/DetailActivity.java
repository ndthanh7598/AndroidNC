package nhom10.hintfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import nhom10.hintfood.Adapter.ListCommentAdap;
import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.CommentResponse;
import nhom10.hintfood.ApiService.Food;
import nhom10.hintfood.Class.CustomDialogClass;
import nhom10.hintfood.Object.comment;
import retrofit2.Call;
import retrofit2.Callback;

import static nhom10.hintfood.Class.CustomDialogClass.GALLERY;

public class DetailActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    int idFood;
    Food fd;
    ImageView ivBanner;
    TextView tvGioiThieu, tvNguyenLieu, tvCachlam;
    public static ListView lvComment;
    CustomDialogClass dialogClass;
    public static ListCommentAdap listCommentAdap;
    ArrayList<comment> listComment = new ArrayList<comment>();
    public static LinearLayout lnRate;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_share:
                    String message = "Cách làm món " + fd.tenmon + "\n\n";
                    message += "Nguyên liệu:\n" + fd.nguyenlieu + "\n\nCách chế biến:\n" + fd.congthuc;
                    Intent share = new Intent(Intent.ACTION_SEND);
                    share.setType("text/plain");
                    share.putExtra(Intent.EXTRA_SUBJECT, "Hint Food");
                    share.putExtra(Intent.EXTRA_TEXT, message);
                    share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(Intent.createChooser(share, "Chia sẻ món ăn"));
                    return true;
                case R.id.navigation_comment:
                    dialogClass = new CustomDialogClass(DetailActivity.this, idFood, R.layout.dialog_comment);
                    dialogClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialogClass.show();
                    return true;
                case R.id.navigation_vote:
                    dialogClass = new CustomDialogClass(DetailActivity.this, idFood, R.layout.dialog_rateus);
                    dialogClass.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialogClass.show();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar my_toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(my_toolbar);

        intent = this.getIntent();
        bundle = intent.getBundleExtra("show");
        idFood = (int) bundle.get("foodid");

        getControl();
        getFoodById();
        getCommentInFood();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportActionBar().setTitle("Công thức chế biến");
        // Hiện nút back ở toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getControl() {
        ivBanner = (ImageView) findViewById(R.id.banner);
        tvGioiThieu = (TextView) findViewById(R.id.gioithieu);
        tvNguyenLieu = (TextView) findViewById(R.id.nguyenlieu);
        tvCachlam = (TextView) findViewById(R.id.cachlam);
        lvComment = (ListView) findViewById(R.id.lvComment);
        lnRate = (LinearLayout) findViewById(R.id.lnRate);
    }

    private void createImageViewStar(int icon){
        ImageView imgStart = new ImageView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imgStart.setLayoutParams(params);
        imgStart.setImageDrawable(ContextCompat.getDrawable(this, icon));
        lnRate.addView(imgStart);
    }

    public void getFoodById() {
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<Food> call = apiService.getFoodById("getFoodById", idFood);

        call.enqueue(new Callback<Food>() {

            @Override
            public void onResponse(Call<Food> call, retrofit2.Response<Food> response) {
                fd = response.body();
                Picasso.get().load(fd.image).into(ivBanner);
                tvGioiThieu.setText(fd.gioithieu);
                tvNguyenLieu.setText(fd.nguyenlieu);
                tvCachlam.setText(fd.congthuc);
                int danhgia = Math.round(fd.danhgia);
                for (int i = 0; i < 5; i++){
                    if (i < danhgia){
                        createImageViewStar(R.drawable.ic_star_yellow);
                    }
                    else{
                        createImageViewStar(R.drawable.ic_star_rate);
                    }
                }
            }

            @Override
            public void onFailure(Call<Food> call, Throwable t) {

            }
        });
    }

    public void getCommentInFood() {
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<CommentResponse> call = apiService.getCommentByIdFood("getCommentByIdFood", idFood);

        call.enqueue(new Callback<CommentResponse>() {

            @Override
            public void onResponse(Call<CommentResponse> call, retrofit2.Response<CommentResponse> response) {
                CommentResponse result = response.body();
                List<CommentResponse.Comment> cmtList = result.data;
                listComment.clear();

                for (CommentResponse.Comment cmt : cmtList) {
                    listComment.add(new comment(cmt.maBL, cmt.noidung, cmt.thoigian, cmt.anhBL, cmt.maUser, cmt.maMon, cmt.avatarUser, cmt.tenUser));
                }

                listCommentAdap = new ListCommentAdap(DetailActivity.this, R.layout.list_comment_custome, listComment);
                if (cmtList.size() != 1) {
                    int height = cmtList.size() * 200;
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
                    lvComment.setLayoutParams(params);
                }
                lvComment.setAdapter(listCommentAdap);
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 650);
                dialogClass.imgShow.setLayoutParams(params);

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    int w = bitmap.getWidth();
                    int h = bitmap.getHeight();

                    if (h > w) {
                        Picasso.get().load(contentURI).rotate(90).into(dialogClass.imgShow);
                    }
                    else{
                        Picasso.get().load(contentURI).into(dialogClass.imgShow);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
