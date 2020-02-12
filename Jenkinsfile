pipeline {
  agent any
  environment {
    BUILD_WORK_PATH="$JENKINS_HOME/BUILD_TMP/$GIT_BRANCH/$BUILD_NUMBER/test-jenkins"
    TEST_RESULT_FILES="reports/**"
  }
  stages {
    stage('Prepare') {
      steps {
        sh "echo $GIT_BRANCH"
        sh "echo $WORKSPACE"
        sh "pwd"
        sh "ls -la"
        sh "rm -rf $BUILD_WORK_PATH"
        sh "mkdir -p $BUILD_WORK_PATH"
        sh "cp -R . $BUILD_WORK_PATH"
        sh "ls -la $BUILD_WORK_PATH"

        dir("$BUILD_WORK_PATH") {
          sh "echo $GIT_BRANCH"
          sh "pwd"
          sh "ls -la"
        }
      }
    }

    stage('Build') {
      steps {
        withAnt(installation: 'gproc-ant') {
          dir("$BUILD_WORK_PATH/testproject") {
            sh "ant jar"
          }
        }
      }
    }

    stage('Test') {
      steps {
        withAnt(installation: 'gproc-ant') {
          dir("$BUILD_WORK_PATH/testproject-test") {
            sh "ant -buildfile build_test.xml report"
            archiveArtifacts "$TEST_RESULT_FILES"
          }
        }
      }
    }

  }
}
