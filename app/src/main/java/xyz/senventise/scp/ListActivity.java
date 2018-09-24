package xyz.senventise.scp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {


    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("SCP基金会档案-索引");
        init();
        int min = getIntent().getIntExtra("MIN",-1);
        int max = getIntent().getIntExtra("MAX",-1);
        boolean cn = getIntent().getBooleanExtra("IS_CN",false);
        create(min,max,cn);
    }

    public void init(){
        listview = findViewById(R.id.listview);
    }

    //生成列表，范围从min到max
    public void create(final int min,final int max,final boolean cn){
        String[] data = new String[max-min+1];
        for(int i=min;i<max;i++){
            if(!cn) {
                data[i - 1] = "SCP-" + Tools.idConvert(i);
            }else {
                data[i - 1] = "SCP-CN-" + Tools.idConvert(i);
            }
        }
        ArrayAdapter<String> aa = new ArrayAdapter<>(ListActivity.this,android.R.layout.simple_list_item_1,data);
        listview.setAdapter(aa);
        // 监听点击事件
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getApplicationContext(),id+1+"",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ListActivity.this,MainActivity.class);
                if (!cn) {
                    i.putExtra("ITEM", "SCP-" + Tools.idConvert((int) (id + 1)));
                }else {
                    i.putExtra("ITEM", "SCP-CN-" + Tools.idConvert((int) (id + 1)));
                }
                startActivity(i);
            }
        });
    }
}
