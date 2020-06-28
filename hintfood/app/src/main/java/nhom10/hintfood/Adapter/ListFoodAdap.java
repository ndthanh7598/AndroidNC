package nhom10.hintfood.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import nhom10.hintfood.Object.food;
import nhom10.hintfood.R;

public class ListFoodAdap extends ArrayAdapter<food> {
    Activity context;
    int resource;
    List<food> objects;
    ViewHolderr viewHolderr;

    public ListFoodAdap(@NonNull FragmentActivity context, int resource, @NonNull List<food> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = this.context.getLayoutInflater();
            convertView = inflater.inflate(resource, null);
            ViewHolderr viewHolderr = new ViewHolderr();
            viewHolderr.tvTenMon = convertView.findViewById(R.id.tvTenMon);
//            viewHolderr.imgAnh = convertView.findViewById(R.id.imgAnh);
            viewHolderr.lnImg = convertView.findViewById(R.id.lnImg);
            convertView.setTag(viewHolderr);
        }

        viewHolderr = (ViewHolderr) convertView.getTag();

        //lay ra doi tuong contact tai position
        final food food = objects.get(position);

        //hien thi cac gia tri contact len view
        viewHolderr.tvTenMon.setText(food.getTenmon());

//        ViewHolderr.lnImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("foodid", getItem(position).get_id());
//                intent.putExtra("show", bundle);
//                context.startActivityForResult(intent, 2);
//            }
//        });

        Picasso.get().load(food.getFileanh()).into(new Target(){

            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                viewHolderr.lnImg.setBackground(new BitmapDrawable(context.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        return convertView;
    }

    static class ViewHolderr{
        //khai bao cac doi tuong view trong adater
        TextView tvTenMon;
        static LinearLayout lnImg;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public food getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return objects.indexOf(objects.get(position));
    }
}