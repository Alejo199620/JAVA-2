

/**
 * Class Traje
 */
public class Traje {
    private String id;
    private String nombre;
    private String clasificacion;
    private String tipoTela;
    private String diseno;
    private String estado;

    //
    // Constructors
    //
    public Traje() { }

    //
    // Methods
    //

    /**
     * Consulta la disponibilidad del traje
     */
    public void consultarDisponibilidad() {
        System.out.println("\nInformación de disponibilidad:");
        System.out.println("Traje: " + nombre);
        System.out.println("Estado: " + estado);
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

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipoTela() {
        return tipoTela;
    }

    public void setTipoTela(String tipoTela) {
        this.tipoTela = tipoTela;
    }

    public String getDiseno() {
        return diseno;
    }

    public void setDiseno(String diseno) {
        this.diseno = diseno;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}