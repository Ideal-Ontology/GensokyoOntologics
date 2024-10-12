package github.thelawf.gensokyoontology.data.world;

import github.thelawf.gensokyoontology.GensokyoOntology;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.DimensionSavedDataManager;
import net.minecraft.world.storage.WorldSavedData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GSKOWorldSavedData extends WorldSavedData {
    private static final String NAME = "GSKOWorldSavedData";
    private GensokyoSeason season = GensokyoSeason.SPRING;
    private final List<ResourceLocation> incidents = new ArrayList<>();
    private float power;
    public GSKOWorldSavedData() {
        super(NAME);
    }
    public GSKOWorldSavedData(String name) {
        super(name);
    }

    public static GSKOWorldSavedData getInstance(World worldIn) {
        if (!(worldIn instanceof ServerWorld)) {
            throw new RuntimeException("Attempted to get the data from a client world. This is wrong.");
        }
        ServerWorld serverWorld = (ServerWorld) worldIn;
        DimensionSavedDataManager storage = serverWorld.getSavedData();
        return storage.getOrCreate(GSKOWorldSavedData::new, NAME);
    }

    @Override
    public void read(CompoundNBT nbt) {
        this.season = GensokyoSeason.valueOf(nbt.getString("season"));
        this.power = nbt.getFloat("power");
        ListNBT listNBT = (ListNBT) nbt.get("incidents");
        if (listNBT != null) {
            for (INBT inbt : listNBT) {
                if (inbt instanceof StringNBT) {
                    StringNBT value = (StringNBT) inbt;
                    this.incidents.add(GensokyoOntology.withRL(value.getString()));
                }
            }
        }
    }

    @Override
    @NotNull
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT listNBT = new ListNBT();
        incidents.forEach((location) -> {
            StringNBT stringNBT = StringNBT.valueOf(location.toString());
            listNBT.add(stringNBT);
        });
        compound.putString("season", this.season.name());
        compound.put("incidents", listNBT);
        compound.putFloat("power", this.power);
        return compound;
    }

    public GensokyoSeason getSeason() {
        return this.season;
    }

    public void setSeason(GensokyoSeason season) {
        this.season = season;
    }

    public List<ResourceLocation> getIncidents() {
        return this.incidents;
    }

}
