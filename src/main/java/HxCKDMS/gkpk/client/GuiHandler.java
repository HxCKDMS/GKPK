package HxCKDMS.gkpk.client;

import HxCKDMS.gkpk.client.gui.GUIExtractor;
import HxCKDMS.gkpk.client.gui.GUIFermenter;
import HxCKDMS.gkpk.block.containers.ContainerExtractor;
import HxCKDMS.gkpk.block.containers.ContainerFermenter;
import HxCKDMS.gkpk.block.tile.TileEntityExtractor;
import HxCKDMS.gkpk.block.tile.TileEntityFermenter;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x,y,z);
        if (tile instanceof TileEntityFermenter) {
            return new ContainerFermenter(player, tile);
        } else if (tile instanceof TileEntityExtractor) {
            return new ContainerExtractor(player, tile);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tile = world.getTileEntity(x,y,z);
        if (tile instanceof TileEntityFermenter) {
            return new GUIFermenter(new ContainerFermenter(player, tile));
        } else if (tile instanceof TileEntityExtractor) {
            return new GUIExtractor(new ContainerExtractor(player, tile));
        }
        return null;
    }
}
