def call(Map params =[1]) {
  def args = [
          NEXUS_IP: '172.31.7.43'
  ]
  args << params

  pipeline {
    agent {
      label "${args.SLAVE_LABEL}"
    }

    environment {
      COMPONENT = "${args.COMPONENT}"
      NEXUS_IP = "${args.NEXUS_IP}"
      PROJECT_NAME = "${args.PROJECT_NAME}"
      SLAVE_LABEL = "${args.SLAVE_LABEL}"
    }

    stages {

      stage('Prepare Articrafts') {
        when {
          environment name: 'COMPONENT', value: 'frontend'
        }
        steps {
          sh '''
          echo ${COMPONENT}
          cd static
          zip -r ../${COMPONENT}.zip *
        '''
        }
      }

      stage('Upload Articrafts') {
        steps {
          sh '''
          curl -f -v -u admin:kavya --upload-file jenkins-1.zip http://NEXUS_IP:8081/repository/jenkins-1/jenkins-1.zip
        '''
        }
      }
    }
  }
}





