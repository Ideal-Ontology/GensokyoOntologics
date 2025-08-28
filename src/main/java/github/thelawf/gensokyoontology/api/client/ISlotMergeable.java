package github.thelawf.gensokyoontology.api.client;

import github.thelawf.gensokyoontology.api.util.IntRange;

public interface ISlotMergeable {
    /**
     * 获取容器中自定义槽位的范围（不包括玩家背包槽位）
     * @return 自定义槽位的索引范围
     */
    IntRange getContainerSlotRange();

    /**
     * 检查指定槽位是否是自定义槽位
     * @param slotIndex 槽位索引
     * @return 如果是自定义槽位返回true
     */
    default boolean isContainerSlot(int slotIndex) {
        IntRange range = getContainerSlotRange();
        return range != null && range.contains(slotIndex);
    }

    /**
     * 检查指定槽位是否是玩家背包槽位
     * @param slotIndex 槽位索引
     * @return 如果是玩家背包槽位返回true
     */
    default boolean isPlayerInventorySlot(int slotIndex) {
        IntRange range = getContainerSlotRange();
        return range != null && !range.contains(slotIndex);
    }
}
