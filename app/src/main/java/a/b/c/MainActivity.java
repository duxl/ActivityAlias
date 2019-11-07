package a.b.c;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button0);
        button.setOnClickListener(this);
        sp = getSharedPreferences("data", Context.MODE_PRIVATE);
        button.setText("点击缓存数据自增（"+sp.getInt("a", 0)+"）");

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button0) {
            int value = sp.getInt("a", 0);
            value++;
            sp.edit().putInt("a", value).commit();

            button.setText("点击缓存数据自增（"+value+"）");

        } else if(v.getId() == R.id.button1) {
            setNomal();
        } else if(v.getId() == R.id.button2) {
            setDouble11();
        }
    }

    /**
     * 设置成普通
     */
    private void setNomal() {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this, MainActivity.class.getName()), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        packageManager.setComponentEnabledSetting(new ComponentName(this, OtherActivity.class.getName()), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        Toast.makeText(this, "已切换成普通", Toast.LENGTH_SHORT).show();
    }

    /**
     * 设置成双11
     */
    private void setDouble11() {
        PackageManager packageManager = getPackageManager();
        packageManager.setComponentEnabledSetting(new ComponentName(this, MainActivity.class.getName()), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
        packageManager.setComponentEnabledSetting(new ComponentName(this, OtherActivity.class.getName()), PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        Toast.makeText(this, "已切换成双11", Toast.LENGTH_SHORT).show();
    }
}
