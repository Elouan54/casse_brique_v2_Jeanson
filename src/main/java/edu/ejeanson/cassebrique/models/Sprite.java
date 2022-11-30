package edu.ejeanson.cassebrique.models;

import java.awt.*;

public abstract class Sprite
{
    protected int positionX;
    protected int positionY;
    protected Color couleur;

    public Sprite() {

    }


    public abstract void dessiner(Graphics2D dessin);

    public Sprite(int positionX, int positionY, Color couleur) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.couleur = couleur;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
}
