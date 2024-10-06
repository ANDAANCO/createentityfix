package com.ancoco.createsqueezerepair.mixin;

import com.simibubi.create.foundation.utility.BlockHelper;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockHelper.class)
public class CallbackInfoReturnableMixin{

@Inject(method = "prepareBlockEntityData", remap = false, cancellable = true, at = @At("HEAD"))
private static void onPrepareBlockEntityData(BlockState blockState, BlockEntity blockEntity, CallbackInfoReturnable<CompoundTag> cir){
    cir.setReturnValue(null);
    cir.cancel();
}
}
