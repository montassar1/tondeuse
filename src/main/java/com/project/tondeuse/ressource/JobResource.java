package com.project.tondeuse.ressource;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class JobResource {

    @Autowired
    private ConfigurableApplicationContext ctx;

    @PostMapping("/startTondeuse")
    public ResponseEntity<String> tondeuseResource() {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
        Job job = ctx.getBean("tondeuseJob", Job.class);
        try {
            JobExecution jobExecution = jobLauncher.run(job, jobParameters);
            BatchStatus batchStatus = jobExecution.getStatus();
            return ResponseEntity.ok("Job status: " + batchStatus);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to start job: " + e.getMessage());
        }
    }

}
