package net.alhanz.kiyoshi;

import net.alhanz.kiyoshi.Utils.System;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.intent.Intent;

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
    }
}
