package technologiesactuelles.ig.hearc.ch.morpion;

import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MorpionActivity extends AppCompatActivity implements View.OnClickListener{

    // Tableau à deux dimentions [colonne][ligne]
    // 0 : case vide
    // 1 : X
    // 2 : O
    private int plateau[][] = new int[3][3];

    // 1 : X
    // 2 : O
    private int currentPlayer = 1;

    private TextView tvPlayer;

    private ArrayList<Button> buttons = new ArrayList();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Récupération des éléments
        tvPlayer = (TextView) findViewById(R.id.joueur);
        Button c1 = (Button) findViewById(R.id.case1);
        Button c2 = (Button) findViewById(R.id.case2);
        Button c3 = (Button) findViewById(R.id.case3);
        Button c4 = (Button) findViewById(R.id.case4);
        Button c5 = (Button) findViewById(R.id.case5);
        Button c6 = (Button) findViewById(R.id.case6);
        Button c7 = (Button) findViewById(R.id.case7);
        Button c8 = (Button) findViewById(R.id.case8);
        Button c9 = (Button) findViewById(R.id.case9);

        // Ajout des boutons dans la liste
        buttons.add(c1);
        buttons.add(c2);
        buttons.add(c3);
        buttons.add(c4);
        buttons.add(c5);
        buttons.add(c6);
        buttons.add(c7);
        buttons.add(c8);
        buttons.add(c9);

        for (Button bt : buttons){
            bt.setBackgroundDrawable(null);
            bt.setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View view) {

        // Vérifie que la case est vide
        if(view.getBackground() != null){
            return;
        }

        // Ajoute le clic sur le plateau (qui permet de déterminer le gagnant)
        switch(view.getId()){
            case R.id.case1:
                plateau[0][0] = currentPlayer;
                break;
            case R.id.case2:
                plateau[1][0] = currentPlayer;
                break;
            case R.id.case3:
                plateau[2][0] = currentPlayer;
                break;
            case R.id.case4:
                plateau[0][1] = currentPlayer;
                break;
            case R.id.case5:
                plateau[1][1] = currentPlayer;
                break;
            case R.id.case6:
                plateau[2][1] = currentPlayer;
                break;
            case R.id.case7:
                plateau[0][2] = currentPlayer;
                break;
            case R.id.case8:
                plateau[1][2] = currentPlayer;
                break;
            case R.id.case9:
                plateau[2][2] = currentPlayer;
                break;
            default:
                return;
        }

        // Prépare l'image
        // 1 : X
        // 2 : O
        Drawable imgPlayer;
        if (currentPlayer == 1){
            imgPlayer = ContextCompat.getDrawable(this, R.drawable.x);
        }
        else {
            imgPlayer = ContextCompat.getDrawable(this, R.drawable.o);
        }

        // Change le background de la case
        view.setBackgroundDrawable(imgPlayer);

        // Change de joueur
        if (currentPlayer == 1){
            currentPlayer = 2;
            tvPlayer.setText("O");
        }
        else {
            currentPlayer = 1;
            tvPlayer.setText("X");
        }

        int res = checkWinner();
        displayAlertDialog(res);
    }

    // Retourne un entier qui détermine le résultat de la partie
    // 0 : Partie en cours (non fini)
    // 1 : X
    // 2 : O
    // 3 : Egalité
    private int checkWinner(){
        for(int col = 0; col <= 2; col++){
            // Test la victoire par alignement sur une colonne
            if (plateau[col][0] == plateau[col][1] && plateau[col][0] == plateau[col][2] && plateau[col][0] != 0){
                return plateau[col][0];
            }
        }

        // Test la victoire par alignement sur une ligne
        for(int lig = 0; lig <= 2; lig++){
            if (plateau[0][lig] == plateau[1][lig] && plateau[0][lig] == plateau[2][lig] && plateau[0][lig] != 0){
                return plateau[0][lig];
            }
        }

        // Test la victoire en diagonale
        if (plateau[0][0] == plateau[1][1] && plateau[0][0] == plateau[2][2] && plateau[0][0] != 0){
            return plateau[0][0];
        }
        if (plateau[0][2] == plateau[1][1] && plateau[0][2] == plateau[2][0] && plateau[0][2] != 0){
            return plateau[0][2];
        }

        // Egalité
        boolean isPlateauPlein = true;
        for (int col = 0; col <= 2; col++) {
            for (int line = 0; line <= 2; line++){
                if (plateau[col][line] == 0) { // case
                    isPlateauPlein = false;
                    break;
                }
            }
            if (!isPlateauPlein) {
                break;
            }
        }
        if (isPlateauPlein) {
            return 3;
        }

        // PArtie en cours
        return 0;
    }

    // Affiche un message en cas de gagnant ou d'égalité
    // 0 : partie non fini
    // 1 : X
    // 2 : O
    // 3 : egalite
    private void displayAlertDialog(int res){
        if (res == 0) // partie non termine
            return;

        String strToDisplay = "";
        if (res == 1)
            strToDisplay = "Les X ont gagnées !";
        if (res == 2)
            strToDisplay = "Les O ont gagnés !";
        if (res == 3)
            strToDisplay = "Egalité !";

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Fin de la partie");
        alertDialog.setMessage(strToDisplay);

        alertDialog.setNeutralButton("Recommencer", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resetGame();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    // Remet le jeu à zéro
    private void resetGame(){

        // Vide le plateau
        for (int col = 0; col <= 2; col++) {
            for (int line = 0; line <= 2; line++) {
                plateau[col][line] = 0;
            }
        }

        // Efface les X et les O
        for (Button bt : buttons) {
            bt.setBackgroundDrawable(null);
        }
    }
}

