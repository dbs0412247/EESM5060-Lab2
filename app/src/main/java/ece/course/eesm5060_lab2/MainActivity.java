package ece.course.eesm5060_lab2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private DisplayView mDisplayView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisplayView = new DisplayView(this);
        //setContentView(R.layout.activity_main);
        setContentView(mDisplayView);
    }
}
