package github.thelawf.gensokyoontology.common.capability;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;

import java.util.ArrayList;
import java.util.List;

public class BloodyMistCapability implements IIncidentCapability {

    private List<RegistryKey<Biome>> biomes;
    public boolean isTriggered;

    public BloodyMistCapability(List<RegistryKey<Biome>> biomes) {
        this.biomes = biomes;
        this.isTriggered = false;
    }
    public List<RegistryKey<Biome>> getBiomes() {
        return this.biomes;
    }


    @Override
    public void applyIncident() {
        this.isTriggered = true;
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        ListNBT listNBT = new ListNBT();

        for (RegistryKey<Biome> registryKey : this.biomes) {
            CompoundNBT biomeNbt = new CompoundNBT();
            biomeNbt.putString("biome", registryKey.getRegistryName().toString());
            listNBT.add(biomeNbt);
        }
        nbt.putBoolean("is_triggered", isTriggered);
        nbt.put("biome_list", listNBT);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        List<RegistryKey<Biome>> biomeRegistries = new ArrayList<>();
        if (nbt.get("biome_list") instanceof ListNBT) {
            ListNBT listNBT = (ListNBT) nbt.get("biome_list");

            if (listNBT == null) return;

            for (INBT inbt : listNBT) {
                if (inbt instanceof CompoundNBT) {
                    CompoundNBT compound = (CompoundNBT) inbt;
                    biomeRegistries.add(RegistryKey.getOrCreateKey(Registry.BIOME_KEY,
                            new ResourceLocation(compound.getString("biome"))));
                }

            }
        }
        this.biomes = biomeRegistries;
        this.isTriggered = nbt.getBoolean("is_triggered");
    }


}
