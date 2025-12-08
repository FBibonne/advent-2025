package ci;

import ci.dependencies.Config;
import ci.dependencies.Emailer;
import ci.dependencies.Logger;
import ci.dependencies.Project;

import static ci.Pipeline.ProcessedProject.from;

public class Pipeline {
    private static final String SUCCEED = "success";
    private final Config config;
    private final Emailer emailer;
    private final Logger log;

    public Pipeline(Config config, Emailer emailer, Logger log) {
        this.config = config;
        this.emailer = emailer;
        this.log = log;
    }

    public void run(Project project) {
        ProcessedProject processedProject = from(project)
                .tryRunTests()
                .logTestResult(log)
                .tryDeploy()
                .logDeployResult(log);
        if (config.sendEmailSummary()) {
            log.info("Sending email");
            processedProject.sendEmailSummary(emailer);
        } else {
            log.info("Email disabled");
        }
    }

    record ProcessedProject(Project project, CurrentState currentState){

        static ProcessedProject from(Project project){
            return new ProcessedProject(project);
        }

        private ProcessedProject(Project project) {
            this(project, CurrentState.INITIAL);
        }

        public boolean hasTests() {
            return this.project.hasTests();
        }

        public ProcessedProject tryRunTests() {
            if (hasTests()) {
                return runTests();
            }
            return withCurrentState(CurrentState.NO_TESTS);

        }

        private ProcessedProject runTests() {
            String testsResult = project.runTests();
            return withCurrentState(isSucceed(testsResult) ? CurrentState.TESTS_PASSED : CurrentState.TESTS_FAILED);
        }

        private ProcessedProject withCurrentState(CurrentState currentState) {
            return new ProcessedProject(this.project, currentState);
        }

        public ProcessedProject logTestResult(Logger log) {
            switch (this.currentState) {
                case TESTS_PASSED ->  log.info("Tests passed");
                case TESTS_FAILED -> log.error("Tests failed");
                case NO_TESTS -> log.info("No tests");
                default -> throw  new IllegalStateException("Unexpected value: " + this.currentState);
            }
            return this;
        }

        public ProcessedProject tryDeploy() {
            return switch (this.currentState) {
                case TESTS_PASSED, NO_TESTS -> this.deploy();
                case TESTS_FAILED -> withCurrentState(CurrentState.NO_DEPLOYMENT);
                default -> throw new IllegalStateException("Unexpected value: " + this.currentState);
            };
        }

        private ProcessedProject deploy() {
            String deployResult = project.deploy();
            return withCurrentState(SUCCEED.equals(deployResult) ? CurrentState.DEPLOYMENT_SUCCESS : CurrentState.DEPLOYMENT_FAILURE);
        }

        public ProcessedProject logDeployResult(Logger log) {
            switch (currentState) {
                case DEPLOYMENT_SUCCESS -> log.info("Deployment successful");
                case DEPLOYMENT_FAILURE -> log.error("Deployment failed");
                case NO_DEPLOYMENT -> {/*nothing to do*/}
                default -> throw new IllegalStateException("Unexpected value: " + this.currentState);
            }
            return this;
        }

        public ProcessedProject sendEmailSummary(Emailer emailer) {
            switch (this.currentState) {
                case DEPLOYMENT_SUCCESS -> emailer.send("Deployment completed successfully");
                case DEPLOYMENT_FAILURE -> emailer.send("Deployment failed");
                case NO_DEPLOYMENT -> emailer.send("Tests failed");
                default -> throw new IllegalStateException("Unexpected value: " + this.currentState);
            }
            return this;
        }
    }

    private static boolean isSucceed(String testsResult) {
        return SUCCEED.equals(testsResult);
    }

    private enum CurrentState {
        INITIAL,
        NO_TESTS,
        TESTS_PASSED,
        TESTS_FAILED,
        ///
        /// Tests OK and deployment OK
        ///
        DEPLOYMENT_SUCCESS,
        ///
        /// Tests OK and deployment KO
        ///
        DEPLOYMENT_FAILURE,
        ///
        /// TEST_FAILED => no deployment
        ///
        NO_DEPLOYMENT;
    }
}
