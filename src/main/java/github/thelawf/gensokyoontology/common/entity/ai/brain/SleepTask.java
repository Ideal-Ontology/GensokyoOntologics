package github.thelawf.gensokyoontology.common.entity.ai.brain;

import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;

import java.util.Map;

public class SleepTask extends Task<HumanResidentEntity> {
    public SleepTask(Map<MemoryModuleType<?>, MemoryModuleStatus> p_i51504_1_) {
        super(p_i51504_1_);
    }
}
