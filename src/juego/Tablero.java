package juego;

import java.util.ArrayList;
import java.util.Random;

class Tablero {

    Controlador controlador;
    private ArrayList<Carro> listCarros = new ArrayList();
    private ArrayList<Huevo> listHuevos = new ArrayList();
    String[][] tablero = new String[15][15];
    private int kromi = 15;
    private int caguano = 15;
    private int trupalla = 5;
    private int puntaje = 0;
    private boolean juegoTerminado = false;

    private int kromiOperativa = 0;
    private int caguanoOperativo = 0;
    private int trupallaOprativa = 0;
    private int lanzamiento;

    public Tablero(Controlador controlador) {
        this.controlador = controlador;

        this.crearCarros();
        this.actualizarTablero();
        this.actualizarVista();
    }

    public void lanzarHuevo(int x, int y) {
        Huevo huevo = new Huevo(x, y);
        this.listHuevos.add(huevo);

        Carro carro = this.colisionHuevo(huevo);

        if (carro != null) {
            carro.setVida(carro.getVida() - 1);
            this.puntaje += this.sumarPuntaje(carro);
        }
        // actualiza el juego
        this.actualizarTablero();
        this.actualizarVista();
        this.verificarTermino();
    }

    private int sumarPuntaje(Carro carro) {
        int puntaje = 0;
        String tipo = carro.getClass().getSimpleName();
        if ("Kromi".equals(tipo)) {
            puntaje = carro.getVida() > 0 ? 3 : 13;// 3 por impacto + 10 por destruccion
        }
        if ("Caguano".equals(tipo)) {
            puntaje = carro.getVida() > 0 ? 2 : 9;// 2 por impacto + 7 por destruccion
        }
        if ("Trupalla".equals(tipo)) {
            puntaje = 1;// 1 por impacto + 0 por destruccion
        }
        return puntaje;
    }

    private Carro colisionHuevo(Huevo huevo) {
        int hx = huevo.getX();
        int hy = huevo.getY();
        String celda = this.tablero[hx][hy];
        for (Carro carro : this.listCarros) {
            int cx = carro.getX();
            int cy = carro.getY();
            // Obtiene el nombre de la clase
            String tipo = carro.getClass().getSimpleName();
            // Si las coordenadas del huevo coinciden con la posición de alguno de los carros, este será retornado
            if ("Kromi".equals(tipo) && "K".equals(celda) && hx == cx) {
                if (cy == hy || cy + 1 == hy || cy + 2 == hy) {
                    return carro;
                }
            } else if ("Caguano".equals(tipo) && "C".equals(celda) && hy == cy) {
                if (cx == hx || cx + 1 == hx) {
                    return carro;
                }
            } else if ("Trupalla".equals(tipo) && "T".equals(celda)) {
                if (cy == hy && cx == hx) {
                    return carro;
                }
            }
        }
        return null;
    }

    private int[] coordenadasRandom() {
        Random random = new Random();
        int x = random.nextInt(15);
        int y = random.nextInt(15);
        return new int[]{x, y};
    }

    private void actualizarTablero() {
        this.kromiOperativa = 0;
        this.caguanoOperativo = 0;
        this.trupallaOprativa = 0;
        
        // actualiza tablero según carros
        for (Carro carro : this.listCarros) {
            int x = carro.getX();
            int y = carro.getY();
            if (carro instanceof Kromi) {
                this.tablero[x][y] = "K";
                this.tablero[x][y + 1] = "K";
                this.tablero[x][y + 2] = "K";
            } else if (carro instanceof Caguano) {
                this.tablero[x][y] = "C";
                this.tablero[x + 1][y] = "C";
            } else if (carro instanceof Trupalla) {
                this.tablero[x][y] = "T";
            }

            // actualizar carros operativos
            if (carro.getVida() > 0) {
                String tipo = carro.getClass().getSimpleName();
                this.kromiOperativa = "Kromi".equals(tipo) ? ++kromiOperativa : kromiOperativa;
                this.caguanoOperativo = "Caguano".equals(tipo) ? ++caguanoOperativo : caguanoOperativo;
                this.trupallaOprativa = "Trupalla".equals(tipo) ? ++trupallaOprativa : trupallaOprativa;
            }
        }

        // actualiza tablero según huevos
        this.lanzamiento = 0;
        for (Huevo huevo : this.listHuevos) {
            int x = huevo.getX();
            int y = huevo.getY();
            // discrimina simbologia segun el valor de la casilla
            if (this.tablero[x][y] == null) {
                this.tablero[x][y] = "H";
            } else if (this.tablero[x][y] != "H") {
                // marca la coordenada en miniscula para identificar que ya fue atacada.
                this.tablero[x][y] = this.tablero[x][y].toLowerCase();
            }
            this.lanzamiento++;
        }
        
    }

