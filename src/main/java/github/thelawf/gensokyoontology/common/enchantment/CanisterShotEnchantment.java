package github.thelawf.gensokyoontology.common.enchantment;

public class CanisterShotEnchantment extends DanmakuShotEnchant {
    protected CanisterShotEnchantment() {
        super(Rarity.RARE);
    }

    @Override
    public int getMaxLevel() {
        return 5;
    }
}
