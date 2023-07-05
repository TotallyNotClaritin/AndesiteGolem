package net.sobe.andesitegolem.handler;

import com.google.common.cache.Cache;
import net.fabricmc.api.ModInitializer;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.sobe.andesitegolem.entity.ModEntities;
import net.sobe.andesitegolem.entity.custom.AndesiteGolemEntity;
import net.sobe.util.ModRegistries;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

public class StructureDetector {

    public static void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemstack, CallbackInfo info)
    {
        if (state.getBlock() != Blocks.TORCH)
        {
            return;
        }

        var pattern = BlockPatternBuilder.start().aisle("a", "b", "c")
                .where('a', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.TORCH)))
                .where('b', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.CARVED_PUMPKIN)))
                .where('c', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.POLISHED_ANDESITE)))
                .build();

        var result = pattern.searchAround(world, pos);
                if (result != null) {
                    for (var i = 0; i < pattern.getWidth(); ++i) {
                        for (var j = 0; j < pattern.getHeight(); ++j) {
                            CachedBlockPosition cachedBlockPosition3 = result.translate(i, j, 0);
                            world.setBlockState(cachedBlockPosition3.getBlockPos(), Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                            world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, cachedBlockPosition3.getBlockPos(), Block.getRawIdFromState(cachedBlockPosition3.getBlockState()));
                        }
                    }

                BlockPos blockPos2 = result.translate(0, 2, 0).getBlockPos();
                AndesiteGolemEntity andesiteGolemEntity = ModEntities.ANDESITEGOLEM.create(world);
                andesiteGolemEntity.refreshPositionAndAngles((double)blockPos2.getX() +0.5D, (double)blockPos2.getY() + 0.05D, (double)blockPos2.getZ() +0.5D, 0.0F, 0.0F);
                world.spawnEntity(andesiteGolemEntity);

                var imprinted = world.getNonSpectatingEntities(ServerPlayerEntity.class, andesiteGolemEntity.getBoundingBox().expand(5.0D)).iterator();

                while(imprinted.hasNext()){
                    var serverPlayerEntity2 = (ServerPlayerEntity)imprinted.next();
                    Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity2, andesiteGolemEntity);
                }

                andesiteGolemEntity.setPlayerCreated();

                for(var l = 0; l < pattern.getWidth(); ++l){
                    for(int m = 0; m < pattern.getHeight(); ++m){
                        CachedBlockPosition cachedBlockPosition4 = result.translate(l, m, 0);
                        world.updateNeighbors(cachedBlockPosition4.getBlockPos(), Blocks.AIR);
                    }
                }
                info.cancel();
        }
    }
}
