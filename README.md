[maven-central]: https://img.shields.io/maven-central/v/net.dv8tion/JDA?color=blue
[blah][][bleh]
![blebbers][][blubbers]

# ScheduledTasks <img align="right" src="https://user-images.githubusercontent.com/70197204/156934977-17c88e14-148c-4191-8237-7d4f19ea99a1.png" height="200" width="200"> 
There's not a single plugin that handles scheduling as easily as this one does.
I made this for my own server, and have never looked back! This plugin is purely
config based. 

- `No commands`
- `No permissions`
- `Time zone setting`
- `PlaceholderAPI support`
- `Easy config`
- `Compatible versions`

[test]

### Configuration
```yaml
# # # # # # # # # # # # # #
# Scheduled ConfigTask Example  #
# # # # # # # # # # # # # #

TimeZone: "America/Chicago"

ScheduledTasks:
  reboot:
    times:
      - "05:10 PM DAILY"
      - "03:10 AM SATURDAY"
    prebroadcasttimes:
      - 3600
      - 1800
      - 900
      - 300
      - 180
      - 120
      - 60
      - 30
      - 5
      - 4
      - 3
      - 2
      - 1
    prebroadcastmessage: "&cReboot &8> &7The reboot is occurring in &6%time%"
    execute:
      - "restart"
```

