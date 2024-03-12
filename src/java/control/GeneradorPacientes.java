package control;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class GeneradorPacientes {

    private static int cont = 0;
    private static boolean finalizar = false;
    private static int boxes = 0;
    private static volatile List<Paciente> listaPacientes = new ArrayList<>();

    public GeneradorPacientes(int boxes) {
        this.boxes = boxes;
    }

    public static synchronized void GeneradorPacientes(List<SalaEspera> listEspera) {
        contador();
        Thread Genpaciente = new Thread(() -> {
            int num = 0;
            Random random = new Random();
            while (!finalizar) {
                int cantGenerar = random.nextInt(5) + 1;
                for (int i = 0; i < cantGenerar; i++) {
                    if (finalizar) {
                        break; // Salir del bucle si finalizar es true
                    }
                    num++;
                    int gravedadPaciente = random.nextInt(6) + 1;
                    Paciente paciente = new Paciente(num, "Paciente " + num, gravedadPaciente);
                    synchronized (listaPacientes) {
                        listaPacientes.add(paciente);
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1); // Esperar 1 segundo antes de la siguiente iteración
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        );
        Genpaciente.start();
    }

    public static synchronized void contador() {
        Thread contador = new Thread(() -> {
            while (cont < 24) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    cont++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            finalizar = true;
        });
        contador.start();
    }

    public static List<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public static void setListaPacientes(List<Paciente> listaPacientes) {
        GeneradorPacientes.listaPacientes = listaPacientes;
    }
}
