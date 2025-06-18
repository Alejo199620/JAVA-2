import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        // Inicialización del sistema
        Catalogo catalogo = new Catalogo();
        List<Cliente> clientes = new ArrayList<>();

        // ===== DATOS DE PRUEBA PARA CLIENTE MOROSO =====
        Cliente clienteMoroso = new Cliente();
        clienteMoroso.setId("C999");
        clienteMoroso.setNombre("Juan Pérez (Cliente Moroso)");
        clienteMoroso.setTelefono("555-1234");
        clienteMoroso.setEmail("juan@moroso.com");
        
        // Crear renta atrasada (debería haberse devuelto hace 2 días)
        Renta rentaAtrasada = new Renta();
        rentaAtrasada.setId("RMOROSO");
        rentaAtrasada.setTraje(catalogo.getTrajes().get(0)); // Usa el primer traje del catálogo
        rentaAtrasada.setFechaRenta(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(5))); // Rentó hace 5 días
        rentaAtrasada.setFechaDevolucionPrevista(new Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(2))); // Debía devolver hace 2 días
        rentaAtrasada.setEstado("activo"); // Todavía no ha devuelto
        
        clienteMoroso.agregarRenta(rentaAtrasada);
        clientes.add(clienteMoroso);
        // ===== FIN DATOS DE PRUEBA PARA CLIENTE MOROSO =====

        // Crear un empleado
        Empleado empleado = new Empleado();
        empleado.setId("E001");
        empleado.setNombre("Alejandro Cuellar");
        empleado.setUsuario("acuellar");
        empleado.setContrasena("1234");

        // Crear el generador de informes
        InformeMorosos informeMorosos = new InformeMorosos(clientes);
        
        // Iniciar el programador de tareas
        ProgramadorTareas programador = new ProgramadorTareas(informeMorosos);
        programador.programarInformeDiario();
        
        // Agregar shutdown hook para cerrar el programador correctamente
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Deteniendo programador de tareas...");
            programador.detener();
        }));
        
        // Menú principal
        Scanner scanner = new Scanner(System.in);
        MenuCliente menuCliente = new MenuCliente(catalogo, scanner);
        MenuEmpleado menuEmpleado = new MenuEmpleado(catalogo, clientes, empleado, scanner, informeMorosos);
        
        int opcion;
        do {
            System.out.println("\n=== SISTEMA DE ALQUILER DE TRAJES DINOSO ===");
            System.out.println("1. Menú Cliente");
            System.out.println("2. Menú Empleado");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch (opcion) {
                case 1:
                    menuCliente.mostrarMenu();
                    break;
                    
                case 2:
                    // Autenticación simple para empleados
                    System.out.print("Usuario: ");
                    String usuario = scanner.nextLine();
                    System.out.print("Contraseña: ");
                    String contrasena = scanner.nextLine();
                    
                    if (usuario.equals(empleado.getUsuario()) && contrasena.equals(empleado.getContrasena())) {
                        menuEmpleado.mostrarMenu();
                    } else {
                        System.out.println("Credenciales incorrectas.");
                    }
                    break;
                    
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                    
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
        
        scanner.close();
    }
}