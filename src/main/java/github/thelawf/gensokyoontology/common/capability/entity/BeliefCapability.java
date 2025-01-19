package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.GensokyoOntology;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.Map;

public class BeliefCapability implements INBTSerializable<ListNBT> {
    // private final List<Pair<BeliefType, Integer>> believes;
    private final Map<ResourceLocation, Integer> believes;
    public static BeliefCapability INSTANCE;

    public BeliefCapability(Map<ResourceLocation, Integer> map) {
        this.believes = map;
    }

    @Override
    public ListNBT serializeNBT() {
        ListNBT beliefList = new ListNBT();
        for (Map.Entry<ResourceLocation, Integer> entry : believes.entrySet()) {
            CompoundNBT belief = new CompoundNBT();
            belief.putString("belief", entry.getKey().toString());
            belief.putInt("value", entry.getValue());
            beliefList.add(belief);
        }

        return beliefList;
    }

    @Override
    public void deserializeNBT(ListNBT listNBT) {
        if (listNBT.size() == 0) return;
        listNBT.forEach(inbt -> {
            if (inbt instanceof CompoundNBT) {
                CompoundNBT nbt = (CompoundNBT) inbt;
                believes.put(new ResourceLocation(nbt.getString("belief")), nbt.getInt("value"));
            }
        });

    }

    public int getValue(ResourceLocation beliefType) {
        return this.believes.get(beliefType);
    }

    public void setValue(ResourceLocation beliefType, int value) {
        this.believes.replace(beliefType, value);
    }

    public void addValue(ResourceLocation type, int addCount) {
        this.setValue(type, this.getValue(type) + addCount);
    }

}
