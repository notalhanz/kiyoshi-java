package net.alhanz.kiyoshi;

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
                .setToken(SecKey.tokenKey())
                .login()
                .join();
        api.updateActivity(ActivityType.WATCHING, "Michael fucking a cunny");
    }
}
