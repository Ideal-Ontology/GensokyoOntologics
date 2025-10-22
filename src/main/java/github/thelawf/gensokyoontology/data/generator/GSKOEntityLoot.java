package github.thelawf.gensokyoontology.data.generator;

import github.thelawf.gensokyoontology.core.init.EntityRegistry;
import github.thelawf.gensokyoontology.core.init.ItemRegistry;
import net.minecraft.data.loot.EntityLootTables;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.conditions.KilledByPlayer;

public class GSKOEntityLoot extends EntityLootTables {

    @Override
    protected void registerLootTable(EntityType<?> type, LootTable.Builder table) {
        super.registerLootTable(type, table);
    }

    @Override
    protected void addTables() {
        registerLootTable(EntityRegistry.FAIRY_ENTITY.getId(), FAIRY_LOOT);
        // registerLootTable(EntityRegistry.KOISHI_ENTITY.getId(), KOISHI_LOOT);
    }

    public static final LootTable.Builder FAIRY_LOOT = LootTable.builder()
            .addLootPool(LootPool.builder()
                    .rolls(ConstantRange.of(1))
                    .addEntry(ItemLootEntry.builder(ItemRegistry.BOMB_ITEM.get()))
                    .acceptCondition(KilledByPlayer.builder()));

    public static final LootTable.Builder KOISHI_LOOT = LootTable.builder()
            .addLootPool(LootPool.builder()
                    .rolls(ConstantRange.of(1))
                    .addEntry(ItemLootEntry.builder(ItemRegistry.KOISHI_EYE_OPEN.get()))
                    .acceptCondition(KilledByPlayer.builder()));

    public static final LootTable.Builder YUKARI_LOOT = LootTable.builder();

    public static final LootTable.Builder MEME_LOOT = LootTable.builder();

    public static final LootTable.Builder SPECTRE_LOOT = LootTable.builder();
}
