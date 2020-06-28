package nhom10.hintfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.MyInfo;
import nhom10.hintfood.ApiService.ResultResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class PersonalActivity extends AppCompatActivity {
    Button btnEdit, btnBack;
    EditText etName, etBirth;
    RadioGroup rdSex;
    RadioButton rdMale,rdFemale;
    ImageView logo_info;
    public static final int GALLERY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        getControl();
        getMyInfo();
        activecontrol();
    }

    private void getControl() {
        logo_info = (ImageView) findViewById(R.id.logo_info);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        etName = (EditText) findViewById(R.id.etName);
        etBirth = (EditText) findViewById(R.id.etBirth);
        rdSex = (RadioGroup) findViewById(R.id.rdSex);
        btnBack = (Button) findViewById(R.id.btnBack);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
    }

    private void getMyInfo() {
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<MyInfo> call = apiService.getMyInfo("getMyInfo", Integer.parseInt(MainActivity.maUser));

        call.enqueue(new Callback<MyInfo>() {

            @Override
            public void onResponse(Call<MyInfo> call, retrofit2.Response<MyInfo> response) {
                MyInfo info = response.body();
                Picasso.get().load(info.avatar).into(logo_info);
                etName.setText(info.hoten);
                etBirth.setText(info.ngaysinh);
                if(info.gioitinh.equals("Nam")){
                    rdMale.setChecked(true);
                }
                else {
                    rdFemale.setChecked(true);
                }
            }

            @Override
            public void onFailure(Call<MyInfo> call, Throwable t) {

            }
        });
    }

    private void activecontrol(){
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = etName.getText().toString();
                String ngaysinh = etBirth.getText().toString();
                String gioitinh = "";
                if(rdMale.isChecked()){
                    gioitinh = "Nam";
                }

                if(rdFemale.isChecked()){
                    gioitinh = "Nữ";
                }
ơ
                String imgBase64 = "";

                try {
                    Bitmap image = ((BitmapDrawable) logo_info.getDrawable()).getBitmap();
                    imgBase64 = encodeBase64(image);
                }
                catch (Exception e){
                    Log.e("err image", e.toString());
                }

                ApiService apiService = APIClient.getClient().create(ApiService.class);

                Call<ResultResponse> call = apiService.updateMyInfo("update_MyInfo", Integer.parseInt(MainActivity.maUser), hoten, ngaysinh, gioitinh, imgBase64);

                call.enqueue(new Callback<ResultResponse>(){

                    @Override
                    public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                        ResultResponse result = response.body();
                        String noti = result.result;
                        Toast.makeText(PersonalActivity.this, noti,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<ResultResponse> call, Throwable t) {

                    }
                });
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        logo_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickPhoto.setType("image/*");
                PersonalActivity.this.startActivityForResult(pickPhoto , GALLERY);
            }
        });
    }

    public String encodeBase64(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
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
                Picasso.get().load(contentURI).into(logo_info);
            }
        }
    }
}
