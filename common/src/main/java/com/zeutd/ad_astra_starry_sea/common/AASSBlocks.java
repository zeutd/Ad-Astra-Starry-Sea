package com.zeutd.ad_astra_starry_sea.common;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistries;
import com.teamresourceful.resourcefullib.common.registry.ResourcefulRegistry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.blocks.ThrusterBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class AASSBlocks {
    public static final ResourcefulRegistry<Block> BLOCKS = ResourcefulRegistries.create(BuiltInRegistries.BLOCK, AdAstraStarrySea.MOD_ID);
    public static final ResourcefulRegistry<Block> THRUSTERS = ResourcefulRegistries.create(BLOCKS);
    public static final RegistryEntry<Block> STEEL_THRUSTER =
            THRUSTERS.register("steel_thruster", () -> new ThrusterBlock(steelProperties().noOcclusion()));
    private static BlockBehaviour.Properties steelProperties() {
        return BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0F, 12.0F).sound(SoundType.COPPER);
    }
}
