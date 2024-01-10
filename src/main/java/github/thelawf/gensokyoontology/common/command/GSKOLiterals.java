package github.thelawf.gensokyoontology.common.command;

public enum GSKOLiterals {
    BLOCK_STATE("blockState"),
    CAPABILITY("capability");

    public final String name;

    GSKOLiterals(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String asString() {
        return this.name().toLowerCase();
    }
}
