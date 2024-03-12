/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.ArrayList;
import java.util.List;

public class GestionBox {

    public static synchronized void GestionarBoxes(List<Paciente> listaPacientes, List<Especialista> listaEspecialistas,
            List<TecnicoSanitario> listaTecnicoSanitarios, List<Boxes> listaBoxs, List<SalaEspera> listEspera) {

        Object lock = new Object();
        List<Thread> listaThreads = new ArrayList<>();
        synchronized (listaBoxs) {
            for (int i = 0; i < listaBoxs.size(); i++) {
                final int indice = i;
                Thread Box = new Thread(() -> {
                    Boxes box = listaBoxs.get(indice);
                    if (isOcupado(box)) {
                        box.setPaciente(listEspera.get(0).getPaciente());
                        listEspera.remove(box.getPaciente());
                    }
                });
                listaThreads.add(Box);
            }
        }
        for (Thread listaThread : listaThreads) {
            listaThread.start();
        }
    }

    public static synchronized boolean isOcupado(Boxes box) {
        if (!box.isOcupado()) {
            return false;
        } else {
            return true;
        }
    }

}
