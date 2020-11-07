package io.inprice.common.info;

import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TimePeriod {

  private int interval;
  private TimeUnit timeUnit;

  public TimePeriod(int interval, TimeUnit timeUnit) {
    this.interval = interval;
    this.timeUnit = timeUnit;
  }

}
