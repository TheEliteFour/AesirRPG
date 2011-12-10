package net.aesircraft.AesirRPG;

import java.util.List;
import org.bukkit.inventory.ItemStack;

public class Quest
{
	private List<String> description;
	private List<String> completionMessage;
	private String title;
	private String requires;
	private List<String> objectives;
	private List<String> rewardDescription;
	private boolean repeatable;
	private boolean loaded=false;
	private int rewardExp;
	private double rewardEyrir;
	private int requiredExp;
	private double requiredEyrir;
	private String requiredClass;
	private int heroesExp;
	private List<String> completers;
	private List<String> questers;
	private List<ItemStack> rewardItems;
	private List<ItemStack> collectItems;
	private List<String> kills;
	private List<Integer> killCounts;
	private List<Integer> blocks;
	private List<Byte> blockData;
	private List<Integer> blockCounts;
	private List<Integer> blockDestroys;
	private List<Byte> blockDestroyData;
	private List<Integer> blockDestroyCounts;
	private List<ItemStack> requiredItems;
	
	public void update(){
		Filer.update(this);
	}
	public void load(String name){
		Filer.load(this);
	}
	public void save(){
		Filer.newQuest(this);
	}
	
	public String getTitle(){
		return title;
	}
	public String getRequires(){
		return requires;
	}
	public String getRequiredClass(){
		return requiredClass;
	}
	public int getHeroesExp(){
		return heroesExp;
	}
	public List<String> getDescription(){
		return description;
	}
	public List<String> getCompletionMessage(){
		return completionMessage;
	}
	public List<ItemStack> getCollectItems(){
		return collectItems;
	}
	public List<String> getObjectives(){
		return objectives;
	}
	public List<String> getRewardDescription(){
		return rewardDescription;
	}
	public boolean isRepeatable(){
		return repeatable;
	}
	public int getRewardExp(){
		return rewardExp;
	}
	public boolean isLoaded(){
		return loaded;
	}
	public int getRequiredExp(){
		return requiredExp;
	}
	public double getRewardEyrir(){
		return rewardEyrir;
	}
	public double getRequiredEyrir(){
		return requiredEyrir;
	}
	public List<String> getCompleters(){
		return completers;
	}
	public List<String> getKills(){
		return kills;
	}
	public List<Integer> getKillCounts(){
		return killCounts;
	}
	public List<String> getQuesters(){
		return questers;
	}
	public List<ItemStack> getRewardItems(){
		return rewardItems;
	}
	public List<ItemStack> getRequiredItems(){
		return requiredItems;
	}
	public List<Integer> getBlocks(){
		return blocks;
	}
	public List<Byte> getBlockDatas(){
		return blockData;
	}
	public List<Integer> getBlockCounts(){
		return blockCounts;
	}
	public List<Integer> getBlockDestroys(){
		return blockDestroys;
	}
	public List<Byte> getBlockDestroyData(){
		return blockDestroyData;
	}
	public List<Integer> getBlockDestroyCounts(){
		return blockDestroyCounts;
	}
	public void setTitle(String string){
		title=string;
	}
	public void setRequires(String string){
		requires=string;
	}
	public void addKills(String string, int amount){
		kills.add(string);
		killCounts.add(amount);
	}
	public void removeKills(int index){
		kills.remove(index);
		killCounts.remove(index);
	}
	public void addCollectItem(ItemStack item){
		collectItems.add(item);
	}
	public void removeCollectItem(int index){
		collectItems.remove(index);
	}
	public void addCompletionMessage(String string){
		completionMessage.add(string);
	}
	public void removeCompletionMessage(int index){
		completionMessage.remove(index);
	}
	public void addDescription(String string){
		description.add(string);
	}
	public void addObjectives(String string){
		objectives.add(string);
	}
	public void addRewardDescription(String string){
		rewardDescription.add(string);
	}
	public void removeDescription(int index){
		description.remove(index);
	}
	public void removeObjectives(int index){
		objectives.remove(index);
	}
	public void removeRewardDescription(int index){
		rewardDescription.remove(index);
	}
	public void setRequiredClass(String string){
		requiredClass=string;
	}
	public void setHeroesExp(int exp){
		heroesExp=exp;
	}
	public void setLoaded(boolean bool){
		loaded=bool;
	}
	public void setRepeatable(boolean bool){
		repeatable=bool;
	}
	public void setRewardExp(int exp){
		rewardExp=exp;
	}
	public void setRewardEyrir(int eyrir){
		rewardEyrir=eyrir;
	}
	public void setRequiredExp(int exp){
		requiredExp=exp;
	}
	public void setRequiredEyrir(int eyrir){
		requiredEyrir=eyrir;
	}
	public void addCompleter(String string){
		completers.add(string);
	}
	public void addQuester(String string){
		questers.add(string);
	}
	public void removeCompleter(int index){
		completers.remove(index);
	}
	public void removeQuester(int index){
		questers.removeAll(blocks);
	}
	public void addRewardItem(ItemStack item){
		rewardItems.add(item);
	}
	public void addRequiredItem(ItemStack item){
		requiredItems.add(item);
	}
	public void removeRewardItem(int index){
		rewardItems.remove(index);
	}
	public void removeRequiredItem(int index){
		requiredItems.remove(index);
	}
	public void addBlock(int id,byte data,int count){
		blocks.add(id);
		blockData.add(data);
		blockCounts.add(count);
	}
	public void removeBlock(int index){
		blocks.remove(index);
		blockData.remove(index);
		blockCounts.remove(index);
	}
	public void addBlockDestroy(int id,byte data,int count){
		blockDestroys.add(id);
		blockDestroyData.add(data);
		blockDestroyCounts.add(count);
	}
	public void removeBlockDestroy(int index){
		blockDestroys.remove(index);
		blockDestroyData.remove(index);
		blockDestroyCounts.remove(index);
	}
}
