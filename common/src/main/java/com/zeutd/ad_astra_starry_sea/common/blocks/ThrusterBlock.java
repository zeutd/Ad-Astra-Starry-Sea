package com.zeutd.ad_astra_starry_sea.common.blocks;

import com.teamresourceful.resourcefullib.common.caches.CacheableFunction;
import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.zeutd.ad_astra_starry_sea.common.AASSBlockEntityTypes;
import earth.terrarium.adastra.common.blocks.base.BasicEntityBlock;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class ThrusterBlock extends BasicEntityBlock {

    public static final DirectionProperty FACING = BlockStateProperties.FACING;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    private static final CacheableFunction<Block, BlockEntityType<?>> BLOCK_TO_ENTITY = new CacheableFunction<>((Block block) -> {
        return AASSBlockEntityTypes.BLOCK_ENTITY_TYPES.stream().map(RegistryEntry::get).filter((type) -> {
            return type.isValid(block.defaultBlockState());
        }).findFirst().orElse(null);
    });
    private BlockEntityType<?> entity;

    @Override
    public BlockEntityType<?> entity(BlockState state) {
        if (this.entity == null) {
            this.entity = BLOCK_TO_ENTITY.apply(state.getBlock());
        }

        return this.entity;
    }

    public ThrusterBlock(Properties properties) {
        super(properties, true);
    }

    public BlockState rotate(BlockState blockState, Rotation rotation) {
        return (BlockState)blockState.setValue(FACING, rotation.rotate((Direction)blockState.getValue(FACING)));
    }

    public BlockState mirror(BlockState blockState, Mirror mirror) {
        return blockState.rotate(mirror.getRotation((Direction)blockState.getValue(FACING)));
    }

    public BlockState getStateForPlacement(BlockPlaceContext blockPlaceContext) {
        return (BlockState)this.defaultBlockState().setValue(FACING, blockPlaceContext.getNearestLookingDirection().getOpposite());
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, LIT);
    }
}
