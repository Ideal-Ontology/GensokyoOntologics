package github.thelawf.gensokyoontology.common.network;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PacketHelper {

    public static List<ResourceLocation> readAsResourcesByNBT(PacketBuffer buf, String listName) {
        List<ResourceLocation> resourceLocations = new ArrayList<>();
        CompoundNBT nbt = buf.readCompoundTag();
        if (nbt != null) {
            INBT inbt = nbt.get(listName);
            if (inbt instanceof ListNBT) {
                ListNBT listNbt = (ListNBT) inbt;
                listNbt.forEach(item -> {
                    if (!(item instanceof StringNBT)) return;
                    StringNBT stringNBT = (StringNBT) item;
                    resourceLocations.add(new ResourceLocation(stringNBT.getString()));
                });
            }
        }
        return resourceLocations;
    }

    public static void writeAsResourcesByNBT(PacketBuffer buf, List<ResourceLocation> resourceLocations, String listName) {
        CompoundNBT nbt = new CompoundNBT();
        ListNBT listNbt = new ListNBT();
        resourceLocations.forEach(resourceLocation -> {
            listNbt.add(StringNBT.valueOf(resourceLocation.toString()));
        });
        nbt.put(listName, listNbt);
        buf.writeCompoundTag(nbt);
    }

    public static void writeAsString(PacketBuffer buf, List<String> stringList, String separator) {
        buf.writeVarInt(stringList.size() * 512);
        for(String s : stringList) {
            buf.writeString(s);
            if (stringList.indexOf(s) == stringList.size() - 1) break;
            buf.writeString(separator);
        }
    }

    public static List<String> readAsStringList(PacketBuffer buf, String separator) {
        String str = buf.readString(buf.readVarInt());
        return Arrays.asList(str.split(separator));
    }

    public static List<ResourceLocation> readAsResource(PacketBuffer buf, String separator) {
        return readAsStringList(buf, separator).stream().map(ResourceLocation::new).collect(Collectors.toList());
    }
}
