package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Calle {
    private double x;
    private double y;
    private double alto;
    private double ancho;
    private Image img;

    public Calle(double x, double y){
        this.x=x;
        this.y=y;
        alto=30;
        ancho=800;
        img=Herramientas.cargarImagen("calle.png");
    }

    public void dibujarse(Entorno e){
        e.dibujarImagen(img, this.x, this.y, 0, 1);
    }

    public void dibujarseRecto(Entorno e){
        e.dibujarImagen(img, this.x, this.y, Math.PI/2, 1);
    }

    public static Calle[] crearCalles(){
        Calle[] calles=new Calle[7];
        calles[0]=new Calle(400,385);
        calles[1]=new Calle(400,80);
        calles[2]=new Calle(400,540);
        calles[3]=new Calle(400,235);
        calles[4]=new Calle(188,300);
        calles[5]=new Calle(396,300);
        calles[6]=new Calle(608,300);
        return calles;
    }

    public static void  mostrarCalles(Calle[] c, Entorno e){
        for(int i=0; i<c.length; i++){
            if(i<4){
                c[i].dibujarse(e);
            }else{
                c[i].dibujarseRecto(e);
            }
        }
    }
}
