package control;

public class Paciente {
    private int id= 0;
    private String nombre="";
    private int gravedad= 0;

    public Paciente(int id, String nombre, int gravedad) {
        this.id = id;
        this.nombre = nombre;
        this.gravedad = gravedad;
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
    
    
    
    
    
}
