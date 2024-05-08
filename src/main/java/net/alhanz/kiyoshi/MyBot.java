package net.alhanz.kiyoshi;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.intent.Intent;

public class MyBot {
    public static void main(String[] args) {
        DiscordApi api = new DiscordApiBuilder()
                .addIntents(Intent.GUILD_PRESENCES)
                .setToken(SecKey.tokenKey())
                .login()
                .join();

    }
}
