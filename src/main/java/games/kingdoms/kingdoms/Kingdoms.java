package games.kingdoms.kingdoms;

import com.github.sanctum.labyrinth.data.BukkitGeneric;
import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.admin.CustomNPCs.CreateNPCCommand;
import games.kingdoms.kingdoms.admin.CustomNPCs.NPCTabCompleter;
import games.kingdoms.kingdoms.admin.balance.EconomyCommand;
import games.kingdoms.kingdoms.admin.balance.EconomyTabCompleter;
import games.kingdoms.kingdoms.admin.customitems.customores.CustomOreCommand;
import games.kingdoms.kingdoms.admin.customitems.customores.CustomOreTabCompleter;
import games.kingdoms.kingdoms.admin.customitems.merchantitems.MerchantCommand;
import games.kingdoms.kingdoms.admin.customitems.merchantitems.MerchantListener;
import games.kingdoms.kingdoms.admin.gamemodes.Adventure;
import games.kingdoms.kingdoms.admin.gamemodes.Creative;
import games.kingdoms.kingdoms.admin.gamemodes.Spectator;
import games.kingdoms.kingdoms.admin.gamemodes.Survival;
import games.kingdoms.kingdoms.admin.npcinteractions.managers.*;
import games.kingdoms.kingdoms.admin.permissions.Permissions;
import games.kingdoms.kingdoms.admin.punishCMD.ConfirmPunishment;
import games.kingdoms.kingdoms.admin.punishCMD.PunishCommand;
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
import games.kingdoms.kingdoms.publiccmds.chats.ChatCMD;
import games.kingdoms.kingdoms.publiccmds.easter.EasterCommand;
import games.kingdoms.kingdoms.publiccmds.kingdoms.chat.KingdomsChat;
import games.kingdoms.kingdoms.publiccmds.kingdoms.chat.KingdomsChatTabCompleter;
import games.kingdoms.kingdoms.publiccmds.kingdoms.command.KingdomInviteList;
import games.kingdoms.kingdoms.publiccmds.kingdoms.command.KingdomsCommands;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.KingdomsConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.MoneyConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.PunishmentConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.configs.StaffConfig;
import games.kingdoms.kingdoms.publiccmds.kingdoms.listeners.KingdomUpgradeListener;
import games.kingdoms.kingdoms.publiccmds.kingdoms.related.KingdomsListener;
import games.kingdoms.kingdoms.publiccmds.nightvision.Commands;
import games.kingdoms.kingdoms.publiccmds.randomtp.RandomTeleportListener;
import games.kingdoms.kingdoms.publiccmds.randomtp.rtp;
import games.kingdoms.kingdoms.publiccmds.teleports.KingdomsCommandListener;
import games.kingdoms.kingdoms.publiccmds.teleports.SpawnCommand;
import games.kingdoms.kingdoms.publiccmds.teleports.WarzoneCMD;
import games.kingdoms.kingdoms.publiccmds.teleports.WarzoneCommandListener;
import games.kingdoms.kingdoms.publiccmds.whisper.WhisperCommand;
import games.kingdoms.kingdoms.publiccmds.whisper.WhisperCommandTabCompleter;
import games.kingdoms.kingdoms.rankedcmds.feed.Feed;
import games.kingdoms.kingdoms.rankedcmds.fly.Fly;
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

    private QuestManager questManager;
    private GuideManager guideManager;
    private BrotherManager brotherManager;
    private ForgeManager forgeManager;
    private GlassManager glassManager;
    private SchematicsManager schematicsManager;
    private NatureManager natureManager;
    private MerchantManager merchantManager;
    private KingdomsConfig kingdomsConfig;
    private MoneyConfig moneyConfig;
    private StaffConfig staffConfig;
    private PunishmentConfig punishmentConfig;
    private static Kingdoms plugin;
    private HashMap<String, Integer> staffCount = new HashMap<>();
    private HashMap<String, String> customRank = new HashMap<>();
    private HashMap<String, String> kingdomExists = new HashMap<>();
    private final ArrayList<Player> invisiblePlayers = new ArrayList<>();
    private ArrayList<Player> modModePlayers = new ArrayList<>();
    private HashMap<String, String> claims = new HashMap<>();
    private HashMap<String, Long> claimPrice = new HashMap<>();
    private HashMap<String, Long> memberPrice = new HashMap<>();
    private HashMap<String, String> bannedNames = new HashMap<>();
    private HashMap<String, String> canUnclaim = new HashMap<>();
    private HashMap<String, String> canClaim = new HashMap<>();
    private HashMap<String, String> playerRank = new HashMap<>();
    private HashMap<String, String> passwords = new HashMap<>();
    private HashMap<String, String> owner = new HashMap<>();
    private HashMap<String, String> chatFocus = new HashMap<>();
    private HashMap<String, String> admin = new HashMap<>();
    private HashMap<String, String> member = new HashMap<>();
    private HashMap<String, String> inviteList = new HashMap<>();
    private HashMap<String, String> claimedChunks = new HashMap<>();
    private HashMap<String, Location> kingdomSpawn = new HashMap<>();
    private HashMap<String, String> staff = new HashMap<>();
    private HashMap<String, String> kingdoms = new HashMap<>();
    private HashMap<String, Long> money = new HashMap<>();
    private HashMap<String, String> onlineStaff = new HashMap<>();
    private HashMap<String, Integer> maxMembers = new HashMap<>();
    private HashMap<String, Integer> maxClaims = new HashMap<>();
    private HashMap<String, Integer> ipAdverts = new HashMap<>();
    private HashMap<String, Integer> mediaAdverts = new HashMap<>();
    private HashMap<String, Integer> reportAbuse = new HashMap<>();
    private HashMap<String, Integer> disrespect = new HashMap<>();
    private HashMap<String, Integer> language = new HashMap<>();
    private HashMap<String, Integer> soliciting = new HashMap<>();
    private HashMap<String, Integer> spam = new HashMap<>();
    private HashMap<String, Integer> discrimination = new HashMap<>();
    private HashMap<String, Integer> threats = new HashMap<>();
    private static PunishCommand punish;
    private HashMap<String, String> playerToPunish = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();
