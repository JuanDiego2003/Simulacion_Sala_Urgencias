package control;

public class TecnicoSanitario {
    private int id=0;
    private volatile boolean libre=true;

    public TecnicoSanitario(int id) {
        this.id=id+1;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLibre() {
        return libre;
    }

    public void setLibre(boolean libre) {
        this.libre = libre;
    }
    
}
