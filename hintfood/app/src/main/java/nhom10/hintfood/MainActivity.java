package nhom10.hintfood;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.InfoLogin;
import nhom10.hintfood.ApiService.ResultResponse;
import nhom10.hintfood.Class.Config;
import nhom10.hintfood.Class.NotificationUtils;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    Button btnLogin, btnFb, btnGg;
    TextView tvDangKy;
    LinearLayout layout_hint;
    EditText etEmail, etPass;
    public static String maUser;
    public static String tenUser;
    public static String avatarUser;

    private CallbackManager callbackManager;
    private FacebookCallback<LoginResult> loginResult;

    private static final String TAG = MainActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        loginFacebook();
        getcontrol();
        activecontrol();

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
                    displayFirebaseRegId();
                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    String message = intent.getStringExtra("message");
                    Toast.makeText(getApplicationContext(), "Tin nhắn mới: " + message, Toast.LENGTH_LONG).show();
                    Log.e(TAG, "Firebase mess: " + message);
                }
            }
        };

        displayFirebaseRegId();

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(my_toolbar);
    }

    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF, 0);
        String regId = pref.getString("regId", null);

        Log.e(TAG, "Firebase reg id: " + regId);

//        if (!TextUtils.isEmpty(regId))
////            txtRegId.setText("Firebase Reg Id: " + regId);
//        else
//            txtRegId.setText("Firebase Reg Id is not received yet!");
    }

    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    private void getcontrol(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnFb = (Button) findViewById(R.id.btnFB);
        btnGg = (Button) findViewById(R.id.btnGoogle);
        tvDangKy = (TextView) findViewById(R.id.tvRegister);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        layout_hint = (LinearLayout) findViewById(R.id.layout_hint);
    }

    private void activecontrol(){
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginApp();
            }
        });
        btnFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile", "user_friends", "email"));
            }
        });
        btnGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loginApp(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

//        new PostToServer(email, pass).execute(urlPostData);
        connectLogin(email, pass);
    }

    private void connectLogin(String email, String pass){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<InfoLogin> call = apiService.loginApp(email, pass);

        call.enqueue(new Callback<InfoLogin>(){
            @Override
            public void onResponse(Call<InfoLogin> call, retrofit2.Response<InfoLogin> response) {
                InfoLogin info = response.body();

                if (info.result != null && info.result.equals("Không có dữ liệu")) {
                    Toast.makeText(MainActivity.this, "Sai tài khoản hoặc mật khẩu. Vui lòng kiểm tra lại", Toast.LENGTH_LONG).show();
                }
                else{
                    JSONObject object = new JSONObject();
                    try {
                        object.put("PK_iMaTaiKhoan", info._id);
                        object.put("sTenHienThi", info.hoten);
                        object.put("sGioiTinh", info.gioitinh);
                        object.put("sNgaySinh",info.ngaysinh);
                        object.put("sEmail", info.email);
                        object.put("sAvatar", info.avatar);
                        object.put("sNhom", info.nhom);
                        object.put("PK_iMaUser", info.maUser);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    maUser = info.maUser;
                    tenUser = info.hoten;
                    avatarUser = info.avatar;
                    sendRegistrationToServer();

                    Intent intent = new Intent();

                    if (info.khaosat.equals("chuaks")){
                        intent = new Intent(MainActivity.this, VoteActivity.class);
                    }
                    else {
                        intent = new Intent(MainActivity.this, HomeActivity.class);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("info", object.toString());
                    intent.putExtra("show", bundle);
                    startActivityForResult(intent, 2);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<InfoLogin> call, Throwable t) {

            }
        });
    }

    private void sendRegistrationToServer() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        // sending gcm token to server
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.saveTokenNotification(maUser, refreshedToken);

        call.enqueue(new Callback<ResultResponse>() {

            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
        Log.e(TAG, "sendRegistrationToServer: " + refreshedToken);
    }

    private void loginFacebook(){
        callbackManager = CallbackManager.Factory.create();
        initFaceBook();
        LoginManager.getInstance().registerCallback(callbackManager, loginResult);
    }

    private void initFaceBook () {
        loginResult = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
//                //Login thành công xử lý tại đây
//                GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
//                    new GraphRequest.GraphJSONObjectCallback() {
//                        @Override
//                        public void onCompleted(JSONObject object, GraphResponse response) {
////                                // Application code
////                                String name = object.optString(getString("name"));
//                            String id = object.optString(getString(R.string.id));
////                                String email = object.optString(getString(R.string.email));
////                                String link = object.optString(getString(R.string.link));
////                                URL imageURL = getFacebookAvatar(id);
////                                Log.d("name: ",name);
////                                Log.d("id: ",id);
////                                Log.d("email: ",email);
////                                Log.d("link: ",link);
////                                Log.d("imageURL: ",imageURL.toString());
//                        }
//                    });
//                Bundle parameters = new Bundle();
//                //parameters.putString(getString(R.string.fields), getString(R.string.fields_name));
//                request.setParameters(parameters);
//                request.executeAsync();
                URL imageURL = getFacebookAvatar(loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        };
    }

    public URL getFacebookAvatar(String id) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL imageURL = new URL("http://graph.facebook.com/" + id
                    + "/picture?type=large");
            return imageURL;
        } catch (Throwable e) {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
