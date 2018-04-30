package com.example.yshya.test;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class MainActivity extends AppCompatActivity{
    TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_accept = (Button) findViewById(R.id.btn_accept);

        TextView textView1=findViewById(R.id.Text);

    }
    //private Handler uiHandler=new Handler();
    /*private Handler uiHandler=new Handler(){
        public void handlerMessage(Message msg){
            switch(msg.what){
                case 1:
                    textView1.setText("連線成功!");
                    break;
            }
        }
    };*/
    public void onClick(View v) {
        new Thread() {
            @Override
            public void run() {
                try {
                    acceptServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void acceptServer() throws IOException {
        //1.创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("192.168.5.2", 11122);

        //2.获取输出流，向服务器端发送信息
      //textView1.setText("connect");
        if(socket.isConnected()){
          //  textView1.setText("連接成功!!!");
        }
      /*  Message msg=new Message();
        msg.what=1;
        uiHandler.sendMessage(msg);*/
        OutputStream os = socket.getOutputStream();//字节输出流
        PrintWriter pw = new PrintWriter(os);//将输出流包装为打印流
        //获取客户端的IP地址
        InetAddress address = InetAddress.getLocalHost();
        String ip = address.getHostAddress();
        pw.write( "Client端的ip是"+ip );
        pw.flush();
        socket.shutdownOutput();//关闭输出流
        socket.close();
    }

}