//    private HashMap<String, Integer> ipAdverts = new HashMap<>();

    @Override
    public void onEnable() {

        //Save default config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        plugin = this;
        punish = new PunishCommand();
        punishmentConfig = new PunishmentConfig(this);
        kingdomsConfig = new KingdomsConfig(this);
        moneyConfig = new MoneyConfig(this);
        staffConfig = new StaffConfig(this);

        if (!punishmentConfig.getConfig().exists()) {
            punishmentConfig.setup();
        }
        if (!kingdomsConfig.getConfig().exists()) {
            kingdomsConfig.setup();
        }
        if (!staffConfig.getConfig().exists()) {
            staffConfig.setup();
        }
        if (!moneyConfig.getConfig().exists()) {
            moneyConfig.setup();
        }

        //Initialize ArrayLists and HashMaps
        initMapList();

        //Restore Plugin Data
        restoreServerData();

        //Commands
        chat();
        punishments();
        interactWithNPC();
        password();
        createNPC();
        setRank();
        staffReload();
        nightVision();
        staffVault();
        Feed();
        Fly();
        Spawns();
        Rtp();
        Vanish();
        kingdom();
        viewPerms();
        gameModes();
        rank();
        ore();
        Pay();
        Eco();
        balanceCMDs();
        Merchant();
        staffChat();
        kingdomChat();
        easter();
        whisper();
        warzone();

        //Events
        events();

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

    private void password() {
//        getCommand("password").setExecutor(new Password());
    }

    private void chat() {
        getCommand("chat").setExecutor(new ChatCMD());
    }

    private void punishments() {
        getCommand("punish").setExecutor(new PunishCommand());
        getCommand("confirm").setExecutor(new ConfirmPunishment());
    }

    private void initMapList() {
        threats = new HashMap<>();
        discrimination = new HashMap<>();
        mediaAdverts = new HashMap<>();
        ipAdverts = new HashMap<>();
        reportAbuse = new HashMap<>();
        disrespect = new HashMap<>();
        language = new HashMap<>();
        soliciting = new HashMap<>();
        spam = new HashMap<>();
        playerToPunish = new HashMap<>();

        chatFocus = new HashMap<>();
        money = new HashMap<>();
        passwords = new HashMap<>();
        modModePlayers = new ArrayList<>();
        kingdomExists = new HashMap<>();
        onlineStaff = new HashMap<>();
        memberPrice = new HashMap<>();
        claimPrice = new HashMap<>();
        maxMembers = new HashMap<>();
        maxClaims = new HashMap<>();
        claims = new HashMap<>();
        customRank = new HashMap<>();
        staffCount = new HashMap<>();
        bannedNames = new HashMap<>();
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
    }

    private void nightVision() {
        getCommand("nv").setExecutor(new Commands(this));
    }

    private void setRank() {
        getCommand("rank").setExecutor(new RankCMD(this));
    }

    private void kingdomChat() {
        getCommand("kingdomchat").setExecutor(new KingdomsChat());
        getCommand("kingdomchat").setTabCompleter(new KingdomsChatTabCompleter(this));
    }

    private void easter() {
        getCommand("easter").setExecutor(new EasterCommand());
    }

    private void warzone() {
        getCommand("warzone").setExecutor(new WarzoneCMD(this));
    }

    private void whisper() {
        getCommand("whisper").setExecutor(new WhisperCommand());
        getCommand("whisper").setTabCompleter(new WhisperCommandTabCompleter());
    }

    private void createNPC() {
        getCommand("npcs").setExecutor(new CreateNPCCommand());
        getCommand("npcs").setTabCompleter(new NPCTabCompleter());
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

    private void Spawns() {
        getCommand("spawn").setExecutor(new SpawnCommand(this, new KingdomsCommandListener(this)));
    }

    private void Vanish() {
        getCommand("vanish").setExecutor(new VanishCMD(this));
    }

    private void Rtp() {
        getCommand("rtp").setExecutor(new rtp());
    }

    private void interactWithNPC() {
        brotherManager = new BrotherManager();
        forgeManager = new ForgeManager();
        glassManager = new GlassManager();
        guideManager = new GuideManager();
        merchantManager = new MerchantManager();
        natureManager = new NatureManager();
        questManager = new QuestManager();
        schematicsManager = new SchematicsManager();
    }

    private void kingdom() {
        getCommand("kingdom").setExecutor(new KingdomsCommands(this));
        getCommand("kingdom").setTabCompleter(new KingdomInviteList());
    }

    private void viewPerms() {
        getCommand("view").setExecutor(new Permissions());
    }

    private void rank() {
        getCommand("rank").setExecutor(new RankCMD(this));
        getCommand("rank").setTabCompleter(new RankTabCompleter());
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
            Score divider = obj.getScore(" ");
            divider.setScore(15);
            Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
            p.setScore(14);
            //Player rank
            if (owner.containsKey(player.getUniqueId().toString())) {
                Score rank = obj.getScore("Rank " + ChatColor.LIGHT_PURPLE + "King");
                rank.setScore(13);
            }
            if (admin.containsKey(player.getUniqueId().toString())) {
                Score rank = obj.getScore("Rank " + ChatColor.LIGHT_PURPLE + "Knight");
                rank.setScore(13);
            }
            if (member.containsKey(player.getUniqueId().toString()) && !owner.containsKey(player.getUniqueId().toString())) {
                Score rank = obj.getScore("Rank " + ChatColor.LIGHT_PURPLE + "Citizen");
                rank.setScore(13);
            }
            if (customRank.containsKey(player.getUniqueId().toString())) {
                Score rank = obj.getScore("Rank " + ChatColor.LIGHT_PURPLE + customRank.get(player.getUniqueId().toString()));
                rank.setScore(13);
            }
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

            //loop through players and add to member count if they are in the kingdom of the chunk the player goes into
            int memberCount = 0;  // Initialize memberCount outside the loop

            // Get the kingdom of the chunk the player is going into
            String chunkKey = chunk.getX() + "," + chunk.getZ();
            String kingdomOfChunk = claimedChunks.get(chunkKey);

            for (Player player1 : Bukkit.getOnlinePlayers()) {
                // Check if the player is in the same kingdom as the kingdom of the chunk
                String playerKingdom = kingdoms.get(player1.getUniqueId().toString());
                if (kingdomOfChunk != null && kingdomOfChunk.equalsIgnoreCase(playerKingdom)) {
                    memberCount++;
                }
            }

            // Set the score for the scoreboard with the updated memberCount
            Score members = obj.getScore("Members " + ChatColor.YELLOW + memberCount + "/" + maxMembers.get(kingdomOfChunk));
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
                    Score claims = obj.getScore("Claims " + ChatColor.YELLOW + claimCount + "/" + maxClaims.get(kingdoms.get(player.getUniqueId().toString())));
                    claims.setScore(5);
                }
            }


            Score energy = obj.getScore("Energy " + ChatColor.YELLOW + "0.0/0" + ChatColor.AQUA + " 0");
            energy.setScore(4);
            Score separator = obj.getScore(ChatColor.WHITE + " ");
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
        Score divider = obj.getScore(" ");
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
        Score kingdom = obj.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "SERVER");
        kingdom.setScore(7);
        Score online = obj.getScore("Online " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
        online.setScore(6);
        Score onlineStaff = obj.getScore("Staff " + ChatColor.YELLOW + staffConfig.getConfig().getNode("online.online").toPrimitive().getInt());
        onlineStaff.setScore(5);
        Score PvP_setting = obj.getScore(ChatColor.DARK_RED + "PvP " + ChatColor.GRAY + "[" + ChatColor.RED + "OFF" + ChatColor.GRAY + "]");
        PvP_setting.setScore(4);
        Score separator = obj.getScore(ChatColor.WHITE + " ");
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
        Score divider = obj.getScore(" ");
        divider.setScore(15);
        Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
        p.setScore(14);
        //Rank
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
        Score kingdom = obj.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "SERVER");
        kingdom.setScore(7);
        Score online = obj.getScore("Online " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
        online.setScore(6);
        Score onlineStaff = obj.getScore("Staff " + ChatColor.YELLOW + staffConfig.getConfig().getNode("online.online").toPrimitive().getInt());
        onlineStaff.setScore(5);
        Score PvP_setting = obj.getScore(ChatColor.DARK_RED + "PvP " + ChatColor.GRAY + "[" + ChatColor.RED + "OFF" + ChatColor.GRAY + "]");
        PvP_setting.setScore(4);
        Score separator = obj.getScore(ChatColor.WHITE + " ");
        separator.setScore(3);
        Score user = obj.getScore(ChatColor.RED + player.getName() + " " + ChatColor.GRAY + dateFormat.format(date));
        user.setScore(2);
        Score server_ip = obj.getScore(ChatColor.GREEN.toString() + ChatColor.UNDERLINE + "play.kingdoms.games");
        server_ip.setScore(1);
        player.setScoreboard(board);
    }

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
                // Figure out why this doesn't show the scoreboard to the player
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
        Player player = event.getPlayer();

        // Additional logic for handling kingdoms
        if (kingdoms.containsKey(player.getUniqueId().toString())) {
            notInPlayersKingdomBoard(player);
        } else {
            notInKingdomBoard(player);
        }
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        //TODO: remove one from staffCounter if a staff member leaves

        int staffCount = 0;

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String playerRank = staff.get(onlinePlayer.getUniqueId().toString());

            if (playerRank != null && (playerRank.equals("ADMIN") ||
                    playerRank.equals("JRADMIN") ||
                    playerRank.equals("SRMOD") ||
                    playerRank.equals("MOD") ||
                    playerRank.equals("JRMOD"))) {
                staffCount--;
                if (staffCount <= 0) {
                    this.staffCount.put("online", 0);
                } else {
                    this.staffCount.put("online", staffCount);
                }
            }
        }

        savePluginData(player);
    }

    @EventHandler
    public void onKick(PlayerKickEvent event) {
        Player player = event.getPlayer();

        int staffCount = 0;

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            String playerRank = staff.get(onlinePlayer.getUniqueId().toString());

            if (playerRank != null && (playerRank.equals("ADMIN") ||
                    playerRank.equals("JRADMIN") ||
                    playerRank.equals("SRMOD") ||
                    playerRank.equals("MOD") ||
                    playerRank.equals("JRMOD"))) {
                staffCount--;
                this.staffCount.put("online", staffCount);
            } else {

                for (Player onlinePlayer1 : Bukkit.getOnlinePlayers()) {
                    String playerRank1 = staff.get(onlinePlayer1.getUniqueId().toString());

                    if (playerRank != null && !(playerRank1.equals("ADMIN") &&
                            !playerRank1.equals("JRADMIN") &&
                            !playerRank1.equals("SRMOD") &&
                            !playerRank1.equals("MOD") &&
                            !playerRank1.equals("JRMOD"))) {
                        staff.remove(player.getUniqueId().toString());
                        staffCount--;
                        this.staffCount.put("online", staffCount);
                    }
                }
            }
        }
    }

    // Define a method to register teams if not already registered
    private Team registerTeam(Scoreboard board, String teamName, String prefix) {
        Team team = board.getTeam(teamName);
        if (team == null) {
            team = board.registerNewTeam(teamName);
            team.setPrefix(prefix);
        }
        return team;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String chunkID = player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ();

        if (!player.hasPlayedBefore()) {
            notInKingdomBoard(player);

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
            Team defaultTeam = board.registerNewTeam("default");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "/pex group " + player.getUniqueId().toString() + " set default");
            defaultTeam.addEntry(player.getUniqueId().toString());
            playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            money.put(player.getUniqueId().toString(), 0L);
            chatFocus.put(player.getUniqueId().toString(), "GLOBAL");
        } else {
            if (!chatFocus.containsKey(player.getUniqueId().toString())) {
                chatFocus.put(player.getUniqueId().toString(), "GLOBAL");
            }
            if (!playerRank.containsKey(player.getUniqueId().toString())) {
                playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "/pex group " + player.getUniqueId().toString() + " set default");
                savePluginData(player);
            }
            if (!money.containsKey(player.getUniqueId().toString())) {
                money.put(player.getUniqueId().toString(), 0L);
                savePluginData(player);
            }

            int staffCount = 0;

            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                String playerRank = staff.get(onlinePlayer.getUniqueId().toString());

                if (playerRank != null && (playerRank.equals("ADMIN") ||
                        playerRank.equals("JRADMIN") ||
                        playerRank.equals("SRMOD") ||
                        playerRank.equals("MOD") ||
                        playerRank.equals("JRMOD"))) {
                    staffCount++;
                    this.staffCount.put("online", staffCount);
                }
            }

            savePluginData(player);
            restorePluginData(player);

