package com.zeutd.ad_astra_starry_sea.common.network;

import com.teamresourceful.bytecodecs.base.ByteCodec;
import com.teamresourceful.bytecodecs.base.object.ObjectByteCodec;
import com.teamresourceful.resourcefullib.common.bytecodecs.ExtraByteCodecs;
import com.teamresourceful.resourcefullib.common.network.Packet;
import com.teamresourceful.resourcefullib.common.network.base.PacketType;
import com.teamresourceful.resourcefullib.common.network.base.ServerboundPacketType;
import com.zeutd.ad_astra_starry_sea.AdAstraStarrySea;
import com.zeutd.ad_astra_starry_sea.common.blockentities.SpaceStationControllerBlockEntity;
import earth.terrarium.adastra.common.network.CodecPacketType;
import earth.terrarium.adastra.common.utils.ModUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

import java.util.function.Consumer;

public record ServerboundSetSpaceStationControllerSelectionAreaPacket(BlockPos machine, long packed) implements Packet<ServerboundSetSpaceStationControllerSelectionAreaPacket> {
    public static final ServerboundPacketType<ServerboundSetSpaceStationControllerSelectionAreaPacket> TYPE = new Type();
    @Override
    public PacketType<ServerboundSetSpaceStationControllerSelectionAreaPacket> type() {
        return null;
    }

    private static class Type extends CodecPacketType<ServerboundSetSpaceStationControllerSelectionAreaPacket> implements ServerboundPacketType<ServerboundSetSpaceStationControllerSelectionAreaPacket> {
        public Type() {
            super(ServerboundSetSpaceStationControllerSelectionAreaPacket.class, new ResourceLocation(AdAstraStarrySea.MOD_ID, "set_space_station_controller_area"), ObjectByteCodec.create(ExtraByteCodecs.BLOCK_POS.fieldOf(ServerboundSetSpaceStationControllerSelectionAreaPacket::machine), ByteCodec.LONG.fieldOf(ServerboundSetSpaceStationControllerSelectionAreaPacket::packed), ServerboundSetSpaceStationControllerSelectionAreaPacket::new));
        }

        public Consumer<Player> handle(ServerboundSetSpaceStationControllerSelectionAreaPacket packet) {
            return (player) -> {
                ModUtils.getMachineFromMenuPacket(packet.machine(), player, player.level()).ifPresent((machine) -> {
                    if (machine instanceof SpaceStationControllerBlockEntity entity) {
                        entity.setExpand(unpack(packet.packed()));
                    }
                });
            };
        }
    }
    public static Byte[] unpack(long input){
        Byte[] output = new Byte[6];
        output[0] = (byte) ((input & 0xff00000000000000L) >> 56);
        output[1] = (byte) ((input & 0x00ff000000000000L) >> 48);
        output[2] = (byte) ((input & 0x0000ff0000000000L) >> 40);
        output[3] = (byte) ((input & 0x000000ff00000000L) >> 32);
        output[4] = (byte) ((input & 0x00000000ff000000L) >> 24);
        output[5] = (byte) ((input & 0x0000000000ff0000L) >> 16);
        return output;
    }

    public static long pack(Byte[] input){
        long output = 0;
        output |= ((long) input[0]) << 56;
        output |= ((long) input[1]) << 48;
        output |= ((long) input[2]) << 40;
        output |= ((long) input[3]) << 32;
        output |= ((long) input[4]) << 24;
        output |= ((long) input[5]) << 16;
        return output;
    }
}
