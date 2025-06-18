import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;

public class InformeMorosos {
    private List<Cliente> clientes;
    private static final String RUTA_INFORMES = System.getProperty("user.home") + "/Desktop/InformesMorosos/";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public InformeMorosos(List<Cliente> clientes) {
        this.clientes = clientes;
        new File(RUTA_INFORMES).mkdirs();
    }

    public void generarInforme() {
        String contenidoInforme = generarContenidoInforme();
        System.out.println(contenidoInforme);
        guardarEnArchivo(contenidoInforme);
    }

    private String generarContenidoInforme() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n=== INFORME DE CLIENTES MOROSOS ===\n");
        sb.append("Generado el: ").append(dateFormat.format(new Date())).append("\n");
        sb.append("----------------------------------\n");
        
        boolean hayMorosos = false;
        Date hoy = new Date();
        
        for (Cliente cliente : clientes) {
            for (Renta renta : cliente.getRentasActivas()) {
                if (renta.getFechaDevolucionPrevista().before(hoy) && 
                    !"devuelto".equals(renta.getEstado())) {
                    
                    hayMorosos = true;
                    long diff = hoy.getTime() - renta.getFechaDevolucionPrevista().getTime();
                    long diasAtraso = diff / (24 * 60 * 60 * 1000);
                    
                    sb.append("Cliente: ").append(cliente.getNombre()).append("\n");
                    sb.append("Teléfono: ").append(cliente.getTelefono()).append("\n");
                    sb.append("Email: ").append(cliente.getEmail()).append("\n");
                    sb.append("Días de atraso: ").append(diasAtraso).append("\n");
                    sb.append("Traje rentado: ").append(renta.getTraje().getNombre()).append("\n");
                    sb.append("Fecha prevista de devolución: ")
                      .append(dateFormat.format(renta.getFechaDevolucionPrevista())).append("\n");
                    sb.append("-----------------------\n");
                }
            }
        }
        
        if (!hayMorosos) {
            sb.append("No hay clientes morosos al día de hoy.\n");
        }
        
        return sb.toString();
    }

    private void guardarEnArchivo(String contenido) {
        String nombreArchivo = RUTA_INFORMES + "informe_morosos_" + 
                             new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".txt";
        
        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo))) {
            writer.print(contenido);
            System.out.println("\nInforme guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al guardar el informe: " + e.getMessage());
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
}