/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GestionBox {

    static boolean a = false;

    public static synchronized void GestionarBoxes(ConcurrentLinkedQueue<Paciente> listaPacientes, ConcurrentLinkedQueue<Especialista> listaEspecialistas,
            ConcurrentLinkedQueue<TecnicoSanitario> listaTecnicoSanitarios, ConcurrentLinkedQueue<Boxes> listaBoxs, ConcurrentLinkedQueue<SalaEspera> listEspera) {

        List<Thread> listaThreads = new ArrayList<>();
        for (Boxes box : listaBoxs) {
            Thread Box = new Thread(() -> {
                while (true) {
                    if (!box.isOcupado() && listEspera.peek().getPaciente() != null) {
                        boolean entro = false;
                        for (SalaEspera salaEspera : listEspera) {
                            synchronized (salaEspera) {
                                if (salaEspera.isOcupado() && !entro && !box.isOcupado()) {
                                    a = true;
                                    entro = true;
                                    box.asignarPaciente(salaEspera.getPaciente());
                                    salaEspera.liberar();
                                }
                            }
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

}
