package github.thelawf.gensokyoontology.common.libs.logoslib;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class BlockCodeRenderer extends JBlockCode{
    String scriptsIn;

    @Override
    @Nonnull
    public String getCode() {
        return scriptsIn;
    }

    @Override
    public int getJava() {
        return java;
    }

    @Override
    public boolean isJava() {
        return true;
    }
}
