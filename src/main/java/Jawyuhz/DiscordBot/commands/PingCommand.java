package Jawyuhz.DiscordBot.commands;

import Jawyuhz.DiscordBot.Command;
import Jawyuhz.DiscordBot.utils.Constants;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Created by Randy on 4/7/2017.
 */
public class PingCommand implements Command {

    private final String HELP = "Usage: " + Constants.PREFIX + "ping";

    public Boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    public void action(String[] args, MessageReceivedEvent event) {
        if(args==null || args.length==0)
            event.getTextChannel().sendMessage("Pong").queue();
        else
            if(args[0]!=null && args[0].equalsIgnoreCase("help"))
                help(event);



    }

    public void help(MessageReceivedEvent event) {
       event.getTextChannel().sendMessage(HELP).queue();
    }

    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}
