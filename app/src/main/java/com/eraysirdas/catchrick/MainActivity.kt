package com.eraysirdas.catchrick

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isInvisible
import com.eraysirdas.catchrick.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var imageId : Array<Int>
    private var randomImageId : Int =0
    var runnable : Runnable = Runnable { }
    private var handler : Handler = Handler(Looper.getMainLooper())
    var number = 15
    private var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        imageId = arrayOf(R.id.Iv1,R.id.Iv2,R.id.Iv3,R.id.Iv4,R.id.Iv5,R.id.Iv6,R.id.Iv7,R.id.Iv8,R.id.Iv9,R.id.Iv10
            ,R.id.Iv11,R.id.Iv12,R.id.Iv13,R.id.Iv14,R.id.Iv15,R.id.Iv16)




       runnable= object : Runnable{
           override fun run() {

               if(number==0){
                   handler.removeCallbacks(runnable)
                   binding.timeTv.text="Time: "
                   findViewById<ImageView>(randomImageId).visibility = View.INVISIBLE
                   startDialog()
                   return
               }
               number--
               randomImageId = imageId.random()
               for(i in imageId){
                   findViewById<ImageView>(i).visibility = View.INVISIBLE
               }
               findViewById<ImageView>(randomImageId).visibility = View.VISIBLE

               handler.postDelayed(runnable,1000)
               binding.timeTv.text="Time: "+number
           }


       }
        handler.post(runnable)
    }

    private fun startDialog() {

        val dialog =  AlertDialog.Builder(this)
        dialog.setTitle("Game Over")
        dialog.setMessage("Restart the game?")

        dialog.setPositiveButton("Evet"){p0,p1->
            recreate()
        }

        dialog.setNegativeButton("Hayır",object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                Toast.makeText(this@MainActivity,"Game Over!",Toast.LENGTH_LONG).show()
            }

        })
        dialog.show()
    }


    fun addScore(view:View){

        score += 5
        binding.scoreTv.text="Score: ${score}"
    }


}