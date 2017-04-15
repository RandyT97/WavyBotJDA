package Jawyuhz.DiscordBot.commands;

import Jawyuhz.DiscordBot.Command;
import com.github.dvdme.ForecastIOLib.FIOCurrently;
import com.github.dvdme.ForecastIOLib.FIODataPoint;
import com.github.dvdme.ForecastIOLib.ForecastIO;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import static com.github.dvdme.ForecastIOLib.ForecastIO.UNITS_US;

/**
 * Created by Randy on 4/14/2017.
 */
public class Weather implements Command {
    private final static String api = "6a8fa90f31b6872a719c2c8ed0d61b1c";
    private ForecastIO fio;
    public void action(String args[], MessageReceivedEvent event) {
        try {
            getCurrent(event);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
    private void getCurrent(MessageReceivedEvent e) throws InterruptedException {
        //Make it a pinned post that updates
        fio = new ForecastIO(api);
        fio.setUnits(UNITS_US);
        fio.getForecast("28.406339", "-81.447637");
        FIOCurrently currently = new FIOCurrently(fio);
        String [] fieldsArray = currently.get().getFieldsArray();
        e.getTextChannel().sendMessage(parseFieldsArray(fieldsArray, currently)).queue();
//        for(int i=0; i<fieldsArray.length; i++) {
//            e.getTextChannel().sendMessage(fieldsArray[i] + ": " + currently.get().getByKey(fieldsArray[i])).queue();
//            Thread.sleep(500);
//        }
    }
    public void executed(boolean success, MessageReceivedEvent event) {

    }
    public Boolean called(String[] args, MessageReceivedEvent event) {return true;}

    private String parseFieldsArray(String [] fieldsArray, FIOCurrently currently) {
        String parsedString = "Current Information: \n";
        FIODataPoint data;
        String label;
        String curr;
        if(fieldsArray.length==17) {

            label = fieldsArray[0];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat(curr + " skies" + "\n");

            label = fieldsArray[1];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Probability of Precipitation: " + curr + "% \n");
//            Cannot implement precipitation intensity: Units unknown
//            if(Integer.parseInt(curr)!=0) {
//                label = fieldsArray[3];
//                curr = currently.get().getByKey(label);
//                parsedString = "    Intensity: " + curr +
//            }


            label = fieldsArray[2];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Current Visibility: " + curr + " mi\n");

            label = fieldsArray[5];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Cloud Cover: " + curr + " oktas\n");

            label = fieldsArray[16];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Wind Speed: " + curr + " mph\n");

//          Wind bearing needs a conversion factor into a cardinal direction
//            label = fieldsArray[6];
//            curr = currently.get().getByKey(label);
//            parsedString = parsedString.concat("Windbearing: " + curr + " \n");

            label = fieldsArray[13];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Temperature: " + curr + " degrees\n");

            label = fieldsArray[7];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Apparent Temperature: " + curr + " degrees\n");

            label = fieldsArray[8];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Pressure: " + curr + " mb\n");

            label = fieldsArray[14];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Humidity: " + curr + "%\n");

            label = fieldsArray[9];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Dew Point: " + curr + " degrees\n");

//            needs units before implementation
//            label = fieldsArray[11];
//            curr = currently.get().getByKey(label);
//            parsedString = parsedString.concat("Nearest Storm Bearing: " + curr + "\n");

            label = fieldsArray[12];
            curr = currently.get().getByKey(label);
            parsedString = parsedString.concat("Nearest Storm: " + curr + " mi \n");







            return parsedString;



        }




        return null;
    }
//  http://climate.umn.edu/snow_fence/components/winddirectionanddegreeswithouttable3.htm
//    private int convertBearing(String bearing) {
//        int degrees = Integer.parseInt(bearing);
//
//
//
//
//    }
    public void help(MessageReceivedEvent event) {

    }
}
