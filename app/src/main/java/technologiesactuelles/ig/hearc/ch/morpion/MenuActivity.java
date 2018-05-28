package technologiesactuelles.ig.hearc.ch.morpion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {
    private Button bt_jouer = null;
    private Button bt_joueurs = null;
    private Button bt_classement = null;
    private Button bt_options = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bt_jouer = (Button) findViewById(R.id.bt_jouer);

        bt_jouer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                System.out.println("Clic sur le bouton bt_jouer");
            }
        });

        bt_joueurs = (Button) findViewById(R.id.bt_joueurs);

        bt_joueurs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                System.out.println("Clic sur le bouton bt_joueurs");
            }
        });

        bt_classement = (Button) findViewById(R.id.bt_classement);

        bt_classement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                Intent intent = new Intent(MenuActivity.this, ClassementActivity.class);
                startActivity(intent);
            }
        });

        bt_options = (Button) findViewById(R.id.bt_options);

        bt_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vue) {
                System.out.println("Clic sur le bouton bt_options");
            }
        });

    }
}