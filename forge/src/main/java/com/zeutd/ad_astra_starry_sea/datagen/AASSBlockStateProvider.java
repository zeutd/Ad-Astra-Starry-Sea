package com.zeutd.ad_astra_starry_sea.datagen;

import com.teamresourceful.resourcefullib.common.registry.RegistryEntry;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.AASSBlocks;
import earth.terrarium.adastra.common.blocks.base.MachineBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import static com.zeutd.ad_astra_starry_sea.common.blocks.ThrusterBlock.FACING;
import static com.zeutd.ad_astra_starry_sea.common.blocks.ThrusterBlock.LIT;
@SuppressWarnings("removal")
public class AASSBlockStateProvider extends BlockStateProvider {
    public AASSBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, AdAstraStarrySea.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        AASSBlocks.THRUSTERS.stream().map(RegistryEntry::get).forEach(this::thruster);
        machine(AASSBlocks.SPACE_STATION_CONTROLLER.get());
    }

    private void thruster(Block block){
        simpleBlockItem(block, models().getBuilder("block/%s".formatted(name(block))));
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(FACING);
            boolean lit = state.getValue(LIT);
            String loc = "block/%s%s".formatted(name(block), lit ? "_lit" : "");
            return ConfiguredModel.builder()
                    .modelFile(
                            models().getBuilder(loc)
                            .texture("0", modLoc(loc))
                            .texture("particle", modLoc("block/%s".formatted(name(block))))
                                    .parent(models().getExistingFile(modLoc("template/thruster")))
                    )
                    .rotationY(facing.getAxis() != Direction.Axis.Y ? (int) facing.toYRot() : 0)
                    .rotationX(facing.getAxis() == Direction.Axis.Y ? (facing == Direction.UP ? 180 : 0) : 90)
                    .build();
        });
    }

    public void machine(Block block) {
        simpleBlockItem(block, models().getBuilder("block/%s_on".formatted(name(block))));
        getVariantBuilder(block).forAllStates(state -> {
            Direction facing = state.getValue(MachineBlock.FACING);
            boolean lit = state.getValue(MachineBlock.LIT);
            String name = this.name(block);
            ResourceLocation texture = modLoc((lit ? "block/%s_front_on" : "block/%s_front").formatted(name, name));

            return ConfiguredModel.builder()
                    .modelFile(models().getBuilder(lit ? name + "_on" : name)
                            .texture("down", modLoc("block/machine_bottom"))
                            .texture("up", modLoc("block/machine_top"))
                            .texture("north", texture)
                            .texture("south", modLoc("block/machine_side"))
                            .texture("east", modLoc("block/machine_side"))
                            .texture("west", modLoc("block/machine_side"))
                            .texture("particle", texture)
                            .parent(models().getExistingFile(new ResourceLocation("block/cube"))))
                    .rotationY((int) (facing.toYRot() + 180) % 360)
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
