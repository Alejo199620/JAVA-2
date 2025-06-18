import java.util.*;

public class MenuCliente {
    private Catalogo catalogo;
    private Scanner scanner;

    public MenuCliente(Catalogo catalogo, Scanner scanner) {
        this.catalogo = catalogo;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ CLIENTE ===");
            System.out.println("1. Consultar catálogo de trajes");
            System.out.println("2. Consultar disponibilidad de un traje");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    // CU001 - Consultar Catálogo
                    catalogo.mostrarTrajes();
                    break;
                    
                case 2:
                    // CU002 - Consultar Disponibilidad
                    System.out.print("Ingrese el ID del traje: ");
                    String trajeId = scanner.nextLine();
                    Traje traje = catalogo.buscarTraje(trajeId);
                    if (traje != null) {
                        traje.consultarDisponibilidad();
                    } else {
                        System.out.println("Traje no encontrado.");
                    }
                    break;
                    
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }
}