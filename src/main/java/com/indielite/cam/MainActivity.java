
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

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        overridePendingTransition(R.anim.right_in, R.anim.left_out);

        ImageView imageButton = findViewById(R.id.button2);
        imageButton.setOnClickListener(v -> {

            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            intentBuilder.setUrlBarHidingEnabled(true);
            intentBuilder.setCloseButtonIcon(toBitmap(Objects.requireNonNull(getDrawable(R.drawable.ic_arrow_back))));
            intentBuilder.setDefaultShareMenuItemEnabled(true);
            intentBuilder.setStartAnimations(this, R.anim.push_down_in, R.anim.push_down_out);
            intentBuilder.setExitAnimations(this, R.anim.push_up_in, R.anim.push_up_out);
            intentBuilder.build().launchUrl(this, Uri.parse(websiteurl));
            intentBuilder.setShowTitle(true);
            Toast.makeText(getApplicationContext(),
                           "Opening IndieCam web app..",
                            Toast.LENGTH_LONG)
                            .show();
        });

        imageButton = findViewById(R.id.button3);
        imageButton.setOnClickListener(v -> {

            Intent intent = getPackageManager().getLaunchIntentForPackage("chat.melior.cam");
            if (intent != null) {
                // We found the activity now start the activity
                intent.setComponent(new ComponentName ("chat.melior.cam", "chat.melior.cam.MainActivity"));
                startActivity(intent);
                overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
                Toast.makeText(getApplicationContext(),
                               "teleporting you to [melior]",
                               Toast.LENGTH_LONG)
                        .show();
            } else {
                CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
                intentBuilder.setToolbarColor(this.getResources().getColor(R.color.colorPrimaryDark));
                intentBuilder.setUrlBarHidingEnabled(true);
                intentBuilder.setCloseButtonIcon(toBitmap(Objects.requireNonNull(getDrawable(R.drawable.ic_arrow_back))));
                intentBuilder.setDefaultShareMenuItemEnabled(true);
                intentBuilder.build().launchUrl(this, Uri.parse(chaturl));
                overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
                intentBuilder.setShowTitle(true);
                Toast.makeText(getApplicationContext(),
                               "Opening chat room.. Get [melior] app to enjoy the full chat experience",
                               Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    public void onButtoonClick(View v) {

        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        intentBuilder.setUrlBarHidingEnabled(true);
        intentBuilder.setCloseButtonIcon(toBitmap(Objects.requireNonNull(getDrawable(R.drawable.ic_arrow_back))));
        intentBuilder.setDefaultShareMenuItemEnabled(true);
        intentBuilder.build().launchUrl(this, Uri.parse(confurl));
        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setShowTitle(true);
        Vibrator y = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        assert y != null;
        y.vibrate(300);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        Toast.makeText(getApplicationContext(),
                "Creating your multiparty room..",
                Toast.LENGTH_LONG)
                .show();
    }

    public void onButtonClick(View v) {
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        intentBuilder.setUrlBarHidingEnabled(true);
        intentBuilder.setCloseButtonIcon(toBitmap(Objects.requireNonNull(getDrawable(R.drawable.ic_arrow_back))));
        intentBuilder.setDefaultShareMenuItemEnabled(true);
        intentBuilder.build().launchUrl(this, Uri.parse(audiourl));
        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setShowTitle(true);
        Vibrator y = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        assert y != null;
        y.vibrate(100);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        Toast.makeText(getApplicationContext(),
                       "Creating your room, joining audio-only..",
                       Toast.LENGTH_LONG)
                .show();
    }

    public void onButoonClick(View v) {