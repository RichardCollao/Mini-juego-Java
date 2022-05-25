package juego;

/**
 * Por cada instancia de esta clase se desea conocer la fila y la columna donde cayó el proyectil
 * el puntajenobtenido
 */
public class Huevo {

    private int y;
    private int x;
    private int puntajeObtenido;

    public Huevo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getPuntajeObtenido() {
        return puntajeObtenido;
    }

    public void setPuntajeObtenido(int puntajeObtenido) {
        this.puntajeObtenido = puntajeObtenido;
    }

    @Override
    public String toString() {
        return "Huevo{" + "y=" + y + ", x=" + x + ", puntajeObtenido=" + puntajeObtenido + '}';
    }


}
