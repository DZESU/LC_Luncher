package kh.edu.app.myproject.lc_luncher.Fragment;


import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoFragment extends AppCompatActivity {

    TextView txt_username;
    TextView txt_phone;
    TextView txt_dob;
    TextView txt_gender;
    TextView txt_edit;

    public PersonalInfoFragment() {
        // Required empty public constructor
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_personal_info);

        Toolbar tlbMain = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(tlbMain);
        getSupportActionBar().setTitle(R.string.app_name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Profile");

        txt_username = (TextView) findViewById(R.id.txt_username);
        txt_phone = (TextView) findViewById(R.id.txt_phoneNumber);
        txt_gender= (TextView) findViewById(R.id.txt_gender);
        txt_dob = (TextView) findViewById(R.id.txt_dob);
        txt_edit = (TextView) findViewById(R.id.txt_edit);

        txt_username.setText(User.getUserName());
        txt_phone.setText(getIntent().getStringExtra("phone"));
        txt_gender.setText(User.getGender());
        txt_dob.setText(User.getDOB());
        txt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnNavClicked(new PersonalInfoEditFragment());

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void OnNavClicked(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lyt_view,fragment);
        fragmentTransaction.commit();

    }


}
