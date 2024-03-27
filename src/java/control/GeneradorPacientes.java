package control;

import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GeneradorPacientes {

    private static int cont = 0;
    private static boolean finalizar = false;
    private static int boxes = 0;
    private static volatile ConcurrentLinkedQueue<Paciente> listaPacientes = new ConcurrentLinkedQueue<>();

    public GeneradorPacientes(int boxes) {
        this.boxes = boxes;
    }

    public static synchronized void GeneradorPacientes(ConcurrentLinkedQueue<SalaEspera> listEspera, ConcurrentLinkedQueue<Especialista> listEspecialistas) {
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
                    int gravedadPaciente = random.nextInt(1);
                    List<String> necesidadesPuedenAtender = new ArrayList<>();
                    if (gravedadPaciente != 0) {
                        for (Especialista listEspecialista : listEspecialistas) {
                            necesidadesPuedenAtender.add(listEspecialista.getEspecializacion());
                        }
                    } else {
                        necesidadesPuedenAtender.add("");
                    }
                    Paciente paciente = new Paciente(num, "Paciente " + num, gravedadPaciente, necesidadesPuedenAtender);
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

    public static ConcurrentLinkedQueue<Paciente> getListaPacientes() {
        return listaPacientes;
    }

    public static void setListaPacientes(ConcurrentLinkedQueue<Paciente> listaPacientes) {
        GeneradorPacientes.listaPacientes = listaPacientes;
    }
}
