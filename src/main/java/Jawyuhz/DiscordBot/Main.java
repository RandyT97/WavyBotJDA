package Jawyuhz.DiscordBot;

/**
 * Created by Randy on 4/7/2017.
 */
import Jawyuhz.DiscordBot.commands.PingCommand;
import Jawyuhz.DiscordBot.utils.CommandParser;
import Jawyuhz.DiscordBot.utils.Constants;
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
            jda = new JDABuilder(AccountType.BOT).addListener(new BotListener()).setToken(Constants.DISCORD_TOKEN).buildBlocking();
            jda.setAutoReconnect(true);
        }
        catch(Exception e){ //Check doc for more
            e.printStackTrace();
        }
        commands.put("ping", new PingCommand());
        commands.put("nba", new PingCommand());
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
