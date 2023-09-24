package net.nimrod.opdetect.mixin;

import net.minecraft.client.gui.screen.GameMenuScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.nimrod.opdetect.OpDetect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameMenuScreen.class)
public class GameMenuScreenMixin {

    @Inject(method = "*", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/report/AbuseReportContext;tryShowDraftScreen(Lnet/minecraft/client/MinecraftClient;Lnet/minecraft/client/gui/screen/Screen;Ljava/lang/Runnable;Z)V"), cancellable = true)
    private void onExitButton(ButtonWidget button, CallbackInfo ci) {
        OpDetect.reset();
    }

}
