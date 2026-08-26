# PlayerProfiles

A modern Minecraft plugin for viewing player profiles with detailed statistics, inventory, and more. This fork provides full support for **Paper 1.21.11+** servers.

## Features

- **Player Profiles** - View detailed player information, inventory, armor, ender chest, and statistics
- **Custom GUIs** - Create and manage custom GUI profiles with flexible configuration
- **WorldGuard Integration** - Region-based profile access control (disabled regions)
- **Combat Integration** - CombatLogX and DeluxeCombat support (disable profiles in combat)
- **PlaceholderAPI Support** - Full placeholder support for dynamic content
- **Profile Locking** - Players can lock/unlock their profiles
- **Cooldown System** - Configurable cooldowns for profile viewing
- **Distance Checks** - Auto-close profiles when players move too far apart
- **Multi-world Support** - Disable profiles in specific worlds
- **Auto-refresh** - Automatic placeholder updates in GUI items
- **Metrics** - bStats integration for plugin statistics

## Requirements

- **Paper 1.21.11+** (or compatible forks)
- **Java 21**
- **WorldGuard 7.x** (for region features)
- **WorldEdit 7.x** (required by WorldGuard)
- Optional: **PlaceholderAPI**, **CombatLogX**, **DeluxeCombat**

## Installation

1. Download the latest `PlayerProfiles-8.0.5.jar` from releases
2. Place in your server's `plugins/` folder
3. Restart the server
4. Configure `plugins/PlayerProfiles/config.yml` to your needs
5. Run `/playerprofiles reload` to apply changes

## Configuration

Main configuration files in `plugins/PlayerProfiles/`:

| File | Description |
|------|-------------|
| `config.yml` | Main settings (cooldowns, combat, worlds, regions, sounds) |
| `gui.yml` | Default profile GUI layout and items |
| `gui-creator.yml` | Custom GUI creator settings |
| `data.yml` | Player profile data storage |
| `custom-gui/punish-gui.yml` | Example custom GUI |

### Key Config Options

```yaml
options:
  disableNPC: true                    # Disable NPC profile viewing
  disableInCombat:
    enabled: true                     # Block profiles in combat
    message: "{prefix} &cYou are not allowed to open profile while in combat!"
  shiftClick: true                    # Shift+right-click to open profile

autoRefresh:
  enabled: true                       # Auto-update placeholders
  refreshEvery: 20                    # Update interval (ticks)

cooldown:
  enabled: true
  duration: 3                         # Seconds
  message: "{prefix} &cPlease wait for another {time} second(s)!"

disabledWorlds:
  message: "{prefix} &cYou are not allowed to open profile in this world!"
  worlds:
    - 'pvpWorld'

disabledRegions:
  playerInDisabledRegionMessage: "{prefix} &cYou are not allowed to open profile in this region!"
  targetInDisabledRegionMessage: "{prefix} &cThe target is in disabled region area!"
  regions:
    - 'disabledRegions'
    - 'pvp'

distanceCheck:
  enabled: true
  distance: 30                        # Blocks
  tooFarMessage: "{prefix} &e{player} &cis too far from you!"
```

## Commands

| Command | Aliases | Permission | Description |
|---------|---------|------------|-------------|
| `/playerprofiles` | `/pp`, `/playerprofile`, `/playerp` | `playerprofiles.admin` | Main command |
| `/playerprofiles reload` | | `playerprofiles.admin` | Reload configuration |
| `/playerprofiles opengui <player> <target> <gui>` | | `playerprofiles.admin` | Open custom GUI for player |
| `/playerprofiles listgui` | | `playerprofiles.admin` | List available custom GUIs |
| `/profile [player]` | `/p`, `/viewprofile` | `playerprofiles.profile` | View profile |
| `/lockprofile [player]` | `/lock`, `/profilelock` | `playerprofiles.lock` | Lock profile |
| `/unlockprofile [player]` | `/unlock`, `/profileunlock` | `playerprofiles.unlock` | Unlock profile |
| `/toggleprofile` | | `playerprofiles.toggle` | Toggle profile viewing |

## Permissions

```
playerprofiles.*                           # All permissions
playerprofiles.admin                       # Admin commands (reload, opengui, listgui)
playerprofiles.profile                     # View profiles (/profile)
playerprofiles.profile.others              # View other players' profiles
playerprofiles.lock                        # Lock own profile
playerprofiles.lock.others                 # Lock others' profiles
playerprofiles.unlock                      # Unlock own profile
playerprofiles.unlock.others               # Unlock others' profiles
playerprofiles.toggle                      # Toggle profile viewing
playerprofiles.bypass.cooldown             # Bypass profile cooldown
playerprofiles.bypass.distance             # Bypass distance check
playerprofiles.bypass.combat               # Bypass combat restriction
playerprofiles.bypass.disabledworld        # Bypass disabled worlds
playerprofiles.bypass.disabledregion       # Bypass disabled regions
```

## Custom GUI Creation

Create custom GUIs in `gui-creator.yml` or use the in-game creator:

1. Run `/playerprofiles opengui <player> <target> <gui-name>` to open a custom GUI
2. Use `/playerprofiles listgui` to see available GUIs
3. Configure custom GUIs in `custom-gui/` folder

## Placeholders

With PlaceholderAPI installed, these placeholders are available:

- `%playerprofiles_<stat>%` - Player statistics
- `%playerprofiles_kills%` - Player kills (if supported)
- `%playerprofiles_deaths%` - Player deaths (if supported)
- Any PAPI placeholder can be used in GUI items and messages

## Building from Source

```bash
# Requires Java 21 and Maven 3.8+
git clone https://github.com/evnrca/PlayerProfiles26.git
cd PlayerProfiles26
mvn clean package -DskipTests
# Output: target/PlayerProfiles-8.0.5.jar
```

## Module Structure

```
PlayerProfiles/
├── api/                 # Core API interfaces
├── core/                # Main plugin logic
├── worldguard-wrapper/  # WorldGuard version abstraction
├── worldguard7/         # WorldGuard 7.x implementation
└── dist/                # Final shaded JAR assembly
```

## Changes in This Fork (v8.0.5)

- **Java 21** baseline
- **Paper 1.21.11** support
- **WorldGuard 7.0.10** / **WorldEdit 7.3.12**
- Removed legacy WorldGuard 6.x module
- Simplified WorldGuard wrapper (single implementation)
- Fixed DependencyManager brace issues
- Updated plugin.yml api-version to 1.21
- Modernized RegionFinder for WG7 API

## License

You can do whatever you want with the source code, just don't redistribute it.

## Support

For support, join the Discord and contact **mdaffa**.

---

**Original Author**: aglerr, Starfruit2210  
**Fork Maintainer**: evnrca