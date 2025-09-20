package github.thelawf.gensokyoontology.api.client.layout;

import github.thelawf.gensokyoontology.api.client.IInputParser;
import github.thelawf.gensokyoontology.api.client.ITextBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GridLayoutScreen extends Screen implements IInputParser, ITextBuilder {
    protected List<Dimension> grid;
    protected int gridLeft;
    protected int gridTop;
    protected int rows;
    protected int cols;
    protected boolean centered;

    protected GridLayoutScreen(ITextComponent title, int colCount, int rowCount) {
        super(title);
        this.grid = new ArrayList<>(rowCount * colCount);
        this.gridLeft = 0;
        this.gridTop = 0;
        this.rows = rowCount;
        this.cols = colCount;
    }

    public void setCellInfo(Dimension dimension, int cellX, int cellY) {
        this.grid.replaceAll(d -> this.grid.indexOf(d) == cellX * this.rows + cellY - 1 ? dimension : d);
    }

    public Dimension getCellInfo(int cellX, int cellY) {
        return this.grid.get(cellX * this.cols + cellY - 1);
    }

    public int getRowCount(){
        return this.rows;
    }

    public int getColCount(){
        return this.cols;
    }
}
