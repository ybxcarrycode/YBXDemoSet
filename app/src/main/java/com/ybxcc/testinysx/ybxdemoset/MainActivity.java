package com.ybxcc.testinysx.ybxdemoset;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ProviderInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ybxcc.testinysx.ybxdemoset.aop_aspectj.AspectActivity;
import com.ybxcc.testinysx.ybxdemoset.fingerprint_recognition.FingerprintActivity;
import com.ybxcc.testinysx.ybxdemoset.palette_cutImg.CutImgTestActivity;
import com.ybxcc.testinysx.ybxdemoset.property_animation.PropertyAnimationActivity;
import com.ybxcc.testinysx.ybxdemoset.shopping_Animal.ShopCartActivity;
import com.ybxcc.testinysx.ybxdemoset.smart_refresh_layout.SmartRefreshLayoutActivity;
import com.ybxcc.testinysx.ybxdemoset.test_textview.TestTextViewActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = MainActivity.this;

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_palette_cutimg) {
            // Handle the camera action
            startActivity(new Intent(context, CutImgTestActivity.class));
        } else if (id == R.id.nav_shopping_cart) {
            startActivity(new Intent(context, ShopCartActivity.class));
        } else if (id == R.id.nav_textview_test) {
            startActivity(new Intent(context, TestTextViewActivity.class));
        } else if (id == R.id.nav_aspectj_test) {
            startActivity(new Intent(context, AspectActivity.class));
        } else if (id == R.id.nav_fingerprint_test) {
            startActivity(new Intent(context, FingerprintActivity.class));
        } else if (id == R.id.nav_property_animation) {
            startActivity(new Intent(context, PropertyAnimationActivity.class));
        }else if (id == R.id.smart_refresh_layout) {
            startActivity(new Intent(context, SmartRefreshLayoutActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
