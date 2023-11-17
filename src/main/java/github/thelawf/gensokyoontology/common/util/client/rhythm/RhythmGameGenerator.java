package github.thelawf.gensokyoontology.common.util.client.rhythm;

import net.minecraft.world.Difficulty;

import java.util.List;

public class RhythmGameGenerator {
    private Difficulty difficulty;
    private float localDifficulty;
    private List<RhythmNote> notes;

    public RhythmGameGenerator(Difficulty difficulty, float localDifficulty) {
        this.difficulty = difficulty;
        this.localDifficulty = localDifficulty;
    }

    public void generate(){
        switch (this.difficulty) {
            case PEACEFUL:
                generateBasicRhythm();
                break;
            case EASY:
                generateAdvancedRhythm();
                break;
            case NORMAL:
                generateExpertRhythm();
                break;
            case HARD:
                generateMasterRhythm();
                break;
        }
    }

    public void generateBasicRhythm() {

    }
    public void generateAdvancedRhythm() {

    }
    public void generateExpertRhythm() {

    }
    public void generateMasterRhythm() {

    }
}
