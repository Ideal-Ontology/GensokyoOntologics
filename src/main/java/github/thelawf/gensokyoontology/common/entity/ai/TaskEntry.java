package github.thelawf.gensokyoontology.common.entity.ai;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.schedule.Activity;
import net.minecraft.entity.ai.brain.task.Task;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Arrays;

public class TaskEntry<E extends LivingEntity> {
    public final ResourceLocation key;
    public final Activity activity;
    public final String taskName;
    public final Task<E> task;
    public boolean shouldTick;
    public int tickCount;

    public TaskEntry(ResourceLocation key, Activity activity, String taskName, Task<E> task) {
        this.key = key;
        this.activity = activity;
        this.taskName = taskName;
        this.task = task;
        this.shouldTick = true;
    }

    public ResourceLocation getKey() {
        return this.key;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Task<E> getTask() {
        return this.task;
    }

    public String getPath() {
        return this.activity.toString() + "/" + this.taskName;
    }

    public String getFullPath() {
        return this.key.toString() + "/" + this.activity.toString() + "/" + this.taskName;
    }

    public static TaskEntry<? extends LivingEntity> parse(String path) {
        String[] str = path.split("/");
        ResourceLocation key = null;
        Activity activity = Activity.CORE;
        String taskName = "";

        switch (str.length){
            case 0:
                break;
            case 1:
                key = new ResourceLocation(str[0]);
                break;
            case 2:
                activity = ForgeRegistries.ACTIVITIES.getValue(new ResourceLocation(str[1]));
                break;
            case 3:
                taskName = str[2];
                break;
            default:
                taskName = String.join("/", Arrays.copyOfRange(str, 2, str.length));
                break;
        }
        return new TaskEntry<>(key, activity, taskName, null);
    }

}
