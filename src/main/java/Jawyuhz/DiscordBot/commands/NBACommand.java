package Jawyuhz.DiscordBot.commands;

import Jawyuhz.DiscordBot.Command;
import Jawyuhz.DiscordBot.utils.Constants;
import net.dean.jraw.RedditClient;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.*;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;


/**
 * Created by Randy on 4/7/2017.
 */
public class NBACommand implements Command{
    private final String HELP = "Usage: " + Constants.PREFIX;
    private static UserAgent myUserAgent = UserAgent.of("desktop","jawyuhz.DiscordBot", "v0.1", "atomsapple1");
    private static RedditClient redditClient = new RedditClient(myUserAgent);
    private static Credentials credentials = Credentials.script(Constants.REDDIT_USER,Constants.REDDIT_PASSWORD,Constants.REDDIT_CLIENT_ID,Constants.REDDIT_SECRET);


    public Boolean called(String[] args, MessageReceivedEvent event) {return true;}

    public void action(String[] args, MessageReceivedEvent event) {


        if(args==null || args.length==0) {
            event.getTextChannel().sendMessage("Please enter " + Constants.PREFIX + "city").queue();
        }
        else try {

            //Make separate class for OAuth
            OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);
            redditClient.authenticate(authData);
            redditClient.me();
            System.out.println("OAuth Successful Login");
            System.out.printf("\n%s", args[0]);
            SubredditPaginator subreddit = new SubredditPaginator(redditClient, "wavesdontdie");
            for(Submission s: subreddit.next()) {

                if(s.getTitle().toLowerCase().contains(args[0])) {
                    Submission fullSubmissionInfo = redditClient.getSubmission(s.getId());
                    CommentNode node = fullSubmissionInfo.getComments();
                    for(CommentNode n : node) {
                        event.getTextChannel().sendMessage(n.getComment().getBody()).queue();

                    }
                }
            }


        } catch (OAuthException e) {
            e.getStackTrace();
        }


    }

    public void help(MessageReceivedEvent event) {

    }
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
