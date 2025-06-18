import java.util.*;

/**
 * Class Empleado
 */
public class Empleado {
    private String id;
    private String nombre;
    private String usuario;
    private String contrasena;

    //
    // Constructors
    //
    public Empleado() { }

    //
    // Methods
    //

    /**
     * Registra una nueva renta en el sistema
     * @param cliente El cliente que realiza la renta
     * @param traje El traje que se va a rentar
     * @param dias Número de días de renta
     * @return La renta creada
     */
    public Renta registrarRenta(Cliente cliente, Traje traje, int dias) {
        Renta renta = new Renta();
        renta.setId("R" + System.currentTimeMillis());
        renta.setFechaRenta(new Date());
        
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, dias);
        renta.setFechaDevolucionPrevista(calendar.getTime());
        
        renta.setEstado("activo");
        traje.setEstado("rentado");
        
        cliente.agregarRenta(renta);
        
        System.out.println("Renta registrada con éxito para el cliente " + cliente.getNombre());
        return renta;
    }

    /**
     * Registra la devolución de un traje
     * @param renta La renta a devolver
     * @param cliente El cliente que devuelve el traje
     * @param traje El traje que se devuelve
     */
    public void registrarDevolucion(Renta renta, Cliente cliente, Traje traje) {
        renta.setEstado("devuelto");
        renta.setFechaDevolucionReal(new Date());
        traje.setEstado("disponible");
        cliente.finalizarRenta(renta.getId());
        
        System.out.println("Devolución registrada con éxito para el cliente " + cliente.getNombre());
    }

    //
    // Accessor methods
    //

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}