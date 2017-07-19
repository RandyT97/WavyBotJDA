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
        String parsed = parseFieldsArray(fieldsArray, currently);
        e.getTextChannel().sendMessage(parsed).queue();


        //Data Barfer
        for(int i=0; i<fieldsArray.length; i++) {
            System.out.println(fieldsArray[i] + ": "+ currently.get().getByKey(fieldsArray[i]));
//            e.getTextChannel().sendMessage(fieldsArray[i] + ": " + currently.get().getByKey(fieldsArray[i])).queue();
        }



    }
    public void executed(boolean success, MessageReceivedEvent event) {

    }
    public Boolean called(String[] args, MessageReceivedEvent event) {return true;}

    private String parseFieldsArray(String [] fieldsArray, FIOCurrently currently) {
        String parsedString = "Current Information: \n";
        FIODataPoint data;
        String label;
        String curr;

        curr = currently.get().getByKey("summary");
        parsedString = parsedString.concat(curr + "\n");

        //precip Probability
        curr = currently.get().getByKey("precipProbability");
        parsedString = parsedString.concat("Probability of Precipitation: " + curr + "% \n");
//        Cannot implement precipitation intensity: Units unknown
//            if(Integer.parseInt(curr)!=0) {
//                label = fieldsArray[3];
//                curr = currently.get().getByKey(label);
//                parsedString = "    Intensity: " + curr +
//            }

        //Visibility
        curr = currently.get().getByKey("visibility");
        parsedString = parsedString.concat("Current Visibility: " + curr + " mi\n");
//
        //Cloud Cover
        curr = currently.get().getByKey("cloudCover");
        parsedString = parsedString.concat("Cloud Cover: " + curr + " oktas\n");
//
        //Wind Speed
        curr = currently.get().getByKey("windSpeed");
        parsedString = parsedString.concat("Wind Speed: " + curr + " mph\n");

//          Wind bearing needs a conversion factor into a cardinal direction
//            label = fieldsArray[6];
//            curr = currently.get().getByKey(label);
//            parsedString = parsedString.concat("Windbearing: " + curr + " \n");
//
        //Temperature
        curr = currently.get().getByKey("temperature");
        parsedString = parsedString.concat("Temperature: " + curr + " degrees\n");

        //Apparent Temperature
        curr = currently.get().getByKey("apparentTemperature");
        parsedString = parsedString.concat("Apparent Temperature: " + curr + " degrees\n");

        //Pressure
        curr = currently.get().getByKey("pressure");
        parsedString = parsedString.concat("Pressure: " + curr + " mb\n");

        //Humidity
        curr = currently.get().getByKey("humidity");
        double mid = Double.parseDouble(curr)*100;
        curr = Double.toString(mid);
        curr = (curr.replaceAll("0\\.",""));
        parsedString = parsedString.concat("Humidity: " + curr + "%\n");

        //Dew Point
        curr = currently.get().getByKey("dewPoint");
        parsedString = parsedString.concat("Dew Point: " + curr + " degrees\n");

//            needs units before implementation
//            label = fieldsArray[11];
//            curr = currently.get().getByKey(label);
//            parsedString = parsedString.concat("Nearest Storm Bearing: " + curr + "\n");

        //Nearest Storm
        curr = currently.get().getByKey("nearestStormDistance");
        parsedString = parsedString.concat("Nearest Storm: " + curr + " mi \n");

        //UV Index
        curr = currently.get().getByKey("uvIndex");
        parsedString = parsedString.concat("UV Index: "+ curr);

        return parsedString;
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
