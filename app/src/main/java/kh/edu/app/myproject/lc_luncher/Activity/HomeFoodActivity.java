package kh.edu.app.myproject.lc_luncher.Activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;

import kh.edu.app.myproject.lc_luncher.DB.User;
import kh.edu.app.myproject.lc_luncher.Fragment.CastsFragment;
import kh.edu.app.myproject.lc_luncher.Fragment.FoodsFragment;
import kh.edu.app.myproject.lc_luncher.Fragment.HistoryFragment;
import kh.edu.app.myproject.lc_luncher.Fragment.LCinfoFragment;
import kh.edu.app.myproject.lc_luncher.Fragment.PersonalInfoFragment;
import kh.edu.app.myproject.lc_luncher.R;
import kh.edu.app.myproject.lc_luncher.datamodel.Food;

/**
 * Created by user on 7/25/2017.
 */

    public class HomeFoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    DrawerLayout drawerLayout;
    static String phoneNumber;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Log.d("Pory Check Token",User.getUserName());
        Toolbar tlbMain = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(tlbMain);
        getSupportActionBar().setTitle(R.string.app_name);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View navHeader = navigationView.getHeaderView(0);
        TextView btn_logout = (TextView)navHeader.findViewById(R.id.btn_logout);
        TextView txt_username = (TextView)navHeader.findViewById(R.id.txt_username);
        final TextView txt_phone = (TextView)navHeader.findViewById(R.id.txt_email);
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                phoneNumber=account.getPhoneNumber()+"";
                if(account.getPhoneNumber()!=null)
                txt_phone.setText(account.getPhoneNumber()+"");
                else if (account.getEmail()!=null)
                    txt_phone.setText(account.getEmail());

            }

            @Override
            public void onError(AccountKitError accountKitError) {
                Toast.makeText(HomeFoodActivity.this,"Something Went Wrong",Toast.LENGTH_LONG).show();
            }
        });


        txt_username.setText(User.getUserName());
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Pory n", " hello");
                AccountKit.logOut();

                launchMainActivity();
            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_main);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, tlbMain, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        OnNavClicked(new FoodsFragment(Food.Category.FOOD.toString()));

    }

    private void launchMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void OnNavClicked(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.lyt_view,fragment);
        fragmentTransaction.commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d("Pory","Check Navigation clicked");
        Intent intent = new Intent();
        switch (item.getItemId()){
            case R.id.mnu_food:
                Log.d("Pory","Enum check  "+Food.Category.FOOD.toString());
                OnNavClicked(new FoodsFragment(Food.Category.FOOD.toString()));
                getSupportActionBar().setTitle("Food");
                break;
            case R.id.mnu_drink:
                Log.d("Pory","Enum check  "+Food.Category.DRINK.toString());
                OnNavClicked(new FoodsFragment(Food.Category.DRINK.toString()));
                getSupportActionBar().setTitle("Drink");
                break;
            case R.id.mnu_dessert:
                Log.d("Pory","Enum check  "+Food.Category.DESSERT.toString());
                OnNavClicked(new FoodsFragment(Food.Category.DESSERT.toString()));
                getSupportActionBar().setTitle("Dessert");
                break;
            case R.id.mnu_info:
                OnNavClicked(new LCinfoFragment());
                getSupportActionBar().setTitle("LC Information");
                break;
            case R.id.mnu_cast:
                Log.d("Pory","Phone at home "+phoneNumber);
                launchAccountActivity(CastsFragment.class,phoneNumber);
                break;
            case R.id.mnu_profile:
                launchAccountActivity(PersonalInfoFragment.class,phoneNumber);
                break;
            case R.id.mnu_history:
                launchAccountActivity(HistoryFragment.class,null);
                break;

        }
        drawerLayout.closeDrawers();
        return true;
    }

    private void launchAccountActivity(Class classActvity,@Nullable String phoneNumber) {

        Log.d("Pory", "launchAccount Check Phone:"+AccountKit.getCurrentPhoneNumberLogInModel());
        Intent intent = new Intent(this, classActvity);

        if(phoneNumber!=null){
            intent.putExtra("phone",phoneNumber);
        }

        startActivity(intent);
    }

}
