package net.alhanz.kiyoshi;

import net.alhanz.kiyoshi.Utils.System;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;
import org.javacord.api.entity.message.MessageFlag;
import org.javacord.api.interaction.SlashCommand;
import org.javacord.api.interaction.SlashCommandBuilder;
import org.javacord.api.interaction.SlashCommandInteraction;

import java.util.HashSet;
import java.util.Set;

public class MyBot {

    public static String prefix() {
        return "!";
    }

    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .addIntents(Intent.MESSAGE_CONTENT)
                .addListener(new System())
                .setToken(SecKey.discordAPIKey)
                .login()
                .join();
        api.updateActivity(ActivityType.LISTENING, "Debugging");

        api.addSlashCommandCreateListener(slashCommandCreateEvent -> {
            SlashCommandInteraction slashCommandInteraction = slashCommandCreateEvent.getSlashCommandInteraction();
            if (slashCommandInteraction.getCommandName().equals("ping")) {
                slashCommandInteraction.createImmediateResponder()
                        .setContent("Pong!")
                        .setFlags(MessageFlag.EPHEMERAL)
                        .respond();
            }
        });

        Set<SlashCommandBuilder> builders = new HashSet<>();
        builders.add(new SlashCommandBuilder().setName("ping").setDescription("A command for the server"));

        api.bulkOverwriteGlobalApplicationCommands(builders).join();
    }
}
