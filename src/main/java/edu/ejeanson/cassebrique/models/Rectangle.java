package edu.ejeanson.cassebrique.models;

import java.awt.*;

public class Rectangle extends Carre
{
    protected int hauteur = 10;

    public Rectangle(int positionX, int positionY, Color couleur, int largeur, int hauteur) {
        super(positionX, positionY, couleur, largeur);
        this.hauteur = hauteur;
    }

    public void dessiner(Graphics2D dessin)
    {
        dessin.setColor(couleur);
        dessin.fillRect(positionX,positionY,largeur,hauteur);
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
