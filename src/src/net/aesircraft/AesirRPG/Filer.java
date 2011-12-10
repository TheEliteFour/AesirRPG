package net.aesircraft.AesirRPG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;


public class Filer
{
	private static String sep = File.separator;

	public static boolean exists(Quester quester)
	{
		File folder = new File(AesirRPG.getStatic().getDataFolder() + sep + "Players");
		File file = file(quester);
		if (!folder.exists())
		{
			folder.mkdir();
			return false;
		}
		if (file.exists())
		{
			return true;
		}
		return false;
	}

	public static boolean exists(Quest quest)
	{
		File folder = new File(AesirRPG.getStatic().getDataFolder() + sep + "Quests");
		File file = file(quest);
		if (!folder.exists())
		{
			folder.mkdir();
			return false;
		}
		if (file.exists())
		{
			return true;
		}
		return false;
	}

	public static String name(Quest quest)
	{
		return quest.getTitle().replace(" ", "").toLowerCase();
	}

	public static String name(Quester quester)
	{
		return quester.getPlayer().getName().replace(" ", "").toLowerCase();
	}

	public static void delete(Quest quest)
	{
		if (exists(quest))
		{
			File file = file(quest);
			file.delete();
		}
	}

	public static File file(Quest quest)
	{
		return new File(AesirRPG.getStatic().getDataFolder() + sep + "Quests" + sep + name(quest) + ".qst");
	}

	public static File file(Quester quester)
	{
		return new File(AesirRPG.getStatic().getDataFolder() + sep + "Players" + sep + name(quester) + ".plr");
	}

	public static void newQuest(Quest quest)
	{
		File file = file(quest);
		if (file.exists())
		{
			return;
		}
		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try
		{
			file.createNewFile();
			fileWriter = new FileWriter(file);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.append("title: " + quest.getTitle());
			bufferWriter.newLine();


			bufferWriter.append("description:");
			bufferWriter.newLine();
			for (int dctr = 0; dctr < quest.getDescription().size(); dctr++)
			{
				bufferWriter.append("- " + quest.getDescription().get(dctr));
				bufferWriter.newLine();
			}


			bufferWriter.append("objectives:");
			bufferWriter.newLine();
			for (int octr = 0; octr < quest.getObjectives().size(); octr++)
			{
				bufferWriter.append("- " + quest.getObjectives().get(octr));
				bufferWriter.newLine();
			}


			bufferWriter.append("completion:");
			bufferWriter.newLine();
			for (int cctr = 0; cctr < quest.getCompletionMessage().size(); cctr++)
			{
				bufferWriter.append("- " + quest.getCompletionMessage().get(cctr));
				bufferWriter.newLine();
			}


			if (quest.getRequires() != null)
			{
				bufferWriter.append("requires: " + quest.getRequires());
				bufferWriter.newLine();
			}


			bufferWriter.append("repeatable: " + Boolean.toString(quest.isRepeatable()));
			bufferWriter.newLine();


			bufferWriter.append("rewards:");
			bufferWriter.newLine();
			for (int rctr = 0; rctr < quest.getRewardDescription().size(); rctr++)
			{
				bufferWriter.append("- " + quest.getRewardDescription().get(rctr));
				bufferWriter.newLine();
			}


			if (quest.getRewardEyrir() > 0)
			{
				bufferWriter.append("eyrir: " + quest.getRequiredEyrir());
				bufferWriter.newLine();
			}


			if (quest.getHeroesExp() != 0)
			{
				bufferWriter.append("heroesexp: " + quest.getHeroesExp());
				bufferWriter.newLine();
			}


			if (quest.getRequiredClass() != null)
			{
				bufferWriter.append("requiredclass: " + quest.getRequiredClass());
				bufferWriter.newLine();
			}


			if (quest.getRewardExp() > 0)
			{
				bufferWriter.append("exp: " + quest.getRequiredExp());
				bufferWriter.newLine();
			}


			if (quest.getRewardItems() != null)
			{
				bufferWriter.append("rewarditems:");
				bufferWriter.newLine();
				for (int rictr = 0; rictr < quest.getRewardItems().size(); rictr++)
				{
					bufferWriter.append("  " + quest.getRewardItems().get(rictr).getTypeId() + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getRewardItems().get(rictr).getAmount());
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getRewardItems().get(rictr).getDurability());
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getRewardItems().get(rictr).getData().getData());
					bufferWriter.newLine();
				}
			}


