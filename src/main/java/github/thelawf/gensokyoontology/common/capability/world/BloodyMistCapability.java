package github.thelawf.gensokyoontology.common.capability.world;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.world.dimension.biome.GSKOBiomes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BloodyMistCapability implements IIncidentCapability {

    public static final List<RegistryKey<Biome>> ABNORMAL_BIOMES = ImmutableList.of(
            GSKOBiomes.MISTY_LAKE_KEY, GSKOBiomes.SCARLET_MANSION_PRECINCTS_KEY);
    private List<String> biomeRegistryNames;
    private boolean isTriggered;
    private boolean dirty = false;
    public static final String ID = "bloody_mist";
    private List<Pair<String, Boolean>> biomes;
    public static BloodyMistCapability INSTANCE;

    public BloodyMistCapability(List<String> biomes, boolean isTriggered) {
        this.biomeRegistryNames = biomes;
        this.isTriggered = isTriggered;
    }

    public List<String> getBiomeRegistryNames() {
        return this.biomeRegistryNames;
    }

    @Override
    public void setTriggered(boolean triggered) {
        this.isTriggered = triggered;
        this.dirty = true;
    }

    @Override
    public boolean isTriggered() {
        return this.isTriggered;
    }

    public boolean checkAndResetDirty() {
        if (this.dirty) {
            this.dirty = false;
            return true;
        }
        return false;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        ListNBT listNBT = new ListNBT();

        for (String registryName : this.biomeRegistryNames) {
            CompoundNBT biomeNbt = new CompoundNBT();
            biomeNbt.putString("biome", registryName);
            listNBT.add(biomeNbt);
        }
        nbt.putBoolean("is_triggered", this.isTriggered);
        nbt.put("biome_list", listNBT);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        List<String> biomeNames = new ArrayList<>();
        if (nbt.get("biome_list") instanceof ListNBT) {
            ListNBT listNBT = (ListNBT) nbt.get("biome_list");

            if (listNBT == null) return;

            for (INBT inbt : listNBT) {
                if (inbt instanceof CompoundNBT) {
                    CompoundNBT compound = (CompoundNBT) inbt;
                    biomeNames.add(compound.getString("biome"));
                }
            }
        }
        this.biomeRegistryNames = biomeNames;
        this.isTriggered = nbt.getBoolean("is_triggered");
    }
}
