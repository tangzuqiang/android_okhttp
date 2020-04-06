package http;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Http {
    /**
     * 获取get请求
     * @param uri
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
   public static JSONObject get(String uri) throws ExecutionException, InterruptedException {
        Callable c1 = new HttpUtils(uri);
        ExecutorService pool = Executors.newFixedThreadPool(1);
        //执行任务并获取Future对象
        Future f1 = pool.submit(c1);
        String res = f1.get().toString();
        pool.shutdown();
        return formatData(res);
    }

    /**
     * 执行post请求
     * @param uri
     * @param body
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static JSONObject post(String uri, Map<String,Object> body) throws ExecutionException, InterruptedException {
        Callable c1 = new HttpUtils(uri,body);
        ExecutorService pool = Executors.newFixedThreadPool(1);
        //执行任务并获取Future对象
        Future f1 = pool.submit(c1);
        String res = f1.get().toString();
        pool.shutdown();
        return  formatData(res);

    }
    private static JSONObject formatData(String json){
        try{
            JSONObject object = JSONObject.parseObject(json);
            return object;

        }catch (RuntimeException e){
            JSONObject object = new JSONObject();
            object.put("code",1);
            object.put("message",e.getMessage());
            return object;
        }
    }
}
