package Jawyuhz.DiscordBot.utils;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.ArrayList;
//import java.util.logging.ConsoleHandler;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * Created by Randy on 4/7/2017.
 */
public class CommandParser {
    public CommandContainer parse(String rw, MessageReceivedEvent e) {
        ArrayList<String> split = new ArrayList<String>();
        String raw = rw;
        String beheaded = raw.replaceFirst(Constants.PREFIX,"");
        String[] splitBeheaded = beheaded.split(" ");


        for(String s : splitBeheaded) {split.add(s);}
        String invoke = split.get(0);
        String[] args = new String[split.size()-1];
        split.subList(1, split.size()).toArray(args);

//        //Test
//        String loggerName = "com.something";
//        Logger log = Logger.getLogger(loggerName);
//        ConsoleHandler handler = new ConsoleHandler();
//        handler.setLevel(Level.ALL);
//        log.addHandler(handler);
//        log.setLevel(Level.ALL);
//        log.fine(raw+" " +beheaded+" " +" " +invoke+" ");
//        log.finer("BEGIN");
//        for(String s : splitBeheaded)
//            log.fine(s);
//        log.finer("END");
        return new CommandContainer(raw, beheaded, splitBeheaded, invoke, args, e);

    }

    public class CommandContainer {
        public final String raw;
        public final String beheaded;
        public final String[] splitBeheaded;
        public final String invoke;
        public final String[] args;
        public final MessageReceivedEvent event;

        public CommandContainer(String rw, String beheaded, String[] splitBeheaded, String invoke, String[] args, MessageReceivedEvent e) {
            this.raw = rw;
            this.beheaded = beheaded;
            this.splitBeheaded = splitBeheaded;
            this.invoke = invoke;
            this.args = args;
            this.event = e;
        }

    }
}
