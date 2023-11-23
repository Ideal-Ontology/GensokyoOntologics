package github.thelawf.gensokyoontology.common.entity.trade;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntArrayMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.merchant.villager.VillagerTrades;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MerchantOffer;
import net.minecraft.tags.ITag;
import net.minecraft.util.Util;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GSKOTrades {

    public static final Int2ObjectMap<VillagerTrades.ITrade[]> HUMAN_RESIDENT_TRADE = gatAsIntMap(ImmutableMap.of(
            1, new VillagerTrades.ITrade[]{}));

    static class ItemForCoinTrade implements VillagerTrades.ITrade{
        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return null;
        }
    }

    static class CoinForItemTrade implements VillagerTrades.ITrade {
        @Nullable
        @Override
        public MerchantOffer getOffer(Entity trader, Random rand) {
            return null;
        }
    }
    public static Int2ObjectMap<VillagerTrades.ITrade[]> gatAsIntMap(ImmutableMap<Integer, VillagerTrades.ITrade[]> p_221238_0_) {
        return new Int2ObjectOpenHashMap<>(p_221238_0_);
    }
}