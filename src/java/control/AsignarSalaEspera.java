/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author henrr
 */
public class AsignarSalaEspera {

    public static synchronized void AsignarEspera(ConcurrentLinkedQueue<SalaEspera> listEspera, ConcurrentLinkedQueue<Paciente> listaPacientes) {
        Thread Genpaciente = new Thread(() -> {
            while (true) {
                for (SalaEspera espera : listEspera) {
                    if (!listaPacientes.isEmpty() && !espera.isOcupado()) {
                        espera.setPaciente(listaPacientes.poll());
                        espera.setOcupado(true);
                    }
                }
            }
        });
        Genpaciente.start();
        System.out.println("");
    }
}
