package com.example.chandu.catchit

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    var score=0
    var imageArray=ArrayList<ImageView>()
    var runnable:Runnable= Runnable { }
    var handler=Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageArray= arrayListOf(imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9)
        hideImages()
        object:CountDownTimer(25000,1000){
            override fun onFinish() {
                tv.text="Time is OFF"
                handler.removeCallbacks(runnable)
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
            }

            override fun onTick(millisUntilFinished: Long) {
               tv.text="Time : "+millisUntilFinished/1000
            }

        }.start()

    }
    fun hideImages(){
        runnable=object :Runnable{
            override fun run(){
                for (image in imageArray){
                    image.visibility=View.INVISIBLE
                }
                val random= Random()
                val index=random.nextInt(8-0)
                imageArray[index].visibility=View.VISIBLE
                handler.postDelayed(runnable,500)
            }
        }
        handler.post(runnable)
    }
    fun AddScore(view: View){
        score++
        tv2.text="score : "+score
    }
    fun restart(view:View){
        val intent= Intent(
                this,
                MainActivity::class.java
        )
        finish()
        this.startActivity(intent)
    }
}
