package com.example.bufo.f4tappv100;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Bufo on 02.06.2017.
 */

@Layout(R.layout.card_view_layout)
public class AdCard extends Application {

    @View(R.id.profileImageView)
    private ImageView profileImageView;

    @View(R.id.typeTxt)
    private TextView typeTxt;

    @View(R.id.descTxt)
    private TextView descTxt;

    @View(R.id.comTxt)
    private TextView comTxt;

    @View(R.id.placeTxt)
    private TextView placeTxt;


    private AdInfo mAdInfo;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private SharedPreferences sharedPreferences;
    public String vita_mail;
    public String shared;


    @Override
    public void onCreate() {
        super.onCreate();
        SwipeMainActivity.mWebView.loadUrl(mAdInfo.getUrl());

        android.content.SharedPreferences sharedPreferences = getSharedPreferences("vita_data",MODE_PRIVATE);
        shared = sharedPreferences.getString("vita_mail",vita_mail);


        Toast.makeText(mContext,shared,Toast.LENGTH_LONG).show();
        Log.d("vita_mail",shared);


    }


    public AdCard(Context context, AdInfo adinfo, SwipePlaceHolderView swipeView) {
        mContext = context;
        mAdInfo = adinfo;
        mSwipeView = swipeView;
    }



    @Resolve
    private void onResolved(){

        Picasso.with(mContext).load(mAdInfo.getImageUrl()).into(profileImageView);
        typeTxt.setText(mAdInfo.getTitle());
        placeTxt.setText(mAdInfo.getPlace());
        comTxt.setText(mAdInfo.getCom());
        descTxt.setText(mAdInfo.getType());

    }

    @SwipeIn
    private void onSwipedIn() {

        Log.d("EVENT", mAdInfo.getTitle().toString());
       // Toast.makeText(mContext, mAdInfo.getId().toString(), Toast.LENGTH_LONG).show();
        String url = "https://www.schuelerkarriere.de/app/mail.php?name=Max%20Mustermann";
        SwipeMainActivity.urlWebView.loadUrl(url);

        HttpClient client = new DefaultHttpClient();

        try {
            client.execute(new HttpGet(url));
        } catch (IOException e) {

        }

    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
    }

    @SwipeOut
    private void onSwipeOut(){
        SwipeMainActivity.mWebView.loadUrl(mAdInfo.getUrl());
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}
