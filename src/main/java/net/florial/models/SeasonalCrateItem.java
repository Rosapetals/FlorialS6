package net.florial.models;

import io.github.bananapuncher714.nbteditor.NBTEditor;
import lombok.Getter;
import net.florial.utils.general.CustomItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum SeasonalCrateItem {

    ITEM1(1, Material.ORANGE_TULIP, "#ff7a8b&lPUMPKIN [Left-Click]", 3),
    ITEM2(2, Material.PAPER, "#ff7a8b&lS O A P [Left-Click]", 5),
    ITEM3(3, Material.PAPER, "#ff7a8b&lPina Colada [Left-Click]", 7),
    ITEM4(4, Material.PAPER, "#ff7a8b&lApple Cider [Left-Click]", 12),
    ITEM5(5, Material.PAPER, "#ff7a8b&lJuicy Grapes [Left-Click]", 15),
    ITEM6(6, Material.PAPER, "#ff7a8b&lSinner's Pineapple Pizza [Left-Click]", 16),
    ITEM7(7, Material.PAPER, "#ff7a8b&lCheese [Left-Click] [DNA]", 18),
    ITEM8(8, Material.PAPER, "#ff7a8b&lCaramelized Apple [Left-Click]", 19),
    ITEM9(9, Material.PAPER, "#ff7a8b&lCranberries [Left-Click]", 20),
    ITEM10(10, Material.PAPER, "#ff7a8b&lCORN ON THE COB [Left-Click]", 21),
    ITEM11(11, Material.SUNFLOWER, "#ff7a8b&lBETTER CORN ON THE COB [Left-Click]", 21),
    ITEM12(12, Material.PAPER, "#ff7a8b&lChocolate Ice-Cream [Left-Click]", 82),
    ITEM13(13, Material.CAKE, "#ff7a8b&lCarrot Cake [Left-Click]", 83),
    ITEM14(14, Material.PAPER, "#ff7a8b&lMAPLE LEAF [Left-Click]", 400);


    @Getter
    private final int id;
    @Getter
    private final Material material;
    @Getter
    private final String name;
    @Getter
    private final int customModelData;



    SeasonalCrateItem(int id, Material material, String name, int customModelData) {
        this.id = id;
        this.material = material;
        this.name = name;
        this.customModelData = customModelData;

    }

    public static ItemStack fromID(int id, int amount) {
        for (SeasonalCrateItem e : values())
            if (e.id == id) {
                return NBTEditor.set(CustomItem.MakeItem(new ItemStack(e.material, amount), e.name, "", false), e.customModelData,"CustomModelData");
            }

        return null;
    }
}
