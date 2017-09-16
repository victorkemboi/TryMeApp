package com.tryme.mes.tryme;

import android.util.Log;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import java.net.URISyntaxException;

public class App extends SugarApp {

    private static App sInstance;
    private Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://192.168.51.2:3000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
    // 192.168.51.2
    //http://192.168.1.10:3000

    public String partnerId="";





    public static App getInstance() {
        return sInstance;
    }


    public Socket getSocket() {
        return mSocket;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        SugarContext.init(this);




    }
    @Override
    public  void  onTerminate(){
        super.onTerminate();
        SugarContext.terminate();
    }







}
