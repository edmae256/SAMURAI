package jp.yasay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Logo extends Activity {

	// TODO 切替時にアニメーション。フェードイン、アウト？
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo);

        Handler handler = new Handler();

        // TODO 次の画面に遷移するまでの時間を設定。
        handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(Logo.this, MainMenu.class));
				finish();
			}
		}, 1000);
    }
}