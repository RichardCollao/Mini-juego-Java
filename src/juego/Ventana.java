package juego;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author richard
 */
public class Ventana extends JFrame implements ActionListener, MouseListener {

    Controlador controlador;
    public JLabel lblPuntaje;
    public JTextField jtfLanzamiento;
    public JLabel lblLanzamiento;
    public JTextField jtfPuntaje;
    public JLabel lblKromi;
    public JTextField jtfKromi;
    public JLabel lblCaguano;
    public JTextField jtfCaguano;
    public JLabel lblTrupalla;
    public JTextField jtfTrupalla;
    public JLabel lblColumna;
    public JTextField jtfColumna;
    public JLabel lblFila;
    public JTextField jtfFila;
    public Button btnNuevoJuego;
    public Button btnLanzarHuevo;
    public JTable jtTabla;
    public DefaultTableModel dtmModelo;

    public Ventana(Controlador controlador) {
        this.controlador = controlador;

        setLocationRelativeTo(null);
        setLayout(null);

        setBounds(10, 10, 1000, 660);
        setTitle("..::FirstLine::..");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.componentes();
        setVisible(true);

        // agrega los eventos a los componentes que interactúan con el usuario. 
        btnNuevoJuego.addActionListener(this);
        jtTabla.addMouseListener((MouseListener) this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // comprueba si el juego está terminado y evita nuevas interacciones
        if (this.controlador.tablero.isJuegoTerminado()) {
            return;
        }
        if (e.getSource() == jtTabla) {
            // obtiene las coordenadas del puntero dentro de la tabla.
            JTable source = (JTable) e.getSource();
            int x = source.columnAtPoint(e.getPoint());
            int y = source.rowAtPoint(e.getPoint());
            this.controlador.tablero.lanzarHuevo(x, y);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnNuevoJuego) {
            this.controlador.run();
        }
    }

    public void componentes() {
        // Puntaje
        lblPuntaje = new JLabel("Puntaje:");
        lblPuntaje.setBounds(700, 50, 200, 30);
        lblPuntaje.setFont(new Font("Calibri", Font.BOLD, 18));
        add(lblPuntaje);
        jtfPuntaje = new JTextField();
        jtfPuntaje.setBounds(870, 50, 80, 30);
        jtfPuntaje.setText("0");
        jtfPuntaje.setEditable(false);
        jtfPuntaje.setFont(new Font("Calibri", Font.BOLD, 18));
        jtfPuntaje.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfPuntaje.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(jtfPuntaje);

        // Lanzamiento
        lblLanzamiento = new JLabel("Lanzamientos:");
        lblLanzamiento.setBounds(700, 100, 200, 30);
        lblLanzamiento.setFont(new Font("Calibri", Font.BOLD, 18));
        add(lblLanzamiento);
        jtfLanzamiento = new JTextField();
        jtfLanzamiento.setBounds(870, 100, 80, 30);
        jtfLanzamiento.setText("0");
        jtfLanzamiento.setEditable(false);
        jtfLanzamiento.setFont(new Font("Calibri", Font.BOLD, 18));
        jtfLanzamiento.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfLanzamiento.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(jtfLanzamiento);

        // Kromi
        lblKromi = new JLabel("Kromis:");
        lblKromi.setBounds(700, 150, 200, 30);
        add(lblKromi);
        jtfKromi = new JTextField();
        jtfKromi.setBounds(870, 150, 80, 30);
        jtfKromi.setText("0");
        jtfKromi.setEditable(false);
        jtfKromi.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfKromi.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(jtfKromi);

        // Caguano
        lblCaguano = new JLabel("Caguanos:");
        lblCaguano.setBounds(700, 200, 200, 30);
        add(lblCaguano);
        jtfCaguano = new JTextField();
        jtfCaguano.setBounds(870, 200, 80, 30);
        jtfCaguano.setText("0");
        jtfCaguano.setEditable(false);
        jtfCaguano.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfCaguano.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(jtfCaguano);

        // Trupalla
        lblTrupalla = new JLabel("Trupallas:");
        lblTrupalla.setBounds(700, 250, 200, 30);
        add(lblTrupalla);
        jtfTrupalla = new JTextField();
        jtfTrupalla.setBounds(870, 250, 80, 30);
        jtfTrupalla.setText("0");
        jtfTrupalla.setEditable(false);
        jtfTrupalla.setHorizontalAlignment(SwingConstants.RIGHT);
        jtfTrupalla.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        add(jtfTrupalla);

        // Boton nuevo juevo
        btnNuevoJuego = new Button("Nuevo juego");
        btnNuevoJuego.setBounds(700, 300, 250, 50);
        add(btnNuevoJuego);

        // Inicializa el componente tabla con el modelo para tener separación modelo-vista del componente
        dtmModelo = new DefaultTableModel();
        jtTabla = new JTable(dtmModelo);
        add(jtTabla);

        String[] columnNames = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        for (String columnName : columnNames) {
            dtmModelo.addColumn(columnName);
        }

        dtmModelo.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        for (int i = 0; i < 14; i++) {
            dtmModelo.addRow(new Object[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }

        jtTabla.setBounds(50, 50, 600, 540);
        jtTabla.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jtTabla.setRowSelectionAllowed(false);
        jtTabla.setRowHeight(36);
        // Centrar texto al centro
        DefaultTableCellRenderer stringRenderer = (DefaultTableCellRenderer) jtTabla.getDefaultRenderer(String.class);
        stringRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void mensaje(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO:
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO:
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO:
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO:
    }

}
