/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    Sara López Vicente 
 * 
 */
public class Podometro {
    //constantes
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';

    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;

    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //atributos
    private String marca; //nombre de la marca
    private double altura; //en centímetros
    private char sexo;    
    private double longitudZancada; //en centímetros

    private int totalPasosLaborables; //nº pasos en laborables
    private int totalPasosSabado; //nº pasos sábado
    private int totalPasosDomingo; //nº pasos domingo
    private double totalDistanciaSemana; //distancia en km/semana
    private double totalDistanciaFinSemana; 

    private int tiempo; //tiempo total en minutos
    private int caminatasNoche; //nº caminatas a partir de 21h

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;      

    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if (sexo == MUJER) {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }
        else if (sexo == HOMBRE) {
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else {
            System.out.println("Error, sexo no válido");
        }        
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFin – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) { 
        //revisar
        double distancia = pasos * longitudZancada / 100000;                     
        totalDistanciaSemana += distancia;
        //calcular las horas caminadas
        //al restar lo hace en formato decimal y dará horas erróneas
        int horas = (horaFin - horaInicio) / 100;
        int minutos = (horaFin - horaInicio) % 100;
        tiempo = horas * 3600 + minutos;
        
        //caminatas por la noche a partir de las 21;00, entendiendo
        //que pueden comenzar antes de esta hora pero acabar más tarde
        if (horaInicio >= 2100 || horaFin > 2100) {
            caminatasNoche ++;
        }
        
        switch (dia) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
            break;
            case SABADO: totalPasosSabado += pasos;
            totalDistanciaFinSemana += distancia;
            break;
            case DOMINGO: totalPasosDomingo += pasos;
            totalDistanciaFinSemana += distancia;
        }
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        double alturaMts = altura / 100;
        double longitudZancadaMts = longitudZancada / 100;
        String queSexo;
        if (sexo == MUJER) {
            queSexo = "MUJER";
        }
        else {
            queSexo = "HOMBRE";
        }
        System.out.println("Configuración del podómetro\n*********************************"
            + "\nAltura: " + alturaMts + " mtos\nSexo: " + queSexo +
            "\nLongitud zancada: " + longitudZancadaMts + " mtos");
    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadisticas() {
        //revisar
        
        System.out.println("Estadísticas\n*********************************"
            + "\nDistancia recorrida toda la semana: " + totalDistanciaSemana +
            " Km\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana
            + " Km" + "\n\nNº pasos días laborables: " + totalPasosLaborables +
            "\nNº pasos SÁBADO: " + totalPasosSabado + "\nNº pasos DOMINGO: " +
            totalPasosDomingo + "\n\nNº caminatas realizadas a partir de las 21h: "
            + caminatasNoche + "\n\nTiempo total caminado en la semana: " + tiempo +
            "\nDía/s con más pasos caminados: " + diaMayorNumeroPasos());

    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMasPasos;
        if (totalPasosLaborables > totalPasosSabado && totalPasosLaborables> totalPasosSabado) {
            diaMasPasos = "LABORABLES";
        }
        else if (totalPasosSabado > totalPasosDomingo) {
            diaMasPasos = "SÁBADO";
        }
        else {
            diaMasPasos = "DOMINGO";
        }
        return diaMasPasos;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
