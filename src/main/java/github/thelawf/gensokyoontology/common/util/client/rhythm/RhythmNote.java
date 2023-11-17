package github.thelawf.gensokyoontology.common.util.client.rhythm;

public abstract class RhythmNote implements IRhythmNote{

    public int speed;
    protected int appearingTick;
    public NoteType noteType;

    public RhythmNote(int appearingTick, NoteType noteType) {
        this.appearingTick = appearingTick;
        this.noteType = noteType;
    }

    public void modifyResponseTick(int tick) {
        this.appearingTick += tick;
    }

    public NoteType getNoteType() {
        return noteType;
    }

    public void setNoteType(NoteType noteType) {
        this.noteType = noteType;
    }
}
