package juego;

/**
 *
 * @author richard
 */
public class Kromi extends Carro {

    private int anio;
    private String marca;
    private int vida;

    public Kromi(int x, int y) {
        super(x, y);
        this.vida = 3;
    }

    public int getAnio() {
        return this.anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Kromi{" + "anio=" + anio + ", marca=" + marca + '}';
    }
}
