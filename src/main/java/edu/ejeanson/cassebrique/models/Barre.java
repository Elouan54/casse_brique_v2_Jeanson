package edu.ejeanson.cassebrique.models;

import edu.ejeanson.cassebrique.CasseBrique;

import java.awt.*;

public class Barre extends Rectangle {

    public Barre(int positionX, int positionY, Color couleur, int largeur, int hauteur) {
        super(positionX, positionY, couleur, largeur, hauteur);
    }

    public void disappear() {
        couleur = Color.white;
    }

    public void deplacementDroite() {
        if (positionX < (CasseBrique.LARGEUR) - largeur) {
            positionX = positionX + 10;
        }

    }

    public void deplacementGauche() {
        if (positionX > 0) {
            positionX = positionX - 10;
        }
    }


}