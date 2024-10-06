package com.ancoco.createsqueezerepair.mixin;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.contraptions.Contraption;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Contraption.class)
public abstract class BlockStateMixin {

    @Unique
    private boolean createsqueezerepair$removeSuperglue = false;

    @Redirect(method = "addBlocksToWorld", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/block/state/BlockState;getDestroySpeed(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F"))
    private float redirectGetDestroySpeed(BlockState instance, BlockGetter world, BlockPos targetPos) {
        if (instance.canBeReplaced() || instance.getCollisionShape(world, targetPos).isEmpty()) {
            // 只允许破坏无碰撞体积的方块和可被替换的方块（例如：草，雪层）
            return instance.getDestroySpeed(world, targetPos);
        }

        // 对铁轨特殊处理，避免损坏铁路盾构机
        if (AllBlocks.TRACK.has(instance) || AllBlocks.FAKE_TRACK.has(instance)) {
            return instance.getDestroySpeed(world, targetPos);
        }

        // 其他情况下阻止破坏，标记需要移除 Superglue
        createsqueezerepair$removeSuperglue = true;
        return -1.0F;
    }

    @Redirect(method = "addBlocksToWorld", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean redirectAddSuperglueEntity(Level instance, Entity entity) {
        if (this.createsqueezerepair$removeSuperglue) {
            return false; // 不添加实体
        }
        return instance.addFreshEntity(entity); // 否则正常添加实体
    }
}
