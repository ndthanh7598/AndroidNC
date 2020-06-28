package nhom10.hintfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HintActivity extends AppCompatActivity {

    Button btnHint;
    EditText etNguyenlieu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);

        Toolbar my_toolbar = (Toolbar) findViewById(R.id.Toolbar);
        setSupportActionBar(my_toolbar);

        getcontrol();
        activecontrol();

        // Hiện nút back ở toolbar
        getSupportActionBar().setTitle("Gợi ý theo nguyên liệu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void getcontrol(){
        btnHint = (Button) findViewById(R.id.btnHint);
        etNguyenlieu = (EditText) findViewById(R.id.etNguyenlieu);
    }

    private void activecontrol(){
        btnHint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String nguyenLieu = etNguyenlieu.getText().toString();
                if (nguyenLieu.equals("")){
                    Toast.makeText(HintActivity.this, "Chưa nhập nguyên liệu muốn được gợi ý", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(HintActivity.this,SuggestFoodActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("foodhint", nguyenLieu);
                intent.putExtra("show", bundle);

                startActivityForResult(intent, 2);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
