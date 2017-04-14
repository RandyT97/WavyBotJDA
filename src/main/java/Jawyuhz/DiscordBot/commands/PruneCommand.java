//package Jawyuhz.DiscordBot.commands;
//
//import Jawyuhz.DiscordBot.utils.Constants;
//import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
///**
// * Created by Randy on 4/13/17.
// */
//public class PruneCommand {
//    private String HELP = "Usage: " + Constants.PREFIX + "prune name####");
//
//    public Boolean called(String[] args, MessageReceivedEvent event) {
//        return true;
//    }
//
//    public void action(String[] args, MessageReceivedEvent event) {
//        if(args==null || args.length==0)
//            event.getTextChannel().sendMessage(HELP).queue();
//        else if(args[0]!=null && args[0].equalsIgnoreCase("help"))
//            help(event);
//        else {
//            int numDelete = 20;
//            if(args[1] != null && (Integer.parseInt(args[1])<100))
//                numDelete = Integer.parseInt(args[1]);
//            List messages = new ArrayList();
////            while()
//
//            }
//        }
//
//
//
//    }
//
//    public void help(MessageReceivedEvent event) {
//        event.getTextChannel().sendMessage(HELP).queue();
//    }
//
//    public void executed(boolean success, MessageReceivedEvent event) {
//        return;
//    }
//}
