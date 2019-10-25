package de.karasuma.discordbot.lol.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Summoner {
    String accountId;
    String name;
}
