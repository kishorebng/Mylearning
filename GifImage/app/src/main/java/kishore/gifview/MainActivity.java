package kishore.gifview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


import java.io.FileNotFoundException;
import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {

    ImageView glideUrl;
    ImageView glidelocal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        glideUrl = (ImageView) findViewById(R.id.glideapi_url);
        glidelocal = (ImageView) findViewById(R.id.glideapi_local);
        Glide.with(this).load("https://media.giphy.com/media/1TZwL1iBAMbvO/200.gif").into(glideUrl);
        Glide.with(this).load("file:///android_asset/13.gif").into(glidelocal);
    }
}
