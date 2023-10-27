package github.thelawf.gensokyoontology.common.capability;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.ArrayList;
import java.util.List;

public class BloodyMistCapability implements INBTSerializable<CompoundNBT> {

    private List<String> biomeRegistryNames;
    public boolean isTriggered;

    public BloodyMistCapability(List<String> biomes, boolean isTriggered) {
        this.biomeRegistryNames = biomes;
        this.isTriggered = isTriggered;
    }
    public List<String> getBiomeRegistryNames() {
        return this.biomeRegistryNames;
    }

    public void isTriggered() {
        this.isTriggered = true;
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
