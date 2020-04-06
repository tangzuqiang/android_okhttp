package http;

import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.Callable;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

class HttpUtils implements Callable {
    private String type = "get";
    private Map<String, Object> body;
    private String url = "http://tp5app.cc/index.php/api";
    private OkHttpClient client = new OkHttpClient();
    private Request request;
    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");
    HttpUtils(String uri) {
        this.url = this.url + uri;
    }
    HttpUtils(String uri, Map<String, Object> body) {
        this.url = this.url + uri;
        this.body = body;
        this.type = "post";
    }
    @Override
    public Object call() throws Exception {
        Request.Builder builder = new Request.Builder().url(this.url);
        System.out.println(this.type);
        if (this.type.equals("post")) {
            //添加post参数
            String bodyStr = this.body.toString();
            System.out.println(bodyStr);
            JSONObject jsonObject = new JSONObject();
            for(Map.Entry<String, Object> entry : body.entrySet()){
                String mapKey = entry.getKey();
                Object mapValue = entry.getValue();
                System.out.println(mapKey+":"+mapValue);
                jsonObject.put(mapKey,mapValue);
            }
            RequestBody body = RequestBody.create(JSON,jsonObject.toString());
            request = builder.post(body).build();
        } else {
            request = builder.build();
        }
        Response response = client.newCall(request).execute();
        String res = response.body().string();
        // 输出获取请求结果
        System.out.println(res);
        return res;

    }
}

