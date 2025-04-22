package github.thelawf.gensokyoontology.data;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Util;

import java.io.File;

import static github.thelawf.gensokyoontology.GensokyoOntology.LOGGER;

public class GSKOPlayerData {

    private final PlayerEntity player;
    private final File playerDataFolder;

    public GSKOPlayerData(PlayerEntity player, File playerDataFolder) {
        this.player = player;
        this.playerDataFolder = playerDataFolder;
    }

    public void savePlayerData(String key, INBT value) {
        try {
            CompoundNBT compoundnbt = player.writeWithoutTypeId(new CompoundNBT());
            compoundnbt.put(key, value);
            File file1 = File.createTempFile(player.getCachedUniqueIdString() + "-", ".dat", this.playerDataFolder);
            CompressedStreamTools.writeCompressed(compoundnbt, file1);
            File file2 = new File(this.playerDataFolder, player.getCachedUniqueIdString() + ".dat");
            File file3 = new File(this.playerDataFolder, player.getCachedUniqueIdString() + ".dat_old");
            Util.backupThenUpdate(file2, file1, file3);
            net.minecraftforge.event.ForgeEventFactory.firePlayerSavingEvent(player, playerDataFolder, player.getCachedUniqueIdString());
        } catch (Exception exception) {
            LOGGER.warn("Failed to save player data for {}", player.getName().getString());
        }
    }

    public void putInt(String key, int value) {
        savePlayerData(key, IntNBT.valueOf(value));
    }
}