			if (quest.getCollectItems() != null)
			{
				bufferWriter.append("collectitems:");
				bufferWriter.newLine();
				for (int cictr = 0; cictr < quest.getCollectItems().size(); cictr++)
				{
					bufferWriter.append("  " + quest.getCollectItems().get(cictr).getTypeId() + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getCollectItems().get(cictr).getAmount());
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getCollectItems().get(cictr).getData().getData());
					bufferWriter.newLine();
				}
			}


			if (quest.getRequiredItems() != null)
			{
				bufferWriter.append("requireditems:");
				bufferWriter.newLine();
				for (int rqictr = 0; rqictr < quest.getRequiredItems().size(); rqictr++)
				{
					bufferWriter.append("  " + quest.getRequiredItems().get(rqictr).getTypeId() + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getRequiredItems().get(rqictr).getAmount());
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getRequiredItems().get(rqictr).getDurability());
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getRequiredItems().get(rqictr).getData().getData());
					bufferWriter.newLine();
				}
			}


			if (quest.getBlocks() != null)
			{
				bufferWriter.append("blocks:");
				bufferWriter.newLine();
				for (int rbctr = 0; rbctr < quest.getBlocks().size(); rbctr++)
				{
					bufferWriter.append("  " + quest.getBlocks().get(rbctr) + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getBlockCounts().get(rbctr));
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getBlockDatas().get(rbctr));
					bufferWriter.newLine();
				}
			}


			if (quest.getBlockDestroys() != null)
			{
				bufferWriter.append("blockdestroys:");
				bufferWriter.newLine();
				for (int bdctr = 0; bdctr < quest.getBlockDestroys().size(); bdctr++)
				{
					bufferWriter.append("  " + quest.getBlockDestroys().get(bdctr) + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getBlockDestroyCounts().get(bdctr));
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getBlockDestroyData().get(bdctr));
					bufferWriter.newLine();
				}
			}


			if (quest.getKills() != null)
			{
				bufferWriter.append("kills:");
				bufferWriter.newLine();
				for (int kctr = 0; kctr < quest.getKills().size(); kctr++)
				{
					bufferWriter.append("  " + quest.getKills().get(kctr) + ": " + quest.getKillCounts().get(kctr));
					bufferWriter.newLine();
				}
			}


			bufferWriter.append("completers:");
			bufferWriter.newLine();
			bufferWriter.append("questers:");
			bufferWriter.newLine();
		}
		catch (IOException ex)
		{
		}
	}

	public static void update(Quest quest)
	{
		if (!exists(quest))
		{
			return;
		}
		File file = file(quest);
		YamlConfiguration config = new YamlConfiguration();
		try
		{
			config.load(file);
		}
		catch (FileNotFoundException ex)
		{
		}
		catch (IOException ex)
		{
		}
		catch (InvalidConfigurationException ex)
		{
		}


		config.set("completers", quest.getCompleters());
		config.set("questers", quest.getQuesters());
		try
		{
			config.save(file);
		}
		catch (IOException ex)
		{
		}
	}

	public static void newPQuest(Quester player, Quest quest)
	{
		File file = file(player);
		if (file.exists())
		{
			return;
		}


		FileWriter fileWriter = null;
		BufferedWriter bufferWriter = null;
		try
		{
			file.createNewFile();
			fileWriter = new FileWriter(file);
			bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.append("title: " + quest.getTitle());
			bufferWriter.newLine();


			if (quest.getCollectItems() != null)
			{
				bufferWriter.append("collectitems:");
				bufferWriter.newLine();
				for (int cictr = 0; cictr < quest.getCollectItems().size(); cictr++)
				{
					bufferWriter.append("  " + quest.getCollectItems().get(cictr).getTypeId() + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getCollectItems().get(cictr).getData().getData());
					bufferWriter.newLine();
					bufferWriter.append("  - 0");
					bufferWriter.newLine();
				}
			}

			if (quest.getBlocks() != null)
			{
				bufferWriter.append("blocks:");
				bufferWriter.newLine();
				for (int rbctr = 0; rbctr < quest.getBlocks().size(); rbctr++)
				{
					bufferWriter.append("  " + quest.getBlocks().get(rbctr) + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getBlockDatas().get(rbctr));
					bufferWriter.newLine();
					bufferWriter.append("  - 0");
					bufferWriter.newLine();
				}
			}


			if (quest.getBlockDestroys() != null)
			{
				bufferWriter.append("blockdestroys:");
				bufferWriter.newLine();
				for (int bdctr = 0; bdctr < quest.getBlockDestroys().size(); bdctr++)
				{
					bufferWriter.append("  " + quest.getBlockDestroys().get(bdctr) + ":");
					bufferWriter.newLine();
					bufferWriter.append("  - " + quest.getBlockDestroyData().get(bdctr));
					bufferWriter.newLine();
					bufferWriter.append("  - 0");
					bufferWriter.newLine();
				}
			}


			if (quest.getKills() != null)
			{
				bufferWriter.append("kills:");
				bufferWriter.newLine();
				for (int kctr = 0; kctr < quest.getKills().size(); kctr++)
				{
					bufferWriter.append("  " + quest.getKills().get(kctr) + ": 0");
					bufferWriter.newLine();
				}
			}

		}
		catch (IOException ex)
		{
		}


	}

	public static void complete(Quester quester)
	{
		File file = file(quester);
		if (file.exists())
		{
			file.delete();
		}
	}

	public static void load(Quester quester)
	{
		File file = file(quester);
		YamlConfiguration config = new YamlConfiguration();
		if (!exists(quester))
		{
			quester.setLoaded(false);
			return;
		}
		try
		{
			config.load(file);
		}
		catch (FileNotFoundException ex)
		{
		}
		catch (IOException ex)
		{
		}
		catch (InvalidConfigurationException ex)
		{
		}
		quester.setQuest(config.getString("title"));

		if (config.contains("collectitems"))
		{
			List<Integer> collectitems = config.getList("collectitems");
			List<Integer> data2;
			ItemStack item2;
			for (int lctr = 0; lctr < collectitems.size(); lctr++)
			{
				data2 = config.getList("collectitems." + collectitems.get(lctr));
				item2 = new ItemStack(collectitems.get(lctr));
				item2.setData(new MaterialData(data2.get(0).byteValue()));
				quester.addCollectItem(item2, data2.get(1));
			}
		}


		if (config.contains("blocks"))
		{
			List<Integer> blocks = config.getList("blocks");
			List<Integer> data4;
			for (int lctr = 0; lctr < blocks.size(); lctr++)
			{
				data4 = config.getList("blocks." + blocks.get(lctr));
				quester.addBlocks(blocks.get(lctr), data4.get(0).byteValue(), data4.get(1));
			}
		}


		if (config.contains("blockdestroys"))
		{
			List<Integer> blocks2 = config.getList("blockdestroys");
			List<Integer> data5;
			for (int lctr = 0; lctr < blocks2.size(); lctr++)
			{
				data5 = config.getList("blockdestroys." + blocks2.get(lctr));
				quester.addBlockDestroys(blocks2.get(lctr), data5.get(0).byteValue(), data5.get(1));
			}
		}


		if (config.contains("kills"))
		{
			List<String> kills = config.getList("kills");
			for (int lctr = 0; lctr < kills.size(); lctr++)
			{
				quester.addKills(kills.get(lctr), config.getInt("kills." + kills.get(lctr)));
			}
		}

		quester.setLoaded(true);



	}

	public static void load(Quest quest)
	{
		File file = file(quest);
		YamlConfiguration config = new YamlConfiguration();
		if (!exists(quest))
		{
			quest.setLoaded(false);
			return;
		}
		try
		{
			config.load(file);
		}
		catch (FileNotFoundException ex)
		{
		}
		catch (IOException ex)
		{
		}
		catch (InvalidConfigurationException ex)
		{
		}
		quest.setTitle(config.getString("title"));


		List<String> desc = config.getList("description");
		for (int lctr = 0; lctr < desc.size(); lctr++)
		{
			quest.addDescription(desc.get(lctr));
		}


		List<String> obj = config.getList("objectives");
		for (int lctr = 0; lctr < obj.size(); lctr++)
		{
			quest.addObjectives(obj.get(lctr));
		}


		List<String> comp = config.getList("completion");
		for (int lctr = 0; lctr < comp.size(); lctr++)
		{
			quest.addCompletionMessage(comp.get(lctr));
		}


		String req = config.getString("requires");
		if (req != null)
		{
			if (!req.equals(""))
			{
				quest.setRequires(req);
			}
		}


		quest.setRepeatable(config.getBoolean("repeatable"));


		List<String> rew = config.getList("rewards");
		for (int lctr = 0; lctr < rew.size(); lctr++)
		{
			quest.addRewardDescription(rew.get(lctr));
		}


		quest.setRewardExp(config.getInt("exp"));


		quest.setRewardEyrir(config.getInt("eyrir"));


		quest.setHeroesExp(config.getInt("heroexp"));


		String rclass = config.getString("requiredclass");
		if (rclass != null)
		{
			if (!rclass.equals(""))
			{
				quest.setRequiredClass(rclass);
			}
		}


		if (config.contains("rewarditems"))
		{
			List<Integer> rewarditems = config.getList("rewarditems");
			List<Integer> data;
			ItemStack item;
			for (int lctr = 0; lctr < rewarditems.size(); lctr++)
			{
				data = config.getList("rewarditems." + rewarditems.get(lctr));
				item = new ItemStack(rewarditems.get(lctr));
				item.setDurability(data.get(1).shortValue());
				item.setAmount(data.get(0));
				item.setData(new MaterialData(data.get(2).byteValue()));
				quest.addRewardItem(item);
			}
		}


		if (config.contains("collectitems"))
		{
			List<Integer> collectitems = config.getList("collectitems");
			List<Integer> data2;
			ItemStack item2;
			for (int lctr = 0; lctr < collectitems.size(); lctr++)
			{
				data2 = config.getList("collectitems." + collectitems.get(lctr));
				item2 = new ItemStack(collectitems.get(lctr));
				item2.setAmount(data2.get(0));
				item2.setData(new MaterialData(data2.get(1).byteValue()));
				quest.addCollectItem(item2);
			}
		}


		if (config.contains("requireditems"))
		{
			List<Integer> requireditems = config.getList("requireditems");
			List<Integer> data3;
			ItemStack item3;
			for (int lctr = 0; lctr < requireditems.size(); lctr++)
			{
				data3 = config.getList("requireditems." + requireditems.get(lctr));
				item3 = new ItemStack(requireditems.get(lctr));
				item3.setDurability(data3.get(1).shortValue());
				item3.setAmount(data3.get(0));
				item3.setData(new MaterialData(data3.get(2).byteValue()));
				quest.addRequiredItem(item3);
			}
		}


		if (config.contains("blocks"))
		{
			List<Integer> blocks = config.getList("blocks");
			List<Integer> data4;
			for (int lctr = 0; lctr < blocks.size(); lctr++)
			{
				data4 = config.getList("blocks." + blocks.get(lctr));
				quest.addBlock(blocks.get(lctr), data4.get(1).byteValue(), data4.get(0));
			}
		}


		if (config.contains("blockdestroys"))
		{
			List<Integer> blocks2 = config.getList("blockdestroys");
			List<Integer> data5;
			for (int lctr = 0; lctr < blocks2.size(); lctr++)
			{
				data5 = config.getList("blockdestroys." + blocks2.get(lctr));
				quest.addBlockDestroy(blocks2.get(lctr), data5.get(1).byteValue(), data5.get(0));
			}
		}


		if (config.contains("kills"))
		{
			List<String> kills = config.getList("kills");
			for (int lctr = 0; lctr < kills.size(); lctr++)
			{
				quest.addKills(kills.get(lctr), config.getInt("kills." + kills.get(lctr)));
			}
		}


		List<String> completers = config.getList("completers");
		if (completers.size() > 0)
		{
			for (int lctr = 0; lctr < completers.size(); lctr++)
			{
				quest.addCompleter(completers.get(lctr));
			}
		}


		List<String> questers = config.getList("questers");
		if (questers.size() > 0)
		{
			for (int lctr = 0; lctr < questers.size(); lctr++)
			{
				quest.addQuester(questers.get(lctr));
			}
		}


		quest.setLoaded(true);
		return;
	}
}
