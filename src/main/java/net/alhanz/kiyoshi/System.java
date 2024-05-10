package net.alhanz.kiyoshi;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class System implements MessageCreateListener {

    // Emoji List
    String pBarZero = "<:bar_zero:1238295241740783616>";
    String pBar25 = "<:bar_25:1238295187277615226>";
    String pBarHalf = "<:bar_50:1238295239362482228>";
    String pBar75 = "<:bar_75:1238295244366286909>";
    String pBarFull = "<:bar_full:1238295236657152102>";

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

            String bar = "CPU Load: " + df.format(cpuLoadPercent) + "%\n" + "[" +
                    pBarFull.repeat(fullBar) +
                    pBar25.repeat(pBar25Count) +
                    pBarHalf.repeat(pBarHalfCount) +
                    pBar75.repeat(pBar75Count) +
                    pBarZero.repeat(remaining) +
                    "]";

            // Bot Uptime

            event.getChannel().sendMessage(bar);
        }
    }
}