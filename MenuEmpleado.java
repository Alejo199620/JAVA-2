import java.util.*;

public class MenuEmpleado {
    private Catalogo catalogo;
    private List<Cliente> clientes;
    private Empleado empleado;
    private Scanner scanner;
    private InformeMorosos informeMorosos;

    public MenuEmpleado(Catalogo catalogo, List<Cliente> clientes, Empleado empleado,
            Scanner scanner, InformeMorosos informeMorosos) {
        this.catalogo = catalogo;
        this.clientes = clientes;
        this.empleado = empleado;
        this.scanner = scanner;
        this.informeMorosos = informeMorosos;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ EMPLEADO ===");
            System.out.println("1. Consultar catálogo de trajes");
            System.out.println("2. Consultar disponibilidad de un traje");
            System.out.println("3. Registrar renta");
            System.out.println("4. Registrar devolución");
            System.out.println("5. Consultar rentas por cliente");
            System.out.println("6. Generar informe de morosos");
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
                    System.out.print("ID del traje a consultar: ");
                    String idTraje = scanner.nextLine();
                    Traje traje = catalogo.buscarTraje(idTraje);
                    if (traje != null) {
                        traje.consultarDisponibilidad();
                    } else {
                        System.out.println("Traje no encontrado.");
                    }
                    break;

                case 3:
                    // CU003 - Registrar Renta
                    registrarRenta();
                    break;

                case 4:
                    // CU004 - Registrar Devolución
                    registrarDevolucion();
                    break;

                case 5:
                    // CU007 - Consultar Rentas por Cliente
                    consultarRentasPorCliente();
                    break;

                case 6:
                    // CU006 - Generar Informe Morosos (manual)
                    System.out.println("\n=== GENERANDO INFORME DE MOROSOS (MANUAL) ===");
                    informeMorosos.generarInforme();
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void registrarRenta() {
        System.out.println("\n=== REGISTRAR RENTA ===");

        // CU005 - Gestionar Cliente (registro si no existe)
        System.out.print("¿El cliente está registrado? (s/n): ");
        String respuesta = scanner.nextLine();

        Cliente cliente;
        if (respuesta.equalsIgnoreCase("n")) {
            cliente = new Cliente();
            System.out.print("ID del cliente: ");
            cliente.setId(scanner.nextLine());
            System.out.print("Nombre: ");
            cliente.setNombre(scanner.nextLine());
            System.out.print("Teléfono: ");
            cliente.setTelefono(scanner.nextLine());
            System.out.print("Email: ");
            cliente.setEmail(scanner.nextLine());

            cliente.registrar();
            clientes.add(cliente);
        } else {
            System.out.print("ID del cliente: ");
            String clienteId = scanner.nextLine();
            cliente = buscarCliente(clientes, clienteId);
            if (cliente == null) {
                System.out.println("Cliente no encontrado.");
                return;
            }
        }

        catalogo.mostrarTrajes();
        System.out.print("ID del traje a rentar: ");
        String idTrajeRenta = scanner.nextLine();
        Traje trajeRenta = catalogo.buscarTraje(idTrajeRenta);

        if (trajeRenta != null && "disponible".equals(trajeRenta.getEstado())) {
            System.out.print("Días de renta: ");
            int dias = scanner.nextInt();

            scanner.nextLine();

            Renta renta = empleado.registrarRenta(cliente, trajeRenta, dias);
            renta.setTraje(trajeRenta); // Asignar el traje a la renta
            // MOSTRAR ID DE LA RENTA
            System.out.println("Renta registrada con éxito. ID de la renta: " + renta.getId());
        } else {
            System.out.println("Traje no disponible o no encontrado.");
        }
    }

    private void registrarDevolucion() {
        System.out.println("\n=== REGISTRAR DEVOLUCIÓN ===");
        System.out.print("ID del cliente: ");
        String clienteDevId = scanner.nextLine();
        Cliente clienteDev = buscarCliente(clientes, clienteDevId);

        if (clienteDev != null && !clienteDev.getRentasActivas().isEmpty()) {
            System.out.println("Rentas activas del cliente:");
            for (Renta renta : clienteDev.getRentasActivas()) {
                if ("activo".equals(renta.getEstado())) {
                    System.out.println("ID Renta: " + renta.getId() +
                            " - Traje: " + renta.getTraje().getNombre() +
                            " - Fecha devolución: " + renta.getFechaDevolucionPrevista());
                }
            }

            System.out.print("ID de la renta a devolver: ");
            String rentaId = scanner.nextLine();

            Renta rentaDev = null;
            Traje trajeDev = null;
            for (Renta r : clienteDev.getRentasActivas()) {
                if (r.getId().equals(rentaId) && "activo".equals(r.getEstado())) {
                    rentaDev = r;
                    trajeDev = r.getTraje();
                    break;
                }
            }

            if (rentaDev != null) {
                empleado.registrarDevolucion(rentaDev, clienteDev, trajeDev);
            } else {
                System.out.println("Renta no encontrada o ya devuelta.");
            }
        } else {
            System.out.println("Cliente no encontrado o no tiene rentas activas.");
        }
    }

    private void consultarRentasPorCliente() {
        System.out.println("\n=== CONSULTAR RENTAS POR CLIENTE ===");
        System.out.print("ID del cliente: ");
        String clienteId = scanner.nextLine();
        Cliente cliente = buscarCliente(clientes, clienteId);

        if (cliente != null) {
            System.out.println("Rentas activas de " + cliente.getNombre() + ":");
            boolean tieneRentas = false;

            for (Renta renta : cliente.getRentasActivas()) {
                if ("activo".equals(renta.getEstado())) {
                    tieneRentas = true;
                    System.out.println("ID Renta: " + renta.getId());
                    System.out.println("Traje: " + renta.getTraje().getNombre());
                    System.out.println("Fecha renta: " + renta.getFechaRenta());
                    System.out.println("Fecha devolución prevista: " + renta.getFechaDevolucionPrevista());
                    System.out.println("-----------------------");
                }
            }

            if (!tieneRentas) {
                System.out.println("El cliente no tiene rentas activas.");
            }
        } else {
            System.out.println("Cliente no encontrado.");
        }
    }

    private Cliente buscarCliente(List<Cliente> clientes, String id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }
}