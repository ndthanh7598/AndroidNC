package nhom10.hintfood.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import nhom10.hintfood.ApiService.APIClient;
import nhom10.hintfood.ApiService.ApiService;
import nhom10.hintfood.ApiService.ResultResponse;
import nhom10.hintfood.Object.member;
import nhom10.hintfood.R;
import retrofit2.Call;
import retrofit2.Callback;

public class ListMemberAdap extends ArrayAdapter<member> {

    Activity context;
    int resource;
    List<member> objects;
//    private ListMemberAdap adapter;
//    String urlPostData ="http://ffcteam.com/androidnc/member.php";

    public ListMemberAdap(@NonNull Context context, int resource, @NonNull List<member> objects) {
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
            viewHolderr.tvHoten = convertView.findViewById(R.id.tvHoten);
            viewHolderr.ivAvatar = convertView.findViewById(R.id.ivAvatar);
            viewHolderr.delMember = convertView.findViewById(R.id.delMember);
            convertView.setTag(viewHolderr);
        }

        final ViewHolderr viewHolderr = (ViewHolderr) convertView.getTag();

        //lay ra doi tuong contact tai position
        final member member = objects.get(position);
        //hien thi cac gia tri contact len view
        viewHolderr.tvHoten.setText(member.gethoten());

        viewHolderr.delMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member mem = getItem(position);
                final int userId = mem.getId();

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                deleteMember(userId);
                                objects.remove(position);
//                                new DeleteGroup("delGroup", userId).execute(urlPostData);
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Bạn chắc chắn muốn xoá thành viên khỏi nhóm?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

//        URL url = null;
//        try {
//            url = new URL(member.getavatar());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        new AsyncTaskLoadImage(viewHolderr.ivAvatar).execute(member.getavatar());
        Picasso.get().load(member.getavatar()).into(viewHolderr.ivAvatar);

        return convertView;
    }

    private void deleteMember(int userId){
        ApiService apiService = APIClient.getClient().create(ApiService.class);

        Call<ResultResponse> call = apiService.delGroup("delGroup", userId);

        call.enqueue(new Callback<ResultResponse>(){

            @Override
            public void onResponse(Call<ResultResponse> call, retrofit2.Response<ResultResponse> response) {
                ResultResponse result = response.body();
                String noti = result.result;
                Toast.makeText(getContext(), noti, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResultResponse> call, Throwable t) {

            }
        });
    }

    static class ViewHolderr{
        TextView tvHoten;
        ImageView ivAvatar, delMember;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public member getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return objects.indexOf(objects.get(position));
    }
}
