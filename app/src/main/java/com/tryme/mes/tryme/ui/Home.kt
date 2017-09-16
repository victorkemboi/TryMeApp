package com.tryme.mes.tryme.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.github.nkzawa.socketio.client.Socket
import com.tryme.mes.tryme.App
import com.tryme.mes.tryme.R
import android.widget.Toast
import com.github.nkzawa.emitter.Emitter

import kotlinx.android.synthetic.main.activity_home.*

class Home : AppCompatActivity() {
    private val TryMeApp: App = App.getInstance()
    private val homeSocket: Socket = TryMeApp.socket
    private var isConnected = false
    private  var  partnerId:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeSocket.on(Socket.EVENT_CONNECT,onConnect);
        homeSocket.on(Socket.EVENT_DISCONNECT,onDisconnect);
        homeSocket.connect();

        btn_start_try.setOnClickListener{
            val intent = Intent(this@Home, MateOptionsActivity::class.java)
            startActivity(intent)
        }


    }

    private val onConnect = Emitter.Listener {
        this.runOnUiThread({
            if (!isConnected) {
                Toast.makeText(this.applicationContext,
                        "connected", Toast.LENGTH_LONG).show()
                isConnected = true
            }
        })
    }
    private val onDisconnect = Emitter.Listener {
        this.runOnUiThread({
            if (isConnected) {
                Toast.makeText(this.applicationContext,
                        "disconnected", Toast.LENGTH_LONG).show()
                isConnected = false
            }
        })
    }
}
