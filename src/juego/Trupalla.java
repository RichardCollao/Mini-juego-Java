package juego;

/**
 *
 * @author richard
 */
public class Trupalla extends Carro {

    private int nivel;
    private String chofer;
    private int vida;

    public Trupalla(int x, int y) {
        super(x, y);
        this.vida = 1;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getChofer() {
        return chofer;
    }

    public void setChofer(String chofer) {
        this.chofer = chofer;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    @Override
    public String toString() {
        return "Trupalla{" + "nivel=" + nivel + ", chofer=" + chofer + '}';
    }

}
