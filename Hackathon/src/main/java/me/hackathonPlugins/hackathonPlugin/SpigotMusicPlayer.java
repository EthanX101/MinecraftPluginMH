
// EVERYTHING EXCEPT THE NOTES WAS AI FROM CLAUDE
package me.hackathonPlugins.hackathonPlugin;

import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class SpigotMusicPlayer {

    // Singleton instance
    private static SpigotMusicPlayer instance;

    // Array of predefined songs (maximum 10 songs)
    private Song[] songs;
    private int songCount;
    private static final int MAX_SONGS = 10;

    public void onEnable() {
        instance = this;

        // Initialize songs array
        songs = new Song[MAX_SONGS];
        songCount = 0;

        // Register default songs
        registerDefaultSongs();

        System.out.println("SpigotMusicPlayer enabled!");
    }

    public static SpigotMusicPlayer getInstance() {
        return instance;
    }

    /**
     * Register some default songs
     */
    private void registerDefaultSongs() {
        // Example: Adding "Twinkle Twinkle Little Star"
        Song twinkleStar = new Song("twinklestar", 8);

        // First part: "Twinkle twinkle little star"
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.C), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.C), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.G), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.G), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.A), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.A), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.G), 50));

        // Second part: "How I wonder what you are"
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.F), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.F), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.E), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.E), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.D), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.D), 4));
        twinkleStar.addNote(new SongNote(Note.natural(1, Note.Tone.C), 8));

        // Register the song
        addSong(twinkleStar);

        // Add the Malevolent Shrine theme from Jujutsu Kaisen
        Song malevolentShrine = new Song("malevolentshrine", 8); // Faster pace for intensity

        // First part - ominous intro
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 6));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 6));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 6));
        malevolentShrine.addNote(new SongNote(Note.natural(1, Note.Tone.F), 6));

        // Second part - building tension
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 4));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 4));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.A), 4));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 4));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 4));

        // Third part - domain expansion
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 6));

        // Fourth part - dark motif
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.A), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.G), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 6));

        // Fifth part - Sukuna's theme
        malevolentShrine.addNote(new SongNote(Note.flat(0, Note.Tone.B), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.G), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.A), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 6));

        // Sixth part - final ominous closing
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.B), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.A), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.G), 3));
        malevolentShrine.addNote(new SongNote(Note.flat(1, Note.Tone.E), 8));

        // Register the song
        addSong(malevolentShrine);
        // You can add more default songs here
    }

    /**
     * Add a song to the song collection
     *
     * @param song The song to add
     * @return true if added successfully, false if array is full
     */
    public boolean addSong(Song song) {
        if (songCount >= MAX_SONGS) {
            return false;
        }

        songs[songCount++] = song;
        return true;
    }

    /**
     * Find a song by name
     *
     * @param name The name of the song to find
     * @return The song if found, null otherwise
     */
    public Song findSong(String name) {
        String lowerName = name.toLowerCase();
        for (int i = 0; i < songCount; i++) {
            if (songs[i].getName().toLowerCase().equals(lowerName)) {
                return songs[i];
            }
        }
        return null;
    }

    /**
     * Play a song for a specific player
     *
     * @param player The player who will hear the song
     * @param songName The name of the song to play
     * @return true if song exists and started playing, false otherwise
     */
    public boolean playSong(Player player, String songName) {
        Song song = findSong(songName);
        if (song == null) {
            return false;
        }

        playSong(player, song);
        return true;
    }

    /**
     * Play a song for a specific player
     *
     * @param player The player who will hear the song
     * @param song The song to play
     */
    public void playSong(Player player, Song song) {
        new BukkitRunnable() {
            int index = 0;

            @Override
            public void run() {
                if (index >= song.getNoteCount() || !player.isOnline()) {
                    this.cancel();
                    return;
                }
                SongNote note = song.getNote(index);
                index++;
                new BukkitRunnable() {
                    public void run() {
                        player.playNote(player.getLocation(), org.bukkit.Instrument.PIANO, note.getNote());
                    }
                }.runTaskLater(HackathonPlugin.getPlug(),note.getLength());
            }
        }.runTaskTimer(HackathonPlugin.getPlug(), 0, song.getTicksBetweenNotes());
    }

    /**
     * Play a song for all players within a radius
     *
     * @param location The center location
     * @param songName The name of the song to play
     * @param radius The radius within which players will hear the song
     * @return true if song exists and started playing, false otherwise
     */
    public boolean playSongInRadius(Location location, String songName, double radius) {
        Song song = findSong(songName);
        if (song == null) {
            return false;
        }

        Player[] nearbyPlayers = location.getWorld().getPlayers().toArray(new Player[0]);
        for (Player player : nearbyPlayers) {
            if (player.getLocation().distance(location) <= radius) {
                playSong(player, song);
            }
        }

        return true;
    }

    /**
     * Class representing a musical note in a song
     */
    public static class SongNote {
        private Note note;
        private int length;

        public SongNote(Note note, int length) {
            this.note = note;
            this.length = length;
        }

        public Note getNote() {
            return note;
        }

        public int getLength() {
            return length;
        }
    }

    /**
     * Class representing a song with a sequence of notes
     */
    public static class Song {
        private String name;
        private SongNote[] notes;
        private int noteCount;
        private int ticksBetweenNotes;
        private static final int MAX_NOTES = 200;

        public Song(String name, int ticksBetweenNotes) {
            this.name = name;
            this.notes = new SongNote[MAX_NOTES];
            this.noteCount = 0;
            this.ticksBetweenNotes = ticksBetweenNotes;
        }

        public boolean addNote(SongNote note) {
            if (noteCount >= MAX_NOTES) {
                return false;
            }

            notes[noteCount++] = note;
            return true;
        }

        public String getName() {
            return name;
        }

        public int getNoteCount() {
            return noteCount;
        }

        public SongNote getNote(int index) {
            if (index < 0 || index >= noteCount) {
                return null;
            }
            return notes[index];
        }

        public int getTicksBetweenNotes() {
            return ticksBetweenNotes;
        }
    }
}
