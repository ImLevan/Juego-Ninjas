package juego;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Manzana {
    private double x;
    private double y;
    private double alto;
    private double ancho;
    private Image img;
    private Image img2;
    private Image img3;
    private Image img4;
    private Casa[] casas;
    private int casaObjetivo;

    public Manzana(double x, double y){
        this.x=x;
        this.y=y;
        alto=112;
        ancho=172;
        casas=new Casa[3];
        casas[0]=new Casa(this.x-42, this.y+38);
        casas[1]=new Casa(this.x+10, this.y+38);
        casas[2]=new Casa(this.x+63, this.y+38);
        casaObjetivo=0;
        img=Herramientas.cargarImagen("manzana.png");
        img2=Herramientas.cargarImagen("manzana2.png");
        img3=Herramientas.cargarImagen("manzana3.png");
        img4=Herramientas.cargarImagen("manzana4.png");
    }

    public void dibujarse(Entorno e){
        if(casaObjetivo==0){
            e.dibujarImagen(img, this.x, this.y, 0, 1);
        }
        if(casaObjetivo==1){
            e.dibujarImagen(img2, this.x, this.y, 0, 1);
        }
        if(casaObjetivo==2){
            e.dibujarImagen(img3, this.x, this.y, 0, 1);
        }
        if(casaObjetivo==3){
            e.dibujarImagen(img4, this.x, this.y, 0, 1);
        }

    }

    public static Manzana[] crearManzanas(){
        Manzana[] manzanas=new Manzana[20];
        manzanas[0]=new Manzana(82, 158);
        manzanas[1]=new Manzana(82, 312);
        manzanas[2]=new Manzana(82, 463);
        manzanas[3]=new Manzana(290, 158);
        manzanas[4]=new Manzana(290, 312);
        manzanas[5]=new Manzana(290, 463);
        manzanas[6]=new Manzana(500, 158);
        manzanas[7]=new Manzana(500, 312);
        manzanas[8]=new Manzana(500, 463);
        manzanas[9]=new Manzana(710, 158);
        manzanas[10]=new Manzana(710, 312); 
        manzanas[11]=new Manzana(710, 463);
        manzanas[12]=new Manzana(82, 4);
        manzanas[13]=new Manzana(290, 4);
        manzanas[14]=new Manzana(500, 4);
        manzanas[15]=new Manzana(710, 4);
        manzanas[16]=new Manzana(82, 616);
        manzanas[17]=new Manzana(290, 616);
        manzanas[18]=new Manzana(500, 616);
        manzanas[19]=new Manzana(710, 616);
        return manzanas;
    }

    public static void mostrarManzanas(Manzana[] m, Entorno e){
        for(int i=0; i<m.length; i++){
            m[i].dibujarse(e);
        }
    }

    public static int[] objetivoCasa(Manzana[] m){
        Random r=new Random();
        int k=0;
        k=r.nextInt(16);              //manzana
        int c=r.nextInt(3);           //casa
        int[] ar={k,c};               //arreglo de manzana y casa
        m[k].casaObjetivo=c+1;
        return ar;
        
    }

    public Casa[] getCasas() {
        return casas;
    }

    public int getCasaObjetivo() {
        return casaObjetivo;
    }

    public void setCasaObjetivo(int casaObjetivo) {
        this.casaObjetivo = casaObjetivo;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAlto() {
        return alto;
    }

    public double getAncho() {
        return ancho;
    }
}
