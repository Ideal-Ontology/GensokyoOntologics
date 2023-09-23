package github.thelawf.gensokyoontology.api.event;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Cancelable;

/**
 * 如果事件被取消则永夜异变结束，玩家不能：<br>
 * 1. 通过杀死幻想生物来获取虚假之月弹幕物品
 * 2.
 */
@Cancelable
public class ImperishableNightEvent extends TickEvent.ServerTickEvent {

    public ImperishableNightEvent(Phase phase) {
        super(phase);
    }
}
