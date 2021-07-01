
package com.indielite.cam;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import org.jetbrains.annotations.Contract;
import java.util.Objects;
import java.util.UUID;

@RequiresApi(api = Build.VERSION_CODES.O)
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView navigationView;

    String text = UUID.randomUUID().toString().replace("-", "");

    String websiteurl = "https://indie.cam/";
    String howtourl = "https://indie.cam/how-to-indiecam-lite/";
    String chaturl = ("https://orbit.chat/#/channel/" + "IndieCam-" + (text));
    String videourl = ("https://go.indie.cam/" + (text));
    String audiourl = ("https://go.indie.cam/" + (text) + "#config.startWithVideoMuted=true");
    String confurl = ("https://beta.meet.jit.si/moderated/" + (text) + "#config.disableDeepLinking=true");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);