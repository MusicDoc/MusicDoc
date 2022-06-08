package io.github.musicdoc.config;

public class MenuConfig extends Config {

  private BatteryConfig battery;

  public BatteryConfig getBattery() {

    if (this.battery == null) {
      this.battery = new BatteryConfig();
    }
    return this.battery;
  }
}
