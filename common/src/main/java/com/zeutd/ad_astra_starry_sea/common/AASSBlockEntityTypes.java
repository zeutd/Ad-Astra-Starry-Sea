package com.zeutd.ad_astra_starry_sea.common;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.blockentities.SpaceStationControllerBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

@SuppressWarnings("unused")
public class AASSBlockEntityTypes {
    public static final ResourcefulRegistry<BlockEntityType<?>> BLOCK_ENTITY_TYPES = ResourcefulRegistries.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, AdAstraStarrySea.MOD_ID);
    public static final RegistryEntry<BlockEntityType<SpaceStationControllerBlockEntity>> SPACE_STATION_CONTROLLER = BLOCK_ENTITY_TYPES.register(
            "space_station_controller",
            () -> createBlockEntityType(
                    SpaceStationControllerBlockEntity::new,
                    AASSBlocks.SPACE_STATION_CONTROLLER
            )
    );
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockEntityType.BlockEntitySupplier<E> factory, ResourcefulRegistry<Block> registry) {
        return BlockEntityType.Builder.of(factory,
                        registry.stream()
                                .map(RegistryEntry::get)
                                .toArray(Block[]::new))
                .build(null);
    }
    public static <E extends BlockEntity> BlockEntityType<E> createBlockEntityType(BlockEntityType.BlockEntitySupplier<E> factory, RegistryEntry<Block> block) {
        return BlockEntityType.Builder.of(factory, block.get())
                .build(null);
    }
}