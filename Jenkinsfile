pipeline {
  agent any

  stages {
    stage('Prepare Artifacts') {
       steps {
       sh '''
         zip -r ../frontend.zip *
       '''
       }
    }
    stage('Upload Artifacts') {
      steps {
       sh '''
       curl -v -u admin:kavya --upload-file /var/lib/jenkins/workspace/frontend.zip http://172.31.7.43:8081/repository/frontend/frontend.zip
       '''
      }
    }
  }
}
