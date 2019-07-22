package com.example.rebelfoodchallengeapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class workManager extends Worker {


    String url =  dataWire.getUrl();
    private static final String TAG = "MyPeriodicWork";
    users users =null;

    List<users> usersList ;
    String urlLINK;
    boolean status =false;



    @NonNull
    @Override
    public Result doWork() {
        usersList =  new ArrayList<>();

        Log.e(TAG, "doWork: Work is done.  "+usersList.size()+" ");
        if (getData())
        {
            return Result.SUCCESS;
        }else
        {
            return Result.RETRY;
        }
    }

    public boolean getData()
    {

        OkHttpClient client = new OkHttpClient();

        Log.e("url >>>>>>",url);
        //Toast.makeText(getApplicationContext(), ""+e, Toast.LENGTH_SHORT).show();

        final Request request =  new  Request.Builder().url(url).build();
        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful())
                {
                    String jsonData  =  response.body().string();
                    Log.e("JSON DATA",jsonData);
                    try {
                        usersList.clear();
                        JSONArray usersO =  new JSONArray(jsonData);
                        Log.e("result",usersO.length()+"");
                        for (int i=0;i<usersO.length();i++)
                        {

                            JSONObject o = usersO.getJSONObject(i);
                            String id = String.valueOf(o.get("id"));
                            String name = String.valueOf(o.get("name"));
                            String username = String.valueOf(o.get("username"));

                            String email= String.valueOf(o.get("email"));


                            JSONObject addressO = o.getJSONObject("address");
                            String  street = String.valueOf(addressO.get("street"));
                            String  suite = String.valueOf(addressO.get("suite"));
                            String  city = String.valueOf(addressO.get("city"));
                            String  zipcode = String.valueOf(addressO.get("zipcode"));
                            JSONObject GEO = addressO.getJSONObject("geo");

                            String  lat = String.valueOf(GEO.get("lat"));
                            String lng = String.valueOf(GEO.get("lng"));





                            users = new users( id,name,username,email,street,suite,city,zipcode,lat,lng,0);

                            usersList.add(users);
                            status = true;


                        }



                        Log.d("size of result",usersList.size()+"");
                        //adapter.notifyDataSetChanged();

                        dataWire.setUsersList(usersList);


                        Log.e(TAG, "doWork: Work ."+usersList.size()+users.getName());
                    } catch (JSONException e1) {
                        e1.printStackTrace();
                    }



                }
            }
        });
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }


        return status;
    }

}
