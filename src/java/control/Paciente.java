package control;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class Paciente {

    private volatile int id = 0;
    private volatile String nombre = "";
    private volatile int gravedad = 0;
    private volatile int tiempoAencion = 0;
    private volatile ConcurrentLinkedQueue<String> necesidad = new ConcurrentLinkedQueue<>();

    public Paciente(int id, String nombre, int gravedad, List<String> especialidades) {
        this.id = id;
        this.nombre = nombre;
        this.gravedad = gravedad;
        Random random = new Random();
        this.necesidad.add(especialidades.get(random.nextInt(especialidades.size())));

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    public int getTiempoAencion() {
        return tiempoAencion;
    }

    public synchronized void StartTiempoAencion() {
        Thread time = new Thread(() -> {
            while (true) {

                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.tiempoAencion++;
            }

        });
        time.start();
    }

}
