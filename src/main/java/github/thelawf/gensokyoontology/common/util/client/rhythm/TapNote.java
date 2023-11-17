package github.thelawf.gensokyoontology.common.util.client.rhythm;

public class TapNote extends RhythmNote{
    public TapNote(int appearingTick) {
        super(appearingTick, NoteType.TAP);
    }

    @Override
    public int getAppearingTick() {
        return this.appearingTick;
    }

    @Override
    public void setAppearingTick(int appearingTick) {
        this.appearingTick = appearingTick;
    }
}
