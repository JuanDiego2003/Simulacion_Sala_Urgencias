/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class GestionBox {

    static boolean a = false;

    public static synchronized void GestionarBoxes(ConcurrentLinkedQueue<Paciente> listaPacientes, ConcurrentLinkedQueue<Especialista> listaEspecialistas,
            ConcurrentLinkedQueue<TecnicoSanitario> listaTecnicoSanitarios, ConcurrentLinkedQueue<Boxes> listaBoxs, ConcurrentLinkedQueue<SalaEspera> listEspera) {

        List<Thread> listaThreads = new ArrayList<>();
        for (Boxes box : listaBoxs) {
            Thread Box = new Thread(() -> {
                while (true) {
                    if (!box.isOcupado() && listEspera.peek().getPaciente() != null) {
                        SalaEspera primerElemento = listEspera.poll(); // Sacamos el primer elemento
                        if (primerElemento.getPaciente() != null) {
                            synchronized (primerElemento) {
                                box.asignarPaciente(primerElemento.getPaciente());
                                box.getPaciente().StartTiempoAencion();
                                primerElemento.liberar();
                            }
                        }
                        listEspera.add(primerElemento); // Lo agregamos de nuevo al final
                    }

                    if (box.getPaciente() != null) {
                        //List<Boxes> listaBoxesOrdenada = new ArrayList<>(listaBoxs);
                        switch (box.getPaciente().getGravedad()) {
                            case 0:
                                int timeatencion = 500;
                                int auxNecesarios = 1;
                                while (auxNecesarios != 0) {
                                    TecnicoSanitario tecnicoSanitario = listaTecnicoSanitarios.poll();
                                    if (tecnicoSanitario.isLibre()) {
                                        box.getTecnicosSanitarios().add(tecnicoSanitario);
                                        tecnicoSanitario.setLibre(false);
                                        auxNecesarios--;
                                        TiempoAtencion(timeatencion);
                                        tecnicoSanitario.setLibre(true);
                                        box.liberarBox();
                                    }
                                    listaTecnicoSanitarios.add(tecnicoSanitario);
                                }
                                break;
                            default:
                                break;
                        }
                    }
                }
            });
            listaThreads.add(Box);
        }
        for (Thread listaThread : listaThreads) {
            listaThread.start();
        }
        System.out.println("");
    }

    public static void AsignacionBoxes(Boxes box, ConcurrentLinkedQueue<SalaEspera> listEspera) {

        System.out.println("");

    }

    public static synchronized void TiempoAtencion(int milisegundos) {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
