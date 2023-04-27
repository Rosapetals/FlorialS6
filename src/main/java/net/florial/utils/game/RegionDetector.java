package net.florial.utils.game;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import org.bukkit.Location;

public class RegionDetector {

    public static String detect(Location location) {
        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery query = container.createQuery();
        ApplicableRegionSet set = query.getApplicableRegions(BukkitAdapter.adapt(location));

        StringBuilder regionsIn = new StringBuilder();
        for (ProtectedRegion rg : set) {
            regionsIn.append(rg.getId().toLowerCase());
        }

        if (regionsIn.toString().equals("")) {
            return "none";
        } else {
            return regionsIn.toString();
        }
    }
}
