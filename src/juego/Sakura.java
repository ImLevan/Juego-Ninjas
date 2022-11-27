package juego;

import entorno.Entorno;
import entorno.Herramientas;
import java.awt.Color;
import java.awt.Image;

public class Sakura {
    private double x;
    private double y;
    private Image img1;
    private double ancho;
    private double alto;
    private double factorDesplazamiento;
    private int direccion; //1 arriba, 2 abajo, 3 izq, 4 derecha

    public Sakura(double x, double y, double f){
        this.x=x;
        this.y=y;
        this.factorDesplazamiento=f;
        this.ancho=28;
        this.alto=25;
        this.direccion=3;  //para evitar que el rasengan no se mueva si no se indica una direccion
        img1=Herramientas.cargarImagen("sakuraa.png");
    }

    public void dibujarse(Entorno e){
        e.dibujarImagen(img1, this.x, this.y, 0, 0.027);
    }

    private boolean chocasteCon(Manzana[] m) {
        for(int i=0; i<m.length; i++){
            if(chocasteConUnaManzana(m[i])){
                return true;
            }
        }
        return false;
    }

    private boolean chocasteConUnaManzana(Manzana manzana) {
        return(y-alto/2<manzana.getY()+ manzana.getAlto()/2 &&
        y+alto/2>manzana.getY()-manzana.getAlto()/2 && 
        x-ancho/2<manzana.getX()+manzana.getAncho()/2 && 
        x+ancho/2>manzana.getX()-manzana.getAncho()/2);
    }

    public void moverIzquierda(Entorno e, Manzana[]  m) {
        if(this.x>ancho/2 && !this.chocasteCon(m)){
		    this.x -= factorDesplazamiento;
        }
        if(chocasteCon(m)){
            this.x+=factorDesplazamiento;
        }
        this.direccion=3;
	}
	

    public void moverDerecha(Entorno e, Manzana[] m) {
        if(this.x<e.ancho()-ancho/2 && !this.chocasteCon(m)){
		    this.x += factorDesplazamiento;
        }
        if(chocasteCon(m)){
            this.x-=factorDesplazamiento;
        }
        this.direccion=4;
	}

    public void moverArriba(Entorno e, Manzana[] m) {
        if(this.y>alto/2 && !this.chocasteCon(m)){
		    this.y -= factorDesplazamiento;
        }
        if(chocasteCon(m)){
            this.y+=factorDesplazamiento;
        }
        this.direccion=1;
	}
	
	public void moverAbajo(Entorno e, Manzana[] m) {
        if(this.y<e.alto()-alto/2 && !this.chocasteCon(m)){
		    this.y += factorDesplazamiento;
        }
        if(chocasteCon(m)){
            this.y-=factorDesplazamiento;
        }
        this.direccion=2;
	}

    public Rasengan disparar(){
        return new Rasengan(this.x, this.y, this.direccion);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    
}
