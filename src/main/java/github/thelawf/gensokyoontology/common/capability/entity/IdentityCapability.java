package github.thelawf.gensokyoontology.common.capability.entity;

import net.minecraft.nbt.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.Map;

public class IdentityCapability implements INBTSerializable<ListNBT> {
    // private final List<Pair<IdentityType, Integer>> believes;
    private final Map<ResourceLocation, Float> identities;
    public static IdentityCapability INSTANCE;

    public IdentityCapability(Map<ResourceLocation, Float> map) {
        this.identities = map;
    }

    @Override
    public ListNBT serializeNBT() {
        ListNBT beliefList = new ListNBT();
        for (Map.Entry<ResourceLocation, Float> entry : identities.entrySet()) {
            CompoundNBT belief = new CompoundNBT();
            belief.putString("identity", entry.getKey().toString());
            belief.putFloat("value", entry.getValue());
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
                identities.put(new ResourceLocation(nbt.getString("belief")), nbt.getFloat("value"));
            }
        });

    }

    public float getValue(ResourceLocation beliefType) {
        return this.identities.get(beliefType);
    }

    public void setValue(ResourceLocation beliefType, float value) {
        this.identities.replace(beliefType, value);
    }

    public void addValue(ResourceLocation type, float addCount) {
        this.setValue(type, this.getValue(type) + addCount);
    }

}
