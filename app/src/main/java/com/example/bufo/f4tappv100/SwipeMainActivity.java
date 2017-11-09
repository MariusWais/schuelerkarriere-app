package com.example.bufo.f4tappv100;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.listeners.ItemRemovedListener;
import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;

import org.w3c.dom.Text;

import static android.widget.Toast.LENGTH_SHORT;
import static com.example.bufo.f4tappv100.UserLogin.log_bool;

public class SwipeMainActivity extends AppCompatActivity {

    private static final String TAG = "SwipeActivity";
    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    public static WebView mWebView;
    public static WebView urlWebView;
    private RelativeLayout btnLayout;
    private Button hideWvBtn;
    private ImageButton vitaButton;
    static TextView testtext;
    private AdInfo mAdInfo;
    ToolBar toolBar;
    private View view;
    String vita_mail;
    static String shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(),log_bool.toString(),Toast.LENGTH_LONG).show();
        mSwipeView = (SwipePlaceHolderView) findViewById(R.id.swipeView);
        mWebView = (WebView) findViewById(R.id.webView);
        urlWebView = (WebView) findViewById(R.id.urlWebView);
        btnLayout = (RelativeLayout) findViewById(R.id.btnLayout);
        mContext = getApplicationContext();
        mWebView.setVisibility(View.GONE);
        view = findViewById(R.id.icons);
        vitaButton = (ImageButton)findViewById(R.id.vitaButton);
        hideWvBtn = (Button) findViewById(R.id.hideWebView);
        hideWvBtn.setVisibility(View.GONE);


        android.content.SharedPreferences sharedPreferences = getSharedPreferences("vita_data",MODE_PRIVATE);
        shared = sharedPreferences.getString("vita_mail",vita_mail);


       // Toast.makeText(mContext,shared,Toast.LENGTH_LONG).show();
        //Log.d("vita_mail",shared);

        mSwipeView.addItemRemoveListener(new ItemRemovedListener() {

            @Override
            public void onItemRemoved(int count) {
                Log.d(TAG, "onItemRemoved: " + count);
                if(count == 0){
                Intent intent = new Intent(SwipeMainActivity.this,StartSplashScreen.class);
                startActivity(intent);}
            }
        });

        mSwipeView.getBuilder()
                .setSwipeDecor(new SwipeDecor()
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_view_layout)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_view_layout));




        for (AdInfo adInfo : SwipeUtils.loadProfiles(this.getApplicationContext())) {
            mSwipeView.addView(new AdCard(mContext, adInfo, mSwipeView));
        }


       findViewById(R.id.rejectBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(false);
            }
        });

        findViewById(R.id.acceptBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });
        findViewById(R.id.descBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.setVisibility(View.VISIBLE);
                btnLayout.setVisibility(View.GONE);
                mSwipeView.setVisibility(View.GONE);
                hideWvBtn.setVisibility(View.VISIBLE);
            }
        });
        findViewById(R.id.hideWebView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.setVisibility(View.GONE);
                btnLayout.setVisibility(View.VISIBLE);
                mSwipeView.setVisibility(View.VISIBLE);
                hideWvBtn.setVisibility(View.GONE);
            }
        });}

    public void getToVita(View view){
        Intent intent = new Intent(SwipeMainActivity.this,EditVita.class);
        startActivity(intent);
    }
    public void getToJobs(View view){
        Intent intent = new Intent(SwipeMainActivity.this,SwipeMainActivity.class);
        startActivity(intent);
    }
    public void getToLogin(View view){
        Intent intent = new Intent(SwipeMainActivity.this,UserLogin.class);
        startActivity(intent);
    }

}
