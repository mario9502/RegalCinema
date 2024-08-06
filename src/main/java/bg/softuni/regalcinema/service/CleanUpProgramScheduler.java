package bg.softuni.regalcinema.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanUpProgramScheduler {

    private final Logger LOGGER = LoggerFactory.getLogger(CleanUpProgramScheduler.class);
    private final ProgramService programService;

    public CleanUpProgramScheduler(ProgramService programService) {
        this.programService = programService;
    }

    @Scheduled(cron = "0 2 0 * * *")
    public void cleanUp(){
        LOGGER.info("Start cleaning outdated programs");
        this.programService.cleanOutdatedProgram();
    }
}
