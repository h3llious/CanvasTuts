package com.blacksun.canvastuts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.view.FrameMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button mButton;
    HandlerThread handlerThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final FrameMetrics[] metrics = {null};
        Window window = getWindow();
        handlerThread = new HandlerThread("Thread1");
        handlerThread.start();
        final Handler mHandler = new Handler(handlerThread.getLooper());
        window.addOnFrameMetricsAvailableListener(new Window.OnFrameMetricsAvailableListener() {
            @Override
            public void onFrameMetricsAvailable(Window window, final FrameMetrics frameMetrics, int i) {
//                metrics[0] = new FrameMetrics(frameMetrics);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Metrics", "===============================================");
                        // Send this data to custom analytcis
                        Log.d("Metrics", "ANIMATION_DURATION: " + frameMetrics.getMetric((FrameMetrics.ANIMATION_DURATION))/Math.pow(10,6));
                        Log.d("Metrics", "COMMAND_ISSUE_DURATION: " + frameMetrics.getMetric(FrameMetrics.COMMAND_ISSUE_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "DRAW_DURATION: " + frameMetrics.getMetric(FrameMetrics.DRAW_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "FIRST_DRAW_FRAME: " + frameMetrics.getMetric(FrameMetrics.FIRST_DRAW_FRAME)/Math.pow(10,6));
                        Log.d("Metrics", "INPUT_HANDLING_DURATION: " + frameMetrics.getMetric(FrameMetrics.INPUT_HANDLING_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "LAYOUT_MEASURE_DURATION: " + frameMetrics.getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "SWAP_BUFFERS_DURATION: " + frameMetrics.getMetric(FrameMetrics.SWAP_BUFFERS_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "SYNC_DURATION: " + frameMetrics.getMetric(FrameMetrics.SYNC_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "TOTAL_DURATION: " + frameMetrics.getMetric(FrameMetrics.TOTAL_DURATION)/Math.pow(10,6));
                        Log.d("Metrics", "UNKNOWN_DELAY_DURATION: " + frameMetrics.getMetric(FrameMetrics.UNKNOWN_DELAY_DURATION)/Math.pow(10,6));
//                );
                        Log.d("Metrics", "===============================================");
                    }
                });
            }
        },mHandler);

        /*mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Send this data to custom analytcis
                Log.d("Metrics", "ANIMATION_DURATION: " + metrics[0].getMetric((FrameMetrics.ANIMATION_DURATION))/Math.pow(10,6));
                Log.d("Metrics", "COMMAND_ISSUE_DURATION: " + metrics[0].getMetric(FrameMetrics.COMMAND_ISSUE_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "DRAW_DURATION: " + metrics[0].getMetric(FrameMetrics.DRAW_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "FIRST_DRAW_FRAME: " + metrics[0].getMetric(FrameMetrics.FIRST_DRAW_FRAME)/Math.pow(10,6));
                Log.d("Metrics", "INPUT_HANDLING_DURATION: " + metrics[0].getMetric(FrameMetrics.INPUT_HANDLING_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "LAYOUT_MEASURE_DURATION: " + metrics[0].getMetric(FrameMetrics.LAYOUT_MEASURE_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "SWAP_BUFFERS_DURATION: " + metrics[0].getMetric(FrameMetrics.SWAP_BUFFERS_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "SYNC_DURATION: " + metrics[0].getMetric(FrameMetrics.SYNC_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "TOTAL_DURATION: " + metrics[0].getMetric(FrameMetrics.TOTAL_DURATION)/Math.pow(10,6));
                Log.d("Metrics", "UNKNOWN_DELAY_DURATION: " + metrics[0].getMetric(FrameMetrics.UNKNOWN_DELAY_DURATION)/Math.pow(10,6));
//                );
            }
        }, 1000);*/

        mButton = findViewById(R.id.btn_switch_to_5);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FiveViewActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(handlerThread != null && handlerThread.isAlive()) {
            handlerThread.quit();
        }
        super.onDestroy();
    }
}
