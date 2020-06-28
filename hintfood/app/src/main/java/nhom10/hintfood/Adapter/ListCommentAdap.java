package nhom10.hintfood.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nhom10.hintfood.Object.comment;
import nhom10.hintfood.R;

public class ListCommentAdap extends ArrayAdapter<comment> {

    Activity context;
    int resource;
    List<comment> objects;
    ViewHolderr viewHolderr;

    public ListCommentAdap(@NonNull Context context, int resource, @NonNull List<comment> objects) {
        super(context, resource, objects);
        this.context = (Activity) context;
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
            viewHolderr.avatarCmt = convertView.findViewById(R.id.avatarCmt);
            viewHolderr.nameUser = convertView.findViewById(R.id.nameUser);
            viewHolderr.ndCmt = convertView.findViewById(R.id.ndCmt);
            viewHolderr.imgCmt = convertView.findViewById(R.id.imgCmt);
            convertView.setTag(viewHolderr);
        }

        viewHolderr = (ViewHolderr) convertView.getTag();

        //lay ra doi tuong contact tai position
        comment cmt = objects.get(position);

        Picasso.get().load(cmt.getAvatar()).into(viewHolderr.avatarCmt);
        viewHolderr.nameUser.setText(cmt.getTenUser());
        viewHolderr.ndCmt.setText(cmt.getNoidung());

        if (!cmt.getAnhbl().equals("")) {
            Picasso.get().load(cmt.getAnhbl()).into(viewHolderr.imgCmt);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, 650);
            params.gravity = Gravity.LEFT;
            params.topMargin = 15;
            viewHolderr.imgCmt.setLayoutParams(params);
        }

        return convertView;
    }

    static class ViewHolderr{
        TextView nameUser, ndCmt;
        ImageView avatarCmt, imgCmt;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public comment getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return objects.indexOf(objects.get(position));
    }

    @Override
    public void add(@Nullable comment object) {
        super.add(object);
    }
}