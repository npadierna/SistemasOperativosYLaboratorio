package co.edu.udea.os.ahorcado;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameMainActivity extends Activity {

    @Override()
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
    }

    @Override()
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_main, menu);
        
        return (true);
    }   
}