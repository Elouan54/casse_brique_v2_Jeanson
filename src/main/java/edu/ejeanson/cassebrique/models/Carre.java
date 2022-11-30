package edu.ejeanson.cassebrique.models;

import java.awt.*;

public class Carre extends Sprite
{
    protected int largeur = 70;

    public Carre(int positionX, int positionY, Color couleur, int largeur) {
        super(positionX, positionY, couleur);
        this.largeur = largeur;
    }

    public void dessiner(Graphics2D dessin)
    {
        dessin.setColor(couleur);
        dessin.fillRect(positionX,positionY,largeur,largeur);
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }


}
