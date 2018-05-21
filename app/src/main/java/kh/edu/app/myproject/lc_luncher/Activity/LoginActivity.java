package kh.edu.app.myproject.lc_luncher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import kh.edu.app.myproject.lc_luncher.DB.DBOperations;
import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;

public class LoginActivity extends AppCompatActivity {
    DBOperations mydb;
    EditText Phone_number,Password;

    Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydb=new DBOperations(this);
        Phone_number=(EditText)findViewById(R.id.login_phone_number);
        Password=(EditText)findViewById(R.id.login_password);
        signup=(Button)findViewById(R.id.btn_signup);
        login=(Button)findViewById(R.id.btn_login);
        login();
    }
    public void login(){
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        String phonenumber=Phone_number.getText().toString();
//                        String password=Password.getText().toString();
//                        User  user=mydb.login(phonenumber,password);
//                        if(user!=null){
//                            Log.d("LC Launcer","Login Successful");
//                            Intent intent= new Intent (LoginActivity.this,HomeFoodActivity.class);
//                            startActivity(intent);
//                        }
//                        else if(phonenumber.equals("admin")&&password.equals("admin")) {
//                            Intent intent= new Intent (LoginActivity.this,AdminActivity.class);
//                            startActivity(intent);
//                        }
//                        else{
//                            Toast.makeText(LoginActivity.this, "Login isn't successful", Toast.LENGTH_LONG).show();
//                        }
                        String username = Phone_number.getText().toString();
                        String password = Password.getText().toString();
                        String url = "http://10.0.2.2/login.php?username="+username+"&password="+password;
                        Log.d("Pory","check url"+url);
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                if(response.contains("ok")) {
//                                    Intent intent = new Intent(LoginActivity.this, HomeFoodActivity.class);
//                                    startActivity(intent);
//                                }else
//                                    Toast.makeText(LoginActivity.this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//
//                            }
//                        }){
//                            @Override
//                            protected Map<String, String> getParams() throws AuthFailureError {
//                                Map<String,String> pramas = new HashMap<String, String>();
//                                pramas.put("username",Phone_number.getText().toString());
//                                pramas.put("password",Password.getText().toString());
//                                return pramas;
//                            }
//                        };
                        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {

                                Log.d("Pory",response.length()+"Check Login");
                                if(response.length()>0) {
                                    try {
                                        JSONObject result =response.getJSONObject(0);
                                        User.setUserName(result.getString("_username"));

                                        User.setId(result.getInt("_id"));
                                        User.setDOB(result.getString("_dob"));
                                        User.setGender(result.getString("_gender"));
                                        User.setPhoneNumber(result.getString("_phone_number"));
                                        User.setPassWord(result.getString("_password"));
                                        Intent intent = new Intent(LoginActivity.this, HomeFoodActivity.class);
                                        startActivity(intent);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }else
                                    Toast.makeText(LoginActivity.this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Pory","Login Error respone"+error.getMessage());
                                Toast.makeText(LoginActivity.this,"Incorrect Username or Password",Toast.LENGTH_LONG).show();
                            }
                        });
                        MySingleton.getInstance(LoginActivity.this).addToRequestQueue(jsonArrayRequest);
                   }
                }
        );
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
