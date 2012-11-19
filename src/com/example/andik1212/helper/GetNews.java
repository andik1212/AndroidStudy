package com.example.andik1212.helper;

import android.util.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: Андрей
 * Date: 13.11.12
 * Time: 11:28
 * To change this template use File | Settings | File Templates.
 */
public class GetNews {
    private JSONArray jNewsArray = null;
    private static String answer;
    public GetNews(JSONArray jArray){
        this.jNewsArray=jArray;
    }

    public static String load(){
        URL url = null;

        try {
            url = new URL("http://android-developers.blogspot.com/feeds/posts/default?alt=json");
            URLConnection conn = null;
            conn = url.openConnection();
//            HttpConnectionParams.setConnectionTimeout((HttpParams) conn, 200/*CONNECTION_TIMEOUT*/);
//            HttpConnectionParams.setSoTimeout((HttpParams) conn, 200/*SOCKET_TIMEOUT*/);
            InputStreamReader rd = new InputStreamReader(conn.getInputStream());
            StringBuilder allpage = new StringBuilder();
            int n = 0;
            char[] buffer = new char[40000];
            while (n >= 0){
                n = rd.read(buffer, 0, buffer.length);
                if (n > 0){
                    allpage.append(buffer, 0, n);
                }
            }
            answer=allpage.toString();

        }
        catch (IOException e){
            Log.e("Error : ", "Error on soapPrimitiveData() " + e.getMessage());
            e.printStackTrace();

        }
        return answer;
    }



//    public static String load() throws IOException {
//        StringBuilder builder = new StringBuilder();
//        HttpClient client;
//        client = new DefaultHttpClient();
//        HttpGet httpGet = new HttpGet("http://android-developers.blogspot.com/feeds/posts/default?alt=json");
//
//        HttpResponse response = client.execute(httpGet);
//        StatusLine statusLine = response.getStatusLine();
//        int statusCode = statusLine.getStatusCode();
//        if (statusCode == 200) {
//            HttpEntity entity = response.getEntity();
//            InputStream content = entity.getContent();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(content));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                builder.append(line);
//            }
//        }
//
//        return builder.toString();
//    }
}
