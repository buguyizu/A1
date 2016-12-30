package info.yinhua.a1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * http://hukai.me/android-training-course-in-chinese/basics/firstapp/starting-activity.html
 * android应用开发全程实录-你有多熟悉listview？
 * http://www.cnblogs.com/noTice520/archive/2011/12/05/2276379.html
 */
public class DisplayMessageActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

//1.接收消息
        Intent intent = getIntent();
        String message = intent.getStringExtra(Activity2.EXTRA_MESSAGE);

        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_display_message);
        layout.addView(textView);

//2.ListView
        lv = (ListView) findViewById(R.id.lv);
/*定义一个动态数组*/
        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();
/*在数组中存放数据*/
        for (int i = 0; i < 10; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemImage", R.drawable.download);//加入图片
            map.put("ItemTitle", "第" + i + "行");
            map.put("ItemText", "这是第" + i + "行");
            listItem.add(map);
        }

        SimpleAdapter mSimpleAdapter = new SimpleAdapter(this, listItem, R.layout.item,
                new String[]{"ItemImage", "ItemTitle", "ItemText"},
                new int[]{R.id.ItemImage, R.id.ItemTitle, R.id.ItemText}
        );
        lv.setAdapter(mSimpleAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DisplayMessageActivity.this, "你点击了第" + position + "行", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
