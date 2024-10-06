package javaRush.module2.model;

import javaRush.module2.service.*;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.concurrent.*;

import static javaRush.module2.service.CreatureSettings.LINE;

@Slf4j
public class Island {

    private final int x_size;
    private final int y_size;
    private ConcurrentMap<Point, Cell> mapIsland;

    public Island(int x_size, int y_size) {
        this.x_size = x_size;
        this.y_size = y_size;

        mapIsland = new ConcurrentHashMap<>();

        initIsland();
    }

    /**
     * Process of initialisation of island.
     *
     */
    private void initIsland() {
        for (int x = 0; x < x_size; x++) {
            for (int y = 0; y < y_size; y++) {
                // Make instance of Cell.class
                Point point = new Point(x, y);
                Cell newCell = new Cell(point);
                // fill island map
                mapIsland.put(point, newCell);
            }
        }
    }

    /**
     * Running of Scheduled threads: report, eating, moving, reproducing
     *
     */
    public void lifeIsStarting() {
            log.info("Starting ScheduledExecutorService tasks...");
            ScheduledExecutorService service = new ScheduledThreadPoolExecutor(4);
            service.scheduleAtFixedRate(new Report(mapIsland, x_size, y_size), 0, 2, TimeUnit.SECONDS);
            service.scheduleAtFixedRate(new EatProcess(mapIsland), 1, 1, TimeUnit.SECONDS);
            service.scheduleAtFixedRate(new MovingProcess(mapIsland, x_size, y_size), 1, 1, TimeUnit.SECONDS);
            service.scheduleAtFixedRate(new ReproduceProcess(mapIsland, x_size, y_size), 1, 1, TimeUnit.SECONDS);

            // make one more thread for interrupting of application
            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("--------------------------------  Press ENTER to stop application... --------------------------------");
                System.out.println(LINE);

                // Waiting for user
                scanner.nextLine();

                // Stop application
                service.shutdownNow();
                System.out.println("---------------  GAME OVER... ------------- " );
                System.exit(0);
                log.info("Interrupt application by pressing Enter");
            }).start();
    }
}
