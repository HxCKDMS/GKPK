package HxCKDMS.gkpk.client.gui;

import HxCKDMS.gkpk.containers.ContainerExtractor;
import HxCKDMS.gkpk.tile.TileEntityExtractor;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static HxCKDMS.gkpk.data.TimeConst.EXTRACTOR_TIME;

public class GUIExtractor extends GuiContainer {
    TileEntityExtractor extractor;
    public GUIExtractor(Container extractor) {
        super(extractor);
        this.extractor = ((ContainerExtractor)extractor).tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.getTextureManager().bindTexture(new ResourceLocation("GKPK" + ":textures/gui/egui.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int progress = Math.round(24 * ((EXTRACTOR_TIME - extractor.processing) / (float)EXTRACTOR_TIME));
        if (progress <= 0 || extractor.processing <= 0) progress = 0;
        drawTexturedModalRect(xStart + 87, yStart + 33, 180, 0, progress, 19);
    }
}
