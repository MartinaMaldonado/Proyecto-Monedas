/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package view;

import controller.E_Monedas;
import controller.Utiles.Utiles;
import java.awt.Component;
import java.awt.Image;
import java.awt.Label;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import model.IconoRenderer;
import modeloTablas.MTableMonedas;

/**
 *
 * @author wilson
 */
public class FrmMonedas extends javax.swing.JDialog {

    private E_Monedas e_Monedas = new E_Monedas();
    private MTableMonedas mTableMonedas;
    private Integer fila = -1;

    public FrmMonedas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        e_Monedas.monedas = e_Monedas.cargarArchivo("data" + File.separatorChar + "monedas.txt");
        cargarTabla(e_Monedas.monedas);
        
        utilesVista.ajustarColumnas(tbltabla);
        utilesVista.cargarNroDatos(cbxmonedaEntrada, e_Monedas.monedas.length);
        utilesVista.cargarNroDatos(cbxmonedaSalida, e_Monedas.monedas.length);
        
        String url = "data" + File.separatorChar + "sesiones.txt";
        e_Monedas.sesiones = e_Monedas.cargarArchivo(url);
    }
    
    private void cargarTabla(Object[][] matriz) {
        mTableMonedas = new MTableMonedas(matriz);
        tbltabla.setModel(mTableMonedas);
        tbltabla.getColumnModel().getColumn(3).setCellRenderer(new IconoRenderer());
        tbltabla.updateUI();
    }

    public String[] generarArregloDeConversion() {
        // Obtener la fecha actual
        Date fechaActual = new Date();
        // Definir el formato de fecha
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        // Formatear la fecha a un String
        String fechaFormateada = formato.format(fechaActual);

        // Obtener el valor ingresado por el usuario (asegurarse de que es un valor numérico)
        String valorEntradaTexto = txtValorEntrada.getText(); // Asume que txtValorEntrada está bien definido

        // Obtener las monedas seleccionadas
        Integer monedaEntrada = (Integer) cbxmonedaEntrada.getSelectedItem();
        Integer monedaSalida = (Integer) cbxmonedaSalida.getSelectedItem();
        //System.out.println(monedaEntrada);

        // Buscar los índices de las monedas seleccionadas en la lista
        int indexEntrada = monedaEntrada - 1;
        int indexSalida = monedaSalida - 1;

        // Obtener las tasas de cambio desde la matriz (asegurándote de que son Double)
        // Aquí se asume que la primera columna de la matriz contiene las tasas de cambio (como Double)
        Double tasaDeCambioEntrada = Double.parseDouble(e_Monedas.monedas[indexEntrada][0].toString()); // Convertir de Object a String y luego a Double
        Double tasaDeCambioSalida = Double.parseDouble(e_Monedas.monedas[indexSalida][0].toString()); // Lo mismo aquí
        try {
            if (valorEntradaTexto.isEmpty()) {
                String[] sesiones = {
                    cbxmonedaEntrada.getSelectedIndex() + "",
                    txtValorEntrada.getText(),
                    cbxmonedaSalida.getSelectedIndex() + "",
                    "(" + e_Monedas.monedas[indexEntrada][1] + ") " + e_Monedas.monedas[indexEntrada][2],
                    "Valor inválido, porfavor ingresar valores numéricos",
                    "(" + e_Monedas.monedas[indexSalida][1] + ") " + e_Monedas.monedas[indexSalida][2],
                    "Valor inválido, porfavor ingresar valores numéricos",
                    fechaFormateada
                };
                return sesiones;
            }

            // Convertir el valor de entrada (valor en la moneda de entrada) de String a Double
            Double valorEntrada = Utiles.tranformStringDouble(valorEntradaTexto);

            // Realizar la conversión: (valor en moneda entrada) * (tasa de moneda salida) / (tasa de moneda entrada)
            Double resultado = valorEntrada * tasaDeCambioSalida / tasaDeCambioEntrada;

            String[] sesiones = {
                cbxmonedaEntrada.getSelectedIndex() + "",
                txtValorEntrada.getText(),
                cbxmonedaSalida.getSelectedIndex() + "",
                "(" + e_Monedas.monedas[indexEntrada][1] + ") " + e_Monedas.monedas[indexEntrada][2],
                e_Monedas.monedas[indexEntrada][3] + " " + String.format("%.2f", Utiles.tranformStringDouble(valorEntradaTexto)),
                "(" + e_Monedas.monedas[indexSalida][1] + ") " + e_Monedas.monedas[indexSalida][2],
                e_Monedas.monedas[indexSalida][3] + " " + String.format("%.2f", resultado),
                fechaFormateada
            };
            return sesiones;

        } catch (NumberFormatException e) {
            // Si el valor ingresado no es un número válido, mostrar un mensaje de error
            String[] sesiones = {
                cbxmonedaEntrada.getSelectedIndex() + "",
                txtValorEntrada.getText(),
                cbxmonedaSalida.getSelectedIndex() + "",
                "(" + e_Monedas.monedas[indexEntrada][1] + ") " + e_Monedas.monedas[indexEntrada][2],
                "Valor inválido, porfavor ingresar valores numéricos",
                "(" + e_Monedas.monedas[indexSalida][1] + ") " + e_Monedas.monedas[indexSalida][2],
                "Valor inválido, porfavor ingresar valores numéricos",
                fechaFormateada
            };
            return sesiones;
        } catch (ClassCastException e) {
            // Si ocurre un error al intentar convertir la tasa de cambio, manejarlo adecuadamente
            String[] sesiones = {
                cbxmonedaEntrada.getSelectedIndex() + "",
                txtValorEntrada.getText(),
                cbxmonedaSalida.getSelectedIndex() + "",
                "(" + e_Monedas.monedas[indexEntrada][1] + ") " + e_Monedas.monedas[indexEntrada][2],
                "Error en las tasas de cambio",
                "(" + e_Monedas.monedas[indexSalida][1] + ") " + e_Monedas.monedas[indexSalida][2],
                "Error en las tasas de cambio",
                fechaFormateada
            };
            return sesiones;
        }
    }

    public void mostrarSesion(String[] sesiones) {
        cbxmonedaEntrada.setSelectedIndex(Utiles.transformStringInt(sesiones[0]));
        txtValorEntrada.setText(sesiones[1]);
        cbxmonedaSalida.setSelectedIndex(Utiles.transformStringInt(sesiones[2]));
        lblMonedaEntrada.setText(sesiones[3]);
        lblCantidadEntrada.setText(sesiones[4]);
        lblMonedaSalida.setText(sesiones[5]);
        lblCantidadSalida.setText(sesiones[6]);
        lblfecha.setText(sesiones[7].split(" ")[0]);
        mostrarImagenes(e_Monedas.monedas[Utiles.transformStringInt(sesiones[0])][5]+"_billete.jpg", lblFotoBilleteEntrada);
        mostrarImagenes(e_Monedas.monedas[Utiles.transformStringInt(sesiones[0])][5]+"_monedas.jpg", lblFotoMonedaEntrada);
        mostrarImagenes(e_Monedas.monedas[Utiles.transformStringInt(sesiones[2])][5]+"_billete.jpg", lblFotoBilleteSalida);
        mostrarImagenes(e_Monedas.monedas[Utiles.transformStringInt(sesiones[2])][5]+"_monedas.jpg", lblFotoMonedaSalida);
    }

    public void agregarSesion() {
        String[][] sesiones = new String[e_Monedas.sesiones.length + 1][0];
        for (int i = 0; i < sesiones.length-1; i++) {
            sesiones[i] = e_Monedas.sesiones[i];
        }
        sesiones[sesiones.length - 1] = generarArregloDeConversion();
        e_Monedas.sesiones = sesiones;
    }

    public String[] generarArregloDeFechasDeSesiones() {;
        String[] fechas = new String[e_Monedas.sesiones.length];    
        for (int i = 0; i < e_Monedas.sesiones.length; i++) {
            fechas[i] = "Sesión " + (i+1) +": "+ e_Monedas.sesiones[i][7];
        }
        return fechas;
    }
    
    public String SolicitarSesion(){
        String[] opciones = generarArregloDeFechasDeSesiones();
        String seleccion = (String) JOptionPane.showInputDialog(
                null, 
                "Seleccione una sesión:", 
                "SESIONES", 
                JOptionPane.QUESTION_MESSAGE, 
                null, 
                opciones, 
                opciones[opciones.length-1]
        );
        return seleccion.split(": ")[1];
    }
    
    public String[] buscarSesionPorFecha(String fecha){
        for (int i = 0; i < e_Monedas.sesiones.length; i++) {
            if(e_Monedas.sesiones[i][7].equalsIgnoreCase(fecha)) return e_Monedas.sesiones[i];
        }
        return null;
    }
    
    public void mostrarImagenes(String imagen, JLabel lbl){
        ImageIcon imagenOriginal = new ImageIcon("imagenes/" + File.separatorChar + imagen); // Cargar imagen
        int anchoLabel = lbl.getWidth(); // Ancho deseado
        int altoLabel = lbl.getHeight(); // Ancho deseado
        int anchoOriginal = imagenOriginal.getIconWidth();
        int altoOriginal = imagenOriginal.getIconHeight();
        int nuevoAncho = anchoLabel;
        int nuevoAlto = (nuevoAncho * altoOriginal) / anchoOriginal;

        if (nuevoAlto > altoLabel) {
            nuevoAlto = altoLabel;
            //nuevoAncho = (nuevoAlto * anchoOriginal) / altoOriginal;
        }
        Image imagenRedimensionada = imagenOriginal.getImage().getScaledInstance(
            (int)(nuevoAncho*0.8), (int)(nuevoAlto*0.8), Image.SCALE_SMOOTH
        );

        // Aplicar la imagen redimensionada al JLabel
        lbl.setIcon(new ImageIcon(imagenRedimensionada));
    }
    

    /*private Boolean choose(String opt, Object[][] matriz) {
        JFileChooser fc = new JFileChooser();
        if (opt.equalsIgnoreCase("guardar")) {
            Integer i = fc.showSaveDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                //System.out.println(fc.getSelectedFile().getAbsolutePath());
                return e_Monedas.generar_files(matriz, fc.getSelectedFile().getAbsolutePath());
            } else {
                System.out.println("No ha seleccionado un archivo");
                return false;
            }
        } else {
            Integer i = fc.showOpenDialog(this);
            if (i == JFileChooser.APPROVE_OPTION) {
                //System.out.println(fc.getSelectedFile().getAbsolutePath());
                matriz = e_Monedas.cargarArchivo(fc.getSelectedFile().getAbsolutePath());
                return true;
            } else {
                System.out.println("No ha seleccionao un archivo");
                return false;
            }
        }
    }*/
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuBar4 = new javax.swing.JMenuBar();
        jMenu5 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbltabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbxmonedaEntrada = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtValorEntrada = new javax.swing.JTextField();
        cbxmonedaSalida = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        btnGenerar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lblMonedaEntrada = new javax.swing.JLabel();
        lblCantidadEntrada = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblFotoMonedaEntrada = new javax.swing.JLabel();
        lblFotoBilleteEntrada = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblCantidadSalida = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblMonedaSalida = new javax.swing.JLabel();
        lblFotoMonedaSalida = new javax.swing.JLabel();
        lblFotoBilleteSalida = new javax.swing.JLabel();
        jMenuBar3 = new javax.swing.JMenuBar();

        jButton1.setText("jButton1");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("File");
        jMenuBar4.add(jMenu5);

        jMenu6.setText("Edit");
        jMenuBar4.add(jMenu6);

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jPanel1.setBackground(new java.awt.Color(76, 76, 76));

        jPanel2.setBackground(new java.awt.Color(89, 89, 89));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbltabla.setBackground(new java.awt.Color(109, 109, 109));
        tbltabla.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tbltabla.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        tbltabla.setForeground(new java.awt.Color(255, 255, 255));
        tbltabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tbltabla);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Monedas");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setOpaque(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(89, 89, 89));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Moneda de entrada:");

        cbxmonedaEntrada.setBackground(new java.awt.Color(51, 51, 51));
        cbxmonedaEntrada.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        cbxmonedaEntrada.setForeground(new java.awt.Color(0, 0, 0));
        cbxmonedaEntrada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbxmonedaEntrada.setToolTipText("");

        jLabel3.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Cantidad:");

        txtValorEntrada.setBackground(new java.awt.Color(51, 51, 51));
        txtValorEntrada.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtValorEntrada.setForeground(new java.awt.Color(255, 255, 255));

        cbxmonedaSalida.setBackground(new java.awt.Color(51, 51, 51));
        cbxmonedaSalida.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        cbxmonedaSalida.setForeground(new java.awt.Color(0, 0, 0));
        cbxmonedaSalida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Moneda de salida:");

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Seleccione");
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel4.setOpaque(true);

        btnGenerar.setBackground(new java.awt.Color(0, 0, 0));
        btnGenerar.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar.setText("CARGAR SESIÓN");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        btnGenerar1.setBackground(new java.awt.Color(0, 0, 0));
        btnGenerar1.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        btnGenerar1.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerar1.setText("GENERAR CONVERSIÓN");
        btnGenerar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtValorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbxmonedaSalida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbxmonedaEntrada, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnGenerar1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGenerar)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cbxmonedaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtValorEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbxmonedaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGenerar, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(btnGenerar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        cbxmonedaEntrada.getAccessibleContext().setAccessibleName("");

        jPanel4.setBackground(new java.awt.Color(89, 89, 89));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Moneda de Entrada");
        jLabel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel6.setOpaque(true);

        jLabel15.setBackground(new java.awt.Color(204, 204, 204));
        jLabel15.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Cantidad:");
        jLabel15.setOpaque(true);

        lblMonedaEntrada.setBackground(new java.awt.Color(204, 204, 204));
        lblMonedaEntrada.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblMonedaEntrada.setForeground(new java.awt.Color(0, 0, 0));
        lblMonedaEntrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonedaEntrada.setText("Realize una conversión");
        lblMonedaEntrada.setOpaque(true);

        lblCantidadEntrada.setBackground(new java.awt.Color(204, 204, 204));
        lblCantidadEntrada.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblCantidadEntrada.setForeground(new java.awt.Color(0, 0, 0));
        lblCantidadEntrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadEntrada.setText("Realize una conversión");
        lblCantidadEntrada.setOpaque(true);

        jLabel16.setBackground(new java.awt.Color(204, 204, 204));
        jLabel16.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Nombre:");
        jLabel16.setOpaque(true);

        lblFotoMonedaEntrada.setBackground(new java.awt.Color(204, 204, 204));
        lblFotoMonedaEntrada.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblFotoMonedaEntrada.setForeground(new java.awt.Color(0, 0, 0));
        lblFotoMonedaEntrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblFotoBilleteEntrada.setBackground(new java.awt.Color(204, 204, 204));
        lblFotoBilleteEntrada.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblFotoBilleteEntrada.setForeground(new java.awt.Color(0, 0, 0));
        lblFotoBilleteEntrada.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMonedaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addComponent(lblFotoBilleteEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFotoMonedaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMonedaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCantidadEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFotoMonedaEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                    .addComponent(lblFotoBilleteEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(89, 89, 89));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Moneda de Salida");
        jLabel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel7.setOpaque(true);

        jLabel12.setBackground(new java.awt.Color(0, 183, 0));
        jLabel12.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Conversión:");
        jLabel12.setOpaque(true);

        lblCantidadSalida.setBackground(new java.awt.Color(0, 183, 0));
        lblCantidadSalida.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblCantidadSalida.setForeground(new java.awt.Color(0, 0, 0));
        lblCantidadSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCantidadSalida.setText("Realize una conversión");
        lblCantidadSalida.setOpaque(true);

        jLabel13.setBackground(new java.awt.Color(173, 173, 0));
        jLabel13.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Fecha");
        jLabel13.setOpaque(true);

        lblfecha.setBackground(new java.awt.Color(173, 173, 0));
        lblfecha.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblfecha.setForeground(new java.awt.Color(0, 0, 0));
        lblfecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblfecha.setText("Realize una conversión");
        lblfecha.setOpaque(true);

        jLabel14.setBackground(new java.awt.Color(204, 204, 204));
        jLabel14.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Nombre:");
        jLabel14.setOpaque(true);

        lblMonedaSalida.setBackground(new java.awt.Color(204, 204, 204));
        lblMonedaSalida.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblMonedaSalida.setForeground(new java.awt.Color(0, 0, 0));
        lblMonedaSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMonedaSalida.setText("Realize una conversión");
        lblMonedaSalida.setOpaque(true);

        lblFotoMonedaSalida.setBackground(new java.awt.Color(204, 204, 204));
        lblFotoMonedaSalida.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblFotoMonedaSalida.setForeground(new java.awt.Color(0, 0, 0));
        lblFotoMonedaSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblFotoBilleteSalida.setBackground(new java.awt.Color(204, 204, 204));
        lblFotoBilleteSalida.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblFotoBilleteSalida.setForeground(new java.awt.Color(0, 0, 0));
        lblFotoBilleteSalida.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCantidadSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMonedaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(lblFotoBilleteSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblFotoMonedaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblMonedaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCantidadSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFotoMonedaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblFotoBilleteSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        setJMenuBar(jMenuBar3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        // TODO add your handling code here:
        mostrarSesion(buscarSesionPorFecha(SolicitarSesion()));
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnGenerar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerar1ActionPerformed
        // TODO add your handling code here:
        agregarSesion();
        mostrarSesion(e_Monedas.sesiones[e_Monedas.sesiones.length-1]);
        String url = "data" + File.separatorChar + "sesiones.txt";
        e_Monedas.generar_files(e_Monedas.sesiones, url);
    }//GEN-LAST:event_btnGenerar1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");


        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmMonedas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmMonedas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmMonedas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmMonedas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmMonedas dialog = new FrmMonedas(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnGenerar1;
    private javax.swing.JComboBox<String> cbxmonedaEntrada;
    private javax.swing.JComboBox<String> cbxmonedaSalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuBar jMenuBar3;
    private javax.swing.JMenuBar jMenuBar4;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCantidadEntrada;
    private javax.swing.JLabel lblCantidadSalida;
    private javax.swing.JLabel lblFotoBilleteEntrada;
    private javax.swing.JLabel lblFotoBilleteSalida;
    private javax.swing.JLabel lblFotoMonedaEntrada;
    private javax.swing.JLabel lblFotoMonedaSalida;
    private javax.swing.JLabel lblMonedaEntrada;
    private javax.swing.JLabel lblMonedaSalida;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JTable tbltabla;
    private javax.swing.JTextField txtValorEntrada;
    // End of variables declaration//GEN-END:variables
}
