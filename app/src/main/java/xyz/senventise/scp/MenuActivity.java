package xyz.senventise.scp;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MenuActivity extends AppCompatActivity {

    Dialog dialog;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
        // 显示 splash activity
        Thread t = new Thread(){
            @Override
            public void run(){
                try {
                    Intent i = new Intent(MenuActivity.this, SplashActivity.class);
                    startActivity(i);
                    TimeUnit.MILLISECONDS.sleep(3000);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        t.start();
    }

    public void onTestClick(View view){
        Intent intent = new Intent(getApplicationContext(),ListActivity.class);
        startActivity(intent);
    }

    public void onGoClick(View view){
        String text = editText.getText().toString().toUpperCase();
        if(text.isEmpty()){
            Toast.makeText(getApplicationContext(),"不可为空",Toast.LENGTH_SHORT).show();
        }else if(!text.contains("SCP-")){
            Toast.makeText(getApplicationContext(),"非法编号",Toast.LENGTH_SHORT).show();
        }else{
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            i.putExtra("ITEM",text.toUpperCase());
            startActivity(i);
        }
        dialog.cancel();
    }

    public void onJumpClick(View view){
        dialog.show();
    }

    public void onScpClick(View view){
        Intent i = new Intent(MenuActivity.this,ListActivity.class);
        i.putExtra("IS_CN",false);
        i.putExtra("MAX",4999);
        i.putExtra("MIN",1);
        startActivity(i);
    }

    public void onScpCnClick(View view){
        Intent i = new Intent(MenuActivity.this,ListActivity.class);
        i.putExtra("IS_CN",true);
        i.putExtra("MAX",1000);
        i.putExtra("MIN",1);
        startActivity(i);
    }

    public void onAboutClick(View view){

    }

    public void onSettingsClick(View view){
        Intent i = new Intent(MenuActivity.this,SettingsActivity.class);
        startActivity(i);
    }

    public void init(){
        dialog = new Dialog(MenuActivity.this);
        dialog.setContentView(R.layout.dialog);
        editText = dialog.findViewById(R.id.edit_text);
    }

}
