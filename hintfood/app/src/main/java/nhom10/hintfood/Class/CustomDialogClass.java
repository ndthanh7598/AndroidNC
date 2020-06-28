package nhom10.hintfood.Class;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.ResultResponse;
import nhom10.hintfood.DetailActivity;
import nhom10.hintfood.MainActivity;
import nhom10.hintfood.Object.comment;
import nhom10.hintfood.R;
import retrofit2.Call;
import retrofit2.Callback;

public class CustomDialogClass extends Dialog implements android.view.View.OnClickListener {

    public Activity context;
    public int idFood;
    public int layout;
    public Button btnComment, btnDissmiss;
    public static final int GALLERY = 1;
    public ImageView imgPick, imgShow, imgRate1, imgRate2, imgRate3, imgRate4, imgRate5;
    int rate = 0;
    TextView tvRate;
    EditText etComment;

    public CustomDialogClass(Activity context, int idFood, int layoutResId) {
        super(context);
        this.context = context;
        this.idFood = idFood;
        this.layout = layoutResId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(layout);
//        setContentView(R.layout.dialog_comment);

        switch (layout){
            case R.layout.dialog_comment:
                getControl();
                break;
            case R.layout.dialog_rateus:
                getControl2();
                break;
        }
    }

    private void getControl(){
        btnComment = (Button) findViewById(R.id.btnComment);
        btnDissmiss = (Button) findViewById(R.id.btnDismiss);
        imgPick = (ImageView) findViewById(R.id.imgPick);
        imgShow = (ImageView) findViewById(R.id.imgShow);
        etComment = (EditText) findViewById(R.id.etComment);

        btnComment.setOnClickListener(this);
        btnDissmiss.setOnClickListener(this);
        imgPick.setOnClickListener(this);
    }

    private void getControl2(){
        imgRate1 = (ImageView) findViewById(R.id.imgRate1);
        imgRate2 = (ImageView) findViewById(R.id.imgRate2);
        imgRate3 = (ImageView) findViewById(R.id.imgRate3);
        imgRate4 = (ImageView) findViewById(R.id.imgRate4);
        imgRate5 = (ImageView) findViewById(R.id.imgRate5);
        tvRate = (TextView) findViewById(R.id.tvRate);

        imgRate1.setOnClickListener(this);
        imgRate2.setOnClickListener(this);
        imgRate3.setOnClickListener(this);
        imgRate4.setOnClickListener(this);
        imgRate5.setOnClickListener(this);
        tvRate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnComment:
                saveComment();
                break;
            case R.id.btnDismiss:
                dismiss();
                break;
            case R.id.imgPick:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*");
                context.startActivityForResult(pickPhoto , GALLERY);
                break;
            case R.id.imgRate1:
                rate = 1;
                imgRate1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                imgRate3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                imgRate4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                imgRate5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                break;
            case R.id.imgRate2:
                rate = 2;
                imgRate1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                imgRate4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                imgRate5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                break;
            case R.id.imgRate3:
                rate = 3;
                imgRate1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                imgRate5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                break;
            case R.id.imgRate4:
                rate = 4;
                imgRate1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_rate));
                break;
            case R.id.imgRate5:
                rate = 5;
                imgRate1.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate2.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate3.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate4.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                imgRate5.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_star_yellow));
                break;
            case R.id.tvRate:
                rateFood();
                break;
            default:
                break;
        }
//        dismiss();
    }

    private void saveComment(){
        String noidung = etComment.getText().toString();

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String time = spf.format(currentTime);
        String imgBase64 = "";

        try {
            Bitmap image = ((BitmapDrawable) imgShow.getDrawable()).getBitmap();
            imgBase64 = encodeBase64(image);
        }
        catch (Exception e){
            Log.e("err image", e.toString());
        }

        String userId = MainActivity.maUser;

        if (!noidung.trim().equals("") || !imgBase64.equals("")) {
            saveCommentToServer(userId, noidung, time, imgBase64);
        }
        else{
            Toast.makeText(context,"Bạn chưa nhập bình luận", Toast.LENGTH_LONG).show();
        }
    }

    public String encodeBase64(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    private void saveCommentToServer(String userId, final String noidung, final String time, String imgBase64){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.saveComment("saveComment", idFood, userId, noidung, time, imgBase64);

        call.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String linkanh = result.result;
                Toast.makeText(context, "Bình luận thành công!",Toast.LENGTH_LONG).show();
                dismiss();
                DetailActivity.listCommentAdap.add(new comment(noidung, time, linkanh, MainActivity.avatarUser, MainActivity.tenUser));
                int height = DetailActivity.lvComment.getHeight() + 200;
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
                DetailActivity.lvComment.setLayoutParams(params);
                DetailActivity.listCommentAdap.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }

    private void createImageViewStar(int icon){
        ImageView imgStart = new ImageView(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        imgStart.setLayoutParams(params);
        imgStart.setImageDrawable(ContextCompat.getDrawable(getContext(), icon));
        DetailActivity.lnRate.addView(imgStart);
    }

    private void rateFood(){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.rateFood("rateFood", idFood, rate);

        call.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;
                if (noti.equals("Có lỗi xảy ra. Vui lòng thử lại!")) {
                    Toast.makeText(context, noti, Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Gửi đánh giá thành công!", Toast.LENGTH_LONG).show();
                    float danhgia = Float.parseFloat(noti);
                    if (DetailActivity.lnRate.getChildCount() > 0) {
                        DetailActivity.lnRate.removeAllViews();
                    }
                    for (int i = 0; i < 5; i++){
                        if (i < danhgia){
                            createImageViewStar(R.drawable.ic_star_yellow);
                        }
                        else{
                            createImageViewStar(R.drawable.ic_star_rate);
                        }
                    }
                }
                dismiss();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }
}