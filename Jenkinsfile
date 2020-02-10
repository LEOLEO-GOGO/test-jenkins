pipeline {
  agent any
  stages {
    stage('Prepare') {
      environment {
        TMP_PATH="$JENKINS_HOME/BUILD_TMP/$JOB_NAME/$GIT_BRANCH/gproc-asset-app/"
      }
      steps {
        sh "echo $GIT_BRANCH"
        sh "echo $WORKSPACE"
        sh "pwd"
        sh "ls -la"
        sh "rm -rf $TMP_PATH"
        sh "mkdir -p $TMP_PATH"
        sh "cp -R . $TMP_PATH"
        sh "ls -la $TMP_PATH"

        dir("$TMP_PATH") {
          sh "echo $GIT_BRANCH"
          sh "pwd"
          sh "ls -la"
        }
      }
    }

  }
}
