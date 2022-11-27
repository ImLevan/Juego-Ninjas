package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Herramientas;
import entorno.Entorno;

public class Ninja {
    private double x;
    private double y;
    private Image img1;
    private double velocidad;
    private double ancho;
    private double alto;
    private int direccion; //Direccion donde se van a mover los ninjas, 1=derecha 2=abajo 3=arriba

    public Ninja(double x, double y, double v, int d){
        this.x=x;
        this.y=y;
        this.velocidad=v;
        this.ancho=15;
        this.alto=30;
        this.direccion=d;
        img1=Herramientas.cargarImagen("ninja.png");
    }

    public void dibujarse(Entorno e){
        e.dibujarImagen(img1, this.x, this.y, 0, 0.03);
    }

    public void mover() {
        if(direccion==1){
            this.x+=velocidad;
        }else if(direccion==2){
            this.y += velocidad;
        }else{
            this.y -= velocidad;
        }
	}

    public void reaparecer(Entorno e){
        if(y>=e.alto()-alto/2){
            this.y=alto/2;
            return;
        }
        if(y<=alto/2){
            this.y=e.alto()-alto/2;
            return;
        }
        if(x<=ancho/2){
            this.x=e.ancho()-ancho/2;
            return;
        }
        if(x>=e.ancho()-ancho/2){
            this.x=ancho/2;
            return;
        }
    }

    static Ninja[] crearNinja(){
        Ninja[] ninjas=new Ninja[6];
        ninjas[0]=new Ninja(10, 78, 1.9, 1);
        ninjas[1]=new Ninja(300, 235, 1.9, 1);
        ninjas[2]=new Ninja(150, 385, 1.9, 1);
        ninjas[3]=new Ninja(188, 70, 1, 2);
        ninjas[4]=new Ninja(396,70, 1, 2);
        ninjas[5]=new Ninja(609,550, 1, 3);
        return ninjas;
    }

    boolean colisionConSakura(Sakura s){
        return(y-alto/2<s.getY()+ s.getAlto()/2 &&
        y+alto/2>s.getY()-s.getAlto()/2 && 
        x-ancho/2<s.getX()+s.getAncho()/2 && 
        x+ancho/2>s.getX()-s.getAncho()/2);
    }

    static void reaparecerNinjas(Ninja[] n){   
        for(int i=0; i<n.length; i++){
            if(n[i]==null){
                if(i==0){
                    n[0]=new Ninja(10, 78, 1.1, 1);
                }else if(i==1){
                    n[1]=new Ninja(300, 235, 1.1, 1);
                }else if(i==2){
                    n[2]=new Ninja(150, 385, 1.1, 1);
                }else if(i==3){
                    n[3]=new Ninja(188, 70, 1.1, 2);
                }else if(i==4){
                    n[4]=new Ninja(396,70,1.1, 2);
                }else if(i==5){
                    n[5]=new Ninja(609,550,1.1, 3);
                }
            }
        }
    }

    static void aparecerNinjas(Ninja[] n , Entorno e){
        for(int i=0; i<n.length; i++){
            if(n[i]!=null){
                n[i].dibujarse(e);
                n[i].mover();
                n[i].reaparecer(e);
            }
        }
    }

    public double getAncho() {
        return ancho;
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

}
