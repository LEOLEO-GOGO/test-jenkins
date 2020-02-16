pipeline {
  agent any
  environment {
    BUILD_TEMP_WORK_PATH="$JENKINS_HOME/BUILD_TEMP/$GIT_BRANCH/test-jenkins"
    BUILD_TEMP_WORK_PATH2="$JENKINS_HOME/BUILD_TEMP/$GIT_BRANCH/testjenkins2"
    TEST_RESULT_FILES="reports/**"
  }

  stages {
    stage('Pull Dependency') {
      steps {
        echo "changesets: ${currentBuild.changeSets}"
        // if () {
        //   git branch: 'testJenkins',
        //     credentialsId: 'test-jenkins-github',
        //     url: 'https://github.com/LEOLEO-GOGO/test-jenkins.git'
        // }

        sh "echo isFromUpStream: ${params.triggeredByUpstream}"
        sh "echo $GIT_BRANCH"
        sh "echo $WORKSPACE"
        sh "pwd"
        sh "ls -la"
        sh "rm -rf $BUILD_TEMP_WORK_PATH"
        sh "mkdir -p $BUILD_TEMP_WORK_PATH"
        sh "cp -R . $BUILD_TEMP_WORK_PATH"
        sh "ls -la $BUILD_TEMP_WORK_PATH"

        dir("$BUILD_TEMP_WORK_PATH") {
          sh "echo $GIT_BRANCH"
          sh "pwd"
          sh "ls -la"
        }
      }
    }

  stages {
    stage('Prepare') {
      steps {
        sh "echo isFromUpStream: ${params.triggeredByUpstream}"
        sh "echo $GIT_BRANCH"
        sh "echo $WORKSPACE"
        sh "pwd"
        sh "ls -la"
        sh "rm -rf $BUILD_TEMP_WORK_PATH"
        sh "mkdir -p $BUILD_TEMP_WORK_PATH"
        sh "cp -R . $BUILD_TEMP_WORK_PATH"
        sh "ls -la $BUILD_TEMP_WORK_PATH"

        dir("$BUILD_TEMP_WORK_PATH") {
          sh "echo $GIT_BRANCH"
          sh "pwd"
          sh "ls -la"
        }
      }
    }

    stage('Build') {
      steps {
        withAnt(installation: 'gproc-ant') {
          dir("$BUILD_TEMP_WORK_PATH/testproject") {
            sh "ant jar"
          }
        }
      }
    }

    stage('Test') {
      steps {
        withAnt(installation: 'gproc-ant') {
          dir("$BUILD_TEMP_WORK_PATH/testproject-test") {
            sh "ant -buildfile build_test.xml report"
            archiveArtifacts "$TEST_RESULT_FILES"
            junit "reports/raw/TEST-*.xml"
            jacoco (execPattern: "reports/raw/*.exec")
          }
        }

        emailext attachLog: true,
                 subject: "Need Approval: $JOB_NAME - Build # $BUILD_NUMBER!",
                 body: "Integration is waiting for your approval: ${env.BUILD_URL}",
                 to: 'louzj@cn.ibm.com'
      }
    }

    stage('Integration') {
      input {
        message "Should we continue?"
        ok "Yes, we should."
        submitter "admin"
        parameters {
            string(name: 'PERSON', defaultValue: 'Mr Anthony', description: 'Who should I say hello to?')
        }
      }
      steps {
        echo "do Integration!"
      }
    }
  }

  post {
    always {
        echo "pipeline finished!"
        sh "echo isFromUpStream: ${params.triggeredByUpstream}"
    }
    success {
        echo "build succeed! clean up build folder."
        sh "rm -rf $BUILD_TEMP_WORK_PATH"
    }
    failure {
      emailext attachLog: true,
               subject: "Build Failed: $JOB_NAME - Build # $BUILD_NUMBER!",
               body: "Something is wrong with: ${env.BUILD_URL}",
               to: 'louzj@cn.ibm.com'
    }
  }
}
