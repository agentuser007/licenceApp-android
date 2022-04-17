package cn.edu.bistu.dj1801.licence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.bistu.dj1801.licence.Utils.HttpUtils;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }

    public void generate(View view) {
        Toast.makeText(AdminActivity.this,"已生成", Toast.LENGTH_SHORT).show();
        String get_setting = ((EditText)findViewById(R.id.setting)).getText().toString();

        String url = "http://10.0.2.2:8080/generate/"+get_setting;
        HttpUtils httpUtils = new HttpUtils();
        String result = httpUtils.request(url);
    }

    public void adminquery(View view) {
        String url = "http://10.0.2.2:8080/adminquery";
        HttpUtils httpUtils = new HttpUtils();
        String result = httpUtils.request(url).replace("},","\n");
        TextView priceTextView = (TextView) findViewById(R.id.result);
        priceTextView.setText(result);


//        String[] strs = result.split("},");
/*
        ListView list = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
//        添加数据 ；
        list.setAdapter(adapter);
//        渲染数据 ；
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
*/
    }

}