package games.kingdoms.kingdoms;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.github.sanctum.labyrinth.data.BukkitGeneric;
import com.github.sanctum.labyrinth.data.FileList;
import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.admin.CustomNPCs.NPCTabCompleter;
import games.kingdoms.kingdoms.admin.CustomNPCs.PlayerMovementListener;
import games.kingdoms.kingdoms.admin.CustomNPCs.managers.NPCManager;
import games.kingdoms.kingdoms.admin.CustomNPCs.managers.QuestManager;
import games.kingdoms.kingdoms.admin.balance.EconomyCommand;
import games.kingdoms.kingdoms.admin.balance.EconomyTabCompleter;
import games.kingdoms.kingdoms.admin.customitems.MerchantCommand;
import games.kingdoms.kingdoms.admin.customitems.MerchantListener;
import games.kingdoms.kingdoms.admin.customores.CustomOreCommand;
import games.kingdoms.kingdoms.admin.customores.CustomOreTabCompleter;
import games.kingdoms.kingdoms.admin.gamemodes.Adventure;
import games.kingdoms.kingdoms.admin.gamemodes.Creative;
import games.kingdoms.kingdoms.admin.gamemodes.Spectator;
import games.kingdoms.kingdoms.admin.gamemodes.Survival;
import games.kingdoms.kingdoms.admin.ranks.Rank;
import games.kingdoms.kingdoms.admin.ranks.RankCMD;
import games.kingdoms.kingdoms.admin.ranks.RankTabCompleter;
import games.kingdoms.kingdoms.admin.reload.StaffReload;
import games.kingdoms.kingdoms.admin.staffchat.StaffChat;
import games.kingdoms.kingdoms.admin.staffchat.StaffChatTabCompleter;
import games.kingdoms.kingdoms.admin.staffvault.StaffVault;
import games.kingdoms.kingdoms.admin.staffvault.StaffVaultListener;
import games.kingdoms.kingdoms.admin.vanish.commands.VanishCMD;
import games.kingdoms.kingdoms.admin.vanish.events.JoinEvent;
import games.kingdoms.kingdoms.publiccmds.balance.BalanceCommand;
import games.kingdoms.kingdoms.publiccmds.balance.PayCommand;
import games.kingdoms.kingdoms.publiccmds.kingdoms.KingdomsCommands;
import games.kingdoms.kingdoms.publiccmds.kingdoms.KingdomsListener;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.KingdomsConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.MoneyConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.StaffConfig;
import games.kingdoms.kingdoms.publiccmds.nightvision.Commands;
import games.kingdoms.kingdoms.publiccmds.randomtp.RandomTeleportListener;
import games.kingdoms.kingdoms.publiccmds.randomtp.rtp;
import games.kingdoms.kingdoms.publiccmds.teleports.WarzoneCMD;
import games.kingdoms.kingdoms.publiccmds.teleports.WarzoneCommandListener;
import games.kingdoms.kingdoms.rankedcmds.feed.Feed;
import games.kingdoms.kingdoms.rankedcmds.fly.Fly;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public final class Kingdoms extends JavaPlugin implements Listener {

    private final ArrayList<Player> invisiblePlayers = new ArrayList<>();
    private final HashMap<String, String> claims = new HashMap<>();
    private List<ServerPlayer> npcs = new ArrayList<>();
    private HashMap<String, String> canUnclaim = new HashMap<>();
    private HashMap<String, String> canClaim = new HashMap<>();
    private HashMap<String, String> playerRank = new HashMap<>();
    private HashMap<String, String> owner = new HashMap<>();
    private HashMap<String, String> admin = new HashMap<>();
    private HashMap<String, String> member = new HashMap<>();
    private HashMap<String, String> inviteList = new HashMap<>();
    private HashMap<String, String> claimedChunks = new HashMap<>();
    private HashMap<String, Location> kingdomSpawn = new HashMap<>();
    private HashMap<String, String> staff = new HashMap<>();
    private HashMap<String, String> kingdoms = new HashMap<>();
    private HashMap<String, Long> money = new HashMap<>();
    private KingdomsConfig kingdomsConfig;
    private MoneyConfig moneyConfig;
    private StaffConfig staffConfig;
    private NPCManager npcManager;
    private QuestManager questManager;
    private static Kingdoms plugin;
    private FileList files;

    @Override
    public void onEnable() {

        plugin = this;
        kingdomsConfig = new KingdomsConfig(this);
        moneyConfig = new MoneyConfig(this);
        staffConfig = new StaffConfig(this);

        if (!kingdomsConfig.getConfig().exists()) {
            kingdomsConfig.setup();
        }
        if (!staffConfig.getConfig().exists()) {
            staffConfig.setup();
        }
        if (!moneyConfig.getConfig().exists()) {
            moneyConfig.setup();
        }

        npcManager = new NPCManager(this);
        questManager = new QuestManager();
        //Initialize ArrayLists and HashMaps
        files = FileList.search(this);
        npcs = new ArrayList<>();
        money = new HashMap<>();
        playerRank = new HashMap<>();
        kingdomSpawn = new HashMap<>();
        kingdoms = new HashMap<>();
        member = new HashMap<>();
        owner = new HashMap<>();
        admin = new HashMap<>();
        staff = new HashMap<>();
        inviteList = new HashMap<>();
        claimedChunks = new HashMap<>();
        canUnclaim = new HashMap<>();
        canClaim = new HashMap<>();

        //Restore Plugin Data
        restoreServerData();

        //Commands
        customNPCs();
        setRank();
        staffReload();
        nightVision();
        staffVault();
        Feed();
        Fly();
        warZone();
        Rtp();
        Vanish();
        kingdom();
        gameModes();
        rank();
        ore();
        Pay();
        Eco();
        balanceCMDs();
        Merchant();
        staffChat();

        //Events
        events();

        //Save Default Config
        this.saveDefaultConfig();

        //Plugin successfully loaded
        MessageManager.consoleGood("Kingdoms successfully Enabled");
    }

    @Override
    public void onDisable() {

        //Save Plugin Data
        savePluginData();

        //Plugin successfully disabled
        MessageManager.consoleBad("Kingdoms Successfully Disabled");
    }

    private void restoreServerData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player != null) {
                restorePluginData();
            }
        }
    }

    private void nightVision() {
        getCommand("nv").setExecutor(new Commands(this));
    }

    private void setRank() {
        getCommand("rank").setExecutor(new RankCMD(this));
    }

    private void customNPCs() {

        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        manager.addPacketListener(new PacketAdapter(this, PacketType.Play.Client.USE_ENTITY) {

            @Override
            public void onPacketReceiving(PacketEvent event) {

                PacketContainer packet = event.getPacket();

                int entityID = packet.getIntegers().read(0);

                //TODO: Add all of the NPCs through the Kody Simpson video
                //Guide
                if (entityID == npcManager.getGuideID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Guide menu

                    }

                } //Guide's Brother
                else if (entityID == npcManager.getGuideBrotherID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Guide Brother menu

                    }

                } //Merchant
                else if (entityID == npcManager.getMerchantID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Merchant menu

                    }

                } //Nature Merchant
                else if (entityID == npcManager.getNatureMerchantID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Nature Merchant menu

                    }

                } //Schematic Merchant
                else if (entityID == npcManager.getSchematicMerchantID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Schematic Merchant menu

                    }

                } //Glass Selling Merchant
                else if (entityID == npcManager.getGlassSellerID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Glass Seller menu

                    }

                } //PostBox NPC
                else if (entityID == npcManager.getPostBoxID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Postbox menu

                    }

                } //Forsaken Forge NPC
                else if (entityID == npcManager.getForgeID()) {

                    EnumWrappers.Hand hand = packet.getEnumEntityUseActions().read(0).getHand();
                    EnumWrappers.EntityUseAction action = packet.getEnumEntityUseActions().read(0).getAction();

                    if (hand == EnumWrappers.Hand.MAIN_HAND && action == EnumWrappers.EntityUseAction.INTERACT) {
                        //Open Forsaken Forge menu

                    }
                }

            }
        });

        getCommand("npc").setExecutor(new NPCManager(this));
        getCommand("npc").setTabCompleter(new NPCTabCompleter());
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerMovementListener(this), this);
    }

    private void staffVault() {
        getCommand("staffvault").setExecutor(new StaffVault());
        Bukkit.getServer().getPluginManager().registerEvents(new StaffVaultListener(), this);
    }

    private void staffChat() {
        getCommand("staffchat").setExecutor(new StaffChat(this));
        getCommand("staffchat").setTabCompleter(new StaffChatTabCompleter());
    }

    private void balanceCMDs() {
        getCommand("balance").setExecutor(new BalanceCommand(this));
    }

    private void Feed() {
        getCommand("feed").setExecutor(new Feed());
    }

    private void staffReload() {
        getCommand("staffreload").setExecutor(new StaffReload());
    }

    private void Eco() {
        getCommand("eco").setExecutor(new EconomyCommand(this));
        getCommand("eco").setTabCompleter(new EconomyTabCompleter());
    }

    private void Pay() {
        getCommand("pay").setExecutor(new PayCommand(this));
    }

    private void Fly() {
        getCommand("fly").setExecutor(new Fly(this));
    }

    private void warZone() {
        getCommand("warzone").setExecutor(new WarzoneCMD(this));
    }

    private void Vanish() {
        getCommand("vanish").setExecutor(new VanishCMD(this));
    }

    private void Rtp() {
        getCommand("rtp").setExecutor(new rtp());
    }

    private void kingdom() {
        getCommand("kingdom").setExecutor(new KingdomsCommands(this));
    }

    private void rank() {
        getCommand("rank").setExecutor(new RankCMD(this));
        getCommand("rank").setTabCompleter(new RankTabCompleter(this));
    }

    private void Merchant() {
        getCommand("merchant").setExecutor(new MerchantCommand(this));
        Bukkit.getServer().getPluginManager().registerEvents(new MerchantListener(this), this);
    }

    private void gameModes() {
        getCommand("gmc").setExecutor(new Creative(this));
        getCommand("gms").setExecutor(new Survival(this));
        getCommand("gmsp").setExecutor(new Spectator(this));
        getCommand("gma").setExecutor(new Adventure(this));
    }

    private void ore() {
        getCommand("ore").setExecutor(new CustomOreCommand(this));
        getCommand("ore").setTabCompleter(new CustomOreTabCompleter(this));
    }

    //Player is in a chunk of the kingdom they are in
    public void inPlayersKingdomBoard(Player player, Chunk chunk) {
        if (claimedChunks.get(chunk.getX() + "," + chunk.getZ()).equals(kingdoms.get(player.getUniqueId().toString()))) {
            long moneyValue = money.get(player.getUniqueId().toString());
            String formattedMoney;
            DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
            Date date = new Date();

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
            //Scoreboard Name
            Objective obj = board.registerNewObjective("inChunk", "dummy", ChatColor.translateAlternateColorCodes('&', "&e&lKingdoms"));
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);
            Score divider = obj.getScore("-----------------------");
            divider.setScore(15);
            Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
            p.setScore(14);
            //Player rank
            Score rank = obj.getScore("Rank " + playerRank.get(player.getUniqueId().toString()));
            rank.setScore(13);
            //Coins
            if (moneyValue == 0) {
                Score coins = obj.getScore("Coins " + ChatColor.GOLD + 0);
                coins.setScore(12);
            } else {
                if (moneyValue == 1) {
                    formattedMoney = "1";
                } else if (moneyValue < 1_000.0) {
                    formattedMoney = String.valueOf(moneyValue);
                } else if (moneyValue < 1_000_000.0) {
                    formattedMoney = String.format("%.3fK", moneyValue / 1_000.0);
                } else if (moneyValue < 1_000_000_000.0) {
                    formattedMoney = String.format("%.3fM", moneyValue / 1_000_000.0);
                } else if (moneyValue < 1_000_000_000_000.0) {
                    formattedMoney = String.format("%.3fB", moneyValue / 1_000_000_000.0);
                } else if (moneyValue < 1_000_000_000_000_000.0) {
                    formattedMoney = String.format("%.3fT", moneyValue / 1_000_000_000_000.0);
                } else if (moneyValue < 1_000_000_000_000_000_000.0) {
                    formattedMoney = String.format("%.3fQ", moneyValue / 1_000_000_000_000_000.0);
                } else if (moneyValue < 1_000_000_000_000_000_000_000.0) {
                    formattedMoney = String.format("%.3fQU", moneyValue / 1_000_000_000_000_000_000.0);
                } else {
                    formattedMoney = String.format("%.3fS", moneyValue / 1_000_000_000_000_000_000_000.0);
                }
                Score coins = obj.getScore("Coins " + ChatColor.GOLD + formattedMoney);
                coins.setScore(11);
            }

            //Kill/Death Ratio
            Score kdr = obj.getScore("KDR " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS) + ChatColor.WHITE + "/" + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
            kdr.setScore(10);
            //edit this to show how many challenges have been completed
            Score challenges = obj.getScore(ChatColor.WHITE + "Challenges " + ChatColor.YELLOW + "WIP");
            challenges.setScore(9);
            Score blank = obj.getScore(" ");
            blank.setScore(8);
            Score kingdom = obj.getScore(ChatColor.GOLD.toString() + ChatColor.BOLD + "Kingdom " + ChatColor.WHITE + ChatColor.BOLD + kingdoms.get(player.getUniqueId().toString()));
            kingdom.setScore(7);

            int memberCount = 0;

            for (String playerName : member.keySet()) {
                if (member.get(playerName).equals(kingdoms.get(player.getUniqueId().toString()))) {
                    memberCount++;
                }
            }

            Score members = obj.getScore("Members " + ChatColor.YELLOW + memberCount);
            members.setScore(6);


            for (String chunkID : claimedChunks.keySet()) {
                if (claimedChunks.get(chunkID).equals(kingdoms.get(player.getUniqueId().toString()))) {
                    // This chunk is claimed by the player's kingdom
                    // You can increment the claim count and update the scoreboard here
                    int claimCount = 0;
                    for (String otherChunkID : claimedChunks.keySet()) {
                        if (claimedChunks.get(otherChunkID).equals(kingdoms.get(player.getUniqueId().toString()))) {
                            claimCount++;
                        }
                    }
                    Score claims = obj.getScore("Claims " + ChatColor.YELLOW + claimCount);
                    claims.setScore(5);
                }
            }


            Score energy = obj.getScore("Energy " + ChatColor.YELLOW + "0.0/0" + ChatColor.AQUA + " 0");
            energy.setScore(4);
            Score separator = obj.getScore(ChatColor.WHITE + "-----------------------");
            separator.setScore(3);
            Score user = obj.getScore(ChatColor.RED + player.getName() + " " + ChatColor.GRAY + dateFormat.format(date));
            user.setScore(2);
            Score server_ip = obj.getScore(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "play.kingdoms.games");
            server_ip.setScore(1);
            player.setScoreboard(board);
        }
    }

    //Player is not in a chunk owned by their kingdom
    public void notInPlayersKingdomBoard(Player player) {
        long moneyValue = money.get(player.getUniqueId().toString());
        String formattedMoney = money.get(player.getUniqueId().toString()).toString();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        //Scoreboard Name
        Objective obj = board.registerNewObjective("notInClaim", "dummy", ChatColor.translateAlternateColorCodes('&', "&e&lKingdoms"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score divider = obj.getScore("-----------------------");
        divider.setScore(14);
        Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
        p.setScore(13);
        //Player rank
        Score rank = obj.getScore("Rank " + playerRank.get(player.getUniqueId().toString()));
        rank.setScore(12);
        //Coins
        if (moneyValue == 0) {
            Score coins = obj.getScore("Coins " + ChatColor.GOLD + 0);
            coins.setScore(11);
        } else {
            if (moneyValue == 1) {
                formattedMoney = "1";
            } else if (moneyValue < 1_000.0) {
                formattedMoney = String.valueOf(moneyValue);
            } else if (moneyValue < 1_000_000.0) {
                formattedMoney = String.format("%.3fK", moneyValue / 1_000.0);
            } else if (moneyValue < 1_000_000_000.0) {
                formattedMoney = String.format("%.3fM", moneyValue / 1_000_000.0);
            } else if (moneyValue < 1_000_000_000_000.0) {
                formattedMoney = String.format("%.3fB", moneyValue / 1_000_000_000.0);
            } else if (moneyValue < 1_000_000_000_000_000.0) {
                formattedMoney = String.format("%.3fT", moneyValue / 1_000_000_000_000.0);
            } else if (moneyValue < 1_000_000_000_000_000_000.0) {
                formattedMoney = String.format("%.3fQ", moneyValue / 1_000_000_000_000_000.0);
            } else if (moneyValue < 1_000_000_000_000_000_000_000.0) {
                formattedMoney = String.format("%.3fQU", moneyValue / 1_000_000_000_000_000_000.0);
            } else {
                formattedMoney = String.format("%.3fS", moneyValue / 1_000_000_000_000_000_000_000.0);
            }
            Score coins = obj.getScore("Coins " + ChatColor.GOLD + formattedMoney);
            coins.setScore(11);
        }

        //Kill/Death Ratio
        Score kdr = obj.getScore("KDR " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS) + ChatColor.WHITE + "/" + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
        kdr.setScore(10);
        //edit this to show how many challenges have been completed
        Score challenges = obj.getScore(ChatColor.WHITE + "Challenges " + ChatColor.YELLOW + "WIP");
        challenges.setScore(9);
        Score blank = obj.getScore(" ");
        blank.setScore(8);
        Score kingdom = obj.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "SERVER");
        kingdom.setScore(7);
        Score online = obj.getScore("Online " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
        online.setScore(6);
        Score online_staff = obj.getScore("Staff " + ChatColor.YELLOW + staff.size());
        online_staff.setScore(5);
        Score PvP_setting = obj.getScore(ChatColor.DARK_RED + "PvP " + ChatColor.GRAY + "[" + ChatColor.RED + "OFF" + ChatColor.GRAY + "]");
        PvP_setting.setScore(4);
        Score separator = obj.getScore(ChatColor.WHITE + "-----------------------");
        separator.setScore(3);
        Score user = obj.getScore(ChatColor.RED + player.getName() + " " + ChatColor.GRAY + dateFormat.format(date));
        user.setScore(2);
        Score server_ip = obj.getScore(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "play.kingdoms.games");
        server_ip.setScore(1);
        player.setScoreboard(board);
    }

    //Player is not in a kingdom
    public void notInKingdomBoard(Player player) {
        long moneyValue = money.get(player.getUniqueId().toString());
        String formattedMoney = money.get(player.getUniqueId().toString()).toString();
        Chunk chunk = player.getLocation().getChunk();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();

        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        //Scoreboard Name
        Objective obj = board.registerNewObjective("notInKingdom", "dummy", ChatColor.translateAlternateColorCodes('&', "&e&lKingdoms"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score divider = obj.getScore("-----------------------");
        divider.setScore(14);
        Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
        p.setScore(13);
        //Rank
        Score rank = obj.getScore("Rank " + playerRank.get(player.getUniqueId().toString()));
        rank.setScore(12);
        //Coins
        if (moneyValue == 0) {
            Score coins = obj.getScore("Coins " + ChatColor.GOLD + 0);
            coins.setScore(11);
        } else {
            if (moneyValue == 1) {
                formattedMoney = "1";
            } else if (moneyValue < 1_000.0) {
                formattedMoney = String.valueOf(moneyValue);
            } else if (moneyValue < 1_000_000.0) {
                formattedMoney = String.format("%.3fK", moneyValue / 1_000.0);
            } else if (moneyValue < 1_000_000_000.0) {
                formattedMoney = String.format("%.3fM", moneyValue / 1_000_000.0);
            } else if (moneyValue < 1_000_000_000_000.0) {
                formattedMoney = String.format("%.3fB", moneyValue / 1_000_000_000.0);
            } else if (moneyValue < 1_000_000_000_000_000.0) {
                formattedMoney = String.format("%.3fT", moneyValue / 1_000_000_000_000.0);
            } else if (moneyValue < 1_000_000_000_000_000_000.0) {
                formattedMoney = String.format("%.3fQ", moneyValue / 1_000_000_000_000_000.0);
            } else if (moneyValue < 1_000_000_000_000_000_000_000.0) {
                formattedMoney = String.format("%.3fQU", moneyValue / 1_000_000_000_000_000_000.0);
            } else {
                formattedMoney = String.format("%.3fS", moneyValue / 1_000_000_000_000_000_000_000.0);
            }
            Score coins = obj.getScore("Coins " + ChatColor.GOLD + formattedMoney);
            coins.setScore(11);
        }
        //Kill/Death Ratio
        Score kdr = obj.getScore("KDR " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS) + ChatColor.WHITE + "/" + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
        kdr.setScore(10);
        //edit this to show how many challenges have been completed
        Score challenges = obj.getScore(ChatColor.WHITE + "Challenges " + ChatColor.YELLOW + "WIP");
        challenges.setScore(9);
        Score blank = obj.getScore(" ");
        blank.setScore(8);
        Score kingdom = obj.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "SERVER");
        kingdom.setScore(7);
        Score online = obj.getScore("Online " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
        online.setScore(6);
        Score online_staff = obj.getScore("Staff " + ChatColor.YELLOW + staff.size());
        online_staff.setScore(5);
        Score PvP_setting = obj.getScore(ChatColor.DARK_RED + "PvP " + ChatColor.GRAY + "[" + ChatColor.RED + "OFF" + ChatColor.GRAY + "]");
        PvP_setting.setScore(4);
        Score separator = obj.getScore(ChatColor.WHITE + "-----------------------");
        separator.setScore(3);
        Score user = obj.getScore(ChatColor.RED + player.getName() + " " + ChatColor.GRAY + dateFormat.format(date));
        user.setScore(2);
        Score server_ip = obj.getScore(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "play.kingdoms.games");
        server_ip.setScore(1);
        player.setScoreboard(board);
    }

    //TODO: Figure out if this is necessary
//    private String getClaimedChunkName(Location location) {
//        // Replace this with your logic to get the claimed chunk name based on the location
//        return "ClaimedChunkName"; // Replace with actual logic
//    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();

        Chunk fromChunk = event.getFrom().getChunk();
        Chunk toChunk = event.getTo().getChunk();

        if (!fromChunk.equals(toChunk)) {

            // The player entered a new chunk

            String title, subtitle;

            if (isChunkClaimed(toChunk)) {
                String ownerKingdom = claimedChunks.get(toChunk.getX() + "," + toChunk.getZ());

                title = " ";
                subtitle = ChatColor.GREEN + ownerKingdom;
            } else {
                title = " ";
                subtitle = ChatColor.RED + "Wilderness";
            }

            sendTitle(player, title, subtitle, 10, 40, 10);
        }

        if (!fromChunk.equals(toChunk)) {
//            String ownerKingdom = claimedChunks.get(toChunk.getX() + "," + toChunk.getZ());
            if (kingdoms.containsKey(player.getUniqueId().toString())) {
                //TODO: Figure out how to calculate if the player is in a chunk that is owned by their kingdom
                if (isChunkClaimed(toChunk)) {
                    inPlayersKingdomBoard(player, toChunk);
                } else {
                    notInPlayersKingdomBoard(player);
                }

            } else {
                notInKingdomBoard(player);
            }
        }
    }

    @EventHandler
    public void onCommandSend(PlayerCommandSendEvent event) {

        for (Player player : Bukkit.getOnlinePlayers()) {

            if (kingdoms.containsKey(player.getUniqueId().toString())) {
                notInPlayersKingdomBoard(player);

            } else {
                notInKingdomBoard(player);
                return;
            }
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        savePluginData(player);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String chunkID = player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ();
        if (!player.hasPlayedBefore()) {
            notInKingdomBoard(player);

            playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            money.put(player.getUniqueId().toString(), 0L);

        } else {
            //TODO: Comment the three lines below this out
//            money.put(player.getUniqueId().toString(), 0L);
//            playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
//            kingdoms.put(player.getUniqueId().toString(), "FallenKingdom");

            restorePluginData(player);

            if (kingdoms.containsKey(player.getUniqueId().toString())) {
                if (claimedChunks.get(chunkID).equals(kingdoms.get(player.getUniqueId().toString()))) {
                    inPlayersKingdomBoard(player, player.getLocation().getChunk());
                } else {
                    notInPlayersKingdomBoard(player);
                }
            } else {
                notInKingdomBoard(player);
            }
        }
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {

        String eventMessage = event.getMessage();
        Player player = event.getPlayer();
        if (kingdoms.containsKey(player.getUniqueId().toString())) {
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.WHITE + player.getDisplayName() + ": " + ChatColor.WHITE + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(Rank.YOUTUBE.getColor().toString() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName() + " " + ChatColor.DARK_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
        } else {
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.GRAY + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.GREEN + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.AQUA + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(Rank.YOUTUBE.getColor().toString() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + Rank.YOUTUBE.getColor() + ChatColor.BOLD + Rank.YOUTUBE.getName() + Rank.YOUTUBE.getSecondaryColor() + ChatColor.BOLD + Rank.YOUTUBE.getSecondaryName() + " " + ChatColor.DARK_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.YELLOW + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + " " + ChatColor.LIGHT_PURPLE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN.toString())) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + playerRank.get(player.getUniqueId().toString()) + ChatColor.LIGHT_PURPLE + " " + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
                event.setFormat(format);
            }
        }
        event.setMessage(eventMessage);
    }

    // Method to check if a chunk is claimed
    private boolean isChunkClaimed(Chunk chunk) {
        String chunkID = chunk.getX() + "," + chunk.getZ();
        return claimedChunks.containsKey(chunkID);
    }

    // Method to send a title to a player
    public void sendTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut) {
        player.sendTitle(title, subtitle, fadeIn, stay, fadeOut);
    }


    public Map<String, String> getPlayerRank() {
        return playerRank;
    }

    public HashMap<String, String> getClaims() {
        return claims;
    }

    public HashMap<String, String> getClaimedChunks() {
        return claimedChunks;
    }

    public HashMap<String, String> getOwner() {
        return owner;
    }

    public HashMap<String, String> getMember() {
        return member;
    }

    public HashMap<String, String> getStaff() {
        return staff;
    }

    public HashMap<String, String> getKingdoms() {
        return kingdoms;
    }

    public static Kingdoms getPlugin() {
        return plugin;
    }

    public NPCManager getNpcManager() {
        return npcManager;
    }

    public QuestManager getQuestManager() {
        return questManager;
    }

    public ArrayList<Player> getInvisiblePlayers() {
        return invisiblePlayers;
    }

    public HashMap<String, String> getCanClaim() {
        return canClaim;
    }

    public HashMap<String, String> getCanUnclaim() {
        return canUnclaim;
    }

    public HashMap<String, Location> getKingdomSpawn() {
        return kingdomSpawn;
    }

    public HashMap<String, String> getAdmin() {
        return admin;
    }

    public HashMap<String, String> getInviteList() {
        return inviteList;
    }

    public HashMap<String, Long> getMoney() {
        return money;
    }

    public List<ServerPlayer> getNpcs() {
        return npcs;
    }

    //TODO: Figure out how to check if the custom config files contain the data
    public void restorePluginData() {

        for (Player player : Bukkit.getOnlinePlayers()) {

//            FileManager kingdomFile = files.get("Kingdoms", "Data", YamlExtension.INSTANCE);
//            FileManager staffFile = files.get("Staff", "Data", YamlExtension.INSTANCE);
//            FileManager moneyFile = files.get("Money", "Data", YamlExtension.INSTANCE);
            Configurable kc = kingdomsConfig.getConfig();
            Configurable sc = staffConfig.getConfig();
            Configurable mc = moneyConfig.getConfig();

            if (kc != null) {
                if (kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).exists()) {
                    kingdoms.put(player.getUniqueId().toString(), kc.getNode("kingdoms." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("owners").getNode(player.getUniqueId().toString()).exists()) {
                    owner.put(player.getUniqueId().toString(), kc.getNode("owners." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("admins").getNode(player.getUniqueId().toString()).exists()) {
                    admin.put(player.getUniqueId().toString(), kc.getNode("admins." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("members").getNode(player.getUniqueId().toString()).exists()) {
                    member.put(player.getUniqueId().toString(), kc.getNode("members." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("canClaim").getNode(player.getUniqueId().toString()).exists()) {
                    canClaim.put(player.getUniqueId().toString(), kc.getNode("canClaim." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("canUnclaim").getNode(player.getUniqueId().toString()).exists()) {
                    canUnclaim.put(player.getUniqueId().toString(), kc.getNode("canUnclaim." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("spawns").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    kingdomSpawn.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("spawns." + kingdoms.get(player.getUniqueId().toString())).toGeneric(BukkitGeneric.class).getLocation());
                }

                if (kc.getNode("claims").getNode(claims.get(kingdoms.get(player.getUniqueId().toString()))).exists()) {
                    if (kingdoms.containsKey(player.getUniqueId().toString())) {
                        claimedChunks.put(kc.getNode("claims." + claims.get(kingdoms.get(player.getUniqueId().toString()))).toPrimitive().getString(), kingdoms.get(player.getUniqueId().toString()));
                    }
                }
            }

            if (mc != null) {
                if (mc.getNode("balance").getNode(player.getUniqueId().toString()).exists()) {
                    money.put(player.getUniqueId().toString(), mc.getNode("balance." + player.getUniqueId().toString()).toPrimitive().getLong());
                } else {
                    money.put(player.getUniqueId().toString(), 0L);
                }
            }

            if (sc != null) {
                if (sc.getNode("rank").getNode(player.getUniqueId().toString()).exists()) {
                    playerRank.put(player.getUniqueId().toString(), sc.getNode("rank." + player.getUniqueId().toString()).toPrimitive().getString());
                } else {
                    playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                }
                if (sc.getNode("staff").getNode(player.getUniqueId().toString()).exists()) {
                    staff.put(player.getUniqueId().toString(), sc.getNode("staff." + player.getUniqueId().toString()).toPrimitive().getString());
                }
            }
        }
    }

    public void restorePluginData(Player player) {

//        FileManager kingdomFile = files.get("Kingdoms", "Data", YamlExtension.INSTANCE);
//        FileManager staffFile = files.get("Staff", "Data", YamlExtension.INSTANCE);
//        FileManager moneyFile = files.get("Money", "Data", YamlExtension.INSTANCE);
        Configurable kc = kingdomsConfig.getConfig();
        Configurable sc = staffConfig.getConfig();
        Configurable mc = moneyConfig.getConfig();

        if (kc != null) {
            if (kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).exists()) {
                kingdoms.put(player.getUniqueId().toString(), kc.getNode("kingdoms." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("owners").getNode(player.getUniqueId().toString()).exists()) {
                owner.put(player.getUniqueId().toString(), kc.getNode("owners." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("admins").getNode(player.getUniqueId().toString()).exists()) {
                admin.put(player.getUniqueId().toString(), kc.getNode("admins." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("members").getNode(player.getUniqueId().toString()).exists()) {
                member.put(player.getUniqueId().toString(), kc.getNode("members." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("canClaim").getNode(player.getUniqueId().toString()).exists()) {
                canClaim.put(player.getUniqueId().toString(), kc.getNode("canClaim." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("canUnclaim").getNode(player.getUniqueId().toString()).exists()) {
                canUnclaim.put(player.getUniqueId().toString(), kc.getNode("canUnclaim." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("spawns").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                kingdomSpawn.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("spawns." + kingdoms.get(player.getUniqueId().toString())).toGeneric(BukkitGeneric.class).getLocation());
            }
            for (Chunk chunk : Bukkit.getWorld("kingdoms").getLoadedChunks()) {
                if (kc.getNode("claims").getNode(claimedChunks.get(chunk.getX() + "," + chunk.getZ())).exists()) {
                    if (kingdoms.containsKey(player.getUniqueId().toString())) {
                        claimedChunks.put(kc.getNode("claims." + kingdoms.get(chunk.getX() + "," + chunk.getZ())).toPrimitive().getString(), kingdoms.get(player.getUniqueId().toString()));
                    }
                }
            }
        }

        if (mc != null) {
            if (mc.getNode("balance").getNode(player.getUniqueId().toString()).exists()) {
                money.put(player.getUniqueId().toString(), mc.getNode("balance." + player.getUniqueId().toString()).toPrimitive().getLong());
            } else {
                money.put(player.getUniqueId().toString(), 0L);
            }
        }

        if (sc != null) {
            if (sc.getNode("rank").getNode(player.getUniqueId().toString()).exists()) {
                playerRank.put(player.getUniqueId().toString(), sc.getNode("rank." + player.getUniqueId().toString()).toPrimitive().getString());
            } else {
                playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            }
            if (sc.getNode("staff").getNode(player.getUniqueId().toString()).exists()) {
                staff.put(player.getUniqueId().toString(), sc.getNode("staff." + player.getUniqueId().toString()).toPrimitive().getString());
            }
        }
    }

    public void savePluginData(Player player) {
        if (!kingdoms.isEmpty()) {
            if (kingdoms.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> kingdoms : kingdoms.entrySet()) {
                        config.set("kingdoms." + player.getUniqueId().toString(), kingdoms.getValue());
                    }
                }
                config.save();
            }
        }
        if (!owner.isEmpty()) {
            if (admin.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> owners : owner.entrySet()) {
                        config.set("owners." + player.getUniqueId().toString(), owners.getValue());
                    }
                }
                config.save();
            }
        }
        if (!admin.isEmpty()) {
            if (admin.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> admins : admin.entrySet()) {
                        config.set("admins." + player.getUniqueId().toString(), admins.getValue());
                    }
                }
                config.save();
            }
        }
        if (!member.isEmpty()) {
            if (member.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> members : member.entrySet()) {
                        config.set("members." + player.getUniqueId().toString(), members.getValue());
                    }
                }
                config.save();
            }
        }

        if (!inviteList.isEmpty()) {
            if (inviteList.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> invites : inviteList.entrySet()) {
                        config.set("invites." + player.getUniqueId().toString(), invites.getValue());
                    }
                }
                config.save();
            }
        }

        if (!canClaim.isEmpty()) {
            if (canClaim.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> canClaim : canClaim.entrySet()) {
                        config.set("canClaim." + player.getUniqueId().toString(), canClaim.getValue());
                    }
                }
                config.save();
            }
        }

        if (!canUnclaim.isEmpty()) {
            if (canUnclaim.containsKey(player.getUniqueId().toString())) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> canUnclaim : canUnclaim.entrySet()) {
                        config.set("canUnclaim." + player.getUniqueId().toString(), canUnclaim.getValue());
                    }
                }
                config.save();
            }
        }

        if (!kingdomSpawn.isEmpty()) {
            if (kingdomSpawn.containsKey(kingdoms.get(player.getUniqueId().toString()))) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, Location> spawns : kingdomSpawn.entrySet()) {
                        config.set("spawns." + kingdoms.get(player.getUniqueId().toString()), spawns.getValue());
                    }
                }
                config.save();
            }
        }

        if (!claimedChunks.isEmpty()) {
            if (claimedChunks.containsKey(kingdoms.get(player.getUniqueId().toString()))) {
                Configurable config = kingdomsConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> claims : claimedChunks.entrySet()) {
                        config.set("claims." + claims.getKey(), claims.getValue());
                    }
                }
                config.save();
            }
        }

        if (!money.isEmpty()) {
            if (money.containsKey(player.getUniqueId().toString())) {
                Configurable config = moneyConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, Long> money : money.entrySet()) {
                        config.set("balance." + money.getKey(), money.getValue());
                    }
                }
                config.save();
            }
        }

        if (!playerRank.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> rank : playerRank.entrySet()) {
                    config.set("rank." + rank.getKey(), rank.getValue());
                }
            }
            config.save();
        }

        if (!staff.isEmpty()) {
            if (staff.containsKey(player.getUniqueId().toString())) {
                Configurable config = staffConfig.getConfig();
                if (config != null) {
                    for (Map.Entry<String, String> staff : staff.entrySet()) {
                        config.set("staff." + staff.getKey(), staff.getValue());
                    }
                }
                config.save();
            }
        }
    }

    public void savePluginData() {
        if (!kingdoms.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> kingdoms : kingdoms.entrySet()) {
                    config.set("kingdoms." + kingdoms.getKey(), kingdoms.getValue());
                }
            }
            config.save();
        }
        if (!owner.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> owners : owner.entrySet()) {
                    config.set("owners." + owners.getKey(), owners.getValue());
                }
            }
            config.save();
        }
        if (!admin.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> admins : admin.entrySet()) {
                    config.set("admins." + admins.getKey(), admins.getValue());
                }
            }
            config.save();
        }
        if (!member.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> members : member.entrySet()) {
                    config.set("members." + members.getKey(), members.getValue());
                }
            }
            config.save();
        }

        if (!inviteList.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> invites : inviteList.entrySet()) {
                    config.set("invites." + invites.getKey(), invites.getValue());
                }
            }
            config.save();
        }

        if (!canClaim.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> canClaim : canClaim.entrySet()) {
                    config.set("canClaim." + canClaim.getKey(), canClaim.getValue());
                }
            }
            config.save();
        }

        if (!canUnclaim.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> canUnclaim : canUnclaim.entrySet()) {
                    config.set("canUnclaim." + canUnclaim.getKey(), canUnclaim.getValue());
                }
            }
            config.save();
        }

        if (!kingdomSpawn.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Location> spawns : kingdomSpawn.entrySet()) {
                    config.set("spawns." + spawns.getKey(), spawns.getValue());
                }
            }
            config.save();
        }

        if (!claimedChunks.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> claims : claimedChunks.entrySet()) {
                    config.set("claims." + claims.getKey(), claims.getValue());
                }
            }
            config.save();
        }

        if (!money.isEmpty()) {
            Configurable config = moneyConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Long> money : money.entrySet()) {
                    config.set("balance." + money.getKey(), money.getValue());
                }
            }
            config.save();
        }

        if (!playerRank.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> rank : playerRank.entrySet()) {
                    config.set("rank." + rank.getKey(), rank.getValue());
                }
            }
            config.save();
        }

        if (!staff.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> staff : staff.entrySet()) {
                    config.set("staff." + staff.getKey(), staff.getValue());
                }
            }
            config.save();
        }
    }

    private void events() {
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WarzoneCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new CustomOreCommand(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomsListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RandomTeleportListener(this), this);
    }
}