import java.util.*;

/**
 * Class Catalogo
 */
public class Catalogo {
    private List<Traje> trajes;

    //
    // Constructors
    //
    public Catalogo() {
        trajes = new ArrayList<>();
        // Inicializar con algunos trajes de ejemplo
        Traje t1 = new Traje();
        t1.setId("T001");
        t1.setNombre("Traje Ejecutivo Negro");
        t1.setClasificacion("diario");
        t1.setTipoTela("Lana");
        t1.setDiseno("Diseño clásico de dos botones");
        t1.setEstado("disponible");
        
        Traje t2 = new Traje();
        t2.setId("T002");
        t2.setNombre("Traje de Gala");
        t2.setClasificacion("ocasional");
        t2.setTipoTela("Seda");
        t2.setDiseno("Diseño elegante con solapa de pico");
        t2.setEstado("disponible");
        
        trajes.add(t1);
        trajes.add(t2);
    }

    //
    // Methods
    //

    /**
     * Muestra todos los trajes disponibles en el catálogo
     */
    public void mostrarTrajes() {
        System.out.println("\n=== CATÁLOGO DE TRAJES ===");
        for (Traje traje : trajes) {
            System.out.println("ID: " + traje.getId());
            System.out.println("Nombre: " + traje.getNombre());
            System.out.println("Clasificación: " + traje.getClasificacion());
            System.out.println("Tipo de tela: " + traje.getTipoTela());
            System.out.println("Diseño: " + traje.getDiseno());
            System.out.println("Estado: " + traje.getEstado());
            System.out.println("-----------------------");
        }
    }

    /**
     * Busca un traje por ID
     * @param id ID del traje a buscar
     * @return El traje encontrado o null si no existe
     */
    public Traje buscarTraje(String id) {
        for (Traje traje : trajes) {
            if (traje.getId().equals(id)) {
                return traje;
            }
        }
        return null;
    }

    //
    // Accessor methods
    //

    public List<Traje> getTrajes() {
        return trajes;
    }

    public void setTrajes(List<Traje> trajes) {
        this.trajes = trajes;
    }
}