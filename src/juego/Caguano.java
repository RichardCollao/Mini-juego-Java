package juego;

/**
 *
 * @author richard
 */
public class Caguano extends Carro {

    private int alcance;
    private String color;
    

    public Caguano(int x, int y) {
        super(x, y);
        this.vida = 2;
    }

    public int getAlcance() {
        return this.alcance;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Caguano{" + "alcance=" + alcance + ", color=" + color + '}';
    }

}