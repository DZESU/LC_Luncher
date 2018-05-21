package kh.edu.app.myproject.lc_luncher.Fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalInfoEditFragment extends Fragment {


    public PersonalInfoEditFragment() {
        Log.d("Pory","Check constructor edit");
        // Required empty public constructor
    }

    EditText edtUsername;
    EditText edtPhonenumber;
    EditText edtPassword;
    EditText edtGender;
    EditText edtDoB;
    Button updateProfile;
    TextView txt_name;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_personal_info_edit, container, false);

        Log.d("Pory","Check onCreate");
        edtPhonenumber =(EditText) view.findViewById(R.id.reg_phone_number);
        edtPassword =(EditText) view.findViewById(R.id.reg_password);
        edtGender =(EditText) view.findViewById(R.id.reg_gender);
        edtDoB =(EditText) view.findViewById(R.id.reg_DOB);
        updateProfile = (Button) view.findViewById(R.id.btn_reg);
        txt_name = (TextView) view.findViewById(R.id.txt_name);

        txt_name.setText(User.getUserName());
       // edtUsername.setText(User.getUserName());
        edtPhonenumber.setText(User.getPhoneNumber());
        edtGender.setText(User.getGender());
        edtDoB.setText(User.getDOB());
        edtPassword.setText(User.getPassWord());

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            updateClicked();
            }
        });


        return view;
    }

    private void updateClicked(){
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,)

//        DBOperations db = new DBOperations(getActivity());
//        db.UpdateData(edtUsername.getText().toString(),edtDoB.getText().toString(),edtGender.getText().toString(),edtPhonenumber.getText().toString(),edtPassword.getText().toString());
//        new User(User.getId(),edtUsername.getText().toString(),edtDoB.getText().toString(),edtGender.getText().toString(),edtPhonenumber.getText().toString(),edtPassword.getText().toString());


    }

}
