package cn.edu.bistu.dj1801.licence;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import cn.edu.bistu.dj1801.licence.Utils.HttpUtils;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void signin(View view) {
        String get_name = ((EditText)findViewById(R.id.username)).getText().toString();
        String get_password = ((EditText)findViewById(R.id.password)).getText().toString();
        String get_con_password = ((EditText)findViewById(R.id.confirm_password)).getText().toString();

        if (get_con_password.equals(get_password) == true) {


            String url = "http://10.0.2.2:8080/signin/" + get_name + "/" + get_password;
            HttpUtils httpUtils = new HttpUtils();
            String result = httpUtils.request(url);
            if (result.equals("username exist")) {
                Toast.makeText(SigninActivity.this, "用户已存在", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(SigninActivity.this, "已注册，请登录", Toast.LENGTH_SHORT).show();

            }
            Intent intent_login = new Intent(SigninActivity.this, LoginActivity.class);
            startActivity(intent_login);
        }else{
            Toast.makeText(SigninActivity.this, "再次确认密码输入错误", Toast.LENGTH_SHORT).show();
        }

    }

}