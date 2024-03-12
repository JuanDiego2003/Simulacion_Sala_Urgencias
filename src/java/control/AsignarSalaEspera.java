/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author henrr
 */
public class AsignarSalaEspera {

    public static synchronized void AsignarEspera(List<SalaEspera> listEspera, List<Paciente> listaPacientes) {
        Thread Genpaciente = new Thread(() -> {
            for (SalaEspera espera : listEspera) {
                if (!listaPacientes.isEmpty()) {
                    espera.setPaciente(listaPacientes.get(0));
                    espera.setOcupado(true);
                    listaPacientes.remove(0);
                }
            }
        });
        Genpaciente.start();
    }
}
