pipeline {
  agent any
  environment {
    TMP_PATH="$JENKINS_HOME/BUILD_TMP/$GIT_BRANCH/$BUILD_NUMBER/test-jenkins"
  }
  stages {
    stage('Prepare') {
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

    stage('Build') {
      steps {
        withAnt(installation: 'gproc-ant') {
          dir("$TMP_PATH/testproject") {
            sh "ant jar"
          }
        }
      }
    }

    stage('Test') {
      steps {
        withAnt(installation: 'gproc-ant') {
          dir("$TMP_PATH/testproject-test") {
            sh "ant -buildfile build_test.xml test"
          }
        }
      }
    }

  }
}
