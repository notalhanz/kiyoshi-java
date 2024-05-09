package net.alhanz.kiyoshi;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import com.sun.management.OperatingSystemMXBean;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

public class System implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(MyBot.prefix() + "sys")) {

            DecimalFormat df = new DecimalFormat("#.##");
            double cpuLoad = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class).getCpuLoad();
            cpuLoad = cpuLoad * 100;

            int completed = (int) (cpuLoad / 10);
            int remaining = 10 - completed;

            String bar = "CPU Load: " + df.format(cpuLoad) + "%\n" + "[" +
                    "=".repeat(Math.max(0, completed)) +
                    "-".repeat(Math.max(0, remaining)) +
                    "]";

            event.getChannel().sendMessage(bar);
        }
    }
}