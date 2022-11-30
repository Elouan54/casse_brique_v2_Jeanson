package edu.ejeanson.cassebrique.models;

import java.awt.*;

public class Brique extends Rectangle
{

    public Brique(int positionX, int positionY, Color couleur, int largeur, int hauteur) {
        super(positionX, positionY, couleur, largeur, hauteur);
    }

    public void disappear() {
        couleur = Color.white;
    }






}
