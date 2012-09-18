package idv.honda.pgap;

import android.os.Bundle;
import org.apache.cordova.*;

public class NcueWifi extends DroidGap
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.loadUrl("file:///android_asset/www/app.html");
    }
}

