package com.example.manga.thread;

import android.widget.LinearLayout;

public class SlideShow implements Runnable{
    private int[] arrImg = new int[5];
    private LinearLayout layout;

    public SlideShow(int[] arrImg, LinearLayout layout) {
        this.arrImg = arrImg;
        this.layout = layout;
    }


    @Override
    public void run() {
        int i = 0;
        while (true){
            if(i > 4){
                i =0;
            }
            int finalI = i;
            layout.post(new Runnable() {
                @Override
                public void run() {
                    layout.setBackgroundResource(arrImg[finalI]);
                }
            });
            i++;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
