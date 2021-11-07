package chat.server;

import java.time.Duration;

public class Constants {
  public static final Duration LOGIN_TIMEOUT = Duration.ofSeconds(10);
  public static final Duration KEEP_ALIVE_TIMEOUT = Duration.ofSeconds(30);
}
