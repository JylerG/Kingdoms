package games.kingdoms.kingdoms.admin.CustomNPCs.managers;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import games.kingdoms.kingdoms.Kingdoms;
import net.minecraft.network.protocol.game.ClientboundAddPlayerPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class NPCManager implements CommandExecutor {
    private final Kingdoms plugin;

    private ServerPlayer merchantNPC;
    private ServerPlayer postBox;
    private ServerPlayer Guide;
    private ServerPlayer guideBrother;
    private ServerPlayer natureMerchant;
    private ServerPlayer glassSeller;
    private ServerPlayer schematicSeller;
    private ServerPlayer forsakenForge;

    public NPCManager(Kingdoms plugin) {
        this.plugin = plugin;
    }

    @Override
    @SuppressWarnings("SpellCheckingInspection")
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("kingdoms.npc")) {
                if (args.length == 1) {
                    CraftPlayer craftPlayer = (CraftPlayer) player;
                    ServerPlayer sp = craftPlayer.getHandle();

                    MinecraftServer server = sp.getServer();
                    ServerLevel level = sp.getLevel();

                    //GameProfile objects
                    GameProfile merchant = new GameProfile(UUID.randomUUID(), ChatColor.GOLD.toString() + ChatColor.BOLD + "Merchant");
                    GameProfile postbox = new GameProfile(UUID.randomUUID(), ChatColor.WHITE.toString() + ChatColor.BOLD + "Post" + ChatColor.BLUE + ChatColor.BOLD + "Box");
                    GameProfile guide = new GameProfile(UUID.randomUUID(), ChatColor.GOLD.toString() + ChatColor.BOLD + "Guide");
                    GameProfile guide_brother = new GameProfile(UUID.randomUUID(), "");
                    GameProfile nature_merchant = new GameProfile(UUID.randomUUID(), ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Nature");
                    GameProfile glass_seller = new GameProfile(UUID.randomUUID(), ChatColor.DARK_GREEN.toString() + ChatColor.BOLD + "Glass Seller");
                    GameProfile schematic_seller = new GameProfile(UUID.randomUUID(), ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + "Schematics");
                    GameProfile forge = new GameProfile(UUID.randomUUID(), ChatColor.GOLD.toString() + ChatColor.BOLD + "The Forsaken");

                    //ServerPlayer objects
                    ServerPlayer merchantNPC = new ServerPlayer(server, level, merchant);
                    ServerPlayer postBox = new ServerPlayer(server, level, postbox);
                    ServerPlayer Guide = new ServerPlayer(server, level, guide);
                    ServerPlayer guideBrother = new ServerPlayer(server, level, guide_brother);
                    ServerPlayer natureMerchant = new ServerPlayer(server, level, nature_merchant);
                    ServerPlayer glassSeller = new ServerPlayer(server, level, glass_seller);
                    ServerPlayer schematicSeller = new ServerPlayer(server, level, schematic_seller);
                    ServerPlayer forsakenForge = new ServerPlayer(server, level, forge);

                    ServerGamePacketListenerImpl ps = sp.connection;

                    switch (args[0].toLowerCase()) {
                        case "merchant":

                            String signature = "N+S/T8Zc+WlMj0flLk55CDq+DCpXslrgLgEQQihd7cpEEYHh648kazrD83iz0qh6h2/4dH9+58adIhYhX9JlXM6Px0ak7OERpmVQNO/eaKngMtWELrASEA+Qr36855IDYbtaUtm9MisDYxstEtgF8jw97tdoZ+4klDXEB+mNnNAmghbve+vQU0Vd+WqOcHrevcgRxCFLAaCIzT7+a0N1tT1ztfGBqvma8PG2x7SZBmto9tY+Ol1F5Dgq1Fvknbddknyg0W0ZJxf15VQNar3K/cD9uvgp3Jfvgyk4K4rUpS1CWh0qQDIT+KsaRs1vqU0nwKHm9HP99oNJIakeaX61mAUkSu7mhZvmHaij6PUUtOvOgycoFqjwdSnJ4Ccnun9w5V/QcdanppSwiTsMjaBnv7u8M0Q0C2nUykqOv1TZcpf7FPzcSiHZJ0J80QMeJZMW6vV8gzrfUEvgkVQS0/ecPPp1pgCRGgDSAdE3DMWhhMjRz+P3Au1DblS2n2/oWZkTe92GmdvlPgqJol9y8e+v3Xp0JqNZGqRUVrtFFpcakWtHd+eGW0Ji2jf2j2mMaFtpL68j0h01Uj4pL82Y7cPcyo7rm/KyHfY+MQJ5/RHwhrrw6GoRDWkLn8So/xpFjPehCdYcPvpYgdg/NKxQNVudxIQ2gsrk986PuKKwmDdMOmY=";
                            String texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5OTEwMzAyMCwKICAicHJvZmlsZUlkIiA6ICI5YTM2ZDg3ODhjMjQ0MjMyODEzNWFjOWNhMDE5ZDk4YyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDb2RlZF9EZXYiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTE1YWYzMjA4ZTdmODRmMDM1ZTQ0MGZkOGY2NTRlYThmMGFiNmE2ZWM5YjM2M2VmNGI0Mzk0YzBjYzgxYzgyMSIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0=";

                            merchant.getProperties().put("textures", new Property("textures", texture, signature));

                            merchantNPC.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, merchantNPC));
                            ps.send(new ClientboundAddPlayerPacket(merchantNPC));
                            player.sendMessage("You spawned a " + ChatColor.GOLD + ChatColor.BOLD + "Merchant");
                            plugin.getNpcs().add(merchantNPC);
                            this.merchantNPC = merchantNPC;
                            break;

                        case "postbox":

                            signature = "vRm4yKHomzGpnn+APgwT4hlm/WXCMvcE9R5bbNJoyYeR90C+FokhUQ/snydnigd/whRTI6CH7Sz9/8zNflBFXitbwyvye+akWccbkSjMZM5ZulA7Xl/Tl5cp56dKIUwB8CVdncgzPeLT3e1tQfsUP/QydkHbjWJrbnG85UpwNU9QsNZajknn8fHDxGDbFeFpWCnWGx5V76BcI5bkua4b/KMNpEWPAYE/b2VdGz1xeu2hIP+OUJWuy6qOC0uiuprLQti4n9q6JwjH2dwNvQ0nszWaagn1QLqnBqL3cs8kCMAphdTUWOFAIgkFj8V0zVHbyrBzvJJgQ+5ddkdDccWOQZRkJ6i3nR2TBAO10aPPwAHo5UDezEIzsQpsiwhrBmaD+N70GSUd30yJtTHB314kWw2ATo8KvOXGwoHqQLbF5x6X0RNb2LisBszvZMxf3TV8PZIcqK2GzYcQfAgiri8TNwBynY0vfK8yX5K8UqwCtffKsr7umLEogJEZia5luEXCLCmbcUy3ZujUXKda27Hu700etovt1O/SkI9FscKV7ISBjrWXyZk3xLOG8mrdmV/IeF/B9U82Bf33uQvzKxBYJasherm2Qdj2f4swzs8ahJ3ReuGafOP1QZ+3T3qVCNNysvi1z+zeOtjhjWu1OXN/m0n2brUcDJJo+ptUIOmAPog=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5OTM0MTAzMCwKICAicHJvZmlsZUlkIiA6ICJhMTg4YWQ0YmI1OWU0YTExOTQ4NTg1MjIyYWM4NTI4ZiIsCiAgInByb2ZpbGVOYW1lIiA6ICJleGl0aXgiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzcwOGVmMThhODgwNWQyMWZlYzQyYTdhODA5YTBjYzFkMDkyMzgwMWM0OTBiODRiZTI4NTIxY2ViZmNhOWRlMiIKICAgIH0sCiAgICAiQ0FQRSIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjM0MGMwZTAzZGQyNGExMWIxNWE4YjMzYzJhN2U5ZTMyYWJiMjA1MWIyNDgxZDBiYTdkZWZkNjM1Y2E3YTkzMyIKICAgIH0KICB9Cn0=";

                            postbox.getProperties().put("textures", new Property("textures", texture, signature));

                            postBox.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, postBox));
                            ps.send(new ClientboundAddPlayerPacket(postBox));
                            player.sendMessage("You spawned a " + ChatColor.BOLD + "Post " + ChatColor.BLUE + ChatColor.BOLD + "Box");
                            plugin.getNpcs().add(postBox);
                            this.postBox = postBox;
                            break;

                        case "guide":

                            signature = "RcQfnJUGY7Yi6n5T3iynQJG3lpGQFgEkseOW6WiZQgMo77sgmEPH+Wsd241unUKCdQ/i4fDJ3tvYt+vwyBszQbSpQX1wi+OuV/XdHCd6dCGf6E2isnCSdBvzK+iWCXHWZEFeqc3O9R7Lpd1Zo1OoQt9OAl5fCjfF9+51cL0WZiIbkIfo82hpHLr6RRJE5aYW/c3Qefg9APWpA8b2XKwv1kHRVjbjVaxdpDFUoNesii/H0RaGpZjbD95ilVNbYWbe8RwWSi5xkpNEyvwttEZC4ZcaDUREA7fXgQAA19Jfgw6YkbXQnTZ7V9p42f7u/Hm90WukYxHQE4TANDHBdKY6b3wGGjtIewSq0Is52xGzGrXGv1DBOa6/I7GSDMPVSTsiEywVWIGdlCmYeLd92t63UWwGy0wWD+zAffT2a3Xj9/JxQuRdVnMQheDakPocVM3In6EPK7DJanEPAGV+KfQQJpz3JTf0lx92yizSiLKmN2rACFTU+KRbhDQ+kNWSgi7VvWUJH7qUcPt50/kWRLDHRma2ErPRAcUtb5cX6AeefGzbkl8HnnKRZOZHpiL5dqMjQdVeMqNueutxak/bX249EOR2ovjCDozyYvQrfb9L8A/JNNofrAm0N6dNoVN5Y0bKwnfa1hl9p9F7ay/B5pkBOeEtstdxsUeMsqM5vEq9pdI=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5OTIxNTA0MCwKICAicHJvZmlsZUlkIiA6ICJjNGE3ZjA2OWNjNzk0ZGFlYjcwZmJlNmZlZWEzN2YzMyIsCiAgInByb2ZpbGVOYW1lIiA6ICJDbGlwbG93IiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQwZWE5ZWE1OTEwNTNjMDkxZjA3YTQ1NjlmNDRmMGRjNzIzZDcyNDU4YmUyZTgyNWViYzkzNTFlNmY1NDA2MGQiCiAgICB9CiAgfQp9";

                            guide.getProperties().put("textures", new Property("textures", texture, signature));

                            Guide.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, Guide));
                            ps.send(new ClientboundAddPlayerPacket(Guide));
                            plugin.getNpcs().add(Guide);
                            this.Guide = Guide;
                            break;

                        case "guide_brother":

                            signature = "njuxShEZyjYVWCs/lGxy76/exGpzIPPQenUQPdNAiTzPSGydqvxc2Ob6PMlV4VDQKYW0q/wJYfKp0GTDePRncqNzqnerMzyEytyL7YomfAPa1uw1EpfzBG00FHR18kPqQ1bVuFQD5JbIbGkwsgSr7daiGHtbM3/g0JsK9fTxUcYDUeHqkVQxqiHVlWrX0z8YNX2GRX+WSNX9574oBxaWMYpfGz3EKBSLYwZ1FUmgTIzSmWk2Zvgnjdq5BYp2Qy8QObWC3ZPa5pmLJG+o20Hl9DtLQALfnjclBpcFBJyPK9ekbQVTCR2mjW/+sdqeGl4Vq0ZsNC0wJ6W7PXN3pRc0KSBNeXK3uQf6igqme1a7MJkRrSMWZMj+FfL0M/mf0KPN99vgyOGocYwhGO60T2VjbgwI43LqhFaAlEbHlwwYZe10M9I0B666LuL0LPSiJVU6jozC4ycMkNwgCcl3GhsUSUxvUadzMg5YoNYssiYoqpFpvRjZoNO9tV8kM+VVSjjqh4W1lATJADVtZT7rF/kd5MB/DpHabL2QMdhJ8Q0iUD0js5av+kVS37VDWweYtnRG1yyVkEH0m3k5cauzCCJ7L/CgAkVd+uwSQCB27YuOKg8ZFfn5EdxpkzsBk0Aoqe7pgnKMlAveeGIhSiUe1jQj0P0q1vAG5KUsWrY3Ox7D640=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5OTI2NzYzOSwKICAicHJvZmlsZUlkIiA6ICJkZTQ4MTI5Yzg0NTU0NzEzOTZiYThmZWE4NjA4Y2NiYiIsCiAgInByb2ZpbGVOYW1lIiA6ICJidWlsZHM0OTgzIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzhkZTg3N2IyZGU2NDQ2MDEyOTgxMjYxMTQ5YjFjNjdkZjIzZTEyYjFhMTlkYWU1MTY5NzEyMDkxYWUzOGM3OTkiCiAgICB9CiAgfQp9";

                            guide_brother.getProperties().put("textures", new Property("textures", texture, signature));

                            guideBrother.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, guideBrother));
                            ps.send(new ClientboundAddPlayerPacket(guideBrother));
                            player.sendMessage("You spawned the " + ChatColor.GOLD + ChatColor.BOLD + "Guide's Brother");
                            plugin.getNpcs().add(guideBrother);
                            this.guideBrother = guideBrother;
                            break;

                        case "nature_merchant":

                            signature = "s5CU2Whua32sWL+Rzrgvf2NFQxAhc0PqpmYqTNhuRazVdcY+l3FQZwjtNJCiDZQpFshNBdH7YNWskGtO0+NozFd5kIlanDuvIk3+KJq9sHM6/mBtt2weg6+gxPcXpO2ZUYSJVw+VxfjZNdYFsgO3Sdu2IhDgBNiOXD2yyC6bJNk31OXmYM2wBbcEApS+gHlYeq7kp7iw5eBH3A2wNaDjuVDQ6YfI6100tnmMR2LHgqwOWlCHKURs/YtELyOp0l3dZOzPJ308E9rDgZxJz7nkHScn21wrLNBY8tobnN/4aNCMmrXSlesDZl4I7uxWVA56SjHNa+7MnqX7m1h0jei0UW984R/AOmx/IUuTwXZt0+nXgUs4Uf/MvM40k3CNXJq/vB4kt+FcZ+bYF5Rg7PmTvAJ7wRAR01qsBmN/hwihjmWuYmXhcgLJ5pXE8to6kQm/kBFtZX/r91yyuTHQRfz10Vo8uNMf72uyYx3bWpjnv5kfxFsgfRgJg4GE/9se4LSAQuRcKR9wW3WsWFVVbNAfY5xeZf5u8CUNxNP3VXe+72CxRnp/ZHcp8n0Lv32XAxBfiSIFxithZItpX/ubRwEti3K7QlnSi/UCww4EiZesviEftcuVdZcBw9G8tVhfqkcuym+bh5QPR1PE8mi/xZo/ED3/MAZKHg9OrTcr2lFotOI=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzcwMDE2MTE3OSwKICAicHJvZmlsZUlkIiA6ICI4MDdjMWQxYmIxNmQ0YzkwYTdkMDg5NzkzZDkxZjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJMb3ZpbmdIaXBwaWUiLAogICJzaWduYXR1cmVSZXF1aXJlZCIgOiB0cnVlLAogICJ0ZXh0dXJlcyIgOiB7CiAgICAiU0tJTiIgOiB7CiAgICAgICJ1cmwiIDogImh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmU3NTI4MmQ1MTliMzNhOWM3ZTRkY2IyOTkyYWUyYTg5YzZkOTY3NzJiY2U5Yjc5MTU3MjVmODhiZDJlYzUzZSIsCiAgICAgICJtZXRhZGF0YSIgOiB7CiAgICAgICAgIm1vZGVsIiA6ICJzbGltIgogICAgICB9CiAgICB9CiAgfQp9";

                            nature_merchant.getProperties().put("textures", new Property("textures", texture, signature));

                            natureMerchant.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, natureMerchant));
                            ps.send(new ClientboundAddPlayerPacket(natureMerchant));
                            player.sendMessage("You spawned a " + ChatColor.DARK_GREEN + ChatColor.BOLD + "Nature Merchant");
                            plugin.getNpcs().add(natureMerchant);
                            this.natureMerchant = natureMerchant;
                            break;

                        case "glass_seller":

                            signature = "QnN62SGoNVE4WPEDFM4MzNx2Q0D95gkhQYbz3Jspc+rtqpgk6jrAcIYNzxUWGUagMFPBSpCiCTiRZmaAA7TJYsZAu9AckegaE8TI0u+gILoLw/1N38R3yeW1ETUNkounZpsioUqrXjjRUDWDGYwRRU3G1y9zNG4KusVhlFOUEjHxPqUtTo39ZRZTSz0r7JqRSVOS6dyzWOzNHxd8rK8fYkd9yjUQK3y3+ob3CG0+Cylp8Ci3p4aOQON383UspTDLGWgeMYCQ3DwHXNtx/Y5XNpjQLf5pzERsMla1CIRtGIr8eW15kcluCI74pNYmNq/RH0+bTF/JT1llb7VHBuxPxMKHBT+JUpV1+9zf9Smpob0J1p1jBOC42RtIzVlGOIjBkDdcfNGWCbs7xSwxQkJ89RdTrus4BhtuDPdTwuip6BANQFZx531rLeMtKmfXaMNg7uQ68BX9p7QGHmQ3RHm+z/Ysmokt8T+5uiPmWeyjUs3i+eenJ9zOEKa2m+j4TOwR9+bfpRtFE+dBhIbY7alOch+G8+c1pZGGs3aJWyCMYmxLwlZdw7dChLnJWPt7JF1b+YwrYC5QQ1HlWwf6yjLlisLZyBS9vI1weecvnvISZJu7VTygPj5475YajUEVMKY05p7FyPnztUlar+QTRYFCTr/FNjDng2lSESRXOrU9cQc=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5OTQ5NTE5NCwKICAicHJvZmlsZUlkIiA6ICI4YTk1NjFiMzU0NDE0ODM1OTgzMGViMjljMmUzYTc4YiIsCiAgInByb2ZpbGVOYW1lIiA6ICJrYXlwbG9wIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZhNTc0YWM5NjJkYWYxMmI1MWE3ZjVmNDk1NjMxYzU4YmQxYzMxY2FmOGU5NThiMTc3NGIyYTgwZjE0NWIzNzciLAogICAgICAibWV0YWRhdGEiIDogewogICAgICAgICJtb2RlbCIgOiAic2xpbSIKICAgICAgfQogICAgfSwKICAgICJDQVBFIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS8yMzQwYzBlMDNkZDI0YTExYjE1YThiMzNjMmE3ZTllMzJhYmIyMDUxYjI0ODFkMGJhN2RlZmQ2MzVjYTdhOTMzIgogICAgfQogIH0KfQ==";

                            glass_seller.getProperties().put("textures", new Property("textures", texture, signature));

                            glassSeller.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, glassSeller));
                            ps.send(new ClientboundAddPlayerPacket(glassSeller));
                            plugin.getNpcs().add(glassSeller);
                            this.glassSeller = glassSeller;
                            break;

                        case "schematic_seller":

                            signature = "Fc7SBNhKOZc+Ebu6P5BXWxIN20U9EI3niIpR4peX+iksCyZeMtOvdgd8SAr6lOqfClZj0lAi9IkXXlbAiW/bu64R0X1y9GwaAbbE9BvrbCNngbyDJgIlXWGLjtTZdwaNAwlaDHo0XcZeLKaILn5D/lcCBvltj/nbi3wQR80gWlNH/M/rQAQ4MpjzdwyPigi/ChD9vUrtpnhiapj/IpOsI6DK8Dg/KQz98rFS4TCe32JW++hQEiXsS8oBWIaXqxHQzcLUmkYPdh7bvhTHexQuXJbunIXalWqgu3drd7f9/0yu9gqeLR0A9HlSvA6VEqJjzsw62tMt1GblMEHMjsJNQiHj8DnFFdotc4seuQRNO9pOrsR7PyWj1kKZfoOqn7ydLk4UQxYo9uumsnbKRMWrEjJuM3Akf/Oey71sg7UDnSoiI9kiSXgFO9ZVosFnFjp09qakbphsBoea8jltGDYULDh4NJkEagHc0Xx9GsxMYyV8cP8L17xP2W9tvxPOvlYqhC4pxZoaHIP6H5g+ZHb4q6sOeT9ez/UP/JLmAxkZZFHY+UX/530EdJLVJhuij4bxztgVU6dejOMjwjH3ShbZAzqzVR+UAs4KjdRRl9BxTfXWjJWY1R9b0gCJiEg+JPA2NFac+e5wwhaAGi15NI0nRQpxue7qrCI+TGRH0w/TvdQ=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5OTQyODc4NiwKICAicHJvZmlsZUlkIiA6ICI1ZGYzYjBlNDk5NTk0ZmNhOWMyYjY0Zjk3YWY5Mzg4OSIsCiAgInByb2ZpbGVOYW1lIiA6ICJiZWxlbml1bSIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ODk1ZDFiYzY4YmVhOThmZjhlNGIxZTI0YTJmMjVhYzEzMmYyODYyODU4MTU5NmVjOTQ5OGVmZGZmNjYwZTQxIgogICAgfQogIH0KfQ==";

                            schematic_seller.getProperties().put("textures", new Property("textures", texture, signature));

                            schematicSeller.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, schematicSeller));
                            ps.send(new ClientboundAddPlayerPacket(schematicSeller));
                            player.sendMessage("You spawned a " + ChatColor.DARK_AQUA + ChatColor.BOLD + "Schematic Seller");
                            plugin.getNpcs().add(schematicSeller);
                            this.schematicSeller = schematicSeller;
                            break;

                        case "forsaken_forge":

                            signature = "ZInCshDej45F1sSEtKM6I6EoLnKOW1c3Zjkdnhss3XmI56A6EiWuJJQJzQUBR6jpAPy4Kk9+9OkMNKzycvlrq55IV7kBJUXY2y/yQIpxWUJweviUEkiXsmDykGO7HiABOh/4DbA52MdfVLIb2Nzh3QjlkZjQlqZUlPHTqpuZwkl6CrpB31gLlXLxTatf0O80Rtmst44TIndVzJ5kEk90NA9dlzjnc2gApQEVuPfIfEMf6+6O300OHHDChQtLtvvnrO+RAZHd9/D04N+JXm1wvEFrmcKgmMzCDSM1D1QhUoUVRTPpTpWGZgpJiho+aEWP6aeSJ4Y3hY0MkEsTq2ekeageGa+EnldMWgUmbcSC946ZJMCbRMseO7gO2jHUkgOCBa1Blggjj8CotApkfi6lUQvPDWpsvyoiuDZ/jGlBoevDI7lo5Dr9EvWb6FcE+No1viITfZi8eJCk/ZmdNcFZohUubBl3ZUIWxz8AxnAvY1U7jH8vbtv8OWgpkyxkcGHTSg8DujKz/KiyJLKqcRbLkSKp7L6k54EW9d0NJThNlXqXWH3hlpfp6RaTXZrFGf0zbqYbIEz/zCUZ8hhVaxuUgkxEGH11FuOrJ9cUc61kSGMFiEAlTYKxwM1LhPUVLNP/4JxP/2xIpgEe5LON/HhB13B8KdddRIBeEou65XRAKaE=";
                            texture = "ewogICJ0aW1lc3RhbXAiIDogMTcwNzY5ODU1NzE4MSwKICAicHJvZmlsZUlkIiA6ICIxMzY4MmJjMTBmZmM0ODA2OTg0MjExNjEwNTg2YWVkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJUaGUwcGFjS25pZ2h0XyIsCiAgInNpZ25hdHVyZVJlcXVpcmVkIiA6IHRydWUsCiAgInRleHR1cmVzIiA6IHsKICAgICJTS0lOIiA6IHsKICAgICAgInVybCIgOiAiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS80ODVjZTQxYmY1YjQ0ZTQ0YWViNjMwMjVjNTUzMDMxOWE0NzAzNWExZDU2MGRkMjc2MTc3MmYxOThiYTdkYTI4IiwKICAgICAgIm1ldGFkYXRhIiA6IHsKICAgICAgICAibW9kZWwiIDogInNsaW0iCiAgICAgIH0KICAgIH0KICB9Cn0=";

                            forge.getProperties().put("textures", new Property("textures", texture, signature));


                            forsakenForge.setPos(player.getLocation().getBlockX(), player.getLocation().getBlockY(), player.getLocation().getBlockZ());
                            ps.send(new ClientboundPlayerInfoPacket(ClientboundPlayerInfoPacket.Action.ADD_PLAYER, forsakenForge));
                            ps.send(new ClientboundAddPlayerPacket(forsakenForge));
                            player.sendMessage("You spawned a " + ChatColor.GOLD + ChatColor.BOLD + "Forsaken Forge " + ChatColor.WHITE + ChatColor.BOLD + "NPC");
                            plugin.getNpcs().add(forsakenForge);
                            this.forsakenForge = forsakenForge;
                            break;

                        default:
                        player.sendMessage(ChatColor.GOLD + "Usage: /npc <npc>");
                        break;
                    }
                }
            }
        }
        return true;
    }

    public int getMerchantID() {
        return (this.merchantNPC == null) ? 0 : this.merchantNPC.getId();
    }

    public int getPostBoxID() {
        return (this.postBox == null) ? 0 : this.postBox.getId();
    }

    public int getGuideID() {
        return (this.Guide == null) ? 0 : this.Guide.getId();
    }

    public int getGuideBrotherID() {
        return (this.guideBrother == null) ? 0 : this.guideBrother.getId();
    }

    public int getNatureMerchantID() {
        return (this.natureMerchant == null) ? 0 : this.natureMerchant.getId();
    }

    public int getGlassSellerID() {
        return (this.glassSeller == null) ? 0 : this.glassSeller.getId();
    }

    public int getSchematicMerchantID() {
        return (this.schematicSeller == null) ? 0 : this.schematicSeller.getId();
    }

    public int getForgeID() {
        return (this.forsakenForge == null) ? 0 : this.forsakenForge.getId();
    }
}
