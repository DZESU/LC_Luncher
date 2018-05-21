package kh.edu.app.myproject.lc_luncher.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.ui.AccountKitActivity;
import com.facebook.accountkit.ui.AccountKitConfiguration;
import com.facebook.accountkit.ui.LoginType;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import kh.edu.app.myproject.lc_luncher.MySingleton;
import kh.edu.app.myproject.lc_luncher.R;

import static kh.edu.app.myproject.lc_luncher.R.id.reg_user_name;
public class SignupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText EUsername, EDob, EGender;
    String username,dob,gender,token, contact;
    int signup_code;
    Button Reg;

    public static int APP_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Spinner sp_contact = (Spinner) findViewById(R.id.sp_contact);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Contact_info, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sp_contact.setAdapter(arrayAdapter);

        EUsername = (EditText) findViewById(reg_user_name);
        EDob = (EditText) findViewById(R.id.reg_DOB);
        EGender = (EditText) findViewById(R.id.reg_gender);
        Reg = (Button) findViewById(R.id.btn_reg);

        sp_contact.setOnItemSelectedListener(this);
        Register();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == APP_REQUEST_CODE) {

            AccountKitLoginResult loginResult = data.getParcelableExtra(AccountKitLoginResult.RESULT_KEY);

            AccessToken accessToken = loginResult.getAccessToken();
            token = accessToken.getAccountId() + "";

            //contact = AccountKit.getCurrentPhoneNumberLogInModel().toString();
            if (loginResult.getError() != null) {

                String toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(SignupActivity.this, toastMessage, Toast.LENGTH_LONG).show();

            } else if (loginResult.getAccessToken() != null) {
                Log.d("Pory ", "Token =" + accessToken.getAccountId() + "");
                setUser();

            }
        }
    }

    private void POSTTEST(){

        String url = "http://10.0.2.2/signUp.php";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                // Do something with response
                                //mTextView.setText(response.toString());

                                // Process the JSON
                                try{


                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener(){
                            @Override
                            public void onErrorResponse(VolleyError error){
                                // Do something when error occurred
                            }
                        }
                ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", EUsername.getText().toString());
                params.put("dob", EDob.getText().toString());
                params.put("gender", EGender.getText().toString());
                params.put("password", token);
                params.put("phoneNumber", contact);
                return params;
            }
        };

    }

    private void CheckAccount(){
        String url = "http://10.0.2.2/signUp.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Pory", response);
                if(response.contains("failed")){
                    Toast.makeText(SignupActivity.this,"This Number ready exits",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(SignupActivity.this, "Sign Up Successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignupActivity.this,HomeFoodActivity.class);
                    startActivity(intent);
                    SignupActivity.this.finish();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", EUsername.getText().toString());
                params.put("dob", EDob.getText().toString());
                params.put("gender", EGender.getText().toString());
                params.put("password", token);
                params.put("phoneNumber", contact);
                return params;
            }
        };
        MySingleton.getInstance(SignupActivity.this).addToRequestQueue(stringRequest);

    }

    private void setUser() {

        username = EUsername.getText().toString();
        dob = EDob.getText().toString();
        gender = EGender.getText().toString();
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(final Account account) {
                // Get Account Kit ID
                Log.d("Pory", account.getId() + " account id");
                String accountKitId = account.getId();
                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();
                if (signup_code == 1) {
                    if (phoneNumber != null) {
                        contact = phoneNumber.toString();
                    }
                }
                // Get email
                else if (signup_code == 2){
                    contact = account.getEmail();
                }

                CheckAccount();
            }

            @Override
            public void onError(final AccountKitError error) {
                Toast.makeText(SignupActivity.this, "Get Contact Error", Toast.LENGTH_LONG).show();
                Log.d("pory", "onError get contact: " + error.getErrorType().getMessage());
            }
        });

    }

    private void onLogin(LoginType loginType) {

        Intent intent = new Intent(this, AccountKitActivity.class);

        AccountKitConfiguration.AccountKitConfigurationBuilder configurationBuilder =
                new AccountKitConfiguration.AccountKitConfigurationBuilder(
                        loginType, AccountKitActivity.ResponseType.TOKEN
                );
        configurationBuilder.setDefaultCountryCode("KH");
        configurationBuilder.setInitialPhoneNumber(new PhoneNumber("KH","+85586474809","116"));
        AccountKitConfiguration configuration = configurationBuilder.build();
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configuration);
        startActivityForResult(intent, APP_REQUEST_CODE);

    }

    private void Register() {
        Reg.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (signup_code) {
                            case 1:
                                onLogin(LoginType.PHONE);
                                contact = AccountKit.getCurrentLogInModel() + "";
                                break;
                            case 2:
                                onLogin(LoginType.EMAIL);
                                contact = AccountKit.getCurrentEmailLogInModel() + "";
                                break;
                        }
                    }
                }

        );

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Log.d("pory", "onitemselected " + i);
        signup_code = i;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



}
