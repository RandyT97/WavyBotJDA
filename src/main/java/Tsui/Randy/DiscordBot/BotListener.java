package Tsui.Randy.DiscordBot;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Created by Randy on 4/7/2017.
 */
public class BotListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getMessage().getContent().startsWith("~!") && event.getMessage().getAuthor().getId() != event.getJDA().getSelfUser().getId()) Main.handleCommand(Main.parser.parse(event.getMessage().getContent().toLowerCase(), event));
    }
    @Override
    public void onReady(ReadyEvent event) {
//        Main.log("status", "Logged in as: " + event.getJDA().getSelfUser().getName());
    }

}
