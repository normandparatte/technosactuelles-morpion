package technologiesactuelles.ig.hearc.ch.morpion;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button bt_jouer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt_jouer = (Button) findViewById(R.id.bt_jouer);

        bt_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                System.out.println("Est-ce que ca joue ?");
            }
        });
    }
}