/*            int staffCounter = 0; // Initialize staffCounter
            // Check that player is a staff member
            String staffRole = kingdomsConfig.getConfig().getNode("staff").getNode(player.getUniqueId().toString()).toPrimitive().getString();
            // Check if the player has a staff role and update counters accordingly
            if (staffRole.equalsIgnoreCase("JRMOD") ||
                    staffRole.equalsIgnoreCase("MOD") ||
                    staffRole.equalsIgnoreCase("SRMOD") ||
                    staffRole.equalsIgnoreCase("JRADMIN") ||
                    staffRole.equalsIgnoreCase("ADMIN")) {
                staffCounter++; // Increment staffCounter if player has a staff role
                staffCount.put(staffCounter, staffCounter);
            } else {
                staffCounter = 0; // Reset staffCounter if player doesn't have a staff role
            }*/

            //TODO: Comment the three lines below this out
            if (!money.containsKey(player.getUniqueId().toString())) {
                money.put(player.getUniqueId().toString(), 0L);
            }
            if (!playerRank.containsKey(player.getUniqueId().toString())) {
                playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            }

            // Register teams
            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = manager.getNewScoreboard();

            Team admin = registerTeam(board, "admin", "§4§lADMIN ");
            Team jradmin = registerTeam(board, "jradmin", "§4§lJRADMIN ");
            Team srmod = registerTeam(board, "srmod", "§6§lSRMOD ");
            Team mod = registerTeam(board, "mod", "§e§lMOD ");
            Team jrmod = registerTeam(board, "jrmod", "§3§lJRMOD ");
            Team hero = registerTeam(board, "hero", "§b§lHERO ");
            Team vip = registerTeam(board, "vip", "§a§lVIP ");
            Team defaultTeam = registerTeam(board, "default", "");

            // Set players' teams based on their ranks
            String playerRank = this.playerRank.get(player.getUniqueId().toString());
            if (playerRank.equalsIgnoreCase(ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT)) {
//                    playerTeam = defaultTeam;
                defaultTeam.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.GREEN.toString() + ChatColor.BOLD + Rank.VIP)) {
//                    playerTeam = vip;
                vip.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.AQUA.toString() + ChatColor.BOLD + Rank.HERO)) {
//                    playerTeam = hero;
                hero.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.DARK_AQUA.toString() + ChatColor.BOLD + Rank.JRMOD)) {
//                    playerTeam = jrmod;
                jrmod.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.YELLOW.toString() + ChatColor.BOLD + Rank.MOD)) {
//                    playerTeam = mod;
                mod.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.GOLD.toString() + ChatColor.BOLD + Rank.SRMOD)) {
//                    playerTeam = srmod;
                srmod.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.JRADMIN)) {
//                    playerTeam = jradmin;
                jradmin.addEntry(player.getUniqueId().toString());
            } else if (playerRank.equalsIgnoreCase(ChatColor.DARK_RED.toString() + ChatColor.BOLD + Rank.ADMIN)) {
//                    playerTeam = admin;
                admin.addEntry(player.getUniqueId().toString());
            }

            // Set the player's team if found
            player.setScoreboard(board); // Set scoreboard for the player
            for (Player p : Bukkit.getOnlinePlayers()) {
                p.setScoreboard(board);
            }

            //check if player is in a kingdom onJoin
            if (kingdoms.containsKey(player.getUniqueId().toString())) {
                if (claimedChunks.get(player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ()).equals(kingdoms.get(player.getUniqueId().toString()))) {
                    inPlayersKingdomBoard(player, player.getLocation().getChunk());
                    //TODO: Figure out why the else block doesn't load
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
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + "[" + ChatColor.GOLD + kingdoms.get(player.getUniqueId().toString()) + ChatColor.WHITE + "] " + ChatColor.RED + ChatColor.BOLD + "YT " + ChatColor.WHITE + player.getDisplayName() + ": " + eventMessage;
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
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.WHITE + player.getDisplayName() + ChatColor.WHITE + ": " + eventMessage;
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
            if (playerRank.get(player.getUniqueId().toString()).equals(ChatColor.RED.toString() + ChatColor.BOLD + "YT")) {
                String format = ChatColor.WHITE.toString() + ChatColor.BOLD + "[G] " + ChatColor.RED + ChatColor.BOLD + "YT " + ChatColor.WHITE + player.getDisplayName() + ": " + eventMessage;
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

    public HashMap<String, String> getChatFocus() {
        return chatFocus;
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

    public HashMap<String, String> getPlayerToPunish() {
        return playerToPunish;
    }

    public static PunishCommand getPunishCommand() {
        return punish;
    }

    public HashMap<String, Integer> getIpAdverts() {
        return ipAdverts;
    }

    public HashMap<String, Integer> getMediaAdverts() {
        return mediaAdverts;
    }

    public HashMap<String, Integer> getReportAbuse() {
        return reportAbuse;
    }

    public HashMap<String, Integer> getDisrespect() {
        return disrespect;
    }

    public HashMap<String, Integer> getLanguage() {
        return language;
    }

    public HashMap<String, Integer> getSoliciting() {
        return soliciting;
    }

    public HashMap<String, Integer> getSpam() {
        return spam;
    }

    public HashMap<String, Integer> getDiscrimination() {
        return discrimination;
    }

    public HashMap<String, Integer> getThreats() {
        return threats;
    }

    public HashMap<String, String> getKingdomExists() {
        return kingdomExists;
    }

    public HashMap<String, Long> getClaimPrice() {
        return claimPrice;
    }

    public HashMap<String, Long> getMemberPrice() {
        return memberPrice;
    }

    public HashMap<String, Integer> getMaxMembers() {
        return maxMembers;
    }

    public HashMap<String, Integer> getMaxClaims() {
        return maxClaims;
    }

    public HashMap<String, String> getBannedNames() {
        return bannedNames;
    }

    public static Kingdoms getPlugin() {
        return plugin;
    }

    public ArrayList<Player> getModModePlayers() {
        return modModePlayers;
    }

    public HashMap<String, String> getStaffPasswords() {
        return passwords;
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

    //TODO: Figure out how to check if the custom config files contain the data
    public void restorePluginData() {

        for (Player player : Bukkit.getOnlinePlayers()) {

            Configurable kc = kingdomsConfig.getConfig();
            Configurable sc = staffConfig.getConfig();
            Configurable mc = moneyConfig.getConfig();
            Configurable pu = punishmentConfig.getConfig();

            if (kc != null) {
                //TODO: Figure out if this works
                if (kc.getNode("bannedNames").exists()) {
                    kc.getNode("bannedNames").getKeys(false).forEach(key -> {
                        String bannedNames = kc.getNode("bannedNames." + key).toPrimitive().getString();
                        this.bannedNames.put(key, bannedNames);
                    });
                }
                if (kc.getNode("exists").exists()) {
                    kc.getNode("exists").getKeys(false).forEach(key -> {
                        String value = kc.getNode("exists." + key).toPrimitive().getString();
                        this.kingdomExists.put(key, value);
                    });
                }
                if (kc.getNode("invites").getNode(player.getUniqueId().toString()).exists()) {
                    inviteList.put(player.getUniqueId().toString(), kc.getNode("invites." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).exists()) {
                    kingdoms.put(player.getUniqueId().toString(), kc.getNode("kingdoms." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("maxClaims").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    maxClaims.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxClaims." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
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
                for (Map.Entry<String, String> kingdom : kingdoms.entrySet()) {
                    if (kc.getNode("exists").getNode(kingdom.getValue()).exists()) {
                        kingdomExists.put(kingdom.getValue(), kingdom.getValue());
                    }
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
                if (kc.getNode("maxMembers").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    maxMembers.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxMembers." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    claimPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getLong());
                }
                if (kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    memberPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getLong());
                }
                for (Chunk chunk : Bukkit.getWorld("kingdoms").getLoadedChunks()) {
                    if (kc.getNode("claims").getNode(chunk.getX() + "," + chunk.getZ()).exists()) {
                        if (kingdoms.containsKey(player.getUniqueId().toString())) {
                            //chunkID, kingdom
                            kc.getNode("claims").getKeys(false).forEach(key -> {
                                String chunkClaimer = kc.getNode("claims." + key).toPrimitive().getString();
                                claimedChunks.put(key, chunkClaimer);
                            });
                        }
                    }
                }
            }

            if (pu != null) {
                if (pu.getNode("report").getNode(player.getUniqueId().toString()).exists()) {
                    reportAbuse.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("discrimination").getNode(player.getUniqueId().toString()).exists()) {
                    discrimination.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("language").getNode(player.getUniqueId().toString()).exists()) {
                    language.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("ipAdverts").getNode(player.getUniqueId().toString()).exists()) {
                    ipAdverts.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("mediaAdverts").getNode(player.getUniqueId().toString()).exists()) {
                    mediaAdverts.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("soliciting").getNode(player.getUniqueId().toString()).exists()) {
                    soliciting.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("spam").getNode(player.getUniqueId().toString()).exists()) {
                    spam.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("threats").getNode(player.getUniqueId().toString()).exists()) {
                    threats.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("disrespect").getNode(player.getUniqueId().toString()).exists()) {
                    disrespect.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
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
                try {
                    if (Integer.parseInt(sc.getNode("online.online").toPrimitive().getString()) > -1) {
                        staffCount.put("online", sc.getNode("online.online").toPrimitive().getInt());
                    }
                } catch (NumberFormatException e) {
                    staffCount.put("online", 0);
                }
                if (sc.getNode("focus." + player.getUniqueId().toString()).exists()) {
                    chatFocus.put(player.getUniqueId().toString(), sc.getNode("focus." + player.getUniqueId().toString()).toPrimitive().getString());
                }
//                if (sc.getNode("passwords").getNode(player.getUniqueId().toString()).exists()) {
//                        passwords.put(player.getUniqueId().toString(), sc.getNode("passwords." + player.getUniqueId().toString()).toPrimitive().getString());
//                }
            }
        }
    }

    public void restorePluginData(Player player) {

        Configurable kc = kingdomsConfig.getConfig();
        Configurable sc = staffConfig.getConfig();
        Configurable mc = moneyConfig.getConfig();
        Configurable pu = punishmentConfig.getConfig();

        if (kc != null) {
            //TODO: Figure out if this works
            if (kc.getNode("bannedNames").exists()) {
                kc.getNode("bannedNames").getKeys(false).forEach(key -> {
                    String bannedNames = kc.getNode("bannedNames." + key).toPrimitive().getString();
                    this.bannedNames.put(key, bannedNames);
                });
            }
            if (kc.getNode("exists").exists()) {
                kc.getNode("exists").getKeys(false).forEach(key -> {
                    String value = kc.getNode("exists." + key).toPrimitive().getString();
                    this.kingdomExists.put(key, value);
                });
            }
            if (kc.getNode("invites").getNode(player.getUniqueId().toString()).exists()) {
                inviteList.put(player.getUniqueId().toString(), kc.getNode("invites." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).exists()) {
                kingdoms.put(player.getUniqueId().toString(), kc.getNode("kingdoms." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("maxClaims").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                maxClaims.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxClaims." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
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
            for (Map.Entry<String, String> kingdom : kingdoms.entrySet()) {
                if (kc.getNode("exists").getNode(kingdom.getValue()).exists()) {
                    kingdomExists.put(kingdom.getValue(), kingdom.getValue());
                }
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
            if (kc.getNode("maxMembers").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                maxMembers.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxMembers." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
            }
            if (kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                claimPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getLong());
            }
            if (kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                memberPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getLong());
            }
            for (Chunk chunk : Bukkit.getWorld("kingdoms").getLoadedChunks()) {
                if (kc.getNode("claims").getNode(chunk.getX() + "," + chunk.getZ()).exists()) {
                    if (kingdoms.containsKey(player.getUniqueId().toString())) {
                        //chunkID, kingdom
                        kc.getNode("claims").getKeys(false).forEach(key -> {
                            String chunkClaimer = kc.getNode("claims." + key).toPrimitive().getString();
                            claimedChunks.put(key, chunkClaimer);
                        });
                    }
                }
            }
        }

        if (pu != null) {
            if (pu.getNode("report").getNode(player.getUniqueId().toString()).exists()) {
                reportAbuse.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("discrimination").getNode(player.getUniqueId().toString()).exists()) {
                discrimination.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("language").getNode(player.getUniqueId().toString()).exists()) {
                language.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("ipAdverts").getNode(player.getUniqueId().toString()).exists()) {
                ipAdverts.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("mediaAdverts").getNode(player.getUniqueId().toString()).exists()) {
                mediaAdverts.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("soliciting").getNode(player.getUniqueId().toString()).exists()) {
                soliciting.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("spam").getNode(player.getUniqueId().toString()).exists()) {
                spam.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("threats").getNode(player.getUniqueId().toString()).exists()) {
                threats.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
            }
            if (pu.getNode("disrespect").getNode(player.getUniqueId().toString()).exists()) {
                disrespect.put(player.getUniqueId().toString(), pu.getNode("report." + player.getUniqueId().toString()).toPrimitive().getInt());
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
            if (sc.getNode("focus." + player.getUniqueId().toString()).exists()) {
                chatFocus.put(player.getUniqueId().toString(), sc.getNode("focus." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            try {
                if (Integer.parseInt(sc.getNode("online.online").toPrimitive().getString()) > -1) {
                    staffCount.put("online", sc.getNode("online.online").toPrimitive().getInt());
                }
            } catch (NumberFormatException e) {
                staffCount.put("online", 0);
            }
//            if (sc.getNode("passwords").getNode(player.getUniqueId().toString()).exists()) {
//                passwords.put(player.getUniqueId().toString(), sc.getNode("passwords." + player.getUniqueId().toString()).toPrimitive().getString());
//            }
        }
    }

    public void savePluginData(Player player) {
        if (!reportAbuse.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> report : reportAbuse.entrySet()) {
                    config.set("report." + report.getKey(), report.getValue());
                }
            }
            config.save();
        }
        if (!disrespect.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> disrespect : disrespect.entrySet()) {
                    config.set("disrespect." + disrespect.getKey(), disrespect.getValue());
                }
            }
            config.save();
        }
        if (!language.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> language : language.entrySet()) {
                    config.set("language." + language.getKey(), language.getValue());
                }
            }
            config.save();
        }
        if (!ipAdverts.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> ipAdverts : ipAdverts.entrySet()) {
                    config.set("ipAdverts." + ipAdverts.getKey(), ipAdverts.getValue());
                }
            }
            config.save();
        }
        if (!mediaAdverts.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> mediaAdverts : mediaAdverts.entrySet()) {
                    config.set("mediaAdverts." + mediaAdverts.getKey(), mediaAdverts.getValue());
                }
            }
            config.save();
        }
        if (!soliciting.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> soliciting : soliciting.entrySet()) {
                    config.set("soliciting." + soliciting.getKey(), soliciting.getValue());
                }
            }
            config.save();
        }
        if (!spam.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> spam : spam.entrySet()) {
                    config.set("spam." + spam.getKey(), spam.getValue());
                }
            }
            config.save();
        }
        if (!discrimination.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> discrimination : discrimination.entrySet()) {
                    config.set("discrimination." + discrimination.getKey(), discrimination.getValue());
                }
            }
            config.save();
        }
        if (!threats.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> threats : threats.entrySet()) {
                    config.set("threats." + threats.getKey(), threats.getValue());
                }
            }
            config.save();
        }
        if (!staffCount.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> online : staffCount.entrySet()) {
                    config.set("online." + online.getKey(), online.getValue());
                }
            }
            config.save();
        }
        if (!bannedNames.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> bannedNames : bannedNames.entrySet()) {
                    config.set("bannedNames." + bannedNames.getKey(), bannedNames.getValue());
                }
            }
            config.save();
        }
        if (!kingdomExists.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> exists : kingdomExists.entrySet()) {
                    config.set("exists." + exists.getKey(), exists.getValue());
                }
            }
            config.save();
        }
        if (!kingdoms.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> kingdoms : kingdoms.entrySet()) {
                    if (this.kingdoms.containsKey(kingdoms.getKey())) {
                        config.set("kingdoms." + kingdoms.getKey(), kingdoms.getValue());
                    } else {
                        config.set("kingdoms." + kingdoms.getKey(), null);
                    }
                }
            }
            config.save();
        }
        if (!maxClaims.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> maxClaims : maxClaims.entrySet()) {
                    config.set("maxClaims." + maxClaims.getKey(), maxClaims.getValue());
                }
            }
            config.save();
        }
        if (!maxMembers.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> maxMembers : maxMembers.entrySet()) {
                    config.set("maxMembers." + maxMembers.getKey(), maxMembers.getValue());
                }
            }
            config.save();
        }
        if (!owner.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> owners : owner.entrySet()) {
                    if (this.owner.containsKey(owners.getKey())) {
                        config.set("owners." + owners.getKey(), owners.getValue());
                    } else {
                        config.set("owners." + owners.getKey(), null);
                    }
                }
            }
            config.save();
        }
        if (!admin.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> admins : admin.entrySet()) {
                    if (admin.containsKey(admins.getKey())) {
                        config.set("admins." + admins.getKey(), admins.getValue());
                    } else {
                        config.set("admins." + admins.getKey(), null);
                    }
                }
            }
            config.save();
        }
        if (!member.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> members : member.entrySet()) {
                    if (member.containsKey(members.getKey())) {
                        config.set("members." + members.getKey(), members.getValue());
                    } else {
                        config.set("members." + members.getKey(), null);
                    }
                }
            }
            config.save();
        }

        if (!inviteList.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> invites : inviteList.entrySet()) {
                    if (inviteList.containsKey(invites.getKey())) {
                        config.set("invites." + invites.getKey(), invites.getValue());
                    } else {
                        config.set("invites." + invites.getKey(), null);
                    }
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
                    if (kingdomSpawn.containsKey(spawns.getKey())) {
                        config.set("spawns." + spawns.getKey(), spawns.getValue());
                    } else {
                        config.set("spawns." + spawns.getKey(), null);
                    }
                }
            }
            config.save();
        }

        if (!claimedChunks.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> claims : claimedChunks.entrySet()) {
                    if (claimedChunks.get(claims.getKey()).equals(kingdoms.get(player.getUniqueId().toString()))) {
                        config.set("claims." + claims.getKey(), claims.getValue());
                    } else {
                        config.set("claims." + claims.getKey(), null);
                    }
                }
            }
            config.save();
        }

        if (!memberPrice.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Long> price : memberPrice.entrySet()) {
                    config.set("memberPrice." + price.getKey(), price.getValue());
                }
            }
            config.save();
        }

        if (!claimPrice.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Long> price : claimPrice.entrySet()) {
                    config.set("claimPrice." + price.getKey(), price.getValue());
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
        if (!chatFocus.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> focus : chatFocus.entrySet()) {
                    config.set("focus." + focus.getKey(), focus.getValue());
                }
            }
            config.save();
        }

