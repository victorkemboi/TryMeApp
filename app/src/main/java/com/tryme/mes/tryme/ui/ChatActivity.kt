package com.tryme.mes.tryme.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.tryme.mes.tryme.App
import com.tryme.mes.tryme.R
import kotlinx.android.synthetic.main.chat_activity.*



class ChatActivity : AppCompatActivity() {
    private val TryMeApp: App = App.getInstance()
    private val homeSocket: Socket = TryMeApp.socket
    private var isConnected = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_activity)
        homeSocket.on("chat message",onChatMessage)
        btn_send.setOnClickListener {
            homeSocket.emit("chat message", ed_message.text.toString().trim())
            ed_message.clearFocus()
            ed_message.setText("")

        }

    }

    private val onConnect = Emitter.Listener {
        this.runOnUiThread({
            if (!isConnected) {

                homeSocket.emit("chat message", "vicki")
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
    private val onChatMessage = Emitter.Listener {args->

        this.runOnUiThread({
                val data = args[0]

                Toast.makeText(this.applicationContext,
                        data.toString(), Toast.LENGTH_LONG).show()


        })
    }


}
