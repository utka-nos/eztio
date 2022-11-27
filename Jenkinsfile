pipeline{
    agent{
        label 'windows'
    }

    stages{

        stage('package'){
            steps{
                script{
                    bat '''
                        mvn clean package
                    '''
                }
            }
        }

        stage('deleting kubernetes objects') {
            steps{
                script{
                    bat '''
                        kubectl delete namespace eztio
                    '''
                }
            }
        }

        stage('deleting images') {
            steps{
               script{
                   try{
                       bat '''
                           minikube docker-env > temp.cmd
                           call temp.cmd
                           del temp.cmd

                           docker rmi product-app
                           docker rmi client-app
                       '''
                   }
               }
            }
        }

        stage('create images'){
            steps{
                script{
                    try{
                        bat '''
                            minikube docker-env > temp.cmd
                            call temp.cmd
                            del temp.cmd

                            docker build -t client-app ./client-service
                            docker build -t product-app .product-service
                        '''
                    }
                }
            }
        }

        stage('creating k8s objects'){
            steps{
                script{
                    try{
                        bat '''

                        '''
                    }
                }
            }
        }

    }
}