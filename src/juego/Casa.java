package juego;

public class Casa {
    private double x;
    private double y;
    private double ancho;
    private double alto;
    
    
    public Casa(double x, double y){
        this.x=x;
        this.y=y;
        ancho=30;
        alto=35;
    }
    
    public boolean colisionConSakura(Sakura s) {
        return(y-(alto/2)-10<s.getY()+ s.getAlto()/2 &&
        y+(alto/2)+10>s.getY()-s.getAlto()/2 && 
        x-(ancho/2)-10<s.getX()+s.getAncho()/2 && 
        x+(ancho/2)+10>s.getX()-s.getAncho()/2);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }

    
}
