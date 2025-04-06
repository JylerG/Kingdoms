package games.kingdoms.kingdoms;

import com.github.sanctum.labyrinth.data.BukkitGeneric;
import com.github.sanctum.panther.file.Configurable;
import games.kingdoms.kingdoms.admin.balance.EconomyCommand;
import games.kingdoms.kingdoms.admin.balance.EconomyTabCompleter;
import games.kingdoms.kingdoms.admin.configs.KingdomsConfig;
import games.kingdoms.kingdoms.admin.configs.MoneyConfig;
import games.kingdoms.kingdoms.admin.configs.PunishmentConfig;
import games.kingdoms.kingdoms.admin.configs.StaffConfig;
import games.kingdoms.kingdoms.admin.customitems.customores.CustomOreCommand;
import games.kingdoms.kingdoms.admin.customitems.customores.CustomOreTabCompleter;
import games.kingdoms.kingdoms.admin.customitems.merchantitems.MerchantCommand;
import games.kingdoms.kingdoms.admin.customitems.merchantitems.MerchantListener;
import games.kingdoms.kingdoms.admin.gamemodes.Adventure;
import games.kingdoms.kingdoms.admin.gamemodes.Creative;
import games.kingdoms.kingdoms.admin.gamemodes.Spectator;
import games.kingdoms.kingdoms.admin.gamemodes.Survival;
import games.kingdoms.kingdoms.admin.modmode.ModMode;
import games.kingdoms.kingdoms.admin.npcinteractions.managers.*;
import games.kingdoms.kingdoms.admin.npcs.CreateNPCCommand;
import games.kingdoms.kingdoms.admin.npcs.NPCTabCompleter;
import games.kingdoms.kingdoms.admin.password.Password;
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
import games.kingdoms.kingdoms.publiccmds.chats.ChatListener;
import games.kingdoms.kingdoms.publiccmds.chats.ChatTabCompleter;
import games.kingdoms.kingdoms.publiccmds.easter.EasterCommand;
import games.kingdoms.kingdoms.publiccmds.kingdoms.chat.KingdomsChat;
import games.kingdoms.kingdoms.publiccmds.kingdoms.chat.KingdomsChatTabCompleter;
import games.kingdoms.kingdoms.publiccmds.kingdoms.command.KingdomsCommands;
import games.kingdoms.kingdoms.publiccmds.kingdoms.listeners.KingdomInfoListener;
import games.kingdoms.kingdoms.publiccmds.kingdoms.listeners.KingdomUpgradeListener;
import games.kingdoms.kingdoms.publiccmds.kingdoms.related.KingdomsListener;
import games.kingdoms.kingdoms.publiccmds.kingdoms.tabcompletion.KingdomInviteTabCompleter;
import games.kingdoms.kingdoms.publiccmds.nightvision.Commands;
import games.kingdoms.kingdoms.publiccmds.randomtp.RandomTeleportListener;
import games.kingdoms.kingdoms.publiccmds.randomtp.rtp;
import games.kingdoms.kingdoms.publiccmds.report.ReportCommand;
import games.kingdoms.kingdoms.publiccmds.report.ReportTabCompleter;
import games.kingdoms.kingdoms.publiccmds.teleports.KingdomsCommandListener;
import games.kingdoms.kingdoms.publiccmds.teleports.SpawnCommand;
import games.kingdoms.kingdoms.publiccmds.teleports.WarzoneCMD;
import games.kingdoms.kingdoms.publiccmds.teleports.WarzoneCommandListener;
import games.kingdoms.kingdoms.publiccmds.whisper.WhisperCommand;
import games.kingdoms.kingdoms.publiccmds.whisper.WhisperCommandTabCompleter;
import games.kingdoms.kingdoms.rankedcmds.feed.Feed;
import games.kingdoms.kingdoms.rankedcmds.fly.Fly;
import net.milkbowl.vault.chat.Chat;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
    final DecimalFormat formatter = new DecimalFormat("#,###.##");
    private HashMap<String, Integer> customRank = new HashMap<>();
    private HashMap<String, Integer> kingdomRank = new HashMap<>();
    private HashMap<Integer, String> playerRankInKingdom = new HashMap<>();
    private HashMap<String, Integer> playerRanks = new HashMap<>();
    private HashMap<String, String> spyOnKingdom = new HashMap<>();
    final ArrayList<Player> invisiblePlayers = new ArrayList<>();
    private ArrayList<Player> modModePlayers = new ArrayList<>();
    private HashMap<String, String> claims = new HashMap<>();
    private HashMap<String, Integer> claimPrice = new HashMap<>();
    private HashMap<String, Integer> memberPrice = new HashMap<>();
    private HashMap<String, String> bannedNames = new HashMap<>();
    private HashMap<String, String> playerRank = new HashMap<>();
    private HashMap<String, HashMap<Integer, String>> kingdomRanks = new HashMap<>();
    private HashMap<String, String> passwords = new HashMap<>();
    private HashMap<String, String> chatFocus = new HashMap<>();
    private HashMap<String, String> inviteList = new HashMap<>();
    private HashMap<String, String> claimedChunks = new HashMap<>();
    private HashMap<String, Location> kingdomSpawn = new HashMap<>();
    private HashMap<String, String> staff = new HashMap<>();
    private HashMap<String, String> kingdoms = new HashMap<>();
    private HashMap<String, Long> money = new HashMap<>();
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
    private HashMap<String, String> playerToPunish = new HashMap<>();
