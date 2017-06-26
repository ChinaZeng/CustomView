package com.zzw.customview

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.Touch
import android.view.View


import kotlinx.android.synthetic.main.activity_main.*


/**
 * Created by zzw on 2017/6/15.
 * Version:
 * Des:
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qq.startActivity(QQSportStepViewActivity::class.java)
        load58.startActivity(JumpLoadViewActivity::class.java)
        suoyin.startActivity(AlphabeticalIndexViewActivity::class.java)
        ziticolor.startActivity(ColorTrackTextViewActivity::class.java)
        colortv_vp.startActivity(ViewPagerActivity::class.java)
        rating.startActivity(RatingBarActivity::class.java)
        tagLayout.startActivity(TagLayoutActivity::class.java)
        touch.startActivity(TouchActivity::class.java)
        skidding.startActivity(SkiddingActivity::class.java)
        radar.startActivity(RadarViewActivity::class.java)
        qqSkidding.startActivity(QQSliddingActivity::class.java)
    }


    fun View.startActivity(clazz: Class<*>) {
        setOnClickListener {
            val intent = Intent(context, clazz)
            startActivity(intent)
        }
    }


}
