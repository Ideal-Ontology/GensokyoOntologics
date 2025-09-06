package github.thelawf.gensokyoontology.common.entity.ai.brain;

import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;

import java.util.Map;

public class BoidTask extends Task<HumanResidentEntity> {

    public BoidTask(Map<MemoryModuleType<?>, MemoryModuleStatus> map) {
        super(map);
    }
}
