package net.aesircraft.AesirRPG;

import java.util.HashMap;
import java.util.List;
import net.aesircraft.TrueEconomy.Data.Bank;
import net.aesircraft.TrueEconomy.Data.Form;
import net.aesircraft.TrueEconomy.Data.Players;
import net.aesircraft.TrueEconomy.Exchange;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Quester
{
	private Player player;
	private String quest;
	private boolean loaded = false;
	private HashMap<ItemStack, Integer> collectItems;
	private HashMap<String, Integer> kills;
	private List<Integer> blocks;
	private List<Byte> blockData;
	private List<Integer> blockCounts;
	private List<Integer> blockDestroys;
	private List<Byte> blockDestroyData;
	private List<Integer> blockDestroyCounts;
	
	
	
	public void echoRequirements(){
		Quest questa=new Quest();
		questa.load(quest);
		if (questa.getBlocks()!=null){
			List<Integer> blocksa=questa.getBlockCounts();
			List<Integer> blocksn=questa.getBlocks();
			List<Byte> blocksd=questa.getBlockDatas();

			for (int ctr=0;ctr<blocksa.size();ctr++){
				player.sendMessage("§2Place §b"+Material.getMaterial(blocksn.get(ctr)).name()+"§2 Data: §b"+blocksd.get(ctr)+" - §b"+blockCounts.get(ctr)+"§2 of §b"+blocksa.get(ctr));					
			}
		}
		if (questa.getBlockDestroys()!=null){
			List<Integer> blockDestroysa=questa.getBlockDestroyCounts();
			List<Integer> blockDestroysn=questa.getBlockDestroys();
			List<Byte> blockDestroysd=questa.getBlockDestroyData();
			for (int ctr1=0;ctr1<blockDestroysa.size();ctr1++){
				player.sendMessage("§2Destroy §b"+Material.getMaterial(blockDestroysn.get(ctr1)).name()+"§2 Data: §b"+blockDestroysd.get(ctr1)+" - §b"+blockDestroyCounts.get(ctr1)+"§2 of §b"+blockDestroysa.get(ctr1));
			}
		}
		
		if (questa.getKills()!=null){
			List<String> killsa=questa.getKills();
			List<Integer> killsc=questa.getKillCounts();
			for (int ctr2=0;ctr2<killsa.size();ctr2++){
				player.sendMessage("§2Kill §b"+killsa.get(ctr2)+"§2 - §b"+kills.get(killsa.get(ctr2).toLowerCase())+"§2 of §b"+ killsc.get(ctr2));
			}
				
		}
		
		if (questa.getCollectItems()!=null){
			List<ItemStack> items=questa.getCollectItems();
			for (int ctr3=0;ctr3<items.size();ctr3++){
				player.sendMessage("§2Collect §b"+items.get(ctr3).getType().name()+"§2 Data: §b"+items.get(ctr3).getData()+ " - §b"+getCollectItemProgress(items.get(ctr3))+"§2 of §b"+items.get(ctr3).getAmount());
			}
		}
		
		if (questa.getRequiredItems()!=null){
			List<ItemStack> itemsa=questa.getRequiredItems();
			for (int ctr4=0;ctr4<itemsa.size();ctr4++){
				player.sendMessage("§2Give §b"+itemsa.get(ctr4).getType().name()+"§2 Data: §b"+itemsa.get(ctr4).getData()+"§2 Amount: §b"+itemsa.get(ctr4).getAmount()+"§2 - §b"+InventoryWorkaround.containsItem(player.getInventory(), true, itemsa.get(ctr4)));
		
			}
		}
		
		if (questa.getRequiredExp()!=0){
			player.sendMessage("§2Give Exp - §b"+getExp()+"§2 of §b"+questa.getRequiredExp());
							
		}
		
		if (questa.getRequiredEyrir()!=0){
			player.sendMessage("§2Give Eyrir - §b"+getEyrir()+"§2 of §b"+questa.getRequiredEyrir());			
		}
		
	}
	
	public void echoDescription(){
		Quest questa=new Quest();
		questa.load(quest);
		player.sendMessage("§eTitle: §b"+questa.getTitle());
		player.sendMessage("§e------------------");
		player.sendMessage("§bDescription: §e");
		List<String> messages=questa.getDescription();
			for (int ctr=0;ctr<messages.size();ctr++){
				player.sendMessage("§e"+messages.get(ctr));
			}
	}
	
	public void echoRewards(){
		Quest questa=new Quest();
		questa.load(quest);
		player.sendMessage("§eRewards");
		player.sendMessage("§e------------------");
		List<String> messages=questa.getRewardDescription();
			for (int ctr=0;ctr<messages.size();ctr++){
				player.sendMessage("§e"+messages.get(ctr));
			}
	}
	
	
	
	public void completeQuest(){
		Quest questa=new Quest();
		questa.load(quest);
		if (hasMetRequirements()){
			takeRequirements();
			giveRewards();
			List<String> messages=questa.getCompletionMessage();
			for (int ctr=0;ctr<messages.size();ctr++){
				player.sendMessage("§e"+messages.get(ctr));
			}
			complete();
		}		
	}
	
	public void giveRewards(){
		Quest questa=new Quest();
		questa.load(quest);
		if (questa.getRewardItems()!=null){
			List<ItemStack> items=questa.getRewardItems();
			for (int ctr4=0;ctr4<items.size();ctr4++){
				InventoryWorkaround.addItem(player.getInventory(), true,items.get(ctr4));
					
			}
		}
		
		if (questa.getRewardExp()!=0){
			addExp(questa.getRewardExp());			
		}
		if (questa.getHeroesExp()!=0){
			addHeroExp(questa.getHeroesExp());			
		}
		
		if (questa.getRewardEyrir()!=0){
			addEyrir(questa.getRewardEyrir());			
		}
	}
	
	
	public void takeRequirements(){
		Quest questa=new Quest();
		questa.load(quest);
		
		
		
		if (questa.getRequiredItems()!=null){
			List<ItemStack> items=questa.getRequiredItems();
			for (int ctr4=0;ctr4<items.size();ctr4++){
				InventoryWorkaround.removeItem(player.getInventory(), true,items.get(ctr4));
					
			}
		}
		
		if (questa.getRequiredExp()!=0){
			subtractExp(questa.getRequiredExp());			
		}
		
		if (questa.getRequiredEyrir()!=0){
			subtractEyrir(questa.getRequiredEyrir());			
		}		
		
	}
	
	
	public boolean hasMetRequirements(){
		Quest questa=new Quest();
		questa.load(quest);
		if (questa.getBlocks()!=null){
			List<Integer> blocksa=questa.getBlockCounts();
			for (int ctr=0;ctr<blocksa.size();ctr++){
				if (blocksa.get(ctr)>blockCounts.get(ctr))
					return false;
			}
		}
		if (questa.getBlockDestroys()!=null){
			List<Integer> blockDestroysa=questa.getBlockCounts();
			for (int ctr1=0;ctr1<blockDestroysa.size();ctr1++){
				if (blockDestroysa.get(ctr1)>blockDestroyCounts.get(ctr1))
					return false;
			}
		}
		
		if (questa.getKills()!=null){
			List<String> killsa=questa.getKills();
			List<Integer> killsc=questa.getKillCounts();
			for (int ctr2=0;ctr2<killsa.size();ctr2++){
				if (kills.get(killsa.get(ctr2).toLowerCase())>killsc.get(ctr2))
					return false;
			}
		}
		
		if (questa.getCollectItems()!=null){
			List<ItemStack> items=questa.getCollectItems();
			for (int ctr3=0;ctr3<items.size();ctr3++){
				if (items.get(ctr3).getAmount()>getCollectItemProgress(items.get(ctr3)))
					return false;
			}
		}
		
		if (questa.getRequiredItems()!=null){
			List<ItemStack> items=questa.getRequiredItems();
			for (int ctr4=0;ctr4<items.size();ctr4++){
				if (!InventoryWorkaround.containsItem(player.getInventory(), true,items.get(ctr4)))
					return false;
			}
		}
		
		if (questa.getRequiredExp()!=0){
			if (getExp()<questa.getRequiredExp())
				return false;			
		}
		
		if (questa.getRequiredEyrir()!=0){
			if (getEyrir()<questa.getRequiredEyrir())
				return false;			
		}
		
		return true;
		
	}
	
	private double getEyrir(){
		return Players.get(player.getName().toLowerCase());
	}
	private void subtractEyrir(double amount){
		Players.sub(player.getName().toLowerCase(),amount);
		player.sendMessage("§2You gave §b"+Form.form(amount)+"§2 to complete the quest.");
		Bank.add(amount);
	}
	private void addEyrir(double amount){
		if (Bank.locked()){
			player.sendMessage("§4You were unable to recieve the amount of §b"+Form.form(amount)+"§4 because the bank is locked down!");
			return;
		}
		Players.add(player.getName().toLowerCase(),amount);
		player.sendMessage("§2For completing this quest you recieved §b"+Form.form(amount)+"§2!");
		Bank.sub(amount);
		Exchange.tax(player,amount);
	}
	
	public boolean isCollectingItems(){
		if (collectItems==null)
			return false;
		return true;
	}
	public boolean isCollectingKills(){
		if (kills==null)
			return false;
		return true;
	}
	public boolean isCollectingblocks(){
		if (blocks==null)
			return false;
		return true;
	}
	public boolean isCollectingBlockDestroys(){
		if (blockDestroys==null)
			return false;
		return true;
	}
	
	public boolean doingCollectItem(ItemStack item)
	{
		ItemStack stack = new ItemStack(item.getTypeId(), item.getData().getData());
		if (collectItems.containsKey(stack))
		{
			return true;
		}
		return false;
	}
	
	public boolean doingKills(String kill)
	{
		if (kills.containsKey(kill.toLowerCase()))
		{
			return true;
		}
		return false;
	}
	
	private void complete()
	{
		Quest questa = new Quest();
		questa.load(quest);
		questa.addCompleter(player.getName());		
		questa.update();
		Filer.complete(this);
	}
	
	public void setOnQuest(Quest quest)
	{
		if (canQuest(quest)){
		Filer.newPQuest(this, quest);
		quest.addQuester(player.getName().toLowerCase());
		}
	}
	
	public int getCollectItemProgress(ItemStack item)
	{
		ItemStack stack = new ItemStack(item.getTypeId(), item.getData().getData());
		if (collectItems.containsKey(stack))
		{
			return collectItems.get(stack);
		}
		return 0;
	}
	
	public int getKillsProgress(String kill)
	{
		if (kills.containsKey(kill.toLowerCase()))
		{
			return kills.get(kill.toLowerCase());
		}
		return 0;
	}
	
	public void addACollectItem(ItemStack item)
	{
		ItemStack stack = new ItemStack(item.getTypeId(), item.getData().getData());
		if (collectItems.containsKey(stack))
		{
			int amount = collectItems.get(stack);
			collectItems.remove(stack);
			collectItems.put(stack, amount + item.getAmount());
		}
		Filer.updateCollectItems(this);
	}
	
	public void addAKill(String kill)
	{		
		if (kills.containsKey(kill.toLowerCase()))
		{
			int amount = kills.get(kill.toLowerCase());
			kills.remove(kill.toLowerCase());
			kills.put(kill.toLowerCase(), amount + 1);
		}
		Filer.updateKills(this);
	}
	
	public boolean doingBlock(int id, byte data)
	{
		for (int ctr = 0; ctr < blocks.size(); ctr++)
		{
			if (blocks.get(ctr) == id)
			{
				if (blockData.get(ctr) == data)
				{
					return true;
				}
			}			
		}
		return false;
	}
	
	public boolean doingBlockDestroy(int id, byte data)
	{
		for (int ctr = 0; ctr < blockDestroys.size(); ctr++)
		{
			if (blockDestroys.get(ctr) == id)
			{
				if (blockDestroyData.get(ctr) == data)
				{
					return true;
				}
			}			
		}
		return false;
	}
	
	public int getBlockProgress(int id, byte data)
	{
		for (int ctr = 0; ctr < blocks.size(); ctr++)
		{
			if (blocks.get(ctr) == id)
			{
				if (blockData.get(ctr) == data)
				{
					return blockCounts.get(ctr);
				}
			}			
		}
		return 0;
	}
	
	public int getBlockDestroyProgress(int id, byte data)
	{
		for (int ctr = 0; ctr < blockDestroys.size(); ctr++)
		{
			if (blockDestroys.get(ctr) == id)
			{
				if (blockDestroyData.get(ctr) == data)
				{
					return blockDestroyCounts.get(ctr);
				}
			}			
		}
		return 0;
	}
	
	public void addABlock(int id, byte data)
	{
		for (int ctr = 0; ctr < blocks.size(); ctr++)
		{
			if (blocks.get(ctr) == id)
			{
				if (blockData.get(ctr) == data)
				{
					blockCounts.set(ctr, blockCounts.get(ctr) + 1);
				}
			}			
		}
		Filer.updateBlockCounts(this);
	}
	
	public void addABlockDestroy(int id, byte data)
	{
		for (int ctr = 0; ctr < blockDestroys.size(); ctr++)
		{
			if (blockDestroys.get(ctr) == id)
			{
				if (blockDestroyData.get(ctr) == data)
				{
					blockDestroyCounts.set(ctr, blockDestroyCounts.get(ctr) + 1);
				}
			}			
		}
		Filer.updateBlockDestroyCounts(this);
	}
	
	public void addBlocks(int id, byte data, int count)
	{
		blocks.add(id);
		blockData.add(data);
		blockCounts.add(count);
	}
	
	public void setQuest(String title)
	{
		quest = title;
	}
	
	public void addBlockDestroys(int id, byte data, int count)
	{
		blockDestroys.add(id);
		blockDestroyData.add(data);
		blockDestroyCounts.add(count);
	}
	
	public void addCollectItem(ItemStack item, int amount)
	{
		collectItems.put(item, amount);
	}
	
	
	private String getHeroClass(){
		if (AesirRPG.heroes)
		return AesirRPG.hero.getHeroManager().getHero(player).getHeroClass().getName();
		return "";
	}
	
	private boolean isHeroClass(String name){
		if (AesirRPG.heroes)
		return (AesirRPG.hero.getHeroManager().getHero(player).getHeroClass().getName().toLowerCase().equals(name.toLowerCase()));
		return false;
	}
	
	private void addHeroExp(double amount){
		if (AesirRPG.heroes)
		AesirRPG.hero.getHeroManager().getHero(player).addExp(amount, AesirRPG.hero.getHeroManager().getHero(player).getHeroClass());
	}
	
	private int getExp(){
		return player.getTotalExperience();
	}
	
	public void giveExp(int amount){
		for (int expctr=0;expctr<amount;expctr++){
				player.giveExp(1);
			}
	}
	
	private boolean canQuest(Quest quest){
		String requiredQuest;
		String hClass;		
		if (quest.getRequiredClass()!=null && AesirRPG.heroes){
		hClass=quest.getRequiredClass().toLowerCase();
		if (!isHeroClass(hClass))
			player.sendMessage("§4You are not a §b"+hClass+"§4!");
			return false;
		}
		if (quest.getRequires()!=null){
		requiredQuest=quest.getRequires();
		if (!completed(requiredQuest))
			player.sendMessage("§4You have not completed §b"+requiredQuest+"§4!");
			return false;
		}
		if (!quest.isRepeatable()){
			if (completed(quest.getTitle())){
			player.sendMessage("§4You already completed this quest!");
			return false;
			}
		}
		return true;
	}
	
	private boolean completed(String name){
		Quest quest=new Quest();
		quest.load(name);
		if (quest.isLoaded()){
			List<String> list=quest.getCompleters();
			for (int ctr=0;ctr<list.size();ctr++){
				if (list.get(ctr).toLowerCase().equals(player.getName().toLowerCase()))
					return true;
			}
		}
		return false;
			
	}
	public void addExp(int amount){
			for (int expctr=0;expctr<amount;expctr++){
				player.giveExp(1);
			}
	}
	public void subtractExp(int amount){
		int exp=player.getTotalExperience()-amount;			
			player.setExp(0);
			player.setTotalExperience(0);
			player.setLevel(0);
			for (int expctr=0;expctr<exp;expctr++){
				player.giveExp(1);
			}
	}
	
	public void addKills(String name, int amount)
	{
		kills.put(name.toLowerCase(), amount);
	}

	public void setLoaded(boolean bool)
	{
		loaded = bool;
	}
	
	public void set(Player player)
	{
		this.player = player;
	}
	
	public void load()
	{
		Filer.load(this);
	}
	
	public Player getPlayer()
	{
		return player;
	}

	public String getQuest()
	{
		return quest;
	}

	public boolean isLoaded()
	{
		return loaded;
	}
	
	public boolean onQuest()
	{
		return Filer.exists(this);
	}
}
