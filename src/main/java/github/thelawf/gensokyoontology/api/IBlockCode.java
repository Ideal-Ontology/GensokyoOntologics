package github.thelawf.gensokyoontology.api;

import javax.annotation.Nonnull;

public interface IBlockCode {
    int java = 0;
    int python = 1;
    int c = 2;
    int cPlus = 3;
    int cSharp = 4;
    int html = 5;
    int css = 6;
    int js = 7;
    int xml = 8;
    int php = 9;
    int go = 10;
    @Nonnull
    String getCode();

}
