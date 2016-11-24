package activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hitek.serial.R;

/**
 * Created by shuang.xiang on 2016/11/24.
 */

public class A extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.a);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
