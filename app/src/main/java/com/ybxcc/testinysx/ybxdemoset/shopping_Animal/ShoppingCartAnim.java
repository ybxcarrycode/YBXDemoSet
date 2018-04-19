package com.ybxcc.testinysx.ybxdemoset.shopping_Animal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 项目名称：BezierCurveAnimater-master
 * 类描述：
 * 创建人：ybx
 * 创建时间：2018/1/2 17:54
 * 备注：
 */

public class ShoppingCartAnim {

    private static Activity activity;
    private ImageView iconImg;
    private int[] start_location = new int[2];
    private int[] end_location = new int[2];
    private static Handler mThreadHandler; //数据操作非UI线程回调
    public ViewGroup group; //动画层
    private static Thread thread; //数据操作非UI线程
    private float[] mCurrentPosition = new float[2];

    /**
     *@description : 实例化子线程
     *@author : ybx
     *@date : 2018/1/2
     *@params :
     */
    static {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 非 UI 线程
                // 这执行动画执行后的数据处理等（如商品数量）


                //发送消息给 UI线程
                mThreadHandler.sendEmptyMessage(0);
            }
        });


        mThreadHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        //线程操作完之后及时关闭
                        thread.interrupt();


                        //UI线程操作

                        //动画结束时的处理 （例如  购物车抖动等）
                        Toast.makeText(activity, "+ + 1", Toast.LENGTH_SHORT).show();

                        break;
                    default:
                        break;
                }
            }
        };
    }

    /**
     * @description : 构造方法
     * @author : ybx
     * @date : 2018/1/2
     * @params :
     */
    public ShoppingCartAnim(Activity activity) {
        this.activity = activity;
//        iconImg = new ImageView(activity);  // 动画的图片 ImageView
//        iconImg.setImageResource(R.drawable.coin);

        group = (ViewGroup) activity.getWindow().getDecorView(); //创建一个动画层
//        group.addView(iconImg);
    }


    /**
     * @description : 将ImageView添加到动画层 并设置到初始位置
     * @author : ybx
     * @date : 2018/1/2
     * @params :
     */
    private View addViewFromAnimLayout(View view, int[] location) {
        int x = location[0];
        int y = location[1];
        FrameLayout.LayoutParams flp = new FrameLayout.LayoutParams(100, 100);
        flp.leftMargin = x;
        flp.topMargin = y;
        view.setLayoutParams(flp);
        return view;
    }


    /**
     * @description : 动画播放的方法
     * @author : ybx
     * @date : 2018/1/2
     * @params :
     */
    public void startAnim(ImageView startView, View endView, final RelativeLayout parentV) {

        final ImageView run_view = new ImageView(activity);
        run_view.setImageDrawable(startView.getDrawable());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(100, 100);
        parentV.addView(run_view, params);

        // 一、
        //获取开始位置
        startView.getLocationInWindow(start_location);
        //获取结束位置
        endView.getLocationInWindow(end_location);
        //获取父控件的位置
        int[] parentLocation = new int[2];
        parentV.getLocationInWindow(parentLocation);
        
        
        
        // 二、
        //将动画图片和起始坐标绘制成新的view，用于播放动画
        //将image图片添加到动画层
        /**这里为什么不直接传一个图片而是传一个imageview呢？
         * 因为我这样做的目的是clone动画播放控件，为什么要clone呢？
         * 因为如果用户连续点击添加购物车的话，如果只用一个imageview去播放动画的话，这个动画就会成还没播放完就回到原点重新播放。
         * 而如果clone一个imageview去播放，那么这个动画还没播放完，用户再点击添加购物车以后我们还是clone一个新的imageview去播放。
         * 这样动画就会出现好几个点而不是一个点还没播放完又缩回去。
         * 说的通俗点，就是依靠这个方法，把参照对象和起始位置穿进去，得到一个clone的对象来播放动画
         */
//        final View run_view = addViewFromAnimLayout(iconImg, start_location);


        // 三、计算图片开始点和结束点
        //开始点计算
        float startX = start_location[0] - parentLocation[0] + startView.getWidth() / 2;
        float startY = start_location[1] - parentLocation[1] + startView.getHeight() / 2;

        //结束点计算
        float endX = end_location[0] - parentLocation[0] + endView.getWidth() / 5;
        float endY = end_location[1] - parentLocation[1];

        // 四、计算中间动画的插值坐标（贝塞尔曲线）
        //绘制贝塞尔曲线
        Path path = new Path();
        //起始点
        path.moveTo(startX, startY);
        //使用二次萨贝尔曲线：注意第一个起始坐标越大，贝塞尔曲线的横向距离就会越大，一般按照下面的式子取即可
        path.quadTo((startX + endX) / 2, startY+150, endX, endY);
        //mPathMeasure用来计算贝塞尔曲线的曲线长度和贝塞尔曲线中间插值的坐标，
        // 如果是true，path会形成一个闭环
        final PathMeasure pathMeasure = new PathMeasure(path, false);


        Log.e("location0", "parent:" + parentLocation[0] + "/" + parentLocation[1] + " start:" + start_location[0] + "/" + start_location[1] + " end:" + end_location[0] + "/" + end_location[1]);


        // 五、属性动画实现（从0到贝塞尔曲线的长度之间进行插值计算，获取中间过程的距离值）
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.setDuration(2000);
        //匀速线性插值器
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                pathMeasure.getPosTan(value, mCurrentPosition, null);
                run_view.setTranslationX(mCurrentPosition[0]);
                run_view.setTranslationY(mCurrentPosition[1]);
            }
        });

        // 六、开始执行动画
        valueAnimator.start();

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                parentV.removeView(run_view);

                Toast.makeText(activity, "+ + 1", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });

    }

}
