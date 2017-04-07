package Tsui.Randy.DiscordBot;

/**
 * Created by Randy on 4/7/2017.
 */
import Tsui.Randy.DiscordBot.commands.PingCommand;
import Tsui.Randy.DiscordBot.utils.CommandParser;
import Tsui.Randy.DiscordBot.utils.Sneaky;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

import java.util.HashMap;


public class Main {
    private static JDA jda;
    public static final CommandParser parser = new CommandParser();

    public static HashMap<String, Command> commands = new HashMap<String, Command>();
    public static void main(String[] args) {
        try{
            jda = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(Sneaky.DISCORD_TOKEN).buildBlocking();
            jda.setAutoReconnect(true);
        }
        catch(Exception e){ //Check doc for more
            e.printStackTrace();
        }
        commands.put("ping", new PingCommand());
    }
    public static void handleCommand(CommandParser.CommandContainer cmd) {
        if(commands.containsKey(cmd.invoke)) {
            boolean safe = commands.get(cmd.invoke).called(cmd.args, cmd.event);

            if(safe) {
                commands.get(cmd.invoke).action(cmd.args, cmd.event);
                commands.get(cmd.invoke).executed(safe, cmd.event);
                //LOG?
            }
            else {

            }
        }
    }
}
