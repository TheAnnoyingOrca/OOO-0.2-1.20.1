package net.orca.oceanoverhaul.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.orca.oceanoverhaul.entity.OceanicEntities;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public abstract class OceanicAbstractFish extends AbstractFish {
    @Nullable
    public OceanicAbstractFish leader;
    public List<OceanicAbstractFish> ownSchool = new ArrayList<>();

    public OceanicAbstractFish(EntityType<? extends AbstractFish> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return this.getMaxSchoolSize();
    }

    public abstract int getMaxSchoolSize();

    public boolean isFollower() {
        return this.leader != null && this.leader.isAlive();
    }

    public void startFollowing(OceanicAbstractFish schoolingFish) {
        this.leader = schoolingFish;
        schoolingFish.addToOwnSchoolFollower(this);
    }

    public void stopFollowing() {
        if (this.leader != null) {
            this.leader.removeFollowerFromOwnSchool(this);
            this.leader = null;
        }
    }

    private void addToOwnSchoolFollower(OceanicAbstractFish entity) {
        this.ownSchool.add(entity);
    }

    private void removeFollowerFromOwnSchool(OceanicAbstractFish entity) {
        this.ownSchool.remove(entity);
    }

    public boolean canBeFollowed() {
        return this.hasFollowers() && this.ownSchool.size() < this.getMaxSchoolSize();
    }

    @Override
    public void tick() {
        super.tick();
    }

    public boolean hasFollowers() {
        return this.ownSchool.size() > 1;
    }

    public void addFollowers(Stream<? extends OceanicAbstractFish> stream) {
        stream.limit(this.getMaxSchoolSize() - this.ownSchool.size()).filter(boidFish -> boidFish != this).forEach(boidFish -> boidFish.startFollowing(this));
    }

    public boolean inRangeOfLeader() {
        return this.distanceToSqr(this.leader) <= 121.0;
    }

    public void pathToLeader() {
        if (this.isFollower()) {
            this.getNavigation().moveTo(this.leader, 1.0);
        }
    }

    @Override
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverLevelAccessor, DifficultyInstance difficultyInstance, MobSpawnType mobSpawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag compoundTag) {
        super.finalizeSpawn(serverLevelAccessor, difficultyInstance, mobSpawnType, spawnGroupData, compoundTag);
        if (spawnGroupData == null) {
            spawnGroupData = new OceanicAbstractFish.SchoolSpawnGroupData(this);
        } else {
            this.startFollowing(((OceanicAbstractFish .SchoolSpawnGroupData)spawnGroupData).leader);
        }
        return spawnGroupData;
    }

    public static class SchoolSpawnGroupData
            implements SpawnGroupData {
        public final OceanicAbstractFish leader;

        public SchoolSpawnGroupData(OceanicAbstractFish schoolingFish) {
            this.leader = schoolingFish;
        }
    }
}