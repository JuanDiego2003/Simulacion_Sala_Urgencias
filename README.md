# hosp_jj
Programa realizado para simular la sala de urgencias de un hospital con diferentes tipos de médicos y diferentes salas de atención (Sala de atención = Box), utilizando hilos de procesos para un funcionamiento independiente en diferentes boxes y tareas.

# Parámetros a tener en cuenta en su realización
El servicio de urgencias de un hospital tiene un número entre 1 y 20 de boxes para atender a pacientes. En cada box puede haber 1 único paciente, pero pueden trabajar hasta tres médicos especialistas y tres técnicos sanitarios.

La sala de espera del servicio de urgencias es de 5·(#boxes) del servicio de urgencias. Los pacientes llegan y esperan en la sala hasta entrar a un box donde son atendidos. Si la sala de espera está llena, se envían a otro hospital.

El servicio de urgencias dispone de un médico especialista por cada 5 boxes. Los médicos hacen turnos de 8 horas (8 segundos en la simulación). Existen tres especialidades diferentes: cirugía, traumatología y diagnóstico.

El servicio de urgencias dispone de tres técnicos sanitarios por cada 5 boxes.

Los pacientes, que aparecen aleatoriamente hasta un máximo de 5 por segundo, padecen enfermedades graves y de tipo diferente. Para simular esto, utilizaremos un entero con los siguientes valores:

0: El paciente no tiene nada grave. Puede ser atendido por un técnico sanitario en un box, que ocupa hasta que es atendido durante 0,5 segundos.

1: El paciente sufre algún tipo de enfermedad poco grave. Necesita un especialista (determina el tipo de esta necesidad al azar) y un técnico sanitario. Ocupa un box hasta que es atendido durante 1 segundo.

2: El paciente padece alguna enfermedad grave. Necesita dos especialistas (determina las dos especialidades al azar, pueden ser la misma dos veces). Necesita un médico especialista con un técnico sanitario para cada necesidad. Estas pueden ser atendidas concurrentemente o secuencialmente, dependiendo de la disponibilidad de médicos. Ocupa un box hasta que es atendido durante 1 segundo por especialidad.

3: El paciente sufre alguna enfermedad muy grave. Necesita tres especialistas (determina las tres especialidades al azar, pueden ser la misma tres veces). Necesita un médico especialista con un técnico sanitario para cada necesidad. Estas pueden ser atendidas concurrentemente o secuencialmente, dependiendo de la disponibilidad de médicos. Ocupa un box hasta que es atendido durante 1 segundo por especialidad.

4: El paciente está en estado crítico. Necesita de cuatro a seis especialistas, con un máximo de tres veces cada una de estas especialidades. Además, si no mejora en un día, posiblemente empeorará. Si en 24 horas (24 segundos) no tiene tres o menos necesidades, pasará a tener severidad 5. Ocupa un box hasta que es atendido durante 1 segundo por especialidad.

5: ¿Hay algún médico en la sala? Este paciente tiene de cuatro a seis necesidades, como en el caso anterior, pero si no mejora en 24 horas (24 segundos en la simulación), morirá. Ocupa el box hasta que es atendido durante 1 segundo por especialidad o muere.

Los pacientes pueden ser atendidos según las necesidades, posibilidades y disponibilidad del servicio de urgencias. Para simular esto, los pacientes pueden tener valores de prioridad (como tratar primero a los pacientes más graves). La aplicación muestra el progreso en pantalla y también en un archivo de registro.

# Extra realizado
El programa no necesariamente tenía que tener interfaz gráfica, pero para poder mostrar mejor todo lo que pasaba en el hospital, decidí hacer la interfaz gráfica usando JSP, obteniendo los datos de clases Java.
