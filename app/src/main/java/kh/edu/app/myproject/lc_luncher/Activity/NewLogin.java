package kh.edu.app.myproject.lc_luncher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;

/**
 * Created by Pory Sovann on 8/19/2017.
 */


public class NewLogin extends AppCompatActivity {

    public static int APP_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_login_activity);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==APP_REQUEST_CODE){
            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);
            AccessToken accessToken = loginResult.getAccessToken();

            if(loginResult.getError()!=null){

                String toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(NewLogin.this,toastMessage,Toast.LENGTH_LONG).show();

            }else if (loginResult.getAccessToken()!= null){
                Log.d("Pory ","Token ="+accessToken.getAccountId()+"");
                launchAccountActivity(accessToken.getAccountId());
//                boolean checkToken = CheckToken(accessToken.getAccountId());
//                if(checkToken && accessToken.getAccountId()=="1995206714047553") {
//                    Intent intent = new Intent(this,AdminActivity.class);
//                    startActivity(intent);
//                }
//                if (checkToken)
//                {
//                    launchAccountActivity(accessToken.getAccountId());
//                }
            }
        }
    }

    private void setUserData(JSONObject result){
        try {

            User.setUserName(result.getString("_username"));
            User.setId(result.getInt("_id"));
            User.setDOB(result.getString("_dob"));
            User.setGender(result.getString("_gender"));
            User.setPhoneNumber(result.getString("_phone_number"));
            User.setPassWord(result.getString("_token"));
            User.setStatus(result.getInt("_admin"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void launchAccountActivity(final String token){
//        String url = "http://10.0.2.2/loginnew.php?";
//        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET,url,null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        try {
//                            Intent intent = new Intent();
//                            Log.d("Pory want to see", response.toString() + "");
//                            JSONObject result = response.getJSONObject(0);
//                            if (result != null) {
//                                Log.d("Pory", "Check phone number when start = " + result.getString("_phone_number"));
//                                Log.d("Pory", "Just Want to Check");
//                                Log.d("Pory Check Token", result.getString("_token") + "");
//
//                                boolean checkToken;
//                                if (token.equals(result.getString("_token"))) {
//                                    Log.d("Pory Check Token", "true" + result.getString("_token"));
//                                    checkToken = true;
//                                    setUserData(result);
//                                } else {
//                                    Log.d("Pory check token", "false");
//                                    checkToken = false;
//                                }
//
//                                if (checkToken && token == "1995206714047553") {
//                                    intent.setClass(NewLogin.this, AdminActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                }
//                                if (checkToken) {
//                                    intent.setClass(NewLogin.this, HomeFoodActivity.class);
//                                    startActivity(intent);
//                                    finish();
//                                } else {
//                                    Toast.makeText(NewLogin.this, "Login Failed", Toast.LENGTH_LONG).show();
//                                }
//                            }
//                        }
//                        catch (JSONException e) {
//
//                            Log.d("Pory","ErrorRespon 0 "+e.getLocalizedMessage());
//
//                        }
//                    }
//
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error){
//                        // Do something when error occurred
//                        Toast.makeText(NewLogin.this, "Loading data from server error", Toast.LENGTH_LONG).show();
//                        Log.d("Pory","ErrorRespon "+error.getMessage());
//
//                    }
//                }
//        ){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("token", token);
//                return params;
//            }
//        };
//
//        MySingleton.getInstance(NewLogin.this).addToRequestQueue(jsonObjectRequest);


        //wtf here
//        Log.d("Pory onCheckToken","check");
//        String url = "http://10.0.2.2/loginnew.php?token="+token;
//        Log.d("Pory check url",url);
//        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    Intent intent = new Intent();
//                    Log.d("Pory want to see", response.toString() + "");
//                    JSONObject result = response.getJSONObject(0);
//                    if (result != null) {
//                        Log.d("Pory", "Check phone number when start = " + result.getString("_phone_number"));
//                        Log.d("Pory", "Just Want to Check");
//                        Log.d("Pory Check Token", result.getString("_token") + "");
//
//                        boolean checkToken;
//                        if (token.equals(result.getString("_token"))) {
//                            Log.d("Pory Check Token", "true" + result.getString("_token"));
//                            checkToken = true;
//                            setUserData(result);
//                        } else {
//                            Log.d("Pory check token", "false");
//                            checkToken = false;
//                        }
//
//                        if (checkToken && token == "1995206714047553") {
//                            intent.setClass(NewLogin.this, AdminActivity.class);
//                            startActivity(intent);
//                            finish();
//                        }
//                        if (checkToken) {
//                            intent.setClass(NewLogin.this, HomeFoodActivity.class);
//                            startActivity(intent);
//                            finish();
//                        } else {
//                            Toast.makeText(NewLogin.this, "Login Failed", Toast.LENGTH_LONG).show();
//                        }
//
//                    }else
//                        Toast.makeText(NewLogin.this, "Login Failed", Toast.LENGTH_LONG).show();
//                }
//                catch (JSONException e) {
//
//                    Log.d("Pory","ErrorRespon 0 "+e.getLocalizedMessage());
//
//                }
//            }
//
//        }; Response.ErrorListener errorListener = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(NewLogin.this, "Loading data from server error", Toast.LENGTH_LONG).show();
//                Log.d("Pory","ErrorRespon "+error.getLocalizedMessage());
//
//            }
//        };
//        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
//        MySingleton.getInstance(NewLogin.this).addToRequestQueue(request);



        //
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                if(response.length()>0) {
//                    try {
//                        Log.d("Pory",response.length()+"    Check Login");
//                        JSONObject result =response.getJSONObject(0);
//                        User.setUserName(result.getString("_username"));
//                        User.setId(result.getInt("_id"));
//                        User.setDOB(result.getString("_dob"));
//                        User.setGender(result.getString("_gender"));
//                        //inComeToken=result.getString("_token");
//                        User.setPhoneNumber(result.getString("_phone_number"));
//                        Log.d("Pory","Just Want to Check");
//                        Log.d("Pory","Check phone number when start = "+result.getString("_phone_number"));
//                        User.setPassWord(result.getString("_token"));
//                        Log.d("Pory Check Token",User.getPassWord()+"");
//
//                    } catch (JSONException e) {
//                       Log.d("Pory sa",e.getMessage()+"");
//                    }
//                }else
//                    Toast.makeText(NewLogin.this,"Incorrect Username or Password 01",Toast.LENGTH_LONG).show();
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("Pory","Login Error respone"+error.getMessage());
//                Toast.makeText(NewLogin.this,"Incorrect Username or Password 02",Toast.LENGTH_LONG).show();
//            }
//        });
//
//        MySingleton.getInstance(this).addToRequestQueue(jsonArrayRequest);

        Log.d("Pory onCheckToken","check");

        String url = "http://10.0.2.2/loginnew.php?token="+token;

        Log.d("Pory check url",url);

        Response.Listener<JSONArray> listener = new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Intent intent = new Intent();
                    Log.d("Pory want to see", response.toString() + "");
                    JSONObject result = response.getJSONObject(0);
                    if (result != null) {
                        Log.d("Pory", "Check phone number when start = " + result.getString("_phone_number"));
                        Log.d("Pory", "Just Want to Check");
                        Log.d("Pory Check Token", result.getString("_token") + "");

                        boolean checkToken = false;
                        if (token.equals(result.getString("_token"))) {
                            Log.d("Pory Check Token", "true" + result.getString("_token"));
                            checkToken = true;
                            setUserData(result);
                        } else {
                            Log.d("Pory check token", "false");
                            checkToken = false;
                        }
                        //admin = 1, cilent = 0
                        if (checkToken && User.getStatus() == 1) {
                            Log.d("Pory ", "Login as admin");
                            intent.setClass(NewLogin.this, AdminActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        else if (checkToken) {
                            intent.setClass(NewLogin.this, HomeFoodActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }else

                        Toast.makeText(NewLogin.this, "Inncorrect", Toast.LENGTH_LONG).show();
                }
                catch (JSONException e) {

                    //still get error Value null at 0 of type org.json.JSONObject$1 cannot be converted to JSONObject
                    Toast.makeText(NewLogin.this, "Inncorrect", Toast.LENGTH_LONG).show();
                    Log.d("Pory","ErrorRespon 0 "+e.getLocalizedMessage());

                }
            }
        }; Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NewLogin.this, "Loading data from server error", Toast.LENGTH_LONG).show();
                Log.d("Pory","ErrorRespon "+error.getLocalizedMessage());

            }
        };
        JsonArrayRequest request = new JsonArrayRequest(url, listener, errorListener);
        MySingleton.getInstance(NewLogin.this).addToRequestQueue(request);

    }

    private void onLogin(LoginType loginType){

        Intent intent = new Intent(this, AccountKitActivity.class);
        String client = "+85570319640";
        String admin = "+85586474809";
        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType, AccountKitActivity.ResponseType.TOKEN
                );
        configurationBuilder.setDefaultCountryCode("KH");
        configurationBuilder.setInitialPhoneNumber(new PhoneNumber("KH",admin,"116"));

        AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION,configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    public void onPhoneLogin(View view){
        onLogin(LoginType.PHONE);
    }
    public void onEmailLogin(View view){
        onLogin(LoginType.EMAIL);
    }

}

