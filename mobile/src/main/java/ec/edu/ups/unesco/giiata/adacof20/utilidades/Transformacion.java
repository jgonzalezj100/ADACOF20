package ec.edu.ups.siia.adacof.utilidades;

public class Transformacion {
	 
    /**
      * Metodo para convertir el tiempo en milisegundos  
      * Formato Timer 
      * Horas: Minutos: Segundos
     * */
    public String tiempoenMilisegundos(long millisegundos){
        String cadenaTiempofinal = "";
        String secondsString = "";
 
        // Convert total duration into time
           int hours = (int)( millisegundos / (1000*60*60));
           int minutes = (int)(millisegundos % (1000*60*60)) / (1000*60);
           int seconds = (int) ((millisegundos % (1000*60*60)) % (1000*60) / 1000);
           // Add hours if there
           if(hours > 0){
               cadenaTiempofinal = hours + ":";
           }
 
           // Prepending 0 to seconds if it is one digit
           if(seconds < 10){
               secondsString = "0" + seconds;
           }else{
               secondsString = "" + seconds;}
 
           cadenaTiempofinal = cadenaTiempofinal + minutes + ":" + secondsString;
 
        // return timer string
        return cadenaTiempofinal;
    }
 
    /**
     * Función para obtener el porcentaje de progreso
     * @param currentDuration
     * @param totalDuration
     * */
    public int getProgressPercentage(long currentDuration, long totalDuration){
        Double percentage = (double) 0;
 
        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);
 
        // calculating percentage
        percentage =(((double)currentSeconds)/totalSeconds)*100;
 
        // return percentage
        return percentage.intValue();
    }
 
    /**
     * Function to change progress to timer
     * @param progress -
     * @param totalDuration
     * returns current duration in milliseconds
     * */
    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress) / 100) * totalDuration);
 
        // return current duration in milliseconds
        return currentDuration * 1000;
    }
}