package com.tryme.mes.tryme.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.tryme.mes.tryme.App
import com.tryme.mes.tryme.R
import kotlinx.android.synthetic.main.activity_mate_options.*


class MateOptionsActivity : AppCompatActivity() {
    private val TryMeApp: App = App.getInstance()
    private val homeSocket: Socket = TryMeApp.socket
    private lateinit var selectedCategory : String
    private lateinit var selectedSubCategory : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mate_options)
        initCategorySpinner()
        btn_start_matching_mate.setOnClickListener {
            initPairing()
            homeSocket.on("setPartner",setPartner)


            val intent = Intent(this@MateOptionsActivity,ChatActivity::class.java)

            startActivity(intent)
        }


    }
    private val setPartner = Emitter.Listener {args->

        this.runOnUiThread({
            TryMeApp.partnerId = args[0].toString()

            Toast.makeText(this.applicationContext,
                    "paired", Toast.LENGTH_LONG).show()


        })
    }
    private  fun initPairing(){
        if(TryMeApp.partnerId !== ""){
            TryMeApp.partnerId =""
        }
        homeSocket.emit("pair","vicki")



    }

    private fun initCategorySpinner(){
        category_spinner.setItems("Select Category","Technology","Love","Cars","Business")
        category_spinner.setOnItemSelectedListener({
            view, position, id, item -> if(position>0){selectedCategory= item.toString()} })

    }
}
