package Tsui.Randy.DiscordBot.commands;

import Tsui.Randy.DiscordBot.Command;
import Tsui.Randy.DiscordBot.utils.Sneaky;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
/**
 * Created by Randy on 4/7/2017.
 */
public class PingCommand implements Command {

    private final String HELP = "USAGE: " + Sneaky.PREFIX + "ping";

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
