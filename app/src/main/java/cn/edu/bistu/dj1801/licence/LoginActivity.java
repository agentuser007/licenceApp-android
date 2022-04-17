package cn.edu.bistu.dj1801.licence;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import cn.edu.bistu.dj1801.licence.Utils.HttpUtils;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    }
    String admin_name = "admin";
    String admin_password = "admin";

    public void login(View view) throws IOException {
        String get_name = ((EditText)findViewById(R.id.username)).getText().toString();
        String get_password = ((EditText)findViewById(R.id.password)).getText().toString();
        if(admin_name.equals(get_name)&&admin_password.equals(get_password)){
            Intent intent_login = new Intent(LoginActivity.this,AdminActivity.class);
            startActivity(intent_login);
        }else if(get_name == null ||get_password == null){
            Toast.makeText(LoginActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();

        }
        String url =  "http://10.0.2.2:8080/login/"+get_name+"/"+get_password;
        //String url = "http://192.168.1.6:8080/login"+get_name+"/"+get_password;
        //String url = "http://baidu.com";
        HttpUtils httpUtils = new HttpUtils();
        String result = httpUtils.request(url);
        if (result.equals("wrong name")){
            Toast.makeText(LoginActivity.this, "用户名错误", Toast.LENGTH_SHORT).show();
        }else if(result.equals("wrong password")){
            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
        }else if(result.equals("0")){
            Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
            Intent intent_login = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent_login);
        }else{

            AlertDialog alertDialog2 = new AlertDialog.Builder(this)
                    .setTitle("已选择")
                    .setMessage(result)
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })

                    .create();
            alertDialog2.show();
        }

    }

    public void signin(View view) {
        Intent intent_login = new Intent(LoginActivity.this,SigninActivity.class);
        startActivity(intent_login);

    }
}