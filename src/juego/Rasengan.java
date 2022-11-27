package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Rasengan {
    private double x;
    private double y;
    private double alto;
    private double ancho;
    private int direccion;
    private Image img;

    public Rasengan(double x, double y, int d){
        this.x=x;
        this.y=y;
        this.alto=20;
        this.ancho=30;
        this.direccion=d;
        img=Herramientas.cargarImagen("rasengan.png");
    }

    public void dibujarse(Entorno e){
        e.dibujarImagen(img, this.x, this.y, 0, 0.03);
    }

    public void mover(){
        if(direccion==1){
            this.y-=5;
        }
        if(direccion==2){
            this.y+=5;
        }
        if(direccion==3){ 
            this.x-=5;
        }
        if(direccion==4){
            this.x+=5;
        }
    }

    boolean colisionConNinja(Ninja n){
        return(y-alto/2<n.getY()+ n.getAlto()/2 &&
        y+alto/2>n.getY()-n.getAlto()/2 && 
        x-ancho/2<n.getX()+n.getAncho()/2 && 
        x+ancho/2>n.getX()-n.getAncho()/2);
    }

    boolean colisionExtremos(Entorno e){
        return(this.y+this.alto/2<0 || this.y-this.alto/2>e.alto() || this.x+this.ancho/2<0 || this.x-this.ancho/2>e.ancho());
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
