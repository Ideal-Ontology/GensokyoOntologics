package github.thelawf.gensokyoontology.common.entity.ai.brain;

import com.google.common.collect.ImmutableMap;
import github.thelawf.gensokyoontology.common.entity.passive.HumanResidentEntity;
import net.minecraft.entity.ai.brain.memory.MemoryModuleStatus;
import net.minecraft.entity.ai.brain.memory.MemoryModuleType;
import net.minecraft.entity.ai.brain.task.Task;

import java.util.Map;

public class FindHomeTask extends Task<HumanResidentEntity> {
    public FindHomeTask() {
        super(ImmutableMap.of());
    }
}
