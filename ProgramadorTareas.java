import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.util.Calendar;

public class ProgramadorTareas {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private InformeMorosos informeMorosos;

    public ProgramadorTareas(InformeMorosos informeMorosos) {
        this.informeMorosos = informeMorosos;
    }

    public void programarInformeDiario() {
        // Calcular el tiempo hasta las 9:00 a.m. del próximo día
        Calendar now = Calendar.getInstance();
        Calendar nextRun = Calendar.getInstance();
        nextRun.set(Calendar.HOUR_OF_DAY, 9);
        nextRun.set(Calendar.MINUTE, 0);
        nextRun.set(Calendar.SECOND, 0);
        nextRun.set(Calendar.MILLISECOND, 0);
        
        if (now.after(nextRun)) {
            nextRun.add(Calendar.DATE, 1);
        }
        
        long initialDelay = nextRun.getTimeInMillis() - now.getTimeInMillis();
        
        // Programar la tarea para ejecutarse diariamente a las 9:00 a.m.
        scheduler.scheduleAtFixedRate(
            this::ejecutarInformeAutomatico,
            initialDelay,
            TimeUnit.DAYS.toMillis(1),
            TimeUnit.MILLISECONDS
        );
        
        System.out.println("\nInforme automático de morosos programado para ejecutarse diariamente a las 9:00 a.m.");
        System.out.println("Próxima ejecución: " + nextRun.getTime());
    }

    private void ejecutarInformeAutomatico() {
        System.out.println("\n=== EJECUTANDO INFORME AUTOMÁTICO DE MOROSOS ===");
        System.out.println("Fecha y hora: " + new Date());
        
        informeMorosos.generarInforme();
    }

    public void detener() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }


//Automatico 1 minuto prueba de ejecución

//     import java.util.concurrent.Executors;
// import java.util.concurrent.ScheduledExecutorService;
// import java.util.concurrent.TimeUnit;
// import java.util.Date;

// public class ProgramadorTareas {
//     private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
//     private InformeMorosos informeMorosos;

//     public ProgramadorTareas(InformeMorosos informeMorosos) {
//         this.informeMorosos = informeMorosos;
//     }

//     // Versión TEMPORAL para pruebas (ejecución inmediata + cada minuto)
//     public void programarInformeDiario() {
//         // 1. Ejecutar inmediatamente
//         ejecutarInformeAutomatico();
        
//         // 2. Programar para repetir cada minuto (solo pruebas)
//         scheduler.scheduleAtFixedRate(
//             this::ejecutarInformeAutomatico,
//             1,                      // Comenzar en 1 minuto
//             1,                      // Repetir cada 1 minuto
//             TimeUnit.MINUTES         // Unidad: minutos
//         );
        
//         System.out.println("Modo prueba: Informe ejecutado AHORA y se repetirá cada minuto");
//     }

//     private void ejecutarInformeAutomatico() {
//         System.out.println("\n=== EJECUTANDO INFORME AUTOMÁTICO ===");
//         System.out.println("Hora actual: " + new Date());
//         informeMorosos.generarInforme();
//     }

//     public void detener() {
//         scheduler.shutdown();
//         try {
//             if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
//                 scheduler.shutdownNow();
//             }
//         } catch (InterruptedException e) {
//             scheduler.shutdownNow();
//             Thread.currentThread().interrupt();
//         }
//     }
    

}
