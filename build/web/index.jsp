<%@page import="java.util.concurrent.ConcurrentLinkedQueue"%>
<%@page import="control.Boxes"%>
<%@page import="control.SalaEspera"%>
<%@page import="control.TecnicoSanitario"%>
<%@page import="control.Especialista"%>
<%@page import="control.Paciente"%>
<%@ page import="control.Controlador" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            table {
                border-collapse: collapse;
                border: 2px solid black;
                width: 35%;
            }
            .laterales{
                height: 50px;

                border-left: 2px solid black;
                border-right: 2px solid black;
            }
            #top{
                border-top: 2px solid black;
            }
            #bottom{
                border-bottom: 2px solid black;
            }
            .Urgencias{
                height: 50px;
            }
            .Box{
                border: 2px solid black;

            }
        </style>
    </head>
    <body>

        <%
            String mensaje = (String) request.getAttribute("mensajeAtributo");%>
        <%=Controlador.hola()%>
        <%

            int boxes = Integer.valueOf(request.getAttribute("boxes") + "");
            int fondo = 0;
            if (boxes % 2 == 0) {
                fondo = boxes / 2;
            } else {
                fondo = (boxes + 1) / 2;
            }
        %>
        <%=mensaje%>
        <%
            List<SalaEspera> pacientes = new ArrayList<>((ConcurrentLinkedQueue<SalaEspera>) request.getAttribute("aa"));
        %>
        <%
            List<Especialista> espe = new ArrayList<>((ConcurrentLinkedQueue<Especialista>) request.getAttribute("aaa"));
            for (Especialista es : espe) {%>
        <p><%=es.getEspecializacion()%></p>
        <%}
        %>
        <%
            List<TecnicoSanitario> tec = new ArrayList<>((ConcurrentLinkedQueue<TecnicoSanitario>) request.getAttribute("aaaa"));
            List<Boxes> listBox = new ArrayList<>((ConcurrentLinkedQueue<Boxes>) request.getAttribute("boxs"));

            for (TecnicoSanitario es : tec) {%>
        <p><%=es.getId()%></p>
        <%}
        %>
        <p id="mensaje">chupa</p>
        <div>
            <table>
                <% if (pacientes.size() > 0) {%>
                <tr class="laterales"  id="top">
                    <td>
                        <p>
                            <%=(pacientes.get(0).getPaciente() != null) ? pacientes.get(0).getPaciente().getNombre() : "-"%> <br>
                            <%=(pacientes.get(0).getPaciente() != null) ? "Gravedad" + pacientes.get(0).getPaciente().getGravedad() : ""%>
                        </p>
                    </td>

                    <td>
                        <p>
                            <%=(pacientes.get(1).getPaciente() != null) ? pacientes.get(1).getPaciente().getNombre() : "-"%> <br>
                            <%=(pacientes.get(1).getPaciente() != null) ? "Gravedad" + pacientes.get(1).getPaciente().getGravedad() : ""%>
                        </p>
                    </td>
                    <td>
                        <p>
                            <%=(pacientes.get(2).getPaciente() != null) ? pacientes.get(2).getPaciente().getNombre() : "-"%> <br>
                            <%=(pacientes.get(2).getPaciente() != null) ? "Gravedad" + pacientes.get(2).getPaciente().getGravedad() : ""%>
                        </p>
                    </td>
                </tr>
                <tr class="laterales" id="bottom">4
                    <td>
                        <p>
                            <%=(pacientes.get(3).getPaciente() != null) ? pacientes.get(3).getPaciente().getNombre() : "-"%> <br>
                            <%=(pacientes.get(3).getPaciente() != null) ? "Gravedad:" + pacientes.get(3).getPaciente().getGravedad() : ""%>
                        </p>
                    </td>
                    <td>
                        <p>
                            <%=(pacientes.get(4).getPaciente() != null) ? pacientes.get(4).getPaciente().getNombre() : "-"%> <br>
                            <%=(pacientes.get(4).getPaciente() != null) ? "Gravedad:" + pacientes.get(4).getPaciente().getGravedad() : ""%>
                        </p>
                    </td>
                </tr>
                <%} else {%>
                <tr class="laterales"  id="top">
                    <td>-</td>
                    <td>-</td>
                    <td>-</td>
                </tr>
                <tr class="laterales" id="bottom">4
                    <td>-</td>
                    <td>-</td>
                </tr>
                <%}%>
                <% int idBox = 0;
                    for (int i = 0; i < fondo; i++) {%>
                <tr class="Urgencias">
                    <% for (int j = 0; j < 3; j++) {%>
                    <% if (j % 2 != 0) {%>
                    <td class="pasillo"></td>
                    <%} else {
                        idBox++;
                        if (idBox <= boxes) {
                    %>                                
                    <td class="Box" id="Box<%=idBox%>">
                        <p>
                            <%=(listBox.get(idBox - 1).getPaciente() != null) ? listBox.get(idBox - 1).getPaciente().getNombre() : ""%><br>
                            <%=(listBox.get(idBox - 1).getPaciente() != null) ? "Gravedad:" + listBox.get(idBox - 1).getPaciente().getGravedad() : ""%><br>
                            Cantidad tecnicos:<%=listBox.get(idBox - 1).getTecnicosSanitarios().size()%><br>
                            <%=(listBox.get(idBox - 1).getPaciente() != null) ? listBox.get(idBox - 1).getPaciente().getTiempoAencion() : ""%>
                        </p>
                    </td>
                    <%}
                            }
                        }%>
                </tr>
                <%}%>
            </table>
        </div>

    </body>

</html>
<script>
    //Función para recargar la página cada segundo
    setInterval(function () {
        location.reload();
    }, 500); // 100 milisegundos = 1 segundo
</script>