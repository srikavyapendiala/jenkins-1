def nexus(COMPONENT) {
  get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
  def get_branch_exec=sh(returnStdout: true, script: get_branch)
  def FILENAME=COMPONENT+'-'+get_branch_exec+'.zip'

  command = "curl -f -v -u admin:admin123 --upload-file ${FILENAME} http://172.31.14.124:8081/repository/${COMPONENT}/${FILENAME}"
  def execute_state=sh(returnStdout: true, script: command)
}

def make_artifacts(APP_TYPE, COMPONENT) {
  get_branch = "env | grep GIT_BRANCH | awk -F / '{print \$NF}' | xargs echo -n"
  def get_branch_exec=sh(returnStdout: true, script: get_branch)
  println("abc${get_branch_exec}abc")
  def FILENAME=COMPONENT+'-'+get_branch_exec+'.zip'
  if(APP_TYPE == "NGINX") {
    command = "cd static && zip -r ../${FILENAME} *"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "NODEJS") {
    command = "zip -r ${FILENAME} node_modules server.js"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "JAVA") {
    command = "cp target/*.jar ${COMPONENT}.jar && zip -r ${FILENAME} ${COMPONENT}.jar"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "PYTHON") {
    command = "zip -r ${FILENAME} payment.ini payment.py rabbitmq.py requirements.txt"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  }
}

def code_build(APP_TYPE, COMPONENT) {
  if(APP_TYPE == "NODEJS") {
    command = "npm install"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  } else if(APP_TYPE == "JAVA") {
    command = "mvn clean package"
    def execute_com=sh(returnStdout: true, script: command)
    print execute_com
  }
}
