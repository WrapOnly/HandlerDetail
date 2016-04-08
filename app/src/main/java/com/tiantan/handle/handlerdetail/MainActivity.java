package com.tiantan.handle.handlerdetail;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Handler 生产者
         * Looper 消费者
         * messageQueue；仓库
         */
        new Thread(new Runnable() {
            /**
             * handler 使用步骤：
             * 1.Looper.prepare();先调用，并且只能执行一次。
             * 2.创建handler,使用handler.
             * 3.Looper.loop();
             */
            @Override
            public void run() {
                /**
                 * 给threadLocal设置了一个looper对象，
                 * 在looper的构造方法当中，创建了一个messageQueue对象；
                 */
                Looper.prepare();

                Handler handler = new Handler(){
                    /**
                     * handler的构造方法当中，干了两件事情，
                     * 1.获得looper
                     * 2.获得messageQueue;
                     */
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                    }
                };

                /**
                 * 最终将message发送到messageQueue里面，在messageQueue里面，所有的message对象
                 * 都是按时间为顺序从小到大排列。
                 */
                handler.sendEmptyMessage(0);

                /**
                 * 顺次取出messageQueue中的message，并执行message.target.dispatchMessage();
                 * 其中message.target 就是发送这个消息的handler.
                 */
                Looper.loop();
            }
        }).start();
    }
}
