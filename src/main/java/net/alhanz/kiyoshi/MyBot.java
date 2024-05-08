package net.alhanz.kiyoshi;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

public class MyBot {
    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .setToken(SecKey.tokenKey())
                .login()
                .join();
        api.updateActivity(ActivityType.WATCHING, "Hentai");

    }
}
