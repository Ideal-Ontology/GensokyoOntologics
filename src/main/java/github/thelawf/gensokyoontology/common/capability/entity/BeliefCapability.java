package github.thelawf.gensokyoontology.common.capability.entity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import net.minecraft.nbt.*;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.List;

public class BeliefCapability implements INBTSerializable<CompoundNBT> {
    private final List<Pair<BeliefType, Integer>> believes;
    public static BeliefCapability INSTANCE;

    public BeliefCapability(List<Pair<BeliefType, Integer>> believes) {
        this.believes = believes;
    }

    @Override
    public CompoundNBT serializeNBT() {
        ListNBT beliefList = new ListNBT();
        ListNBT valueList = new ListNBT();
        CompoundNBT nbt = new CompoundNBT();
        believes.forEach(pair -> beliefList.add(StringNBT.valueOf(pair.getFirst().toString())));
        believes.forEach(pair -> valueList.add(IntNBT.valueOf(pair.getSecond())));
        nbt.put("believes", beliefList);
        nbt.put("values", valueList);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        ListNBT beliefList = new ListNBT();
        ListNBT valueList = new ListNBT();

        if (nbt.contains("believes") && nbt.get("believes") instanceof ListNBT) {
            beliefList = (ListNBT) nbt.get("believes");
        }
        if (nbt.contains("values") && nbt.get("values") instanceof ListNBT) {
            valueList = (ListNBT) nbt.get("values");
        }

        if (valueList != null && beliefList != null && !beliefList.isEmpty() && !valueList.isEmpty() &&
        beliefList.size() == valueList.size()) {
            for (int i = 0; i < beliefList.size(); i++) {
                if (beliefList.get(i) instanceof StringNBT && valueList.get(i) instanceof IntNBT) {
                    this.believes.clear();
                    this.believes.add(Pair.of(Enum.valueOf(BeliefType.class, beliefList.get(i).getString()), ((IntNBT) valueList.get(i)).getInt()));
                }
            }
        }
    }

    public int getValue(BeliefType type) {
        return this.believes.stream().filter(pair -> pair.getFirst() == type).findAny().orElse(Pair.of(BeliefType.NONE, -1)).getSecond();
    }

    public void setValue(BeliefType type, int value) {
        this.believes.set(this.believes.indexOf(this.believes.stream().filter(pair -> pair.getFirst() == type).findAny().orElse(null)), Pair.of(type, value));
    }

    public void addValue(BeliefType type, int addCount) {
        this.setValue(type, this.getValue(type) + addCount);
    }

    public void setCleared() {
        for (BeliefType type : BeliefType.values()) {
            this.setValue(type, 0);
        }
    }
    public void setHakurei(int value) {
        this.setValue(BeliefType.HAKUREI, value);
    }
    public void setKochiya(int value) {
        this.setValue(BeliefType.KOCHIYA, value);
    }
    public void setBuddhism(int value) {
        this.setValue(BeliefType.BUDDHISM, value);
    }

    public void setTaoism(int value) {
        this.setValue(BeliefType.TAOISM, value);
    }
    public void setMarket(int value) {
        this.setValue(BeliefType.MARKET, value);
    }
}
