package control;

import java.util.Random;

public class Especialista {

    private int id = 0;
    private String especializacion = "";
    private boolean libre=true;

    public Especialista(int id) {
        this.id = id+1;
        String[] especialidades = {"cirugia", "traumatologia", "diagnostico"};
        Random random = new Random();
        this.especializacion = especialidades[random.nextInt(especialidades.length)];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEspecializacion() {
        return especializacion;
    }

    public void setEspecializacion(String especializacion) {
        this.especializacion = especializacion;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }

}
