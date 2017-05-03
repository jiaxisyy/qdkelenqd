package activity;

import android.os.Bundle;

import cn.edu.zafu.corepage.base.BaseActivity;
import cn.edu.zafu.corepage.core.CoreAnim;

/**
 * Created by shuang.xiang on 2016/11/25.
 */

public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openPage("f1", null, CoreAnim.none);

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
