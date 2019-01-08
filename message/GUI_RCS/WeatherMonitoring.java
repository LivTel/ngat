package ngat.message.GUI_RCS;


import java.rmi.*;

/** Class to detect weather state changes and make available via RMI interface.*/
public interface WeatherMonitoring extends Remote {

    public static final int WEATHER_UNKNOWN = 0;

    public static final int WEATHER_GOOD = 1;
    
    public static final int WEATHER_BAD = 2;

    /** Implementors should return the weather state.*/
    public int getWeatherState() throws RemoteException;

    /** Implementors should return the length of time the weather has been stable (i.e. good or bad).*/
    public long getWeatherStableTime() throws RemoteException;

}
