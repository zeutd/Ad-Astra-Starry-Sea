package com.zeutd.ad_astra_starry_sea.common.blocks;

import com.teamresourceful.resourcefullib.common.caches.CacheableFunction;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.zeutd.ad_astra_starry_sea.common.AASSBlockEntityTypes;
import earth.terrarium.adastra.common.blocks.base.MachineBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class AASSMachineBlock extends MachineBlock {
    private static final CacheableFunction<Block, BlockEntityType<?>> BLOCK_TO_ENTITY = new CacheableFunction<>((Block block) -> {
        return AASSBlockEntityTypes.BLOCK_ENTITY_TYPES.stream().map(RegistryEntry::get).filter((type) -> {
            return type.isValid(block.defaultBlockState());
        }).findFirst().orElse(null);
    });
    private BlockEntityType<?> entity;

    public AASSMachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntityType<?> entity(BlockState state) {
        if (this.entity == null) {
            this.entity = BLOCK_TO_ENTITY.apply(state.getBlock());
        }

        return this.entity;
    }
}
