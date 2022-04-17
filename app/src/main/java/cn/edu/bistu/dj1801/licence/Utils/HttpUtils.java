package cn.edu.bistu.dj1801.licence.Utils;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import cn.edu.bistu.dj1801.licence.LoginActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpUtils {

    public String request(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)//访问连接
                .get()
                .build();
        Call call = client.newCall(request);
        //通过execute()方法获得请求响应的Response对象
        String result = "";

        try {
            Response response = call.execute();//发送请求
            if (response.isSuccessful()) {
                result = response.body().string();
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}
