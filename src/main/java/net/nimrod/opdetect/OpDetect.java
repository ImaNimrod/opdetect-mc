package net.nimrod.opdetect;

import net.fabricmc.api.ModInitializer;
import net.nimrod.opdetect.util.LogUtils;

public class OpDetect implements ModInitializer {

    public static final String NAME = "opdetect";
    public static final String VERSION = "1.0.0";
    public static final String AUTHOR = "nimrod";

	@Override
	public void onInitialize() {
        LogUtils.consoleLog(String.format("%s v%s - created by %s", NAME, VERSION, AUTHOR));
	}

}
