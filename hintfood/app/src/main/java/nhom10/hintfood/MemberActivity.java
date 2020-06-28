package nhom10.hintfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nhom10.hintfood.Adapter.ListMemberAdap;
import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.MemberResponse;
import nhom10.hintfood.ApiService.ResultResponse;
import nhom10.hintfood.Object.member;
import retrofit2.Call;
import retrofit2.Callback;

public class MemberActivity extends AppCompatActivity {

    ListView lvMember;
    AutoCompleteTextView search;
    ListMemberAdap listMemberAdap;
    ArrayList<member> listMember = new ArrayList<member>();
    ArrayList<member> listSearchMember = new ArrayList<member>();
    ArrayList<String> arrayId = new ArrayList<String>();
    ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        getControl();
        try {
            activeControl();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Hiện nút back ở toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Thành viên");
    }

    private void getControl() {
        lvMember = (ListView) findViewById(R.id.lvMember);
        search = (AutoCompleteTextView) findViewById(R.id.search);
    }

    private void activeControl() throws JSONException {
        final Intent intent = this.getIntent();
        final Bundle bundle = intent.getBundleExtra("show");
        final String arrInfo = (String) bundle.get("info");
        final JSONObject jsonObject = new JSONObject(arrInfo);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getSearch("", jsonObject.getString("PK_iMaUser"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        search.showDropDown();
                    }
                }, 100);
            }
        });

        search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                search.setText("");
                String get_tu = (String) parent.getItemAtPosition(position);
                id = Long.parseLong(arrayId.get(position));
                Log.e("pr id", String.valueOf(id));

                final long finalId = id;
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                try {
                                    String currentId = jsonObject.getString("PK_iMaUser");
                                    addGroup(currentId, finalId);
                                    member mem = listSearchMember.get(position);
                                    listMember.add(mem);
                                    listMemberAdap = new ListMemberAdap(MemberActivity.this, R.layout.list_member_custome, listMember);
                                    lvMember.setAdapter(listMemberAdap);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MemberActivity.this);
                builder.setMessage("Bạn chắc chắn muốn thêm "+get_tu+" vào nhóm?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String word = search.getText().toString();
                try {
//                    new GetSearch("getSearchUsers", word, jsonObject.getString("PK_iMaUser")).execute(urlPostData);
                    getSearch(word, jsonObject.getString("PK_iMaUser"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        search.showDropDown();
                    }
                }, 100);
            }
        });

//        new GetGroup("getGroup", jsonObject.getString("sNhom"), jsonObject.getString("PK_iMaUser")).execute(urlPostData);
        getGroup(jsonObject.getString("sNhom"), jsonObject.getString("PK_iMaUser"));
    }

    private void addGroup(String currentId, long finalId){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.addGroup("addGroup", currentId, String.valueOf(finalId));

        call.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;
                Toast.makeText(MemberActivity.this, noti, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }

    private void getGroup(String sNhom, String userId){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<MemberResponse> call = apiService.getGroup("getGroup", sNhom, userId);

        call.enqueue(new Callback<MemberResponse>(){

            @Override
            public void onResponse(Call<MemberResponse> call, retrofit2.Response<MemberResponse> response) {
                MemberResponse result = response.body();
                List<MemberResponse.Member> memList = result.data;
                listMember.clear();
                for (MemberResponse.Member mem : memList) {
                    listMember.add(new member(mem._id, mem.hoten, mem.email, mem.avatar));
                }

                listMemberAdap = new ListMemberAdap(MemberActivity.this, R.layout.list_member_custome, listMember);
                lvMember.setAdapter(listMemberAdap);
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {

            }
        });
    }

    private void setSearchAdap(ArrayList<String> arrayList){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, arrayList);
        search.setAdapter(adapter);
    }

    private void getSearch(String word, String userId){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<MemberResponse> call = apiService.getSearch("getSearchUsers", word, userId);

        call.enqueue(new Callback<MemberResponse>(){

            @Override
            public void onResponse(Call<MemberResponse> call, retrofit2.Response<MemberResponse> response) {
                MemberResponse result = response.body();
                List<MemberResponse.Member> memList = result.data;
                arrayList.clear();

                for (MemberResponse.Member mem : memList) {
                    String name = mem.hoten;
                    if (!mem.ngaysinh.equals("")){
                        name += " - " + mem.ngaysinh;
                    }
                    arrayList.add(name);
                    arrayId.add(String.valueOf(mem._id));
                    listSearchMember.add(new member(mem._id, mem.hoten, mem.email, mem.avatar));
                }
                setSearchAdap(arrayList);
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
