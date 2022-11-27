package juego;

import entorno.Entorno;
import entorno.Herramientas;
import entorno.InterfaceJuego;

import java.awt.Color;
import java.awt.Image;

public class Juego extends InterfaceJuego {

	// El objeto Entorno que controla el tiempo y otros
	private Image img;
	private Entorno entorno;
	private Sakura sakura;
	private int puntaje;
	private Ninja[] ninjas;
	private boolean perdiste;
	private boolean ganaste;
	private Calle[] calles;
	private Manzana[] manzanas;
	private int[] manzana_casas; //nombre de manzana y casa
	private Casa objetivo;
	private Rasengan rasengan;
	private int ninjas_eliminados;
	private int tiempo;
	private boolean cuenta_atras;
	private int contador_ninjas_nulls;
	
	public Juego() {
		// Inicializa el objeto entorno
		entorno = new Entorno(this, "Sakura Ikebana Delivery - Grupo 10 - Chianese - V0.01", 800, 600);
		sakura=new Sakura(entorno.ancho()/2-6, entorno.alto()/2+100, 3); //Inicializar a sakura en el medio
		img=Herramientas.cargarImagen("ganador.png"); //Imagen de fondo si se gana el juego
		manzanas=Manzana.crearManzanas();
		manzana_casas=Manzana.objetivoCasa(manzanas); //Manzana y casa objetivo
		calles=Calle.crearCalles();
		ninjas=Ninja.crearNinja();
		perdiste=false;
		ganaste=false;
		tiempo=0;
		contador_ninjas_nulls=0;
		cuenta_atras=true;
		objetivo=manzanas[manzana_casas[0]].getCasas()[manzana_casas[1]]; //Casa objetivo

		// Inicia el juego!
		entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	public void tick() {
		// Procesamiento de un instante de tiempo
		// ...
		if(!perdiste && !ganaste){
			Manzana.mostrarManzanas(manzanas, entorno);
			if(objetivo.colisionConSakura(sakura)){ //aumentar puntos
				manzanas[manzana_casas[0]].setCasaObjetivo(0); //seteo la manzana para q deje de ser objetivo
				manzana_casas=Manzana.objetivoCasa(manzanas);
				objetivo=manzanas[manzana_casas[0]].getCasas()[manzana_casas[1]];
				puntaje+=5;
				
			}
			Calle.mostrarCalles(calles, entorno);
			sakura.dibujarse(entorno);
			Ninja.aparecerNinjas(ninjas, entorno);
			if(rasengan==null && entorno.sePresiono(entorno.TECLA_ESPACIO) && tiempo==0){
                rasengan=sakura.disparar();
				tiempo=1000;
				cuenta_atras=false;
			}
			if(rasengan!=null){
				rasengan.dibujarse(entorno);
				rasengan.mover();
			}
			for(int i=0; i<ninjas.length; i++){ 
				if(rasengan!=null && ninjas[i]!=null && rasengan.colisionConNinja(ninjas[i])){ //Usar contador para los nulls
					ninjas[i]=null;
					rasengan=null;
					ninjas_eliminados++;
					contador_ninjas_nulls++;
				}
			}
			for(int i=0; i<ninjas.length; i++){ //Checkear q no sea null
				if(ninjas[i]!=null && ninjas[i].colisionConSakura(sakura)){  //Perder el juego
					perdiste=true;
				}
			}
			
			if(tiempo==0 && contador_ninjas_nulls==2){   //Cuando se matan 2 ninjas y el tiempo es 0, vuelven a aparecer
				Ninja.reaparecerNinjas(ninjas); //implementar cuando se hayan matado cierta cantidad de ninjas
				contador_ninjas_nulls=0;
			}

			if(!cuenta_atras){               //El cooldown del rasengan
				tiempo--;
			}
			if(tiempo==0){                   //Rasengan disponible  , hacer null el rasengan cuando se va de los limites de la pantalla
				rasengan=null;
				cuenta_atras=true;
			}

			if(rasengan!=null && rasengan.colisionExtremos(entorno)){  //Si falla el rasengan, puede volver a tirarlo
				tiempo=0;
				rasengan=null;
				cuenta_atras=true;
			}

			if(puntaje>=150){           //150 puntos para ganar el juego
				ganaste=true;
			}
			
			if (entorno.estaPresionada(entorno.TECLA_DERECHA) || entorno.estaPresionada('d'))
				sakura.moverDerecha(entorno, manzanas);

			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA) || entorno.estaPresionada('a'))
				sakura.moverIzquierda(entorno, manzanas);

			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)|| entorno.estaPresionada('w'))
				sakura.moverArriba(entorno, manzanas);
			if (entorno.estaPresionada(entorno.TECLA_ABAJO) || entorno.estaPresionada('s'))
				sakura.moverAbajo(entorno, manzanas);

					
		
			entorno.cambiarFont("Arial", 18, Color.BLACK);
			entorno.escribirTexto("Puntaje: "+puntaje, 680, 20);
			entorno.escribirTexto("Ninjas eliminados: "+ninjas_eliminados, 10, 20);       //Texto in-game
			entorno.cambiarFont("Arial Black", 18, Color.BLACK);
			entorno.escribirTexto("Rasengan: "+tiempo, 650, 580);
		}

		if(perdiste){
			entorno.cambiarFont("Arial", 18, Color.white);
			entorno.escribirTexto("Puntaje: "+puntaje, 150, 350);
			entorno.escribirTexto("Ninjas eliminados: "+ninjas_eliminados, 500, 350);    //Game Over
			entorno.cambiarFont("Arial Black", 56, Color.WHITE);
			entorno.escribirTexto("GAME OVER", 215, 200);
		}

		if(ganaste){
			entorno.dibujarImagen(img, entorno.ancho()/2, entorno.alto()/2, 0, 1);
			entorno.cambiarFont("Arial", 24, Color.black);
			entorno.escribirTexto("Puntaje: "+puntaje, 150, 350);
			entorno.escribirTexto("Ninjas eliminados: "+ninjas_eliminados, 500, 350);    //Pantalla final
			entorno.cambiarFont("Arial Black", 56, Color.BLACK);
			entorno.escribirTexto("GANASTE EL JUEGO", 105, 120);
		}

	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Juego juego = new Juego();
	}

}
