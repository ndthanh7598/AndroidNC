package nhom10.hintfood;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.ListKhaoSat;
import nhom10.hintfood.ApiService.ResultResponse;
import nhom10.hintfood.Object.member;
import retrofit2.Call;
import retrofit2.Callback;

public class VoteActivity extends AppCompatActivity {
    Button btnVote;
    TextView tvWelcome;
    LinearLayout lnTop, lnBot;
    CircleImageView imgAvatar;
    member mem;
    List<ListKhaoSat.KhaoSat> dmks;
    List<ListKhaoSat.KsUser> ksuser;
    String info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        mem = getDataLogin();
        getControl();
        activeControl();

        Picasso.get().load(MainActivity.avatarUser).into(imgAvatar);
        tvWelcome.setText("Welcome, " + MainActivity.tenUser);
     }

    private void getControl() {
        btnVote = (Button) findViewById(R.id.btnVote);
        tvWelcome = (TextView) findViewById(R.id.tvWelcome);
        lnTop = (LinearLayout) findViewById(R.id.lnTopCheck);
        lnBot = (LinearLayout) findViewById(R.id.lnBotCheck);
        imgAvatar = (CircleImageView) findViewById(R.id.imgAvatar);

        getListKhaoSat();
    }

    private void activeControl() {
        btnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listChecked = new ArrayList<>();
                for (ListKhaoSat.KhaoSat ks:dmks){
                    CircleImageView civ = (CircleImageView) findViewById(ks.maks);
                    if (civ.getBorderWidth() > 0){
                        listChecked.add(String.valueOf(ks.maks));
                    }
                }

                JSONArray jsArray = new JSONArray(listChecked);

                saveKhaoSat(jsArray.toString());
            }
        });
    }

    private member getDataLogin() {
        //tao ra 1 intent de lang nghe intent duoc truyen den
        final Intent intent = this.getIntent();
        //tao ra 1 bien bundle de chua bien bundle duoc gui sang qua key
        final Bundle bundle = intent.getBundleExtra("show");
        //tao ra 1 contact de lay duoc doi tuong contact duoc gui sang
        info = (String) bundle.get("info");

        member mem = null;
        try {
            JSONObject object = new JSONObject(info);

            mem = new member(
                    Integer.parseInt(object.optString("PK_iMaTaiKhoan")),
                    object.optString("sTenHienThi"),
                    object.optString("sEmail"),
                    object.optString(null)
            );
        } catch (JSONException e) {
            Log.e("Parse Json", "Không thể parse mảng 1" + info);
        }

        return mem;
    }

    private void getListKhaoSat(){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ListKhaoSat> call = apiService.getListKhaoSat("getListKhaoSat", String.valueOf(mem.getId()));

        call.enqueue(new Callback<ListKhaoSat>() {

            private void createCircleImageView(int i, LinearLayout linear){
                final CircleImageView civ = new CircleImageView(getBaseContext());
                civ.setId(dmks.get(i).maks);

                Picasso.get().load(dmks.get(i).image).into(civ);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(24, 24, 24, 24);
                lp.width = 300;
                lp.height = 300;
                civ.setLayoutParams(lp);

                civ.setBorderColor(Color.parseColor("#66FF33"));

                for (ListKhaoSat.KsUser user:ksuser){
                    if (user.maks.equals(dmks.get(i).maks)){
                        civ.setBorderWidth(20);
                        break;
                    }
                }

                civ.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int borWid = civ.getBorderWidth();
                        if (borWid > 0){
                            civ.setBorderWidth(0);
                        }
                        else{
                            civ.setBorderWidth(20);
                        }
                    }
                });
                linear.addView(civ);
            }

            @Override
            public void onResponse(Call<ListKhaoSat> call, retrofit2.Response<ListKhaoSat> response) {
                ListKhaoSat result = response.body();
                dmks = result.dmks;
                ksuser = result.ksuser;

                for (int i=0; i < (dmks.size()/2); i++){
                    createCircleImageView(i, lnTop);
                }

                for (int i=(dmks.size()/2); i < dmks.size(); i++){
                    createCircleImageView(i, lnBot);
                }
            }

            @Override
            public void onFailure(Call<ListKhaoSat> call, Throwable t) {

            }
        });
    }

    private void saveKhaoSat(String listKs){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.saveKhaoSat("saveKhaoSat", String.valueOf(mem.getId()), listKs);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;

                Toast.makeText(VoteActivity.this, noti, Toast.LENGTH_LONG).show();

//                Intent intent = new Intent(VoteActivity.this, SuggestFoodActivity.class);
                Intent intent = new Intent(VoteActivity.this, HomeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                intent.putExtra("show", bundle);
                startActivityForResult(intent, 2);
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }
}
