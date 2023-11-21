
package com.dam.pmdm.actividad7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var btn : Button
    private lateinit var coliseo : ImageView
    private lateinit var xina : ImageView
    private lateinit var petra : ImageView
    val nimg = 3
    val contMax = 5
    var cont = 0
    @SuppressLint("MissingInflatedId") //FIXME: ??
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        coliseo = findViewById(R.id.img_coliseo)
        xina = findViewById(R.id.img_xina)
        petra = findViewById(R.id.img_petra)
        btn.setOnClickListener(object : View.OnClickListener{
            @SuppressLint("SetTextI18n") //FIXME: ??
            override fun onClick(p0: View?) {
                cont++
                //TODO: esto cuenta como texto hardcoreado
                btn.text = "You have pressed $cont time"
                if (cont>=contMax){

                    coliseo.layoutParams = xina.layoutParams
                    petra.layoutParams = xina.layoutParams

                    xina.alpha = 0.5f
                    coliseo.alpha = 0.5f
                    petra.alpha = 0.5f

                    val index = (cont+1) % nimg
                    when (index){
                        0 -> {
                            coliseo.visibility = View.VISIBLE
                            xina.visibility = View.INVISIBLE
                            petra.visibility = View.INVISIBLE
                        }
                        1 -> {
                            coliseo.visibility = View.INVISIBLE
                            xina.visibility = View.VISIBLE
                            petra.visibility = View.INVISIBLE
                        }
                        2 -> {
                            coliseo.visibility = View.INVISIBLE
                            xina.visibility = View.INVISIBLE
                            petra.visibility = View.VISIBLE
                        }
                    }
                }
            }
        })

    }
}