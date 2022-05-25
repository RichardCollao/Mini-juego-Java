package juego;

public class Controlador {

    public Ventana ventana;
    public Tablero tablero;

    public Controlador() {
        this.ventana = new Ventana(this);
        this.run();
    }

    void run() {
        this.tablero = new Tablero(this);
    }
}
