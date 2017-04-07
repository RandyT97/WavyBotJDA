package Jawyuhz.DiscordBot.commands;

import Jawyuhz.DiscordBot.Command;
import Jawyuhz.DiscordBot.utils.Constants;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Created by Randy on 4/7/2017.
 */
public class PingCommand implements Command {

    private final String HELP = "USAGE: " + Constants.PREFIX + "ping";

    public Boolean called(String[] args, MessageReceivedEvent event) {
        return true;
    }

    public void action(String[] args, MessageReceivedEvent event) {
        event.getTextChannel().sendMessage("Pong").queue();


    }

    public String help() {
        return HELP;
    }

    public void executed(boolean success, MessageReceivedEvent event) {
        return;
    }
}