//todo: Any additional punishment types
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

    private static Chat chat = null;
    PlayerJoinListener pjl = new PlayerJoinListener();

    public boolean isPluginLoaded(String plugin) {
        return Bukkit.getPluginManager().getPlugin(plugin) != null;
    }

    @Override
    public void onEnable() {


        try {
            //Save default config
            getConfig().options().copyDefaults();
            saveDefaultConfig();

            setupChat();
            plugin = this;
            pjl = new PlayerJoinListener();

            configInit();

            //Set up initial configs
            setupConfigs();

            //Initialize ArrayLists and HashMaps
            initMapList();

            //Restore Plugin Data
            restoreOfflineData();
            restoreServerData();

            //Commands
            commands();

            //Events
            events();

            //Plugin successfully loaded
            MessageManager.consoleGood("Kingdoms successfully Enabled");
        } catch (UnknownDependencyException e) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!isPluginLoaded("ProtocolLib")) {
                    MessageManager.consoleBad("ProtocolLib is not installed on this server");
                }
                if (!isPluginLoaded("Labyrinth")) {
                    MessageManager.consoleBad("Labyrinth is not installed on this server");
                }
                if (!isPluginLoaded("Vault")) {
                    MessageManager.consoleBad("Vault is not installed on this server");
                }
            }
        }
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

    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        if (rsp != null) {
            chat = rsp.getProvider();
        }
        return chat != null;
    }

    private void configInit() {
        punishmentConfig = new PunishmentConfig(this);
        kingdomsConfig = new KingdomsConfig(this);
        moneyConfig = new MoneyConfig(this);
        staffConfig = new StaffConfig(this);
    }
    private void setupConfigs() {
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
    }

    private void commands() {
        chat();
        reportPlayer();
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
        modMode();
        help();
        viewPerms();
        gameModes();
        rank();
        ore();
        Pay();
        Economy();
        balanceCMDs();
        Merchant();
        staffChat();
        kingdomChat();
        easter();
        whisper();
        warzone();
    }

    private void password() {
        getCommand("password").setExecutor(new Password());
    }

    private void reportPlayer() {
        getCommand("report").setTabCompleter(new ReportTabCompleter());
        getCommand("report").setExecutor(new ReportCommand());
    }

    private void chat() {
        getCommand("chat").setExecutor(new ChatCMD());
        getCommand("chat").setTabCompleter(new ChatTabCompleter());
    }

    private void punishments() {
        getCommand("punish").setExecutor(new PunishCommand(this));
        getCommand("confirm").setExecutor(new ConfirmPunishment(this));
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

        playerRankInKingdom = new HashMap<>();
        playerRanks = new HashMap<>();
        kingdomRank = new HashMap<>();
        chatFocus = new HashMap<>();
        kingdomRanks = new HashMap<>();
        spyOnKingdom = new HashMap<>();
        money = new HashMap<>();
        passwords = new HashMap<>();
        modModePlayers = new ArrayList<>();
        memberPrice = new HashMap<>();
        claimPrice = new HashMap<>();
        maxMembers = new HashMap<>();
        maxClaims = new HashMap<>();
        claims = new HashMap<>();
        customRank = new HashMap<>();
        bannedNames = new HashMap<>();
        playerRank = new HashMap<>();
        kingdomSpawn = new HashMap<>();
        kingdoms = new HashMap<>();
        staff = new HashMap<>();
        inviteList = new HashMap<>();
        claimedChunks = new HashMap<>();
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

    private void Economy() {
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
        getCommand("spawn").setExecutor(new SpawnCommand(this));
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

    private void registerCommand(String commandName, CommandExecutor executor) {
        try {
            // Get the command map
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getPluginManager());

            // Create and register a new command
            commandMap.register(commandName, new CommandWrapper(commandName, executor));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void unregisterCommand(String commandName) {
        try {
            // Get the command map
            Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            CommandMap commandMap = (CommandMap) commandMapField.get(Bukkit.getPluginManager());

            // Get the command
            Command command = commandMap.getCommand(commandName);
            if (command != null) {
                // Unregister the command
                command.unregister(commandMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void modMode() {
        getCommand("modmode").setExecutor(new ModMode());
    }

    private void kingdom() {
        getCommand("kingdom").setExecutor(new KingdomsCommands());
        getCommand("kingdom").setTabCompleter(new KingdomInviteTabCompleter());
    }

    private void viewPerms() {
        getCommand("view").setExecutor(new Permissions());
    }

    private void help() {
//        unregisterCommand("?");
//        unregisterCommand("help");
//        registerCommand("help", new HelpCommand());
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
        getCommand("gmc").setExecutor(new Creative());
        getCommand("gms").setExecutor(new Survival());
        getCommand("gmsp").setExecutor(new Spectator());
        getCommand("gma").setExecutor(new Adventure());
    }

    private void ore() {
        getCommand("ore").setExecutor(new CustomOreCommand(this));
        getCommand("ore").setTabCompleter(new CustomOreTabCompleter(this));
    }

    //Player is in a chunk of the kingdom they are in
    public void inPlayersKingdomBoard(Player player, Chunk chunk) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        String formattedMoney = formatter.format(money.get(player.getUniqueId().toString()));
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        //Scoreboard Name
        Objective obj = board.registerNewObjective("inChunk", "dummy", ChatColor.translateAlternateColorCodes('&', "&e&lKingdoms"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score divider = obj.getScore(" ");
        divider.setScore(14);
        Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
        p.setScore(13);
        //Player rank
        String playerChunk = chunk.getX() + "," + chunk.getZ();
        String playerObj = player.getUniqueId().toString();
        String Kingdom = kingdoms.get(player.getUniqueId().toString());
        if (claimedChunks.get(playerChunk).equals(Kingdom)) {
            // Find the rank integer stored under the player's UUID
            int playerRank = -1;
            for (String key : kingdomsConfig.getConfig().getNode(player.getUniqueId().toString()).getKeys(false)) {
                try {
                    playerRank = Integer.parseInt(key);

                    String playerKingdom = kingdoms.get(player.getUniqueId().toString());
                    // Retrieve the rank name using the rank integer from the player's kingdom
                    String rankName = kingdomsConfig.getConfig().getNode(playerKingdom + "." + playerRank).toPrimitive().getString();
                    Score rank = obj.getScore("Rank " + ChatColor.LIGHT_PURPLE + rankName);
                    rank.setScore(12);
                    break; // Stop at the first found rank
                } catch (NumberFormatException ignored) {
                    // Ignore non-numeric keys
                }
            }
        }
        //Coins
        Score coins = obj.getScore("Coins " + ChatColor.GOLD + formattedMoney);
        coins.setScore(11);
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

        //todo: Figure out why this double counts
        //Initialize memberCount and ensure it is reset for each chunk
        int memberCount = 0;

        // Get the chunk coordinates (the key used to store the claimed chunk data)
        String chunkKey = chunk.getX() + "," + chunk.getZ();

        // Find the kingdom that owns this chunk
        String kingdomOfChunk = claimedChunks.get(chunkKey);

        // Check if the chunk belongs to a kingdom
        if (kingdomOfChunk != null) {

            // Create a set to track players that have already been counted
            Set<UUID> countedPlayers = new HashSet<>();

            // Loop through all online players
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                // Skip the player if they've already been counted
                if (countedPlayers.contains(onlinePlayer.getUniqueId())) {
                    continue;
                }

                // Get the player's kingdom
                String playerKingdom = kingdoms.get(onlinePlayer.getUniqueId().toString());

                // Check if the player belongs to the same kingdom as the one that owns the chunk
                if (kingdomOfChunk.equalsIgnoreCase(playerKingdom)) {
                    memberCount++;
                    // Mark this player as counted
                    countedPlayers.add(onlinePlayer.getUniqueId());
                }
            }


            // Loop through all offline players (if needed)
            for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
                // Loop through all online players
                // Skip the player if they've already been counted
                if (countedPlayers.contains(offline.getUniqueId())) {
                    continue;
                }

                // Get the player's kingdom
                String playerKingdom = kingdoms.get(offline.getUniqueId().toString());

                // Check if the player belongs to the same kingdom as the one that owns the chunk
                if (kingdomOfChunk.equalsIgnoreCase(playerKingdom)) {
                    memberCount++;
                    // Mark this player as counted
                    countedPlayers.add(offline.getUniqueId());
                }
            }

            // Set the score for the scoreboard with the updated memberCount
            int maxAllowedMembers = maxMembers.getOrDefault(kingdomOfChunk, 0); // Get max members, default to 0 if none
            Score membersScore = obj.getScore("Members " + ChatColor.YELLOW + memberCount + "/" + maxAllowedMembers);
            membersScore.setScore(6);
        }

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

    //Player is not in a chunk owned by their kingdom
    public void notInPlayersKingdomBoard(@NotNull Player player) {
        Chunk chunk = player.getLocation().getChunk();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        String formattedMoney = formatter.format(money.get(player.getUniqueId().toString()));
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        //Scoreboard Name
        Objective obj = board.registerNewObjective("notInClaim", "dummy", ChatColor.translateAlternateColorCodes('&', "&e&lKingdoms"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score divider = obj.getScore(" ");
        divider.setScore(14);
        Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
        p.setScore(13);
        //Rank
        if (!playerRank.containsKey(player.getUniqueId().toString())) {
            playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
        }
        Score rank = obj.getScore("Rank " + playerRank.get(player.getUniqueId().toString()));
        rank.setScore(12);
        //Coins
        Score coins = obj.getScore("Coins " + ChatColor.GOLD + formattedMoney);
        coins.setScore(11);
        //Kill/Death Ratio
        Score kdr = obj.getScore("KDR " + ChatColor.YELLOW + player.getStatistic(Statistic.PLAYER_KILLS) + ChatColor.WHITE + "/" + ChatColor.YELLOW + player.getStatistic(Statistic.DEATHS));
        kdr.setScore(10);
        //edit this to show how many challenges have been completed
        Score challenges = obj.getScore(ChatColor.WHITE + "Challenges " + ChatColor.YELLOW + "WIP");
        challenges.setScore(9);
        Score blank = obj.getScore(" ");
        blank.setScore(8);
        Score server = obj.getScore(ChatColor.YELLOW.toString() + ChatColor.BOLD + "SERVER");
        server.setScore(7);
        //All online players
        Score online = obj.getScore("Online " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
        online.setScore(6);
        //Online Staff Count
        int staff = 0;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            if (this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                    || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("MOD")
                    || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                    || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                    || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                staff++;
            }
        }
        Score onlineStaff = obj.getScore("Staff " + ChatColor.YELLOW + staff);
        onlineStaff.setScore(5);
        //PvP is on/off
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
        String formattedMoney = money.get(player.getUniqueId().toString()).toString();
        Chunk chunk = player.getLocation().getChunk();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        Date date = new Date();
        formattedMoney = formatter.format(plugin.getMoney().get(player.getUniqueId().toString()));
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
        //Scoreboard Name
        Objective obj = board.registerNewObjective("notInClaim", "dummy", ChatColor.translateAlternateColorCodes('&', "&e&lKingdoms"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score divider = obj.getScore(" ");
        divider.setScore(14);
        Score p = obj.getScore(ChatColor.BLUE.toString() + ChatColor.BOLD + "PLAYER");
        p.setScore(13);
        //Rank
        Score rank = obj.getScore("Rank " + playerRank.get(player.getUniqueId().toString()));
        rank.setScore(12);
        //Coins
        if (!money.containsKey(player.getUniqueId().toString())) {
            money.put(player.getUniqueId().toString(), 0L);
        }
        Score coins = obj.getScore("Coins " + ChatColor.GOLD + formattedMoney);
        coins.setScore(11);
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
        int staff = 0;
        for (Player pl : Bukkit.getOnlinePlayers()) {
            //Online Staff Count
            if (this.staff.containsKey(pl.getUniqueId().toString())) {
                if (this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("JRMOD")
                        || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("MOD")
                        || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("SRMOD")
                        || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("JRADMIN")
                        || this.staff.get(pl.getUniqueId().toString()).equalsIgnoreCase("ADMIN")) {
                    staff++;
                }
                Score onlineStaff = obj.getScore("Staff " + ChatColor.YELLOW + staff);
                onlineStaff.setScore(5);
            }
        }
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
            updateTabListWithScoreboard(player);

            String title = " ";
            String subtitle;

            String chunkKey = toChunk.getX() + "," + toChunk.getZ();

            if (isChunkClaimed(toChunk)) {
                String ownerKingdom = claimedChunks.get(chunkKey);
                String playerKingdom = kingdoms.getOrDefault(player.getUniqueId().toString(), "");

                if (ownerKingdom != null && !ownerKingdom.isEmpty()) {
                    if (playerKingdom.equals(ownerKingdom)) {
                        // Player owns the kingdom and chunk
                        title = " ";
                    } else {
                        // Player does not own the kingdom, but the chunk is owned by someone else
                        title = ChatColor.RED + "Owned by";
                    }
                    subtitle = ChatColor.GREEN + ownerKingdom;
                } else {
                    // If no owner, it's wilderness
                    title = " ";
                    subtitle = ChatColor.RED + "Wilderness";
                }
            } else {
                // If the chunk is not claimed, it's wilderness
                title = " ";
                subtitle = ChatColor.RED + "Wilderness";
            }

            // Send title and subtitle
            sendTitle(player, title, subtitle, 10, 40, 10);

            // Update the player's scoreboard
            String playerKingdom = kingdoms.getOrDefault(player.getUniqueId().toString(), "");

            if (!playerKingdom.isEmpty()) {
                String kingdom = kingdoms.get(player.getUniqueId().toString());

                // Check if the chunk is claimed and whether it's owned by the player's kingdom
                if (isChunkClaimed(toChunk)) {
                    String chunkOwnerKingdom = claimedChunks.get(toChunk.getX() + "," + toChunk.getZ());

                    // Only call inPlayersKingdomBoard if the chunk is owned by the player's kingdom
                    if (chunkOwnerKingdom != null && chunkOwnerKingdom.equalsIgnoreCase(kingdom)) {
                        inPlayersKingdomBoard(player, toChunk);
                    } else {
                        notInPlayersKingdomBoard(player);
                    }
                } else {
                    // If the chunk is not claimed, handle the case where the player is not in any kingdom
                    notInPlayersKingdomBoard(player);
                }
            } else {
                // If the player doesn't belong to any kingdom, show the appropriate scoreboard
                notInKingdomBoard(player);
            }
        }
    }

    private void updateTabListWithScoreboard(Player player) {
        // Get the player's color for the tab list
        ChatColor color = pjl.getColorForPlayer(player); // Ensure this method returns a ChatColor value

        // Retrieve or create the player's scoreboard
        Scoreboard scoreboard = player.getScoreboard();
        if (scoreboard == null || scoreboard == Bukkit.getScoreboardManager().getMainScoreboard()) {
            scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        }

        // Get or create a team for the player
        Team team = scoreboard.getTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
        }

        // Set prefix (color for tab list)
        team.setColor(color); // Sets name color
        team.addEntry(player.getName()); // Adds player to the team

        // Apply the updated scoreboard to the player
        player.setScoreboard(scoreboard);

        // Set the tab list name
        player.setPlayerListName(color + player.getDisplayName());
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        event.setQuitMessage("");
        Player player = event.getPlayer();
        savePluginData(player);
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

        String joinMessage = ChatColor.GREEN.toString() + ChatColor.BOLD + "Welcome " + ChatColor.WHITE + ChatColor.BOLD + player.getName() +
                ChatColor.GREEN + ChatColor.BOLD + " to " + ChatColor.YELLOW + ChatColor.BOLD + "Kingdoms";
        event.setJoinMessage(joinMessage);

        if (!player.hasPlayedBefore()) {
            notInKingdomBoard(player);

            ScoreboardManager manager = Bukkit.getScoreboardManager();
            Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
            Team defaultTeam = board.registerNewTeam("default");
            Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getUniqueId().toString() + " group set default");
            defaultTeam.addEntry(player.getUniqueId().toString());
            playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            money.put(player.getUniqueId().toString(), 0L);
            chatFocus.put(player.getUniqueId().toString(), "GLOBAL");
            kingdoms.put(player.getUniqueId().toString(), "");
            passwords.put(player.getUniqueId().toString(), "");

            PlayerJoinListener pjl = new PlayerJoinListener();
            // Update the player's tab list name
            updateTabListWithScoreboard(player);
        } else {
            if (!kingdoms.containsKey(player.getUniqueId().toString())) {
                kingdoms.put(player.getUniqueId().toString(), "");
            }
            if (!chatFocus.containsKey(player.getUniqueId().toString())) {
                chatFocus.put(player.getUniqueId().toString(), "GLOBAL");
            }
            if (!passwords.containsKey(player.getUniqueId().toString())) {
                passwords.put(player.getUniqueId().toString(), "");
            }
            if (!playerRank.containsKey(player.getUniqueId().toString())) {
                playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "pex user " + player.getUniqueId().toString() + " group set default");
            }
            if (!money.containsKey(player.getUniqueId().toString())) {
                money.put(player.getUniqueId().toString(), 0L);
            }

            if (!money.containsKey(player.getUniqueId().toString())) {
                money.put(player.getUniqueId().toString(), 0L);
            }
            if (!playerRank.containsKey(player.getUniqueId().toString())) {
                playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            }

            savePluginData(player);

//            TODO: figure out if this is usable
//             PlayerJoinListener pjl = new PlayerJoinListener();
            // Update the player's tab list name
            updateTabListWithScoreboard(player);

            // Check if player is in a kingdom onJoin
            if (kingdoms.containsKey(player.getUniqueId().toString())) {

                String kingdomId = kingdoms.get(player.getUniqueId().toString());
                String chunkKey = player.getLocation().getChunk().getX() + "," + player.getLocation().getChunk().getZ();

                // First, check if there are any claimed chunks in this kingdom
                if (!claimedChunks.containsValue(kingdomId)) {
                    notInPlayersKingdomBoard(player);
                    return;
                }

                // Check if the current chunk is claimed by any kingdom
                if (!claimedChunks.containsKey(chunkKey)) {
                    notInPlayersKingdomBoard(player);
                    return;
                }

                // Now check if the chunk belongs to the player's kingdom
                String claimedKingdomId = claimedChunks.get(chunkKey);

                if (claimedKingdomId == null || claimedKingdomId.isEmpty() || !claimedKingdomId.equalsIgnoreCase(kingdomId)) {
                    notInPlayersKingdomBoard(player);
                } else {
                    inPlayersKingdomBoard(player, player.getLocation().getChunk());
                }
            }
            restorePluginData(player);
        }
    }

    // Method to check if a chunk is claimed
    private boolean isChunkClaimed(Chunk chunk) {
        String chunkID = chunk.getX() + "," + chunk.getZ();

        // Check if claimedChunks is not empty
        if (claimedChunks.isEmpty()) {
            return false;
        }

        // Check if the chunk is claimed
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

    public HashMap<String, String> getStaff() {
        return staff;
    }

    public HashMap<String, String> getKingdoms() {
        return kingdoms;
    }

    public HashMap<String, HashMap<Integer, String>> getKingdomRanks() {
        return kingdomRanks;
    }

    public HashMap<String, Integer> getPlayerRanks() {
        return playerRanks;
    }

    public HashMap<String, String> getSpyOnKingdom() {
        return spyOnKingdom;
    }

    public HashMap<String, Integer> getKingdomRank() {
        return kingdomRank;
    }

    public HashMap<String, Integer> getCustomRank() {
        return customRank;
    }

    public HashMap<Integer, String> getPlayerRankInKingdom() {
        return playerRankInKingdom;
    }

    public static Chat getChat() {
        return chat;
    }

    public HashMap<String, String> getPlayerToPunish() {
        return playerToPunish;
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

    public HashMap<String, Integer> getClaimPrice() {
        return claimPrice;
    }

    public HashMap<String, Integer> getMemberPrice() {
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

    public HashMap<String, Location> getKingdomSpawn() {
        return kingdomSpawn;
    }

    public HashMap<String, String> getInviteList() {
        return inviteList;
    }

    public HashMap<String, Long> getMoney() {
        return money;
    }

    public void restorePluginData() {

        //todo: set up restore methods for playerRanks, kingdomRanks, playerRank, spyOnKingdom

        for (Player player : Bukkit.getOnlinePlayers()) {

            Configurable kc = kingdomsConfig.getConfig();
            Configurable sc = staffConfig.getConfig();
            Configurable mc = moneyConfig.getConfig();
            Configurable pu = punishmentConfig.getConfig();

            if (!mc.getNode(player.getUniqueId().toString()).exists()) {
                playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                money.put(player.getUniqueId().toString(), 0L);
                chatFocus.put(player.getUniqueId().toString(), "GLOBAL");
                kingdoms.put(player.getUniqueId().toString(), "");
                passwords.put(player.getUniqueId().toString(), "");
            }

            if (kc != null) {
                if (kc.getNode("bannedNames").exists()) {
                    kc.getNode("bannedNames").getKeys(false).forEach(key -> {
                        String bannedNames = kc.getNode("bannedNames." + key).toPrimitive().getString();
                        this.bannedNames.put(key, bannedNames);
                    });
                }
                if (kc.getNode(player.getUniqueId().toString()) != null) {
                    if (kc.getNode(player.getUniqueId().toString()).exists()) {
                        kc.getNode(player.getUniqueId().toString()).getKeys(false).forEach(key -> {
                            String value = kc.getNode(player.getUniqueId().toString() + "." + key).toPrimitive().getString();
                            this.playerRankInKingdom.put(Integer.valueOf(key), value);
                        });
                    }
                }
                if (kc.getNode("invites").getNode(player.getUniqueId().toString()).exists()) {
                    kc.getNode("invites").getKeys(false).forEach(key -> {
                        String kingdom = kc.getNode("invites." + key).toPrimitive().getString();
                        inviteList.put(player.getUniqueId().toString(), kingdom);
                    });

                }
                if (kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).exists()) {
                    kingdoms.put(player.getUniqueId().toString(), kc.getNode("kingdoms." + player.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("maxClaims").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    maxClaims.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxClaims." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("spawns").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    kingdomSpawn.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("spawns." + kingdoms.get(player.getUniqueId().toString())).toGeneric(BukkitGeneric.class).getLocation());
                }
                if (kc.getNode("maxMembers").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    maxMembers.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxMembers." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    claimPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                    memberPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
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
                if (sc.getNode("passwords").getNode(player.getUniqueId().toString()).exists()) {
                    passwords.put(player.getUniqueId().toString(), sc.getNode("passwords." + player.getUniqueId().toString()).toPrimitive().getString());
                }
            }
        }
    }

    public void restoreOfflineData() {

        //todo: set up restore methods for playerRanks, kingdomRanks, playerRank, spyOnKingdom

        for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {

            Configurable kc = kingdomsConfig.getConfig();
            Configurable sc = staffConfig.getConfig();
            Configurable mc = moneyConfig.getConfig();
            Configurable pu = punishmentConfig.getConfig();

            // Helper method to check for valid path and handle exceptions
            if (kc != null) {
                // Validate and check paths before using getNode()
                if (kc.getNode("bannedNames").exists()) {
                    kc.getNode("bannedNames").getKeys(false).forEach(key -> {
                        String bannedNames = kc.getNode("bannedNames." + key).toPrimitive().getString();
                        this.bannedNames.put(key, bannedNames);
                    });
                }
                if (kc.getNode("kingdoms").getNode(offline.getUniqueId().toString()).exists()) {
                    kingdoms.put(offline.getUniqueId().toString(), kc.getNode("kingdoms." + offline.getUniqueId().toString()).toPrimitive().getString());
                }
                String playerUUID = offline.getUniqueId().toString();

                if (kingdoms.containsKey(playerUUID)) {
                    String kingdomName = kingdoms.get(playerUUID);

                    if (kc.getNode(kingdomName).exists()) {
                        for (String key : kc.getNode(kingdomName).getKeys(false)) {
                            try {
                                int rankValue = Integer.parseInt(key); // Convert rank key to an integer
                                String rankName = kc.getNode(kingdomName + "." + key).toPrimitive().getString();

                                if (rankName != null) {
                                    playerRankInKingdom.put(rankValue, rankName);
                                    MessageManager.consoleInfo("Restored rank for " + playerUUID + ": " + rankName + " (Rank " + rankValue + ")");
                                }
                            } catch (NumberFormatException ignored) {
                                MessageManager.consoleInfo("Skipping non-numeric key: " + key);
                            }
                        }
                    }
                }


                if (kc.getNode("invites").getNode(offline.getUniqueId().toString()).exists()) {
                    inviteList.put(offline.getUniqueId().toString(), kc.getNode("invites." + offline.getUniqueId().toString()).toPrimitive().getString());
                }
                if (kc.getNode("maxClaims").getNode(kingdoms.get(offline.getUniqueId().toString())).exists()) {
                    maxClaims.put(kingdoms.get(offline.getUniqueId().toString()), kc.getNode("maxClaims." + kingdoms.get(offline.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("spawns").getNode(kingdoms.get(offline.getUniqueId().toString())).exists()) {
                    kingdomSpawn.put(kingdoms.get(offline.getUniqueId().toString()), kc.getNode("spawns." + kingdoms.get(offline.getUniqueId().toString())).toGeneric(BukkitGeneric.class).getLocation());
                }
                if (kc.getNode("maxMembers").getNode(kingdoms.get(offline.getUniqueId().toString())).exists()) {
                    maxMembers.put(kingdoms.get(offline.getUniqueId().toString()), kc.getNode("maxMembers." + kingdoms.get(offline.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("claimPrice").getNode(kingdoms.get(offline.getUniqueId().toString())).exists()) {
                    claimPrice.put(kingdoms.get(offline.getUniqueId().toString()), kc.getNode("claimPrice").getNode(kingdoms.get(offline.getUniqueId().toString())).toPrimitive().getInt());
                }
                if (kc.getNode("memberPrice").getNode(kingdoms.get(offline.getUniqueId().toString())).exists()) {
                    memberPrice.put(kingdoms.get(offline.getUniqueId().toString()), kc.getNode("memberPrice").getNode(kingdoms.get(offline.getUniqueId().toString())).toPrimitive().getInt());
                }
                for (Chunk chunk : Bukkit.getWorld("kingdoms").getLoadedChunks()) {
                    if (kc.getNode("claims").getNode(chunk.getX() + "," + chunk.getZ()).exists()) {
                        if (kingdoms.containsKey(offline.getUniqueId().toString())) {
                            //chunkID, kingdom
                            kc.getNode("claims").getKeys(false).forEach(key -> {
                                String chunkClaimer = kc.getNode("claims." + key).toPrimitive().getString();
                                claimedChunks.put(key, chunkClaimer);
                            });
                        }
                    }
                }
            }

            // Repeat similar validation logic for the other configuration files (pu, mc, sc)
            if (pu != null) {
                if (pu.getNode("report").getNode(offline.getUniqueId().toString()).exists()) {
                    reportAbuse.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("discrimination").getNode(offline.getUniqueId().toString()).exists()) {
                    discrimination.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("language").getNode(offline.getUniqueId().toString()).exists()) {
                    language.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("ipAdverts").getNode(offline.getUniqueId().toString()).exists()) {
                    ipAdverts.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("mediaAdverts").getNode(offline.getUniqueId().toString()).exists()) {
                    mediaAdverts.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("soliciting").getNode(offline.getUniqueId().toString()).exists()) {
                    soliciting.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("spam").getNode(offline.getUniqueId().toString()).exists()) {
                    spam.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("threats").getNode(offline.getUniqueId().toString()).exists()) {
                    threats.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
                if (pu.getNode("disrespect").getNode(offline.getUniqueId().toString()).exists()) {
                    disrespect.put(offline.getUniqueId().toString(), pu.getNode("report." + offline.getUniqueId().toString()).toPrimitive().getInt());
                }
            }

            if (mc != null) {
                if (mc.getNode("balance").getNode(offline.getUniqueId().toString()).exists()) {
                    money.put(offline.getUniqueId().toString(), mc.getNode("balance." + offline.getUniqueId().toString()).toPrimitive().getLong());
                } else {
                    money.put(offline.getUniqueId().toString(), 0L);
                }
            }

            if (sc != null) {
                if (sc.getNode("rank").getNode(offline.getUniqueId().toString()).exists()) {
                    playerRank.put(offline.getUniqueId().toString(), sc.getNode("rank." + offline.getUniqueId().toString()).toPrimitive().getString());
                } else {
                    playerRank.put(offline.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
                }
                if (sc.getNode("staff").getNode(offline.getUniqueId().toString()).exists()) {
                    staff.put(offline.getUniqueId().toString(), sc.getNode("staff." + offline.getUniqueId().toString()).toPrimitive().getString());
                }
                if (sc.getNode("focus." + offline.getUniqueId().toString()).exists()) {
                    chatFocus.put(offline.getUniqueId().toString(), sc.getNode("focus." + offline.getUniqueId().toString()).toPrimitive().getString());
                }
                if (sc.getNode("passwords").getNode(offline.getUniqueId().toString()).exists()) {
                    passwords.put(offline.getUniqueId().toString(), sc.getNode("passwords." + offline.getUniqueId().toString()).toPrimitive().getString());
                }
            }

        }
    }

    public void restorePluginData(Player player) {

        //todo: set up restore methods for playerRanks, kingdomRanks, playerRank, spyOnKingdom

        Configurable kc = kingdomsConfig.getConfig();
        Configurable sc = staffConfig.getConfig();
        Configurable mc = moneyConfig.getConfig();
        Configurable pu = punishmentConfig.getConfig();

        if (!mc.getNode(player.getUniqueId().toString()).exists() || !kc.getNode(player.getUniqueId().toString()).exists()
                || !sc.getNode(player.getUniqueId().toString()).exists() || !pu.getNode(player.getUniqueId().toString()).exists()) {
            playerRank.put(player.getUniqueId().toString(), ChatColor.DARK_GRAY.toString() + ChatColor.BOLD + Rank.DEFAULT);
            money.put(player.getUniqueId().toString(), 0L);
            chatFocus.put(player.getUniqueId().toString(), "GLOBAL");
            kingdoms.put(player.getUniqueId().toString(), "");
            passwords.put(player.getUniqueId().toString(), "");
        }

        if (kc != null) {
            if (kc.getNode("bannedNames").exists()) {
                kc.getNode("bannedNames").getKeys(false).forEach(key -> {
                    String bannedNames = kc.getNode("bannedNames." + key).toPrimitive().getString();
                    this.bannedNames.put(key, bannedNames);
                });
            }
            if (kc.getNode("invites").getNode(player.getUniqueId().toString()).exists()) {
                inviteList.put(player.getUniqueId().toString(), kc.getNode("invites." + player.getUniqueId().toString()).toPrimitive().getString());
            }
            if (kc.getNode("kingdoms").getNode(player.getUniqueId().toString()).exists()) {
                kingdoms.put(player.getUniqueId().toString(), kc.getNode("kingdoms." + player.getUniqueId().toString()).toPrimitive().getString());
            }

            //todo: ends here
            if (kc.getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                kc.getNode(kingdoms.get(player.getUniqueId().toString())).getKeys(false).forEach(key -> {
                    Integer value = kc.getNode(kingdoms.get(player.getUniqueId().toString()) + "." + key).toPrimitive().getInt();
                    customRank.put(key, value);
                });
            }

            String playerUUID = player.getUniqueId().toString();

            if (kingdoms.containsKey(playerUUID)) {
                String kingdomName = kingdoms.get(playerUUID);

                if (kc.getNode(kingdomName).exists()) {
                    for (String key : kc.getNode(kingdomName).getKeys(false)) {
                        try {
                            int rankValue = Integer.parseInt(key); // Convert rank key to an integer
                            String rankName = kc.getNode(kingdomName + "." + key).toPrimitive().getString();

                            if (rankName != null) {
                                playerRankInKingdom.put(rankValue, rankName);
                                MessageManager.consoleInfo("Restored rank for " + playerUUID + ": " + rankName + " (Rank " + rankValue + ")");
                            }
                        } catch (NumberFormatException ignored) {
                            MessageManager.consoleInfo("Skipping non-numeric key: " + key);
                        }
                    }
                }
            }

            if (kc.getNode("maxClaims").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                maxClaims.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxClaims." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
            }
            if (kc.getNode("spawns").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                kingdomSpawn.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("spawns." + kingdoms.get(player.getUniqueId().toString())).toGeneric(BukkitGeneric.class).getLocation());
            }
            if (kc.getNode("maxMembers").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                maxMembers.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("maxMembers." + kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
            }
            if (kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                claimPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("claimPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
            }
            if (kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).exists()) {
                memberPrice.put(kingdoms.get(player.getUniqueId().toString()), kc.getNode("memberPrice").getNode(kingdoms.get(player.getUniqueId().toString())).toPrimitive().getInt());
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
            if (sc.getNode("passwords").getNode(player.getUniqueId().toString()).exists()) {
                passwords.put(player.getUniqueId().toString(), sc.getNode("passwords." + player.getUniqueId().toString()).toPrimitive().getString());
            }
        }
    }

    public void savePluginData(Player player) {
        //todo: make it so that a player's rank in the kingdom will be saved properly and that all kingdoms will be saved along with their applicable ranks
        saveData(punishmentConfig.getConfig(), reportAbuse, "report.");
        saveData(punishmentConfig.getConfig(), disrespect, "disrespect.");
        saveData(punishmentConfig.getConfig(), language, "language.");
        saveData(punishmentConfig.getConfig(), ipAdverts, "ipAdverts.");
        saveData(punishmentConfig.getConfig(), mediaAdverts, "mediaAdverts.");
        saveData(punishmentConfig.getConfig(), soliciting, "soliciting.");
        saveData(punishmentConfig.getConfig(), spam, "spam.");
        saveData(punishmentConfig.getConfig(), discrimination, "discrimination.");
        saveData(punishmentConfig.getConfig(), threats, "threats.");
        saveData(kingdomsConfig.getConfig(), bannedNames, "bannedNames.");
        saveData(kingdomsConfig.getConfig(), kingdoms, "kingdoms.");
        saveMatchingData(kingdomsConfig.getConfig(), claimedChunks, kingdoms, "claims.");
        saveData(kingdomsConfig.getConfig(), maxClaims, "maxClaims.");
        saveData(kingdomsConfig.getConfig(), maxMembers, "maxMembers.");
        saveData(kingdomsConfig.getConfig(), inviteList, "invites.");
        saveData(kingdomsConfig.getConfig(), kingdomSpawn, "spawns.");
        saveData(kingdomsConfig.getConfig(), memberPrice, "memberPrice.");
        saveData(kingdomsConfig.getConfig(), claimPrice, "claimPrice.");
        saveData(moneyConfig.getConfig(), money, "balance.");
        saveData(staffConfig.getConfig(), playerRank, "rank.");
        saveData(kingdomsConfig.getConfig(), kingdomRanks, "RanksInKingdoms");
        saveData(staffConfig.getConfig(), staff, "staff.");
        saveData(kingdomsConfig.getConfig(), playerRanks, "players.");
        saveData(staffConfig.getConfig(), chatFocus, "focus.");
        saveData(staffConfig.getConfig(), passwords, "passwords.");
        saveData(staffConfig.getConfig(), spyOnKingdom, "spy.");
        // Ensure the player has a valid kingdom before saving their rank
        String kingdomName = kingdoms.get(player.getUniqueId().toString());

        if (kingdomName != null && !kingdomName.isEmpty()) {
            saveData(kingdomsConfig.getConfig(), playerRankInKingdom, "players." + player.getUniqueId().toString() + ".");
        } else {
            MessageManager.consoleBad("Skipping save for " + player.getName() + " due to null or empty kingdom.");
        }
    }

    public void savePluginData() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            saveData(punishmentConfig.getConfig(), reportAbuse, "report.");
            saveData(punishmentConfig.getConfig(), disrespect, "disrespect.");
            saveData(punishmentConfig.getConfig(), language, "language.");
            saveData(punishmentConfig.getConfig(), ipAdverts, "ipAdverts.");
            saveData(punishmentConfig.getConfig(), mediaAdverts, "mediaAdverts.");
            saveData(punishmentConfig.getConfig(), soliciting, "soliciting.");
            saveData(punishmentConfig.getConfig(), spam, "spam.");
            saveData(punishmentConfig.getConfig(), discrimination, "discrimination.");
            saveData(punishmentConfig.getConfig(), threats, "threats.");
            saveData(kingdomsConfig.getConfig(), bannedNames, "bannedNames.");
            saveData(kingdomsConfig.getConfig(), kingdoms, "kingdoms.");
            saveMatchingData(kingdomsConfig.getConfig(), claimedChunks, kingdoms, "claims.");
            saveData(kingdomsConfig.getConfig(), maxClaims, "maxClaims.");
            saveData(kingdomsConfig.getConfig(), maxMembers, "maxMembers.");
            saveData(kingdomsConfig.getConfig(), inviteList, "invites.");
            saveData(kingdomsConfig.getConfig(), kingdomSpawn, "spawns.");
            saveData(kingdomsConfig.getConfig(), memberPrice, "memberPrice.");
            saveData(kingdomsConfig.getConfig(), claimPrice, "claimPrice.");
            saveData(moneyConfig.getConfig(), money, "balance.");
            saveData(staffConfig.getConfig(), playerRank, "rank.");
            saveData(kingdomsConfig.getConfig(), kingdomRanks, "RanksInKingdoms");
            saveData(staffConfig.getConfig(), staff, "staff.");
            saveData(kingdomsConfig.getConfig(), playerRanks, "players.");
            saveData(staffConfig.getConfig(), chatFocus, "focus.");
            saveData(staffConfig.getConfig(), passwords, "passwords.");
            saveData(staffConfig.getConfig(), spyOnKingdom, "spy.");
            // Ensure the player has a valid kingdom before saving their rank
            String kingdomName = kingdoms.get(player.getUniqueId().toString());

            if (kingdomName != null && !kingdomName.isEmpty()) {
                saveData(kingdomsConfig.getConfig(), playerRankInKingdom, "players." + player.getUniqueId().toString() + ".");
            } else {
                MessageManager.consoleBad("Skipping save for " + player.getName() + " due to null or empty kingdom.");
            }
        }

        for (OfflinePlayer offline : Bukkit.getOfflinePlayers()) {
            saveData(punishmentConfig.getConfig(), reportAbuse, "report.");
            saveData(punishmentConfig.getConfig(), disrespect, "disrespect.");
            saveData(punishmentConfig.getConfig(), language, "language.");
            saveData(punishmentConfig.getConfig(), ipAdverts, "ipAdverts.");
            saveData(punishmentConfig.getConfig(), mediaAdverts, "mediaAdverts.");
            saveData(punishmentConfig.getConfig(), soliciting, "soliciting.");
            saveData(punishmentConfig.getConfig(), spam, "spam.");
            saveData(punishmentConfig.getConfig(), discrimination, "discrimination.");
            saveData(punishmentConfig.getConfig(), threats, "threats.");
            saveData(kingdomsConfig.getConfig(), bannedNames, "bannedNames.");
            saveData(kingdomsConfig.getConfig(), kingdoms, "kingdoms.");
            saveMatchingData(kingdomsConfig.getConfig(), claimedChunks, kingdoms, "claims.");
            saveData(kingdomsConfig.getConfig(), maxClaims, "maxClaims.");
            saveData(kingdomsConfig.getConfig(), maxMembers, "maxMembers.");
            saveData(kingdomsConfig.getConfig(), inviteList, "invites.");
            saveData(kingdomsConfig.getConfig(), kingdomSpawn, "spawns.");
            saveData(kingdomsConfig.getConfig(), memberPrice, "memberPrice.");
            saveData(kingdomsConfig.getConfig(), claimPrice, "claimPrice.");
            saveData(moneyConfig.getConfig(), money, "balance.");
            saveData(staffConfig.getConfig(), playerRank, "rank.");
            saveData(kingdomsConfig.getConfig(), kingdomRanks, "RanksInKingdoms");
            saveData(staffConfig.getConfig(), staff, "staff.");
            saveData(kingdomsConfig.getConfig(), playerRanks, "players.");
            saveData(staffConfig.getConfig(), chatFocus, "focus.");
            saveData(staffConfig.getConfig(), passwords, "passwords.");
            saveData(staffConfig.getConfig(), spyOnKingdom, "spy.");
            // Ensure the player has a valid kingdom before saving their rank
            String kingdomName = kingdoms.get(offline.getUniqueId().toString());

            if (kingdomName != null && !kingdomName.isEmpty()) {
                saveData(kingdomsConfig.getConfig(), playerRankInKingdom, "players." + offline.getUniqueId().toString() + ".");
            } else {
                MessageManager.consoleBad("Skipping save for " + offline.getName() + " due to null or empty kingdom.");
            }
        }
    }

    private <K, V> void saveData(Configurable config, Map<K, V> data, String pathPrefix) {
        if (!data.isEmpty() && config != null) {
            for (Map.Entry<K, V> entry : data.entrySet()) {
                if (entry.getKey() != null && !entry.getKey().toString().trim().isEmpty()) { // Check for empty or null keys
                    config.set(pathPrefix + entry.getKey(), entry.getValue()); // Ensure value is a string
                }
            }
            config.save();
        }
    }


    private <K, V> void saveNestedData(Configurable config, Map<K, V> data, Map<K, String> kingdoms, String pathPrefix) {
        if (!data.isEmpty() && config != null) {
            for (Map.Entry<K, V> entry : data.entrySet()) {
                K key = entry.getKey(); // Key, e.g., player's UUID
                String kingdom = kingdoms.get(key); // Get the player's kingdom from the kingdoms map

                // Ensure the kingdom is valid (not null)
                if (kingdom != null && !kingdom.isEmpty()) {
                    String fullPath = pathPrefix + key; // Set key as the player's UUID in the path

                    // Set the kingdom as the value for the player's UUID key
                    config.set(fullPath, kingdom);
                }
            }
            config.save(); // Save the configuration after updating all entries
        }
    }

    private <K, V> void saveMatchingData(Configurable config, Map<K, V> claimedChunks, Map<K, V> kingdoms, String pathPrefix) {
        if (!claimedChunks.isEmpty() && config != null) {
            for (Map.Entry<K, V> claims : claimedChunks.entrySet()) {
                for (Map.Entry<K, V> kingdom : kingdoms.entrySet()) {
                    if (claimedChunks.get(claims.getKey()).equals(kingdom.getValue())) {
                        config.set(pathPrefix + claims.getKey(), claims.getValue());
                    }
                }
            }
            config.save();
        }
    }

    private void events() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomInfoListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Password(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomsCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WarzoneCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomUpgradeListener(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new WarzoneCommandListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CustomOreCommand(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new KingdomsListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new RandomTeleportListener(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new ChatListener(), this);
    }
}