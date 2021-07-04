
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
        CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
        intentBuilder.setToolbarColor(this.getResources().getColor(R.color.colorPrimaryDark));
        intentBuilder.setUrlBarHidingEnabled(true);
        intentBuilder.setCloseButtonIcon(toBitmap(Objects.requireNonNull(getDrawable(R.drawable.ic_arrow_back))));
        intentBuilder.setDefaultShareMenuItemEnabled(true);
        intentBuilder.build().launchUrl(this, Uri.parse(videourl));
        overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
        intentBuilder.setShowTitle(true);
        Vibrator y = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        assert y != null;
        y.vibrate(300);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        Toast.makeText(getApplicationContext(),
                       "Creating your room..",
                       Toast.LENGTH_LONG)
                .show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finishAffinity(); // or finish();
            overridePendingTransition(R.anim.right_out, R.anim.left_in);
        }
    }

    @NonNull
    @Contract("_ -> new")
    public static Intent getOpenFacebookIntent(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.facebook.katana", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/106889727716197"));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.facebook.com/indiecamapp"));
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            navigationView.getMenu().getItem(0).setChecked(false);
        } else if (id == R.id.howto) {
            CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
            intentBuilder.setToolbarColor(this.getResources().getColor(R.color.colorPrimaryDark));
            intentBuilder.setUrlBarHidingEnabled(true);
            intentBuilder.setCloseButtonIcon(toBitmap(Objects.requireNonNull(getDrawable(R.drawable.ic_arrow_back))));
            intentBuilder.setDefaultShareMenuItemEnabled(true);
            intentBuilder.build().launchUrl(this, Uri.parse(howtourl));
            overridePendingTransition (R.anim.slide_in_right, R.anim.slide_out_left);
            intentBuilder.setShowTitle(true);
        } else if (id == R.id.lod) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://indiecam.page.link/linkoftheday"));
            startActivity(browserIntent);
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            Toast.makeText(getApplicationContext(),
                           "Take a look at this!",
                           Toast.LENGTH_LONG)
                           .show();
        } else if (id == R.id.share) {
            navigationView.getMenu().getItem(1).setChecked(false);
            Intent intentInvite = new Intent(Intent.ACTION_SEND);
            intentInvite.setType("text/plain");
            String body = "https://play.google.com/store/apps/details?id=com.indielite.cam";
            String subject = "Download IndieCam android to connect with me!";
            intentInvite.putExtra(Intent.EXTRA_SUBJECT, subject);
            intentInvite.putExtra(Intent.EXTRA_TEXT, body);
            startActivity(Intent.createChooser(intentInvite, "Share IndieCam app"));
            return true;
        } else if (id == R.id.rate) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.indielite.cam")));
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
        } else if (id == R.id.IndieChat) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=chat.melior.cam")));
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
        } else if (id == R.id.fullversion) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.indie.cam")));
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
        } else if (id == R.id.email) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "indiecam.app@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "IndieCam Lite - android app - user feedback");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "(Your text here..)");
            startActivity(Intent.createChooser(emailIntent, "Contact IndieCam"));
            overridePendingTransition (R.anim.bounce, R.anim.bounce);
        } else if (id == R.id.like) {
            startActivity(getOpenFacebookIntent(getApplicationContext()));
            overridePendingTransition (R.anim.slide_in_left, R.anim.slide_out_right);
            Toast.makeText(getApplicationContext(),
                    "Opening Facebook Page..",
                    Toast.LENGTH_LONG)
                    .show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private Bitmap toBitmap(@NonNull Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Rect oldBounds = new Rect(drawable.getBounds());

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(new Canvas(bitmap));

        drawable.setBounds(oldBounds);
        return bitmap;
    }
}