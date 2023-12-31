package net.nimrod.opdetect.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import net.nimrod.opdetect.OpDetect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerEntityMixin {

    @Inject(method = "tick()V", at = @At("RETURN"), cancellable = true)
    private void onTick(CallbackInfo ci) {
        OpDetect.onTick();
    }   

}
