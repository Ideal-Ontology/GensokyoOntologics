package github.thelawf.gensokyoontology.common.nbt;

import net.minecraft.nbt.CompoundNBT;

public class GensokyoOntologyNBT implements IGensokyoOntologyNBT {
    public static CompoundNBT nbtYattsume = new CompoundNBT();
    public static CompoundNBT nbtKoishiMousse = new CompoundNBT();
    public static CompoundNBT nbtWhiteSnow = new CompoundNBT();
    public static CompoundNBT lovePotionMasterName = new CompoundNBT();

    public static CompoundNBT nbtCanPlaceIn = new CompoundNBT();

    int[] tagIntArrayYattsume = {CHEAP,SEAFOOD,FRESH};
    int[] tagIntArrayKoishiMousse = {HIGH_STANDARD,WESTERN,SWEET,PICTURALBE,FASCINATING};
    int tagWhiteSnow = CULTURAL;

    String canPlaceIn = "cyber_statistic_dimension";

    public GensokyoOntologyNBT() {
        nbtKoishiMousse.putIntArray("Koishi Hat Mousse",tagIntArrayKoishiMousse);
        nbtYattsume.putIntArray("Yattsume Una Yaki",tagIntArrayYattsume);
        nbtWhiteSnow.putInt("white_snow",tagWhiteSnow);
        nbtCanPlaceIn.putString("can_place_in", canPlaceIn);
    }

    public GensokyoOntologyNBT(String playerNameIn){
        lovePotionMasterName.putString("love_potion", playerNameIn);
    }

    public static CompoundNBT getLovePotionMasterName() {
        return lovePotionMasterName;
    }
}
