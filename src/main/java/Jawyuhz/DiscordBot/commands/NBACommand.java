package Jawyuhz.DiscordBot.commands;

import Jawyuhz.DiscordBot.utils.Constants;
import net.dean.jraw.RedditClient;
import net.dean.jraw.fluent.SubredditReference;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Randy on 4/7/2017.
 */
public class NBACommand {
    private final String HELP = "Usage: " + Constants.PREFIX;
    public Boolean called(String[] args, MessageReceivedEvent event) {return true;}
    public void action(String[] args, MessageReceivedEvent event) {
        UserAgent myUserAgent = UserAgent.of("desktop","jawyuhz.DiscordBot", "v0.1", "atomsapple1");
        RedditClient redditClient = new RedditClient(myUserAgent);
        Credentials credentials = Credentials.script(Constants.REDDIT_USER,Constants.REDDIT_PASSWORD
        ,Constants.REDDIT_CLIENT_ID,Constants.REDDIT_SECRET);
        try {
            OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);
            redditClient.authenticate(authData);
            redditClient.me();

        } catch (OAuthException e) {
            e.printStackTrace();
        }


    }
    public void help(MessageReceivedEvent event) {

    }
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
