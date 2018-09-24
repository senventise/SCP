package xyz.senventise.scp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class SplashActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.text_view);
        Thread t = new Thread(){
            @Override
            public void run() {
                try {
                    textView.setText("username:******\npassword:**********");
                    //TimeUnit.MILLISECONDS.sleep(300);
                    textView.append("\n登录中...");
                    //TimeUnit.MILLISECONDS.sleep(300);
                    textView.append("\n正在连接验证服务器...");
                    //TimeUnit.MILLISECONDS.sleep(600);
                    textView.append("\n连接建立成功，正在查询数据库...");
                    //TimeUnit.MILLISECONDS.sleep(400);
                    textView.append("\n验证成功!\n欢迎回来，******");
                    //TimeUnit.MILLISECONDS.sleep(200);
                    textView.append("\n访问记录已上传.");
                    TimeUnit.MILLISECONDS.sleep(2000);
                    SplashActivity.this.finish();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }
}
