package fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hitek.serial.R;

import utils.Constants;
import utils.ReadAndWrite;
import view.DrawChart;

/**
 * Created by shuang.xiang on 2016/8/11.
 */
public class ShowFragment extends Fragment {

    private static final String TAG = "ShowFragment";
    private View view;
    private boolean flag = true;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared;
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            mDc.invalidate();

            return false;
        }
    });
    private DrawChart mDc;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.show_fragment, container, false);
        //XXX初始化view的各控件
        init();

        return view;
    }

    private void init() {
        mDc = (DrawChart) view.findViewById(R.id.dc_show);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    String[] data = ReadAndWrite.ReadJni(Constants.Define.OP_REAL_D, new int[]{16, 10});
                    if (data != null && data.length > 0) {
                        String sub = data[0].substring(0, data[0].indexOf("."));
                        Log.d(TAG,String.valueOf(Integer.parseInt(sub)));
                        mDc.setY(Integer.parseInt(sub));
                        float i = Float.parseFloat(data[1]) / 3;

                        float v = i * 150;

                        String s = String.valueOf(v);

                        String substring = s.substring(0, s.indexOf("."));
                        Log.d(TAG,String.valueOf(Integer.parseInt(substring)));
                        mDc.setOtherY(Integer.parseInt(substring));
                        Message obtain = Message.obtain();
                        mHandler.sendMessage(obtain);

                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }




}
