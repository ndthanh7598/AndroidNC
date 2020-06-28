package nhom10.hintfood;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.ListFood;
import nhom10.hintfood.Object.food;
import retrofit2.Call;
import retrofit2.Callback;

public class SuggestFoodActivity extends AppCompatActivity {

    Intent intent;
    Bundle bundle;
    String foodhint;
    LinearLayout lnLeft, lnRight;
    private food fd;
    private LinearLayout ln;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_food);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(my_toolbar);

        intent = this.getIntent();
        bundle = intent.getBundleExtra("show");
        foodhint = (String) bundle.get("foodhint");

        lnLeft = (LinearLayout) findViewById(R.id.listLeft);
        lnRight = (LinearLayout) findViewById(R.id.listRight);

        getListFood();

        // Hiện nút back ở toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    private void createElementList(food fd, LinearLayout ln){
        final LinearLayout lnFood = new LinearLayout(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 5, 5, 5);
        lp.height = 350;
        lp.width = (int) (getScreenWidth(this) * 0.50);
        lnFood.setId(fd.get_id());
        lnFood.setLayoutParams(lp);
        lnFood.setGravity(Gravity.BOTTOM);
        Picasso.get().load(fd.getFileanh()).into(
            new Target(){

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    lnFood.setBackground(new BitmapDrawable(getBaseContext().getResources(), bitmap));
                }

                @Override
                public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {

                }
            }
        );

        TextView tvName = new TextView(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvName.setLayoutParams(params);
        tvName.setBackgroundResource(R.color.colorCrystal_clear);
        tvName.setPadding(10, 10,10,10);
        tvName.setText(fd.getTenmon());
        tvName.setTextColor(Color.WHITE);
        tvName.setTextSize(18);
        tvName.setAllCaps(true);

        lnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int idFood = lnFood.getId();

                Intent intent = new Intent(SuggestFoodActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("foodid", idFood);
                intent.putExtra("show", bundle);
                startActivityForResult(intent, 2);
            }
        });

        lnFood.addView(tvName); //thêm textview vào linear con
        ln.addView(lnFood); //đưa linear đã tạo vào linear danh sách
    }

    private void getListFood() {
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ListFood> call = apiService.getFoodByIngredients("getFoodByIngredients", foodhint);

        call.enqueue(new Callback<ListFood>() {

            @Override
            public void onResponse(Call<ListFood> call, retrofit2.Response<ListFood> response) {
                ListFood result = response.body();
                List<ListFood.Food> foodRes = result.data;
                int i = 0;
                for (ListFood.Food fd : foodRes) {
                    if (i % 2 == 0){
                        createElementList(new food(fd._id, fd.tenmon, fd.gioithieu, fd.congthuc, fd.nguyenlieu, fd.image, fd.danhgia, fd.xuatxu, fd.loai, fd.chedo), lnLeft);
                    }
                    else{
                        createElementList(new food(fd._id, fd.tenmon, fd.gioithieu, fd.congthuc, fd.nguyenlieu, fd.image, fd.danhgia, fd.xuatxu, fd.loai, fd.chedo), lnRight);
                    }
                    i++;
                }
            }

            @Override
            public void onFailure(Call<ListFood> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
