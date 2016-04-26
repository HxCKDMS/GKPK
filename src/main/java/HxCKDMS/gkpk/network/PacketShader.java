package HxCKDMS.gkpk.network;

import HxCKDMS.HxCCore.api.AbstractPacket;
import HxCKDMS.gkpk.client.ShaderThing;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class PacketShader extends AbstractPacket {
    public PacketShader() {}
    private boolean enableShade;
    private String shadeString;
    public PacketShader(boolean enableShade) {
        this.enableShade = enableShade;
        this.shadeString = null;
    }
    public PacketShader(boolean enableShade, String shadeString) {
        this.enableShade = enableShade;
        this.shadeString = shadeString;
    }
    @Override
    public void encodeInto(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
    byteBuf.writeBoolean(enableShade);
        ByteBufUtils.writeUTF8String(byteBuf,shadeString);
    }

    @Override
    public void decodeInto(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        enableShade = byteBuf.readBoolean();
       shadeString = ByteBufUtils.readUTF8String(byteBuf);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void handleClientSide(EntityPlayer entityPlayer) {
        if (enableShade) {
            ShaderThing.setShader(new ResourceLocation(shadeString));
        }
        else{
            Minecraft.getMinecraft().entityRenderer.deactivateShader();
        }
    }

    @SideOnly(Side.SERVER)
    @Override
    public void handleServerSide(EntityPlayer entityPlayer) {}
}
