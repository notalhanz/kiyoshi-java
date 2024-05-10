package net.alhanz.kiyoshi;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import com.sun.management.OperatingSystemMXBean;


import java.awt.*;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class System implements MessageCreateListener {

    // Emoji List
    String pBarZero = "<:bar0:1238447780914266132>";
    String pBar25 = "<:bar25:1238447791273934959>";
    String pBarHalf = "<:bar50:1238447788526796863>";
    String pBar75 = "<:bar75:1238447786127654942>";
    String pBarFull = "<:bar100:1238447783514603581>";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(MyBot.prefix() + "sys")) {

            // CPU Info
            DecimalFormat df = new DecimalFormat("#.##");
            double cpuLoad = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class).getCpuLoad();
            double cpuLoadPercent = cpuLoad * 100.0;

            int fullBar = (int) cpuLoadPercent / 10;
            int pBar25Count = 0, pBarHalfCount = 0, pBar75Count = 0;
            double remainder = (cpuLoadPercent / 10) % 1;

            // Check for "cpuLoadPercent" remainder
            if (remainder >= .75) {
                ++pBar75Count;
            } else if (remainder >= .5) {
                ++pBarHalfCount;
            } else if (remainder >= .25) {
                ++pBar25Count;
            }

            int remaining = 10 - fullBar - pBar25Count - pBarHalfCount - pBar75Count;

            String bar = "\n[" +
                    pBarFull.repeat(fullBar) +
                    pBar25.repeat(pBar25Count) +
                    pBarHalf.repeat(pBarHalfCount) +
                    pBar75.repeat(pBar75Count) +
                    pBarZero.repeat(remaining) + "] "
                    + df.format(cpuLoadPercent) + "%";

            // Message Embed
            MessageAuthor author = event.getMessageAuthor();
            EmbedBuilder sysEmb = new EmbedBuilder()
                    .setTitle("System Information")
                    .setColor(Color.decode("0x69ff96"))
                    .addField("Resources","* CPU Load" + bar, true)
                    .setFooter(author.getName(), author.getAvatar());

            event.getChannel().sendMessage(sysEmb);
        }
    }
}