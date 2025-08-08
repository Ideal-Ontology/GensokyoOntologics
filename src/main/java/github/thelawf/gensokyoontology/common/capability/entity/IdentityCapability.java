package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Map;

public class IdentityCapability implements INBTSerializable<ListNBT> {
    // private final List<Pair<IdentityType, Integer>> believes;
    private final Map<ResourceLocation, Float> floatIdentities;
    private final Map<ResourceLocation, Integer> intIdentities;
    public static IdentityCapability INSTANCE;

    public IdentityCapability(Map<ResourceLocation, Float> floatMap, Map<ResourceLocation, Integer> intMap) {
        this.floatIdentities = floatMap;
        this.intIdentities = intMap;
    }

    @Override
    public ListNBT serializeNBT() {
        ListNBT beliefList = new ListNBT();
        for (Map.Entry<ResourceLocation, Float> entry : floatIdentities.entrySet()) {
            CompoundNBT belief = new CompoundNBT();
            belief.putString("identity", entry.getKey().toString());
            belief.putFloat("value", entry.getValue());
            beliefList.add(belief);
        }
        for (Map.Entry<ResourceLocation, Integer> entry : intIdentities.entrySet()) {
            CompoundNBT belief = new CompoundNBT();
            belief.putString("identity", entry.getKey().toString());
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
                floatIdentities.put(new ResourceLocation(nbt.getString("belief")), nbt.getFloat("value"));
            }
        });

    }

    public float getValue(ResourceLocation beliefType) {
        return this.floatIdentities.get(beliefType);
    }

    public void setValue(ResourceLocation beliefType, float value) {
        this.floatIdentities.replace(beliefType, value);
    }

    public void addValue(ResourceLocation type, float addCount) {
        this.setValue(type, this.getValue(type) + addCount);
    }

}
