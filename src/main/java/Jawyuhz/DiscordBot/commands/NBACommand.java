package Jawyuhz.DiscordBot.commands;

import Jawyuhz.DiscordBot.Command;
import Jawyuhz.DiscordBot.utils.Constants;
import net.dean.jraw.RedditClient;
import net.dean.jraw.fluent.SubredditReference;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.http.oauth.Credentials;
import net.dean.jraw.http.oauth.OAuthData;
import net.dean.jraw.http.oauth.OAuthException;
import net.dean.jraw.models.*;
import net.dean.jraw.paginators.SubredditPaginator;
import net.dean.jraw.paginators.CommentStream;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import static java.util.concurrent.TimeUnit.SECONDS;
import static net.dean.jraw.paginators.Sorting.TOP;

/**
 * Created by Randy on 4/7/2017.
 */
public class NBACommand implements Command{
    private final String HELP = "Usage: " + Constants.PREFIX;

    public Boolean called(String[] args, MessageReceivedEvent event) {return true;}

    public void action(String[] args, MessageReceivedEvent event) {

        UserAgent myUserAgent = UserAgent.of("desktop","jawyuhz.DiscordBot", "v0.1", "atomsapple1");
        RedditClient redditClient = new RedditClient(myUserAgent);

        Credentials credentials = Credentials.script(Constants.REDDIT_USER,Constants.REDDIT_PASSWORD
        ,Constants.REDDIT_CLIENT_ID,Constants.REDDIT_SECRET);

        if(args==null || args.length==0) {
            event.getTextChannel().sendMessage("Please enter "+
                    Constants.PREFIX +
                    "TEAMSHORTHAND").queue();
        }
        else {
            try {

                        OAuthData authData = redditClient.getOAuthHelper().easyAuth(credentials);
                        redditClient.authenticate(authData);
                        redditClient.me();
                        System.out.println("OAuth Successful Login");
                        SubredditPaginator link = new SubredditPaginator(redditClient , "wavesdontdie");
                        CommentStream commentPaginator = new CommentStream(redditClient,"wavesdontdie");
                        Listing<Submission> test = link.next();
                        System.out.println(test.size());
                        for(Listing s : commentPaginator) {
                            System.out.println(s.setSorting(TOP));
                        }
//                        for(Submission sub : test) {
//                            if(sub.getTitle().toLowerCase().contains(args[0])) {//Conditional Successful to find if team matches thread title
//                                System.out.println(commentPaginator.)
//                                event.getTextChannel().sendMessage(sub.getTitle());
//                                CommentNode comments = sub.getComments();
//                                event.getTextChannel().sendMessage(comments.getComment().getBody()).queue();
//
//                            }
//                            try {
//                                Thread.sleep(1000);
//                            }
//                            catch(Exception e) {
//
//                            }
//                        }





                    } catch (OAuthException e) {
                        e.printStackTrace();
                    }
        }


    }
    public void help(MessageReceivedEvent event) {

    }
    public void executed(boolean success, MessageReceivedEvent event) {

    }

}
