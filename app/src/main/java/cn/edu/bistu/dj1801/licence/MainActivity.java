package cn.edu.bistu.dj1801.licence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.bistu.dj1801.licence.Utils.HttpUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    int flag = 0;
    public void selectF(View view) {

            TextView priceTextView = (TextView) findViewById(R.id.select_type);
            priceTextView.setText("燃油");
            String url = "http://10.0.2.2:8080/userquery/f";
            HttpUtils httpUtils = new HttpUtils();
            String result = httpUtils.request(url);



            String[] strs = result.split(",");
            ListView list = (ListView) findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
//        添加数据 ；
            list.setAdapter(adapter);
//        渲染数据 ；
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String s = strs[position];
                    int index = s.indexOf("=");
                    String result = s.substring(index + 1, index + 8);
                    if(flag == 0) {
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    String sel_url = "http://10.0.2.2:8080/userselect/" + result;
                    HttpUtils httpUtils = new HttpUtils();
                    s = httpUtils.request(sel_url);
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    flag = 1;}
                }
            });
        Toast.makeText(getApplicationContext(), "已选择", Toast.LENGTH_SHORT).show();

        //Intent intent_login = new Intent(MainActivity.this,LoginActivity.class);
        //startActivity(intent_login);
    }

    public void selectE(View view) {

            TextView priceTextView = (TextView) findViewById(R.id.select_type);
            priceTextView.setText("电动");
            String url = "http://10.0.2.2:8080/userquery/e";
            HttpUtils httpUtils = new HttpUtils();
            String result = httpUtils.request(url);



            String[] strs = result.split(",");
            ListView list = (ListView) findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
//        添加数据 ；
            list.setAdapter(adapter);
//        渲染数据 ；
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String s = strs[position];
                    int index = s.indexOf("=");
                    String result = s.substring(index + 1, index + 9);
                    if (flag==0) {
                        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                    String sel_url = "http://10.0.2.2:8080/userselect/" + result;
                    HttpUtils httpUtils = new HttpUtils();
                    s = httpUtils.request(sel_url);
                    Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
                    flag = 1;}
                }
            });
        Toast.makeText(getApplicationContext(), "已选择", Toast.LENGTH_SHORT).show();

//Intent intent_login = new Intent(MainActivity.this,LoginActivity.class);
//startActivity(intent_login);

    }

}