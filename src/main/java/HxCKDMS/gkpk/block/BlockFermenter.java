package HxCKDMS.gkpk.block;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.GordianCreativeTab;
import HxCKDMS.gkpk.tile.TileEntityFermenter;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockFermenter extends BlockContainer {

    public BlockFermenter()
    {
        super(Material.iron);
        this.setCreativeTab(GordianCreativeTab.gordianCreativeTab);
        setBlockName("gFermenter");
        setHardness(3F);
        this.setBlockTextureName("minecraft:sponge"); // Temp texture
    }

    @Override
    public TileEntity createNewTileEntity(World world, int thing) {
        return new TileEntityFermenter();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer ply, int metadata, float bx, float by, float bz) {
        TileEntity tile = world.getTileEntity(x,y,z);
        if (tile != null || !ply.isSneaking())
        {
        ply.openGui(GKPK.instance,0,world,x,y,z);
            return true;
        }
        return false;
    }
}
