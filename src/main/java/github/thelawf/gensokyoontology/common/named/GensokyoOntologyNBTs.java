package github.thelawf.gensokyoontology.common.named;

import net.minecraft.block.Block;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tags.Tag;

public class GensokyoOntologyNBTs implements IGensokyoOntologyNBT {
    public static CompoundNBT nbtYattsume = new CompoundNBT();
    public static CompoundNBT nbtKoishiMousse = new CompoundNBT();
    public static CompoundNBT lovePotionMasterName = new CompoundNBT();
    int[] tagIntArrayYattsume = {CHEAP,SEAFOOD,FRESH};
    int[] tagIntArrayKoishiMousse = {HIGH_STANDARD,WESTERN,SWEET,PICTURALBE,FASCINATING};

    public static Tag<Block> blockTag;

    public GensokyoOntologyNBTs() {
        nbtKoishiMousse.putIntArray("Koishi Hat Mousse",tagIntArrayKoishiMousse);
        nbtYattsume.putIntArray("Yattsume Una Yaki",tagIntArrayYattsume);
    }

    public GensokyoOntologyNBTs(String playerNameIn){
        lovePotionMasterName.putString("love_potion", playerNameIn);
    }

    public static CompoundNBT getLovePotionMasterName() {
        return lovePotionMasterName;
    }
}
