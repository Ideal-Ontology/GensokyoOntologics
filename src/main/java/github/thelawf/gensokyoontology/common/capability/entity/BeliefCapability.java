package github.thelawf.gensokyoontology.common.capability.entity;

import github.thelawf.gensokyoontology.common.nbt.GSKONBTUtil;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import github.thelawf.gensokyoontology.common.util.GSKOUtil;
import net.minecraft.nbt.*;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeliefCapability implements INBTSerializable<ListNBT> {
    // private final List<Pair<BeliefType, Integer>> believes;
    private final Map<String, Integer> believes = new HashMap<>();
    public static BeliefCapability INSTANCE;

    public BeliefCapability() {
        this.believes.put(BeliefType.NONE, 0);
        this.believes.put(BeliefType.MARKET, 0);
        this.believes.put(BeliefType.HAKUREI, 0);

        this.believes.put(BeliefType.TAOISM, 0);
        this.believes.put(BeliefType.BUDDHISM, 0);
        this.believes.put(BeliefType.SHINTOISM, 0);
    }

    @Override
    public ListNBT serializeNBT() {
        ListNBT beliefList = new ListNBT();
        CompoundNBT belief = new CompoundNBT();
        for (Map.Entry<String, Integer> entry : this.believes.entrySet()) {
            belief.putString("belief", entry.getKey());
            belief.putInt("value", entry.getValue());
            beliefList.add(belief);
        }
        return beliefList;
    }

    @Override
    public void deserializeNBT(ListNBT listNBT) {
        List<CompoundNBT> nbts = GSKONBTUtil.getListCompound(listNBT);
        Map<String, Integer> map = new HashMap<>();
        if (nbts.size() == 0) return;
        nbts.forEach(compoundNBT -> map.put(compoundNBT.getString("belief"), compoundNBT.getInt("value")));
        this.believes.clear();
        this.believes.putAll(map);
    }

    public int getValue(String beliefType) {
        return this.believes.get(beliefType);
    }

    public void setValue(String beliefType, int value) {
        this.believes.replace(beliefType, value);
    }

    public void addValue(String type, int addCount) {
        this.setValue(type, this.getValue(type) + addCount);
    }

    public void setCleared() {

    }
    public void setKochiya(int value) {
        this.setValue(BeliefType.SHINTOISM, value);
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
