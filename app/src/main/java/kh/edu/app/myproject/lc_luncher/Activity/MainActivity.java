package kh.edu.app.myproject.lc_luncher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;

/**
 * Created by Pory Sovann on 5/13/2018.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        AccessToken accessToken = AccountKit.getCurrentAccessToken();
        if(accessToken!=null){
            Log.d("Pory","account id "+accessToken.getAccountId());
            launchHomeFoodActivity(accessToken.getAccountId());
        }

        setContentView(R.layout.activity_main);
        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_signup = (Button) findViewById(R.id.btn_signup);
        btn_login.setOnClickListener(this);
        btn_signup.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.btn_login:
                intent.setClass(this,NewLogin.class);
                break;
            case R.id.btn_signup:
                intent.setClass(this,SignupActivity.class);
                break;
        }
        startActivity(intent);
        finish();
    }

    private void setUserData(JSONObject result){
        try {

            User.setUserName(result.getString("_username"));
            User.setId(result.getInt("_id"));
            User.setDOB(result.getString("_dob"));
            User.setGender(result.getString("_gender"));
            User.setPhoneNumber(result.getString("_phone_number"));
            User.setPassWord(result.getString("_token"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void launchHomeFoodActivity(final String token) {

        String url = "http://10.0.2.2/loginnew.php?token="+token;

        Log.d("Pory check url",url);

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Intent intent = new Intent();
                    Log.d("Pory want to see",response.toString()+"");
                    JSONObject result =response.getJSONObject(0);

                    Log.d("Pory","Check phone number when start = "+result.getString("_phone_number"));
                    Log.d("Pory","Just Want to Check");
                    Log.d("Pory Check Token",result.getString("_token")+"");

                    boolean checkToken;
                    if(token.equals(result.getString("_token")) ){
                        Log.d("Pory Check Token","true" +result.getString("_token"));
                        checkToken = true;
                        setUserData(result);
                    }

                    else {
                        Log.d("Pory check token","false") ;
                        checkToken = false;
                    }

                    if(checkToken && token=="1995206714047553") {
                        intent.setClass(MainActivity.this,AdminActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }

                    if (checkToken)
                    {
                        intent.setClass(MainActivity.this,HomeFoodActivity.class);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                }

                catch (JSONException e) {
                    Log.d("Pory","ErrorRespon 0 "+e.getLocalizedMessage());

                }
            }
        }; Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Loading data from server error", Toast.LENGTH_LONG).show();
                Log.d("Pory","ErrorRespon "+error.getLocalizedMessage());
            }
        };
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        MySingleton.getInstance(MainActivity.this).addToRequestQueue(request);

    }
}
