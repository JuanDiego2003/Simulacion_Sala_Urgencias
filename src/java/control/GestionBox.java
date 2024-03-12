/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.List;

public class GestionBox {

    public static synchronized void GestionarBoxes(ConcurrentLinkedQueue<Paciente> listaPacientes, ConcurrentLinkedQueue<Especialista> listaEspecialistas,
            ConcurrentLinkedQueue<TecnicoSanitario> listaTecnicoSanitarios, ConcurrentLinkedQueue<Boxes> listaBoxs, ConcurrentLinkedQueue<SalaEspera> listEspera) {

        Object lock = new Object();
        List<Thread> listaThreads = new ArrayList<>();

        for (Boxes box : listaBoxs) {
            Thread Box = new Thread(() -> {
                while (true) {
                    if (box.isOcupado()) {
                        box.setPaciente(listEspera.get(0).getPaciente());
                        listEspera.remove(box.getPaciente());
                    }
                }

            });
            listaThreads.add(Box);
        }

        for (Thread listaThread : listaThreads) {
            listaThread.start();
        }
    }

}
