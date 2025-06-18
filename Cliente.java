import java.util.*;

/**
 * Class Cliente
 */
public class Cliente {
    private String id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String email;
    private List<Renta> rentasActivas;

    //
    // Constructors
    //
    public Cliente() {
        rentasActivas = new ArrayList<>();
    }

    //
    // Methods
    //

    /**
     * Registra un nuevo cliente en el sistema
     */
    public void registrar() {
        System.out.println("Cliente " + nombre + " registrado con éxito.");
    }

    /**
     * Agrega una renta a las rentas activas del cliente
     */
    public void agregarRenta(Renta renta) {
        rentasActivas.add(renta);
    }

    /**
     * Finaliza una renta (marcar como devuelta)
     */
    public void finalizarRenta(String rentaId) {
        for (Renta renta : rentasActivas) {
            if (renta.getId().equals(rentaId)) {
                renta.setEstado("devuelto");
                renta.setFechaDevolucionReal(new Date());
                break;
            }
        }
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Renta> getRentasActivas() {
        return rentasActivas;
    }

    public void setRentasActivas(List<Renta> rentasActivas) {
        this.rentasActivas = rentasActivas;
    }
}