package control;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.servlet.annotation.WebServlet;
import javax.swing.Box;

@WebServlet("")
public class Controlador extends HttpServlet {

    public static final int BOXES = 10;
    public boolean iniciado = false;
    public volatile List<Paciente> listaPacientes = new ArrayList<>();
    public volatile List<Especialista> listaEspecialistas = new ArrayList<>();
    public volatile List<TecnicoSanitario> listaTecnicoSanitarios = new ArrayList<>();
    public volatile List<Boxes> listaBoxs = new ArrayList<>();
    public volatile List<SalaEspera> listEspera = new ArrayList<>();

    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!iniciado) {
            iniciado = true;
            listaEspecialistas = GenerarEspecialistas();
            listaTecnicoSanitarios = GenerarTecnicoSanitarios();
            listaBoxs = GenerarBoxes();
            listEspera = GenerarEspera();
            listaPacientes = IniciarGeneracionPacientes(listEspera);
            AsignarSalaEspera.AsignarEspera(listEspera, listaPacientes);
            GestionBox.GestionarBoxes(listaPacientes, listaEspecialistas, listaTecnicoSanitarios, listaBoxs, listEspera);
        }

        // Establecer un atributo en el objeto request
        String mensaje = "Hola desde el servlet";
        request.setAttribute("aa", listEspera);
        request.setAttribute("aaa", listaEspecialistas);
        request.setAttribute("aaaa", listaTecnicoSanitarios);
        request.setAttribute("boxs", listaBoxs);

        request.setAttribute("mensajeAtributo", mensaje);
        request.setAttribute("boxes", BOXES);
        // Redirigir a la página JSP
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    public static synchronized List<Paciente> IniciarGeneracionPacientes(List<SalaEspera> listEspera) {
        GeneradorPacientes generadorPacientes = new GeneradorPacientes(BOXES);
        generadorPacientes.GeneradorPacientes(listEspera);
        // Esperar a que se genere la lista antes de continuar
        try {
            Thread.sleep(1000); // Puedes ajustar este valor según tus necesidades
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return generadorPacientes.getListaPacientes();
    }

    public static List<Especialista> GenerarEspecialistas() {
        List<Especialista> listaEspecialistas = new ArrayList<>();
        int cantidadEsp = BOXES / 5;
        for (int i = 0; i < cantidadEsp; i++) {
            //repite los medicos aunque sean menos de 3
            boolean repetir = false;
            do {
                Especialista especialista = new Especialista(i);
                if (listaEspecialistas.contains(especialista.getEspecializacion()) && cantidadEsp < 3) {
                    repetir = true;
                } else {
                    listaEspecialistas.add(especialista);
                }
            } while (repetir);

        }
        return listaEspecialistas;
    }

    public static List<TecnicoSanitario> GenerarTecnicoSanitarios() {
        List<TecnicoSanitario> listaTecnicoSanitarios = new ArrayList<>();
        int cantidadTecnic = (BOXES / 5) * 3;
        for (int i = 0; i < cantidadTecnic; i++) {
            TecnicoSanitario tecnicoSanitario = new TecnicoSanitario(i);
            listaTecnicoSanitarios.add(tecnicoSanitario);
        }
        return listaTecnicoSanitarios;
    }

    private static List<Boxes> GenerarBoxes() {
        List<Boxes> listaBoxs = new ArrayList<>();
        for (int i = 0; i < BOXES; i++) {
            Boxes box = new Boxes(i);
            listaBoxs.add(box);
        }
        return listaBoxs;
    }

    private List<SalaEspera> GenerarEspera() {
        List<SalaEspera> listaEspera = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SalaEspera salaEspera = new SalaEspera(i);
            listaEspera.add(salaEspera);
        }
        return listaEspera;
    }

    public static int hola() {
        Random random = new Random();

        // Generar un número aleatorio entre 0 (inclusive) y 100 (exclusive)
        int numeroAleatorio = random.nextInt(100);

        return numeroAleatorio;
    }

}
