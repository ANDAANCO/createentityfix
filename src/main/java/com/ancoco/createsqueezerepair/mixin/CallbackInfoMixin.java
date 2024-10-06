package com.ancoco.createsqueezerepair.mixin;

import com.simibubi.create.content.logistics.chute.ChuteBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChuteBlockEntity.class)
public abstract class CallbackInfoMixin{

@Shadow(remap = false)
float pull;
@Shadow(remap = false)
float push;

@SuppressWarnings("DataFlowIssue")
@Inject(method = "tick", at = @At("HEAD"), remap = false)
public void tick(CallbackInfo ci){
    BlockPos pos = ((BlockEntity)(Object)this).getBlockPos();
    Level level = ((BlockEntity)(Object)this).getLevel();
    if(this.pull != 0 || this.push != 0){
        assert level != null;
        level.destroyBlock(pos, false);
    }
}
}
