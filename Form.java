

package ProyectoFinal;     //Paquete en el cual guardamos nuestras clases y formularios

    //Librerias
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * @author daniel10522
 */
public class Form extends javax.swing.JFrame {
    /**
     * Creates new form Form
     */
    public Form() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PROYECTO 2");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setToolTipText("");
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Iniciar Aplicacion");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 470, 150, 31));

        txtArea.setBackground(new java.awt.Color(204, 204, 204));
        txtArea.setColumns(20);
        txtArea.setRows(5);
        jScrollPane1.setViewportView(txtArea);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 609, 443));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 632, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Botonque da inicio a nuestro programa
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(validarDefinicion()){
            menuDefinicion(true);
        }else{
            menuDefinicion(false);
        }
        //System.exit(0);     //Salir
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new Form().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables

   
    RandomAccessFile fichero = null, entidades = null, atributos = null;    //Acceso aleatorio
    private final String rutaBase = "C:\\Users\\daniel10522\\Desktop\\Proyecto_Final\\Proyecto2";   //Ubicacion a guardar archivo
    private final String rutaEntidades = "C:\\Users\\daniel10522\\Desktop\\Proyecto_Final\\Proyecto2\\entidades.dat";//Ubicacion a guardar archivo
    private final String rutaAtributos = "C:\\Users\\daniel10522\\Desktop\\Proyecto_Final\\Proyecto2\\atributos.dat"; //Ubicacion a guardar archivo 
    private final int totalBytes = 83, bytesEntidades = 47, bytesAtributo = 43;
    private final static String formatoFecha = "dd/mm/yy";      //Formato de Fecha 
    static DateFormat format = new SimpleDateFormat(formatoFecha);
    Scanner sc = new Scanner(System.in);
 
     private List<Entidad> listaEntidades = new ArrayList<>();      //Lista de Entidades
    
    //Metodo para validar definiciones y crear archivos
    boolean validarDefinicion(){
        boolean respuesta = false;
        try{
            entidades = new RandomAccessFile(rutaEntidades, "rw");
            atributos = new RandomAccessFile(rutaAtributos, "rw");
            long longitud = entidades.length();
            if(longitud <= 0){
                JOptionPane.showMessageDialog(null, "NO EXISTEN REGISTROS", 
                        "IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
                respuesta = false;
            }
            if(longitud >= bytesEntidades){
                entidades.seek(0);
                Entidad ent;
                while(longitud >= bytesEntidades){
                    ent = new Entidad();
                    ent.setIndice(entidades.readInt());
                    byte[] bnombre = new byte[30];
                    entidades.read(bnombre);
                    ent.setBytesNombre(bnombre);
                    ent.setCantidad(entidades.readInt());
                    ent.setBytes(entidades.readInt());
                    ent.setPosicion(entidades.readLong());
                    entidades.readByte();
                    longitud -=bytesEntidades;
                    
                    long longitudAtributos = atributos.length();
                    if(longitudAtributos <= 0){
                        JOptionPane.showMessageDialog(null, "NO EXISTEN REGISTROS", 
                                "IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
                        respuesta = false;
                        break;
                    }
                    atributos.seek(ent.getPosicion());
                    Atributos a;
                    longitudAtributos = ent.getCantidad() * bytesAtributo;
                    while(longitudAtributos >= bytesAtributo){
                        a = new Atributos();
                        a.setIndice(atributos.readInt());
                        byte[] bNombreAtributo = new byte[30];
                        atributos.read(bNombreAtributo);
                        a.setBytesNombre(bNombreAtributo);
                        a.setValorTipoDato(atributos.readInt());
                        a.setLongitud(atributos.readInt());
                        a.setNombreTipoDato();
                        atributos.readByte();
                        ent.setAtributo(a);
                        longitudAtributos -= bytesAtributo;
                    }
                    listaEntidades.add(ent);  
                }
                if(listaEntidades.size() > 0){
                    respuesta = true;
                }
            }
                    
        }catch(Exception e){
            e.printStackTrace();
        }
        return respuesta;
    }
    
    //Metodo que nos muestra las entidades con atributos en nuestra caja de texto
    private void mostrarEntidades(Entidad entidad){
        txtArea.append("----------------------------------\n");
        txtArea.append("Indice: "+ entidad.getIndice()+"\n");
         txtArea.append("Nombre: "+ entidad.getNombre()+"\n");
         txtArea.append("Cantidad de Atributos: "+ entidad.getCantidad()+"\n");
         txtArea.append("Atributos: \n");
        int i =1;
        for (Atributos atributo : entidad.getAtributos()) {
             txtArea.append("\tNo. "+i+"\n");
             txtArea.append("\tNombre: "+atributo.getNombre()+"\n");
             txtArea.append("\tTipo de Dato: "+ atributo.getNombreTipoDato()+"\n");
            if(atributo.isRequiereLongitud()){
                 txtArea.append("\tLongitud: "+atributo.getLongitud()+"\n");
            }
            i++;
        }
    }
    
    //Metodo que nos sirve para agregar entidad nueva
    private boolean agregarEntidad(){
        boolean resultado = false;
        try{
            Entidad entidad = new Entidad();
            entidad.setIndice(listaEntidades.size() + 1);
            String strNombre = "";
            int longitud = 0;
            do{ 
                 strNombre = JOptionPane.showInputDialog(null, "Ingrese el Nombre de la Entidad",
                         "NOMBRE ENTIDAD", JOptionPane.INFORMATION_MESSAGE);
                longitud = strNombre.length();
                if(longitud < 2 || longitud > 30){
                    JOptionPane.showMessageDialog(null, "La longitud del Nombre no es Valida (3-30)", 
                            "ERROR", JOptionPane.ERROR_MESSAGE);                
                }else{
                    if(strNombre.contains(" ")){
                       JOptionPane.showMessageDialog(null, "El Nombre no Puede Tener Espacios, "
                               + "Sustituya con guion bajo", "ERROR", JOptionPane.ERROR_MESSAGE);
                        longitud = 0;
                    }
                }  
            }while(longitud < 2 || longitud>30);
            entidad.setNombre(strNombre);
            int bndDetener = 0;
            do{
                Atributos atributos = new Atributos();
                atributos.setIndice(entidad.getIndice());
                longitud = 0;
                do{
                    strNombre = JOptionPane.showInputDialog(null, "Escriba el Nombre del Atributo no. "
                            + (entidad.getCantidad()+1), "ATRIBUTO", JOptionPane.INFORMATION_MESSAGE);
                    longitud = strNombre.length();
                    if(longitud<2 || longitud>30){
                         JOptionPane.showMessageDialog(null, "La Longitud del Nombre no es Valida (3 - 30)", 
                                 "ERROR", JOptionPane.ERROR_MESSAGE);     
                    }else{
                        if(strNombre.contains(" ")){
                            JOptionPane.showMessageDialog(null, "El Nombre no debe contener espacios, utilize guion bajo ",
                                    "ERROR", JOptionPane.ERROR_MESSAGE);
                             longitud = 0;
                        }
                    }  
                }while(longitud < 2 || longitud > 30);
                atributos.setNombre(strNombre);
                txtArea.append("SELECCIONE TIPO DE DATO\n");
                txtArea.append("        "+TipoDato.INT.getValue() + " .......... " + TipoDato.INT.name()+"\n");
                txtArea.append("        "+TipoDato.LONG.getValue() + " .......... " + TipoDato.LONG.name()+"\n");
                txtArea.append("        "+TipoDato.STRING.getValue() + " .......... " + TipoDato.STRING.name()+"\n");
                txtArea.append("        "+TipoDato.DOUBLE.getValue() + " .......... " + TipoDato.DOUBLE.name()+"\n");
                txtArea.append("        "+TipoDato.FLOAT.getValue() + " .......... " + TipoDato.FLOAT.name()+"\n");
                txtArea.append("        "+TipoDato.DATE.getValue() + " .......... " + TipoDato.DATE.name()+"\n");
                txtArea.append("        "+TipoDato.CHAR.getValue() + " .......... " + TipoDato.CHAR.name()+"\n");
                txtArea.append(" \n");
                atributos.setValorTipoDato(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese Tipo de Dato", 
                            "TIPO DE DATO", JOptionPane.INFORMATION_MESSAGE)));
                if(atributos.isRequiereLongitud()){
                    //System.out.println("Ingrese la Longitud");
                    atributos.setLongitud(Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese la longitud", 
                            "LONGITUD", JOptionPane.INFORMATION_MESSAGE)));
                }else{
                    atributos.setLongitud(0);
                }
                atributos.setNombreTipoDato();
                entidad.setAtributo(atributos);
                bndDetener = Integer.parseInt(JOptionPane.showInputDialog(null,"¿Desea agregar otro atributo?\n"
                        + "Si presione cualquier numero\nNo presione 0","INTERROGANTE", JOptionPane.QUESTION_MESSAGE));
            }while(bndDetener != 0);
            mostrarEntidades(entidad);
            //System.out.println("Presione 1 para guardar 0 para cancelar");
            longitud = Integer.parseInt(JOptionPane.showInputDialog(null, "Esta Seguro de Registrar los Datos?\n"
                    + "1->>Guardar\n0-->>Cancelar","INTERROGANTE", JOptionPane.QUESTION_MESSAGE));
            if(longitud == 1){
                entidad.setPosicion(atributos.length());
                atributos.seek(atributos.length());
                for(Atributos atributo : entidad.getAtributos()){
                    atributos.writeInt(atributo.getIndice());
                     atributos.write(atributo.getBytesNombre());
                    atributos.writeInt(atributo.getValorTipoDato());
                     atributos.writeInt(atributo.getLongitud());
                    atributos.write("\n".getBytes());
                }
                entidades.writeInt(entidad.getIndice());
                entidades.write(entidad.getBytesNombre());
                entidades.writeInt(entidad.getCantidad());
                entidades.writeInt(entidad.getBytes());
                entidades.writeLong(entidad.getPosicion());
                entidades.write("\n".getBytes());
                listaEntidades.add(entidad);
                resultado = true;    
            }else{
                JOptionPane.showMessageDialog(null ,"No se ha Guardado la Entidad porque el Usuario decidio Cancelar", 
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                resultado = false;
            }    
        }catch(Exception e){
            resultado = false;
            e.printStackTrace();
        }
        return resultado;
    }   
    
    // Metodo que nos modifica la entidad
    private void modificarEntidad(){
    try{
        int indice = 0;
        while(indice<1 || indice>listaEntidades.size()){
            for(Entidad entidad : listaEntidades){
               System.out.println(entidad.getIndice() + "..... "+ entidad.getNombre());
            }
           System.out.println("Seleccione la Entidad que Desea Modificar");
           indice = sc.nextInt();
       }
       Entidad entidad = null;
       for(Entidad e : listaEntidades){
           if(indice == e.getIndice()){
               entidad = e;
               break;
           }
       }
       String nombreFichero = formarNombreFichero(entidad.getNombre());
       fichero = new RandomAccessFile(rutaBase + nombreFichero, "rw");
       long longitudDatos = fichero.length();
       fichero.close();
       if(longitudDatos > 0){
           JOptionPane.showMessageDialog(null, "La Entidad no se Puede Modificar porque ya Tiene Datos");      
       }else{
           boolean bdnEncontrado = false, bdnModificado = false;
           entidades.seek(0);
           long longitud = entidades.length();
           int registros = 0, salir = 0, i;
           Entidad e;
           byte[] tmpBytes;
           while(longitud > totalBytes){
               e = new Entidad();
               e.setIndice(entidades.readInt());
               tmpBytes = new byte[30];
               entidades.read(tmpBytes);
               e.setBytesNombre(tmpBytes);
               e.setCantidad(entidades.readInt());
               e.setBytes(entidades.readInt());
               e.setPosicion(entidades.readLong());
               if(entidad.getIndice() == e.getIndice()){
                   System.out.println("Si no desea Modificar el Campo Prsione Enter");
                   System.out.println("Ingrese el Nombre");
                   String tmpStr = "";
                   int len = 0;
                   long posicion;
                    do{
                        tmpStr = sc.nextLine();
                         len = tmpStr.length();
                        if(len== 1 || len>30){
                           JOptionPane.showMessageDialog(null, "La Longitud no es Valida (2 - 30)");
                        }      
                    }while(len==1 || len>30);
                    if(len > 0){
                        e.setNombre(tmpStr);
                        posicion = registros * totalBytes;
                        fichero.seek(posicion);
                        fichero.skipBytes(4);
                        fichero.write(e.getBytesNombre());
                        bdnModificado = true;
                   }
                   i = 1;
                   for(Atributos a : entidad.getAtributos()){
                       System.out.println("Modificando Atributo 1");
                       System.out.println(a.getNombre().trim());
                    }
                   break;
                }
                registros++;
                longitud -= totalBytes;            
            }
        }
           
   }catch(Exception e){
       System.out.println("Error: "+ e.getMessage());
    }    
}   
   //Menu de Opciones que nos brindara el programa
