package control;

import java.util.ArrayList;
import java.util.List;

public class Boxes {

    private int idBox;
    private Paciente paciente;
    private List<Especialista> especialista = new ArrayList<>();
    private List<TecnicoSanitario> tecnicoSanitario = new ArrayList<>();
    private boolean ocupado=false;

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

    public List<Especialista> getEspecialistas() {
        return especialista;
    }

    public void setEspecialistas(List<Especialista> especialistas) {
        this.especialista = especialistas;
    }

    public List<TecnicoSanitario> getTecnicosSanitarios() {
        return tecnicoSanitario;
    }

    public void setTecnicosSanitarios(List<TecnicoSanitario> tecnicosSanitarios) {
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
