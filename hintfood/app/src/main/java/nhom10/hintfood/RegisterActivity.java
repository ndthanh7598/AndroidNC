package nhom10.hintfood;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;

import java.io.IOException;
import java.util.Arrays;

import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.ResultResponse;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister, btnBack;
    EditText etEmail, etPass, etRepass, etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getcontrol();
        activecontrol();

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(my_toolbar);
    }

    private void getcontrol(){
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnBack = (Button) findViewById(R.id.btnBack);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        etRepass = (EditText) findViewById(R.id.etRepass);
        etName = (EditText) findViewById(R.id.etName);
    }

    private void activecontrol(){
        btnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                registerAccount();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerAccount(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String repass = etRepass.getText().toString();
        String name = etName.getText().toString();

        if (pass.equals("")) {
            Toast.makeText(RegisterActivity.this, "Mật khẩu không được để trống", Toast.LENGTH_LONG).show();
            return;
        }
        else{
            if (!pass.equals(repass)){
                Toast.makeText(RegisterActivity.this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_LONG).show();
                return;
            }
            else{
//                new PostToServer(email, pass, name).execute(urlPostData);
                registerAccount(email, pass, name);
            }
        }
    }

    private void registerAccount(String email, String pass, String name){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.registerAccount(email, pass, name);

        call.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;
                if (noti.equals("Email đã tồn tại")){
                    Toast.makeText(RegisterActivity.this, "Email đã được đăng ký",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this, noti, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }
}