    // dibuja una representación del tablero en el componente JTable & Consola
    private void mostrarMatriz() {
        System.out.println("");
        for (int i = 0; i <= 14; i++) {
            // cadena que contiene una fila del tablero, para visualizar en consola
            StringBuilder row = new StringBuilder();
            for (int j = 0; j <= 14; j++) {
                // borra el residuo anterior 
                this.controlador.ventana.dtmModelo.setValueAt(" ", i, j);
                // comprueba el valor de la coordenada en el tablero 
                if (this.tablero[j][i] == null) {
                    row.append("-");
                } else {
                    // muestra solo los huevos y las partes del carro que han recibido un impacto.
                    if ("Hktc".contains(this.tablero[j][i])) {
                        this.controlador.ventana.dtmModelo.setValueAt(this.tablero[j][i].toUpperCase(), i, j);
                    }
                    row.append(this.tablero[j][i]);
                }
            }
            // dibuja una fila del tablero en la consola
            System.out.println(row);
        }
    }

    private void actualizarVista() {
        this.mostrarMatriz();
        this.controlador.ventana.jtfPuntaje.setText(Integer.toString(this.puntaje));
        this.controlador.ventana.jtfKromi.setText(Integer.toString(this.kromiOperativa));
        this.controlador.ventana.jtfCaguano.setText(Integer.toString(this.caguanoOperativo));
        this.controlador.ventana.jtfTrupalla.setText(Integer.toString(this.trupallaOprativa));
        this.controlador.ventana.jtfLanzamiento.setText(Integer.toString(this.lanzamiento));
    }

    private void crearCarros() {
        int[] xy;
        for (int i = 0; i < kromi; i++) {
            xy = this.obtenerCoordenadasDisponibles("Kromi");
            this.listCarros.add(new Kromi(xy[0], xy[1]));
        }
        for (int i = 0; i < caguano; i++) {
            xy = this.obtenerCoordenadasDisponibles("Caguano");
            this.listCarros.add(new Caguano(xy[0], xy[1]));
        }
        for (int i = 0; i < trupalla; i++) {
            xy = this.obtenerCoordenadasDisponibles("Trupalla");
            this.listCarros.add(new Trupalla(xy[0], xy[1]));
        }
    }

    private int[] obtenerCoordenadasDisponibles(String tipo) {
        this.actualizarTablero();
        int[] coordenadasRandom = this.coordenadasRandom();
        int x = coordenadasRandom[0];
        int y = coordenadasRandom[1];

        if ("Kromi".equals(tipo) && (y > 12 || tablero[x][y] != null || tablero[x][y + 1] != null || tablero[x][y + 2] != null)) {
            coordenadasRandom = this.obtenerCoordenadasDisponibles(tipo);
        } else if ("Caguano".equals(tipo) && (x > 13 || tablero[x][y] != null || tablero[x + 1][y] != null)) {
            coordenadasRandom = this.obtenerCoordenadasDisponibles(tipo);
        } else if ("Trupalla".equals(tipo) && (tablero[x][y] != null)) {
            coordenadasRandom = this.obtenerCoordenadasDisponibles(tipo);
        }
        return coordenadasRandom;
    }

    public boolean isJuegoTerminado() {
        return juegoTerminado;
    }

    private void verificarTermino() {
        int vida = 0;
        for (Carro carro : this.listCarros) {
            vida += carro.getVida();
        }
        if (vida <= 0) {
            this.juegoTerminado = true;
            this.controlador.ventana.mensaje("Juego terminado");
        }
    }

}
