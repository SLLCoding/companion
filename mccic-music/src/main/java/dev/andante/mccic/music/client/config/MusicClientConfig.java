package dev.andante.mccic.music.client.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.andante.mccic.config.ConfigCodecBuilder;
import dev.andante.mccic.config.ConfigHolder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public record MusicClientConfig(float gameMusicVolume, float gameMusicVolumeAfterDeath, float sfxVolume, HITWSoundOnOtherDeath hitwSoundOnOtherDeath, boolean stopMusicOnDeath, boolean stopMusicOnChickenHit) {
    public static final Codec<MusicClientConfig> CODEC = RecordCodecBuilder.create(
        instance -> {
            ConfigCodecBuilder<MusicClientConfig> builder = new ConfigCodecBuilder<>(MusicClientConfig.createDefaultConfig());
            return instance.group(
                builder.createFloat("game_music_volume", MusicClientConfig::gameMusicVolume),
                builder.createFloat("game_music_volume_after_death", MusicClientConfig::gameMusicVolumeAfterDeath),
                builder.createFloat("sfx_volume", MusicClientConfig::sfxVolume),
                builder.createEnum("hitw_sound_on_other_death", HITWSoundOnOtherDeath::values, MusicClientConfig::hitwSoundOnOtherDeath),
                builder.createBool("stop_music_on_death", MusicClientConfig::stopMusicOnDeath),
                builder.createBool("stop_music_on_chicken_hit", MusicClientConfig::stopMusicOnChickenHit)
            ).apply(instance, MusicClientConfig::new);
        }
    );

    public static final ConfigHolder<MusicClientConfig> CONFIG_HOLDER = new ConfigHolder<>("music", CODEC, createDefaultConfig());

    public static MusicClientConfig getConfig() {
        return CONFIG_HOLDER.get();
    }

    public static MusicClientConfig createDefaultConfig() {
        return new MusicClientConfig(0.5F, 0.25F, 1.0F, HITWSoundOnOtherDeath.OFF, true, false);
    }
}
