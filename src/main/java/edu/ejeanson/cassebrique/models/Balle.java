package edu.ejeanson.cassebrique.models;

import edu.ejeanson.cassebrique.CasseBrique;

import java.awt.*;

public class Balle extends Rond
{
    //protected int positionX ;
    //protected int positionY ;
    protected int vitesseBalleX;
    protected int vitesseBalleY;

    protected int largeur;

    protected Color couleur;

    /*public Balle(int positionX1, int positionY1, Color couleur1, int largeur) {
        this.positionX = positionX1;
        this.positionY = positionY1;
        this.couleur = couleur1;
        this.largeur= largeur;
    }*/


    public Balle(int positionX, int positionY, int vitesseBalleX, int vitesseBalleY, int diametre, Color couleur) {
        super(positionX, positionY, couleur, diametre);
        this.vitesseBalleX = vitesseBalleX == 0 ? 1 : vitesseBalleX;
        this.vitesseBalleY = vitesseBalleY == 0 ? 1 : vitesseBalleY;
        this.couleur = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
    }

    public void disappear() {
        couleur = Color.white;
    }

    public void rebondY() {
        vitesseBalleY *= -1;
    }

    public void rebondX() {
        vitesseBalleX *= -1;
    }

    public void mouvement()
    {
        positionX += vitesseBalleX;
        positionY += vitesseBalleY;
    }

    public void collision()
    {
        if (positionX >= CasseBrique.LARGEUR -largeur || positionX <= 0)
        {
            vitesseBalleX *= -1;
        }
        if (positionY >= CasseBrique.HAUTEUR -largeur || positionY <= 0)
        {
            vitesseBalleY *= -1;
        }
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

    public int getVitesseBalleX() {
        return vitesseBalleX;
    }

    public void setVitesseBalleX(int vitesseBalleX) {
        this.vitesseBalleX = vitesseBalleX;
    }

    public int getVitesseBalleY() {
        return vitesseBalleY;
    }

    public void setVitesseBalleY(int vitesseBalleY) {
        this.vitesseBalleY = vitesseBalleY;
    }


}
