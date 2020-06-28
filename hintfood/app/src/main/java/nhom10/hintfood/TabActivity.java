package nhom10.hintfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import nhom10.hintfood.Adapter.ListFoodAdap;
import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.FoodInDay;
import nhom10.hintfood.ApiService.ListFood;
import nhom10.hintfood.ApiService.ResultResponse;
import nhom10.hintfood.Object.food;
import retrofit2.Call;
import retrofit2.Callback;

public class TabActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private String[] tabs = {
            "Thứ 2",
            "Thứ 3",
            "Thứ 4",
            "Thứ 5",
            "Thứ 6",
            "Thứ 7",
            "Chủ nhật "
    };

    private static String[] dates = getAllDayOfWeek();

    private TabLayout tabLayout;
//    public static int[] resourceIds = {
//            R.layout.fragment_monday,
//            R.layout.fragment_tuesday,
//            R.layout.fragment_wednesday,
//            R.layout.fragment_thursday,
//            R.layout.fragment_friday,
//            R.layout.fragment_saturday,
//            R.layout.fragment_sunday
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        // Selected page today
        SimpleDateFormat dateFormater = new SimpleDateFormat("dd/MM/yyyy");
        String dateNow = dateFormater.format(Calendar.getInstance().getTime());
        for (int i = 0; i < dates.length; i++){
            if (dates[i].equals(dateNow)){
                mViewPager.setCurrentItem(i);
            }
        }

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        getSupportActionBar().setTitle("Thực đơn tuần");
        // Hiện nút back ở toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private static String[] getAllDayOfWeek(){
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        String[] date = new String[7];
        for (int i = 0; i < 7; i++)
        {
            date[i] = format.format(calendar.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
        return date;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.action_settings:
                break;
            default:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        ArrayList<food> listFood = new ArrayList<food>();
        ListFoodAdap listFoodAdap;
        private ListView lvFood;
        private LinearLayout lnSangg, lnTruaa, lnToii;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_tab, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

            final int index = getArguments().getInt(ARG_SECTION_NUMBER);
//            View rootView = inflater.inflate(resourceIds[index], container, false);
            View rootView = inflater.inflate(R.layout.fragment_monday, container, false);

            lvFood = (ListView) rootView.findViewById(R.id.lvFood);
            lnSangg = (LinearLayout) rootView.findViewById(R.id.lnSang);
            lnTruaa = (LinearLayout) rootView.findViewById(R.id.lnTrua);
            lnToii = (LinearLayout) rootView.findViewById(R.id.lnToi);
            getListFood();
            getFoodInDay(rootView, dates[index], getContext());

            lvFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("foodid", listFood.get(position).get_id());
                    intent.putExtra("show", bundle);
                    startActivityForResult(intent, 2);
                }
            });

            lvFood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(final AdapterView<?> parent, final View view, final int position, long id) {
                    final PopupMenu popup = new PopupMenu(getContext(), view);
                    popup.inflate(R.menu.menu_add_food);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            int id = item.getItemId();
                            final food fd = listFood.get(position);
                            switch (id){
                                case R.id.breakfast:
                                    addFoodToDay(lnSangg, fd, "sang", dates[index], getContext());
                                    return true;
                                case R.id.lunch:
                                    addFoodToDay(lnTruaa, fd, "trua", dates[index], getContext());
                                    return true;
                                case R.id.dinner:
                                    addFoodToDay(lnToii, fd, "toi", dates[index], getContext());
                                    return true;
                            }
                            return true;
                        }
                    });
                    popup.show();
                    return true;
                }
            });

            return rootView;
        }

        private void getListFood() {
            final Intent intent = getActivity().getIntent();
            final Bundle bundle = intent.getBundleExtra("show");
            final String info = (String) bundle.get("info");

//            member mem = null;
//            try {
//                JSONObject object = new JSONObject(info);
//
//                mem = new member(
//                        Integer.parseInt(object.optString("PK_iMaTaiKhoan")),
//                        object.optString("sTenHienThi"),
//                        object.optString("sEmail"),
//                        object.optString(null)
//                );
//            } catch (JSONException e) {
//                Log.e("Parse Json", "Không thể parse mảng 1" + info);
//            }

            listFood.clear();

            ApiService apiService = APIClient.getClient().create(ApiService.class);

            Call<ListFood> call = apiService.getFood("getFoodHint", MainActivity.maUser);

            call.enqueue(new Callback<ListFood>() {

                @Override
                public void onResponse(Call<ListFood> call, retrofit2.Response<ListFood> response) {
                    ListFood result = response.body();
                    List<ListFood.Food> foodRes = result.data;

                    for (ListFood.Food fd : foodRes) {
                        listFood.add(new food(fd._id, fd.tenmon, fd.image));
                    }

                    listFoodAdap = new ListFoodAdap(getActivity(), R.layout.list_food_custome, listFood);
                    lvFood.setAdapter(listFoodAdap);
                }

                @Override
                public void onFailure(Call<ListFood> call, Throwable t) {

                }
            });
        }
    }

    private static void createElement(LinearLayout ln, final food fd, final Context c, int idTD){
        final TextView tv = new TextView(c);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.leftMargin = 30;
        lp.bottomMargin = 5;
        tv.setLayoutParams(lp);
        tv.setPadding(20, 20, 20,20);
        tv.setId(idTD);
        tv.setText(fd.getTenmon());
        tv.setTextSize(20);
        tv.setTextColor(ContextCompat.getColor(c, R.color.colorWhite));
        tv.setBackgroundColor(ContextCompat.getColor(c, R.color.colorCrystal_clear));
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                final PopupMenu popup = new PopupMenu(c, v);
                popup.inflate(R.menu.menu_activity_food);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        switch (id){
                            case R.id.detail:
                                Intent intent = new Intent(c, DetailActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("foodid", fd.get_id());
                                intent.putExtra("show", bundle);
                                c.startActivity(intent);
                                return true;
                            case R.id.delete:
                                int idTD = v.getId();
                                deleteFoodInDay(idTD, c);
                                ((ViewManager)v.getParent()).removeView(v);
                                return true;
                        }
                        return true;
                    }
                });
                popup.show();
            }
        });

        ln.addView(tv);
    }

    private static void deleteFoodInDay(int idTD, final Context c){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.deleteFoodInDay("deleteFoodInDay", idTD);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;
                Toast.makeText(c, noti, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }

    private static void getFoodInDay(final View v, String ngay, final Context c) {
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<FoodInDay> call = apiService.getFoodInDay("getFoodInDay", ngay, MainActivity.maUser);

        call.enqueue(new Callback<FoodInDay>() {
            @Override
            public void onResponse(Call<FoodInDay> call, retrofit2.Response<FoodInDay> response) {
                FoodInDay result = response.body();
                List<FoodInDay.Food> listSang = result.sang;
                List<FoodInDay.Food> listTrua = result.trua;
                List<FoodInDay.Food> listToi = result.toi;

                LinearLayout lnSang = v.findViewById(R.id.lnSang);
                LinearLayout lnTrua = v.findViewById(R.id.lnTrua);
                LinearLayout lnToi = v.findViewById(R.id.lnToi);

                for (FoodInDay.Food fd : listSang) {
                    createElement(lnSang, new food(fd._id, fd.tenmon, fd.image), c, fd.maTD);
                }

                for (FoodInDay.Food fd : listTrua) {
                    createElement(lnTrua, new food(fd._id, fd.tenmon, fd.image), c, fd.maTD);
                }

                for (FoodInDay.Food fd : listToi) {
                    createElement(lnToi, new food(fd._id, fd.tenmon, fd.image), c, fd.maTD);
                }
            }

            @Override
            public void onFailure(Call<FoodInDay> call, Throwable t) {

            }
        });
    }

    private static void addFoodToDay(final LinearLayout ln, final food fd, final String buoi, String ngay, final Context c){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.saveFoodInDay("saveFoodInDay", buoi, ngay, fd.get_id(), MainActivity.maUser);

        call.enqueue(new Callback<ResultResponse>() {
            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String idTD = result.result;
                Toast.makeText(c, "Thêm vào thực đơn thành công", Toast.LENGTH_LONG).show();

                createElement(ln, fd, c, Integer.parseInt(idTD));
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
//            return super.getPageTitle(position);
            return tabs[position];
        }
    }
}
