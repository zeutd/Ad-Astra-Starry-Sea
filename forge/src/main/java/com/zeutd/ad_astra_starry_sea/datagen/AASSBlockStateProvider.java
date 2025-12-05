package com.zeutd.ad_astra_starry_sea.datagen;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.AASSBlocks;
import earth.terrarium.adastra.common.blocks.base.MachineBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static net.minecraft.world.level.block.DirectionalBlock.FACING;

public class AASSBlockStateProvider extends BlockStateProvider {
    public AASSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AdAstraStarrySea.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        AASSBlocks.THRUSTERS.stream().map(RegistryEntry::get).forEach(this::thruster);
    }

    private void thruster(Block block){
        simpleBlockItem(block, models().getBuilder("block/%s".formatted(name(block))));
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(FACING);
            return ConfiguredModel.builder()
                    .modelFile(
                            models().getBuilder("block/%s".formatted(name(block)))
                            .texture("0", modLoc("block/%s".formatted(name(block))))
                                    .parent(models().getExistingFile(modLoc("template/thruster")))
                    )
                    .rotationY(facing.getAxis() != Direction.Axis.Y ? (int) facing.toYRot() : 0)
                    .rotationX(facing.getAxis() == Direction.Axis.Y ? (facing == Direction.UP ? 180 : 90) : 90)
                    .build();
        });
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }
}