//        if (!passwords.isEmpty()) {
//            Configurable config = staffConfig.getConfig();
//            if (config != null) {
//                for (Map.Entry<String, String> passwords : passwords.entrySet()) {
//                    config.set("passwords." + passwords.getKey(), passwords.getValue());
//                }
//            }
//            config.save();
//        }
    }

    public void savePluginData() {
        if (!reportAbuse.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> report : reportAbuse.entrySet()) {
                    config.set("report." + report.getKey(), report.getValue());
                }
            }
            config.save();
        }
        if (!disrespect.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> disrespect : disrespect.entrySet()) {
                    config.set("disrespect." + disrespect.getKey(), disrespect.getValue());
                }
            }
            config.save();
        }
        if (!language.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> language : language.entrySet()) {
                    config.set("language." + language.getKey(), language.getValue());
                }
            }
            config.save();
        }
        if (!ipAdverts.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> ipAdverts : ipAdverts.entrySet()) {
                    config.set("ipAdverts." + ipAdverts.getKey(), ipAdverts.getValue());
                }
            }
            config.save();
        }
        if (!mediaAdverts.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> mediaAdverts : mediaAdverts.entrySet()) {
                    config.set("mediaAdverts." + mediaAdverts.getKey(), mediaAdverts.getValue());
                }
            }
            config.save();
        }
        if (!soliciting.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> soliciting : soliciting.entrySet()) {
                    config.set("soliciting." + soliciting.getKey(), soliciting.getValue());
                }
            }
            config.save();
        }
        if (!spam.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> spam : spam.entrySet()) {
                    config.set("spam." + spam.getKey(), spam.getValue());
                }
            }
            config.save();
        }
        if (!discrimination.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> discrimination : discrimination.entrySet()) {
                    config.set("discrimination." + discrimination.getKey(), discrimination.getValue());
                }
            }
            config.save();
        }
        if (!threats.isEmpty()) {
            Configurable config = punishmentConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> threats : threats.entrySet()) {
                    config.set("threats." + threats.getKey(), threats.getValue());
                }
            }
            config.save();
        }
        if (!staffCount.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> online : staffCount.entrySet()) {
                    config.set("online." + online.getKey(), online.getValue());
                }
            }
            config.save();
        }
        if (!bannedNames.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> bannedNames : bannedNames.entrySet()) {
                    config.set("bannedNames." + bannedNames.getKey(), bannedNames.getValue());
                }
            }
            config.save();
        }
        if (!kingdomExists.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> exists : kingdomExists.entrySet()) {
                    config.set("exists." + exists.getKey(), exists.getValue());
                }
            }
            config.save();
        }
        if (!kingdoms.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> kingdoms : kingdoms.entrySet()) {
                    config.set("kingdoms." + kingdoms.getKey(), kingdoms.getValue());
                }
            }
            config.save();
        }
        if (!maxClaims.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> maxClaims : maxClaims.entrySet()) {
                    config.set("maxClaims." + maxClaims.getKey(), maxClaims.getValue());
                }
            }
            config.save();
        }
        if (!maxMembers.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Integer> maxMembers : maxMembers.entrySet()) {
                    config.set("maxMembers." + maxMembers.getKey(), maxMembers.getValue());
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

        if (!memberPrice.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Long> price : memberPrice.entrySet()) {
                    config.set("memberPrice." + price.getKey(), price.getValue());
                }
            }
            config.save();
        }

        if (!claimPrice.isEmpty()) {
            Configurable config = kingdomsConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, Long> price : claimPrice.entrySet()) {
                    config.set("claimPrice." + price.getKey(), price.getValue());
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
        if (!chatFocus.isEmpty()) {
            Configurable config = staffConfig.getConfig();
            if (config != null) {
                for (Map.Entry<String, String> focus : chatFocus.entrySet()) {
                    config.set("focus." + focus.getKey(), focus.getValue());
                }
            }
            config.save();
        }

//        if (!passwords.isEmpty()) {
//            Configurable config = staffConfig.getConfig();
//            if (config != null) {
//                for (Map.Entry<String, String> passwords : passwords.entrySet()) {
//                    config.set("passwords." + passwords.getKey(), passwords.getValue());
//                }
//            }
//            config.save();
//        }
    }

    private void events() {
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new Password(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomsCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WarzoneCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomUpgradeListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WarzoneCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new CustomOreCommand(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomsListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RandomTeleportListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatCMD(), this);
    }
}