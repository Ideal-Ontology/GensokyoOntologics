package github.thelawf.gensokyoontology.common.capability.entity;

import com.mojang.datafixers.util.Pair;
import github.thelawf.gensokyoontology.common.util.BeliefType;
import net.minecraft.nbt.*;
import net.minecraftforge.common.util.INBTSerializable;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class FaithCapability implements INBTSerializable<CompoundNBT> {
    private final Set<Pair<BeliefType, Integer>> believes;

    public FaithCapability(Set<Pair<BeliefType, Integer>> believes) {
        this.believes = believes;
    }

    @Override
    public CompoundNBT serializeNBT() {
        ListNBT beliefList = new ListNBT();
        ListNBT meterList = new ListNBT();
        CompoundNBT nbt = new CompoundNBT();
        believes.forEach(pair -> beliefList.add(StringNBT.valueOf(pair.getFirst().toString())));
        believes.forEach(pair -> meterList.add(IntNBT.valueOf(pair.getSecond())));
        nbt.put("believes", beliefList);
        nbt.put("meters", meterList);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        ListNBT beliefList = new ListNBT();
        ListNBT meterList = new ListNBT();

        if (nbt.contains("believes") && nbt.get("believes") instanceof ListNBT) {
            beliefList = (ListNBT) nbt.get("believes");
        }
        if (nbt.contains("meters") && nbt.get("meters") instanceof ListNBT) {
            meterList = (ListNBT) nbt.get("meters");
        }

        if (meterList != null && beliefList != null && !beliefList.isEmpty() && !meterList.isEmpty() &&
        beliefList.size() == meterList.size()) {
            for (int i = 0; i < beliefList.size(); i++) {
                if (beliefList.get(i) instanceof StringNBT && meterList.get(i) instanceof IntNBT) {
                    this.believes.clear();
                    this.believes.add(Pair.of(Enum.valueOf(BeliefType.class, beliefList.get(i).getString()), ((IntNBT) meterList.get(i)).getInt()));
                }
            }
        }
    }

    public int getMeter(BeliefType type) {
        return this.believes.stream().filter(pair -> pair.getFirst() == type).findAny().orElse(Pair.of(BeliefType.NONE, -1)).getSecond();
    }
}
