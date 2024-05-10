package net.alhanz.kiyoshi;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class System implements MessageCreateListener {

    // Emoji List
    String pBarZero = "<:bar0:1238447780914266132>";
    String pBar25 = "<:bar25:1238447791273934959>";
    String pBarHalf = "<:bar50:1238447788526796863>";
    String pBar75 = "<:bar75:1238447786127654942>";
    String pBarFull = "<:bar100:1238447783514603581>";

    public String visualBar(double valuePercent) {

        // Bar is composed of 10 bar emojis.
        int fullBar = (int) valuePercent / 10;

        // Declaring variables for Remainder.
        int pBar25Count = 0, pBarHalfCount = 0, pBar75Count = 0;
        double remainder = (valuePercent / 10) % 1;

        // Checking Remainder for the following split bars.
        if (remainder >= .75) {
            ++pBar75Count;
        } else if (remainder >= .5) {
            ++pBarHalfCount;
        } else if (remainder >= .25) {
            ++pBar25Count;
        }

        // Calculate for bar to generate each of the bars.
        int remaining = 10 - fullBar - pBar25Count - pBarHalfCount - pBar75Count;

        // Formatting the value
        DecimalFormat df = new DecimalFormat("#.##");

        return "\n[" +
                pBarFull.repeat(fullBar) +
                pBar25.repeat(pBar25Count) +
                pBarHalf.repeat(pBarHalfCount) +
                pBar75.repeat(pBar75Count) +
                pBarZero.repeat(remaining) + "] "
                + df.format(valuePercent) + "%";
    }

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(MyBot.prefix() + "sys")) {

            // CPU Info
            double cpuLoad = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class).getCpuLoad();
            double cpuLoadPercent = cpuLoad * 100.0;

            // Memory Info in Megabyte (MB)
            double memFree = (double) Runtime.getRuntime().freeMemory() / 1024 / 1024;
            double memTotal = (double) Runtime.getRuntime().totalMemory() / 1024 / 1024;

            double memoryUsage = memTotal - memFree;
            double memoryUsagePercent = (memoryUsage / memTotal) * 100.00;


            // Message Embed
            MessageAuthor author = event.getMessageAuthor();
            EmbedBuilder sysEmb = new EmbedBuilder()
                    .setTitle("System Information")
                    .setColor(Color.decode("0x69ff96"))
                    .addField("**CPU Load**", this.visualBar(cpuLoadPercent))
                    .addField("** Memory Usage**" + " [" + Math.round(memoryUsage) + "MB / "
                            + Math.round(memTotal) + "MB]", this.visualBar(memoryUsagePercent))
                    .setFooter(author.getName(), author.getAvatar());

            event.getChannel().sendMessage(sysEmb);
        }
    }
}