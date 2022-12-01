package edu.ejeanson.cassebrique;

import edu.ejeanson.cassebrique.models.Balle;
import edu.ejeanson.cassebrique.models.Barre;
import edu.ejeanson.cassebrique.models.Brique;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class CasseBrique extends Canvas {
    public final static int LARGEUR = 500;
    public final static int HAUTEUR = 600;


    int rangeeHauteur = 7;
    int rangeeLongueur = 10;
    public int briquePositionY = 100;
    int m=0;
    public Brique[][] tableauBrique = new Brique[rangeeLongueur][rangeeHauteur];

    public Balle[] tableauBalle;

    private Barre barre = new Barre(215,570,Color.black,70, 10);


    public CasseBrique() throws InterruptedException {

        JFrame fenetre = new JFrame("Casse brique");
        //On récupère le panneau de la fenetre principale
        JPanel panneau = (JPanel) fenetre.getContentPane();
        //On définie la hauteur / largeur écran
        panneau.setPreferredSize(new Dimension(LARGEUR, HAUTEUR));
        setBounds(0, 0, LARGEUR, HAUTEUR);
        //On ajoute cette classe (qui hérite de Canvas) comme composant du panneau principal
        panneau.add(this);

        fenetre.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 90 || e.getKeyCode() == 65){
                    if (e.getKeyCode() == 90)
                    {
                        barre.deplacementDroite();
                    }
                    if (e.getKeyCode() == 65)
                    {
                        barre.deplacementGauche();
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        fenetre.pack();
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setVisible(true);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.requestFocus();

        //On indique que le rafraîchissement écran doit être fait manuellement.
        createBufferStrategy(2);
        setIgnoreRepaint(true);
        setFocusable(false);
        demarrer();
    }

    public void demarrer() throws InterruptedException {

        tableauBalle = new Balle[1];

        for (int i=0; i<tableauBalle.length; i++)
        {
            //int taille = (int)(Math.random()*40 + 10);
            int taille = 10;
            //int positionDepartX = (int)(Math.random()*(LARGEUR -taille));
            //int positionDepartY = (int)(Math.random()*(LARGEUR -taille));
            //int vitesseBalleX = (int)(Math.random()*20 -10);
            //int vitesseBalleY = (int)(Math.random()*20 - 10);
            Color couleur = new  Color((float)Math.random(), (float)Math.random(), (float)Math.random());

            tableauBalle[i]= new Balle(245,550,-3, -5, taille, couleur);
        }

        for (int k=0; k<tableauBrique[2].length; k++)
        {
            int briquePositionX = 100;
            for (int j=0; j<tableauBrique.length; j++)
            {
                m++;
                Color briqueCouleur = new  Color((float)Math.random(), (float)Math.random(), (float)Math.random());
                tableauBrique[j][k] = new Brique(briquePositionX,briquePositionY,briqueCouleur,30,30);
                briquePositionX = briquePositionX + 30;
            }
            briquePositionY = briquePositionY + 30;
        }

        int nombreBrique = m;

        while(true) {

            Graphics2D dessin = (Graphics2D) getBufferStrategy().getDrawGraphics();
            dessin.setColor(Color.white);
            dessin.fillRect(0,0, LARGEUR, HAUTEUR);

            barre.dessiner(dessin);

            for (int k=0; k<tableauBrique[2].length; k++) {
                for (int j = 0; j < tableauBrique.length; j++) {
                    tableauBrique[j][k].dessiner(dessin);
                }
            }

            for (Balle balle : tableauBalle) {
                balle.mouvement();
                balle.collision();
                balle.dessiner(dessin);

                if ((balle.getPositionY() > (barre.getPositionY() - barre.getHauteur()))
                        && (balle.getPositionX() <= (barre.getPositionX() + barre.getLargeur())
                        && (balle.getPositionX() >= barre.getPositionX()))) {
                    balle.setPositionY(balle.getPositionY() - 2);
                    balle.rebondY();
                }

                for (int k = 0; k < tableauBrique[2].length; k++) {
                    for (int j = 0; j < tableauBrique.length; j++) {

                        if (( tableauBrique[j][k].getCouleur() != Color.white ) &&
                                (balle.getPositionY() + 10 > tableauBrique[j][k].getPositionY()
                                && balle.getPositionY() < tableauBrique[j][k].getPositionY() + 30)
                                && ((balle.getPositionX() + 10 > tableauBrique[j][k].getPositionX())
                                && (balle.getPositionX() < tableauBrique[j][k].getPositionX() + 30)))
                        {
                            if ((balle.getVitesseBalleX() > 0) && (balle.getVitesseBalleY() > 0)) //nord-ouest
                            {
                                if (balle.getPositionY() + 10 < briquePositionY) {
                                    balle.setPositionY(balle.getPositionY() - 5);
                                    //System.out.println("1,1 haut"); //haut
                                    balle.rebondY();
                                    tableauBrique[j][k].disappear();
                                }
                                else
                                {
                                    balle.setPositionX(balle.getPositionX() - 5);
                                    //System.out.println("1,2 gauche");//gauche
                                    balle.rebondX();
                                    tableauBrique[j][k].disappear();
                                }
                            }
                            else if ((balle.getVitesseBalleX() < 0) && (balle.getVitesseBalleY() < 0)) //sud-est
                            {
                                if (balle.getPositionY() > 210 )
                                {
                                    balle.setPositionX(balle.getPositionX() + 5);
                                    //System.out.println("2,1 droite"); //droite
                                    balle.rebondX();
                                    tableauBrique[j][k].disappear();
                                }
                                else
                                {
                                    balle.setPositionY(balle.getPositionY() + 5);
                                    //System.out.println("2,2 bas"); //bas
                                    balle.rebondY();
                                    tableauBrique[j][k].disappear();
                                }
                            }
                            else if ((balle.getVitesseBalleX() < 0) && (balle.getVitesseBalleY() > 0)) //nord-est
                            {
                                if (balle.getPositionY() + 10 < briquePositionY)
                                {
                                    //System.out.println("3,1 haut");//haut
                                    balle.setPositionY(balle.getPositionY() - 5);
                                    balle.rebondY();
                                    tableauBrique[j][k].disappear();
                                }
                                else
                                {
                                    //System.out.println("3,2 droite"); //droite
                                    balle.setPositionX(balle.getPositionX() + 5);
                                    balle.rebondX();
                                    tableauBrique[j][k].disappear();
                                }
                            }
                            else if ((balle.getVitesseBalleX() > 0) && (balle.getVitesseBalleY() < 0)) //sud-ouest
                            {
                                if (balle.getPositionY() < 213 )
                                {
                                    //System.out.println("4,1 gauche"); //gauche
                                    balle.setPositionX(balle.getPositionX() - 5);
                                    balle.rebondX();
                                    tableauBrique[j][k].disappear();
                                }
                                else
                                {
                                    //System.out.println("4,2 bas"); //bas
                                    balle.setPositionY(balle.getPositionY() + 5);
                                    balle.rebondY();
                                    tableauBrique[j][k].disappear();
                                }
                            }
                            else {
                                System.out.println("?");
                            }
                            nombreBrique --;

                            Random random = new Random();
                            int value = random.nextInt(5) + 1;
                            if (value == 1){
                                bonus();
                            }

                        }
                    }
                }

                //System.out.println(nombreBrique);
                if (nombreBrique == 0)
                {
                    balle.disappear();
                    barre.disappear();
                    System.out.println("win");
                    JOptionPane.showMessageDialog(this,"WINNER", "Résultat", JOptionPane.WARNING_MESSAGE);
                    System.exit(1);
                }

                if (balle.getPositionY()>590){
                    balle.disappear();
                    barre.disappear();
                    System.out.println("loose");
                    JOptionPane.showMessageDialog(this,"LOOSER", "Résultat", JOptionPane.WARNING_MESSAGE);
                    System.exit(1);
                }
            }

            dessin.dispose();
            getBufferStrategy().show();
            Thread.sleep(1000 / 60);
        }
    }

    private void bonus() {
        Random random = new Random();
        int value = random.nextInt(3) + 1;

        switch (value){
            case 1: //mettre rangée
                briquePositionY = briquePositionY -60;
                rangeeHauteur= rangeeHauteur-1;
                for (int j=0; j<rangeeLongueur; j++)
                {
                    m++;
                    Color briqueCouleur = new  Color((float)Math.random(), (float)Math.random(), (float)Math.random());
                    tableauBrique[j][rangeeHauteur].setCouleur(briqueCouleur);
                }
                System.out.println("ajout rangée " + rangeeHauteur);
            case 2: //agrandissement barre
                barre.setLargeur(barre.getLargeur() + 10);
                System.out.println("barre agrandit");
            case 3: //rétrécissement barre
                barre.setLargeur(barre.getLargeur() - 10);
                System.out.println("barre rétréci");
        }



    }

    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }
}