package ro.menast.libary.threaded;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AsyncThreadScheduler {

  public static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(20000);
  private final Runnable runnable;
  
  public AsyncThreadScheduler(Runnable runnable) {
    this.runnable = runnable;
  }
  
  public void runAsync() {
    scheduler.schedule(this.runnable, 1L, TimeUnit.MILLISECONDS);
    Thread thread = new Thread(this.runnable);
  }
  
  public void runAsyncTaskLater(long seconds) {
    scheduler.schedule(this.runnable, seconds, TimeUnit.SECONDS);
  }
  
  public ScheduledFuture<?> scheduleAsyncTask(long initDelay, long milli) {
    return scheduler.scheduleAtFixedRate(this.runnable, initDelay, milli, TimeUnit.MILLISECONDS);
  }
}