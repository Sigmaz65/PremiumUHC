package fr.infarium.premiumuhc;

import com.onarandombox.MultiverseCore.MultiverseCore;
import fr.infarium.premiumuhc.commands.SaveInvCommands;
import fr.infarium.premiumuhc.commands.TeamsCommands;
import fr.infarium.premiumuhc.commands.UHCCommand;
import fr.infarium.premiumuhc.enums.GameState;
import fr.infarium.premiumuhc.listeners.*;
import fr.infarium.premiumuhc.manager.*;
import fr.infarium.premiumuhc.menu.ConfigHostAnvil;
import fr.infarium.premiumuhc.mysql.DatabaseManager;
import fr.infarium.premiumuhc.mysql.HostData;
import fr.infarium.premiumuhc.mysql.ScenariosData;
import fr.infarium.premiumuhc.mysql.TablesManager;
import fr.infarium.premiumuhc.tasks.ScoreBoardUpdate;
import fr.infarium.premiumuhc.tasks.TabUpdate;
import fr.infarium.premiumuhc.team.TeamList;
import fr.infarium.premiumuhc.team.UpdateTeams;
import fr.kotlini.supragui.InvHandler;
import fr.mrmicky.fastboard.FastBoard;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import javax.persistence.Table;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;


public final class Main extends JavaPlugin {

    private static Main instance;
    private FileConfiguration dataConfig = null;
    private File dataFile = null;
    public static GameState state;
    public DatabaseManager database;
    public HashMap<Player, FastBoard> scoreBoardPlayers = new HashMap<>();
    public List<TeamList> activesTeams = new ArrayList<>();


    @Override
    public void onEnable() {
        instance = this;

        //database
        database = new DatabaseManager("jdbc:mysql://", "176.31.132.185:3306", "jombkd_infarium_db?useSSL=false&characterEncoding=utf8", "jombkd_infarium_db", "Qu_Eq4%P87!i-1cY");
        database.connexion();
        //create tables
        TablesManager.testTables();


        // bungee
        getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeListener());

        //config.yml
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        ConfigManager.loadConfig(false);

        //data.yml
        saveDefaultDataConfig();
        getDataConfig().options().copyDefaults(true);
        reloadDataConfig();

        //insert missing scénarios
        ScenariosData.insertMissingScenarios();


        // setstate
        GameManager.setState(GameState.WAITING);

        //listeners
        Bukkit.getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);
        Bukkit.getPluginManager().registerEvents(new DamageListener(), this);
        Bukkit.getPluginManager().registerEvents(new AdvancementListener(), this);
        Bukkit.getPluginManager().registerEvents(new SaveInvListener(), this);
        Bukkit.getPluginManager().registerEvents(new NetherListener(), this);
        Bukkit.getPluginManager().registerEvents(new DropsListener(), this);
        Bukkit.getPluginManager().registerEvents(new ScListener(), this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);

        //enable message
        System.out.println("[PremiumUHC] ------------------------------------------------------");
        System.out.println("[PremiumUHC]            PremiumUHC 1.0-SNAPSHOP is loading...");
        System.out.println("[PremiumUHC]             The best UHC Plugin for BungeeCord");
        System.out.println("[PremiumUHC]                    Plugin by Sigmaz65");
        System.out.println("[PremiumUHC]");
        System.out.println("[PremiumUHC]                 Date Modified: 30/08/2023");
        System.out.println("[PremiumUHC] ------------------------------------------------------");

        // commandes
        getCommand("uhc").setExecutor(new UHCCommand());
        getCommand("finish").setExecutor(new SaveInvCommands());
        getCommand("enchant").setExecutor(new SaveInvCommands());
        getCommand("teams").setExecutor(new TeamsCommands());




        // worlds and scoreboard
        TeleportManager.loadSpawn();
        ScoreBoardUpdate.start();
        WorldManager.setWorldGameRule(Bukkit.getWorld("world"));

        //create world
        MultiverseCore mv = getPlugin(MultiverseCore.class);
        mv.deleteWorld("world");
        mv.deleteWorld("world_nether");

        mv.getMVWorldManager().addWorld("world", World.Environment.NORMAL, null, WorldType.NORMAL, true, null, true);

        final World world0 = getServer().getWorld("world");
        getServer().getPluginManager().registerEvents(new Listener()
        {
            @EventHandler
            public void onChunkPopulate(ChunkPopulateEvent event)
            {
                if(event.getWorld().equals(world0))
                {
                    int xOffset = event.getChunk().getX() * 16;
                    int zOffset = event.getChunk().getZ() * 16;
                    for(int xz = 0; xz < 256; xz++)
                    {
                        world0.setBiome(xOffset + xz / 16, zOffset + xz % 16, Biome.ROOFED_FOREST);
                    }
                }
            }
        }, this);

        //wordborder
        HostData hostData = new HostData(ConfigManager.server_host);
        Integer border_start = Integer.valueOf(HostData.getInfoHostString("border_start"));

        World world = Bukkit.getWorld("world");
        WorldBorder wb = world.getWorldBorder();
        wb.setCenter(ConfigManager.worldborder_center_x, ConfigManager.worldborder_center_y);
        wb.setSize(border_start * 2);


        // database set
        // HostData hostData = new HostData(ConfigManager.server_host);
        HostData.setInfoHostString("max_player", String.valueOf(ConfigManager.max_player));
        HostData.setInfoHostString("server_status", "§dEn attente de joueurs...");
        HostData.setInfoHostString("player_host", "§cEn attente...");
        VarManager.initializeServerVar();

        InvHandler.register(this);

        UpdateTeams.updateTeams();

        TabUpdate.start();
    }

    @Override
    public void onDisable() {
        System.out.println(TextManager.formatString(getConfig().getString("messages.console.disable-message")));
        DataManager.resetConfigHost();
        HostData.setInfoHostString("num_player", "0");
        HostData.setInfoHostString("server_status", "§6En redémarrage...");
        DataManager.resetConfigHost();

        saveDataConfig();
        saveDefaultConfig();
        database.deconnexion();
    }





    // Méthode pour obtenir la configuration "data.yml"
    public void reloadDataConfig() {
        if (dataFile == null) {
            dataFile = new File(getDataFolder(), "data.yml");
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);

        // Charger le fichier de configuration par défaut s'il est vide
        try (Reader defaultConfigStream = new InputStreamReader(getResource("data.yml"), StandardCharsets.UTF_8)) {
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultConfigStream);
            dataConfig.setDefaults(defaultConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Méthode pour obtenir la configuration "data.yml"
    public FileConfiguration getDataConfig() {
        if (dataConfig == null) {
            reloadDataConfig();
        }
        return dataConfig;
    }

    // Méthode pour sauvegarder la configuration "data.yml"
    public void saveDataConfig() {
        if (dataConfig == null || dataFile == null) {
            return;
        }
        try {
            getDataConfig().save(dataFile);
        } catch (IOException e) {
            getLogger().warning("Impossible de sauvegarder le fichier data.yml !");
        }
    }

    // Méthode pour créer le fichier "data.yml" s'il n'existe pas
    public void saveDefaultDataConfig() {
        if (dataFile == null) {
            dataFile = new File(getDataFolder(), "data.yml");
        }
        if (!dataFile.exists()) {
            saveResource("data.yml", false);
        }
    }
    public static Main getInstance() {
        return instance;
    }
}
