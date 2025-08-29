package github.thelawf.gensokyoontology.common.enchantment;

public class SpeedShotEnchantment extends DanmakuShotEnchant {

    protected SpeedShotEnchantment() {
        super(Rarity.RARE);
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }
}
