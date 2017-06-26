package com.zzw.customview.acitvity

import com.zzw.customview.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by zzw on 2017/6/15.
 * Version:
 * Des:
 */

class MainActivity : android.support.v7.app.AppCompatActivity() {

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        qq.startActivity(QQSportStepViewActivity::class.java)
        load58.startActivity(JumpLoadViewActivity::class.java)
        suoyin.startActivity(AlphabeticalIndexViewActivity::class.java)
        ziticolor.startActivity(ColorTrackTextViewActivity::class.java)
        colortv_vp.startActivity(ViewPagerActivity::class.java)
        rating.startActivity(RatingBarActivity::class.java)
        tagLayout.startActivity(TagLayoutActivity::class.java)
        skidding.startActivity(SkiddingActivity::class.java)
        qqSkidding.startActivity(QQSliddingActivity::class.java)
        erticalDragListView.startActivity(VerticalDragListViewActivity::class.java)

        radar.startActivity(RadarViewActivity::class.java)
        touch.startActivity(TouchActivity::class.java)
    }


    fun android.view.View.startActivity(clazz: Class<*>) {
        setOnClickListener {
            val intent = android.content.Intent(context, clazz)
            startActivity(intent)
        }
    }


}
