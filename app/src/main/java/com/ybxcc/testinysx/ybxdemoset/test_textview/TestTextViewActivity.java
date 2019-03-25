package com.ybxcc.testinysx.ybxdemoset.test_textview;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ybxcc.testinysx.ybxdemoset.R;
import com.ybxcc.testinysx.ybxdemoset.service.FloatingChatService;
import com.ybxcc.testinysx.ybxdemoset.view.EnclosureShapeView;
import com.ybxcc.testinysx.ybxdemoset.view.ShapeTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestTextViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_textview_drawable);

        final Context context = this;

        TextView textView = (TextView) findViewById(R.id.textView);

        textView.setGravity(Gravity.CENTER);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String url1 = "123456789@";
//
//                String url4 = "123456789@";
//
//                String url2 = "123456789";
//
//                String url3 = "";
//
//                url1 = url1.replace("@", "www");
//
//                url4 = url4.replace("@", "");
//
//                url2 = url2.replace("@", "wwww");
//
//                url3 = url3.replace("@", "wwww");
//
//                Log.e("url", url1 + "//" + url4 + "//" + url2 + "//" + url3);

//                sedad();

                if (FloatingChatService.isStarted) {
                    return;
                }

                Intent intentSer = new Intent(context, FloatingChatService.class);

                Log.e("////", Build.VERSION.SDK_INT + "//" + Settings.canDrawOverlays(context));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!Settings.canDrawOverlays(context)) {
                        Toast.makeText(context, "当前无权限，请授权", Toast.LENGTH_SHORT);
                        startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 1);
                    } else {
                        Toast.makeText(context, "开始讲盘", Toast.LENGTH_SHORT);
                        startService(intentSer);
                    }
                }

            }
        });

//        EnclosureShapeView tvEn = findViewById(R.id.tvEn);
//        tvEn.setText("卡还款静安寺");
        ShapeTextView tv_s = findViewById(R.id.tv_s);
        tv_s.setTextStr(null,"卡还款静安寺");

    }


    private void sedad() {
//        int[] a = {123, 45, 25, 67, 23, 14, 13, 41, 24, 8, 5, 56, 64};

//        int a1;
//        for (int i = 0; i < a.length; i++) {
//            for (int j = i; j < a.length; j++) {
//                if (a[i] > a[j]) {
//                    a1 = a[j];
//                    a[j] = a[i];
//                    a[i] = a1;
//                }
//            }
//        }


        /*** 定义一个 类 Count 来存储相加为16的两个数（如果需要的是下标值  a1、a2 也可以存储下标值），
         *
         */
        class Count {
            int a1;
            int a2;
        }

        int[] a = {1, 3, 4, 9, 8, 8, 13, 15, 18, 20, 1, 1, 20, -4, -2, 7};

        int k = a[0];    //这里取的是第一个数，也可以取任意一个，取第一个好处是不需要考虑数组的长度

        List<Count> list = new ArrayList<>();

        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int co = a[i] + a[j];
                if (i == 0) {
                    if (co == 16) {   // 考虑到数组可能存在两个8 的情况  ， 这种是  8+8=16 这种情况符合要求
//                    if (co == 16 && a[i] != a[j]) {   // 这种是  8+8=16 这种情况  不符合要求
                        Count count = new Count();
                        count.a1 = a[i];
                        count.a2 = a[j];
                        list.add(count);
                        a[j] = k;
                    }
                } else {
                    if (a[i] != k && a[j] != k && co == 16) {  //  8+8=16 这种情况符合要求
//                    if (a[i] != k && a[j] != k && co == 16 && a[i] != a[j]) {  //8+8=16 这种情况  不符合要求
                        Count count = new Count();
                        count.a1 = a[i];
                        count.a2 = a[j];
                        a[i] = k;
                        a[j] = k;
                        list.add(count);
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            Log.e("count", list.get(i).a1 + "//" + list.get(i).a2);
        }

    }


}
