package info.yinhua.a1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity2 extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "info.yinhua.a1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);

        /*保存的数据传递给下一个activity*/
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String message = sharedPreferences.getString(getString(R.string.saved_message), "");

        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void saveMessage(View view) {
        EditText editText = (EditText) findViewById(R.id.edit_message);
        String message = editText.getText().toString().trim();

        String text;
        if (message == null || message.length() == 0) {
            text = "Empty message!";
        } else {
            /*保存拼接的字符串*/
            SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
            String savedMessage = sharedPreferences.getString(getString(R.string.saved_message), "");
            message = savedMessage + message;

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.saved_message), message);
            editor.commit();

            text = "Saved";
            editText.setText("");
        }
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 打开浏览器
     * http://hukai.me/android-training-course-in-chinese/basics/intents/sending.html
     * @param view
     */
    public void shareMessage(View view) {

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String message = sharedPreferences.getString(getString(R.string.saved_message), "");

        Uri webpage = Uri.parse("http://sogou.com");
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        startActivity(intent);
    }
}
