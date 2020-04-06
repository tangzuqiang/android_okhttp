package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import http.Http;
import utils.User;

public class MainActivity extends AppCompatActivity {
    private User user;
    private Button bt_success;
    private Button bt_error;
    private TextView t_text_view;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 测试okhttp
        bt_error = findViewById(R.id.error);
        bt_success = findViewById(R.id.success);
        t_text_view = findViewById(R.id.show_text);
        bt_success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_text_view.setText("");
                try {
                   JSONObject object =  Http.get("/index/getsuccess");
                   t_text_view.setText("GET请求\n");
                   t_text_view.append(object.toJSONString()+"\n");
                   System.out.println(object);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Map<String,Object> body = new HashMap<>();
                body.put("param1",1);
                body.put("param2","参数二");
                body.put("userDev",user.getUserDev());
                try {
                    JSONObject object = Http.post("/index/getsuccess",body);
                    t_text_view.append("Post请求\n");
                    t_text_view.append(object.toJSONString()+"\n");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        bt_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t_text_view.setText("");
                try {
                    JSONObject object =  Http.get("/index/geterror");
                    t_text_view.setText("GET请求\n");
                    t_text_view.append(object.toJSONString()+"\n");
                    System.out.println(object);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Map<String,Object> body = new HashMap<>();
                body.put("param1",1);
                body.put("param2","参数二");
                body.put("userDev",user.getUserDev());
                try {
                    JSONObject object = Http.post("/index/geterror",body);
                    t_text_view.append("Post请求\n");
                    t_text_view.append(object.toJSONString()+"\n");
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
