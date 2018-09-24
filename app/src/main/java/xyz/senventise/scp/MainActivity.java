package xyz.senventise.scp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //webview 配置
        webView = findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            // 拦截 url
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                if(url.contains("file:///scp-")){
                    // SCP-xxx
                    show(url.replace("file:///",""));
                }else if(url.contains("http://") || url.contains("https://")){
                    Toast.makeText(getApplicationContext(),"即将访问外部网页，切勿泄露基金会相关信息。",Toast.LENGTH_SHORT).show();
                    view.loadUrl(url);
                }
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url){
                super.onPageFinished(view,url);
                //显示标题
                String title = view.getTitle();
                if(!TextUtils.isEmpty(title)){
                    setTitle(title.replace(".html","").toUpperCase());
                }
            }
        });
        String startURL = getIntent().getStringExtra("ITEM");
        show(startURL);
    }

    public void print(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }

    public void show(String link){
        int index = link.contains("CN")?Integer.parseInt(link.substring(6)):Integer.parseInt(link.substring(4));
        //System.out.println(index+"");
        webView.loadUrl("file:///android_asset/pages/"+link.toLowerCase()+".html");
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            // 返回前一个页面
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
