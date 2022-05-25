package juego;

/**
 *
 * @author richard
 */
public class Carro {

    private int x;
    private int y;
    protected int vida;

    public Carro(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void restarVida() {
        this.vida = this.vida - 1;
    }

    @Override
    public String toString() {
        return "Carro{" + "x=" + x + ", y=" + y + '}';
    }

}