void menuDefinicion(boolean mostrarAgregarRegistros){
    int opcion = 0;
    while(opcion !=6){ 
       try{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null,
                    //Menu de Opciones
                    "Elija una opcion\n" 
                    +"1---Agregar Entidad\n"
                    +"2---Modificar Entidad\n"
                    +"3---Listar Entidad\n"
                    +"4---Agregar Registros\n"
                    +"5---Eliminar Bases de Datos\n"
                    + "6---Salir", "MENU DE OPCIONES",JOptionPane.INFORMATION_MESSAGE));
        switch(opcion){
            case 1:
                //Agregar Entidad
                if(agregarEntidad()){
                    JOptionPane.showMessageDialog(null, "Entidad Agregada Con Exito", 
                            "ENTIDAD AGREGADA", JOptionPane.INFORMATION_MESSAGE);
                    mostrarAgregarRegistros = true;
                }
                break;

            case 2:
                //Modificar Entidad
                modificarEntidad();
                break;

            case 3:
                //Listar Entidad
                   if(listaEntidades.size()>0){
                       int tmpInt = 0;                       
                       tmpInt = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Desea imprimir los detalles?\n"
                               +"Si, presione 1.\nNo, presione 0.", "INTERROGANTE", JOptionPane.QUESTION_MESSAGE));
                       if(tmpInt == 1){
                           for(Entidad entidad : listaEntidades){
                               mostrarEntidades(entidad);
                           }
                       }else{
                           for(Entidad entidad : listaEntidades){
                               txtArea.append("indice: " + entidad.getIndice());
                               txtArea.append("Nombre: " + entidad.getNombre());
                               txtArea.append("Cantidad de Atributos: " + entidad.getCantidad());
                           }
                       }
                   }else{
                       JOptionPane.showMessageDialog(null, "No Hay Entidades Registradas ", 
                               "IMPORTANTE",JOptionPane.INFORMATION_MESSAGE);
                   }
                   break;

            case 4:
                //Agregar Registro
                int indice = 0;
                while(indice<1 || indice>listaEntidades.size()){
                    for(Entidad entidad : listaEntidades){
                        txtArea.append("\nENTIDADES DISPONIBLES\n");
                        txtArea.append("     "+entidad.getIndice() + "---" + entidad.getNombre()+"\n");
                    }                  
                    indice = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione la Entidad", 
                            "SELECCIONAR", JOptionPane.INFORMATION_MESSAGE));
                }
                iniciar(indice);
                break;
            
            case 5:
                //Eliminar Base de Datos
                int confirmar = 0;
                confirmar = Integer.parseInt(JOptionPane.showInputDialog(null, "¿ESTA SEGURO DE ELIMINAR LAS BASES DE DATOS? \n"
                        +"Presione 1 para eliminar todo, de lo contrario cualquier numero para cancelar\n"
                        + "UNA VEZ ELIMINADO YA NO SE PODRA RECUPERAR", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE ));
                if(confirmar == 1){
                    cerrarArchivos();
                        if(borrarArchivos()){
                            listaEntidades = null;
                            listaEntidades = new ArrayList<>();
                            mostrarAgregarRegistros = false;
                            JOptionPane.showMessageDialog(null,"BASES DE DATOS ELIMINADAS CON EXITO", 
                                    "ELIMINADO", JOptionPane.INFORMATION_MESSAGE);
                        }
                }
                break;
                
            case 6:
                //Mensaje de Despedida
                JOptionPane.showMessageDialog(null, "Gracias por Utilizar Nuestro Sistema", 
                        "HASTA PRONTO", JOptionPane.CLOSED_OPTION);
                break;

            default:
                JOptionPane.showMessageDialog(null, "OPCION INVALIDA", 
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                break;
        }
      }catch(Exception e){
      JOptionPane.showMessageDialog(null, "Debe Seleccionar Una Opcion", 
              "ERROR", JOptionPane.ERROR_MESSAGE);
    }
        }       
}

        //Cerrando archivos
    private void cerrarArchivos(){
        if(fichero != null){
            try{
                fichero.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        if(atributos != null){
            try{
                atributos.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
        if(entidades != null){
            try{
                entidades.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
        //Borrando Archivos
    public boolean borrarArchivos(){
        boolean res = false;
        try{
            File file;
            for(Entidad entidad : listaEntidades){
                file = new File(rutaBase + entidad.getNombre().trim()+ ".dat");
                if(file.exists()){
                    file.delete();
                }
                file = null;
            }
            file = new File(rutaAtributos);
            if(file.exists()){
                file.delete();
            }
            file = null;
            file = new File(rutaEntidades);
            if(file.exists()){
                file.delete();
            }
            file = null;
            res = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return res;    
    }
    //Estableciendo formato de archivos
   private String formarNombreFichero(String nombre) {
	return nombre.trim() + ".dat";
    }
    
   //Metodo para agregar registros
    private void iniciar(int indice){
        int opcion = 0;
         String nombreFichero = "";
        try{
            Entidad entidad = null;
            for(Entidad e : listaEntidades){
                if(indice == e.getIndice()){
                    nombreFichero = formarNombreFichero(e.getNombre());
                    entidad = e;
                    break;
                }
            }
           fichero = new RandomAccessFile(rutaBase + nombreFichero, "rw");
           Atributos a = entidad.getAtributos().get(0);
           do{
               try{
                   //Opciones de los Registros
                    opcion = Integer.parseInt(JOptionPane.showInputDialog(null, "Seleccione una Opcion\n"
                            + "1---Agregar\n"
                            + "2---Listar\n"
                            + "3---Modificar\n"
                            + "4---Eiminar\n"
                            + "0---Regresar al Menu Anterior","BIENVENIDO", JOptionPane.INFORMATION_MESSAGE));
                    switch(opcion){
                        case 0:
                            //Regrear al menu anterior
                            System.out.println("");
                            break;
                        
                        case 1:
                            //Agregar y Guardar registro
                            grabarRegistro(entidad);
                            break;
                          
                        case 2:
                            //Listar registros en la caja de texto
                            listarRegistros(entidad);
                             break;
                        
                        case 3:
                            //Modificar registro
                            System.out.println("Ingrese el carne a modificar: ");
                            //carne = sc.nextInt();
                            //sc.nextLine();
                            modificarRegistros();
                            break;
                        
                        default:
                            //Mensaje de Opcion incorrecta
                            System.out.println("Opcion Invalida");
                            break;
                    }                  
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Debe de Elegir una Opcion", 
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                }           
            }while(opcion != 0);           
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Eliga una Opcion", 
                            "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Gauardar Registro
    private boolean grabarRegistro(Entidad entidad){
        boolean resultado = false;
        try{
            fichero.seek(fichero.length());
            boolean valido;
            byte[] bytesString;
            String tmpString = "";
            for(Atributos atributo : entidad.getAtributos()){
                valido = false;
               // System.out.println("Ingrese " + atributo.getNombre().trim());
                while(!valido){
                    try{
                        switch(atributo.getTipoDato()){
                            case INT:
                                int tmpInt = Integer.parseInt(JOptionPane.showInputDialog(null, 
                                        "Ingrese "+ atributo.getNombre().trim()));
                                fichero.writeInt(tmpInt);
                               // sc.nextLine();
                                break;
                            
                            case LONG:
                                long tmpLong = Long.parseLong(JOptionPane.showInputDialog(null, 
                                        "Ingrese "+ atributo.getNombre().trim()));
                                fichero.writeLong(tmpLong);
                                break;
                            
                            case STRING:
                                int longitud = 0;
                                do{
                                   tmpString = JOptionPane.showInputDialog(null, 
                                        "Ingrese "+ atributo.getNombre().trim());
                                   longitud = tmpString.length();
                                   if(longitud <=1 || longitud>atributo.getLongitud()){
                                       JOptionPane.showMessageDialog(null, "La longitud de "+ atributo.getNombre().trim() 
                                                + " no es valida [1- "+ atributo.getLongitud() + "]",
                                               "ERROR", JOptionPane.ERROR_MESSAGE);   
                                    }    
                                }while(longitud <= 0 || longitud > atributo.getLongitud());
                                bytesString = new byte[atributo.getLongitud()];
                                for(int i=0; i<tmpString.length(); i++){
                                    bytesString[i] = (byte) tmpString.charAt(i);
                                }
                                fichero.write(bytesString);
                                break;
                            
                            case DOUBLE:
                                double tmpDouble = Double.parseDouble(JOptionPane.showInputDialog(null, 
                                        "Ingrese "+ atributo.getNombre().trim()));
                                fichero.writeDouble(tmpDouble);
                                break;
                                
                            case FLOAT:
                                float tmpFloat = Float.parseFloat(JOptionPane.showInputDialog(null, 
                                        "Ingrese "+ atributo.getNombre().trim()));
                                fichero.writeFloat(tmpFloat);
                                break;
                            
                            case DATE:
                                Date date = null;
                                tmpString = "";
                                while(date == null){
                                   //System.out.println("Formato de Fecha: " + formatoFecha);
                                    tmpString = JOptionPane.showInputDialog(null, "Formato de Fecha: "+ formatoFecha);
                                    date = stringToDate(tmpString);
                                }
                                bytesString = new byte[atributo.getBytes()];
                                for(int i=0; i<tmpString.length(); i++){
                                    bytesString[i] = (byte) tmpString.charAt(i);
                                }
                                fichero.write(bytesString);
                                break;
                                
                            case CHAR:
                                do{
                                    tmpString = JOptionPane.showInputDialog(null, 
                                        "Ingrese "+ atributo.getNombre().trim());
                                    longitud = tmpString.length();
                                    if(longitud <1 || longitud>1){
                                        JOptionPane.showMessageDialog(null, "Solo se Permite un Caracter", 
                                                "IMPORTANTE", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }while(longitud<1 || longitud>1);
                                byte caracter = (byte) tmpString.charAt(0);
                                fichero.writeByte(caracter);
                                break;
                        }
                        valido = true;
                    }catch(Exception e){
                        //System.out.println("Error "+ e.getMessage()+ " al capturar tipo de dato, "
                         //       + "ingrese de nuevo el valoro: ");
                        JOptionPane.showInputDialog(null, "Error "+ e.getMessage()+ " al capturar tipo de dato, "
                                + "ingrese de nuevo el valoro: ", "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            fichero.write("\n".getBytes());
            resultado = true;
        }catch(Exception e){
            resultado = false;
            JOptionPane.showMessageDialog(null, "Error al agregar el registro " + e.getMessage(), 
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return resultado;
    }
    
    //Metodo para Modificar Registros
    public void modificarRegistros(){
        
    }
    
    
    //Metodo para Eliminar Registros
        public void eliminarRegistros(){
            
            
            
        }
        
    //Metodo para Listar Registros
    public void listarRegistros(Entidad entidad){
        try{
            long longitud = fichero.length();
            if(longitud <=0){
                JOptionPane.showMessageDialog(null, "No Existen Registros", 
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                return;
            }
            fichero.seek(0);
            byte[] tmpArrayByte;
            String linea = "";
            for(Atributos atributo : entidad.getAtributos()){
               linea += atributo.getNombre().toString().trim() + "\t";
            }
            txtArea.append("-------------------------------------------------------\n");
            txtArea.append(linea+"\n");
            while(longitud>= entidad.getBytes()){
                linea = "";
                for(Atributos atributo : entidad.getAtributos()){
                    switch(atributo.getTipoDato()){
                        case INT:
                            int tmpInt = fichero.readInt();
                            linea += String.valueOf(tmpInt) + "\t";
                            break;
                        
                        case LONG:
                            long tmpLong = fichero.readLong();
                            linea += String.valueOf(tmpLong) + "\t";
                            break;
                            
                        case STRING:
                            tmpArrayByte = new byte[atributo.getLongitud()];
                            fichero.read(tmpArrayByte);
                            String tmpString = new String(tmpArrayByte);
                            linea += tmpString.trim() + "\t";
                            break;
                            
                        case DOUBLE:
                            double tmpDouble = fichero.readDouble();
                            linea += String.valueOf(tmpDouble)+ "\t";
                            break;
                        
                        case FLOAT:
                            float tmpFloat = fichero.readFloat();
                            linea += String.valueOf(tmpFloat)+ "\t";
                            break;
                        
                        case DATE:
                            tmpArrayByte = new byte[atributo.getBytes()];
                            fichero.read(tmpArrayByte);
                            tmpString = new String(tmpArrayByte);
                            linea += tmpString.trim() + "\t";
                        
                        case CHAR:
                            char tmpChar = (char) fichero.readByte();
                            linea += tmpChar + "\t";
                            break;
                    }
                }
                fichero.readByte();
                longitud-=entidad.getBytes();
                txtArea.append(linea+"\n");
            } 
        }catch(Exception e){
           // JOptionPane.showMessageDialog(null, "Error: "+ e.getMessage(),
             //       "ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
        //Metodo para fecha
    public Date stringToDate(String strFecha){
        Date date = null;
        try{
            date = format.parse(strFecha);
        }catch(Exception e){
            date = null;
            JOptionPane.showMessageDialog(null, "Error en Fecha"+ e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return date;
    }
    
    public String dateToString(Date date){
        String strFecha;
        strFecha = format.format(date);
        return strFecha;
    }  
}
