package HxCKDMS.gkpk.block;

import HxCKDMS.gkpk.GKPK;
import HxCKDMS.gkpk.client.GordianCreativeTab;
import HxCKDMS.gkpk.block.tile.TileEntityFermenter;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockFermenter extends BlockContainer {
    @SideOnly(Side.CLIENT)
    private IIcon[] bSidedTextureArray;

    public BlockFermenter()
    {
        super(Material.iron);
        this.setCreativeTab(GordianCreativeTab.gordianCreativeTab);
        setBlockName("gFermenter");
        setHardness(3F);
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister){
        bSidedTextureArray = new IIcon[6];

        bSidedTextureArray[0] = iconRegister.registerIcon("GKPK" + ":blockBottom");
        bSidedTextureArray[1] = iconRegister.registerIcon("GKPK" + ":blockFermenterTop");
        bSidedTextureArray[2] = iconRegister.registerIcon("GKPK" + ":blockFermenterFace");
        bSidedTextureArray[3] = iconRegister.registerIcon("GKPK" + ":blockSides");
        bSidedTextureArray[4] = iconRegister.registerIcon("GKPK" + ":blockSides");
        bSidedTextureArray[5] = iconRegister.registerIcon("GKPK" + ":blockSides");
    }
    @Override
    public IIcon getIcon(int side, int metadata){
        ForgeDirection sDirection = ForgeDirection.getOrientation(side);
        ForgeDirection mDirection = ForgeDirection.getOrientation(metadata);
        if (mDirection == ForgeDirection.DOWN)
            mDirection = ForgeDirection.SOUTH;

        ForgeDirection lDirection = getSideLeftToCurrentDirection(mDirection);

        if(sDirection == mDirection)
            return bSidedTextureArray[2];

        if (sDirection == mDirection.getOpposite())
            return bSidedTextureArray[3];

        if(sDirection == ForgeDirection.DOWN)
            return bSidedTextureArray[3];

        if(sDirection == ForgeDirection.UP)
            return bSidedTextureArray[1];

        if(sDirection == lDirection)
            return bSidedTextureArray[5];

        if(sDirection == lDirection.getOpposite())
            return bSidedTextureArray[4];


        return null;
    }

    private ForgeDirection getSideLeftToCurrentDirection(ForgeDirection direction){
        switch (direction){
            case NORTH:
                return ForgeDirection.WEST;
            case SOUTH:
                return ForgeDirection.EAST;
            case WEST:
                return ForgeDirection.SOUTH;
            case EAST:
                return ForgeDirection.NORTH;
            default:
                return ForgeDirection.UNKNOWN;
        }
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack) {
        int l = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if(l == 0)
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        if(l == 1)
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        if(l == 2)
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        if(l == 3)
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
    }
}
