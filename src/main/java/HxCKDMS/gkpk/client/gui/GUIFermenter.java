package HxCKDMS.gkpk.client.gui;

import HxCKDMS.gkpk.containers.ContainerFermenter;
import HxCKDMS.gkpk.tile.TileEntityFermenter;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static HxCKDMS.gkpk.data.TimeConst.FERMENTER_TIME;

public class GUIFermenter extends GuiContainer {
    TileEntityFermenter fermenter;
    public GUIFermenter(Container ferm) {
        super(ferm);
        fermenter = ((ContainerFermenter)ferm).tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y) {
        GL11.glColor4f(1f, 1f, 1f, 1f);
        mc.getTextureManager().bindTexture(new ResourceLocation("GKPK" + ":textures/gui/fgui.png"));
        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
        int progress = Math.round(24 * ((FERMENTER_TIME - fermenter.processing) / (float)FERMENTER_TIME));
        if (progress <= 0 || fermenter.processing <= 0) progress = 0;
        drawTexturedModalRect(xStart + 87, yStart + 33, 180, 0, progress, 19);
    }
}
