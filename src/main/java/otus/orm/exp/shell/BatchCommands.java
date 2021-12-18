package otus.orm.exp.shell;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BatchCommands {

    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer;
    private final Job migrationJob;

    public BatchCommands(JobLauncher jobLauncher, JobExplorer jobExplorer, Job migrationJob) {
        this.jobLauncher = jobLauncher;
        this.jobExplorer = jobExplorer;
        this.migrationJob = migrationJob;
    }

    @ShellMethod(value = "Start migrate job", key = {"mj"})
    public void startMigrationJob() throws Exception {
        JobExecution execution = jobLauncher.run(migrationJob, new JobParameters());
        System.out.println(execution);
    }

    @ShellMethod(value = "showInfo", key = "i")
    public void showInfo() {
        System.out.println(jobExplorer.getJobNames());
        System.out.println(jobExplorer.getLastJobInstance("migrationJob"));
    }
}
