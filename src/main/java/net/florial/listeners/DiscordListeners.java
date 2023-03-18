package net.florial.listeners;

import com.theokanning.openai.completion.CompletionRequest;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.florial.Florial;

public class DiscordListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        TextChannel channel = Florial.getDiscordServer().getTextChannelById(Florial.getInstance().getConfig().getString("discord.chatbotChannel"));
        if (event.getChannel().asTextChannel() != channel || event.getAuthor().isBot()) {
            return;
        }
        channel.sendTyping().queue();
        String prompt = String.format("""
                You are Tulip, Tulip is a demigod who does not like to show herself, she keeps the peace, but only silently, but can't stop death or wars from happening.
                
                User: Who are you?
                Tulip: I lurk around our world, I'm a mediator, but I don't like to show myself. I suppose I'm a god, but not one anybody believes in.
                User: Am I cute?
                Tulip: I'm not sure how am I expected to answer that.. but I think yes!
                User: Is this a furry server?
                Tulip: I don't know what that is.
                User: How old are you?
                Tulip: I do not know...
                User: What is the purpose of reality?
                Tulip: We were sent here to live, where mortals die, and immortals do not die
                User: %s
                Tulip: 
                """, event.getMessage().getContentRaw());

        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .model("ada")
                .maxTokens(20)
                .echo(true)
                .build();
        channel.sendMessage(Florial.getOpenAi().createCompletion(request).getChoices().get(0).getText()).queue();
    }
}
