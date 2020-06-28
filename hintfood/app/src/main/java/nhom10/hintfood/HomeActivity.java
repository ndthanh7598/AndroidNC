package nhom10.hintfood;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import nhom10.hintfood.Object.member;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button btnHintNL, btnHintWeek;
    TextView nameNavi, emailNavi;
    ImageView imageNavi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(my_toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, my_toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerLayout = navigationView.getHeaderView(0);
        nameNavi = (TextView) headerLayout.findViewById(R.id.nameNavi);
        emailNavi = (TextView) headerLayout.findViewById(R.id.emailNavi);
        imageNavi = (ImageView) headerLayout.findViewById(R.id.imageNavi);

        getcontrol();

//        // Hiện nút back ở toolbar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getcontrol(){
        btnHintNL = (Button) findViewById(R.id.btnHintNL);
        btnHintWeek = (Button) findViewById(R.id.btnHintWeek);

        //tao ra 1 intent de lang nghe intent duoc truyen den
        final Intent intent = this.getIntent();
        //tao ra 1 bien bundle de chua bien bundle duoc gui sang qua key
        final Bundle bundle = intent.getBundleExtra("show");
        //tao ra 1 contact de lay duoc doi tuong contact duoc gui sang
        final String info = (String) bundle.get("info");

        try {
            JSONObject object = new JSONObject(info);
            Log.e("length json", object.toString());

            member mem = new member(
                Integer.parseInt(object.optString("PK_iMaTaiKhoan")),
                object.optString("sTenHienThi"),
                object.optString("sEmail"),
                object.optString("sAvatar")
            );

            nameNavi.setText(mem.gethoten());
            emailNavi.setText(mem.getemail());
            Picasso.get().load(mem.getavatar()).into(imageNavi);
        } catch (JSONException e) {
            Log.e("Parse Json", "Không thể parse mảng 1" + info);
        }

        btnHintNL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,HintActivity.class);
                startActivity(intent);
            }
        });

        btnHintWeek.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,TabActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("info", info);
                intent.putExtra("show", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
//        finish();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = this.getIntent();
        final Bundle bundle = intent.getBundleExtra("show");

        switch (id){
//            case R.id.nav_camera:
////                break;
            case R.id.nav_personalinfo:
                intent = new Intent(HomeActivity.this, PersonalActivity.class);
                intent.putExtra("show", bundle);
                startActivity(intent);
                break;
            case R.id.nav_member:
                intent = new Intent(HomeActivity.this, MemberActivity.class);
                intent.putExtra("show", bundle);
                startActivityForResult(intent, 2);
                break;
            case R.id.nav_like:
                break;
            case R.id.nav_vote:
                intent = new Intent(HomeActivity.this, VoteActivity.class);
                intent.putExtra("show", bundle);
                startActivity(intent);
                break;
//            case R.id.nav_share:
//                break;
            case R.id.nav_logout:
                intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
