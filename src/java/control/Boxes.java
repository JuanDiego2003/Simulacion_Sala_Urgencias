package control;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Boxes {

    private int idBox;
    private volatile Paciente paciente;
    private volatile ConcurrentLinkedQueue<Especialista> especialista = new ConcurrentLinkedQueue<>();
    private volatile ConcurrentLinkedQueue<TecnicoSanitario> tecnicoSanitario = new ConcurrentLinkedQueue<>();
    private volatile boolean ocupado=false;

    public Boxes(int idBox) {
        this.idBox = idBox + 1;
    }

    public int getIdBox() {
        return idBox;
    }

    public void setIdBox(int idBox) {
        this.idBox = idBox;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public ConcurrentLinkedQueue<Especialista> getEspecialistas() {
        return especialista;
    }

    public void setEspecialistas(ConcurrentLinkedQueue<Especialista> especialistas) {
        this.especialista = especialistas;
    }

    public ConcurrentLinkedQueue<TecnicoSanitario> getTecnicosSanitarios() {
        return tecnicoSanitario;
    }

    public void setTecnicosSanitarios(ConcurrentLinkedQueue<TecnicoSanitario> tecnicosSanitarios) {
        this.tecnicoSanitario = tecnicosSanitarios;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    public void asignarPaciente(Paciente paciente) {
        this.paciente = paciente;
        this.ocupado = true;
    }

    public void liberarBox() {
        this.paciente = null;
        this.ocupado = false;
        this.especialista.clear();
        this.tecnicoSanitario.clear();
    }
}
