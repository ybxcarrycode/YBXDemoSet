package com.ybxcc.testinysx.ybxdemoset.palette_cutImg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ybxcc.testinysx.ybxdemoset.R;

import java.io.InputStream;
import java.util.List;

/**
 * 项目名称：BezierCurveAnimater-master
 * 类描述：
 * 创建人：ybx
 * 创建时间：2018/1/8 10:59
 * 备注：
 */

public class CutImgTestActivity extends Activity {
    private ImageView img_big;
    private ImageView img_show;

    private EditText edit_left;
    private EditText edit_top;
    private EditText edit_right;
    private EditText edit_bottom;

    private Button btn_ok;

    private TextView tv_1;
    private TextView tv_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_img_test_layout);

        img_big = (ImageView) findViewById(R.id.img_big);
        img_show = (ImageView) findViewById(R.id.img_show);

        edit_left = (EditText) findViewById(R.id.edit_left);
        edit_top = (EditText) findViewById(R.id.edit_top);
        edit_right = (EditText) findViewById(R.id.edit_right);
        edit_bottom = (EditText) findViewById(R.id.edit_bottom);

        tv_1 = (TextView) findViewById(R.id.tv_1);
        tv_2 = (TextView) findViewById(R.id.tv_2);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cutBitmap();
//                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.btn_zhiwen_changan);
//                getPaletteData(bitmap);
            }
        });


    }

    private void cutBitmap() {
        int left = Integer.parseInt(edit_left.getText().toString().trim());
        int top = Integer.parseInt(edit_top.getText().toString().trim());
        int right = Integer.parseInt(edit_right.getText().toString().trim());
        int bottom = Integer.parseInt(edit_bottom.getText().toString().trim());

        Rect rect = new Rect();
        rect.set(left, top, right, bottom);

        BitmapRegionDecoder decoder = null;

        try {
            InputStream is = getResources().openRawResource(R.drawable.tu_woxiu_zhanwei);
            decoder = BitmapRegionDecoder.newInstance(is, true);
        } catch (Exception e) {
        }
        if (decoder != null) {
            Bitmap bitmap = decoder.decodeRegion(rect, null);
            img_show.setImageBitmap(bitmap);
            getPaletteData(bitmap);
        }
    }

    private void getPaletteData(Bitmap bitmap) {
        Palette.Builder builder = Palette.from(bitmap);
        builder.maximumColorCount(10);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                List<Palette.Swatch> list = palette.getSwatches();
                int listCount = list.size();
                Toast.makeText(CutImgTestActivity.this, "//" + listCount, Toast.LENGTH_SHORT).show();

                int thePos = 0;
                int thePopulation = 0;

                for (int i = 0; i < listCount; i++) {
                    int popluation = list.get(i).getPopulation();
                    if (popluation > thePopulation) {
                        thePopulation = popluation;
                        thePos = i;
                    }
                }
                int txt_rgb_1 = list.get(thePos).getBodyTextColor();
                int txt_rgb_2 = list.get(thePos).getTitleTextColor();
                
                Log.e("palette", "population = " + thePopulation + "// rgb = " + list.get(thePos).getRgb()
                        + " // txt_rgb_1 = " + list.get(thePos).getBodyTextColor()
                        + " // txt_rgb_2 = " + list.get(thePos).getTitleTextColor());
                
                tv_1.setTextColor(txt_rgb_1);
                tv_2.setTextColor(txt_rgb_2);
                
            }
        });

    }

}
